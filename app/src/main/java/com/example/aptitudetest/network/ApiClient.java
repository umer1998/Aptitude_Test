package com.example.aptitudetest.network;

import com.example.aptitudetest.info.RefreshTokenRequest;
import com.example.aptitudetest.info.RefreshTokenResponse;
import com.example.aptitudetest.info.UserDate;
import com.example.aptitudetest.utils.AppUtils;
import com.example.aptitudetest.utils.PreferenceUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victor.loading.BuildConfig;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Muhammad Ahmad on 07/08/2020.
 */
public class ApiClient {

    private static Retrofit retrofit = null;

    private static final String PARAM_HEADER_ACCESS_TOKEN_KEY = "X-Authorization";
    private static final String PARAM_HEADER_ANDROID_VERSION_KEY = "version-no";
    private static final String PARAM_HEADER_ANDROID_VERSION_KEY_VALUE
            = "15";
    public static final String BASE_URL = "http://13.95.146.210/aptitude_test/public/";
    private static String paramHeaderAccessTokenValue = "";
    private static String paramHeaderRefreshTokenValue = "";


    private ApiClient() {

    }

    public static Retrofit getApiClient() {



        final String bearerToken = paramHeaderAccessTokenValue;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient client = getHttpClient(bearerToken);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;
    }

    private static OkHttpClient getHttpClient(final String bearerToken) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(40, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header(PARAM_HEADER_ACCESS_TOKEN_KEY, bearerToken)
                        .header(PARAM_HEADER_ANDROID_VERSION_KEY,
                                PARAM_HEADER_ANDROID_VERSION_KEY_VALUE)
                        .header("refresh-token", paramHeaderRefreshTokenValue)
                        .method(original.method(), original.body())
                        .build();

                Response response = chain.proceed(request);

                if (response.code() == 403) {

                    String newToken = getRefreshToken(chain);

                    Request newRequest = original.newBuilder()
                            .header(PARAM_HEADER_ACCESS_TOKEN_KEY, newToken)
                            .header(PARAM_HEADER_ANDROID_VERSION_KEY,
                                    PARAM_HEADER_ANDROID_VERSION_KEY_VALUE)
                            .method(original.method(), original.body())
                            .build();

                    return getHttpClient(newToken).newCall(newRequest).execute();
                }

                return response;
            }
        });

        return httpClient.build();
    }

    private static String getRefreshToken(Interceptor.Chain chain) throws IOException {

        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
//        refreshTokenRequest.token = preferenceUtils.getUserDetail().token;
//        refreshTokenRequest.refreshToken = preferenceUtils.getUserDetail().refeshToken;
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        final String requestStr = gson.toJson(refreshTokenRequest);

        RequestBody requestBody =
                RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                        requestStr);
        Request newRequest =
                chain.request().newBuilder()
                        .url(BASE_URL + "authorize/getRefresh")
                        .method("GET", null)
                        .build();

        final String bearerToken = paramHeaderAccessTokenValue;

        Response newResponse2 = getHttpClient(bearerToken).newCall(newRequest).execute();

        assert newResponse2.body() != null;
        RefreshTokenResponse refreshTokenResponse = new Gson().fromJson(newResponse2.body().string(),
                RefreshTokenResponse.class);

        if (PreferenceUtils.getInstance().isLoginAsActive()) {

            UserDate userDetailInfo = PreferenceUtils.getInstance().getUserDetail();
            if (userDetailInfo == null ||
                    refreshTokenResponse.refreshDate == null ||
                    refreshTokenResponse.refreshDate.userDate == null) {
                AppUtils.logOutApplication();
                return null;
            }

            PreferenceUtils.getInstance()
                    .addUserDetail(refreshTokenResponse.refreshDate.userDate);

            return refreshTokenResponse.refreshDate.userDate.getAuthkey();

        }

        return null;
    }
}

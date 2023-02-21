package com.example.aptitudetest.business.impl;

import com.example.aptitudetest.business.AuthService;
import com.example.aptitudetest.delegate.ResponseCallBack;
import com.example.aptitudetest.info.JobRoles;
import com.example.aptitudetest.info.PostQuestionsAnswers;
import com.example.aptitudetest.info.QuestionData;
import com.example.aptitudetest.info.QuestionsAnswerResponce;
import com.example.aptitudetest.info.RegisterUserRequest;
import com.example.aptitudetest.info.responce.ResponceObject;
import com.example.aptitudetest.network.ApiClient;
import com.google.gson.Gson;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Muhammad Umer on 07/06/2020.
 * Mod by Muhammad Ahmad
 */
public class AuthBusiness {

    private AuthService authService;

    public AuthBusiness() {

        authService = ApiClient.getApiClient().create(AuthService.class);
    }

    public void register(final RegisterUserRequest requestInfo,
                         final ResponseCallBack<QuestionData> responseCallBack) {

        String json = new Gson().toJson(requestInfo);

        Call<ResponceObject<QuestionData>> call = authService.register(requestInfo.getName(), requestInfo.getPhoneno(),String.valueOf(requestInfo.getId()));

        call.enqueue(new Callback<ResponceObject<QuestionData>>() {
            @Override
            public void onResponse(Call<ResponceObject<QuestionData>> call,
                                   Response<ResponceObject<QuestionData>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null
                            && response.body().getMeta() != null
                            && response.body().getMeta().getCode() == 200) {
                        responseCallBack.onSuccess(response.body().getData());
                    } else if (response.body() != null
                            && response.body().getMeta() != null) {

                        responseCallBack.onFailure(response.body().getMeta().getMessage());
                    }
                } else {
                    responseCallBack.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponceObject<QuestionData>> call, Throwable t) {
                responseCallBack.onFailure(t.getMessage());
            }

        });
    }

    public void postAnswers(final PostQuestionsAnswers requestInfo,
                         final ResponseCallBack<QuestionsAnswerResponce> responseCallBack) {

        String json = new Gson().toJson(requestInfo);

        Call<ResponceObject<QuestionsAnswerResponce>> call = authService.postAnswers(requestInfo);

        call.enqueue(new Callback<ResponceObject<QuestionsAnswerResponce>>() {
            @Override
            public void onResponse(Call<ResponceObject<QuestionsAnswerResponce>> call,
                                   Response<ResponceObject<QuestionsAnswerResponce>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null
                            && response.body().getMeta() != null
                            && response.body().getMeta().getCode() == 200) {
                        responseCallBack.onSuccess(response.body().getData());
                    } else if (response.body() != null
                            && response.body().getMeta() != null) {

                        responseCallBack.onFailure(response.body().getMeta().getMessage());
                    }
                } else {
                    responseCallBack.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponceObject<QuestionsAnswerResponce>> call, Throwable t) {
                responseCallBack.onFailure(t.getMessage());
            }

        });
    }


    public void jobRole(final ResponseCallBack<JobRoles> responseCallBack) {



        Call<ResponceObject<JobRoles>> call = authService.jobRole();

        call.enqueue(new Callback<ResponceObject<JobRoles>>() {
            @Override
            public void onResponse(Call<ResponceObject<JobRoles>> call,
                                   Response<ResponceObject<JobRoles>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null
                            && response.body().getMeta() != null
                            && response.body().getMeta().getCode() == 200) {
                        responseCallBack.onSuccess(response.body().getData());
                    } else if (response.body() != null
                            && response.body().getMeta() != null) {

                        responseCallBack.onFailure(response.body().getMeta().getMessage());
                    }
                } else {
                    responseCallBack.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponceObject<JobRoles>> call, Throwable t) {
                responseCallBack.onFailure(t.getMessage());
            }

        });
    }



}



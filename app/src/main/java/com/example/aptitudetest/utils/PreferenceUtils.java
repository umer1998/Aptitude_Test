package com.example.aptitudetest.utils;

import static com.example.aptitudetest.utils.AppConstantsUtils.PreferenceUtils.PREFERENCE_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.aptitudetest.application.MainApplication;
import com.example.aptitudetest.info.JobRoles;
import com.example.aptitudetest.info.UserDate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;

/**
 * Created by Muhammad Ahmad on 07/06/2020.
 */
public class PreferenceUtils {
    private static PreferenceUtils instance;
    private static SharedPreferences prefs;


    private PreferenceUtils() {
    }

    public static PreferenceUtils getInstance() {

        if (instance == null) {
            instance = new PreferenceUtils();
            prefs = MainApplication.getAppContext()
                    .getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return instance;
    }

    public void setLoginAsActive(boolean isActive) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(AppConstantsUtils.ProfileUtils.LOGIN_AS_USER_ACTIVE, isActive);
        editor.apply();
    }

    public boolean isLoginAsActive() {
        return prefs.getBoolean(AppConstantsUtils.ProfileUtils.LOGIN_AS_USER_ACTIVE, false);
    }
    public void clearPreferences() {

        prefs.edit().clear().apply();
    }


    public void addUserDetail(UserDate userDetailInfo) {

        String userInfo = new Gson().toJson(userDetailInfo);

        try {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(AppConstantsUtils.ProfileUtils.JOB_DETAIL, userInfo);
            editor.apply();
        } catch (Exception e) {
            Log.e("ahmad", e.getMessage());
        }
    }


    public UserDate getUserDetail() {

        Gson gson = new Gson();
        Type type = new TypeToken<UserDate>() {
        }.getType();

        String json = prefs.getString(AppConstantsUtils.ProfileUtils.JOB_DETAIL, "");

        if (json.equals("")) {
            return null;
        }
        return gson.fromJson(json, type);
    }


    public void addJobDetail(JobRoles userDetailInfo) {

        String userInfo = new Gson().toJson(userDetailInfo);

        try {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(AppConstantsUtils.ProfileUtils.JOB_DETAIL, userInfo);
            editor.apply();
        } catch (Exception e) {
            Log.e("ahmad", e.getMessage());
        }
    }


    public JobRoles getJobDetail() {

        Gson gson = new Gson();
        Type type = new TypeToken<JobRoles>() {
        }.getType();

        String json = prefs.getString(AppConstantsUtils.ProfileUtils.JOB_DETAIL, "");

        if (json.equals("")) {
            return null;
        }
        return gson.fromJson(json, type);
    }
    public void setUSerID(String id) {
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString("user_id", id);
        prefsEditor.apply();
    }

    public String getUSerID() {

        return prefs.getString("user_id", null);
    }
}


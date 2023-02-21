package com.example.aptitudetest.business;



import com.example.aptitudetest.info.JobRoles;
import com.example.aptitudetest.info.PostQuestionsAnswers;
import com.example.aptitudetest.info.QuestionData;
import com.example.aptitudetest.info.QuestionsAnswerResponce;
import com.example.aptitudetest.info.RegisterUserRequest;
import com.example.aptitudetest.info.responce.ResponceObject;
import com.example.aptitudetest.utils.AppConstantsUtils;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Muhammad Umer on 07/06/2020.
 */
public interface AuthService {

    @GET(AppConstantsUtils.REGISTER)
    Call<ResponceObject<QuestionData>> register(@Query("name") String name,
                                                @Query("phone") String phone,
                                                @Query("job_role_id") String jobId);

    @POST(AppConstantsUtils.POST_ANSWER)
    Call<ResponceObject<QuestionsAnswerResponce>> postAnswers(@Body PostQuestionsAnswers loginRequestInfo);

    @GET(AppConstantsUtils.JOB_ROLE)
    Call<ResponceObject<JobRoles>> jobRole();
   
}

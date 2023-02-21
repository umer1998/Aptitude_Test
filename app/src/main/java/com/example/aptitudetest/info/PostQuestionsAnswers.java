package com.example.aptitudetest.info;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostQuestionsAnswers {

    @SerializedName("candidate_id")
    int userid;

    @SerializedName("quiz")
    List<QuestionsAnswer> list;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public List<QuestionsAnswer> getList() {
        return list;
    }

    public void setList(List<QuestionsAnswer> list) {
        this.list = list;
    }
}

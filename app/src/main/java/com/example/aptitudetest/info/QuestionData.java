package com.example.aptitudetest.info;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class QuestionData {

    @SerializedName("candidate_id")
    private int userId;

    @SerializedName("queries_count")
    private String totalQuestion;

    @SerializedName("time")
    private String totalTime;

    @SerializedName("questions")
    private ArrayList<Questions> questions;

    public QuestionData() {
    }

    public QuestionData(int userId, String totalQuestion, String totalTime, ArrayList<Questions> questions) {
        this.userId = userId;
        this.totalQuestion = totalQuestion;
        this.totalTime = totalTime;
        this.questions = questions;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(String totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public ArrayList<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Questions> questions) {
        this.questions = questions;
    }

}

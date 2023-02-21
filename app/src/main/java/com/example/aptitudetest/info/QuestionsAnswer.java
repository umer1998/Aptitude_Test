package com.example.aptitudetest.info;

import com.google.gson.annotations.SerializedName;

public class QuestionsAnswer {

    @SerializedName("selected")
    String answer;

    @SerializedName("question_id")
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

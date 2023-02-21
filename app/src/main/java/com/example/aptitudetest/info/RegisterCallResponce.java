package com.example.aptitudetest.info;

import com.example.aptitudetest.UI.fragments.QuestionFragment;

public class RegisterCallResponce {

    private QuestionData questionData;


    public RegisterCallResponce(QuestionData questionData) {
        this.questionData = questionData;
    }

    public QuestionData getQuestionData() {
        return questionData;
    }

    public void setQuestionData(QuestionData questionData) {
        this.questionData = questionData;
    }
}

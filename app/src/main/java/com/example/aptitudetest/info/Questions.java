package com.example.aptitudetest.info;

import com.google.gson.annotations.SerializedName;

public class Questions {



  @SerializedName("id")
  private int questionId;

  @SerializedName("image")
  private String questionImage;

  @SerializedName("statement")
  private String questionStatement;

  @SerializedName("option1")
  private String option1;

  @SerializedName("option2")
  private String option2;

  @SerializedName("option3")
  private String option3;

  @SerializedName("option4")
  private String option4;

  public Questions() {
  }

  public Questions(int questionId, String questionImage, String questionStatement,
                   String option1, String option2, String option3, String option4) {
    this.questionId = questionId;
    this.questionImage = questionImage;
    this.questionStatement = questionStatement;
    this.option1 = option1;
    this.option2 = option2;
    this.option3 = option3;
    this.option4 = option4;
  }

  public int getQuestionId() {
    return questionId;
  }

  public void setQuestionId(int questionId) {
    this.questionId = questionId;
  }

  public String getQuestionImage() {
    return questionImage;
  }

  public void setQuestionImage(String questionImage) {
    this.questionImage = questionImage;
  }

  public String getQuestionStatement() {
    return questionStatement;
  }

  public void setQuestionStatement(String questionStatement) {
    this.questionStatement = questionStatement;
  }

  public String getOption1() {
    return option1;
  }

  public void setOption1(String option1) {
    this.option1 = option1;
  }

  public String getOption2() {
    return option2;
  }

  public void setOption2(String option2) {
    this.option2 = option2;
  }

  public String getOption3() {
    return option3;
  }

  public void setOption3(String option3) {
    this.option3 = option3;
  }

  public String getOption4() {
    return option4;
  }

  public void setOption4(String option4) {
    this.option4 = option4;
  }
}

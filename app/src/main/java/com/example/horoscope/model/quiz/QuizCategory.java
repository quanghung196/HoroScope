package com.example.horoscope.model.quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizCategory {

    @SerializedName("quiz_name")
    @Expose
    private String quizName;
    @SerializedName("quiz_descript")
    @Expose
    private String quizDescript;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("quiz_id")
    @Expose
    private Integer quizId;
    @SerializedName("quiz_type")
    @Expose
    private String quizType;

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getQuizDescript() {
        return quizDescript;
    }

    public void setQuizDescript(String quizDescript) {
        this.quizDescript = quizDescript;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

}
package com.example.horoscope.model.quiz;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quiz {

    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("data")
    @Expose
    private List<QuizCategory> data = null;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public List<QuizCategory> getData() {
        return data;
    }

    public void setData(List<QuizCategory> data) {
        this.data = data;
    }

}

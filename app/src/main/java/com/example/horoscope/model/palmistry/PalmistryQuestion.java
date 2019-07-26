package com.example.horoscope.model.palmistry;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PalmistryQuestion {

    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("options")
    @Expose
    private List<PalmistryOption> options = null;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<PalmistryOption> getOptions() {
        return options;
    }

    public void setOptions(List<PalmistryOption> options) {
        this.options = options;
    }

}

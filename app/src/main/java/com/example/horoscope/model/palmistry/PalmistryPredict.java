package com.example.horoscope.model.palmistry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PalmistryPredict {

    @SerializedName("outro")
    @Expose
    private String outro;
    @SerializedName("questions")
    @Expose
    private List<PalmistryQuestion> questions = null;

    public PalmistryPredict(String outro, List<PalmistryQuestion> questions) {
        this.outro = outro;
        this.questions = questions;
    }

    public String getOutro() {
        return outro;
    }

    public void setOutro(String outro) {
        this.outro = outro;
    }

    public List<PalmistryQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<PalmistryQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "PalmistryPredict{" +
                "outro='" + outro + '\'' +
                '}';
    }
}

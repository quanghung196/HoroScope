package com.example.horoscope.model.palmistry;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PalmistryOption {

    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("answer")
    @Expose
    private String answer;

    private int position;
    private boolean isChecked;

    public PalmistryOption(String img, String text, String answer, int position, boolean isChecked) {
        this.img = img;
        this.text = text;
        this.answer = answer;
        this.position = position;
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}

package com.example.horoscope.model.palmistry;

public class Palmistry {
    private int palmImage;
    private String palmTitle;

    public Palmistry(int palmImage, String palmTitle) {
        this.palmImage = palmImage;
        this.palmTitle = palmTitle;
    }

    public int getPalmImage() {
        return palmImage;
    }

    public void setPalmImage(int palmImage) {
        this.palmImage = palmImage;
    }

    public String getPalmTitle() {
        return palmTitle;
    }

    public void setPalmTitle(String palmTitle) {
        this.palmTitle = palmTitle;
    }
}

package com.example.horoscope.model;

public class Zodiac {
    private int zodiacImage;
    private String zodiacName;
    private String zodiacDate;

    public Zodiac(int zodiacImage, String zodiacName, String zodiacDate) {
        this.zodiacImage = zodiacImage;
        this.zodiacName = zodiacName;
        this.zodiacDate = zodiacDate;
    }

    public Zodiac(int zodiacImage, String zodiacName) {
        this.zodiacImage = zodiacImage;
        this.zodiacName = zodiacName;
    }

    public int getZodiacImage() {
        return zodiacImage;
    }

    public void setZodiacImage(int zodiacImage) {
        this.zodiacImage = zodiacImage;
    }

    public String getZodiacName() {
        return zodiacName;
    }

    public void setZodiacName(String zodiacName) {
        this.zodiacName = zodiacName;
    }

    public String getZodiacDate() {
        return zodiacDate;
    }

    public void setZodiacDate(String zodiacDate) {
        this.zodiacDate = zodiacDate;
    }
}

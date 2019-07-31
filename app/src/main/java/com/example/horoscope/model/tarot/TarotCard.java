package com.example.horoscope.model.tarot;

public class TarotCard {
    private int tarotIndex;
    private int tarotNumber;
    private String tarotName;
    private String tarotImage;

    public TarotCard() {
    }

    public TarotCard(int tarotIndex, String tarotName, String tarotImage, int tarotNumber) {
        this.tarotIndex = tarotIndex;
        this.tarotName = tarotName;
        this.tarotImage = tarotImage;
        this.tarotNumber = tarotNumber;
    }

    public int getTarotNumber() {
        return tarotNumber;
    }

    public void setTarotNumber(int tarotNumber) {
        this.tarotNumber = tarotNumber;
    }

    public int getTarotIndex() {
        return tarotIndex;
    }

    public void setTarotIndex(int tarotIndex) {
        this.tarotIndex = tarotIndex;
    }

    public String getTarotName() {
        return tarotName;
    }

    public void setTarotName(String tarotName) {
        this.tarotName = tarotName;
    }

    public String getTarotImage() {
        return tarotImage;
    }

    public void setTarotImage(String tarotImage) {
        this.tarotImage = tarotImage;
    }
}

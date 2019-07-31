package com.example.horoscope.model.tarot;

public class TarotRead {
    private String positionHeader;
    private String interp;
    private String interpGeneral;
    private String interpHeader;

    public TarotRead(String positionHeader, String interp, String interpGeneral, String interpHeader) {
        this.positionHeader = positionHeader;
        this.interp = interp;
        this.interpGeneral = interpGeneral;
        this.interpHeader = interpHeader;
    }

    public String getPositionHeader() {
        return positionHeader;
    }

    public void setPositionHeader(String positionHeader) {
        this.positionHeader = positionHeader;
    }

    public String getInterp() {
        return interp;
    }

    public void setInterp(String interp) {
        this.interp = interp;
    }

    public String getInterpGeneral() {
        return interpGeneral;
    }

    public void setInterpGeneral(String interpGeneral) {
        this.interpGeneral = interpGeneral;
    }

    public String getInterpHeader() {
        return interpHeader;
    }

    public void setInterpHeader(String interpHeader) {
        this.interpHeader = interpHeader;
    }

    @Override
    public String toString() {
        return "" + positionHeader + '\'' +
                "" + interp + '\'' +
                "" + interpGeneral + '\'' +
                "" + interpHeader + '\'' +
                "";
    }
}

package com.example.horoscope.model;

import java.io.Serializable;

public class HoroScope implements Serializable {

    private String horoscope;
    private String sunsign;
    private int percent;

    private String year;
    private String date;
    private String week;
    private String month;
    private String title;

    public HoroScope(String horoscope, int percent) {
        this.horoscope = horoscope;
        this.percent = percent;
    }

    public HoroScope(String title, String horoscope, int percent) {
        this.horoscope = horoscope;
        this.percent = percent;
        this.title = title;
    }

    public HoroScope(String title, String horoscope) {
        this.title = title;
        this.horoscope = horoscope;
    }

    public HoroScope() {
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getHoroscope() {
        return horoscope;
    }

    public void setHoroscope(String horoscope) {
        this.horoscope = horoscope;
    }

    public String getSunsign() {
        return sunsign;
    }

    public void setSunsign(String sunsign) {
        this.sunsign = sunsign;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "HoroScope{" +
                ", percent=" + percent +
                ", title='" + title + '\'' +
                '}';
    }
}

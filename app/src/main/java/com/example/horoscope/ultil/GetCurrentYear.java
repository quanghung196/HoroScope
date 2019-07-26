package com.example.horoscope.ultil;

import java.util.Calendar;

public class GetCurrentYear {

    public static String getCurrentYear() {
        Calendar calendar = Calendar.getInstance();

        return String.valueOf(calendar.get(Calendar.YEAR));
    }
}

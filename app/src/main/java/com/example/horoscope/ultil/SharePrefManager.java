package com.example.horoscope.ultil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.horoscope.R;

import java.util.List;
import java.util.StringTokenizer;

public class SharePrefManager {

    /*public static void setPosition(Context context, int position) {
        SharedPreferences putPosition = context.getSharedPreferences("Position", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = putPosition.edit();
        editor.putInt("position", position);
        editor.commit();
    }

    public static int getPosition(Context context) {
        SharedPreferences getPosition = context.getSharedPreferences("Position", Context.MODE_PRIVATE);
        return getPosition.getInt("position", 0);
    }

    public static void deletePosition(Context context) {
        SharedPreferences delPosition = context.getSharedPreferences("Position", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = delPosition.edit();
        editor.clear();
        editor.commit();
    }*/

    public static String getZodiac(Context context) {
        SharedPreferences getZodiac = context.getSharedPreferences("Zodiac", Context.MODE_PRIVATE);
        return getZodiac.getString("zodiac", context.getString(R.string.default_zodiac_display_home));
    }

    public static void setZodiac(Context context, String zodiac) {
        SharedPreferences myZodiac = context.getSharedPreferences("Zodiac", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myZodiac.edit();
        editor.putString("zodiac", zodiac);
        editor.commit();
    }

    public static void setPermisstionState(Context context, int state) {
        SharedPreferences myState = context.getSharedPreferences("State", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myState.edit();
        editor.putInt("state", state);
        editor.commit();
    }

    public static int getPermisstionState(Context context) {
        SharedPreferences getState = context.getSharedPreferences("State", Context.MODE_PRIVATE);
        return getState.getInt("state", 0);
    }

    public static void putPostion(Context context, Activity activity, Class classs) {
        Intent intent = new Intent(activity, classs);
        intent.putExtra("Position", 1);
        context.startActivity(intent);
    }

    public static int getDate(Context context) {
        SharedPreferences getDate = context.getSharedPreferences("Date", Context.MODE_PRIVATE);
        return getDate.getInt("date", 1);
    }

    public static void setDate(Context context, int date) {
        SharedPreferences setDate = context.getSharedPreferences("Date", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = setDate.edit();
        editor.putInt("date", date);
        editor.commit();
    }

    public static void saveValueRandomList(Context context, List<Integer> randoms) {
        SharedPreferences randomValue = context.getSharedPreferences("Rand", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = randomValue.edit();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < randoms.size(); i++) {
            str.append(randoms.get(i)).append(",");
        }
        editor.putString("rand", str.toString());
        editor.commit();
    }

    public static void getValueSaved(Context context, List<Integer> randoms) {
        SharedPreferences getValue = context.getSharedPreferences("Rand", Context.MODE_PRIVATE);
        String savedString = getValue.getString("rand", "");
        if (savedString.trim() != "") {
            StringTokenizer st = new StringTokenizer(savedString, ",");
            if(randoms.size()<3){
                for (int i = 0; i < 3; i++) {
                    randoms.add(Integer.parseInt(st.nextToken()));
                }
            }
        }
    }

    public static boolean getBool(Context context) {
        SharedPreferences getBool = context.getSharedPreferences("Bool", Context.MODE_PRIVATE);
        return getBool.getBoolean("bool", true);
    }

    public static void setBool(Context context, boolean jump) {
        SharedPreferences setBool = context.getSharedPreferences("Bool", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = setBool.edit();
        editor.putBoolean("bool", jump);
        editor.commit();
    }
}

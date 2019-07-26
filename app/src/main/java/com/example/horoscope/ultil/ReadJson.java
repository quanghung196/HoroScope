package com.example.horoscope.ultil;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadJson {
    public static String readText(Context context, String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
        StringBuilder sb = new StringBuilder();
        String s = null;
        while ((s = br.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}

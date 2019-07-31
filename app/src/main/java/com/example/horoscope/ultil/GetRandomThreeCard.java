package com.example.horoscope.ultil;

import android.content.Context;
import android.util.Log;

import com.example.horoscope.model.tarot.TarotCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class GetRandomThreeCard {

    public static List<Integer> randoms = new ArrayList<>();
    public static TarotCard tarotCard;
    public static Context context;

    public GetRandomThreeCard(Context context) {
        this.context = context;
    }

    public static int getRandom() {
        Random rand = new Random();
        int n = rand.nextInt(78);
        return n;
    }

    public static void getListRandom() {
        randoms.clear();
        int count = 0;
        while (count < 3) {
            int n = getRandom();
            int check = 1;
            if (count == 0) {
                randoms.add(n);
                count++;
                Log.e("rand", n + "");
            } else {
                for (int i = 0; i < randoms.size(); i++) {
                    if (randoms.get(i) == n) {
                        break;
                    }
                    if (i == randoms.size() - 1) {
                        if (check == 1) {
                            randoms.add(n);
                            count++;
                            Log.e("rand", n + "");
                        }
                    }
                }
            }
        }
    }

    public static void getOneListPerDay() {
        Calendar calendar = Calendar.getInstance();
        int curentDay = calendar.get(Calendar.DATE);
        SharePrefManager.getValueSaved(context, randoms);
        if (curentDay == SharePrefManager.getDate(context)) {
            if (randoms.size() < 3) {
                getListRandom();
                SharePrefManager.saveValueRandomList(context, randoms);
                SharePrefManager.setDate(context, curentDay);
            }
        } else {
            getListRandom();
            SharePrefManager.saveValueRandomList(context, randoms);
            SharePrefManager.setDate(context, curentDay);
        }
    }

    public static List<TarotCard> tarotCards(Context context, String fileName) throws IOException, JSONException {
        getOneListPerDay();
        int count = 0;
        List<TarotCard> tarotCards = new ArrayList<>();
        fakeData(tarotCards);
        String jsonText = ReadJson.readText(context, fileName);
        JSONArray jsonRoot = new JSONArray(jsonText);

        for (int i = 0; i < jsonRoot.length(); i++) {
            JSONObject object = jsonRoot.getJSONObject(i);
            String tarotName = object.getString("name");
            int tarotIndex = object.getInt("index");
            JSONObject image = object.getJSONObject("images");
            String tarotImage = image.getString("full");
            if (count < 3) {
                for (int j = 0; j < randoms.size(); j++) {
                    if (i == randoms.get(j)) {
                        tarotCard = new TarotCard(tarotIndex, tarotName, tarotImage, i);
                        tarotCards.set(j, tarotCard);
                        count++;
                    }
                }
            } else {
                break;
            }
        }
        return tarotCards;
    }

    public static void fakeData(List<TarotCard> tarotCards) {
        tarotCards.add(new TarotCard(1, "", "", 1));
        tarotCards.add(new TarotCard(1, "", "", 1));
        tarotCards.add(new TarotCard(1, "", "", 1));
    }


}

package com.example.horoscope.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.horoscope.R;
import com.example.horoscope.activity.Activity_Articel;
import com.example.horoscope.activity.Activity_LoveCompatibility;
import com.example.horoscope.activity.Activity_Palmistry;
import com.example.horoscope.activity.Activity_Quotes;
import com.example.horoscope.activity.Activity_Tarot;
import com.example.horoscope.activity.Activity_ZodiacCompatibility;
import com.example.horoscope.ultil.SharePrefManager;

public class Fragment_Feature extends Fragment {

    private LinearLayout linearZodiac, linearLove, linearPalmistry, linearTarot, linearArticel, linearQuotes;

    public Fragment_Feature() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feature, container, false);

        init(view);
        buttonListener();

        return view;
    }

    private void init(View view) {
        linearZodiac = view.findViewById(R.id.linearZodiac);
        linearLove = view.findViewById(R.id.linearLove);
        linearPalmistry = view.findViewById(R.id.linearPalmistry);
        linearTarot = view.findViewById(R.id.linearTarot);
        linearArticel = view.findViewById(R.id.linearArticel);
        linearQuotes = view.findViewById(R.id.linearQuotes);
    }

    private void buttonListener() {
        linearZodiac.setOnClickListener(listener);
        linearLove.setOnClickListener(listener);
        linearPalmistry.setOnClickListener(listener);
        linearTarot.setOnClickListener(listener);
        linearArticel.setOnClickListener(listener);
        linearQuotes.setOnClickListener(listener);
    }

    private final View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.linearZodiac:
                    Intent intent1 = new Intent(getActivity(), Activity_ZodiacCompatibility.class);
                    startActivity(intent1);
                    break;
                case R.id.linearLove:
                    Intent intent2 = new Intent(getActivity(), Activity_LoveCompatibility.class);
                    startActivity(intent2);
                    break;
                case R.id.linearPalmistry:
                    Intent intent3 = new Intent(getActivity(), Activity_Palmistry.class);
                    startActivity(intent3);
                    break;
                case R.id.linearTarot:
                    Toast.makeText(getActivity(), "Comming soon", Toast.LENGTH_SHORT).show();
                    /*Intent intent4 = new Intent(getActivity(), Activity_Tarot.class);
                    startActivity(intent4);*/
                    break;
                case R.id.linearArticel:
                    Toast.makeText(getActivity(), "Comming soon", Toast.LENGTH_SHORT).show();
                    /*Intent intent5 = new Intent(getActivity(), Activity_Articel.class);
                    startActivity(intent5);*/
                    break;
                case R.id.linearQuotes:
                    Intent intent6 = new Intent(getActivity(), Activity_Quotes.class);
                    startActivity(intent6);
                    break;
            }
        }
    };
}

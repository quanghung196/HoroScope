package com.example.horoscope.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horoscope.MainActivity;
import com.example.horoscope.R;
import com.example.horoscope.adapter.ZodiacPicker_Adapter;
import com.example.horoscope.model.Zodiac;
import com.example.horoscope.ultil.ChangeColorStatusBar;
import com.example.horoscope.ultil.RecyclerItemClickListener;
import com.example.horoscope.ultil.SharePrefManager;

import java.util.ArrayList;
import java.util.List;

public class Activity_SelectZodiac extends AppCompatActivity {

    private RecyclerView recyclerZodiacPicker;
    private GridLayoutManager manager;
    private List<Zodiac> zodiacs;
    private ZodiacPicker_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChangeColorStatusBar.changeColorStatusBar(this);
        setContentView(R.layout.activity_select_zodiac);

        init();
        setUpRecyclerView();
        addValue();
        setAdapter();
    }


    private void init() {
        recyclerZodiacPicker = findViewById(R.id.recyclerZodiacPicker);
        zodiacs = new ArrayList<>();
    }

    private void setUpRecyclerView() {
        manager = new GridLayoutManager(this, 3);
        recyclerZodiacPicker.setHasFixedSize(true);
        recyclerZodiacPicker.setLayoutManager(manager);
        recyclerZodiacPicker.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerZodiacPicker, new RecyclerItemClickListener.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Zodiac zodiac = zodiacs.get(position);
                SharePrefManager.setZodiac(getApplicationContext(), zodiac.getZodiacName());
                Intent intent = new Intent(Activity_SelectZodiac.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public boolean onLongItemClick(View view, int position) {

                return false;
            }
        }));
    }

    private void setAdapter() {
        adapter = new ZodiacPicker_Adapter(zodiacs, this);
        recyclerZodiacPicker.setAdapter(adapter);
    }

    private void addValue() {
        zodiacs.add(new Zodiac(R.drawable.ic_aquarius, "Aquarius", "20/01 ~ 18/02"));
        zodiacs.add(new Zodiac(R.drawable.ic_pisces, "Pisces", "19/02 ~ 20/03"));
        zodiacs.add(new Zodiac(R.drawable.ic_aries, "Aries", "21/03 ~ 20/04"));
        zodiacs.add(new Zodiac(R.drawable.ic_taurus, "Taurus", "21/04 ~ 20/05"));
        zodiacs.add(new Zodiac(R.drawable.ic_gemini, "Gemini", "21/05 ~ 21/06"));
        zodiacs.add(new Zodiac(R.drawable.ic_cancer, "Cancer", "22/06 ~ 22/07"));
        zodiacs.add(new Zodiac(R.drawable.ic_leo, "Leo", "23/07 ~ 22/08"));
        zodiacs.add(new Zodiac(R.drawable.ic_virgo, "Virgo", "23/08 ~ 22/09"));
        zodiacs.add(new Zodiac(R.drawable.ic_libra, "Libra", "23/09 ~ 22/10"));
        zodiacs.add(new Zodiac(R.drawable.ic_scorpio, "Scorpio", "23/10 ~ 21/11"));
        zodiacs.add(new Zodiac(R.drawable.ic_sagittarius, "Sagittarius", "22/11 ~ 21/12"));
        zodiacs.add(new Zodiac(R.drawable.ic_capricom, "Capricorn", "22/12 ~ 19/01"));
    }

    public void imgBack(View view) {
        Intent intent = new Intent(Activity_SelectZodiac.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

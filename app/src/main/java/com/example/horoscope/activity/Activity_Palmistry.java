package com.example.horoscope.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horoscope.MainActivity;
import com.example.horoscope.R;
import com.example.horoscope.adapter.PalmPicker_Adapter;
import com.example.horoscope.model.palmistry.Palmistry;
import com.example.horoscope.ultil.RecyclerItemClickListener;
import com.example.horoscope.ultil.SharePrefManager;

import java.util.ArrayList;
import java.util.List;

public class Activity_Palmistry extends AppCompatActivity {

    private RecyclerView recyclerPalmistry;
    private Palmistry palmistry;
    private List<Palmistry> palmistries;
    private GridLayoutManager manager;
    private PalmPicker_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palmistry);

        init();
        setUpRecyclerView();
        addValue();
        setAdapter();
    }

    private void init() {
        recyclerPalmistry = findViewById(R.id.recyclerPalmPicker);
        palmistries = new ArrayList<>();
    }

    private void setUpRecyclerView() {
        manager = new GridLayoutManager(this, 2);
        recyclerPalmistry.setHasFixedSize(true);
        recyclerPalmistry.setLayoutManager(manager);
        recyclerPalmistry.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerPalmistry, new RecyclerItemClickListener.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Palmistry palmistry = palmistries.get(position);
                Intent intent = new Intent(Activity_Palmistry.this, Activity_Palmistry_Predict.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putString("palmName", palmistry.getPalmTitle());
                bundle.putInt("palmImage", palmistry.getPalmImage());
                intent.putExtra("Palmistry", bundle);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    private void setAdapter() {
        adapter = new PalmPicker_Adapter(palmistries, this);
        recyclerPalmistry.setAdapter(adapter);
    }

    private void addValue() {
        palmistries.add(new Palmistry(R.drawable.life_line, "Life Line"));
        palmistries.add(new Palmistry(R.drawable.head_line, "Head Line"));
        palmistries.add(new Palmistry(R.drawable.heart_line, "Heart Line"));
        palmistries.add(new Palmistry(R.drawable.fate_line, "Fate Line"));
        palmistries.add(new Palmistry(R.drawable.minor_line, "Minor Lines"));
        palmistries.add(new Palmistry(R.drawable.hand_shape, "Hand Shapes"));
        palmistries.add(new Palmistry(R.drawable.finger, "Fingers"));
        palmistries.add(new Palmistry(R.drawable.thumb, "Thumbs"));
    }

    public void imgBack(View view) {
        finish();
    }

}

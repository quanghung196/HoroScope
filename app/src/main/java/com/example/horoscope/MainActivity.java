package com.example.horoscope;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.horoscope.fragment.Fragment_Feature;
import com.example.horoscope.fragment.Fragment_HoroScope;
import com.example.horoscope.fragment.Fragment_Setting;
import com.example.horoscope.ultil.ChangeColorStatusBar;
import com.example.horoscope.ultil.SharePrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavView;
    final Fragment fragment1 = new Fragment_HoroScope();
    final Fragment fragment2 = new Fragment_Feature();
    final Fragment fragment3 = new Fragment_Setting();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChangeColorStatusBar.changeColorStatusBar(this);
        setContentView(R.layout.activity_main);

        init();
    }


    private void init() {
        bottomNavView = findViewById(R.id.bottomNavView);
        bottomNavView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        fm.beginTransaction().add(R.id.container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.container, fragment1, "1").commit();

        getData();

    }

    private void getData(){
        Intent intent = getIntent();
        if (intent.hasExtra("Position")) {
            bottomNavView.setSelectedItemId(R.id.nav_feature);
        } else {
            bottomNavView.setSelectedItemId(R.id.nav_horoScope);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.isChecked()) {
                return false;
            } else {
                switch (item.getItemId()) {
                    case R.id.nav_horoScope:
                        fm.beginTransaction().hide(active).show(fragment1).commit();
                        active = fragment1;
                        return true;

                    case R.id.nav_feature:
                        fm.beginTransaction().hide(active).show(fragment2).commit();
                        active = fragment2;
                        return true;

                    case R.id.nav_setting:
                        fm.beginTransaction().hide(active).show(fragment3).commit();
                        active = fragment3;
                        return true;
                }
                return false;
            }
        }
    };
}

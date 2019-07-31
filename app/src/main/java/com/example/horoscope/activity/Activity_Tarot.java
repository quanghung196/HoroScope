package com.example.horoscope.activity;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.example.horoscope.R;
import com.example.horoscope.adapter.Card_Adapter;
import com.example.horoscope.model.tarot.TarotCard;
import com.example.horoscope.ultil.ChangeColorStatusBar;
import com.example.horoscope.ultil.GetRandomThreeCard;
import com.example.horoscope.ultil.OverlapDecoration;
import com.example.horoscope.ultil.RecyclerItemClickListener;
import com.example.horoscope.ultil.SharePrefManager;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Activity_Tarot extends AppCompatActivity implements View.OnDragListener {

    private RecyclerView recyclerCard;
    private Card_Adapter adapter;
    private EasyFlipView flip1, flip2, flip3;
    private ImageView imgFront1, imgFront2, imgFront3, imgBack1, imgBack2, imgBack3;
    private LinearLayout linearTop, linearFlip, linearMain;
    private Button btnWatch;

    private int check = 0;
    private List<Integer> integers;
    private List<TarotCard> tarotCards;
    private int position1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChangeColorStatusBar.changeColorStatusBar(this);
        setContentView(R.layout.activity_tarot);

        init();
        setUpRecyclerView();
        addData();
        setAdapter();
        setDragListener();
        setBGImage();
    }

    private void init() {
        integers = new ArrayList<>();
        recyclerCard = findViewById(R.id.recyclerCard);
        btnWatch = findViewById(R.id.btnWatch);

        linearMain = findViewById(R.id.linearMain);
        linearTop = findViewById(R.id.linearTop);
        linearFlip = findViewById(R.id.linearFlip);

        flip1 = findViewById(R.id.flip1);
        flip2 = findViewById(R.id.flip2);
        flip3 = findViewById(R.id.flip3);

        imgFront1 = findViewById(R.id.imgFront1);
        imgFront2 = findViewById(R.id.imgFront2);
        imgFront3 = findViewById(R.id.imgFront3);

        imgBack1 = findViewById(R.id.imgBack1);
        imgBack2 = findViewById(R.id.imgBack2);
        imgBack3 = findViewById(R.id.imgBack3);

        flip1.setFlipEnabled(false);
        flip2.setFlipEnabled(false);
        flip3.setFlipEnabled(false);
    }

    private void addData() {
        for (int i = 0; i < 35; i++) {
            integers.add(i);
        }
    }

    private void setDragListener() {
        imgFront1.setOnDragListener(this);
        imgFront2.setOnDragListener(this);
        imgFront3.setOnDragListener(this);
    }

    private void setUpRecyclerView() {
        recyclerCard.addItemDecoration(new OverlapDecoration());
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerCard.setLayoutManager(manager);
        recyclerCard.setHasFixedSize(true);
        recyclerCard.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerCard, new RecyclerItemClickListener.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }

            @Override
            public boolean onLongItemClick(View view, int position) {
                position1 = position;

                integers.remove(position);
                adapter.notifyItemRemoved(position);

                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        }));
    }

    private void setAdapter() {
        adapter = new Card_Adapter(this, integers);
        recyclerCard.setAdapter(adapter);
    }

    public void imgBack(View view) {
        finish();
    }

    public void setBtnWatch(View view) {
        SharePrefManager.setBool(this, false);
        startActivity(new Intent(Activity_Tarot.this, Activity_ReadTarotCard.class));
        finish();
    }

    private void setBGImage() {
        try {
            GetRandomThreeCard randomThreeCard = new GetRandomThreeCard(this);
            tarotCards = randomThreeCard.tarotCards(this, "tarot.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Glide.with(this).load(tarotCards.get(0).getTarotImage()).into(imgBack1);
        Glide.with(this).load(tarotCards.get(1).getTarotImage()).into(imgBack2);
        Glide.with(this).load(tarotCards.get(2).getTarotImage()).into(imgBack3);
    }

    private void flipTheView() {
        flip1.setFlipEnabled(true);
        flip2.setFlipEnabled(true);
        flip3.setFlipEnabled(true);

        flip1.flipTheView();
        flip1.setFlipEnabled(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                flip2.flipTheView();
                flip2.setFlipEnabled(false);
            }
        }, 400);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                flip3.flipTheView();
                flip3.setFlipEnabled(false);
                Transition transition = new Slide(Gravity.LEFT);
                transition.setDuration(1000);
                transition.addTarget(btnWatch);
                TransitionManager.beginDelayedTransition(linearMain, transition);
                btnWatch.setVisibility(View.VISIBLE);
            }
        }, 800);
    }

    @Override
    public boolean onDrag(View view, DragEvent event) {
        int action = event.getAction();
        switch (action) {
            case DragEvent.ACTION_DRAG_ENTERED:
                view.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                view.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                view.getBackground().clearColorFilter();
                view.invalidate();
                return true;

            case DragEvent.ACTION_DROP:
                ClipData.Item item = event.getClipData().getItemAt(0);
                view.getBackground().clearColorFilter();
                view.invalidate();
                View v = (View) event.getLocalState();
                if (view.getId() == R.id.imgFront1) {
                    imgFront1.setBackgroundResource(R.drawable.card_back);
                    imgFront1.setEnabled(false);
                    check++;
                } else if (view.getId() == R.id.imgFront2) {
                    imgFront2.setBackgroundResource(R.drawable.card_back);
                    imgFront2.setEnabled(false);
                    check++;
                } else {
                    imgFront3.setBackgroundResource(R.drawable.card_back);
                    imgFront3.setEnabled(false);
                    check++;
                }
                if (check == 3) {
                    startAnim();
                }
                v.setVisibility(View.VISIBLE);
                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                view.getBackground().clearColorFilter();
                view.invalidate();
                if (event.getResult()) {
                    return true;
                } else {
                    ((View) event.getLocalState()).setVisibility(View.VISIBLE);
                    /*integers.add(position1, 0);
                    adapter.notifyItemInserted(position1);*/
                    return false;
                }
        }
        return true;
    }

    private void startAnim() {
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        slideUp.setFillAfter(true);
        slideUp.setFillBefore(false);
        slideUp.setFillEnabled(true);
        linearTop.startAnimation(slideUp);

        Animation slideUpV2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down_v2);
        slideUpV2.setFillAfter(true);
        slideUpV2.setFillBefore(false);
        slideUpV2.setFillEnabled(true);
        slideUpV2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //linearTop.setVisibility(View.GONE);
                flipTheView();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        linearFlip.startAnimation(slideUpV2);
    }
}

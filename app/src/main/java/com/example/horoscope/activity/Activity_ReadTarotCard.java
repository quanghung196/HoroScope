package com.example.horoscope.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horoscope.R;
import com.example.horoscope.adapter.Tarot_Adapter;
import com.example.horoscope.model.tarot.TarotCard;
import com.example.horoscope.model.tarot.TarotRead;
import com.example.horoscope.ultil.ChangeColorStatusBar;
import com.example.horoscope.ultil.GetRandomThreeCard;
import com.example.horoscope.ultil.ReadJson;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Activity_ReadTarotCard extends AppCompatActivity {

    private TextView tvCardName, tvCardPlace, tvCardRead;
    private DiscreteScrollView cardView;
    private List<TarotCard> tarotCards;
    private List<TarotRead> tarotReads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChangeColorStatusBar.changeColorStatusBar(this);
        setContentView(R.layout.activity_read_tarot_card);

        init();
        addValueToRead();
        setUpInfiniteScroll();
    }

    private void init() {
        try {
            GetRandomThreeCard randomThreeCard = new GetRandomThreeCard(this);
            tarotCards = randomThreeCard.tarotCards(this, "tarot.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        tvCardName = findViewById(R.id.tvCardName);
        tvCardRead = findViewById(R.id.tvCardRead);
        tvCardPlace = findViewById(R.id.tvCardPlace);
        cardView = findViewById(R.id.cardView);
    }

    private void addValueToRead() {
        tarotReads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            try {
                tarotReads.addAll(tarotReads1(this, "card_" + tarotCards.get(i).getTarotNumber() + ".json", i));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void setUpInfiniteScroll() {
        cardView.setOrientation(DSVOrientation.HORIZONTAL);
        cardView.setAdapter(new Tarot_Adapter(this, tarotCards));
        cardView.setItemTransitionTimeMillis(350);
        cardView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
        cardView.scrollToPosition(1);
        cardView.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
                tvCardName.setText(tarotCards.get(position).getTarotName());
                if (position == 0) {
                    tvCardPlace.setText("Self");
                } else if (position == 1) {
                    tvCardPlace.setText("Situation");
                } else {
                    tvCardPlace.setText("Challengers");
                }
                tvCardRead.setText(tarotReads.get(position).toString());
            }
        });
    }

    public static List<TarotRead> tarotReads1(Context context, String fileName, int index) throws IOException, JSONException {
        List<TarotRead> tarotReads = new ArrayList<>();
        String jsonText = ReadJson.readText(context, fileName);
        JSONArray jsonRoot = new JSONArray(jsonText);
        JSONObject object = jsonRoot.getJSONObject(index);
        String positionHeader = object.getString("position_header");
        String interp = object.getString("interp");
        String interpGeneral = object.getString("interp_general");
        String interpHeader = object.getString("interp_header");
        tarotReads.add(new TarotRead(positionHeader, interp, interpGeneral, interpHeader));
        return tarotReads;
    }

    public void imgBack(View view) {
        finish();
    }
}

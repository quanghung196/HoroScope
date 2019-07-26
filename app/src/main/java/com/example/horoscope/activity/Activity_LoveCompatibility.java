package com.example.horoscope.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horoscope.MainActivity;
import com.example.horoscope.R;
import com.example.horoscope.adapter.PredictCompatibility_Adapter;
import com.example.horoscope.adapter.ZodiacPickerFeature_Adapter;
import com.example.horoscope.model.HoroScope;
import com.example.horoscope.model.Zodiac;
import com.example.horoscope.ultil.AdmodManager;
import com.example.horoscope.ultil.ReadJson;
import com.example.horoscope.ultil.RecyclerItemClickListener;
import com.example.horoscope.ultil.SharePrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Activity_LoveCompatibility extends AppCompatActivity {

    private ImageView imgMale, imgFemale;
    private TextView tvMale, tvFemale;
    private LinearLayout linearPick;
    private RelativeLayout relativeResult;
    private RecyclerView recyclerZodiac, recyclerPredict;
    private List<Zodiac> zodiacs;
    private List<HoroScope> horoScopes;
    private Zodiac zodiac;
    private GridLayoutManager manager, manager2;
    private ZodiacPickerFeature_Adapter adapter;
    private PredictCompatibility_Adapter adapter2;
    private String male, female;

    //dialog
    private TextView tvTitle, tvContent, tvOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love_compatibility);

        init();
        addValue();
        setUpRecyclerZodiac();
        setUpRecyclerResult();
        setAdapter();
    }

    private void init() {
        linearPick = findViewById(R.id.linearPick);
        relativeResult = findViewById(R.id.relativeResult);
        tvMale = findViewById(R.id.tvMale);
        tvFemale = findViewById(R.id.tvFemale);
        imgMale = findViewById(R.id.imgMale);
        imgFemale = findViewById(R.id.imgFemale);
        recyclerZodiac = findViewById(R.id.recyclerZodiac);
        recyclerPredict = findViewById(R.id.recyclerPredict);
        zodiacs = new ArrayList<>();
        horoScopes = new ArrayList<>();

    }

    private void setUpRecyclerResult() {
        manager2 = new GridLayoutManager(this, 1);
        recyclerPredict.setHasFixedSize(true);
        recyclerPredict.setLayoutManager(manager2);
    }

    private void setUpRecyclerZodiac() {
        manager = new GridLayoutManager(this, 3);
        recyclerZodiac.setHasFixedSize(true);
        recyclerZodiac.setLayoutManager(manager);
        recyclerZodiac.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerZodiac, new RecyclerItemClickListener.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                zodiac = zodiacs.get(position);
                if (imgMale.getDrawable() != null) {
                    tvFemale.setText(zodiac.getZodiacName());
                    imgFemale.setImageResource(zodiac.getZodiacImage());
                    female = zodiac.getZodiacName().toLowerCase();
                } else {
                    tvMale.setText(zodiac.getZodiacName());
                    imgMale.setImageResource(zodiac.getZodiacImage());
                    male = zodiac.getZodiacName().toLowerCase();
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    private void setAdapter() {
        adapter = new ZodiacPickerFeature_Adapter(zodiacs, this, 2);
        recyclerZodiac.setAdapter(adapter);
    }

    private void setAdapter2() {
        adapter2 = new PredictCompatibility_Adapter(horoScopes, this);
        recyclerPredict.setAdapter(adapter2);
    }

    private void addValue() {
        zodiacs.add(new Zodiac(R.drawable.ic_aquarius_white, "Aquarius"));
        zodiacs.add(new Zodiac(R.drawable.ic_pisces_white, "Pisces"));
        zodiacs.add(new Zodiac(R.drawable.ic_aries_white, "Aries"));
        zodiacs.add(new Zodiac(R.drawable.ic_taurus_white, "Taurus"));
        zodiacs.add(new Zodiac(R.drawable.ic_gemini_white, "Gemini"));
        zodiacs.add(new Zodiac(R.drawable.ic_cancer_white, "Cancer"));
        zodiacs.add(new Zodiac(R.drawable.ic_leo_white, "Leo"));
        zodiacs.add(new Zodiac(R.drawable.ic_virgo_white, "Virgo"));
        zodiacs.add(new Zodiac(R.drawable.ic_libra_white, "Libra"));
        zodiacs.add(new Zodiac(R.drawable.ic_scorpio_white, "Scorpio"));
        zodiacs.add(new Zodiac(R.drawable.ic_sagittarius_white, "Sagittarius"));
        zodiacs.add(new Zodiac(R.drawable.ic_capricorn_white, "Capricorn"));
    }

    public void btnCheck(View view) {
        if (imgMale.getDrawable() != null && imgFemale.getDrawable() != null) {
            AdmodManager.forceEventShowAds();
            try {
                horoScopes = readCompanyJSONFile(this, male + "_" + female + ".json");
                setAdapter2();
                linearPick.setVisibility(View.GONE);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            showAlertDialog();
        }
    }

    private void showAlertDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_alert_pick);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        String title = "Horoscope";
        String content = "Please choose zodiac";
        String ok = "OK";
        tvTitle = dialog.findViewById(R.id.tvTitle);
        tvContent = dialog.findViewById(R.id.tvContent);
        tvOK = dialog.findViewById(R.id.tvOK);

        tvTitle.setText(title);
        tvContent.setText(content);
        tvOK.setText(ok);

        setColorTextView(tvTitle, title);
        setColorTextView(tvContent, content);
        setColorTextView(tvOK, ok);

        tvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void setColorTextView(TextView textView, String text) {
        TextPaint paint = textView.getPaint();
        float width = paint.measureText(text);

        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        Color.parseColor("#47066a"),
                        Color.parseColor("#784fa0"),
                        Color.parseColor("#2016a8"),
                        Color.parseColor("#1295c9"),
                        Color.parseColor("#6dcaef")
                }, null, Shader.TileMode.MIRROR);
        textView.getPaint().setShader(textShader);
    }

    public List<HoroScope> readCompanyJSONFile(Context context, String fileName) throws IOException, JSONException {
        List<HoroScope> horoScopes = new ArrayList<>();
        String jsonText = ReadJson.readText(context, fileName);
        JSONObject jsonRoot = new JSONObject(jsonText);
        int percent= jsonRoot.getInt("percent");
        String content= jsonRoot.getString("content");

        HoroScope horoScopee = new HoroScope(content, percent);
        horoScopes.add(horoScopee);

        return horoScopes;
    }

    public void imgBack(View view) {
        finish();
    }

    public void linearMale(View view) {
        if (linearPick.getVisibility() == View.GONE) {

        } else {
            tvMale.setText("");
            imgMale.setImageResource(0);
        }
    }

    public void linearFemale(View view) {
        if (linearPick.getVisibility() == View.GONE) {

        } else {
            tvFemale.setText("");
            imgFemale.setImageResource(0);
        }
    }

    public void btnDone(View view) {
        finish();
    }

    public void btnTryAgain(View view) {
        linearPick.setVisibility(View.VISIBLE);
    }
}

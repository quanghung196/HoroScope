package com.example.horoscope.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.horoscope.R;
import com.example.horoscope.activity.Activity_Privacy;
import com.example.horoscope.ultil.SharePrefManager;

import org.pcc.webviewOverlay.WebViewOverlay;


public class Fragment_Setting extends Fragment {

    private ImageView imgZodiacPicker;
    private TextView tvZodiacName;
    private String zodiac, appPackageName;

    private LinearLayout linearPrivacy;
    private LinearLayout linearRateApp;
    private LinearLayout linearShare;
    private LinearLayout linearSupport;
    private LinearLayout linearAbout;

    WebViewOverlay webViewOverlay;

    public Fragment_Setting() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        init(view);
        tvZodiacName.setText(zodiac);
        changeImageViewByZodiac();
        setColorTextView();
        buttonListener();
        return view;
    }

    private void init(View view) {
        appPackageName = getActivity().getPackageName();
        zodiac = SharePrefManager.getZodiac(getActivity());
        imgZodiacPicker = view.findViewById(R.id.imgZodiac);
        tvZodiacName = view.findViewById(R.id.tvZodiacName);
        linearPrivacy = view.findViewById(R.id.linearPrivacy);
        linearRateApp = view.findViewById(R.id.linearRateApp);
        linearShare = view.findViewById(R.id.linearShare);
        linearSupport = view.findViewById(R.id.linearSupport);
        linearAbout = view.findViewById(R.id.linearAbout);

        webViewOverlay = new WebViewOverlay(getContext());
    }

    private void buttonListener() {
        linearPrivacy.setOnClickListener(listener);
        linearRateApp.setOnClickListener(listener);
        linearShare.setOnClickListener(listener);
        linearSupport.setOnClickListener(listener);
        linearAbout.setOnClickListener(listener);
    }

    private final View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.linearPrivacy:
                    Intent intent = new Intent(getActivity(), Activity_Privacy.class);
                    intent.putExtra("lala", 1);
                    startActivity(intent);
                    /*String url = "https://suntechltd.net/privacy-policy/";
                    webViewOverlay.loadWebViewOverlay(url, null, "Privacy Policy");*/
                    break;
                case R.id.linearRateApp:
                    rateApp();
                    break;
                case R.id.linearShare:
                    shareApp();
                    break;
                case R.id.linearSupport:
                    sendMail();
                    break;
                case R.id.linearAbout:
                    Intent intent2 = new Intent(getActivity(), Activity_Privacy.class);
                    intent2.putExtra("lala", 2);
                    startActivity(intent2);
                    break;
            }
        }
    };

    private void sendMail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        String[] recipients = {getString(R.string.email)};
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.setType("text/html");
        intent.setPackage("com.google.android.gm");
        startActivity(Intent.createChooser(intent, "Send mail"));
    }

    private void rateApp() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    private void shareApp() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
        i.putExtra(Intent.EXTRA_TEXT, "Down load app on: https://play.google.com/store/apps/details?id=" + appPackageName);
        startActivity(Intent.createChooser(i, "Share URL"));
    }

    private void setColorTextView() {
        TextPaint paint = tvZodiacName.getPaint();
        float width = paint.measureText(zodiac);

        Shader textShader = new LinearGradient(0, 0, width, tvZodiacName.getTextSize(),
                new int[]{
                        Color.parseColor("#F97C3C"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),
                }, null, Shader.TileMode.CLAMP);
        tvZodiacName.getPaint().setShader(textShader);
    }

    private void changeImageViewByZodiac() {
        if (zodiac.contains("Aquarius")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_aquarius);
        } else if (zodiac.contains("Pisces")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_pisces);
        } else if (zodiac.contains("Aries")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_aries);
        } else if (zodiac.contains("Taurus")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_taurus);
        } else if (zodiac.contains("Gemini")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_gemini);
        } else if (zodiac.contains("Cancer")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_cancer);
        } else if (zodiac.contains("Leo")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_leo);
        } else if (zodiac.contains("Virgo")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_virgo);
        } else if (zodiac.contains("Libra")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_libra);
        } else if (zodiac.contains("Scorpio")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_scorpio);
        } else if (zodiac.contains("Sagittarius")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_sagittarius);
        } else if (zodiac.contains("Capricorn")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_capricom);
        }
    }

}

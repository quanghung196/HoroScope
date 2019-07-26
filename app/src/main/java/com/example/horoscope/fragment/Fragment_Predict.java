package com.example.horoscope.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horoscope.R;
import com.example.horoscope.adapter.Predict_Adapter;
import com.example.horoscope.model.HoroScope;
import com.example.horoscope.network.ApiService;
import com.example.horoscope.network.RetrofitClient;
import com.example.horoscope.ultil.ReadJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Predict extends Fragment {
    private static final String POSITION = "position";
    private static final String ZODIAC_NAME = "zodiacName";
    private static final String TAG = "PredictFragment";
    private int position;
    private String zodiacName;
    private String time;
    private Context context;
    private RecyclerView recyclerPredict;
    private Predict_Adapter adapter;
    private GridLayoutManager manager;

    private ApiService api;
    private HoroScope horoScope;
    private List<HoroScope> horoScopes;

    public Fragment_Predict() {
        // Required empty public constructor
    }

    public static Fragment_Predict newInstance(int position, String zodiacName) {
        Fragment_Predict fragment = new Fragment_Predict();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        args.putString(ZODIAC_NAME, zodiacName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        if (getArguments() != null) {
            position = getArguments().getInt(POSITION);
            zodiacName = getArguments().getString(ZODIAC_NAME).toLowerCase();
        }
    }

    private void getPredictByPosition() {

        if (position == 0) {
            try {
                horoScopes = readCompanyJSONFile(getActivity(), zodiacName + ".json");
                setAdapter();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (position == 1) {
            time = "today";
            load(time, zodiacName);
        } else if (position == 2) {
            time = "week";
            load(time, zodiacName);
        } else if (position == 3) {
            time = "month";
            load(time, zodiacName);
        } else if (position == 4) {
            time = "year";
            load(time, zodiacName);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_predict, container, false);
        init(view);
        getPredictByPosition();

        return view;
    }


    private void init(View view) {
        horoScope = new HoroScope();
        horoScopes = new ArrayList<>();
        api = RetrofitClient.createService(ApiService.class, getActivity(), "http://horoscope-api.herokuapp.com/");
        recyclerPredict = view.findViewById(R.id.recyclerPredict);
        manager = new GridLayoutManager(getActivity(), 1);
        recyclerPredict.setHasFixedSize(true);
        recyclerPredict.setLayoutManager(manager);
    }

    private void setAdapter() {
        adapter = new Predict_Adapter(horoScopes, getActivity(), position);
        recyclerPredict.setAdapter(adapter);
    }

    private void load(String time, String zodiac) {
        Call<HoroScope> call = api.getHoroScopeByZodiacAndTime(time, zodiac);
        call.enqueue(new Callback<HoroScope>() {
            @Override
            public void onResponse(Call<HoroScope> call, Response<HoroScope> response) {
                if (response.isSuccessful()) {
                    horoScope = response.body();
                } else {
                    Log.e(TAG, "onResponse " + response.code());
                }
                horoScopes.add(horoScope);
                setAdapter();
            }

            @Override
            public void onFailure(Call<HoroScope> call, Throwable t) {
                Log.e(TAG, " onFailure " + t.getMessage());
            }
        });
    }

    public static List<HoroScope> readCompanyJSONFile(Context context, String fileName) throws IOException, JSONException {
        List<HoroScope> horoScopes = new ArrayList<>();
        String jsonText = ReadJson.readText(context, fileName);
        JSONObject jsonRoot = new JSONObject(jsonText);
        JSONArray jsonArray = jsonRoot.getJSONArray("Fortune");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String title = object.getString("title");
            String horoscope = object.getString("content");
            HoroScope horoScope = new HoroScope(title, horoscope);
            horoScopes.add(horoScope);
        }
        return horoScopes;
    }
}

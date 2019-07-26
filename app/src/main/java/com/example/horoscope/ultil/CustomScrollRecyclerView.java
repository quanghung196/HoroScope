package com.example.horoscope.ultil;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

public class CustomScrollRecyclerView extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public CustomScrollRecyclerView(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }

    @Override
    public boolean canScrollHorizontally() {
        return isScrollEnabled && super.canScrollHorizontally();
    }
}

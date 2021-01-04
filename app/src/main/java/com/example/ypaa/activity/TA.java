package com.example.ypaa.activity;

import android.os.Bundle;

import com.example.ypaa.R;
import com.example.ypaa.databinding.ActivityABinding;

public class TA extends TTA<ActivityABinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_t;
    }
}
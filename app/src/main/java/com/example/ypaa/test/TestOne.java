package com.example.ypaa.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import android.os.Bundle;

import com.example.ypaa.R;
import com.example.ypaa.databinding.ActivityTestOneBinding;

public class TestOne extends Base<ActivityTestOneBinding> {
    ActivityTestOneBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestOneBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_test_one);
    }

    @Override
    public ViewBinding getBinding(ViewBinding viewBinding) {
        return binding;
    }
}
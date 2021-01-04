package com.example.ypaa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ypaa.R;

public class C extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        Log.d("abcd", "onCreate:B ");
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("abcd", "onNewIntent:B ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("abcd", "onStart:B ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("abcd", "onRestart:B ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("abcd", "onResume:B ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("abcd", "onPause:B ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("abcd", "onStop:B ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("abcd", "onDestroy:B ");
    }
}

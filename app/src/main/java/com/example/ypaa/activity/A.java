package com.example.ypaa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ypaa.R;
import com.example.ypaa.databinding.ActivityABinding;

public class A extends AppCompatActivity {
    Button btn_ckick;
    TextView tv_log;
    StringBuilder stringBuilder = new StringBuilder();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityABinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_a);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        btn_ckick = findViewById(R.id.btn_ckick);
        btn_ckick.setText("跳转B");
        btn_ckick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(A.this, B.class));
            }
        });
        tv_log = findViewById(R.id.tv_log);
        stringBuilder.append("onCreate->");
        Log.d("abcd", "onCreate:A ");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        stringBuilder.append("onNewIntent->");
        Log.d("abcd", "onNewIntent:A ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        stringBuilder.append("onNewIntent->");
        Log.d("abcd", "onStart:A ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        stringBuilder.append("onNewIntent->");
        Log.d("abcd", "onRestart:A ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        stringBuilder.append("onNewIntent->");
        Log.d("abcd", "onResume:A ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        stringBuilder.append("onNewIntent->");
        Log.d("abcd", "onPause:A ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        stringBuilder.append("onStop->");
        Log.d("abcd", "onStop:A ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stringBuilder.append("onDestroy->");
        Log.d("abcd", "onDestroy:A ");
    }
}

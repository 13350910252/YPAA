package com.example.ypaa.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ypaa.R;
import com.example.ypaa.view.TouchPullView;

public class CustomRefreshActivity extends AppCompatActivity {
    TouchPullView tp_pull;
    private static final float PULL_MAX_Y = 600;
    private float mTouchPullY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_refresh);

        tp_pull = findViewById(R.id.tp_pull);
        findViewById(R.id.ll_main).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        mTouchPullY = motionEvent.getY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        float y = motionEvent.getY();
                        if (y >= mTouchPullY) {
                            float moveSize = y - mTouchPullY;
                            float progress = moveSize >= mTouchPullY?1:moveSize/mTouchPullY;
                            tp_pull.setmProgress(progress);
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        tp_pull.release();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}

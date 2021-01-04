package com.example.ypaa.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

public class DD extends RelativeLayout {
    public DD(Context context) {
        this(context, null);
    }

    public DD(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DD(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public DD(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d("abcd", "onLayout: " + l + "," + t + "," + r + "," + b + ",");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode= MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        Log.d("abcd", "onMeasure: "+mode);
        Log.d("abcd", "onMeasure: "+size);
        Log.d("abcd", "onMeasure: " + widthMeasureSpec + "," + heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("abcd", "onDraw: ");
    }
}

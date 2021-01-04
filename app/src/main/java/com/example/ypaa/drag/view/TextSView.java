package com.example.ypaa.drag.view;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;


public class TextSView extends LinearLayout {
    public TextSView(Context context) {
        this(context, null);
    }

    public TextSView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextSView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    long start;
//    long end;
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_MOVE:
//                start = System.currentTimeMillis();
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                end = System.currentTimeMillis();
//                break;
//        }
//        if (end - start < 1000 && end - start > 0) {
//            return true;
//        }
//        return false;
//    }
}

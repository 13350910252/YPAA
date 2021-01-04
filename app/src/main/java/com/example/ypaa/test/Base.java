package com.example.ypaa.test;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public class Base<T> extends AppCompatActivity {
    protected T binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public <T> void set(Class<T> tClass){

    }
    public ViewBinding getBinding(ViewBinding viewBinding){
        
        return viewBinding;
    }

}

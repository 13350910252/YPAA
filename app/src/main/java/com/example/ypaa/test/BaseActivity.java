package com.example.ypaa.test;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

//public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity {
//    public ActivityBaseBinding baseBinding;
//    public T viewBinding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        baseBinding = ActivityBaseBinding.inflate(getLayoutInflater());
//        setContentView(baseBinding.getRoot());
//        viewBinding = getViewBinding();
//    }
//
//    protected abstract T getViewBinding();
//
//    public void setToolbarTitle(CharSequence title) {
//        baseBinding.toolbar.setTitle(title);
//    }
//
//    public void setToolbarTitle(@StringRes int stringId) {
//        baseBinding.toolbar.setTitle(stringId);
//    }
//}
public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity {
    public T binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            try {
                Class<T> clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];

                Method method = clazz.getMethod("inflate", LayoutInflater.class);
                binding = (T) method.invoke(null, getLayoutInflater());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            setContentView(binding.getRoot());
        }
        init();
    }

    abstract void init();
}


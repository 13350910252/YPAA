package com.example.ypaa.test;

import android.os.Bundle;

import com.example.ypaa.databinding.ActivityTestTwoBinding;

//public class TestTwo extends BaseActivity<ActivityTestTwoBinding> {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_two);
//
//        viewBinding.tvTest.setText("test");
//        setToolbarTitle("dd");
//    }
//
//    @Override
//    protected ActivityTestTwoBinding getViewBinding() {
//        return ActivityTestTwoBinding.inflate(getLayoutInflater(), baseBinding.getRoot(), true);
//    }
//
//}
public class TestTwo extends BaseActivity<ActivityTestTwoBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    void init() {
        binding.tvTest.setText("HAHAHA");
    }

}
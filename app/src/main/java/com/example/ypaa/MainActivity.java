package com.example.ypaa;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TestClass testClass = new TestClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testFor();
        Button btn = (Button) findViewById(R.id.button);
        testClass = new TestClass();
        if (testClass.getFlag() == null)
            Log.d("onCreate", "the  value is null");
        else
            Log.d("onCreate", "the value is" + testClass.getFlag());
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DemoThread demothread = new DemoThread();
                demothread.start();

                DemoThread2 demothread2 = new DemoThread2();
                demothread2.start();

                Random r = new Random();
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);//睡眠1秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (testClass) {
                        testClass.setFlag("for:" + Integer.toString(i));
                        testClass.notify();


                        //testClass.notify();


                        Log.d("onCreate:", Integer.toString(i));
                    }
                }
            }
        });

    }

    public class TestClass {
        private String flag;

        public TestClass() {
            setFlag(null);
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getFlag() {
            return flag;
        }
    }

    private class DemoThread extends Thread {
        @Override
        public void run() {
            Random r = new Random();
            while (true) {
                synchronized (testClass) {
                    try {
                        Log.d("onCreate", "111wait");
                        testClass.wait();
                        Log.d("onCreate", "111testClassvalue is " + testClass.getFlag());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("onCreate", "111while!!");
            }
        }
    }

    private class DemoThread2 extends Thread {
        @Override
        public void run() {
            Random r = new Random();
            while (true) {
                synchronized (testClass) {
                    try {
                        Log.d("onCreate", "22wait");
                        testClass.wait();
                        Log.d("onCreate", "22testClassvalue is " + testClass.getFlag());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("onCreate", "111while!!");
            }
        }
    }
    private void testFor(){
        for (int i = 0;i<10;i++){
            if (i == 4){
                return;
            }
            Log.d("abcd", "testFor: "+i);
        }
        Log.d("abcd", "testFor: "+"end");
    }

}


package com.noprom.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by noprom on 2015/1/9.
 */
public class SecondActivity extends Activity{

    private Handler handler = new Handler();


    class MyThread extends Thread{

        public Handler handler;
        @Override
        public void run() {
            // 创建Looper
            Looper.prepare();
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    Log.i("TAG","current thread "+Thread.currentThread());
                }
            };
            Looper.loop();
        }
    }

    private MyThread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("Hello hanlder~");
        setContentView(textView);


        thread = new MyThread();
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.handler.sendEmptyMessage(1);
    }
}

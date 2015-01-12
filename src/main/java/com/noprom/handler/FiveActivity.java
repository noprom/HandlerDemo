package com.noprom.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;


public class FiveActivity extends Activity {

    private TextView textView;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        textView = (TextView) findViewById(R.id.id_textview);

        // 在子线程中更新UI
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("ok");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}

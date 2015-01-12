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
            textView.setText("ok in handler2");
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
                    updateUI();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     * 第三种方式
     * 实现runOnUiThread的runnable接口
     */
    private void updateUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("ok in updateUI");
            }
        });
    }

    /**
     * 第二重方式
     * 直接发送一个message
     */
    private void handler2() {
        handler.sendEmptyMessage(1);
    }

    /**
     * 第一种方式
     * 通过handler的post方法更新UI
     */
    private void handler1() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("ok in handler1");
            }
        });
    }
}

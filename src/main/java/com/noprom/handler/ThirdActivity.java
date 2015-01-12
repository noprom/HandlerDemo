package com.noprom.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;



public class ThirdActivity extends ActionBarActivity {

    private TextView textView;

    private HandlerThread thread;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setText("handler Thread");
        setContentView(textView);

        thread = new HandlerThread("handler Thread");
        thread.start();

        handler = new Handler(thread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.i("TAG","current thread ------------>"+Thread.currentThread());
            }
        };
        handler.sendEmptyMessage(1);

    }
}

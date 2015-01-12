package com.noprom.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by noprom.
 */
public class FourActivity extends Activity implements View.OnClickListener {

    // 按钮初始化
    private Button btn_send;
    private Button btn_stop;

    // 主线程handler
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 向子线程发送消息
            Message message = new Message();
            threadHandler.sendMessageDelayed(message, 1000);

            Log.i("TAG", "main handler");
        }
    };

    // 子线程handler
    private Handler threadHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        btn_send = (Button) findViewById(R.id.send);
        btn_stop = (Button) findViewById(R.id.stop);
        btn_send.setOnClickListener(this);
        btn_stop.setOnClickListener(this);

        HandlerThread thread = new HandlerThread("handler thread");
        thread.start();
        threadHandler = new Handler(thread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                // 向主线程发送消息
                Message message = new Message();
                // 每隔一秒发送消息
                handler.sendMessageDelayed(message, 1000);
                Log.i("TAG", "thread handler");
            }
        };


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send: {
                // 向子线程发送消息
                handler.sendEmptyMessage(1);
                break;
            }
            case R.id.stop: {
                handler.removeMessages(1);
                break;
            }
        }
    }
}

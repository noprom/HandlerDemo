package com.noprom.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by noprom on 2015/1/11.
 */
public class SecondActivity extends Activity {


//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            Log.i("TAG","UI-------------------->"+Thread.currentThread());
//        }
//    };

    class MyThread extends Thread {

        public Handler handler;

        public Looper looper;

        @Override
        public void run() {
            // 创建Looper
            Looper.prepare();
            looper = Looper.myLooper();
            // 创建handler处理消息
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Log.i("TAG", "current thread " + Thread.currentThread());
                }
            };
            Looper.loop();
        }
    }

    private MyThread thread;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置布局
        TextView textView = new TextView(this);
        textView.setText("Hello hanlder~");
        setContentView(textView);

        // 创建线程并开启线程
        thread = new MyThread();
        thread.start();
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // 发送消息
//        thread.handler.sendEmptyMessage(1);
//        handler.sendEmptyMessage(1);

        handler = new Handler(thread.looper) {
            @Override
            public void handleMessage(Message msg) {
                Log.i("TAG", msg + "");
            }
        };
        handler.sendEmptyMessage(1);

    }
}

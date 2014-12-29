package com.noprom.handler;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private TextView textView;
    private Handler handler = new Handler();


    private ImageView imageView;
    // 图片资源
    private int images[] = {R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3, R.drawable.avatar4};
    // 图片索引
    private int index;
    // 开启子线程
    private MyRunnable myRunnable = new MyRunnable();
    class MyRunnable implements Runnable {

        @Override
        public void run() {
            index++;
            index = index % 4;
            imageView.setImageResource(images[index]);
            handler.postDelayed(myRunnable,1000);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化View
//        textView = (TextView) findViewById(R.id.id_textview);
        imageView = (ImageView) findViewById(R.id.id_imageView);

        handler.postDelayed(myRunnable,1000);

//        new Thread(){
//            public void run(){
//                try {
//                    Thread.sleep(1000);
//                    // 通过handler来解决异常
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            textView.setText("update thread");
//                        }
//                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
    }


}

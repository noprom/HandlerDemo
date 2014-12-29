package com.noprom.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    private TextView textView;
    private Button button;
//    private Handler handler = new Handler() {
//        // handler处理消息
//        @Override
//        public void handleMessage(Message msg) {
////            textView.setText("" + msg.arg1 + "-" + msg.arg2);、
//            textView.setText(msg.obj + "");
//        }
//    };

    // 指定callBack
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), "" + 1, Toast.LENGTH_LONG).show();
            return false;
        }
    }) {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), "" + 2, Toast.LENGTH_LONG).show();
        }
    };


    @Override
    public void onClick(View v) {
//        handler.removeCallbacks(myRunnable);
        handler.sendEmptyMessage(1);
    }


    class Person {
        public int age;
        public String name;

        @Override
        public String toString() {
            return "name = " + name + "age = " + age;
        }
    }


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
            handler.postDelayed(myRunnable, 1000);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化View
        textView = (TextView) findViewById(R.id.id_textview);
        imageView = (ImageView) findViewById(R.id.id_imageView);
        button = (Button) findViewById(R.id.id_button);
        button.setOnClickListener(this);


        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
//                    Message msg = new Message();
//                    msg.arg1 = 100;
//                    msg.arg2 = 200;


                    Message msg = handler.obtainMessage();
                    // 指定msg的obj
                    Person person = new Person();
                    person.age = 25;
                    person.name = "小明";
                    msg.obj = person;

                    // 发送方法
//                    handler.sendMessage(msg);
                    msg.sendToTarget();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        handler.postDelayed(myRunnable, 1000);


//
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

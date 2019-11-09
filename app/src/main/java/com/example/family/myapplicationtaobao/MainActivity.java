package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                //实现页面跳转
                startActivity(new Intent(getApplicationContext(),DengLuActivity.class));
                finish();//关闭当前向导activity
                return false;
            }
        }).sendEmptyMessageDelayed(0,1500);//表示延迟1.5秒发送任务




    }
}

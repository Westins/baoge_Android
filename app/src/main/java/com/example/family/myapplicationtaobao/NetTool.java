package com.example.family.myapplicationtaobao;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//链接网络的工具类
public class NetTool {
    public  static  void postHttp(final Handler handler, final String url , final FormBody body, final OnNetBack netBack){
        new Thread(){
            @Override
            public void run() {

                try {
                    Request request = new Request.Builder().url(url).post(body).build();
                    Response  response = new OkHttpClient().newCall(request).execute();
                    final String result = response.body().string();
                    Log.i("MyThread", "result:" + result);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            netBack.netFinish(result);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
    interface OnNetBack {
        void netFinish(String json);
    }
}

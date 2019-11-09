package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

// 订单中心
public class Fragment_3_A2_Activity extends AppCompatActivity implements View.OnClickListener {
    Context context;

    private ListView multlist;
    private ImageView img_ddtc;

    private DDAdapter ddAdapter;
    List<ShangpingDD> contactses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_3_2);
        context = this;
        initView();
    }

    private void initView() {
        multlist = (ListView) findViewById(R.id.list_dingdan);
        img_ddtc = (ImageView)findViewById(R.id.img_ddtc);

        img_ddtc.setOnClickListener(this);

        SharedPreferences sp = getSharedPreferences("yhdl",MODE_PRIVATE);
        String gid = sp.getString("userId","");//键，如果没有希望返回的值

        String httpzy=this.getString(R.string.httpzy);
        String url=httpzy+"androids/getOR.do";
        FormBody body = new FormBody.Builder()
                .add("gid",gid)
                .build();
        NetTool.postHttp(new Handler(), url, body, new NetTool.OnNetBack() {
            @Override
            public void netFinish(String json) {
                Log.i("MyThread2", "json:" + json);
                JsonArray array = new JsonParser().parse(json).getAsJsonArray();
                for (JsonElement element:array){
                    ShangpingDD con = new Gson().fromJson(element,ShangpingDD.class);
                    contactses.add(con);
                }
                Log.i("MyThread2", "x:" + contactses.size());
            }
        });


        ddAdapter = new DDAdapter(context,contactses);
        multlist.setAdapter(ddAdapter);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_ddtc:
                finish();
                break;
        }
    }
}

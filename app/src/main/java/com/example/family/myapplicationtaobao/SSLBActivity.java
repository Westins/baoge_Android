package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

//搜索界面列表
public class SSLBActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    private ImageView lb_tuichu;
    private TextView lb_ssdj;
    private View lb_qb;
    private TextView textView;
    private View lb_ssdp;
    private ListView list_ss;

    private SSLBAdapter sslbAdapter;
    List<ShangpingSSLB> contactses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sslb);
        context = this;
        initView();
    }

    private void initView() {
        lb_tuichu = (ImageView) findViewById(R.id.lb_tuichu);
        lb_ssdj = (TextView) findViewById(R.id.lb_ssdj);
        lb_qb = (View) findViewById(R.id.lb_qb);
        textView = (TextView) findViewById(R.id.textView);
        lb_ssdp = (View) findViewById(R.id.lb_ssdp);
        list_ss = (ListView) findViewById(R.id.list_ss);

        lb_ssdj.setOnClickListener(this);
        lb_tuichu.setOnClickListener(this);

        SharedPreferences sp = getSharedPreferences("sssr",MODE_PRIVATE);
        String name = sp.getString("name","");//键，如果没有希望返回的值
        String httpzy=this.getString(R.string.httpzy);
        String url=httpzy+"androids/GetGoodsByGoodsLikeGoodsName.do";
        FormBody body = new FormBody.Builder()
                .add("name",name)
                .build();
        Log.i("MyThread2", "name:" + name);
        NetTool.postHttp(new Handler(), url, body, new NetTool.OnNetBack() {
            @Override
            public void netFinish(String json) {
                Log.i("MyThread2", "json:" + json);
                JsonArray array = new JsonParser().parse(json).getAsJsonArray();
                for (JsonElement element:array){
                    ShangpingSSLB con = new Gson().fromJson(element,ShangpingSSLB.class);
                    contactses.add(con);
                }

            }
        });

        sslbAdapter = new SSLBAdapter(context,contactses);
        list_ss.setAdapter(sslbAdapter);
    }

    private void submit() {
        // validate
//        String ssdj = lb_ssdj.getText().toString().trim();
//        if (TextUtils.isEmpty(ssdj)) {
//            Toast.makeText(this, "搜点什么吧...", Toast.LENGTH_SHORT).show();
//            return;
//        }

        // TODO validate success, do something


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lb_ssdj:
                SharedPreferences sp = context.getSharedPreferences("sssr",MODE_PRIVATE);
                SharedPreferences.Editor edt = sp.edit();//用于保存
                edt.putString("pd","0");
                edt.commit();
                startActivity(new Intent(context,SSSRActivity.class));
                finish();
                break;
            case R.id.lb_tuichu:
                finish();
                break;
        }
    }
}

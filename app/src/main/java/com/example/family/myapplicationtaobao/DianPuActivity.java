package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

//店铺界面
public class DianPuActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    private TextView dp_dpName;
    private Button dp_guanzhu;
    private ImageView dp_gb;
    private TextView dp_gzrs;
    private LinearLayout dp_ss;
    private View lb_qb;
    private ImageView dp_gg1;
    private MultiGridView dp_splb;
    private ScrollView scrollView;

    private ShangAdapter shangAdapter;
    List<ShangpingLB> contactses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianpu);
        context = this;
        initView();
    }

    private void initView() {
        dp_dpName = (TextView) findViewById(R.id.dp_dpName);
        dp_guanzhu = (Button) findViewById(R.id.dp_guanzhu);
        dp_gb = (ImageView) findViewById(R.id.dp_gb);
        dp_gzrs = (TextView) findViewById(R.id.dp_gzrs);
        dp_ss = (LinearLayout) findViewById(R.id.dp_ss);
        lb_qb = (View) findViewById(R.id.lb_qb);
        dp_gg1 = (ImageView) findViewById(R.id.dp_gg1);
        dp_splb = (MultiGridView) findViewById(R.id.dp_splb);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        dp_guanzhu.setOnClickListener(this);
        dp_ss.setOnClickListener(this);
        dp_gb.setOnClickListener(this);

        dp_splb.setFocusable(false);

        SharedPreferences sp3 = getSharedPreferences("sid",MODE_PRIVATE);
        String name = sp3.getString("id","");//键，如果没有希望返回的值
        String httpzy=this.getString(R.string.httpzy);
        String url=httpzy+"androids/GetGoodsByShopId.do";
        FormBody body = new FormBody.Builder()
                .add("shopsId",name)
                .build();
        NetTool.postHttp(new Handler(), url, body, new NetTool.OnNetBack() {
            @Override
            public void netFinish(String json) {
                Log.i("MyThread2", "json:" + json);
                Gson gson = new Gson();
                Shops user = gson.fromJson(json,Shops.class);
                if (null==user){

                    finish();
                    return;
                }
                contactses = user.getGoods();

                //adapter.notifyDataSetChanged();//更新UI
                Log.i("MyThread2", "siz:" + contactses.size());
                xians(contactses);
                dp_dpName.setText(""+user.getShopsName());
            }
        });

//        shangAdapter = new ShangAdapter(context,contactses);
//        dp_splb.setAdapter(shangAdapter);
    }
    public void  xians(List<ShangpingLB> contactses2){
        shangAdapter = new ShangAdapter(context,contactses2);
        dp_splb.setAdapter(shangAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dp_guanzhu:

                break;
            case R.id.dp_ss:
                startActivity(new Intent(this,SSSRActivity.class));
                finish();
                break;
            case R.id.dp_gb:
                finish();
                break;
        }
    }
}

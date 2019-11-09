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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

//商品购买界面
public class Fragment_1_A1_Activity extends AppCompatActivity implements View.OnClickListener {
    Context context;


    private PLAdapter plAdapter;
    List<ShangpingPL> contactses = new ArrayList<>();
    private ImageView sp_dt;
    private ImageView sp_tc;
    private TextView text_spxxjg;
    private TextView sp_name;
    private TextView text_dpdizhi;
    private TextView text_yijingsm;
    private TextView dp_spdz;
    private LinearLayout sp_gg;
    private ImageView sp_sljia;
    private TextView sp_sl;
    private ImageView sp_sljian;
    private TextView text_pjsl;
    private MultiListView list_pl;
    private LinearLayout sp_jrdp;
    private ImageView imageView3;
    private LinearLayout sp_dpkf;
    private LinearLayout sp_shoucang;
    private Button but_jiarugwc;
    private Button but_lijigoumai;

    String pdgg="";
    String yan[];
    List<Button> listButton = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_1_1);
        context = this;
        initView();

    }

    private void initView() {
        sp_dt = (ImageView) findViewById(R.id.sp_dt);
        sp_tc = (ImageView) findViewById(R.id.sp_tc);
        text_spxxjg = (TextView) findViewById(R.id.text_spxxjg);
        sp_name = (TextView) findViewById(R.id.sp_name);
        text_dpdizhi = (TextView) findViewById(R.id.text_dpdizhi);
        text_yijingsm = (TextView) findViewById(R.id.text_yijingsm);
        dp_spdz = (TextView) findViewById(R.id.dp_spdz);
        sp_gg = (LinearLayout) findViewById(R.id.sp_gg);
        sp_sljia = (ImageView) findViewById(R.id.sp_sljia);
        sp_sl = (TextView) findViewById(R.id.sp_sl);
        sp_sljian = (ImageView) findViewById(R.id.sp_sljian);
        text_pjsl = (TextView) findViewById(R.id.text_pjsl);
        list_pl = (MultiListView) findViewById(R.id.list_pl);
        sp_jrdp = (LinearLayout) findViewById(R.id.sp_jrdp);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        sp_dpkf = (LinearLayout) findViewById(R.id.sp_dpkf);
        sp_shoucang = (LinearLayout) findViewById(R.id.sp_shoucang);
        but_jiarugwc = (Button) findViewById(R.id.but_jiarugwc);
        but_lijigoumai = (Button) findViewById(R.id.but_lijigoumai);

        but_jiarugwc.setOnClickListener(this);
        but_lijigoumai.setOnClickListener(this);
        sp_sljia.setOnClickListener(this);
        sp_sljian.setOnClickListener(this);
        sp_tc.setOnClickListener(this);
        sp_jrdp.setOnClickListener(this);

        SharedPreferences sp = getSharedPreferences("sid",MODE_PRIVATE);
        String info = sp.getString("id","");//键，如果没有希望返回的值

        Log.i("MyThreadinfo", "json:" + info);
        final String httpzy=this.getString(R.string.httpzy);
        String url=httpzy+"androids/GetgoodsByGoodsId.do";
        FormBody body = new FormBody.Builder()
                .add("gid",info)
                .build();
        NetTool.postHttp(new Handler(), url, body, new NetTool.OnNetBack() {
            @Override
            public void netFinish(String json) {
                Log.i("M2", "json:" + json);
                List<ShangpingLB> contactses = new ArrayList<ShangpingLB>();
                JsonArray array = new JsonParser().parse(json).getAsJsonArray();
                for (JsonElement element:array){
                    ShangpingLB con = new Gson().fromJson(element,ShangpingLB.class);
                    contactses.add(con);
                }
                //sp_dt
                Glide.with(context)
                        .load(httpzy+contactses.get(0).getGoodsImg())
                        .apply(new RequestOptions().error(R.mipmap.logding_error).placeholder(R.mipmap.loading))
                        .into(sp_dt);
                text_spxxjg.setText(""+contactses.get(0).getGoodsOriginalPrice());
                sp_name.setText(""+contactses.get(0).getGoodsName());
                text_yijingsm.setText(""+contactses.get(0).getGoodsSalesVolume());

                yan = contactses.get(0).getGoodsModel().split(",");
                butb(yan);

            }
        });

        String url2=httpzy+"androids/GetReviewByGoodsId.do";
        FormBody body2 = new FormBody.Builder()
                .add("gid",info)
                .build();
        NetTool.postHttp(new Handler(), url2, body2, new NetTool.OnNetBack() {
            @Override
            public void netFinish(String json) {
                JsonArray array2 = new JsonParser().parse(json).getAsJsonArray();
                for (JsonElement element2:array2){
                    ShangpingPL con2 = new Gson().fromJson(element2,ShangpingPL.class);
                    contactses.add(con2);
                }
                text_pjsl.setText(""+contactses.size());

            }
        });




        plAdapter = new PLAdapter(context,contactses);
        list_pl.setAdapter(plAdapter);




    }
    public void butb(String yan[]){
        String yan1[]=yan;
        for (int i = 0; i < yan1.length; i++) {
            Button button = new Button(context);
            button.setWidth(1);
            button.setHeight(5);
            button.setTag(i);
            button.setText(yan1[i]);
            button.setTextSize(13);
            button.setOnClickListener(this);
            listButton.add(button);
            sp_gg.addView(button);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_jiarugwc:
                SharedPreferences sp = getSharedPreferences("yhdl",MODE_PRIVATE);
                String userId = sp.getString("userId","");//键，如果没有希望返回的值
                Log.i("MyThread2", "www:" + userId);
                SharedPreferences sp2 = getSharedPreferences("sid",MODE_PRIVATE);
                String goodsId = sp2.getString("id","");//键，如果没有希望返回的值
                Log.i("MyThread2", "xxx:" + goodsId);
                String shoppingCartNum = sp_sl.getText().toString();
                String shoppingCartState ="0";
                String shoppingCartColor = "黑";
                if ("".equals(pdgg)){
                    Toast.makeText(context, "请选择商品规格！", Toast.LENGTH_SHORT).show();
                    return;
                }
                String shoppingCartModel = pdgg;


                final String httpzy=this.getString(R.string.httpzy);
                String url2=httpzy+"androids/addShopcart.do";
                FormBody body2 = new FormBody.Builder()
                        .add("userId",userId)
                        .add("goodsId",goodsId)
                        .add("shoppingCartNum",shoppingCartNum)
                        .add("shoppingCartState",shoppingCartState)
                        .add("shoppingCartColor",shoppingCartColor)
                        .add("shoppingCartModel",shoppingCartModel)
                        .build();
                NetTool.postHttp(new Handler(), url2, body2, new NetTool.OnNetBack() {
                    @Override
                    public void netFinish(String json) {
                        if ("1".equals(json)){
                            Toast.makeText(context, "添加购物车成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "添加购物车失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                break;
            case R.id.but_lijigoumai:

                break;
            case R.id.sp_sljia:
                String shul = sp_sl.getText().toString();
                if (null!=shul){
                    sp_sl.setText(""+(Integer.parseInt(shul)+1));
                }
                //Toast.makeText(context, ""+shul, Toast.LENGTH_SHORT).show();
                break;
            case R.id.sp_sljian:
                String shul2 = sp_sl.getText().toString();
                if (null!=shul2&&Integer.parseInt(shul2)>1){
                    sp_sl.setText(""+(Integer.parseInt(shul2)-1));
                }
                //Toast.makeText(context, ""+shul, Toast.LENGTH_SHORT).show();

                break;
            case R.id.sp_tc:
                finish();
                break;
            case R.id.sp_jrdp:
//                SharedPreferences sp3 = getSharedPreferences("sid",MODE_PRIVATE);
//                String name = sp3.getString("id","");//键，如果没有希望返回的值
                startActivity(new Intent(this,DianPuActivity.class));
                finish();
                break;
        }
        if (null!=v.getTag()){
            for (int i=0;i<listButton.size();i++){
                if ((int)v.getTag()==i){
                    for (int m=0;m<listButton.size();m++){
                        int b =  getResources().getColor(R.color.listBottom2);
                        listButton.get(m).setTextColor(b);
                    }
                    int b2 =  getResources().getColor(R.color.listBottom);
                    listButton.get(i).setTextColor(b2);
                    pdgg=listButton.get(i).getText().toString();
                }
            }
        }
    }


}

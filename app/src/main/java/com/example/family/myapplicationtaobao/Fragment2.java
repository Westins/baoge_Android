package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

import static android.content.Context.MODE_PRIVATE;

public class Fragment2 extends Fragment implements View.OnClickListener {



    private TextView gwc_sc;
    private CheckBox che_quan;
    private TextView gwc_hj;
    private Button but_jies;

    Context context;

    private ListView list_gou;
    private GouAdapter adapter;
    List<ShoppingCart> contactses = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context=this.getActivity();
        View view = inflater.inflate(R.layout.fragment_2, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        //test = (ImageView) view.findViewById(R.id.test);
        list_gou = (ListView) view.findViewById(R.id.list_gou);
        gwc_sc = (TextView)view.findViewById(R.id.gwc_sc);
        che_quan = (CheckBox)view.findViewById(R.id.che_quan);
        gwc_hj = (TextView)view.findViewById(R.id.gwc_hj);
        but_jies = (Button)view.findViewById(R.id.but_jies);

        gwc_sc.setOnClickListener(this);
        che_quan.setOnClickListener(this);
        but_jies.setOnClickListener(this);

        String httpzy=this.getString(R.string.httpzy);
        String url=httpzy+"androids/getGouWuChe.do";
        FormBody body = new FormBody.Builder()
                .build();
        NetTool.postHttp(new Handler(), url, body, new NetTool.OnNetBack() {
            @Override
            public void netFinish(String json) {
                Log.i("MyThread2", "json:" + json);
                JsonArray array = new JsonParser().parse(json).getAsJsonArray();
                for (JsonElement element:array){
                    ShoppingCart con = new Gson().fromJson(element,ShoppingCart.class);
                    contactses.add(con);
                }
                sp();
                Log.i("MyThread2", "x:" + contactses.size());
            }
        });

        //adapter = new GouAdapter(context,contactses);

    }
    public void sp(){
        adapter = new GouAdapter(context,contactses,this);
        list_gou.setAdapter(adapter);
    }
    //onResume                                               onHiddenChanged

    //出现的事件
    @Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden){
            //Toast.makeText(context, "请输入手机号码", Toast.LENGTH_SHORT).show();
            String httpzy=this.getString(R.string.httpzy);
            String url=httpzy+"androids/getGouWuChe.do";
            FormBody body = new FormBody.Builder()
                    .build();
            NetTool.postHttp(new Handler(), url, body, new NetTool.OnNetBack() {
                @Override
                public void netFinish(String json) {
                    Log.i("MyThread2", "json:" + json);
                    JsonArray array = new JsonParser().parse(json).getAsJsonArray();
                    contactses.clear();
                    for (JsonElement element:array){
                        ShoppingCart con = new Gson().fromJson(element,ShoppingCart.class);
                        contactses.add(con);
                    }
                    adapter.notifyDataSetChanged();//更新UI

                }
            });
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gwc_sc:
                List<String> li = new ArrayList<>();
                for (int i=0;i<contactses.size();i++){
                    String pd= contactses.get(i).getChe_dan();
                    if("1".equals(pd)){
                        String id = contactses.get(i).getShoppingCartld()+"";
                        li.add(id);
                        Log.i("MyThreaw", "xxxxxsss:" + id);
                        //contactses.remove(i--);
                    }
                }
                for (int i=0;i<contactses.size();i++){
                    String pd= contactses.get(i).getChe_dan();
                    if("1".equals(pd)){
                        //li.add(contactses.get(i).getShoppingCartId()+"");
                        contactses.remove(i--);
                    }
                }
                if (li.size()>0){
                    for (int i=0;i<li.size();i++){
                        String httpzy=this.getString(R.string.httpzy);
                        String url=httpzy+"androids/delShopsCartByCartId.do";
                        FormBody body = new FormBody.Builder()
                                .add("CareId",li.get(i))
                                .build();
                        NetTool.postHttp(new Handler(), url, body, new NetTool.OnNetBack() {
                            @Override
                            public void netFinish(String json) {
                                Toast.makeText(context, "删除成功"+json, Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }

                adapter.notifyDataSetChanged();//更新UI
                ToUpdate(0);//更新UI
                break;
            case R.id.che_quan:
                if(che_quan.isChecked()){
                    for (int i=0;i<contactses.size();i++){
                        contactses.get(i).setChe_dan("1");
                    }
                    adapter.notifyDataSetChanged();//更新UI
                }else{
                    for (int i=0;i<contactses.size();i++){
                        contactses.get(i).setChe_dan("0");
                    }
                    adapter.notifyDataSetChanged();//更新UI
                }
                ToUpdate(0);
                break;
            //but_jies
            case R.id.but_jies:

                startActivity(new Intent(context,ZhiFuActivity.class));
                jiezhang();
                break;

        }
    }
    public void ToUpdate(Object o){
        if((int)o==0){
            double jg = 0.0;
            int sl =0;
            for (int i=0;i<contactses.size();i++){
                String pd= contactses.get(i).getChe_dan();
                if("1".equals(pd)){
                    jg=jg+(contactses.get(i).getGoods().getGoodsOriginalPrice()*contactses.get(i).getShoppingCartNum());
                    Log.i("MyThread", "result:" + contactses.get(i).getGoods().getGoodsOriginalPrice());
                    sl++;
                }
            }
            but_jies.setText("结算("+sl+")");
            gwc_hj.setText(""+new BigDecimal(jg).setScale(2, BigDecimal.ROUND_HALF_UP));
        }else  if((int)o==1){
            adapter.notifyDataSetChanged();//更新UI
            ToUpdate(0);
        }
    }
    public void jiezhang(){
        for (int i=0;i<contactses.size();i++){
            String pd= contactses.get(i).getChe_dan();
            if("1".equals(pd)){
                SharedPreferences sp = context.getSharedPreferences("yhdl",MODE_PRIVATE);
                String userId = sp.getString("userId","");//键，如果没有希望返回的值
                Log.i("MyThread2", "www:" + userId);
                String httpzy=this.getString(R.string.httpzy);
                String url=httpzy+"androids/addOrder.do";
                double CartPrice = contactses.get(i).getShoppingCartNum()*contactses.get(i).getGoods().getGoodsOriginalPrice();
                int carID = contactses.get(i).getShoppingCartld();
                String uuid = (int) (Math.random() * 10000)+"";
                String time = "2019-06-20";
                FormBody body = new FormBody.Builder()
                        .add("carID",carID+"")
                        .add("CartPrice",CartPrice+"")
                        .add("uuid",uuid)
                        .add("time",time)
                        .add("userId",userId)
                        .build();
                NetTool.postHttp(new Handler(), url, body, new NetTool.OnNetBack() {
                    @Override
                    public void netFinish(String json) {
                        if ("1".equals(json)){
                            //Toast.makeText(context, "结账成功", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        }
        for (int i=0;i<contactses.size();i++){
            String pd= contactses.get(i).getChe_dan();
            if("1".equals(pd)){
                //li.add(contactses.get(i).getShoppingCartId()+"");
                contactses.remove(i--);
            }
        }
        adapter.notifyDataSetChanged();//更新UI
    }
}

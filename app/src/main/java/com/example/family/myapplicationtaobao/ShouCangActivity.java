package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//收藏界面
public class ShouCangActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    private ImageView sc_tc;
    private TextView sc_sc;
    private ListView sc_list;

    List<ShangpingSSLB> contactses = new ArrayList<>();
    SSLBWAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoucang);
        context = this;
        initView();
    }

    private void initView() {
        sc_tc = (ImageView) findViewById(R.id.sc_tc);
        sc_sc = (TextView) findViewById(R.id.sc_sc);
        sc_list = (ListView) findViewById(R.id.sc_list);

        sc_sc.setOnClickListener(this);



        adapter = new SSLBWAdapter(context,contactses,this);
        sc_list.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sc_sc:
                for (int i=0;i<contactses.size();i++){
                    String pd= contactses.get(i).getLb_dx();
                    if("1".equals(pd)){
                        contactses.remove(i--);
                        Log.i("MyThread2", "result:" + i);
                    }
                }
                adapter.notifyDataSetChanged();//更新UI
                break;
        }

    }
}

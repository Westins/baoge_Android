package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//发布评论
public class GuanZhuActivity extends AppCompatActivity {
    Context context;
    private ImageView gz_tc;
    private TextView gz_sc;
    private ListView gz_list;

    private GZAdapter gzAdapter;
    List<GuanZhupojo> contactses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanzhu);
        context = this;
        initView();
    }

    private void initView() {
        gz_tc = (ImageView) findViewById(R.id.gz_tc);
        gz_sc = (TextView) findViewById(R.id.gz_sc);
        gz_list = (ListView) findViewById(R.id.gz_list);

        GuanZhupojo g = new GuanZhupojo();
        g.setGz_name("菜鸟驿站");
        g.setGz_tp(R.mipmap.test_1);

        contactses.add(g);
        contactses.add(g);
        contactses.add(g);
        contactses.add(g);
        contactses.add(g);
        contactses.add(g);
        contactses.add(g);
        contactses.add(g);
        contactses.add(g);
        contactses.add(g);

        gzAdapter = new GZAdapter(context,contactses);
        gz_list.setAdapter(gzAdapter);
    }
}

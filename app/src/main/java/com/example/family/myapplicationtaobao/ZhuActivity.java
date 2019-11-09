package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//主页管理器
public class ZhuActivity extends AppCompatActivity  implements View.OnClickListener{
    Context context;
    private FrameLayout fl_frame;
    private LinearLayout qh_1;
    private LinearLayout qh_2;
    private LinearLayout qh_3;
    private TextView shouye;
    private TextView gouwuche;
    private TextView wode;
    private ImageView shouyetp;
    private ImageView gouwuchetp;
    private ImageView wodetp;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu);
        initView();
        context = this;

//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.fl_frame,new Fragment1());
//        ft.commit();
        showFragment(1);


    }

    private void initView() {

        fl_frame = (FrameLayout) findViewById(R.id.fl_frame);
        qh_1 = (LinearLayout) findViewById(R.id.qh_1);
        qh_2 = (LinearLayout) findViewById(R.id.qh_2);
        qh_3 = (LinearLayout) findViewById(R.id.qh_3);
        shouye = (TextView)findViewById(R.id.shouye);
        gouwuche = (TextView)findViewById(R.id.gouwuche);
        wode = (TextView)findViewById(R.id.wode);
        shouyetp = (ImageView)findViewById(R.id.shouyetp);
        gouwuchetp = (ImageView)findViewById(R.id.gouwuchetp);
        wodetp = (ImageView)findViewById(R.id.wodetp);
        //tp_lb.setImageResource(R.mipmap.pager2);
        //new ZhuActivity.MyThread().start();
//        fragment1 = new Fragment1();
//        fragment2 = new Fragment2();
//        fragment3 = new Fragment3();


        qh_1.setOnClickListener(this);
        qh_2.setOnClickListener(this);
        qh_3.setOnClickListener(this);

    }


    private void submit() {
        // validate
//        String txtshou = edit_txtshou.getText().toString().trim();
//        if (TextUtils.isEmpty(txtshou)) {
//            Toast.makeText(this, "txtshou不能为空", Toast.LENGTH_SHORT).show();
//            return;
//        }

        // TODO validate success, do something


    }
    @Override
    public void onClick(View v) {
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.qh_1:
                //ft.replace(R.id.fl_frame,fragment1);
                showFragment(1);
                shouye.setTextColor(Color.parseColor("#ff5000"));
                shouyetp.setImageResource(R.mipmap.act_home_home_press);

                gouwuche.setTextColor(Color.parseColor("#6d717a"));
                gouwuchetp.setImageResource(R.mipmap.act_home_shopcar);

                wode.setTextColor(Color.parseColor("#6d717a"));
                wodetp.setImageResource(R.mipmap.act_home_my);
                break;
            case R.id.qh_2:
                //ft.replace(R.id.fl_frame,fragment2);
                showFragment(2);
                gouwuche.setTextColor(Color.parseColor("#ff5000"));
                gouwuchetp.setImageResource(R.mipmap.act_home_shopcar_press);

                shouye.setTextColor(Color.parseColor("#6d717a"));
                shouyetp.setImageResource(R.mipmap.act_home_home);

                wode.setTextColor(Color.parseColor("#6d717a"));
                wodetp.setImageResource(R.mipmap.act_home_my);
                break;
            case R.id.qh_3:
                //ft.replace(R.id.fl_frame,fragment3);
                showFragment(3);
                wode.setTextColor(Color.parseColor("#ff5000"));
                wodetp.setImageResource(R.mipmap.act_home_my_press);

                shouye.setTextColor(Color.parseColor("#6d717a"));
                shouyetp.setImageResource(R.mipmap.act_home_home);

                gouwuche.setTextColor(Color.parseColor("#6d717a"));
                gouwuchetp.setImageResource(R.mipmap.act_home_shopcar);
                break;
        }
//        ft.commit();
    }
    private void hideFragment(FragmentTransaction transaction){
        if (fragment1!=null){
            transaction.hide(fragment1);
        }
        if (fragment2!=null){
            transaction.hide(fragment2);
        }
        if (fragment3!=null){
            transaction.hide(fragment3);
        }
    }
    public void showFragment(int index) {
        // Fragment事务管理器
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // 隐藏已加载过的Fragment
        hideFragment(ft);
        switch (index) {
            // 判断Fragment是否实例化，实例化过直接显示出来，否者实例化
            case 1:
                if (fragment1 != null) {
                    ft.show(fragment1);
                } else {
                    fragment1 = new Fragment1();
                    ft.add(R.id.fl_frame, fragment1);
                }
                break;
            case 2:
                if (fragment2 != null) {
                    ft.show(fragment2);
                } else {
                    fragment2 = new Fragment2();
                    ft.add(R.id.fl_frame, fragment2);
                }
                break;
            case 3:
                if (fragment3 != null) {
                    ft.show(fragment3);
                } else {
                    fragment3 = new Fragment3();
                    ft.add(R.id.fl_frame, fragment3);
                }
                break;
        }
        ft.commit();
    }



}

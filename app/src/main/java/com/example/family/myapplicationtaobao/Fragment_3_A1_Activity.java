package com.example.family.myapplicationtaobao;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//我的卡雅  设置按钮  设置页面
public class Fragment_3_A1_Activity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    private ImageView img_fanghuisz;
    private CircleImageView yonghu_touxiang;
    private TextView text_name;
    private TextView text_zh;
    private LinearLayout yonghui_szshzz;
    private LinearLayout yonghu_zhaq;
    private LinearLayout gy_kfz;
    private TextView yonghu_tc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_3_1);
        initView();
        context = this;
    }

    private void initView() {
        img_fanghuisz = (ImageView) findViewById(R.id.img_fanghuisz);
        yonghu_touxiang = (CircleImageView) findViewById(R.id.yonghu_touxiang);
        text_name = (TextView) findViewById(R.id.text_name);
        text_zh = (TextView) findViewById(R.id.text_zh);
        yonghui_szshzz = (LinearLayout) findViewById(R.id.yonghui_szshzz);
        yonghu_zhaq = (LinearLayout) findViewById(R.id.yonghu_zhaq);
        gy_kfz = (LinearLayout) findViewById(R.id.gy_kfz);
        yonghu_tc = (TextView) findViewById(R.id.yonghu_tc);

        yonghui_szshzz.setOnClickListener(this);
        img_fanghuisz.setOnClickListener(this);
        yonghu_tc.setOnClickListener(this);
    }
//img_fanghuisz
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yonghui_szshzz:
                startActivity(new Intent(this,Fragment_3_A1_1_Activity.class));

                break;
            case R.id.img_fanghuisz:
                finish();
                break;
            case R.id.yonghu_tc:
                //Toast.makeText(context, "哈哈", Toast.LENGTH_SHORT).show();
                SharedPreferences sp = getSharedPreferences("yhdl",MODE_PRIVATE);
                SharedPreferences.Editor edt = sp.edit();//用于保存
                edt.clear();
                edt.commit();
                final Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}

package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
// 设置收货地址界面
public class Fragment_3_A1_1_Activity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    private ImageView img_dztu;
    private TextView text_dzbc;
    private EditText edit_sjr;
    private EditText edit_sjhm;
    private EditText edit_dz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_3_1_1);
        initView();
        context = this;
    }

    private void initView() {
        img_dztu = (ImageView) findViewById(R.id.img_dztu);
        text_dzbc = (TextView) findViewById(R.id.text_dzbc);
        edit_sjr = (EditText) findViewById(R.id.edit_sjr);
        edit_sjhm = (EditText) findViewById(R.id.edit_sjhm);
        edit_dz = (EditText) findViewById(R.id.edit_dz);

        img_dztu.setOnClickListener(this);
        text_dzbc.setOnClickListener(this);
        //获取保存的内容
        SharedPreferences sp = getSharedPreferences("yhdz",MODE_PRIVATE);
        //取
        String edit_sjr2 = sp.getString("edit_sjr","");//键，如果没有希望返回的值
        String edit_sjhm2 = sp.getString("edit_sjhm","");
        String edit_dz2 = sp.getString("edit_dz","");
        if (!TextUtils.isEmpty(edit_sjr2)) {
            edit_sjr.setText(edit_sjr2);
        }
        if (!TextUtils.isEmpty(edit_sjhm2)) {
            edit_sjhm.setText(edit_sjhm2);
        }
        if (!TextUtils.isEmpty(edit_dz2)) {
            edit_dz.setText(edit_dz2);
        }

    }

    private void submit() {
        // validate
        String sjr = edit_sjr.getText().toString().trim();
        if (TextUtils.isEmpty(sjr)) {
            Toast.makeText(this, "请输入收件人", Toast.LENGTH_SHORT).show();
            return;
        }

        String sjhm = edit_sjhm.getText().toString().trim();
        if (TextUtils.isEmpty(sjhm)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }

        String dz = edit_dz.getText().toString().trim();
        if (TextUtils.isEmpty(dz)) {
            Toast.makeText(this, "请输入您的详细地址", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        SharedPreferences sp = getSharedPreferences("yhdz",MODE_PRIVATE);
        SharedPreferences.Editor edt = sp.edit();//用于保存
        edt.putString("edit_sjr",sjr);
        edt.putString("edit_sjhm",sjhm);
        edt.putString("edit_dz",dz);
        edt.commit();


    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_dztu:
//                startActivity(new Intent(this,ZhuActivity.class));
                finish();
                break;
            case R.id.text_dzbc:
                submit();
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}

package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.FormBody;

//注册页面
public class ZhuCeActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private EditText edit_name;
    private EditText edit_pwd;
    private EditText edit_pwdf;
    private TextView text_dl;
    private Button btn_zcw;
    private EditText edit_shoujih;
    private TextView text_huoqv;
    private String yzm="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuche);
        context=this;
        initView();

    }


    private void initView() {
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_pwd = (EditText) findViewById(R.id.edit_pwd);
        edit_pwdf = (EditText) findViewById(R.id.edit_pwdf);
        text_dl = (TextView) findViewById(R.id.text_dl);
        btn_zcw = (Button) findViewById(R.id.btn_zcw);
        edit_shoujih = (EditText)findViewById(R.id.edit_shoujih);
        text_huoqv = (TextView) findViewById(R.id.text_huoqv);

        text_dl.setOnClickListener(this);
        btn_zcw.setOnClickListener(this);
        text_huoqv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_zcw:
                ZhuCheff(edit_name.getText().toString().trim(),edit_pwd.getText().toString().trim());
                break;
            case R.id.text_dl:
                startActivity(new Intent(this,DengLuActivity.class));
                finish();
                break;
            case R.id.text_huoqv:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String name = edit_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = edit_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

         String pwdf = edit_pwdf.getText().toString().trim();
        if (TextUtils.isEmpty(pwdf)) {
            Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
            return;
        }
        //String shoujih = edit_shoujih.getText().toString().trim();
        if (!pwd.equals(pwdf)) {
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            Log.i("MyThread2", "pwd:" + pwd);
            Log.i("MyThread2", "pwdf:" + pwdf);
            return;
        }
        Toast.makeText(this, "请稍后。", Toast.LENGTH_SHORT).show();
        String httpzy=this.getString(R.string.httpzy);
        String url=httpzy+"androids/YZM2.do";
        FormBody body = new FormBody.Builder()
                .add("phone",name)
                .build();
        NetTool.postHttp(new Handler(), url, body, new NetTool.OnNetBack() {
            @Override
            public void netFinish(String json) {

                //Toast.makeText(context,""+json,Toast.LENGTH_LONG).show();
                if(json.equals("0")){
                    Toast.makeText(context,"该用户已存在"+json,Toast.LENGTH_LONG).show();
                }else{
                    yzm=json;
                    //Toast.makeText(context,""+json,Toast.LENGTH_LONG).show();
                }

            }
        });

        // TODO validate success, do something
    }
    public void ZhuCheff(String phone,String pwd){
        if (!yzm.equals(edit_shoujih.getText().toString().trim())){
            Toast.makeText(context,"验证码不正确！",Toast.LENGTH_LONG).show();
            return;
        }
        String httpzy=this.getString(R.string.httpzy);
        String url=httpzy+"androids/Source2.do";
        FormBody body = new FormBody.Builder()
                .add("phone",phone)
                .add("pwd",pwd)
                .build();
        //Toast.makeText(context,""+phone,Toast.LENGTH_LONG).show();

        NetTool.postHttp(new Handler(), url, body, new NetTool.OnNetBack() {
            @Override
            public void netFinish(String json) {

                //Toast.makeText(context,""+json,Toast.LENGTH_LONG).show();
                if(1>Integer.parseInt(json)){
                    Toast.makeText(context,"服务器繁忙请稍后再试！"+json,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context,"注册成功",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context,DengLuActivity.class));
                    finish();
                }

            }
        });
    }
}

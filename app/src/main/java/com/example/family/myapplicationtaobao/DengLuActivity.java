package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import okhttp3.FormBody;

//登录界面
public class DengLuActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    private EditText edit_name;
    private EditText edit_pwd;
    private TextView text_zc;
    private TextView text_zh;
    private Button btn_lgoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);
        context =this;
        initView();
    }

    private void initView() {
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_pwd = (EditText) findViewById(R.id.edit_pwd);
        text_zc = (TextView) findViewById(R.id.text_zc);
        text_zh = (TextView) findViewById(R.id.text_zh);
        btn_lgoin = (Button) findViewById(R.id.btn_lgoin);

        btn_lgoin.setOnClickListener(this);
        text_zc.setOnClickListener(this);

        //获取保存的内容
        SharedPreferences sp = getSharedPreferences("yhdl",MODE_PRIVATE);
        String name = sp.getString("userName","");//键，如果没有希望返回的值
        if (!TextUtils.isEmpty(name)) {
            Toast.makeText(this, "自动登录", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,ZhuActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_lgoin:
//                startActivity(new Intent(this,ZhuActivity.class));
//                finish();
                String httpzy=this.getString(R.string.httpzy);
                String url=httpzy+"androids/dengLuff.do";
                FormBody body = new FormBody.Builder()
                        .add("userAccount",edit_name.getText().toString().trim())
                        .add("userPwd",edit_pwd.getText().toString().trim())
                        .build();
                NetTool.postHttp(new Handler(), url, body, new NetTool.OnNetBack() {
                    @Override
                    public void netFinish(String json) {
                        Log.i("MyThread2", "json:" + json);
                        Gson gson = new Gson();
                        try {
                            UserInfo user = gson.fromJson(json,UserInfo.class);
                            if (null==user){
                                Toast.makeText(context, "该用户不存在！", Toast.LENGTH_SHORT).show();
                            }else{
                                if (edit_pwd.getText().toString().trim().equals(user.getUserPwd())){
                                    SharedPreferences sp = getSharedPreferences("yhdl",MODE_PRIVATE);
                                    SharedPreferences.Editor edt = sp.edit();//用于保存
                                    edt.putString("userName",user.getUserName());
                                    edt.putString("userId",user.getUserId()+"");
                                    edt.putString("userImg",user.getUserImg());
                                    edt.putString("userAccount",user.getUserAccount());
                                    edt.putString("zhifumm","123456");
                                    edt.commit();
                                    Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(context,ZhuActivity.class));
                                    finish();
                                }else{
                                    Toast.makeText(context, "密码错误，请注意大小写！", Toast.LENGTH_SHORT).show();
                                }
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "该用户不存在！", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                break;
            case R.id.text_zc:
                //Toast.makeText(this, "zc不能为空", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,ZhuCeActivity.class));
                finish();
                break;
        }
    }

    private void submit() {
        // validate
        String name = edit_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = edit_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String namew = "";
        String pwew = "";
        if(name.equals(namew) && pwd.equals(pwew)){
            SharedPreferences sp = getSharedPreferences("mysp",MODE_PRIVATE);
            SharedPreferences.Editor edt = sp.edit();//用于保存
            edt.putString("name",name);
            edt.putString("pwd",pwd);
            edt.commit();
        }



        // TODO validate success, do something


    }
}

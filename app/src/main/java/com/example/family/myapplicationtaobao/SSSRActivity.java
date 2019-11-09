package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


//搜索输入框
public class SSSRActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    private EditText ss_editan;
    private TextView ss_qx;
    private View ss_qb;
    private View ss_dp;
    private LinearLayout tsfx_1;
    private LinearLayout tsfx_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sssr);
        context = this;
        initView();

    }

    private void initView() {
        ss_editan = (EditText) findViewById(R.id.ss_editan);
        ss_qx = (TextView) findViewById(R.id.ss_qx);
        ss_qb = (View) findViewById(R.id.ss_qb);
        ss_dp = (View) findViewById(R.id.ss_dp);
        tsfx_1 = (LinearLayout) findViewById(R.id.tsfx_1);
        tsfx_2 = (LinearLayout) findViewById(R.id.tsfx_2);

        ss_qx.setOnClickListener(this);

        SharedPreferences sp = getSharedPreferences("sssr",MODE_PRIVATE);
        String pd = sp.getString("pd","0");//键，如果没有希望返回的值
        if ("0".equals(pd)){
            ss_dp.setBackgroundColor(Color.parseColor("#ff6b01"));
        }
        ss_editan.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                // 或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                // 注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND	|| actionId == EditorInfo.IME_ACTION_DONE	||
                        (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {					//处理事件
                    String name = ss_editan.getText().toString();
                    SharedPreferences sp = getSharedPreferences("sssr",MODE_PRIVATE);
                    SharedPreferences.Editor edt = sp.edit();//用于保存
                    edt.putString("name",name);
                    edt.commit();
                    startActivity(new Intent(context,SSLBActivity.class));
                    finish();
                }
                return false;
            }
        });


//        SharedPreferences sp = getSharedPreferences("yhdl",MODE_PRIVATE);
//        SharedPreferences.Editor edt = sp.edit();//用于保存
//        edt.putString("userName",user.getUserName());
//        edt.putString("userId",user.getUserId()+"");
//        edt.putString("userImg",user.getUserImg());
//        edt.putString("userAccount",user.getUserAccount());
//        edt.putString("zhifumm","123456");
//        edt.commit();

    }

    private void submit() {
        // validate
        String editan = ss_editan.getText().toString().trim();
        if (TextUtils.isEmpty(editan)) {
            Toast.makeText(this, "搜点什么吧...", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ss_qx:
                finish();
                break;

        }
    }
}

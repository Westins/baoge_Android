package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import okhttp3.FormBody;

//发布评论
public class Fragment_3_A3_Activity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    private ImageView fbpl_tc;
    private TextView pl_fabu;
    private ImageView pl_fbtp;
    private EditText pl_yhneirong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_3_3);
        context = this;
        initView();
    }


    private void initView() {
        fbpl_tc = (ImageView) findViewById(R.id.fbpl_tc);
        pl_fabu = (TextView) findViewById(R.id.pl_fabu);
        pl_fbtp = (ImageView) findViewById(R.id.pl_fbtp);
        pl_yhneirong = (EditText) findViewById(R.id.pl_yhneirong);

        pl_fabu.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String yhneirong = pl_yhneirong.getText().toString().trim();
        if (TextUtils.isEmpty(yhneirong)) {
            Toast.makeText(this, "宝贝满足你的期待吗？说说它的优点和美中不足的地方吧", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pl_fabu:
                SharedPreferences sp = getSharedPreferences("pl",MODE_PRIVATE);
                String goodsId = sp.getString("goodsId","");//键，如果没有希望返回的值
                SharedPreferences sp2 = getSharedPreferences("yhdl",MODE_PRIVATE);
                String userId = sp2.getString("userId","");//键，如果没有希望返回的值
                String httpzy=this.getString(R.string.httpzy);
                String url=httpzy+"androids/addReview.do";
                FormBody body = new FormBody.Builder()
                        .add("goodsReviewTest",pl_yhneirong.getText().toString().trim())
                        .add("goodsId",goodsId)
                        .add("userId",userId)
                        .build();
                NetTool.postHttp(new Handler(), url, body, new NetTool.OnNetBack() {
                    @Override
                    public void netFinish(String json) {
                    if ("1".equals(json)){
                        Toast.makeText(context, "评论成功"+json, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "评论失败"+json, Toast.LENGTH_SHORT).show();
                    }
                    finish();

                    }
                });
                break;
        }
    }
}

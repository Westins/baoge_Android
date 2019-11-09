package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import okhttp3.FormBody;


public class Fragment3 extends Fragment implements View.OnClickListener{
    Context context;

    private ImageView img_szzh;
    private LinearLayout yonghu_dd;

    private ImageView img_touxiang;
    private TextView wd_name;
    private LinearLayout wd_shoucang;
    private LinearLayout wd_guanzhu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, null);
        context=this.getActivity();
        initView(view);
        return view;
    }

    private void initView(View view) {
        img_szzh = (ImageView) view.findViewById(R.id.img_szzh);
        yonghu_dd = (LinearLayout) view.findViewById(R.id.yonghu_dd);
        img_touxiang = (ImageView) view.findViewById(R.id.img_touxiang);

        Glide.with(this)
                .load(R.mipmap.default_head)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(img_touxiang);

        img_szzh.setOnClickListener(this);
        yonghu_dd.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_szzh:
               startActivity(new Intent(context,Fragment_3_A1_Activity.class));
                //test();
                break;
            case R.id.yonghu_dd:
                startActivity(new Intent(context,Fragment_3_A2_Activity.class));
                break;
        }
    }
    public void test(){
        String url="http://192.168.43.200:8080/Test_0.1/showinto.do";
        FormBody body = new FormBody.Builder()
               .build();
        NetTool.postHttp(new Handler(), url, body, new NetTool.OnNetBack() {
            @Override
            public void netFinish(String json) {
                String result = json.equals("1")?"提交成功":"提交失败";
                Toast.makeText(context,result,Toast.LENGTH_LONG).show();

            }
        });

    }

}

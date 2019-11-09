package com.example.family.myapplicationtaobao;




import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;

import android.support.v4.view.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;

import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import android.widget.LinearLayout;

import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

import static android.content.Context.MODE_PRIVATE;


public class Fragment1 extends Fragment implements ViewPager.OnPageChangeListener,View.OnClickListener{
    Context context;
    int[] tp = {R.mipmap.pager1,R.mipmap.pager2,R.mipmap.pager3,R.mipmap.pager4};


    private ViewPager viewPager;

    private int[] imageResIds;

    private ArrayList<ImageView> imageViewList;

    private LinearLayout ll_point_container;

    private String[] contentDescs;

    private TextView tv_desc;

    private int previousSelectedPosition = 0;

    boolean isRunning = false;

    private LinearLayout edit_txtshou;

    int [] f1tpgg = new int[]{R.id.f1_tj1,R.id.f1_tj2,R.id.f1_tj3,R.id.f1_tj4,R.id.f1_tj5,R.id.f1_tj6,R.id.f1_tj7,R.id.f1_tj8,R.id.f1_tj9,R.id.f1_tj10,R.id.f1_tj11,R.id.f1_tj12};


    private GridView gridLayout;
    private ShangAdapter shangAdapter;
    List<ShangpingLB> contactses = new ArrayList<>();
    List<ShangpingLB> contactsesw = new ArrayList<>();
    List<ImageView> listi = new ArrayList<>();

    View view2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1,null);
        context = this.getActivity();
        initView(view);

        initData();

        initAdapter();

        // 开启轮询

        new Thread() {

            public void run() {

                isRunning = true;

                while (isRunning) {

                    try {

                        Thread.sleep(2000);

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }
                    //runOnUiThread
                    // 往下跳一位


                    getActivity().runOnUiThread(new Runnable() {



                        @Override

                        public void run() {

                            System.out.println("设置当前位置: " + viewPager.getCurrentItem());

                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

                        }

                    });

                }

            }

            ;

        }.start();


        return view;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }

    public void initView(final View view){

        view2=view;

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        viewPager.setOnPageChangeListener(this);// 设置页面更新监听

//		viewPager.setOffscreenPageLimit(1);// 左右各保留几个对象

        ll_point_container = (LinearLayout) view.findViewById(R.id.ll_point_container);

        tv_desc = (TextView) view.findViewById(R.id.tv_desc);

        gridLayout = (GridView)view.findViewById(R.id.gl_main);
        edit_txtshou =(LinearLayout) view.findViewById(R.id.edit_txtshou);
        edit_txtshou.setOnClickListener(this);

        String httpzy2=this.getString(R.string.httpzy);
        String url2=httpzy2+"androids/GetgoodsByVolumeLimit12.do";
        FormBody body2 = new FormBody.Builder()
                .build();
        NetTool.postHttp(new Handler(), url2, body2, new NetTool.OnNetBack() {
            @Override
            public void netFinish(String json) {
                Log.i("MyThread3", "json2:" + json);
                JsonArray array2 = new JsonParser().parse(json).getAsJsonArray();
                for (JsonElement element2:array2){
                    ShangpingLB con2 = new Gson().fromJson(element2,ShangpingLB.class);
                    contactsesw.add(con2);
                }
                Log.i("MyThread5", "json2:" + contactsesw.size());
                s12( contactsesw,view );
            }
        });


        String httpzy=this.getString(R.string.httpzy);
        String url=httpzy+"androids/Android_getMainGoodsInfo.do";
        FormBody body = new FormBody.Builder()
                .build();
        NetTool.postHttp(new Handler(), url, body, new NetTool.OnNetBack() {
            @Override
            public void netFinish(String json) {
                Log.i("MyThread2", "json:" + json);
                JsonArray array = new JsonParser().parse(json).getAsJsonArray();
                for (JsonElement element:array){
                    ShangpingLB con = new Gson().fromJson(element,ShangpingLB.class);
                    contactses.add(con);
                }

            }
        });



        shangAdapter = new ShangAdapter(context,contactses);
        gridLayout.setAdapter(shangAdapter);

//        shangAdapter.notifyDataSetChanged();



    }
    public void  s12( List<ShangpingLB> contactsesw,View view ){
        List<ShangpingLB> contactsesw2=contactsesw;
                String httpzy3=this.getString(R.string.httpzy);
        for (int i=0;i<contactsesw2.size();i++){
            ImageView i1 =(ImageView) view2.findViewById(f1tpgg[i]);
            listi.add(i1);
            i1.setOnClickListener(this);
            i1.setTag(null);
            Glide.with(context)
                    .load(httpzy3+contactsesw2.get(i).getGoodsImg())
                    .apply(new RequestOptions().error(R.mipmap.logding_error).placeholder(R.mipmap.loading))
                    .into(i1);
            i1.setTag(i);
        }
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden){
            //Toast.makeText(context, "请输入手机号码", Toast.LENGTH_SHORT).show();
            String httpzy=this.getString(R.string.httpzy);
            String url=httpzy+"androids/Android_getMainGoodsInfo.do";
            FormBody body = new FormBody.Builder()
                    .build();
            NetTool.postHttp(new Handler(), url, body, new NetTool.OnNetBack() {
                @Override
                public void netFinish(String json) {
                    Log.i("MyThread2", "json:" + json);
                    JsonArray array = new JsonParser().parse(json).getAsJsonArray();
                    for (JsonElement element:array){
                        ShangpingLB con = new Gson().fromJson(element,ShangpingLB.class);
                        contactses.add(con);
                    }

                }
            });

            shangAdapter.notifyDataSetChanged();//更新UI
            String httpzy2=this.getString(R.string.httpzy);
            String url2=httpzy2+"androids/GetgoodsByVolumeLimit12.do";
            FormBody body2 = new FormBody.Builder()
                    .build();
            NetTool.postHttp(new Handler(), url2, body2, new NetTool.OnNetBack() {
                @Override
                public void netFinish(String json) {
                    Log.i("MyThread3", "json2:" + json);
                    JsonArray array2 = new JsonParser().parse(json).getAsJsonArray();
                    for (JsonElement element2:array2){
                        ShangpingLB con2 = new Gson().fromJson(element2,ShangpingLB.class);
                        contactsesw.add(con2);
                    }
                    Log.i("MyThread5", "json2:" + contactsesw.size());


                    String httpzy3=context.getString(R.string.httpzy);
                    for (int i=0;i<listi.size();i++){
                        ImageView li = listi.get(i);

                        li.setTag(null);
                        Glide.with(context)
                                .load(httpzy3+contactsesw.get(i).getGoodsImg())
                                .apply(new RequestOptions().error(R.mipmap.logding_error).placeholder(R.mipmap.loading))
                                .into(li);
                        li.setTag(i);
                    }
                }
            });

        }
        super.onHiddenChanged(hidden);
    }
    private void initData() {

        // 图片资源id数组

        imageResIds = new int[]{R.mipmap.pager1,R.mipmap.pager2,R.mipmap.pager3,R.mipmap.pager4};
        // 文本描述

        contentDescs = new String[]{

                "碎花连衣裙",

                "繁花似锦",

                "夏季，马上有爱",

                "奢夏盛宴"
        };



        // 初始化要展示的5个ImageView

        imageViewList = new ArrayList<ImageView>();



        ImageView imageView;

        View pointView;

        LinearLayout.LayoutParams layoutParams;

        for (int i = 0; i < imageResIds.length; i++) {

            // 初始化要显示的图片对象

            imageView = new ImageView(context);

            imageView.setBackgroundResource(imageResIds[i]);

            imageView.setTag(i+1);
            imageView.setOnClickListener(this);

            imageViewList.add(imageView);



            // 加小白点, 指示器

            pointView = new View(context);

            pointView.setBackgroundResource(R.drawable.selector_bg_point);

            layoutParams = new LinearLayout.LayoutParams(5, 5);

            if (i != 0)

                layoutParams.leftMargin = 10;

            // 设置默认所有都不可用

            pointView.setEnabled(false);

            ll_point_container.addView(pointView, layoutParams);

        }

    }
    private void initAdapter() {

        ll_point_container.getChildAt(0).setEnabled(true);

        tv_desc.setText(contentDescs[0]);

        previousSelectedPosition = 0;



        // 设置适配器

        viewPager.setAdapter(new MyAdapter());



        // 默认设置到中间的某个位置

        int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageViewList.size());

        // 2147483647 / 2 = 1073741823 - (1073741823 % 5)

        viewPager.setCurrentItem(5000000); // 设置到某个位置

    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 新的条目被选中时调用

        System.out.println("onPageSelected: " + position);

        int newPosition = position % imageViewList.size();



        //设置文本

        tv_desc.setText(contentDescs[newPosition]);



//		for (int i = 0; i < ll_point_container.getChildCount(); i++) {

//			View childAt = ll_point_container.getChildAt(position);

//			childAt.setEnabled(position == i);

//		}

        // 把之前的禁用, 把最新的启用, 更新指示器

        ll_point_container.getChildAt(previousSelectedPosition).setEnabled(false);

        ll_point_container.getChildAt(newPosition).setEnabled(true);



        // 记录之前的位置

        previousSelectedPosition = newPosition;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        if (null!=v.getTag()) {
            switch ((int) v.getTag()) {
                case 1:
                    Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(context, "3", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(context, "4", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        switch (v.getId()) {
            case R.id.f1_tj1:
                //Toast.makeText(context, ""+contactsesw.get(0).getGoodsSalesVolume(), Toast.LENGTH_SHORT).show();
                tiaozhuanw(contactsesw.get(0).getGoodsId()+"");
                break;
            case R.id.f1_tj2:
                tiaozhuanw(contactsesw.get(1).getGoodsId()+"");
                break;
            case R.id.f1_tj3:
                tiaozhuanw(contactsesw.get(2).getGoodsId()+"");
                break;
            case R.id.f1_tj4:
                tiaozhuanw(contactsesw.get(3).getGoodsId()+"");
                break;
            case R.id.f1_tj5:
                tiaozhuanw(contactsesw.get(4).getGoodsId()+"");
                break;
            case R.id.f1_tj6:
                tiaozhuanw(contactsesw.get(5).getGoodsId()+"");
                break;
            case R.id.f1_tj7:
                tiaozhuanw(contactsesw.get(6).getGoodsId()+"");
                break;
            case R.id.f1_tj8:
                tiaozhuanw(contactsesw.get(7).getGoodsId()+"");
                break;
            case R.id.f1_tj9:
                tiaozhuanw(contactsesw.get(8).getGoodsId()+"");
                break;
            case R.id.f1_tj10:
                tiaozhuanw(contactsesw.get(9).getGoodsId()+"");
                break;
            case R.id.f1_tj11:
                tiaozhuanw(contactsesw.get(10).getGoodsId()+"");
                break;
            case R.id.f1_tj12:
                tiaozhuanw(contactsesw.get(11).getGoodsId()+"");
                break;
            case R.id.edit_txtshou:
                SharedPreferences sp = context.getSharedPreferences("sssr",MODE_PRIVATE);
                SharedPreferences.Editor edt = sp.edit();//用于保存
                edt.putString("pd","0");
                edt.commit();
                startActivity(new Intent(context,SSSRActivity.class));
                break;
        }

    }
    public void tiaozhuanw(String i){
        SharedPreferences sp = context.getSharedPreferences("sid",MODE_PRIVATE);
        SharedPreferences.Editor edt = sp.edit();//用于保存
        edt.putString("id",i);
        edt.commit();
        Intent intent=new Intent(context,Fragment_1_A1_Activity.class);
        context.startActivity(intent);
    }

    class MyAdapter extends PagerAdapter {



        @Override

        public int getCount() {

            return Integer.MAX_VALUE;

        }



        // 3. 指定复用的判断逻辑, 固定写法

        @Override

        public boolean isViewFromObject(View view, Object object) {

//			System.out.println("isViewFromObject: "+(view == object));

            // 当划到新的条目, 又返回来, view是否可以被复用.

            // 返回判断规则

            return view == object;

        }



        // 1. 返回要显示的条目内容, 创建条目

        @Override

        public Object instantiateItem(ViewGroup container, int position) {

            System.out.println("instantiateItem初始化: " + position);

            // container: 容器: ViewPager

            // position: 当前要显示条目的位置 0 -> 4



//			newPosition = position % 5

            int newPosition = position % imageViewList.size();



            ImageView imageView = imageViewList.get(newPosition);

            // a. 把View对象添加到container中

            container.addView(imageView);

            // b. 把View对象返回给框架, 适配器

            return imageView; // 必须重写, 否则报异常

        }



        // 2. 销毁条目

        @Override

        public void destroyItem(ViewGroup container, int position, Object object) {

            // object 要销毁的对象

            System.out.println("destroyItem销毁: " + position);

            container.removeView((View) object);

        }

    }

}

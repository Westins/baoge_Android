package com.example.family.myapplicationtaobao;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by Family on 2019/6/6.
 */
//购物车适配器
public class GouAdapter extends BaseAdapter {
    Context context;
    List<ShoppingCart> contactses;
    Fragment2 f2;

    public GouAdapter(Context context, List<ShoppingCart> contactses,Fragment2 f2){
        this.context=context;
        this.contactses=contactses;
        this.f2=f2;
        Log.i("MyThread2", "xwwwww:" + contactses.size());
    }
    @Override
    public int getCount() {
        return contactses.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        v = LayoutInflater.from(context).inflate(R.layout.fragment_2_1, null);
        Object obj = v.getTag();
          ViewHolder holder;
        if(obj==null){
            holder=new ViewHolder(v);
            v.setTag(holder);
        }else{
            holder=(ViewHolder)obj;
        }
        ShoppingCart con = contactses.get(position);
        holder.imageView.setBackgroundResource(0);
        //holder.imageView.setImageResource(con.getImageView());
        String httpzy=context.getString(R.string.httpzy);
        Glide.with(context)
                .load(httpzy+con.getGoods().getGoodsImg())
                //.apply(new RequestOptions().error(R.mipmap.logding_error).placeholder(R.mipmap.loading).centerCrop())
                .into(holder.imageView);
        holder.text_shangjs.setText(""+con.getGoods().getGoodsName());
        holder.text_shanglb.setText(""+con.getShoppingCartModel());
        holder.text_shangjg.setText(""+con.getGoods().getGoodsOriginalPrice());
        holder.text_shul.setText(""+con.getShoppingCartNum());
        //setChecked(true);
        if ("1".equals(con.getChe_dan())){
            holder.che_dan.setChecked(true);
        }

        holder.che_dan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    contactses.get(position).setChe_dan("1");
                }else{
                    contactses.get(position).setChe_dan("0");
                }

                f2.ToUpdate(0);


            }
        });
        holder.Img_sljian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contactses.get(position).getShoppingCartNum()>1){
                    contactses.get(position).setShoppingCartNum((contactses.get(position).getShoppingCartNum())-1);
                }
                f2.ToUpdate(1);
            }
        });
        holder.Img_slj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactses.get(position).setShoppingCartNum(contactses.get(position).getShoppingCartNum()+1);
                f2.ToUpdate(1);
            }
        });

        return v;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView imageView;
        public TextView text_shangjs;
        public TextView text_shanglb;
        public TextView text_shangjg;
        public TextView text_shul;
        public CheckBox che_dan;
        public ImageView Img_sljian;
        public ImageView Img_slj;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.imageView = (ImageView) rootView.findViewById(R.id.imageView);
            this.text_shangjs = (TextView) rootView.findViewById(R.id.text_shangjs);
            this.text_shanglb = (TextView) rootView.findViewById(R.id.text_shanglb);
            this.text_shangjg = (TextView) rootView.findViewById(R.id.text_shangjg);
            this.text_shul = (TextView) rootView.findViewById(R.id.text_shul);
            this.che_dan = (CheckBox) rootView.findViewById(R.id.che_dan);
            this.Img_sljian = (ImageView) rootView.findViewById(R.id.Img_sljian);
            this.Img_slj = (ImageView) rootView.findViewById(R.id.Img_slj);
        }

    }

}

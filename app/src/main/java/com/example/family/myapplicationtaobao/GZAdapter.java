package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by Family on 2019/6/6.
 */
//关注界面适配器
public class GZAdapter extends BaseAdapter {
    Context context;
    List<GuanZhupojo> contactses;

    public GZAdapter(Context context, List<GuanZhupojo> contactses){
        this.context=context;
        this.contactses=contactses;
    }
    @Override
    public int getCount() {
        return contactses.size();
    }

    @Override
    public Object getItem(int i) {
        return contactses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        v = LayoutInflater.from(context).inflate(R.layout.activity_guanzhu_1, null);
        Object obj = v.getTag();
        ViewHolder holder;
        if(obj==null){
            holder=new ViewHolder(v);
            v.setTag(holder);
        }else{
            holder=(ViewHolder)obj;
        }
        GuanZhupojo con = contactses.get(position);

        holder.gz_tp.setBackgroundResource(0);
        //holder.gz_tp.setImageResource(con.getGz_tp());
        Glide.with(context)
                .load(con.getGz_tp())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()).placeholder(R.mipmap.loading).error(R.mipmap.logding_error))
                .into(holder.gz_tp);
        holder.gz_name.setText(""+con.getGz_name());
        return v;
    }
    /**
     *
     private int id;
     private int gz_tp;
     private String gz_gl;
     private String gz_name;
     *
     * **/
    public static class ViewHolder {
        public View rootView;
        public ImageView gz_tp;
        public CheckBox gz_gl;
        public TextView gz_name;
        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.gz_tp = (ImageView) rootView.findViewById(R.id.gz_tp);
            this.gz_gl = (CheckBox) rootView.findViewById(R.id.gz_gl);
            this.gz_name = (TextView) rootView.findViewById(R.id.gz_name);
        }

    }
}

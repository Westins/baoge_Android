package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Family on 2019/6/6.
 */
//主页 所有商品适配器
public class ShangAdapter extends BaseAdapter {
    Context context;
    List<ShangpingLB> contactses;

    public ShangAdapter(Context context, List<ShangpingLB> contactses){
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
    public View getView(final int position, View v, ViewGroup parent) {
        v = LayoutInflater.from(context).inflate(R.layout.fragment_1_1, null);
        Object obj = v.getTag();
        ViewHolder holder;
        if(obj==null){
            holder=new ViewHolder(v);
            v.setTag(holder);
        }else{
            holder=(ViewHolder)obj;
        }
        ShangpingLB con = contactses.get(position);
        holder.shangping_tp.setBackgroundResource(0);
        //holder.shangping_tp.setImageResource(con.getShangping_tp());
        String httpzy=context.getString(R.string.httpzy);
        Glide.with(context)
                .load(httpzy+con.getGoodsImg())
                .apply(new RequestOptions().error(R.mipmap.logding_error).placeholder(R.mipmap.loading))
                .into(holder.shangping_tp);
        holder.shangping_bt.setText(""+con.getGoodsName());
        holder.shangping_jg.setText(""+con.getGoodsOriginalPrice());
        holder.shangping_xl.setText(""+con.getGoodsSalesVolume());

        holder.shangping_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, ""+contactses.get(position).getGoodsId(), Toast.LENGTH_SHORT).show();
                SharedPreferences sp = context.getSharedPreferences("sid",MODE_PRIVATE);
                SharedPreferences.Editor edt = sp.edit();//用于保存
                edt.putString("id",contactses.get(position).getGoodsId()+"");
                edt.commit();
                Intent intent=new Intent(context,Fragment_1_A1_Activity.class);
                context.startActivity(intent);
            }
        });

        return v;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView shangping_tp;
        public TextView shangping_bt;
        public TextView shangping_jg;
        public TextView shangping_xl;
        public LinearLayout shangping_sp;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.shangping_tp = (ImageView) rootView.findViewById(R.id.shangping_tp);
            this.shangping_bt = (TextView) rootView.findViewById(R.id.shangping_bt);
            this.shangping_jg = (TextView) rootView.findViewById(R.id.shangping_jg);
            this.shangping_xl = (TextView) rootView.findViewById(R.id.shangping_xl);
            this.shangping_sp = (LinearLayout) rootView.findViewById(R.id.shangping_sp);
        }

    }
}

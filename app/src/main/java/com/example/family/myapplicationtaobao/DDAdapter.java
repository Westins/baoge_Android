package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Family on 2019/6/6.
 */
//订单界面适配器
public class DDAdapter extends BaseAdapter {
    Context context;
    List<ShangpingDD> contactses;

    public DDAdapter(Context context, List<ShangpingDD> contactses){
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
        v = LayoutInflater.from(context).inflate(R.layout.activity_fragment_3_2_1, null);
        Object obj = v.getTag();
        ViewHolder holder;
        if(obj==null){
            holder=new ViewHolder(v);
            v.setTag(holder);
        }else{
            holder=(ViewHolder)obj;
        }
        ShangpingDD con = contactses.get(position);

        holder.dd_tp.setBackgroundResource(0);
        //holder.dd_tp.setImageResource(con.getDd_tp());
        String httpzy=context.getString(R.string.httpzy);
        Glide.with(context)
                .load(httpzy+con.getGoods().getGoodsImg())
                .apply(new RequestOptions().error(R.mipmap.logding_error).placeholder(R.mipmap.loading))
                .into(holder.dd_tp);
        holder.dd_bt.setText(""+con.getGoods().getGoodsName());
        holder.dd_jg.setText(""+con.getGoods().getGoodsOriginalPrice());
        holder.dd_sl.setText(""+con.getOrdersInfoNum());
        holder.text_ddsj.setText(""+con.getOrdersInfoTime());
        holder.text_ddgg.setText(""+con.getOrdersInfoModel());

        holder.but_pjan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = context.getSharedPreferences("pl",MODE_PRIVATE);
                SharedPreferences.Editor edt = sp.edit();//用于保存
                edt.putString("goodsId",contactses.get(position).getGoods().getGoodsId()+"");
                edt.commit();
                context.startActivity(new Intent(context,Fragment_3_A3_Activity.class));
            }
        });



        return v;
    }
    /**
     *
     private int dd_tp;
     private String dd_bt;
     private double dd_jg;
     private int dd_sl;
     private String text_ddsj;
     private String text_ddgg;
     *
     * **/



    public static class ViewHolder {
        public View rootView;
        public ImageView dd_tp;
        public TextView dd_bt;
        public TextView dd_jg;
        public TextView dd_sl;
        public TextView text_ddsj;
        public TextView text_ddgg;
        public Button but_pjan;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.dd_tp = (ImageView) rootView.findViewById(R.id.dd_tp);
            this.dd_bt = (TextView) rootView.findViewById(R.id.dd_bt);
            this.dd_jg = (TextView) rootView.findViewById(R.id.dd_jg);
            this.dd_sl = (TextView) rootView.findViewById(R.id.dd_sl);
            this.text_ddsj = (TextView) rootView.findViewById(R.id.text_ddsj);
            this.text_ddgg = (TextView) rootView.findViewById(R.id.text_ddgg);
            this.but_pjan = (Button) rootView.findViewById(R.id.but_pjan);
        }

    }
}

package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by Family on 2019/6/6.
 */
//搜索列表适配器
public class SSLBWAdapter extends BaseAdapter {
    Context context;
    List<ShangpingSSLB> contactses;
    ShouCangActivity shou;

    public SSLBWAdapter(Context context, List<ShangpingSSLB> contactses,ShouCangActivity shou){
        this.context=context;
        this.contactses=contactses;
        this.shou=shou;
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
        v = LayoutInflater.from(context).inflate(R.layout.activity_sslb_1_1, null);
        Object obj = v.getTag();
        ViewHolder holder;
        if(obj==null){
            holder=new ViewHolder(v);
            v.setTag(holder);
        }else{
            holder=(ViewHolder)obj;
        }
        ShangpingSSLB con = contactses.get(position);
        holder.lb_sptp.setBackgroundResource(0);
        //holder.lb_sptp.setImageResource(con.getLb_sptp());
        Glide.with(context)
                .load(con.getGoodsImg())
                .apply(new RequestOptions().error(R.mipmap.logding_error).placeholder(R.mipmap.loading))
                .into(holder.lb_sptp);
        holder.lb_spmc.setText(""+con.getGoodsName());
        holder.lb_spjg.setText(""+con.getGoodsOriginalPrice());
        holder.lb_gmrs.setText(""+con.getGoodsSalesVolume());
        holder.lb_dpmc.setText("");
        if ("1".equals(con.getLb_dx())){
            holder.lb_dx.setChecked(true);
        }
        holder.lb_dx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    contactses.get(position).setLb_dx("1");
                }else{
                    contactses.get(position).setLb_dx("0");
                }
            }
        });
        holder.lb_jdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, ""+contactses.get(position).getDpid(), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
    /**
     *      private int id;
     private int lb_sptp;
     private String lb_spmc;
     private double lb_spjg;
     private int lb_gmrs;
     private String lb_dpdz;
     private String lb_dpmc;
     *
     * **/



    public static class ViewHolder {
        public View rootView;
        public ImageView lb_sptp;
        public TextView lb_spmc;
        public TextView lb_spjg;
        public TextView lb_gmrs;
        public TextView lb_dpmc;
        public CheckBox lb_dx;
        public LinearLayout lb_jdan;


        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.lb_sptp = (ImageView) rootView.findViewById(R.id.lb_sptp);
            this.lb_spmc = (TextView) rootView.findViewById(R.id.lb_spmc);
            this.lb_spjg = (TextView) rootView.findViewById(R.id.lb_spjg);
            this.lb_gmrs = (TextView) rootView.findViewById(R.id.lb_gmrs);
            this.lb_dpmc = (TextView) rootView.findViewById(R.id.lb_dpmc);
            this.lb_dx = (CheckBox) rootView.findViewById(R.id.lb_dx);
            this.lb_jdan = (LinearLayout) rootView.findViewById(R.id.lb_jdan);
        }

    }
}

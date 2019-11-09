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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Family on 2019/6/6.
 */
//搜索列表适配器
public class SSLBAdapter extends BaseAdapter {
    Context context;
    List<ShangpingSSLB> contactses;

    public SSLBAdapter(Context context, List<ShangpingSSLB> contactses){
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
        v = LayoutInflater.from(context).inflate(R.layout.activity_sslb_1, null);
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
        String httpzy=context.getString(R.string.httpzy);
        Glide.with(context)
                .load(httpzy+con.getGoodsImg())
                .apply(new RequestOptions().error(R.mipmap.logding_error).placeholder(R.mipmap.loading))
                .into(holder.lb_sptp);
        holder.lb_spmc.setText(""+con.getGoodsName());
        holder.lb_spjg.setText(""+con.getGoodsOriginalPrice());
        holder.lb_gmrs.setText(""+con.getGoodsSalesVolume());
        holder.lb_dpdz.setText(""+"长沙");
        holder.lb_dpmc.setText("");

        holder.shang_lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        public TextView lb_dpdz;
        public TextView lb_dpmc;
        public LinearLayout shang_lb;


        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.lb_sptp = (ImageView) rootView.findViewById(R.id.lb_sptp);
            this.lb_spmc = (TextView) rootView.findViewById(R.id.lb_spmc);
            this.lb_spjg = (TextView) rootView.findViewById(R.id.lb_spjg);
            this.lb_gmrs = (TextView) rootView.findViewById(R.id.lb_gmrs);
            this.lb_dpdz = (TextView) rootView.findViewById(R.id.lb_dpdz);
            this.lb_dpmc = (TextView) rootView.findViewById(R.id.lb_dpmc);
            this.shang_lb = (LinearLayout) rootView.findViewById(R.id.shang_lb);
        }

    }
}

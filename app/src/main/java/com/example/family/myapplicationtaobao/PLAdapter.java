package com.example.family.myapplicationtaobao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Family on 2019/6/6.
 */
//评论适配器
public class PLAdapter extends BaseAdapter {
    Context context;
    List<ShangpingPL> contactses;

    public PLAdapter(Context context, List<ShangpingPL> contactses){
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
        v = LayoutInflater.from(context).inflate(R.layout.activity_fragment_1_1_1, null);
        Object obj = v.getTag();
        ViewHolder holder;
        if(obj==null){
            holder=new ViewHolder(v);
            v.setTag(holder);
        }else{
            holder=(ViewHolder)obj;
        }
        ShangpingPL con = contactses.get(position);
        holder.pl_plrname.setText(""+con.getUserinfo().getUserName());
        holder.pl_goumairq.setText("2019");
        holder.pl_goumaigg.setText(" ");
        holder.pl_plnr.setText(""+con.getGoodsReviewTest());

        return v;
    }
    /**
     *  private int id;
     private String pl_plrname;
     private String pl_goumairq;
     private String pl_goumaigg;
     private String pl_plnr;
     *
     * **/



    public static class ViewHolder {
        public View rootView;
        public TextView pl_plrname;
        public TextView pl_goumairq;
        public TextView pl_goumaigg;
        public TextView pl_plnr;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.pl_plrname = (TextView) rootView.findViewById(R.id.pl_plrname);
            this.pl_goumairq = (TextView) rootView.findViewById(R.id.pl_goumairq);
            this.pl_goumaigg = (TextView) rootView.findViewById(R.id.pl_goumaigg);
            this.pl_plnr = (TextView) rootView.findViewById(R.id.pl_plnr);
        }

    }
}

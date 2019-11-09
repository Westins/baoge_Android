package com.example.family.myapplicationtaobao;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
//解决嵌套滚动条冲突  重写ListView
public class MultiListView extends ListView {


    public MultiListView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MultiListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public MultiListView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

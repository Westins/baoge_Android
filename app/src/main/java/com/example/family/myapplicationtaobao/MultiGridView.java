package com.example.family.myapplicationtaobao;

/**
 * Created by Family on 2019/6/14.
 */


import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
//解决嵌套滚动条冲突  重写GridView
public class MultiGridView extends GridView {
    public MultiGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiGridView(Context context) {
        super(context);
    }

    public MultiGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}

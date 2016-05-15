package com.imooc.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by SUN on 2016/5/15.
 */
public class StaggeredAdapter extends SimpleAdapter {
    protected List<Integer> mHeights;

    public StaggeredAdapter(Context context, List<String> datas){
        super(context, datas);
        initItemHeights();
    }

    private void initItemHeights() {
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mDatas.size(); i++){
            mHeights.add((int)(100+ Math.random()*400));
        }
    }


    //数据的赋值
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {

        ViewGroup.LayoutParams lp = viewHolder.itemView.getLayoutParams();
        lp.height = mHeights.get(i);
        viewHolder.itemView.setLayoutParams(lp);
        viewHolder.tv.setText(mDatas.get(i));


        setupItemEvent(viewHolder);

    }

}

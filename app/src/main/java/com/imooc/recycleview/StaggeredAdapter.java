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
public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mDatas;
    private LayoutInflater mLayoutInflater;
    private List<Integer> mHeights;

    public StaggeredAdapter(Context context, List<String> datas){
        mContext = context;
        mDatas = datas;
        mLayoutInflater = LayoutInflater.from(mContext);
        initItemHeights();
    }

    private void initItemHeights() {
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mDatas.size(); i++){
            mHeights.add((int)(100+ Math.random()*400));
        }
    }

    /**
     * layout init
     * */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_view, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    //数据的赋值
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {

        ViewGroup.LayoutParams lp = viewHolder.itemView.getLayoutParams();
        lp.height = mHeights.get(i);
        viewHolder.itemView.setLayoutParams(lp);
        viewHolder.tv.setText(mDatas.get(i));

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_tv_item);
        }
    }

    public void addItem(int pos){
        mDatas.add(pos, "Insert one");
        notifyItemInserted(pos);
    }

    public void removeItem(int pos){
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }
}

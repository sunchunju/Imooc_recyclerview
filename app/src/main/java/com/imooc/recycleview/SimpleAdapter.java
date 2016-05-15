package com.imooc.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SUN on 2016/5/15.
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mDatas;
    private LayoutInflater mLayoutInflater;

    public SimpleAdapter(Context context, List<String> datas){
        mContext = context;
        mDatas = datas;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * jianting jiekou*/
    public interface OnItemClickListener{
        void onItemClick(View view, int pos);
        void onItemLongClick(View view, int pos);
    }

    private OnItemClickListener mOnItemClickListener;

    /**
     * 供外部activity设置回调*/
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
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
    public void onBindViewHolder(final MyViewHolder viewHolder, final int i) {
        viewHolder.tv.setText(mDatas.get(i));
//        final int pos = viewHolder.getLayoutPosition();

        //触发回调
        if (mOnItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPos = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(viewHolder.itemView, layoutPos);
                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int layoutPos = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(viewHolder.itemView, layoutPos);
                    return false;
                }
            });
        }

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

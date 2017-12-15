package com.bj.myapplication.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bj.myapplication.Bean.HomePage;
import com.bj.myapplication.R;

import java.util.List;

/**
 * Created by 吴丽杰 on 2017/12/15.
 */

public class TTAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    List<HomePage.RetBean.ListBean.ChildListBean> childList;
    Context context;

    public TTAdapter(List<HomePage.RetBean.ListBean.ChildListBean> childList, Context context) {
        this.childList = childList;
        this.context = context;
    }

    private TTAdapter.OnItemClickListener mOnItemClickListener = null;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(TTAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ttitem, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        for (int i = 0; i <childList.size(); i++) {
            myViewHolder.ta_image.setImageURI(Uri.parse(childList.get(i).getPic()));
            myViewHolder.ta_tv.setText(childList.get(i).getTitle());
        }
        myViewHolder.ta_image.setTag(position);
    }

    @Override
    public int getItemCount() {
        return childList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ta_image;
        private final TextView ta_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ta_image = itemView.findViewById(R.id.ta_image);
            ta_tv = itemView.findViewById(R.id.ta_tv);
        }
    }

}

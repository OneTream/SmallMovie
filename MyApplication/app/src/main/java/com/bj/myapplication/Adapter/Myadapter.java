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
 * Created by 努力努力再努力 on 2017/11/23.
 */

public class Myadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    List<HomePage.RetBean.ListBean.ChildListBean> childList;
    Context context;

    public Myadapter(List<HomePage.RetBean.ListBean.ChildListBean> childList, Context context) {
        this.childList = childList;
        this.context = context;
    }

    private OnItemClickListener mOnItemClickListener = null;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
       MyViewHolder vh = new MyViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
            myViewHolder.main_adv.setImageURI(Uri.parse(childList.get(position).getPic()));
            myViewHolder.tv.setText(childList.get(position).getTitle());
            String dataId = childList.get(position).getDataId();
        myViewHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return childList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView main_adv;
        private final TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            main_adv = itemView.findViewById(R.id.main_adv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}

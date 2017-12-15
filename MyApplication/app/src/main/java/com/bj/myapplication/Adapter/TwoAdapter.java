package com.bj.myapplication.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bj.myapplication.Bean.HomePage;
import com.bj.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.*;

/**
 * Created by 吴丽杰 on 2017/12/14.
 */

public class TwoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    List<HomePage.RetBean.ListBean> lists;
    Context context;

    public TwoAdapter(List<HomePage.RetBean.ListBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    private OnItemClickListener mOnItemClickListener = null;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
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
        View view = LayoutInflater.from(context).inflate(R.layout.twoitem, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        List<HomePage.RetBean.ListBean.ChildListBean> childList = lists.get(position).getChildList();
        myViewHolder.two_image.setImageURI(Uri.parse((lists.get(position).getChildList().get(0).getPic())));
        myViewHolder.two_tv.setText(lists.get(position).getTitle());

        myViewHolder.two_image.setTag(position);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView two_image;
        private final TextView two_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            two_image = itemView.findViewById(R.id.two_image);
            two_tv = itemView.findViewById(R.id.two_text);
        }
    }

}

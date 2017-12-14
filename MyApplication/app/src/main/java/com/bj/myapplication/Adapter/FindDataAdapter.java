package com.bj.myapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bj.myapplication.Bean.FindDataBean;
import com.bj.myapplication.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/21.
 */

public class FindDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FindDataBean.RetBean.ListBean> list;
    private Context context;
    private OnClickItem onClickItem;

    public interface OnClickItem {
        void clickImg(int position);
    }

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public FindDataAdapter(List<FindDataBean.RetBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.find_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        FindDataBean.RetBean.ListBean listBean = list.get(position);
        myViewHolder.tvTitle.setText(listBean.title);
        myViewHolder.tvJs.setText(listBean.description);
        Glide.with(context).load(listBean.pic).into(myViewHolder.sdv);
        myViewHolder.sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.clickImg(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final ImageView sdv;
        private final TextView tvJs;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            sdv = itemView.findViewById(R.id.sdv);
            tvJs = itemView.findViewById(R.id.tvJs);
        }
    }
}

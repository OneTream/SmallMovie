package com.bj.myapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bj.myapplication.Bean.SearchBean;
import com.bj.myapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/12/14.
 */

public class SearchDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SearchBean.RetBean.ListBean> list;
    private Context context;

    private OnClickSItem onClickSItem;
    public interface OnClickSItem{
        void onClick(int position);
    }
    public void setOnClickSItem(OnClickSItem onClickSItem){
        this.onClickSItem=onClickSItem;
    }
    public SearchDataAdapter(List<SearchBean.RetBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHodler myViewHodler = (MyViewHodler) holder;
        SearchBean.RetBean.ListBean listBean = list.get(position);
        myViewHodler.sdv.setImageURI(listBean.pic);
        myViewHodler.tv.setText(listBean.title);
        myViewHodler.sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSItem.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHodler extends RecyclerView.ViewHolder {

        private final SimpleDraweeView sdv;
        private final TextView tv;

        public MyViewHodler(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.main_adv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}

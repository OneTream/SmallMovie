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

public class TwoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<HomePage.RetBean.ListBean> lists;
    Context context;
    /**
     * 自定义接口实现点击事件
     */
    private OnClickItemListener onClickItemListener;

    public  interface  OnClickItemListener{
        void onClick(int position);
    }
    public  void setOnClickItemListener( OnClickItemListener onClickItemListener){
        this.onClickItemListener=onClickItemListener;
    }
    public TwoAdapter(List<HomePage.RetBean.ListBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.twoitem, parent, false);
        final MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        HomePage.RetBean.ListBean listBean = lists.get(position);
        String moreURL = listBean.getMoreURL();
        String title = listBean.getTitle();
        List<HomePage.RetBean.ListBean.ChildListBean> childList = listBean.getChildList();
        for (int i = 0; i <childList.size() ; i++) {
            String pic = childList.get(i).getPic();
            myViewHolder.two_image.setImageURI(Uri.parse(pic));
        }

        myViewHolder.two_tv.setText(title);

        myViewHolder.two_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListener.onClick(position);
            }
        });
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

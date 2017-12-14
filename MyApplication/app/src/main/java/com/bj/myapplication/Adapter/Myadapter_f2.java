package com.bj.myapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bj.myapplication.Bean.Comment;
import com.bj.myapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 努力努力再努力 on 2017/11/23.
 */

public class Myadapter_f2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Comment.RetBean.ListBean> list;
    Context context;

    public Myadapter_f2(List<Comment.RetBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_f2, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        Comment.RetBean.ListBean listBean = list.get(position);
        myViewHolder.main_adv.setImageURI(listBean.getUserPic());
        myViewHolder.phoneNumber.setText(listBean.getPhoneNumber());
        myViewHolder.time.setText(listBean.getTime());
        myViewHolder.msg.setText(listBean.getMsg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{


        private final SimpleDraweeView main_adv;
        private final TextView phoneNumber;
        private final TextView time;
        private final TextView msg;

        public MyViewHolder(View itemView) {
            super(itemView);
            main_adv = itemView.findViewById(R.id.main_adv);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            time = itemView.findViewById(R.id.time);
            msg = itemView.findViewById(R.id.msg);
        }
    }
}


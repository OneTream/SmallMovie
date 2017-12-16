package com.bj.myapplication.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bj.myapplication.Bean.FilmBean;
import com.bj.myapplication.R;
import com.bj.myapplication.net.OnNetListener;

import java.util.List;

/**
 * Created by 吴丽杰 on 2017/12/15.
 */

public class TTAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FilmBean.RetBean.ListBean> list;
    private Context context;

    public TTAdapter(List<FilmBean.RetBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ttitem, parent, false);
        final MyViewHolder vh = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecyclerListener.onRecycler(vh.getPosition());
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        String pic = list.get(position).getPic();
        myViewHolder.ta_image.setImageURI(Uri.parse(pic));
        myViewHolder.ta_tv.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
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

    /**
     * 自定义接口实现点击事件
     */
    private OnRecyclerListener onRecyclerListener;

    public interface OnRecyclerListener {
        void onRecycler(int position);
    }

    public void setOnRecyclerListener(OnRecyclerListener onRecyclerListener) {
        this.onRecyclerListener = onRecyclerListener;
    }
}

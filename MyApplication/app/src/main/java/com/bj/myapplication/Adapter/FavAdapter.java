package com.bj.myapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bj.myapplication.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 大白 on 2017/12/15.
 */

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.MyViewHolder> {

    private List<String> images;
    private Context context;

    public FavAdapter(List<String> images, Context context) {
        this.images = images;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.fav_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

         ViewGroup.LayoutParams params = holder.img_fav.getLayoutParams();
                 if(position == 0){
                     params.height = 200;
                 }else{
                     params.height = 400;
                 }
                 holder.img_fav.setLayoutParams(params);

        Glide.with(context).load(images.get(position)).into(holder.img_fav);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }




    class  MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img_fav;

        public MyViewHolder(View itemView) {
            super(itemView);

            img_fav = itemView.findViewById(R.id.img_fav);

        }
    }

}

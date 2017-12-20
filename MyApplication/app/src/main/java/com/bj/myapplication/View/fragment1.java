package com.bj.myapplication.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bj.myapplication.Adapter.Myadapter_f1;
import com.bj.myapplication.Bean.VideoDetail;
import com.bj.myapplication.R;
import com.bj.myapplication.presenter.P_Video_Detail;

import java.util.List;

import static android.R.id.edit;
import static android.content.Context.MODE_PRIVATE;


/**
 * Created by 努力努力再努力 on 2017/11/23.
 */

public class fragment1 extends Fragment implements IView_Video_Detail, View.OnClickListener {

    private RecyclerView rv;
    private TextView actors;
    private TextView director;
    private String dataId;
    private TextView description;
    private TextView open;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("User",MODE_PRIVATE);
        dataId = sharedPreferences.getString("dataId", "");
        P_Video_Detail p_video_detail=new P_Video_Detail(this);
        p_video_detail.relevance2(this.dataId);
        actors = view.findViewById(R.id.actors);
        director = view.findViewById(R.id.director);
        description = view.findViewById(R.id.description);
        rv = view.findViewById(R.id.rv_f1);
        open = view.findViewById(R.id.open);
        open.setOnClickListener(this);
        return view;
    }
    @Override
    public void getShow(VideoDetail videoDetail) {
        final List<VideoDetail.RetBean.ListBean.ChildListBean> childList = videoDetail.getRet().getList().get(0).getChildList();
        Log.i("childList",childList.get(0).getTitle());
        Myadapter_f1 myadapter_f1=new Myadapter_f1(childList,getActivity());
        myadapter_f1.setOnItemClickListener(new Myadapter_f1.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                dataId = childList.get(position).getDataId();
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                intent.putExtra("dataId", dataId);
                startActivity(intent);
            }
        });
        rv.setLayoutManager(new GridLayoutManager(getActivity(),3));
        rv.setAdapter(myadapter_f1);
        actors.setText("导演："+videoDetail.getRet().getActors());
        director.setText("主演："+videoDetail.getRet().getDirector());
        description.setText("简介："+videoDetail.getRet().getDescription());
    }

    @Override
    public void onClick(View v) {
        if(description.getVisibility()==View.VISIBLE){
            actors.setVisibility(View.VISIBLE);
            director.setVisibility(View.VISIBLE);
            description.setVisibility(View.GONE);
            open.setText("展开");
        }else{
            actors.setVisibility(View.VISIBLE);
            director.setVisibility(View.VISIBLE);
            description.setVisibility(View.VISIBLE);
            open.setText("收起");
        }
    }
}

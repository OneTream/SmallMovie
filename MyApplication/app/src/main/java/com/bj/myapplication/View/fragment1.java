package com.bj.myapplication.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bj.myapplication.Adapter.Myadapter_f1;
import com.bj.myapplication.Bean.VideoDetail;
import com.bj.myapplication.R;
import com.bj.myapplication.presenter.P_Video_Detail;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 努力努力再努力 on 2017/11/23.
 */

public class fragment1 extends Fragment implements IView_Video_Detail{

    private RecyclerView rv;
    private TextView actors;
    private TextView director;
    private String dataId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("User",MODE_PRIVATE);
        dataId = sharedPreferences.getString("dataId","b6c8438873fd4a0f8880b2def64c6472");
        P_Video_Detail p_video_detail=new P_Video_Detail(this);
        p_video_detail.relevance2(dataId);
        actors = view.findViewById(R.id.actors);
        director = view.findViewById(R.id.director);
        rv = view.findViewById(R.id.rv);
        return view;
    }
    @Override
    public void getShow(VideoDetail videoDetail) {
        List<VideoDetail.RetBean.ListBean> list = videoDetail.getRet().getList();
        List<VideoDetail.RetBean.ListBean.ChildListBean> childList = list.get(0).getChildList();
        Myadapter_f1 myadapter_f1=new Myadapter_f1(childList,getActivity());
        myadapter_f1.setOnItemClickListener(new Myadapter_f1.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                intent.putExtra("dataId", dataId);
                startActivity(intent);
            }
        });
        rv.setLayoutManager(new GridLayoutManager(getActivity(),3));
        rv.setAdapter(myadapter_f1);
    }
}

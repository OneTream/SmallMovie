package com.bj.myapplication.View;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bj.myapplication.Adapter.Myadapter_f2;
import com.bj.myapplication.Bean.Comment;
import com.bj.myapplication.R;
import com.bj.myapplication.presenter.P_Comment;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by 努力努力再努力 on 2017/11/23.
 */

public class fragment2 extends Fragment implements IView_Comment{

    private RecyclerView rv_f2;
    private String dataId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        rv_f2 = view.findViewById(R.id.rv_f2);
        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("User",MODE_PRIVATE);
        dataId = sharedPreferences.getString("dataId","b6c8438873fd4a0f8880b2def64c6472");
        P_Comment p_comment=new P_Comment(this);
        p_comment.relevance3(dataId);
        return view;
    }

    @Override
    public void getShow(Comment comment) {
        List<Comment.RetBean.ListBean> list = comment.getRet().getList();
        rv_f2.setLayoutManager(new LinearLayoutManager(getActivity()));
        Myadapter_f2 myadapter_f2=new Myadapter_f2(list,getActivity());
        rv_f2.setAdapter(myadapter_f2);
    }
}

package com.bj.myapplication.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bj.myapplication.Adapter.Myadapter;
import com.bj.myapplication.Adapter.TwoAdapter;
import com.bj.myapplication.Bean.HomePage;
import com.bj.myapplication.R;
import com.bj.myapplication.presenter.P_Home_Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 努力努力再努力 on 2017/12/13.
 */

public class TwoFragment extends Fragment implements IView_Home_Page {

    private RecyclerView mRv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.twofragment, container, false);
        mRv = view.findViewById(R.id.rv);
        P_Home_Page p_home_page = new P_Home_Page(this);
        p_home_page.relevance();
        return view;
    }

    @Override
    public void getShow(HomePage homePage) {
        List<HomePage.RetBean.ListBean> lists = homePage.getRet().getList();
        TwoAdapter twoAdapter = new TwoAdapter(lists,getActivity());
        mRv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRv.setAdapter(twoAdapter);

        twoAdapter.setOnItemClickListener(new TwoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), TwoActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.bj.myapplication.View;

import android.content.Intent;
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
    /**
     * 我的
     */
    private TextView mTitleName;
    private List<HomePage.RetBean.ListBean.ChildListBean> childList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.twofragment, container, false);
        P_Home_Page p_home_page = new P_Home_Page(this);
        p_home_page.relevance();
        initView(view);
        mTitleName.setText("专辑");
        return view;
    }

    @Override
    public void getShow(HomePage homePage) {
        final List<HomePage.RetBean.ListBean> lists = homePage.getRet().getList();

        Log.e("SSSSSSSSSSSSSSSSSS",lists+"  ");
        for (int i = 0; i <lists.size() ; i++) {
            childList = lists.get(i).getChildList();

        }

        final List<HomePage.RetBean.ListBean> newList=new ArrayList<>();

        for (int i = 0; i <lists.size() ; i++) {
            String moreURL = lists.get(i).getMoreURL();
            HomePage.RetBean.ListBean listBean = lists.get(i);
            if (!moreURL.equals("")){
                newList.add(listBean);
            }
        }



        TwoAdapter twoAdapter = new TwoAdapter(newList, getActivity());
        mRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRv.setAdapter(twoAdapter);
        twoAdapter.setOnClickItemListener(new TwoAdapter.OnClickItemListener() {
            @Override
            public void onClick(int position) {

                String moreURL = newList.get(position).getMoreURL();
                String title1 = newList.get(position).getTitle();
              //  Toast.makeText(getActivity(),moreURL+""+title1,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), TwoActivity.class);
                intent.putExtra("moreURL", moreURL);
                intent.putExtra("title1", title1);
                startActivity(intent);
            }
        });
        /*twoAdapter.setOnClickItemListener(new TwoAdapter.OnClickItemListener() {
            @Override
            public void onClick(int position) {
                String moreURL = sp.get(position).getMoreURL();
                String title1 = sp.get(position).getTitle();
                Intent intent = new Intent(getActivity(), TwoActivity.class);
                intent.putExtra("moreURL", moreURL);
                intent.putExtra("title1", title1);
                startActivity(intent);
            }
        });*/
    }

    private void initView(View view) {
        mTitleName = (TextView) view.findViewById(R.id.title_name);
        mRv = (RecyclerView) view.findViewById(R.id.rv);
    }
}

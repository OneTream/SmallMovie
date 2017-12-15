package com.bj.myapplication.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bj.myapplication.Adapter.TwoAdapter;
import com.bj.myapplication.Bean.HomePage;
import com.bj.myapplication.R;
import com.bj.myapplication.presenter.P_Home_Page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 努力努力再努力 on 2017/12/13.
 */

public class TwoFragment extends Fragment implements IView_Home_Page {

    private RecyclerView mRv;
    private View view;
    /**
     * 我的
     */
    private TextView mTitleName;

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
        TwoAdapter twoAdapter = new TwoAdapter(lists, getActivity());
        mRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRv.setAdapter(twoAdapter);

        twoAdapter.setOnItemClickListener(new TwoAdapter.OnItemClickListener() {

            private String moreURL;

            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), TwoActivity.class);
                moreURL = lists.get(position).getMoreURL();
                List<HomePage.RetBean.ListBean.ChildListBean> childList = lists.get(position).getChildList();
                intent.putExtra("list", (Serializable) childList);
                String title = lists.get(position).getTitle();
                intent.putExtra("title", title);
                intent.putExtra("moreURL", moreURL);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (moreURL.equals("")){
                            moreURL ="http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=ff8080815b9075a6015b94ef79dc0284&information=null";
                        }
                    }
                });
                intent.putExtra("moreURL", moreURL);
                startActivity(intent);
            }
        });
    }

    private void initView(View view) {
        mTitleName = (TextView) view.findViewById(R.id.title_name);
        mRv = (RecyclerView) view.findViewById(R.id.rv);
    }
}

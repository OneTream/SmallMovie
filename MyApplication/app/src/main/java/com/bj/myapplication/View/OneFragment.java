package com.bj.myapplication.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bj.myapplication.Adapter.Myadapter;
import com.bj.myapplication.Bean.HomePage;
import com.bj.myapplication.R;
import com.bj.myapplication.presenter.P_Home_Page;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 努力努力再努力 on 2017/12/13.
 */

public class OneFragment extends Fragment implements IView_Home_Page {
    private Banner mBanner;
    private RecyclerView mRlv;
    private EditText editText;
    private String dataId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onefragment, container, false);
        editText = view.findViewById(R.id.etSearch);
        P_Home_Page p = new P_Home_Page(this);
        p.relevance();
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        initView(view);
        return view;
    }

    @Override
    public void getShow(HomePage homePage) {
        final List<HomePage.RetBean.ListBean.ChildListBean> childList = homePage.getRet().getList().get(0).getChildList();
        Myadapter myadapter = new Myadapter(childList, getActivity());
        mRlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRlv.setAdapter(myadapter);
        myadapter.setOnItemClickListener(new Myadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                dataId = childList.get(position).getDataId();
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                intent.putExtra("dataId", dataId);
                startActivity(intent);


            }
        });
        List<String> mlist = new ArrayList<>();
        for (int i = 0; i < childList.size(); i++) {
            String pic = childList.get(i).getPic();
            mlist.add(pic);
        }
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(mlist);
        mBanner.start();
        mBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                intent.putExtra("dataId", dataId);
                startActivity(intent);
            }
        });
    }

    private void initView(View view) {
        mBanner = (Banner) view.findViewById(R.id.banner);
        mRlv = (RecyclerView) view.findViewById(R.id.rlv);
    }


    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}

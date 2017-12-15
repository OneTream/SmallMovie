package com.bj.myapplication.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bj.myapplication.Adapter.TTAdapter;
import com.bj.myapplication.Bean.FilmBean;
import com.bj.myapplication.Bean.HomePage;
import com.bj.myapplication.R;
import com.bj.myapplication.net.ApiService;
import com.bj.myapplication.net.OkHttpUtils;
import com.bj.myapplication.net.OnNetListener;
import com.bj.myapplication.net.OnNetLister;
import com.bj.myapplication.net.RetrofitUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by 吴丽杰 on 2017/12/14.
 */

public class TwoActivity extends Activity{
    private String dataId;
    private TextView mTv;
    private RecyclerView mRlv;
    private String moreURL;
    private List<FilmBean.RetBean.ListBean> list;
    private TTAdapter ttAdapter;
    private View inflate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        mTv = findViewById(R.id.tt_tv);
        mRlv = findViewById(R.id.tt_rlv);
        Log.e("XXX", moreURL);

        ttAdapter = new TTAdapter(list,this);
        OkHttpUtils.getInstance(this).doget(moreURL, FilmBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Object o) throws IOException {
                FilmBean filmBean = (FilmBean) o;
                list = filmBean.getRet().getList();
                mRlv.setAdapter(ttAdapter);
            }

            @Override
            public void onError(IOException e) {

            }
        });
        mRlv.setLayoutManager(new GridLayoutManager(this, 3));
    }

}

package com.bj.myapplication.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bj.myapplication.Adapter.TTAdapter;
import com.bj.myapplication.Adapter.TwoAdapter;
import com.bj.myapplication.Bean.FilmBean;
import com.bj.myapplication.R;
import com.bj.myapplication.net.OkHttpUtils;
import com.bj.myapplication.net.OnNetListener;

import java.io.IOException;
import java.util.List;


/**
 * Created by 吴丽杰 on 2017/12/14.
 */

public class TwoActivity extends Activity{
    private RecyclerView mRlv;
    private TextView mTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        mTv = findViewById(R.id.tt_tv);
        mRlv = findViewById(R.id.tt_rlv);
        mRlv.setLayoutManager(new GridLayoutManager(this, 3));
        Intent intent = getIntent();
        String moreURL = intent.getStringExtra("moreURL");
        String title1 = intent.getStringExtra("title1");
        mTv.setText(title1);
        OkHttpUtils.getInstance(this).doget(moreURL, FilmBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Object o) throws IOException {
                final FilmBean filmBean = (FilmBean) o;
                final List<FilmBean.RetBean.ListBean> list = filmBean.getRet().getList();
                TTAdapter ttAdapter = new TTAdapter(list,TwoActivity.this);
                mRlv.setAdapter(ttAdapter);
                ttAdapter.setOnRecyclerListener(new TTAdapter.OnRecyclerListener() {
                    @Override
                    public void onRecycler(int position) {
                        Intent intent1 = new Intent(TwoActivity.this,Main2Activity.class);
                        String dataId = list.get(position).getDataId();
                        intent1.putExtra("dataId",dataId);
                        startActivity(intent1);
                    }
                });
            }

            @Override
            public void onError(IOException e) {

            }
        });
    }

}

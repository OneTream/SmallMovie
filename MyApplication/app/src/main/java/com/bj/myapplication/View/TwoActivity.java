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
import com.bj.myapplication.Bean.HomePage;
import com.bj.myapplication.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 吴丽杰 on 2017/12/14.
 */

public class TwoActivity extends Activity implements View.OnClickListener{
    private String dataId;
    private TextView mTv;
    private RecyclerView mRlv;
    private String moreURL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        mTv = findViewById(R.id.tt_tv);
        mRlv = findViewById(R.id.tt_rlv);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        List<HomePage.RetBean.ListBean.ChildListBean> childList= (List<HomePage.RetBean.ListBean.ChildListBean>) intent.getSerializableExtra("list");
        moreURL = intent.getStringExtra("moreURL");
        Log.e("XXX", moreURL);
        Toast.makeText(TwoActivity.this,childList.toString(),Toast.LENGTH_LONG).show();
        mTv.setText(title);
        TTAdapter ttAdapter = new TTAdapter(childList, this);
        mRlv.setLayoutManager(new GridLayoutManager(this, 3));
        mRlv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwoActivity.this, Main2Activity.class);
                intent.putExtra("dataId", dataId);
                startActivity(intent);
            }
        });
        mRlv.setAdapter(ttAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}

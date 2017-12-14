package com.bj.myapplication.View;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bj.myapplication.Adapter.SearchDataAdapter;
import com.bj.myapplication.Bean.SearchBean;
import com.bj.myapplication.R;
import com.bj.myapplication.presenter.SearchPresenter;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchView, View.OnClickListener {

    /**
     * 请输入您要喜欢的电影
     */
    private EditText mEtSearch;
    /**
     * 搜索
     */
    private TextView mTvSearch;
    private RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        initView();
        mRv.setLayoutManager(new GridLayoutManager(this,3));

    }

    @Override
    public void getSearchData(SearchBean searchBean) {
        final List<SearchBean.RetBean.ListBean> list = searchBean.ret.list;
        SearchDataAdapter searchDataAdapter = new SearchDataAdapter(list,this);
        mRv.setAdapter(searchDataAdapter);
        searchDataAdapter.setOnClickSItem(new SearchDataAdapter.OnClickSItem() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(SearchActivity.this, Main2Activity.class);
                String dataId = list.get(position).dataId;
                Toast.makeText(SearchActivity.this,dataId,Toast.LENGTH_SHORT).show();
                intent.putExtra("dataId",dataId);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        mEtSearch = (EditText) findViewById(R.id.etSearch);
        mTvSearch = (TextView) findViewById(R.id.tvSearch);
        mTvSearch.setOnClickListener(this);
        mRv = (RecyclerView) findViewById(R.id.rv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSearch:
                String keyWord = mEtSearch.getText().toString();
                if (TextUtils.isEmpty(keyWord)){
                    Toast.makeText(SearchActivity.this,"请输入关键字",Toast.LENGTH_SHORT).show();
                    return;
                }
                SearchPresenter searchPresenter = new SearchPresenter(this);
                searchPresenter.relevance(keyWord,1);
                break;
        }
    }
}

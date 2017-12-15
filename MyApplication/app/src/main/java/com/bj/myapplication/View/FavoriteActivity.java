package com.bj.myapplication.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.bj.myapplication.Adapter.FavAdapter;
import com.bj.myapplication.Bean.FavBean;
import com.bj.myapplication.R;
import com.bj.myapplication.net.GankApis;
import com.bj.myapplication.net.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView recycler_fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);


        initView();


    }

    private void initView() {
        recycler_fav = (RecyclerView) findViewById(R.id.recycler_fav);
        recycler_fav.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        GankApis apiService = new RetrofitUtils().getApiService("http://gank.io/api/", GankApis.class);
        Flowable<FavBean> flowable = apiService.getFav();
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<FavBean>() {
            @Override
            public void onNext(FavBean favBean) {

                List<String> images = new ArrayList<String>();
                List<FavBean.ResultsBean> results = favBean.getResults();
                for (int i=0;i<results.size();i++){
                    String url = results.get(i).getUrl();
                    images.add(url);
                }

                FavAdapter adapter = new FavAdapter(images,FavoriteActivity.this);
                recycler_fav.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


}

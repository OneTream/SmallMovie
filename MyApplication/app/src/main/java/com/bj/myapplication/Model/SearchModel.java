package com.bj.myapplication.Model;

import com.bj.myapplication.Bean.SearchBean;
import com.bj.myapplication.net.Api;
import com.bj.myapplication.net.ApiService;
import com.bj.myapplication.net.OnNetLister;
import com.bj.myapplication.net.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by 郑文杰 on 2017/12/13.
 */

public class SearchModel {
    public void getData(final OnNetLister onNetLister, String keyword, int pnum){
        ApiService apiService = RetrofitUtils.getInstance().getApiService(Api.HOST, ApiService.class);
        Flowable<SearchBean> searchBeanFlowable = apiService.getSearchData(keyword, pnum);
        searchBeanFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<SearchBean>() {
                    @Override
                    public void onNext(SearchBean searchBean) {
                        if (onNetLister!=null){
                            onNetLister.OnSuccess(searchBean);
                        }
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

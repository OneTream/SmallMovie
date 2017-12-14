package com.bj.myapplication.Model;

import com.bj.myapplication.Bean.FindDataBean;
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

public class FindModel {
    public void getData(final OnNetLister onNetLister, String catalogId, int pnum){
        ApiService apiService = RetrofitUtils.getInstance().getApiService(Api.HOST, ApiService.class);
        Flowable<FindDataBean> findDataBeanFlowable = apiService.getFindData(catalogId, pnum);
        findDataBeanFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<FindDataBean>() {
                    @Override
                    public void onNext(FindDataBean findDataBean) {
                        if (onNetLister!=null){
                            onNetLister.OnSuccess(findDataBean);
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

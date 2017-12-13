package com.bj.myapplication.Model;


import com.bj.myapplication.Bean.VideoDetail;
import com.bj.myapplication.net.Api;
import com.bj.myapplication.net.ApiService;
import com.bj.myapplication.net.OnNetLister;
import com.bj.myapplication.net.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by 努力努力再努力 on 2017/11/23.
 */

public class Model_Video_Detail implements IModel_Video_Detail {

    @Override
    public void getVideoDetail(final OnNetLister listener, String dataId) {
        Flowable<VideoDetail> login = RetrofitUtils.getInstance().getApiService(Api.HOST, ApiService.class).getvideoDetail(dataId);
        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<VideoDetail>() {
                    @Override
                    public void onNext(VideoDetail videoDetail) {
                        if (listener != null) {
                            listener.OnSuccess(videoDetail);
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

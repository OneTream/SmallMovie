package com.bj.myapplication.Model;


import com.bj.myapplication.Bean.HomePage;
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

public class Model_Home_Page implements IModel_Home_Page {

    @Override
    public void getData(final OnNetLister listener) {
        Flowable<HomePage> login = RetrofitUtils.getInstance().getApiService(Api.HOST, ApiService.class).getHomePage();
        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<HomePage>() {
                    @Override
                    public void onNext(HomePage homePage) {
                        if (listener != null) {
                            listener.OnSuccess(homePage);
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

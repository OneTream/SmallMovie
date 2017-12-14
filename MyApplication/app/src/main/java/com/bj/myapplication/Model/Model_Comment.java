package com.bj.myapplication.Model;


import com.bj.myapplication.Bean.Comment;
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

public class Model_Comment implements IModel_Comment {

    @Override
    public void getData(final OnNetLister listener,String dataId) {
        Flowable<Comment> commentList = RetrofitUtils.getInstance().getApiService(Api.HOST, ApiService.class).getCommentList(dataId);
        commentList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<Comment>() {
                    @Override
                    public void onNext(Comment comment) {
                        if (listener != null) {
                            listener.OnSuccess(comment);
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

package com.bj.myapplication.net;


import com.bj.myapplication.Bean.FavBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;


/**
 * Created by codeest on 16/8/19.
 */

public interface GankApis {

    //http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1

    public  static  final String HOST1 = "http://gank.io/api/";

    /**
     * 福利列表
     */
    @GET("data/福利/20/1")
    Flowable<FavBean> getFav();

}

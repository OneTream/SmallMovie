package com.bj.myapplication.net;

import android.util.Log;

import com.bj.myapplication.Bean.FilmBean;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 郑文杰 on 2017/12/5.
 */

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;

    public RetrofitUtils() {
    }
    public static RetrofitUtils getInstance(){
        if (retrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if (retrofitUtils==null){
                    retrofitUtils=new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    private  Retrofit retrofit;
    private  synchronized Retrofit getRetrofit(String url){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("xxx", message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).connectTimeout(3000, TimeUnit.SECONDS).build();

        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(url)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

        }

        return retrofit;
    }

    public <T>T getApiService(String url,Class<T> cl){
        Retrofit retrofit = getRetrofit(url);
        return retrofit.create(cl);
    }


}

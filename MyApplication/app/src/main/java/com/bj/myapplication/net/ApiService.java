package com.bj.myapplication.net;

import com.bj.myapplication.Bean.HomePage;
import com.bj.myapplication.Bean.VideoDetail;

import org.w3c.dom.Comment;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 郑文杰 on 2017/12/12.
 */

public interface ApiService {
    //首页
    @GET("homePageApi/homePage.do")
    Flowable<HomePage> getHomePage();
    //影片详情
    @GET("videoDetailApi/videoDetail.do")
    Flowable<VideoDetail> getvideoDetail(@Query("mediaId") String dataId);
    //获取评论列表参数
    @GET("Commentary/getCommentList.do")
    Flowable<Comment>  getCommentList(@Query("mediaId") String dataId);
}

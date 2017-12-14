package com.bj.myapplication.net;

import com.bj.myapplication.Bean.Comment;
import com.bj.myapplication.Bean.FindDataBean;
import com.bj.myapplication.Bean.HomePage;
import com.bj.myapplication.Bean.SearchBean;
import com.bj.myapplication.Bean.VideoDetail;

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

    //发现界面
    @GET("columns/getVideoList.do")
    Flowable<FindDataBean> getFindData(@Query("catalogId") String catalogId, @Query("pnum") int pnum);

    //搜索界面
    //http://api.svipmovie.com/front/searchKeyWordApi/getVideoListByKeyWord.do?keyword=念天堂&pnum=1
    @GET("searchKeyWordApi/getVideoListByKeyWord.do")
    Flowable<SearchBean> getSearchData(@Query("keyword")String keyword, @Query("pnum")int pnum);

}

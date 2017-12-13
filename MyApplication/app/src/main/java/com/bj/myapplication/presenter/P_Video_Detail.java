package com.bj.myapplication.presenter;

import com.bj.myapplication.Bean.VideoDetail;
import com.bj.myapplication.Model.Model_Video_Detail;
import com.bj.myapplication.View.IView_Video_Detail;
import com.bj.myapplication.net.OnNetLister;

/**
 * Created by 努力努力再努力 on 2017/12/12.
 */

public class P_Video_Detail implements IP_Video_Detail,OnNetLister {
    IView_Video_Detail iView_video_detail;

    private final Model_Video_Detail modelData2;

    public P_Video_Detail(IView_Video_Detail iView_video_detail) {
        this.iView_video_detail = iView_video_detail;
        modelData2 = new Model_Video_Detail();
    }

    @Override
    public void relevance2(String dataId) {
        modelData2.getVideoDetail(this,dataId);
    }


    @Override
    public void OnSuccess(Object o) {
        VideoDetail videoDetail= (VideoDetail) o;
        iView_video_detail.getShow(videoDetail);
    }
}

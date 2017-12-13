package com.bj.myapplication.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bj.myapplication.Bean.VideoDetail;
import com.bj.myapplication.R;
import com.bj.myapplication.presenter.P_Video_Detail;
import com.hjm.bottomtabbar.BottomTabBar;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.widget.media.IjkVideoView;

public class Main2Activity extends AppCompatActivity implements IView_Video_Detail {


    private IjkVideoView mIjkPlayer;
    private BottomTabBar mMBottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        Intent intent = getIntent();
        String dataId = intent.getStringExtra("dataId");
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_APPEND);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("dataId", dataId);
        //与P层交互
        P_Video_Detail p_video_detail = new P_Video_Detail(this);
        p_video_detail.relevance2(dataId);
        mMBottomTabBar.init(getSupportFragmentManager())
                .setImgSize(40, 40)
                .setFontSize(14)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.YELLOW, Color.DKGRAY)
                .addTabItem("简介", R.mipmap.collection, fragment1.class)
                .addTabItem("评论", R.mipmap.special, fragment2.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });
    }


    @Override
    public void getShow(VideoDetail videoDetail) {
        String smoothURL = videoDetail.getRet().getSmoothURL();
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        AndroidMediaController controller = new AndroidMediaController(this, false);
        mIjkPlayer.setMediaController(controller);
        mIjkPlayer.setVideoURI(Uri.parse(smoothURL));
        mIjkPlayer.start();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIjkPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IjkMediaPlayer.native_profileEnd();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIjkPlayer.resume();
    }

    private void initView() {
        mIjkPlayer = (IjkVideoView) findViewById(R.id.ijkPlayer);
        mMBottomTabBar = (BottomTabBar) findViewById(R.id.mBottomTabBar);
    }
}

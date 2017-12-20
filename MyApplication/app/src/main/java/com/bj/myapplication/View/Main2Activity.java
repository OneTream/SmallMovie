package com.bj.myapplication.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.astuetz.PagerSlidingTabStrip;
import com.bj.myapplication.Bean.VideoDetail;
import com.bj.myapplication.R;
import com.bj.myapplication.presenter.P_Video_Detail;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.widget.media.IjkVideoView;

public class Main2Activity extends AppCompatActivity implements IView_Video_Detail {

    private IjkVideoView mIjkPlayer;
    private PagerSlidingTabStrip pst;
    private ViewPager vp;
    String[] snames = {"简介", "评论"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        Intent intent = getIntent();
        String dataId = intent.getStringExtra("dataId");
        Log.i("dataId,Main2Activity",dataId);
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_APPEND);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("dataId", dataId);
        edit.commit();
        //与P层交互
        P_Video_Detail p_video_detail = new P_Video_Detail(this);
        p_video_detail.relevance2(dataId);
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
        pst = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pst.setViewPager(vp);
    }

    //适配器
    class MyPagerAdapter extends FragmentPagerAdapter {


        public MyPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return snames[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new fragment1();
                    break;
                case 1:
                    fragment = new fragment2();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return snames.length;
        }
    }
}

package com.bj.myapplication.View;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bj.myapplication.R;
import com.hjm.bottomtabbar.BottomTabBar;


public class MainActivity extends AppCompatActivity {


    private BottomTabBar mBtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mBtb.init(getSupportFragmentManager())
                .setImgSize(50, 50)
                .setFontSize(8)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("精选", R.mipmap.found_select,R.mipmap.found,OneFragment.class)
                .addTabItem("专辑",R.mipmap.special_select, R.mipmap.special, TwoFragment.class)
                .addTabItem("发现", R.mipmap.fancy_select,R.mipmap.fancy, ThreeFragment.class)
                .addTabItem("我的", R.mipmap.my_select,R.mipmap.my, FourFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });
    }

    private void initView() {
        mBtb = (BottomTabBar) findViewById(R.id.bottom_tab_bar);
    }
}

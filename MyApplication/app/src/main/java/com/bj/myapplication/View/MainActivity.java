package com.bj.myapplication.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bj.myapplication.R;
import com.hjm.bottomtabbar.BottomTabBar;


public class MainActivity extends AppCompatActivity {


    private BottomTabBar mBtb;
    private TextView tv_shoucang;

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

        tv_shoucang = (TextView) findViewById(R.id.tv_shoucang);
        tv_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"收藏",Toast.LENGTH_SHORT).show();
            }
        });


        TextView tv_xiazai = (TextView) findViewById(R.id.tv_xiazai);
        tv_xiazai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"敬请期待",Toast.LENGTH_SHORT).show();
            }
        });


        TextView tv_fuli = (TextView) findViewById(R.id.tv_fuli);
        tv_fuli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,FavoriteActivity.class);
                startActivity(intent);

            }
        });


        TextView tv_fenxiang = (TextView) findViewById(R.id.tv_fenxiang);
        tv_fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"分享",Toast.LENGTH_SHORT).show();
            }
        });


        TextView tv_shezhi = (TextView) findViewById(R.id.tv_shezhi);
        tv_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SetActivity.class);
                startActivity(intent);
            }
        });






    }
}

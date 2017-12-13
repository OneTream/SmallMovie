package com.bj.myapplication.presenter;

import com.bj.myapplication.Bean.HomePage;
import com.bj.myapplication.Model.Model_Home_Page;
import com.bj.myapplication.View.IView_Home_Page;
import com.bj.myapplication.net.OnNetLister;

/**
 * Created by 努力努力再努力 on 2017/11/23.
 */

public class P_Home_Page implements IP_Home_Page,OnNetLister{
    IView_Home_Page iView_home_page;

    private final Model_Home_Page modelData;

    public P_Home_Page(IView_Home_Page iView_home_page) {
        this.iView_home_page = iView_home_page;
        modelData = new Model_Home_Page();
    }

    @Override
    public void relevance() {
        modelData.getData(this);
    }


    @Override
    public void OnSuccess(Object o) {
        HomePage homePage= (HomePage) o;
        iView_home_page.getShow(homePage);
    }
}

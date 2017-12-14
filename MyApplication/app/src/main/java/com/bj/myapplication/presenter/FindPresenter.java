package com.bj.myapplication.presenter;

import com.bj.myapplication.Bean.FindDataBean;
import com.bj.myapplication.Model.FindModel;
import com.bj.myapplication.View.FindView;
import com.bj.myapplication.net.OnNetLister;

/**
 * Created by 郑文杰 on 2017/12/13.
 */

public class FindPresenter implements OnNetLister {

    private FindView findView;
    private final FindModel findModel;

    public FindPresenter(FindView findView) {
        this.findView = findView;
        findModel = new FindModel();
    }
    public void relevance(String catalogId, int pnum){
        findModel.getData(this,catalogId,pnum);
    }

    @Override
    public void OnSuccess(Object o) {
        FindDataBean findDataBean= (FindDataBean) o;
        findView.getFindData(findDataBean);
    }
}

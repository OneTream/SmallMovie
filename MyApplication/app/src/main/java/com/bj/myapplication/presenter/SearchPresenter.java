package com.bj.myapplication.presenter;

import com.bj.myapplication.Bean.SearchBean;
import com.bj.myapplication.Model.SearchModel;
import com.bj.myapplication.View.SearchView;
import com.bj.myapplication.net.OnNetLister;

/**
 * Created by 郑文杰 on 2017/12/14.
 */

public class SearchPresenter implements OnNetLister {

    private SearchView searchView;
    private final SearchModel searchModel;

    public SearchPresenter(SearchView searchView) {
        this.searchView = searchView;
        searchModel = new SearchModel();
    }

    public void relevance(String keyword, int pnum) {
        searchModel.getData(this, keyword, pnum);
    }

    @Override
    public void OnSuccess(Object o) {
        SearchBean searchBean = (SearchBean) o;
        searchView.getSearchData(searchBean);
    }
}

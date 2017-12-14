package com.bj.myapplication.presenter;

import com.bj.myapplication.Bean.Comment;
import com.bj.myapplication.Model.Model_Comment;
import com.bj.myapplication.View.IView_Comment;
import com.bj.myapplication.net.OnNetLister;

/**
 * Created by 努力努力再努力 on 2017/12/12.
 */

public class P_Comment implements IP_Comment,OnNetLister {
    IView_Comment iView_comment;
    private final Model_Comment model_comment;

    public P_Comment(IView_Comment iView_comment) {
        this.iView_comment = iView_comment;
        model_comment = new Model_Comment();
    }

    @Override
    public void OnSuccess(Object o) {
        Comment comment= (Comment) o;
        iView_comment.getShow(comment);
    }

    @Override
    public void relevance3(String dataId) {
        model_comment.getData(this,dataId);
    }
}

package com.bj.myapplication.View;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.bj.myapplication.Adapter.FindDataAdapter;
import com.bj.myapplication.Bean.FindDataBean;
import com.bj.myapplication.R;
import com.bj.myapplication.presenter.FindPresenter;
import com.mcxtzhang.layoutmanager.swipecard.CardConfig;
import com.mcxtzhang.layoutmanager.swipecard.OverLayCardLayoutManager;
import com.mcxtzhang.layoutmanager.swipecard.RenRenCallback;

import java.util.List;

/**
 * Created by 努力努力再努力 on 2017/12/13.
 */

public class ThreeFragment extends Fragment implements FindView, View.OnClickListener {

    private RecyclerView rv;
    final String catalogId = "402834815584e463015584e539330016";
    private Button bt;
    private int page = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.threefragment, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        rv = view.findViewById(R.id.rv);
        bt = view.findViewById(R.id.bt);
        bt.setOnClickListener(this);
        FindPresenter findPresenter = new FindPresenter(this);
        findPresenter.relevance(catalogId, 1);
        return view;
    }

    @Override
    public void getFindData(FindDataBean findDataBean) {
        final List<FindDataBean.RetBean.ListBean> list = findDataBean.ret.list;
        rv.setLayoutManager(new OverLayCardLayoutManager());
        CardConfig.initConfig(getContext());
        FindDataAdapter adapter = new FindDataAdapter(list, getContext());
        ItemTouchHelper.Callback callback = new RenRenCallback(rv, adapter, list);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rv);
        rv.setAdapter(adapter);
        adapter.setOnClickItem(new FindDataAdapter.OnClickItem() {
            @Override
            public void clickImg(int position) {
                Intent intent = new Intent(getContext(), Main2Activity.class);
                String dataId = list.get(position).dataId;
                intent.putExtra("dataId",dataId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
                page++;
                FindPresenter findPresenter = new FindPresenter(this);
                findPresenter.relevance(catalogId, page);
                break;
        }
    }
}

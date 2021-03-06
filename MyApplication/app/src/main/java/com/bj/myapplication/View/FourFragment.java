package com.bj.myapplication.View;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bj.myapplication.R;

/**
 * Created by 努力努力再努力 on 2017/12/13.
 */

public class FourFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fourfragment, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        initView();
        return view;
    }

    private void initView() {

        ImageView img_set = view.findViewById(R.id.img_set);

        img_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SetActivity.class);
                startActivity(intent);
            }
        });


        LinearLayout layout_lishi = view.findViewById(R.id.layout_lishi);
        layout_lishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Intent intent = new Intent(getActivity(),LiShiActivity.class);
                        startActivity(intent);

            }
        });


        LinearLayout layout_huancun = view.findViewById(R.id.layout_huancun);
        layout_huancun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"敬请期待",Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout layout_shoucang = view.findViewById(R.id.layout_shoucang);
        layout_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),ShouCangActivity.class);
                startActivity(intent);

            }
        });

        LinearLayout layout_zhuti = view.findViewById(R.id.layout_zhuti);
        layout_zhuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"敬请期待",Toast.LENGTH_SHORT).show();

            }
        });


    }


}

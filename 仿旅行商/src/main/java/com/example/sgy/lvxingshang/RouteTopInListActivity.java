package com.example.sgy.lvxingshang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import adapter.RouteTopListAdapter;

/**
 * Created by sgy on 2017/2/22.
 * 国内游-->列表界面
 */

public class RouteTopInListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewList;
    // 数据--> 路线
    private List<Integer> mRouteNormalImages;
    private List<String> mRouteNormalInstractions;
    private List<Integer> mRouteNormalPrices;
    private ImageView ivBackToRote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_top_in_list);
        init();
    }

    private void init() {
        initView();
        initData();
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerViewList.setLayoutManager(manager);
        RouteTopListAdapter adapter = new RouteTopListAdapter(mRouteNormalImages,
                mRouteNormalInstractions, mRouteNormalPrices);
        recyclerViewList.setAdapter(adapter);
    }

    /**
     * 初始化View
     */
    private void initView() {
        recyclerViewList = (RecyclerView) findViewById(R.id.rv_route_top_list);
        ivBackToRote = (ImageView) findViewById(R.id.iv_back);
        ivBackToRote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mRouteNormalImages = new ArrayList<>();
        mRouteNormalInstractions = new ArrayList<>();
        mRouteNormalPrices = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mRouteNormalImages.add(R.drawable.ic_launcher);
            mRouteNormalInstractions.add("景点名称: " + i);
            mRouteNormalPrices.add(i);
        }
    }
}

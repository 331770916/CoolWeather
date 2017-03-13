package com.android.coolweather.mvp.ui.history;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.coolweather.R;
import com.android.coolweather.core.BaseFragment;
import com.android.coolweather.mvp.modle.HistoryEntity;

import java.util.ArrayList;

/**
 * Created by zhangwenbo on 2017/3/10.
 */

public class HistoryFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    private HistoryAdapter mHistoryAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        RecyclerView recyclerView = (RecyclerView ) root.findViewById(R.id.fg_historyRecylerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.historySwipeRefresh);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mHistoryAdapter = new HistoryAdapter(getActivity());
        recyclerView.setAdapter(mHistoryAdapter);
    }

    @Override
    protected void initData() {
        super.initData();

        String imgeUrl = "http://img.qt.baidu.com/hiapk_pic/201703/10/58c25a1d998a3.jpg";

        ArrayList<HistoryEntity> historyEntities = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            HistoryEntity historyEntity = new HistoryEntity();
            historyEntity.setPicUrl(imgeUrl);
            historyEntity.setName("BookName" + i);
            historyEntity.setAuthor("Mr.Zhang");
            historyEntity.setTime("2017-03" + "-" + i);
            historyEntities.add(historyEntity);
        }
        mHistoryAdapter.setDatas(historyEntities);

    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mSwipeRefreshLayout.setRefreshing(false);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_history;
    }
}

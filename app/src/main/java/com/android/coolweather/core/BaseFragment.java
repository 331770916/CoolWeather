package com.android.coolweather.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangwenbo on 2017/3/9.
 * Fragment基类
 */

public abstract class BaseFragment extends Fragment {
    protected Context   mContext;
    protected View      mRoot;
    protected Bundle    mBundle;
    protected LayoutInflater mInflater;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments();
        initBundle(mBundle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRoot != null) {
            ViewGroup parent = (ViewGroup) mRoot.getParent();
            if (parent != null)
                parent.removeView(mRoot);
        } else {
            mRoot = inflater.inflate(getLayoutId(), container, false);
            mInflater = inflater;

            onBindViewBefore(mRoot);
            if (savedInstanceState != null)
                onRestartInstance(savedInstanceState);
            // Init
            initWidget(mRoot);
            initData();
        }

        return mRoot;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBundle = null;
    }

    protected abstract int getLayoutId();

    protected void initBundle(Bundle bundle) {

    }

    protected void initWidget(View root) {

    }

    protected void initData() {

    }

    protected void onBindViewBefore(View root) {
        // ...
    }

    protected void onRestartInstance(Bundle bundle) {

    }
}

package cn.edu.hpu.yuan.yuannews.main.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.hpu.yuan.yuannews.main.app.ApplicationComponent;
import cn.edu.hpu.yuan.yuannews.main.data.remote.NewsAPIService;

/**
 * Created by yuan on 16-5-9.
 * Fragment 基类
 * 1.为广大的fragment提供支持
 * 2.ApplicationComponent
 */
public abstract class BaseFragment extends Fragment{


    protected ApplicationComponent applicationComponent;
    protected NewsAPIService newsAPIService;
    protected Activity activity;

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public NewsAPIService getNewsAPIService() {
        return newsAPIService;
    }

    public void setNewsAPIService(NewsAPIService newsAPIService) {
        this.newsAPIService = newsAPIService;
    }

    protected boolean isVisible;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
        initComponent();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            isVisible=true;
        }else{
            isVisible=false;
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 初始化变量操作（注入器注入）
     */
    protected abstract void initComponent();

}

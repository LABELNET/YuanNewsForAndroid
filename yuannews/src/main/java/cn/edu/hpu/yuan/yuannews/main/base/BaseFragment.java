package cn.edu.hpu.yuan.yuannews.main.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import com.pnikosis.materialishprogress.ProgressWheel;
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
    private ProgressWheel wheel;


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


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initProgress();
    }

    private void initProgress() {
        wheel = new ProgressWheel(getContext());
        wheel.setBarColor(Color.GREEN);
        wheel.setEnabled(true);
        wheel.setBarColor(Color.BLUE);
        wheel.setBackgroundColor(Color.GRAY);
    }

    protected void showProgress(){
        wheel.setVisibility(View.VISIBLE);
    }

    protected void hideProgress(){
        wheel.setVisibility(View.GONE);
    }

    /**
     * 初始化变量操作（注入器注入）
     */
    protected abstract void initComponent();

}

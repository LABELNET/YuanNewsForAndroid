package cn.edu.hpu.yuan.yuannews.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

/**
 * Created by yuan on 16-5-9.
 * Fragment 基类
 * 1.为广大的fragment提供支持
 * 2.ApplicationComponent
 */
public abstract class BaseFragment extends Fragment{


    @Inject
    protected BaseApplication baseApplication;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFragmentComponent(baseApplication.getApplicationComponent());
    }

    protected abstract void setFragmentComponent(ApplicationComponent applicationComponent);
}

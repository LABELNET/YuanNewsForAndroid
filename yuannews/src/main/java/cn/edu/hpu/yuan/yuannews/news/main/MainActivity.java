package cn.edu.hpu.yuan.yuannews.news.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import cn.edu.hpu.yuan.yuannews.main.base.BaseActivity;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;

/**
 * Created by yuan on 16-5-10.
 * 1.主界面
 * 2.功能：初始化界面，包括分类和来源
 */
public class MainActivity extends BaseActivity{
    @Override
    protected void initView(Bundle savedInstanceState, Toolbar toolbar) {

    }

    @Override
    protected BaseFragment initFragment() {
        return new MainFragment();
    }

    @Override
    protected void setComponent() {

    }
}

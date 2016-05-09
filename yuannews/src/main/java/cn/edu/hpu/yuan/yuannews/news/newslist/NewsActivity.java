package cn.edu.hpu.yuan.yuannews.news.newslist;

import android.content.Context;
import android.os.Bundle;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.main.base.BaseActivity;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;


/**
 * Created by yuan on 16-5-9.
 * 新闻列表Activity
 */
public class NewsActivity extends BaseActivity {


    @Inject
    protected Context context;

    @Inject
    protected NewsFragment fragment;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("新闻推荐");
    }

    @Override
    protected BaseFragment initFragment() {
        return new NewsFragment();
    }

    @Override
    protected void setComponent() {

         DaggerNewsComponent.builder()
                 .applicationComponent(getApplicationComponent())
                 .newsModule(new NewsModule())
                 .build()
                 .injectNewsActivity(this);
    }
}

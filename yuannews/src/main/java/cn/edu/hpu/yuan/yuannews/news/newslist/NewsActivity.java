package cn.edu.hpu.yuan.yuannews.news.newslist;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.main.base.BaseActivity;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;


/**
 * Created by yuan on 16-5-9.
 * 新闻列表Activity
 */
public class NewsActivity extends BaseActivity {



    @Inject
    protected NewsFragment fragment;

    @Override
    protected void initView(Bundle savedInstanceState,Toolbar toolbar) {
     toolbar.setVisibility(View.GONE);
    }

    @Override
    protected BaseFragment initFragment() {
        return new NewsFragment();
    }

    @Override
    protected void setComponent() {

         DaggerNewsComponent.builder()
                 .newsModule(new NewsModule())
                 .build()
                 .injectNewsActivity(this);
    }
}

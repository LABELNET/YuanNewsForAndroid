package cn.edu.hpu.yuan.yuannews.news.newsdetail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.base.NormalBackActivity;

/**
 * Created by yuan on 16-5-11.
 * 新闻详情页Activity
 */
public class NewsDetailActivity extends NormalBackActivity{



    @Inject
    protected NewsDetailFragment newsDetailFragment;

    @Override
    protected void initView(Bundle savedInstanceState, Toolbar toolbar) {

    }

    @Override
    protected BaseFragment initFragment() {
        return newsDetailFragment;
    }

    @Override
    protected void setComponent() {
        int nid=getIntent().getIntExtra("nid",400);
        DaggerNewsDetailComponent
                .builder()
                .newsDetailModule(new NewsDetailModule(nid))
                .build()
                .injectNewsDetailActivity(this);

    }


}

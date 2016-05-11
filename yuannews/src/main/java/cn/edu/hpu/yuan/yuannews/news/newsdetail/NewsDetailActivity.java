package cn.edu.hpu.yuan.yuannews.news.newsdetail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuancore.util.LogUtil;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.base.NormalBackActivity;

/**
 * Created by yuan on 16-5-11.
 * 新闻详情页Activity
 */
public class NewsDetailActivity extends NormalBackActivity{



    private static final String NEWSDETAIL_FRAGMENT_NID_KEY="news_detail_nid_key";

    @Inject
    protected NewsDetailFragment newsDetailFragment;

    @Override
    protected void initView(Bundle savedInstanceState, Toolbar toolbar) {
    }

    @Override
    protected BaseFragment initFragment() {
        Bundle bundle=new Bundle();
        bundle.putInt(NEWSDETAIL_FRAGMENT_NID_KEY,getIntent().getIntExtra("nid",400));
        newsDetailFragment.setArguments(bundle);
        return newsDetailFragment;
    }

    @Override
    protected void setComponent() {
        DaggerNewsDetailComponent
                .builder()
                .newsDetailModule(new NewsDetailModule())
                .build()
                .injectNewsDetailActivity(this);
    }


}

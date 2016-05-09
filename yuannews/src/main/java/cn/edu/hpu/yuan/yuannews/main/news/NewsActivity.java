package cn.edu.hpu.yuan.yuannews.main.news;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.main.BaseActivity;
import cn.edu.hpu.yuan.yuannews.main.BaseFragment;

/**
 * Created by yuan on 16-5-9.
 * 新闻列表Activity
 */
public class NewsActivity extends BaseActivity{


    @Inject
    protected NewsFragment fragment;

    @Override
    protected BaseFragment initFragment() {
        return fragment;
    }

    @Override
    protected void setToolBarTitle() {
        setTitle("新闻推荐");
    }

    @Override
    protected void setComponent() {
         DaggerNewsComponent.builder()
                 .newsModule(new NewsModule())
                 .build()
                 .injectNewsActivity(this);
    }
}

package cn.edu.hpu.yuan.yuannews.news.newsdetail;

import dagger.Component;

/**
 * Created by yuan on 16-5-11.
 */
@Component(modules = NewsDetailModule.class)
public interface NewsDetailComponent {

    void injectNewsDetailActivity(NewsDetailActivity newsDetailActivity);

    void injectNewsDetailFragment(NewsDetailFragment newsDetailFragment);

}

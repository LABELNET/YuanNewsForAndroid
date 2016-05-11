package cn.edu.hpu.yuan.yuannews.news.newsdetail;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-11.
 * 提供类：activity和fragment
 */

@Module
public class NewsDetailModule {



    private NewsDetailContancts.NewsDetailView newsDetailView;


    public NewsDetailModule() {

    }

    public NewsDetailModule(NewsDetailContancts.NewsDetailView newsDetailView) {
        this.newsDetailView = newsDetailView;
    }

    @Provides
    NewsDetailFragment provideNewsDetaulFragment(){
        return NewsDetailFragment.getInstance();
    }


    @Provides
    NewsDetailContancts.NewsDetailPresenter provideNewsDetailPresenter(){
        return new NewsDetailPresenter(newsDetailView);
    }

}

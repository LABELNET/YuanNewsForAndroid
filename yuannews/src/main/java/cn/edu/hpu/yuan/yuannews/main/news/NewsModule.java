package cn.edu.hpu.yuan.yuannews.main.news;

import cn.edu.hpu.yuan.yuannews.main.data.remote.NewsAPIService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-9.
 * 新闻modlue类
 */
@Module
public class NewsModule {


    private NewsContract.View newsView;
    private NewsAPIService newsAPIService;

    public NewsModule(NewsContract.View newsView, NewsAPIService newsAPIService) {
        this.newsView = newsView;
        this.newsAPIService = newsAPIService;
    }

    @Provides
    NewsFragment provideNewsFragment(){
        return new NewsFragment();
    }

    @Provides
    NewsContract.View provideNewsView(){
        return newsView;
    }

    @Provides
    MewsPresenter provideNewsPresenter(){
        return new MewsPresenter(newsAPIService,provideNewsView());
    }

}

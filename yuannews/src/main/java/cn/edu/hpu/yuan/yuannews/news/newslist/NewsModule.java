package cn.edu.hpu.yuan.yuannews.news.newslist;

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


    public NewsModule(){}


    public NewsModule(NewsContract.View newsView) {
        this.newsView = newsView;
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
    NewsContract.Presenter provideNewsPresenter(){
        return new MewsPresenter(newsView);
    }

}

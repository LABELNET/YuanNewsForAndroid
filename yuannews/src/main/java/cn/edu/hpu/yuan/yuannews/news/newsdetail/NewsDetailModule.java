package cn.edu.hpu.yuan.yuannews.news.newsdetail;

import android.content.Context;

import cn.edu.hpu.yuan.yuannews.news.newsdetail.adapter.NewsDetailGridViewAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-11.
 * 提供类：activity和fragment
 */

@Module
public class NewsDetailModule {



    private NewsDetailContancts.NewsDetailView newsDetailView;
    private Context context;


    public NewsDetailModule() {

    }

    public NewsDetailModule(NewsDetailContancts.NewsDetailView newsDetailView,Context context) {
        this.newsDetailView = newsDetailView;
        this.context=context;
    }

    @Provides
    NewsDetailFragment provideNewsDetaulFragment(){
        return NewsDetailFragment.getInstance();
    }


    @Provides
    NewsDetailContancts.NewsDetailPresenter provideNewsDetailPresenter(){
        return new NewsDetailPresenter(newsDetailView);
    }

    @Provides
    NewsDetailGridViewAdapter provideNewsDetailGridViewAdapter(){
        return new NewsDetailGridViewAdapter(context);
    }

}

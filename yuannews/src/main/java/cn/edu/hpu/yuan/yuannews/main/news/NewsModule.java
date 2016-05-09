package cn.edu.hpu.yuan.yuannews.main.news;

import android.app.AlertDialog;
import android.content.Context;

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
    private Context context;


    public NewsModule(){}


    public NewsModule(NewsContract.View newsView, NewsAPIService newsAPIService,Context context) {
        this.newsView = newsView;
        this.newsAPIService = newsAPIService;
        this.context=context;
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
        return new MewsPresenter(newsAPIService,newsView);
    }

    @Provides
    AlertDialog.Builder provideAlertDialogBuilder(){
        return new AlertDialog.Builder(context);
    }

}

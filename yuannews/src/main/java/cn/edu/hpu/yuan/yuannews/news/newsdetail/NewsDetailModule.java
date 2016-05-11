package cn.edu.hpu.yuan.yuannews.news.newsdetail;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-11.
 * 提供类：activity和fragment
 */

@Module
public class NewsDetailModule {


    private int nid;

    public NewsDetailModule(int nid) {
        this.nid=nid;
    }

    @Provides
    NewsDetailFragment provideNewsDetaulFragment(){
        return NewsDetailFragment.getInstance(nid);
    }







}

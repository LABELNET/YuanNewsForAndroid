package cn.edu.hpu.yuan.yuannews.main.news;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-9.
 * 新闻modlue类
 */
@Module
public class NewsModule {

    @Provides
    NewsFragment provideNewsFragment(){
        return new NewsFragment();
    }


}

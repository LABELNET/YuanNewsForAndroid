package cn.edu.hpu.yuan.yuannews.news.newslist;

import cn.edu.hpu.yuan.yuannews.main.app.ApplicationComponent;
import cn.edu.hpu.yuan.yuannews.main.scope.PerActivity;
import dagger.Component;

/**
 * Created by yuan on 16-5-9.
 * 新闻列表注入类
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = NewsModule.class)
public interface NewsComponent {

    void injectNewsActivity(NewsActivity newsActivity);

    void injectNewsFragment(NewsFragment newsFragment);

}

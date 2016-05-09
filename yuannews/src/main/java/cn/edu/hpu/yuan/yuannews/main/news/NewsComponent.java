package cn.edu.hpu.yuan.yuannews.main.news;


import dagger.Component;

/**
 * Created by yuan on 16-5-9.
 * 新闻列表注入类
 */
@Component(modules = NewsModule.class)
public interface NewsComponent {

    void injectNewsActivity(NewsActivity newsActivity);

}

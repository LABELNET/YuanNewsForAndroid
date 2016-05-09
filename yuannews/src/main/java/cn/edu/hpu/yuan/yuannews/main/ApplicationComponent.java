package cn.edu.hpu.yuan.yuannews.main;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yuan on 16-5-9.
 * 指定目标类
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    //注入到Fragment
    void injectBaseFragment(BaseFragment baseFragment);

    //注入到Application中
    void injectBaseApplication(BaseApplication baseApplication);

}

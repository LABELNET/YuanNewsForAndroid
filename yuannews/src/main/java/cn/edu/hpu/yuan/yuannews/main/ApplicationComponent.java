package cn.edu.hpu.yuan.yuannews.main;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yuan on 16-5-9.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context getContext();

    //对BaseApplication进行注入
    void injectBaseApplication(BaseApplication baseApplication);

}

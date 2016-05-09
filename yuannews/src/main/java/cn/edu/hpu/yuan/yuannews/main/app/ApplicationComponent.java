package cn.edu.hpu.yuan.yuannews.main.app;

import android.content.Context;
import javax.inject.Singleton;
import cn.edu.hpu.yuan.yuannews.main.data.NewsAPIModule;
import dagger.Component;

/**
 * Created by yuan on 16-5-9.
 */
@Singleton
@Component(modules = {ApplicationModule.class,NewsAPIModule.class})
public interface ApplicationComponent {

    //Application的Context
    Context getContext();

    //对BaseApplication进行注入
    void injectBaseApplication(BaseApplication baseApplication);

}

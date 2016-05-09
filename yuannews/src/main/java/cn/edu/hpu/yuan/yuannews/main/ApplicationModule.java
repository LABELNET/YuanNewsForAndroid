package cn.edu.hpu.yuan.yuannews.main;


import javax.inject.Singleton;

import cn.edu.hpu.yuan.yuancore.util.CrashHandler;
import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-9.
 * 为BaseApplication提供实例
 * 1.提供Context
 */

@Module
public class ApplicationModule {

    private final BaseApplication application;

    public ApplicationModule(BaseApplication application) {
        this.application = application;
    }


    @Provides
    BaseApplication provideBaseApplication(){
        return application;
    }

    @Provides
    @Singleton
    CrashHandler provideCrashHandler(){
        return CrashHandler.getInstance();
    }


}

package cn.edu.hpu.yuan.yuannews.main.app;



import android.content.Context;

import javax.inject.Singleton;

import cn.edu.hpu.yuan.yuancore.util.CrashHandler;
import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-9.
 * 为BaseApplication提供实例
 * 1.提供Application的Context
 * 2.提供CrashHandler类
 * 3.等等 数据API等
 */

@Module
public class ApplicationModule {

    protected Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides @Singleton
    public Context provideContext(){
        return context;
    }

    @Provides @Singleton
    public CrashHandler provideCrashHandler(){
        return CrashHandler.getInstance();
    }


    //等等

}

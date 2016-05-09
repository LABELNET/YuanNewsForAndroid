package cn.edu.hpu.yuan.yuannews.main;



import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-9.
 * 为BaseApplication提供实例
 * 1.提供Application
 * 2.提供数据API请求
 * 3.等
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


}

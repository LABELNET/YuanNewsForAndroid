package cn.edu.hpu.yuan.yuannews.main.data;

import cn.edu.hpu.yuan.yuannews.main.ApplicationComponent;
import cn.edu.hpu.yuan.yuannews.main.BaseApplication;
import dagger.Component;

/**
 * Created by yuan on 16-5-9.
 * APIComponent
 *
 */
@NewsAPI
@Component(dependencies = ApplicationComponent.class,modules = NewsAPI.class)
public interface NewsAPIComponent {

    //在Application中进行初始化
   void injectBaseApplication(BaseApplication baseApplication);

}

package cn.edu.hpu.yuan.yuannews.news.main;

import cn.edu.hpu.yuan.yuannews.main.scope.PerActivity;
import dagger.Component;

/**
 * Created by yuan on 16-5-10.
 * 注入器，两个地方：Activity和Fragment注入实例
 */
@PerActivity
@Component(modules = MainModule.class)
public interface MainComponent {

    void injectMainActivity(MainActivity mainActivity);

    void injectMainFragment(MainFragment mainFragment);

}

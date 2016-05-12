package cn.edu.hpu.yuan.yuannews.user.center;

import dagger.Component;

/**
 * Created by yuan on 16-5-12.
 */
@Component(modules = CenterModule.class)
public interface CenterComponent {

    void injectCenterActivity(CenterActivity centerActivity);

    void injectCenterFragment(CenterFragment centerFragment);

}

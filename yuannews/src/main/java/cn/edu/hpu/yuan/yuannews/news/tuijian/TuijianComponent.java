package cn.edu.hpu.yuan.yuannews.news.tuijian;

import dagger.Component;

/**
 * Created by yuan on 16-5-13.
 */
@Component(modules = TuijianModule.class)
public interface TuijianComponent {

    void injectTuijianActivity(TuijianActivity tuijianActivity);

    void injectTuijianFragment(TuijianFragment tuijianFragment);

}

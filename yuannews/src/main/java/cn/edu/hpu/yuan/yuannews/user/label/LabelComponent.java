package cn.edu.hpu.yuan.yuannews.user.label;

import dagger.Component;

/**
 * Created by yuan on 16-5-12.
 */
@Component(modules = LabelModule.class)
public interface LabelComponent {

    void injectLabelActivity(LabelActivity labelActivity);

    void injectLabelFragment(LabelFragment labelFragment);

}

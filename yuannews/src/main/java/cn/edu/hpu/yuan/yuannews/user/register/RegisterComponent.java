package cn.edu.hpu.yuan.yuannews.user.register;

import dagger.Component;

/**
 * Created by yuan on 16-5-12.
 */

@Component(modules = RegisterModule.class)
public interface RegisterComponent {

    void injectRegisterActivity(RegisterActivity registerActivity);

    void injectRegisterFragment(RegisterFragment registerFragment);

}

package cn.edu.hpu.yuan.yuannews.user.login;

import dagger.Component;

/**
 * Created by yuan on 16-5-12.
 */
@Component(modules = LoginModule.class)
public interface LoginComponent {

    void injectLoginActivity(LoginActivity loginActivity);

    void injectLoginFragment(LoginFragment loginFragment);

}

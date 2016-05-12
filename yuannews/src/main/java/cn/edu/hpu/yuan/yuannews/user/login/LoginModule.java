package cn.edu.hpu.yuan.yuannews.user.login;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-12.
 */
@Module
public class LoginModule {


    protected LoginContract.LoginContractView loginContractView;

    public LoginModule() {
    }

    public LoginModule(LoginContract.LoginContractView loginContractView) {
        this.loginContractView = loginContractView;
    }

    @Provides
    LoginFragment provideLoginFragment(){
        return new LoginFragment();
    }

    @Provides
    LoginContract.LoginContractPresenter provideLoginContractPresenter(){
        return new LoginPresenter(loginContractView);
    }

}

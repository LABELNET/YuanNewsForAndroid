package cn.edu.hpu.yuan.yuannews.user.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.base.NormalBackActivity;

/**
 * Created by yuan on 16-5-12.
 */
public class LoginActivity extends NormalBackActivity{


    @Inject
    protected LoginFragment loginFragment;


    @Override
    protected void initView(Bundle savedInstanceState, Toolbar toolbar) {
        toolbar.setTitle("用户登陆");
    }

    @Override
    protected BaseFragment initFragment() {
        return loginFragment;
    }

    @Override
    protected void setComponent() {
        DaggerLoginComponent
                .builder()
                .loginModule(new LoginModule())
                .build()
                .injectLoginActivity(this);
    }
}

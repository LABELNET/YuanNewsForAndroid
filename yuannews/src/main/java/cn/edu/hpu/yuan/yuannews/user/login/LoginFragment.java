package cn.edu.hpu.yuan.yuannews.user.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;

/**
 * Created by yuan on 16-5-12.
 */
public class LoginFragment extends NorbalBackFragment implements LoginContract.LoginContractView{


    @Inject
    protected LoginContract.LoginContractPresenter loginContractPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {



    }

    @Override
    protected void initComponent() {
        DaggerLoginComponent
                .builder()
                .loginModule(new LoginModule(this))
                .build()
                .injectLoginFragment(this);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void success() {

    }

    @Override
    public void error() {

    }

    @Override
    public void dmissDialog() {

    }
}
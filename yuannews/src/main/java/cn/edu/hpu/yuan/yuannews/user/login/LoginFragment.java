package cn.edu.hpu.yuan.yuannews.user.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuancore.util.LogUtil;
import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.LoginFragmentBinding;
import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;

/**
 * Created by yuan on 16-5-12.
 */
public class LoginFragment extends NorbalBackFragment implements LoginContract.LoginContractView{


    @Inject
    protected LoginContract.LoginContractPresenter loginContractPresenter;

    //初始化binding
    protected LoginFragmentBinding binding;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.login_fragment,container,false);
        return binding.getRoot();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        binding.textnum.setErrorEnabled(true);
        binding.textpass.setErrorEnabled(true);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登陆操作
                int result=login();
                LogUtil.v("登陆  登陆 "+result);

            }
        });


    }
    //登陆操作
    private int login() {

        String num = binding.textnum.getEditText().getText().toString().trim();
        String pass=binding.textpass.getEditText().getText().toString().trim();

        if(num.length()==0){
            binding.textnum.setError("请输入账户");
        }

        if(pass.length()==0){
            binding.textpass.setError("请输入密码");
        }
        return loginContractPresenter.postUserLogin(num,pass);
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
        showMsg("登陆中...");
    }

    @Override
    public void success() {
        showMsg("登陆成功，正在跳转");
    }

    @Override
    public void error() {
        showMsg("登陆失败");
    }

    @Override
    public void dmissDialog() {

    }

    private void showMsg(String msg){
        Snackbar.make(binding.loginView,msg,Snackbar.LENGTH_SHORT).show();
    }
}

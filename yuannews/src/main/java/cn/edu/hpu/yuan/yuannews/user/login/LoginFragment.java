package cn.edu.hpu.yuan.yuannews.user.login;

import android.content.Intent;
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
import cn.edu.hpu.yuan.yuannews.user.register.RegisterActivity;

/**
 * Created by yuan on 16-5-12.
 */
public class LoginFragment extends NorbalBackFragment implements LoginContract.LoginContractView{


    @Inject
    protected LoginContract.LoginContractPresenter loginContractPresenter;

    private final int LOGIN_SUCCESS=2016;
    private final String BASE_BORDERCAST_ACTION="base_bordercast_action";

    //初始化binding
    protected LoginFragmentBinding binding;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        binding= LoginFragmentBinding.bind(view);
        return binding.getRoot();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        binding.textnum.setErrorEnabled(true);
        binding.textpass.setErrorEnabled(true);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        binding.btnRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到注册
                startActivity(new Intent(getActivity(), RegisterActivity.class));
            }
        });

    }
    //登陆操作
    private void login() {

        String num = binding.textnum.getEditText().getText().toString().trim();
        String pass=binding.textpass.getEditText().getText().toString().trim();

        if(num.length()==0){
            binding.textnum.setError("请输入账户");
            return;
        }

        if(pass.length()==0){
            binding.textpass.setError("请输入密码");
            return;
        }
        loginContractPresenter.postUserLogin(num,pass);
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
        Intent intent=new Intent();
        intent.setAction(BASE_BORDERCAST_ACTION);
        activity.sendBroadcast(intent);
        //主界面
        getActivity().setResult(LOGIN_SUCCESS);
        getActivity().onBackPressed();
        getActivity().finish();
    }

    @Override
    public void error(String msg) {
        showMsg(msg);
    }

    @Override
    public void dmissDialog() {

    }

    private void showMsg(String msg){
        Snackbar.make(binding.loginView,msg,Snackbar.LENGTH_SHORT).show();
    }
}

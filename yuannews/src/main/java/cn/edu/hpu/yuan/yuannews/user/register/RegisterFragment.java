package cn.edu.hpu.yuan.yuannews.user.register;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.RegisterFragmentBinding;
import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;
import cn.edu.hpu.yuan.yuannews.user.login.LoginActivity;

/**
 * Created by yuan on 16-5-12.
 */
public class RegisterFragment extends NorbalBackFragment implements RegisterContancts.RegisterCOntanctsView{


    @Inject
    protected RegisterContancts.RegisterContanctsPresenter registerContanctsPresenter;

    private RegisterFragmentBinding binding;

    @Override
    protected void initComponent() {
         DaggerRegisterComponent.builder()
                 .registerModule(new RegisterModule(this))
                 .build()
                 .injectRegisterFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.register_fragment,container,false);
        return binding.getRoot();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        binding.btnLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),LoginActivity.class));
                getActivity().finish();
            }
        });

    }

    private void register() {

        binding.textnick.setErrorEnabled(true);
        binding.textnum.setErrorEnabled(true);
        binding.textpass.setErrorEnabled(true);

        String nick=binding.editUsernick.getText().toString().trim();
        if(nick.length()==0){
            binding.textnick.setError("请输入昵称");
            return;
        }
        String num=binding.editUserNum.getText().toString().trim();

        if(num.length()==11){
            binding.textnum.setError("手机号长度不够");
            return;
        }

        String pass=binding.editUserPass.getText().toString().trim();
        if(pass.length()==0){
            binding.textpass.setError("请输入密码");
            return;
        }
        registerContanctsPresenter.postUserRegister(nick,num,pass);
    }

    @Override
    public void showDialog() {
        showMsg("登陆中...");
    }

    @Override
    public void showError(String msg) {
        showMsg(msg);
    }

    @Override
    public void showSuccess() {
        //跳转等
        Toast.makeText(getContext(),"注册成功",Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    private void showMsg(String msg){
        Snackbar.make(binding.loginView,msg,Snackbar.LENGTH_SHORT).show();
    }


}

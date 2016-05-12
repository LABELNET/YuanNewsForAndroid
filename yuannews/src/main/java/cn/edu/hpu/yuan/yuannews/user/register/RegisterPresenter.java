package cn.edu.hpu.yuan.yuannews.user.register;

import cn.edu.hpu.yuan.yuannews.user.register.RegisterContancts.RegisterContanctsPresenter;

/**
 * Created by yuan on 16-5-12.
 */
public class RegisterPresenter implements RegisterContanctsPresenter{

    private RegisterContancts.RegisterCOntanctsView registerCOntanctsView;

    public RegisterPresenter(RegisterContancts.RegisterCOntanctsView registerCOntanctsView) {
        this.registerCOntanctsView = registerCOntanctsView;
    }

    @Override
    public void postUserRegister(String nick, String num, String pass) {

    }
}

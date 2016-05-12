package cn.edu.hpu.yuan.yuannews.user.login;

/**
 * Created by yuan on 16-5-12.
 */
public class LoginPresenter implements LoginContract.LoginContractPresenter{


    private LoginContract.LoginContractView loginContractView;

    public LoginPresenter(LoginContract.LoginContractView loginContractView) {
        this.loginContractView = loginContractView;
    }

    @Override
    public void postUserLogin(String num, String pass) {
        loginContractView.showDialog();

    }
}

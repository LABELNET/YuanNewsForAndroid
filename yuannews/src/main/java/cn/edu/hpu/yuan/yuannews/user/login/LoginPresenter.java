package cn.edu.hpu.yuan.yuannews.user.login;

import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.data.model.DataBean;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.UserVo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuan on 16-5-12.
 */
public class LoginPresenter implements LoginContract.LoginContractPresenter{


    private LoginContract.LoginContractView loginContractView;

    public LoginPresenter(LoginContract.LoginContractView loginContractView) {
        this.loginContractView = loginContractView;
    }

    @Override
    public Integer postUserLogin(String num, String pass) {
        loginContractView.showDialog();
        BaseApplication.newsAPIService.postUserLogin(num,pass).enqueue(new Callback<DataBean<UserVo>>() {
            @Override
            public void onResponse(Call<DataBean<UserVo>> call, Response<DataBean<UserVo>> response) {
                loginContractView.dmissDialog();
                if(response.isSuccessful()){
                    //存用户信息
                }
            }

            @Override
            public void onFailure(Call<DataBean<UserVo>> call, Throwable t) {

            }
        });
        return -1;
    }
}

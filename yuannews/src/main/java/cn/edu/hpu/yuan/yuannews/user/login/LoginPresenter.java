package cn.edu.hpu.yuan.yuannews.user.login;

import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.data.NewsAPI;
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
    public void postUserLogin(String num, String pass) {
        loginContractView.showDialog();
        BaseApplication.newsAPIService.postUserLogin(num,pass).enqueue(new Callback<DataBean<UserVo>>() {
            @Override
            public void onResponse(Call<DataBean<UserVo>> call, Response<DataBean<UserVo>> response) {
                loginContractView.dmissDialog();
                if(response.isSuccessful()){
                    DataBean<UserVo> body = response.body();
                    if(body.getCode()==0){

                        UserVo data = body.getData();
                        if(data!=null){
                            //存储用户信息
                            BaseApplication.newsAPIShared.putSharedUserIfo(NewsAPI.BASE_URL+data.getHead()
                                    ,data.getId(),
                                    data.getNick());
                            loginContractView.success();
                        }else{
                            loginContractView.error("请求失败");
                        }
                    }else {
                        loginContractView.error(body.getMsg());
                    }
                }else {
                    loginContractView.error("网络错误");
                }
            }
            @Override
            public void onFailure(Call<DataBean<UserVo>> call, Throwable t) {
                loginContractView.error("网络错误");
            }
        });
    }
}

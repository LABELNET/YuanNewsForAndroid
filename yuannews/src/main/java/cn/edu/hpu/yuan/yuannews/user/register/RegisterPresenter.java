package cn.edu.hpu.yuan.yuannews.user.register;

import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.data.model.DataBean;
import cn.edu.hpu.yuan.yuannews.user.register.RegisterContancts.RegisterContanctsPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        registerCOntanctsView.showDialog();
        BaseApplication.newsAPIService.postUserRegister(num,pass,nick).enqueue(new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {

                if(response.isSuccessful()){
                    if(response.body().getCode()==0){
                        registerCOntanctsView.showSuccess();
                    }else{
                        registerCOntanctsView.showError(response.body().getMsg());
                    }
                }else {
                    registerCOntanctsView.showError("网络不稳定");
                }
            }
            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {
                registerCOntanctsView.showError("网络不稳定");
            }
        });
    }
}

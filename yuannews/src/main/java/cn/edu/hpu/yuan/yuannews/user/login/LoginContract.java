package cn.edu.hpu.yuan.yuannews.user.login;

/**
 * Created by yuan on 16-5-12.
 */
public interface LoginContract {


    interface LoginContractPresenter{

      void postUserLogin(String num,String pass);

    }

    interface LoginContractView{

        void showDialog();

        void success();

        void error(String msg);

        void dmissDialog();
    }

}

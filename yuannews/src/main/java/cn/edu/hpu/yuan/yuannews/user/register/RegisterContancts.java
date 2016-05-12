package cn.edu.hpu.yuan.yuannews.user.register;

/**
 * Created by yuan on 16-5-12.
 */
public interface RegisterContancts {

    interface RegisterContanctsPresenter{

        void postUserRegister(String nick,String num,String pass);

    }

    interface RegisterCOntanctsView{
        void showDialog();
        void showError(String msg);
        void showSuccess();
    }

}

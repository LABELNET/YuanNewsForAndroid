package cn.edu.hpu.yuan.yuannews.user.userIfo;

/**
 * Created by yuan on 16-5-12.
 */
public interface UserIfoContrancts {

    interface UserIfoContranctsPresenter{

        void postUpdateUserNick(String nick);

        void postUpdateUserSex(Integer sex);

        void postUpdateUserPass(String pass);

        void postUpdateUserHead();

    }

    interface UserIfoContranctsView{

        void showDialog();
        void showMsg(String msg);

    }

}

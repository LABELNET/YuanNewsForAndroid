package cn.edu.hpu.yuan.yuannews.user.userIfo;

import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;

/**
 * Created by yuan on 16-5-12.
 */
public class UserIfoPresenter implements UserIfoContrancts.UserIfoContranctsPresenter{

    private int uid;
    private UserIfoContrancts.UserIfoContranctsView userIfoContranctsView;
    public UserIfoPresenter(UserIfoContrancts.UserIfoContranctsView userIfoContranctsView) {
        this.userIfoContranctsView = userIfoContranctsView;
        uid= BaseApplication.newsAPIShared.getSharedUserID();
    }

    @Override
    public void postUpdateUserNick(String nick) {

    }

    @Override
    public void postUpdateUserSex(Integer sex) {

    }

    @Override
    public void postUpdateUserPass(String pass) {

    }

    @Override
    public void postUpdateUserHead() {

    }
}

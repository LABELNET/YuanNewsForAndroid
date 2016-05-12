package cn.edu.hpu.yuan.yuannews.user.userIfo;

import android.os.Bundle;
import android.view.View;

import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;

/**
 * Created by yuan on 16-5-12.
 */
public class UserIfoFragment extends NorbalBackFragment implements UserIfoContrancts.UserIfoContranctsView{


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initComponent() {

        DaggerUserifoComponent
                .builder()
                .userIfoModule(new UserIfoModule(this))
                .build()
                .injectUserIfoFragment(this);

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void showMsg(String msg) {

    }
}

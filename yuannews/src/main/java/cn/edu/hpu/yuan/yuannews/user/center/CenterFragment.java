package cn.edu.hpu.yuan.yuannews.user.center;

import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.TasteVo;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.UserVo;

/**
 * Created by yuan on 16-5-12.
 */
public class CenterFragment extends NorbalBackFragment implements CenterContancts.CenterContanctsView {


    @Inject
    protected  CenterContancts.CenterContanctsPresenter centerContanctsPresenter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initComponent() {
        DaggerCenterComponent
                .builder()
                .centerModule(new CenterModule(this))
                .build()
                .injectCenterFragment(this);
    }


    @Override
    public void showDialog() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showUserDetail(UserVo userVo) {

    }

    @Override
    public void showAllLabels(TasteVo tasteVo) {

    }
}

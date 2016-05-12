package cn.edu.hpu.yuan.yuannews.user.label;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;
import cn.edu.hpu.yuan.yuannews.user.label.adapter.LabelIfoAdapter;

/**
 * Created by yuan on 16-5-12.
 */
public class LabelFragment extends NorbalBackFragment implements LabelContancts.LabelContanctsView{


    @Inject
    protected LabelContancts.LabelContanctsPresenter labelContanctsPresenter;

    @Inject
    protected LabelIfoAdapter labelIfoAdapter;

    @Override
    protected void initComponent() {
        DaggerLabelComponent
                .builder()
                .labelModule(new LabelModule(this))
                .build()
                .injectLabelFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }
}

package cn.edu.hpu.yuan.yuannews.news.labels;

import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.TasteVo;
import cn.edu.hpu.yuan.yuannews.user.label.adapter.LabelIfoAdapter;

/**
 * Created by yuan on 16-5-13.
 */
public class LabelsFragment extends NorbalBackFragment implements LabelsContancts.LabelsContanctsView,LabelIfoAdapter.OnDeleteItemClick{


    @Inject
    protected LabelsContancts.LabelsContanctsPresenter labelsContanctsPresenter;

    @Inject
    protected LabelIfoAdapter labelIfoAdapter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void onloadData() {
        super.onloadData();
    }

    @Override
    protected void initComponent() {
       DaggerLabelsComponent.builder()
               .labelsModule(new LabelsModule(this,getContext()))
               .build()
               .injectLabelsFragment(this);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void showLabelsData() {

    }

    @Override
    public void onDelete(TasteVo tasteVo, int position) {
        //这个是点击关注后，移除的详情标签
    }
}

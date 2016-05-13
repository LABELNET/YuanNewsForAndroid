package cn.edu.hpu.yuan.yuannews.news.labels;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.databinding.LabelFragmentBinding;
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

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
    public void showLabelsData(List<String> tastes) {

    }

    @Override
    public void showErrorMsg(String msg) {

    }


    @Override
    public void onDelete(TasteVo tasteVo, int position) {
        //这个是点击关注后，移除的详情标签
    }
}

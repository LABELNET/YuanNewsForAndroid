package cn.edu.hpu.yuan.yuannews.user.label;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import cn.edu.hpu.yuan.yuancore.util.LogUtil;
import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.LabelFragmentBinding;
import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.TasteVo;
import cn.edu.hpu.yuan.yuannews.user.label.adapter.LabelIfoAdapter;

/**
 * Created by yuan on 16-5-12.
 */
public class LabelFragment extends NorbalBackFragment implements LabelContancts.LabelContanctsView,LabelIfoAdapter.OnDeleteItemClick{


    @Inject
    protected LabelContancts.LabelContanctsPresenter labelContanctsPresenter;

    @Inject
    protected LabelIfoAdapter labelIfoAdapter;

    private LabelFragmentBinding binding;

    @Override
    protected void initComponent() {
        DaggerLabelComponent
                .builder()
                .labelModule(new LabelModule(this,getContext()))
                .build()
                .injectLabelFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.label_fragment,container,false);
        return binding.getRoot();
    }

    @Override
    public void showDialog() {
        show("正在处理中");
    }

    @Override
    public void showMsg(String msg) {
        show(msg);
    }

    @Override
    public void deleteSuccess() {
        labelContanctsPresenter.postAllLabel();
    }

    @Override
    public void addSuccess() {
        labelContanctsPresenter.postAllLabel();
    }

    @Override
    public void showAlldata(List<TasteVo> tasteVos) {
        Collections.reverse(tasteVos);//倒序
        initLabels(tasteVos);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        labelIfoAdapter.setOnDeleteItemClick(this);
        binding.labelLists.setAdapter(labelIfoAdapter);

        if(getArguments()!=null){
            List<TasteVo> tasteVos = (List<TasteVo>) getArguments().getSerializable("tasteVos");
            initLabels(tasteVos);
        }else{
            labelContanctsPresenter.postAllLabel();
        }

        binding.btnAddLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLabel();
            }
        });
    }

    private void initLabels(List<TasteVo> tasteVos) {
        if(tasteVos.size()==0){
            binding.noData.setVisibility(View.VISIBLE);
        }else {
            binding.noData.setVisibility(View.GONE);
        }
        labelIfoAdapter.addTasteVo(tasteVos);
        labelIfoAdapter.notifyDataSetChanged();
    }

    //添加兴趣标签
    private void addLabel() {

        String label=binding.btnEditIfo.getText().toString().trim();
        if(label.length()>=0&&label.length()<3){
            show("标签不足4个字，无法添加！");
            return;
        }

        if(labelIfoAdapter.getCount()>=30){
            show("最多给关注30个兴趣标签！");
            return;
        }
        binding.btnEditIfo.setText("");
        labelContanctsPresenter.postAddLabel(label);
    }

    private void show(String msg){
        Snackbar.make(binding.labelFragment,msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDelete(TasteVo tasteVo,int position) {
        labelContanctsPresenter.postDeleteLabel(tasteVo.getId());
    }
}

package cn.edu.hpu.yuan.yuannews.user.center;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.CenterFragmentBinding;
import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.TasteVo;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.UserVo;

/**
 * Created by yuan on 16-5-12.
 */
public class CenterFragment extends NorbalBackFragment implements CenterContancts.CenterContanctsView {


    @Inject
    protected  CenterContancts.CenterContanctsPresenter centerContanctsPresenter;
    private CenterFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.center_fragment,container,false);
        return binding.getRoot();
    }

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
        showMsg("加载中...");
    }

    @Override
    public void showError(String msg) {
        showMsg(msg);
    }

    @Override
    public void showUserDetail(UserVo userVo) {
        binding.setUserVo(userVo);
    }

    @Override
    public void showAllLabels(List<TasteVo> tasteVo) {

        if(tasteVo!=null){
            if(tasteVo.size()==0){
                binding.userNoLabel.setVisibility(View.VISIBLE);
            }else{
                binding.userNoLabel.setVisibility(View.GONE);
                //TODO 处理label,添加到linearlayout
            }
        }
    }

    private void showMsg(String msg){
        Snackbar.make(binding.userCenterPage,msg,Snackbar.LENGTH_SHORT).show();
    }
}

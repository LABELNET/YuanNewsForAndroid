package cn.edu.hpu.yuan.yuannews.news.labels;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import java.util.List;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.LabelFragmentBinding;
import cn.edu.hpu.yuan.yuannews.databinding.LabelsFragmentBinding;
import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.TasteVo;
import cn.edu.hpu.yuan.yuannews.news.labels.adapter.LabelsAdapter;
import cn.edu.hpu.yuan.yuannews.user.label.adapter.LabelIfoAdapter;
import cn.edu.hpu.yuan.yuannews.user.login.LoginActivity;

/**
 * Created by yuan on 16-5-13.
 */
public class LabelsFragment extends NorbalBackFragment implements LabelsContancts.LabelsContanctsView,LabelIfoAdapter.OnDeleteItemClick{


    @Inject
    protected LabelsContancts.LabelsContanctsPresenter labelsContanctsPresenter;

    @Inject
    protected LabelsAdapter labelsAdapter;

    private LabelsFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.labels_fragment,container,false);
        return binding.getRoot();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        labelsAdapter.initTasteVo();
        binding.labelLists.setAdapter(labelsAdapter);

        binding.labelLists.setOnScrollListener(new AbsListView.OnScrollListener() {

            private int lastItemIndex=0;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && lastItemIndex == labelsAdapter.getCount() - 1) {
                    labelsContanctsPresenter.nextgetTasteData();//加载更多
                  }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastItemIndex = firstVisibleItem + visibleItemCount - 1 ;
            }
        });



    }

    @Override
    protected void onloadData() {
        labelsContanctsPresenter.initgetTasteData();
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
        showSnack("加载中...");
    }

    @Override
    public void showLabelsData(List<String> tastes) {
        labelsAdapter.addTasteVo(tastes);
        labelsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMsg(String msg) {
        showSnack(msg);
    }


    @Override
    public void onDelete(TasteVo tasteVo, int position) {
        //这个是点击关注后，移除的详情标签
        if(BaseApplication.newsAPIShared.getSharedUserID()>0){
            //可以关注
        }else{
            showSnackAction("你还未登陆");
        }
    }

    private void showSnack(String msg){
        Snackbar.make(binding.labelsFragmentPage,msg,Snackbar.LENGTH_SHORT).show();
    }

    private void showSnackAction(String msg){
        Snackbar.make(binding.labelsFragmentPage,msg,Snackbar.LENGTH_SHORT)
                .setAction("点我登陆", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //登陆
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    }
                }).show();
    }
}

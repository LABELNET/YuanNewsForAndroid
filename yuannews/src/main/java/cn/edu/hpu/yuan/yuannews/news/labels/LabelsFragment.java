package cn.edu.hpu.yuan.yuannews.news.labels;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import java.util.List;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuancore.util.LogUtil;
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

    private SwipeRefreshLayout.OnRefreshListener swipeOnRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.labels_fragment,container,false);
        return binding.getRoot();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        binding.labelLists.setAdapter(labelsAdapter);
        initSwipeRefreshLayout();
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

    private void initSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary
                ,R.color.colorPrimaryDark
                ,R.color.colorAccent);
        binding.swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        binding.swipeRefreshLayout.setProgressViewEndTarget(true,500);//进度条位置
        binding.swipeRefreshLayout.setOnRefreshListener(swipeOnRefresh=new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onloadData();
            }
        });

        binding.swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                binding.swipeRefreshLayout.setRefreshing(true);
            }
        });
        swipeOnRefresh.onRefresh();
    }

    @Override
    protected void onloadData() {
        labelsAdapter.initTasteVo();
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
        LogUtil.v(tastes.toString());
        if(binding.swipeRefreshLayout.isRefreshing()){
            binding.swipeRefreshLayout.setRefreshing(false);
        }
        labelsAdapter.addTasteVo(tastes);
        if(labelsAdapter.getCount()==0){
            binding.noData.setVisibility(View.VISIBLE);
        }else {
            binding.noData.setVisibility(View.GONE);
            labelsAdapter.notifyDataSetChanged();
        }
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
            showSnack("点击关注了");
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

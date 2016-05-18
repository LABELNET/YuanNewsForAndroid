package cn.edu.hpu.yuan.yuannews.news.tuijian;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.NewsFragmentBinding;
import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;
import cn.edu.hpu.yuan.yuannews.news.labels.LabelsActivity;
import cn.edu.hpu.yuan.yuannews.news.newsdetail.NewsDetailActivity;
import cn.edu.hpu.yuan.yuannews.news.newslist.adapter.CustomRecyclerViewAdapter;

/**
 * Created by yuan on 16-5-13.
 */
public class TuijianFragment extends NorbalBackFragment implements TuijianContancts.TuijianContanctsView,CustomRecyclerViewAdapter.NewsListItemClick{


    @Inject
    protected TuijianContancts.TuijianContanctsPresenter tuijianContanctsPresenter;

    @Inject
    protected CustomRecyclerViewAdapter customRecyclerViewAdapter;

    private NewsFragmentBinding binding;

    private int type=2;
    private boolean isNoData=true;

    private SwipeRefreshLayout.OnRefreshListener swipeOnRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.news_fragment,container,false);
        return binding.getRoot();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initSwipeRefreshLayout();
        initRecyclerView();

        binding.btnLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LabelsActivity.class));
                getActivity().finish();
            }
        });
    }


    /**
     * 初始化下拉刷新
     */
    private void initSwipeRefreshLayout() {

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) binding.swipeRefreshLayout.getLayoutParams();
        layoutParams.topMargin=10;
        binding.swipeRefreshLayout.setLayoutParams(layoutParams);

        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary
                ,R.color.colorPrimaryDark
                ,R.color.colorAccent);
        binding.swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        binding.swipeRefreshLayout.setProgressViewEndTarget(true,500);//进度条位置
        binding.swipeRefreshLayout.setOnRefreshListener(swipeOnRefresh=new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tuijianContanctsPresenter.initNewsListData(type);
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


    /**
     *  初始化RecyclerView
     */
    private void initRecyclerView() {
        customRecyclerViewAdapter.setNewsListItemClick(this);
        binding.recyclerView.setAdapter(customRecyclerViewAdapter);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
                int lastVisibleItem = ((LinearLayoutManager) lm).findLastVisibleItemPosition();
                int totalItemCount = lm.getItemCount();
                //最后一项
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem == totalItemCount - 1) {
                    tuijianContanctsPresenter.nextNewsListData(type);
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    protected void initComponent() {
        DaggerTuijianComponent
                .builder()
                .tuijianModule(new TuijianModule(this,getContext()))
                .build()
                .injectTuijianFragment(this);
    }

    @Override
    public void showDialog() {
        showMsg("加载中...");
    }

    @Override
    public void showErrorMsg(String msg) {
        hideSwipRefreshLayout();
        showMsg(msg);
        initNoData();
    }

    @Override
    public void showNewsData(ArrayList<NewsCustom> newsCustoms) {
        hideSwipRefreshLayout();
        customRecyclerViewAdapter.initData(newsCustoms);
        customRecyclerViewAdapter.notifyDataSetChanged();
        if(!isNoData){
            isNoData=true;
        }
    }

    private void initNoData(){
        if(customRecyclerViewAdapter.getItemCount()==0){
            binding.noDataNews.setVisibility(View.VISIBLE);
        }else{
            binding.noDataNews.setVisibility(View.GONE);
        }
    }

    @Override
    public void showNoData() {
        hideSwipRefreshLayout();
        if(isNoData) {
            showMsg("没有更多数据了");
            isNoData=false;
        }
        initNoData();
    }

    private void hideSwipRefreshLayout() {
        if(binding.swipeRefreshLayout.isRefreshing()){
            binding.swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onItemClick(int nid) {
        Intent intent=new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra("nid",nid);
        startActivity(intent);
    }

    private void showMsg(String msg){
        Snackbar.make(binding.recyclerView,msg,Snackbar.LENGTH_SHORT).show();
    }
}

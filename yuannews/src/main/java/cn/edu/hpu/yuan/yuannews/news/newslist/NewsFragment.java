package cn.edu.hpu.yuan.yuannews.news.newslist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.NewsFragmentBinding;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;
import cn.edu.hpu.yuan.yuannews.news.newslist.adapter.CustomRecyclerViewAdapter;


/**
 * Created by yuan on 16-5-9.
 * 新闻列表Fragment
 * 1.功能：加载新闻列表
 *
 */
public class NewsFragment extends BaseFragment implements NewsContract.View{

    @Inject
    protected NewsContract.Presenter newsPresenter;

    @Inject
    protected CustomRecyclerViewAdapter customRecyclerViewAdapter;

    private  NewsFragmentBinding bind;
    private SwipeRefreshLayout.OnRefreshListener swipeOnRefresh;

    //action
    private static final String NEWSFRAGMENT_TYPE_ACTION="newsfragment_type_action";

    private static final String NEWS_TYPE="news_type";
    private static final String NEWS_TYPE_NTYPE="news_type_ntype";
    private String title ="首页";//分类来源内容
    private Integer nType=6;//查询类型
    private Integer type=2; //正常情况，默认，是可以修改的通过floatButton修改
    private boolean isloadMore=false;

    public static NewsFragment getNewsFragmentInstance(String type,Integer nType){
        NewsFragment fragment = new NewsFragment();
        Bundle bundle=new Bundle();
        bundle.putString(NEWS_TYPE,type);
        bundle.putInt(NEWS_TYPE_NTYPE,nType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBaseActivityReceiver();//注册广播
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unRegisterBaseActivityReceiver();//注销广播
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.news_fragment, null);
         bind = NewsFragmentBinding.bind(view);
        return  bind.getRoot();
    }

    @Override
    protected void initComponent() {
        DaggerNewsComponent.builder()
                .newsModule(new NewsModule(this,getContext()))
                .build()
                .injectNewsFragment(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        initData();
        initSwipeRefreshLayout();
    }



    /**
     * 初始化下拉刷新
     */
    private void initSwipeRefreshLayout() {
        bind.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary
                ,R.color.colorPrimaryDark
                ,R.color.colorAccent);
        bind.swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        bind.swipeRefreshLayout.setProgressViewEndTarget(true,500);//进度条位置
        bind.swipeRefreshLayout.setOnRefreshListener(swipeOnRefresh=new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(isloadMore){
                    refresh();
                }else {
                    bind.swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        refresh();
    }

    private void refresh() {
        if(isloadMore) {
            isloadMore = false;
        }
        onload(title,type,nType);
    }

    private void refreshMore(){
        if(!isloadMore) {
            isloadMore = true;
        }
        onLoadMore(title,type,nType);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        title= getArguments().getString(NEWS_TYPE);
        nType=getArguments().getInt(NEWS_TYPE_NTYPE);
    }

    /**
     * 加载数据的方法
     * @param title   分类和来源信息
     * @param type   当前类型
     * @param nType  当前查询方式
     */
    private void onload(String title,int type,int nType){
        newsPresenter.initNewsListData(title,type,nType);
    }

    /**
     * 加载更多
     * @see #onload(String, int, int)
     */
    private void onLoadMore(String title,int type,int nType) {
        newsPresenter.nextNewsListData(title, type, nType);
    }

    /**
     *  初始化RecyclerView
     */
    private void initRecyclerView() {
        bind.recyclerView.setAdapter(customRecyclerViewAdapter);
        bind.recyclerView.setHasFixedSize(true);
        bind.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bind.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
                int lastVisibleItem = ((LinearLayoutManager) lm).findLastVisibleItemPosition();
                int totalItemCount = lm.getItemCount();
                //最后一项
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem == totalItemCount - 1) {
                    refreshMore();
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void showDialog() {
        bind.swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                bind.swipeRefreshLayout.setRefreshing(true);
            }
        });
        swipeOnRefresh.onRefresh();
    }

    @Override
    public void showNewsList(ArrayList<NewsCustom> newsCustoms) {
        customRecyclerViewAdapter.initData(newsCustoms);
        customRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showInitNewsList() {
        //清空adapter数据
        customRecyclerViewAdapter.clearData();
    }

    @Override
    public void dismssDiolog() {
        //隐藏进度条
        bind.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showNotData() {
        showSnackBar("没有数据了");
    }

    @Override
    public void showSnackBar(String msg) {
        Snackbar.make(bind.recyclerView,msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {

    }


    private void registerBaseActivityReceiver(){
        IntentFilter filter=new IntentFilter();
        filter.addAction(NEWSFRAGMENT_TYPE_ACTION);
        activity.registerReceiver(baseActivityReceiver,filter);
    }

    private void unRegisterBaseActivityReceiver(){
        activity.unregisterReceiver(baseActivityReceiver);
    }


    /**
     * 广播，进行接收type类型，改变排序规则
     */
    BroadcastReceiver baseActivityReceiver=new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action=intent.getAction();
            if(NEWSFRAGMENT_TYPE_ACTION.equals(action)){
                type=intent.getIntExtra(NEWSFRAGMENT_TYPE_ACTION,2);
                refresh();
            }

        }
    };

}

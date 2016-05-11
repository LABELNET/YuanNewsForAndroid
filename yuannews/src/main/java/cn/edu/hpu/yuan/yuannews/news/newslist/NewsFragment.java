package cn.edu.hpu.yuan.yuannews.news.newslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
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

    private static final String NEWS_TYPE="news_type";
    private static final String NEWS_TYPE_NTYPE="news_type_ntype";
    private String title = getArguments().getString(NEWS_TYPE);//分类来源内容
    private Integer nType=getArguments().getInt(NEWS_TYPE_NTYPE);//查询类型
    private Integer type=2; //正常情况，默认，是可以修改的通过floatButton修改

    public static NewsFragment getNewsFragmentInstance(String type,Integer nType){
        NewsFragment fragment = new NewsFragment();
        Bundle bundle=new Bundle();
        bundle.putString(NEWS_TYPE,type);
        bundle.putInt(NEWS_TYPE_NTYPE,nType);
        fragment.setArguments(bundle);
        return fragment;
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
        onload(title,type,nType);
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
     *  初始化RecyclerView
     */
    private void initRecyclerView() {
        bind.recyclerView.setAdapter(customRecyclerViewAdapter);
        bind.recyclerView.setHasFixedSize(true);
        bind.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    public void showDialog() {
        //显示进度条
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
}

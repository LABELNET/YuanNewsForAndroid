package cn.edu.hpu.yuan.yuannews.news.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import javax.inject.Inject;

import cn.edu.hpu.yuan.yuancore.util.LogUtil;
import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.news.main.adapter.MainViewPagerAdapter;

/**
 * Created by yuan on 16-5-10.
 * 1.主界面：初始化加载，分类和来源信息到tabs上
 * 2.。。。
 */
public class MainFragment extends BaseFragment implements MainContract.MainView{


    @Inject
    protected MainContract.MainPresenter mainPresenter;

    @Inject
    protected MainViewPagerAdapter mainViewPagerAdapter;

    private   ViewPager mViewPager;
    private   TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    protected void initComponent() {
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this,getActivity().getSupportFragmentManager()))
                .build()
                .injectMainFragment(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing);
        collapsingToolbar.setTitle("失控");
        //设置ViewPager
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mViewPager.setAdapter(mainViewPagerAdapter);

        //给TabLayout增加Tab, 并关联ViewPager
        tabLayout= (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(mViewPager);

        //加载数据
        mainPresenter.loadDataRequest();
    }

    @Override
    public void showLoadDialog() {
        LogUtil.v("showLoadDialog");
    }

    @Override
    public void showLoadData(List<Fragment> fragments, List<String> titles) {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mainViewPagerAdapter.initAdapter(fragments,titles);
        mainViewPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void finishLoadDialog() {
        LogUtil.v("finishLoadDialog");
    }

    @Override
    public void loadError() {
        loadMsg("网络请求失败");
    }

    @Override
    public void loadMsg(String msg) {
        Snackbar.make(mViewPager,msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(MainContract.MainPresenter presenter) {
    }

}

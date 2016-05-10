package cn.edu.hpu.yuan.yuannews.news.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.news.main.adapter.MainViewPagerAdapter;
import cn.edu.hpu.yuan.yuannews.news.newslist.NewsFragment;

/**
 * Created by yuan on 16-5-10.
 * 1.主界面：初始化加载，分类和来源信息到tabs上
 * 2.。。。
 */
public class MainFragment extends BaseFragment implements MainContract.MainView{


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
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        setupViewPager(mViewPager);

        //给TabLayout增加Tab, 并关联ViewPager
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        tabLayout.addTab(tabLayout.newTab().setText("内容简介"));
//        tabLayout.addTab(tabLayout.newTab().setText("作者简介"));
//        tabLayout.addTab(tabLayout.newTab().setText("目录"));
        tabLayout.setupWithViewPager(mViewPager);


    }

    private void setupViewPager(ViewPager mViewPager) {
        final List<Fragment> views=new ArrayList<>();
        final List<String> strings=new ArrayList<>();
        for(int i=0;i<2;i++){
            views.add(NewsFragment.getNewsFragmentInstance(" 原 "+i));
            strings.add(" 原 "+i);
        }
      final  MainViewPagerAdapter adapter = new MainViewPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i=3;i<10;i++){
                    views.add(NewsFragment.getNewsFragmentInstance(" 原 "+i));
                    strings.add(" 原sdvsdvsdv "+i);
                }
                adapter.notifyDataSetChanged();
            }
        },10000);
    }


    @Override
    public void showLoadDialog() {

    }

    @Override
    public void showLoadData(List<Fragment> fragments, List<String> titles) {

    }

    @Override
    public void finishLoadDialog() {

    }

    @Override
    public void setPresenter(MainContract.MainPresenter presenter) {

    }
}

package cn.edu.hpu.yuan.yuannews.news.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import javax.inject.Singleton;

import cn.edu.hpu.yuan.yuannews.news.main.adapter.MainViewPagerAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-10.
 * Module包括两个功能：
 * 1.给MainAvtivity提供Fragment
 * 2.给Fragment提供其他实例化的对象
 * 所以存在两个构造函数；
 */
@Module
public class MainModule {


    private MainContract.MainView mainView;
    private FragmentManager fragmentManager;


    //Activity 实例化
    public MainModule() {
    }

    //fragment实例化
    public MainModule(MainContract.MainView mainView,FragmentManager fragmentManager) {
        this.mainView = mainView;
        this.fragmentManager=fragmentManager;
    }

    //给mainActivity 给他Fragment
    @Provides
    MainFragment provideMainFragment(){
        return new MainFragment();
    }

    //给mainFragment提供MainPresenter
    @Provides
    MainContract.MainPresenter provideMainPresenter(){
        return new MainPresenter(mainView);
    }

    //给MainFragment提供adapter
    @Provides
    MainViewPagerAdapter provideMainViewPagerAdapter(){
        return new MainViewPagerAdapter(fragmentManager);
    }


}

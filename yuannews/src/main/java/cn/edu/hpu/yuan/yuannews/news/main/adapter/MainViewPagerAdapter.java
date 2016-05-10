package cn.edu.hpu.yuan.yuannews.news.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hpu.yuan.yuannews.news.newslist.NewsFragment;

/**
 * Created by yuan on 16-5-10.
 * 主页的ViewPager的Adapter
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter{

    private final List<Fragment> list_fragment=new ArrayList<>();
    private final List<String> list_Title=new ArrayList<>();



    public void initAdapter(List<Fragment> fragments, List<String> listTitle){
        list_fragment.addAll(fragments);
        list_Title.addAll(listTitle);
    }

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        list_fragment.add(NewsFragment.getNewsFragmentInstance("默认",0,6));
        list_Title.add("默认");
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_Title.size();
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position);
    }

}

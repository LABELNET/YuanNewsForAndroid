package cn.edu.hpu.yuan.yuannews.news.newslist.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yuan on 16-5-9.
 */
public class ViewPageNewsAdapter extends PagerAdapter{


    private List<View> array;

    @Override
    public int getCount() {
        return array.size();
    }


    public ViewPageNewsAdapter(List<View> array) {
        super();
        this.array=array;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(array.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(array.get(position));
        return array.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

}

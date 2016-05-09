package cn.edu.hpu.yuan.yuannews.main.news;

import cn.edu.hpu.yuan.yuannews.main.BaseActivity;
import cn.edu.hpu.yuan.yuannews.main.BaseFragment;

/**
 * Created by yuan on 16-5-9.
 * 新闻列表Activity
 */
public class NewsActivity extends BaseActivity{

    @Override
    protected BaseFragment initFragment() {
        return new NewsFragment();
    }

    @Override
    protected void setToolBarTitle() {
        setTitle("新闻推荐");
    }
}

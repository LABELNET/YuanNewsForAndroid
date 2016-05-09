package cn.edu.hpu.yuan.yuannews.main.news;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.main.BaseActivity;
import cn.edu.hpu.yuan.yuannews.main.BaseFragment;

/**
 * Created by yuan on 16-5-9.
 * 新闻列表Activity
 */
public class NewsActivity extends BaseActivity{


    @Inject
    protected Context context;

    @Inject
    protected NewsFragment fragment;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("news");
        Toast.makeText(context,"Hi",Toast.LENGTH_LONG).show();
    }

    @Override
    protected BaseFragment initFragment() {
        return new NewsFragment();
    }

    @Override
    protected void setComponent() {
         DaggerNewsComponent.builder()
                 .applicationComponent(getApplicationComponent())
                 .newsModule(new NewsModule())
                 .build()
                 .injectNewsActivity(this);
    }
}

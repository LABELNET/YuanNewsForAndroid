package cn.edu.hpu.yuan.yuannews.news.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuancore.util.LogUtil;
import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.base.BaseActivity;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.user.center.CenterActivity;
import cn.edu.hpu.yuan.yuannews.user.login.LoginActivity;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yuan on 16-5-10.
 * 1.主界面
 * 2.功能：初始化界面，包括分类和来源
 */
public class MainActivity extends BaseActivity{



    @Inject
    protected MainFragment mainFragment;

    @Override
    protected void initView(Bundle savedInstanceState, Toolbar toolbar) {
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected BaseFragment initFragment() {
        return mainFragment;
    }

    @Override
    protected void setComponent() {
        DaggerMainComponent.builder()
                .mainModule(new MainModule())
                .build()
                .injectMainActivity(this);
    }

    @Override
    protected Context getChildContext() {
        return MainActivity.this;
    }

}

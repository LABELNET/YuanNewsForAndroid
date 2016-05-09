package cn.edu.hpu.yuan.yuannews.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import cn.edu.hpu.yuan.yuannews.R;

/**
 * Created by yuan on 16-5-9.
 * BaseActivity 基类
 * 1.toolbar
 * 2.初始化fragment
 * 3.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.base_main);

        //初始化toolbar
        final Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        //初始化fragment
        BaseFragment fragment=initFragment();
        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction().add(R.id.contentFrame, fragment).commit();
    }

    //返回fragment
    protected abstract BaseFragment initFragment();



}

package cn.edu.hpu.yuan.yuannews.main.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.app.ApplicationComponent;
import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.util.LollipopUtils;

/**
 * Created by yuan on 16-5-12.
 *
 * 正常的，独立的Activity
 * 独立于BaseActivity ：
 * 登陆
 * 注册
 * 个人中心
 * 标签管理
 * 关于
 * 这几个界面
 */
public abstract class NormalBaseActivity extends AppCompatActivity {


    public ApplicationComponent getApplicationComponent() {
        return ((BaseApplication) getApplication()).getApplicationComponent();
    }

    Toolbar toolbar;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_base_main);
        setComponet();
        //初始化toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        LollipopUtils.setStatusbarColor(this, toolbar);

        //初始化fragment
        BaseFragment fragment = initFragment();
        if(fragment!=null) {
            fragment.setApplicationComponent(getApplicationComponent());
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.contentFrame, fragment).commit();
        }
        //初始化toolbar
        initToolbar(toolbar);

        initView();

    }

    protected abstract void initView();

    protected abstract void setComponet();

    protected abstract void initToolbar(Toolbar toolbar);

    protected abstract BaseFragment initFragment();
}

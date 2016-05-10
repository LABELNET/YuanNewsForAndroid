package cn.edu.hpu.yuan.yuannews.main.base;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.app.ApplicationComponent;
import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.data.remote.NewsAPIService;

/**
 * Created by yuan on 16-5-9.
 * BaseActivity 基类
 * 1.toolbar
 * 2.初始化fragment
 * 3.
 */
public abstract class BaseActivity extends AppCompatActivity {


    /**
     * 得到ApplicationComponent对象
     *
     * @return
     */
    public ApplicationComponent getApplicationComponent() {
        return ((BaseApplication) getApplication()).getApplicationComponent();
    }

    /**
     * 获取NewsApIService对象
     * @return
     */
    public NewsAPIService getNewsAPIService(){
        return ((BaseApplication) getApplication()).getNewsAPIService();
    }

    private   DrawerLayout  mDrawerLayout;
    private  ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.base_main);
        setComponent();


        //初始化toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);


        //设置DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        //设置NavigationView点击事件
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(mNavigationView);

        //初始化fragment
        BaseFragment fragment = initFragment();
        fragment.setApplicationComponent(getApplicationComponent());
        fragment.setNewsAPIService(getNewsAPIService());
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.contentFrame, fragment).commit();


        initView(savedInstanceState,toolbar);
    }


    //设置NavigationView点击事件
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.navigation_item_example:
                                showToast("navigation_item_example");
                                break;
                            case R.id.navigation_item_blog:
                                showToast("navigation_item_blog");
                                break;
                            case R.id.navigation_item_about:
                                showToast("navigation_item_about");
                                break;

                        }
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    protected void showToast(String msg){
        Toast.makeText(getBaseContext(),msg,Toast.LENGTH_SHORT).show();
    }

    //初始化界面和toolbae添加其他的操作
    protected abstract void initView(Bundle savedInstanceState,Toolbar toolbar);

    //返回fragment
    protected abstract BaseFragment initFragment();

    //初始化Component
    protected abstract void setComponent();

}

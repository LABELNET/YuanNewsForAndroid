package cn.edu.hpu.yuan.yuannews.main.base;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
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
    private  Toolbar toolbar;
    private  AlertDialog dialog;
    private  FloatDialogClickListener floatDialogClickListener;
    private static final String NEWSFRAGMENT_TYPE_ACTION="newsfragment_type_action";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.base_main);
        setComponent();


        //初始化toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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


        //初始化FloatButton
        FloatingActionButton floatingActionButton= (FloatingActionButton) findViewById(R.id.fab_add_task);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog();
            }
        });

        initView(savedInstanceState,toolbar);
        initToolbar(toolbar,floatingActionButton);
    }
    private void showChoiceDialog(){
        floatDialogClickListener=new FloatDialogClickListener();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.base_main_float_dialog, null);
        builder.setView(view);
        builder.setTitle("排序规则");
        dialog = builder.create();
        dialog.show();
        view.findViewById(R.id.float_tuijian).setOnClickListener(floatDialogClickListener);
        view.findViewById(R.id.float_comment).setOnClickListener(floatDialogClickListener);
        view.findViewById(R.id.float_quxiao).setOnClickListener(floatDialogClickListener);
        view.findViewById(R.id.float_rnum).setOnClickListener(floatDialogClickListener);
        view.findViewById(R.id.float_zan).setOnClickListener(floatDialogClickListener);
    }



    protected void closeDrawerLayout(){
        mDrawerLayout.closeDrawers();
    }

    //设置NavigationView点击事件
    protected void setupDrawerContent(NavigationView navigationView) {
    }

    public  void showToast(String msg){
        Toast.makeText(getBaseContext(),msg,Toast.LENGTH_SHORT).show();
    }

    protected void initToolbar(Toolbar toolbar, FloatingActionButton floatingActionButton){}

    //初始化界面和toolbae添加其他的操作
    protected abstract void initView(Bundle savedInstanceState,Toolbar toolbar);

    //返回fragment
    protected abstract BaseFragment initFragment();

    //初始化Component
    protected abstract void setComponent();


    //dialog的点击事件
     class FloatDialogClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.float_tuijian:
                    sendReceiver(2);
                    break;
                case R.id.float_rnum:
                    sendReceiver(3);
                    break;
                case R.id.float_comment:
                    sendReceiver(5);
                    break;
                case R.id.float_zan:
                    sendReceiver(4);
                    break;
                default:
                    break;
            }

            dialog.dismiss();
        }
    }

    //广播，发给新闻列表页，改变排序类型
    private void sendReceiver(Integer type){
        Intent intent=new Intent();
        intent.setAction(NEWSFRAGMENT_TYPE_ACTION);
        intent.putExtra(NEWSFRAGMENT_TYPE_ACTION,type);
        sendBroadcast(intent);
    }



}

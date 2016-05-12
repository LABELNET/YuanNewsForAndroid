package cn.edu.hpu.yuan.yuannews.news.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.base.BaseActivity;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.data.NewsAPI;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.UserVo;
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
    protected void initHeadView(View view) {
        CircleImageView circleImageView= (CircleImageView) view.findViewById(R.id.profile_image);
        TextView navigation_name= (TextView) view.findViewById(R.id.navigation_name);
        if(BaseApplication.newsAPIShared.getSharedUserID()!=0){
            //已经登陆
            UserVo userVo=BaseApplication.newsAPIShared.getSharedUser();
            navigation_name.setText(userVo.getNick());
            Glide.with(this)
                    .load(NewsAPI.BASE_IMAGE_URL+userVo.getHead())
                    .placeholder(R.mipmap.user_head)
                    .error(R.mipmap.user_head)
                    .into(circleImageView);
            //点击头像进入个人信息
        }else{
            navigation_name.setText("未登陆?点击头像登陆");
            circleImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //重新更新headview
    }


}

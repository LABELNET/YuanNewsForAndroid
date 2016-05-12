package cn.edu.hpu.yuan.yuannews.user.center;

import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.base.NormalBaseActivity;

/**
 * Created by yuan on 16-5-12.
 */
public class CenterActivity extends NormalBaseActivity{


    private final String SHRAED_MOREN_USER_IFNO="No Data";

    @Inject
    protected CenterFragment centerFragment;

    @Override
    protected void initView() {

    }

    @Override
    protected void setComponet() {
       DaggerCenterComponent.builder()
               .centerModule(new CenterModule())
               .build()
               .injectCenterActivity(this);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        String userNick = BaseApplication.newsAPIShared.getSharedUserNick();
        if(SHRAED_MOREN_USER_IFNO.equals(userNick)){
            setTitle("个人中心");
        }else{
            setTitle(userNick);
        }
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected BaseFragment initFragment() {
        return centerFragment;
    }

}

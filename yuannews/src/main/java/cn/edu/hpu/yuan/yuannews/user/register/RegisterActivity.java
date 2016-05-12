package cn.edu.hpu.yuan.yuannews.user.register;

import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.base.NormalBaseActivity;

/**
 * Created by yuan on 16-5-12.
 */
public class RegisterActivity extends NormalBaseActivity{

    @Inject
    protected RegisterFragment registerFragment;

    @Override
    protected void initView() {

    }
    @Override
    protected void setComponet() {

        DaggerRegisterComponent
                .builder()
                .registerModule(new RegisterModule())
                .build()
                .injectRegisterActivity(this);

    }
    @Override
    protected void initToolbar(Toolbar toolbar) {
        setTitle("用户注册");
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
        return registerFragment;
    }
}

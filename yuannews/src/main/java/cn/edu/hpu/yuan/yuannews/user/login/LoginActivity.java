package cn.edu.hpu.yuan.yuannews.user.login;

import android.support.v7.widget.Toolbar;
import android.view.View;
import javax.inject.Inject;
import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.base.NormalBaseActivity;

/**
 * Created by yuan on 16-5-12.
 * 用户登陆页面
 */
public class LoginActivity extends NormalBaseActivity {

    @Inject
    protected LoginFragment loginFragment;

    @Override
    protected void initView() {
    }
    @Override
    protected void setComponet() {
        DaggerLoginComponent
                .builder()
                .loginModule(new LoginModule())
                .build()
                .injectLoginActivity(this);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        setTitle("用户登陆");
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
        return loginFragment;
    }


}

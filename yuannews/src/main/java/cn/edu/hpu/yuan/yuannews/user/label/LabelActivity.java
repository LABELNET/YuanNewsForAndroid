package cn.edu.hpu.yuan.yuannews.user.label;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.base.NormalBaseActivity;

/**
 * Created by yuan on 16-5-12.
 */
public class LabelActivity extends NormalBaseActivity{

    @Inject
    protected LabelFragment labelFragment;

    @Override
    protected void initView() {
    }
    @Override
    protected void setComponet() {
       DaggerLabelComponent
               .builder()
               .labelModule(new LabelModule())
               .build()
               .injectLabelActivity(this);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        setTitle("兴趣标签管理");
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
        Bundle tasteVos = getIntent().getExtras();
        labelFragment.setArguments(tasteVos);
        return labelFragment;
    }

}

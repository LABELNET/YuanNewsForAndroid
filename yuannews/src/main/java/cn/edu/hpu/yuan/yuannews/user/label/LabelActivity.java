package cn.edu.hpu.yuan.yuannews.user.label;

import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

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

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {

    }

    @Override
    protected BaseFragment initFragment() {
        return labelFragment;
    }


}

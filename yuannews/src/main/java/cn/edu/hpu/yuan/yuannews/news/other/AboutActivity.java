package cn.edu.hpu.yuan.yuannews.news.other;

import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.base.NormalBaseActivity;

/**
 * Created by yuan on 16-5-13.
 */
public class AboutActivity extends NormalBaseActivity{

    @Override
    protected void initView() {
    }

    @Override
    protected void setComponet() {

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        setTitle("关于");
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
        return new AboutFragment();
    }
}

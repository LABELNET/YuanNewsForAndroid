package cn.edu.hpu.yuan.yuannews.news.labels;

import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.base.NormalBaseActivity;

/**
 * Created by yuan on 16-5-13.
 * 所有标签页，分页加载实现
 */
public class LabelsActivity extends NormalBaseActivity{

    @Inject
    protected LabelsFragment labelsFragment;

    @Override
    protected void initView() {
    }

    @Override
    protected void setComponet() {
        DaggerLabelsComponent
                .builder()
                .labelsModule(new LabelsModule())
                .build()
                .injectLabelsActivity(this);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        setTitle("关注兴趣标签");
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
        return labelsFragment;
    }
}

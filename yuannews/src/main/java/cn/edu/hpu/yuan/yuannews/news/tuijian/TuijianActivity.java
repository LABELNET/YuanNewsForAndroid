package cn.edu.hpu.yuan.yuannews.news.tuijian;

import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.base.NormalBaseActivity;

/**
 * Created by yuan on 16-5-13.
 */
public class TuijianActivity extends NormalBaseActivity{

    @Inject
    protected TuijianFragment tuijianFragment;

    @Override
    protected void initView() {

    }

    @Override
    protected void setComponet() {
        DaggerTuijianComponent
                .builder()
                .tuijianModule(new TuijianModule())
                .build()
                .injectTuijianActivity(this);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        setTitle("推荐新闻");
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
        return tuijianFragment;
    }


}

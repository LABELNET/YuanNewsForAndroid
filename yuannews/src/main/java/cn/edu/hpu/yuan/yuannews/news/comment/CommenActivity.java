package cn.edu.hpu.yuan.yuannews.news.comment;

import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.base.NormalBaseActivity;

/**
 * Created by yuan on 16-5-13.
 */
public class CommenActivity extends NormalBaseActivity{

    @Inject
    protected CommentFragment commentFragment;

    @Override
    protected void initView() {

    }

    @Override
    protected void setComponet() {

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        setTitle("新闻评论列表");
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
        return commentFragment;
    }
}

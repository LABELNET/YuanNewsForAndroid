package cn.edu.hpu.yuan.yuannews.main.base;

import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.edu.hpu.yuan.yuannews.R;

/**
 * Created by yuan on 16-5-11.
 */
public abstract class NormalBackActivity extends BaseActivity{


    @Override
    protected void initToolbar(Toolbar toolbar) {
        super.initToolbar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

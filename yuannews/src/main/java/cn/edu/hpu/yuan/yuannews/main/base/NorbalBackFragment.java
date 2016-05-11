package cn.edu.hpu.yuan.yuannews.main.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by yuan on 16-5-11.
 */
public abstract class NorbalBackFragment extends BaseFragment{

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view,savedInstanceState);
    }

    protected abstract void initView(View view, Bundle savedInstanceState);


}

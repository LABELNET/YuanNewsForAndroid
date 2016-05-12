package cn.edu.hpu.yuan.yuannews.user.label;

import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;

/**
 * Created by yuan on 16-5-12.
 */
public class LabelPresenter implements LabelContancts.LabelContanctsPresenter{

    private LabelContancts.LabelContanctsView labelContanctsView;

    private int uid;

    public LabelPresenter(LabelContancts.LabelContanctsView labelContanctsView) {
        this.labelContanctsView = labelContanctsView;
        uid= BaseApplication.newsAPIShared.getSharedUserID();
    }

    @Override
    public int postDeleteLabel(Integer tid) {

        return 0;
    }

    @Override
    public int postAddLabel( String label) {

        return 0;
    }
}

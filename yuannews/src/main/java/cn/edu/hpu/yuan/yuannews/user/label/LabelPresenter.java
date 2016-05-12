package cn.edu.hpu.yuan.yuannews.user.label;

/**
 * Created by yuan on 16-5-12.
 */
public class LabelPresenter implements LabelContancts.LabelContanctsPresenter{

    private LabelContancts.LabelContanctsView labelContanctsView;

    public LabelPresenter(LabelContancts.LabelContanctsView labelContanctsView) {
        this.labelContanctsView = labelContanctsView;
    }

    @Override
    public void postDeleteLabel(Integer tid) {

    }

    @Override
    public void postAddLabel(Integer id, String label) {

    }
}

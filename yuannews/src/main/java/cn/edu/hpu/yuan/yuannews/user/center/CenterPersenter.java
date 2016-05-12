package cn.edu.hpu.yuan.yuannews.user.center;

/**
 * Created by yuan on 16-5-12.
 */
public class CenterPersenter implements CenterContancts.CenterContanctsPresenter {

    private CenterContancts.CenterContanctsView centerContanctsView;


    public CenterPersenter(CenterContancts.CenterContanctsView centerContanctsView) {
        this.centerContanctsView = centerContanctsView;
    }

    @Override
    public void getUserDetailData() {

    }

    @Override
    public void getUserAllLabels() {

    }
}

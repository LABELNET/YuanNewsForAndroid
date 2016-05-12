package cn.edu.hpu.yuan.yuannews.news.labels;

/**
 * Created by yuan on 16-5-13.
 */
public class LabelsPresenter implements LabelsContancts.LabelsContanctsPresenter{

    private LabelsContancts.LabelsContanctsView labelsContanctsView;

    public LabelsPresenter(LabelsContancts.LabelsContanctsView labelsContanctsView) {
        this.labelsContanctsView = labelsContanctsView;
    }
    @Override
    public void getLabelsPageData(int p) {

    }
}

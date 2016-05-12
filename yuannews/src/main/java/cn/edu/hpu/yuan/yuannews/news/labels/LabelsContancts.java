package cn.edu.hpu.yuan.yuannews.news.labels;

/**
 * Created by yuan on 16-5-13.
 */
public interface LabelsContancts {

    interface LabelsContanctsPresenter{
        void getLabelsPageData(int p);
    }

    interface LabelsContanctsView{
        void showDialog();
        void showLabelsData();
    }

}

package cn.edu.hpu.yuan.yuannews.news.labels;

import java.util.List;

/**
 * Created by yuan on 16-5-13.
 */
public interface LabelsContancts {

    interface LabelsContanctsPresenter{
        void initgetTasteData();
        void nextgetTasteData();
    }

    interface LabelsContanctsView{
        void showDialog();
        void showLabelsData(List<String> tastes);
        void showErrorMsg(String msg);
    }

}

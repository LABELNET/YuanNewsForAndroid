package cn.edu.hpu.yuan.yuannews.user.label;

/**
 * Created by yuan on 16-5-12.
 */
public interface LabelContancts {

    interface LabelContanctsPresenter{

        void postDeleteLabel(Integer tid);

        void postAddLabel(String label);

    }

    interface LabelContanctsView{

        void showDialog();

        void showMsg(String msg);

        void deleteSuccess();

        void addSuccess();

    }

}

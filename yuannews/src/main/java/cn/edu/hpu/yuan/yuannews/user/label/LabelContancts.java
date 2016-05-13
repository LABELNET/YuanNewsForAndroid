package cn.edu.hpu.yuan.yuannews.user.label;

import java.util.List;

import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.TasteVo;

/**
 * Created by yuan on 16-5-12.
 */
public interface LabelContancts {

    interface LabelContanctsPresenter{

        void postDeleteLabel(Integer tid);

        void postAddLabel(String label);

        void postAllLabel();

    }

    interface LabelContanctsView{

        void showDialog();

        void showMsg(String msg);

        void deleteSuccess();

        void addSuccess();

        void showAlldata(List<TasteVo> tasteVos);

    }

}

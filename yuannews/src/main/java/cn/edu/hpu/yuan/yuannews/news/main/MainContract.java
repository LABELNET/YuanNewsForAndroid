package cn.edu.hpu.yuan.yuannews.news.main;

import android.support.v4.app.Fragment;

import java.util.List;

import cn.edu.hpu.yuan.yuannews.main.base.BasePresenter;
import cn.edu.hpu.yuan.yuannews.main.base.BaseView;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.CSCustom;

/**
 * Created by yuan on 16-5-10.
 * 包括Presenter和View接口
 */
public interface MainContract {


    interface MainPresenter extends BasePresenter{

        void loadDataRequest();

    }

    interface MainView extends BaseView<MainPresenter>{

        void showLoadDialog(); //加载数据

        void showLoadData(List<Fragment> fragments,List<String> titles);//返回数据到主界面

        void finishLoadDialog(); //停止加载
    }

}

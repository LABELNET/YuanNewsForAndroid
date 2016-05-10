package cn.edu.hpu.yuan.yuannews.news.main;

import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;

/**
 * Created by yuan on 16-5-10.
 * Presenter具体实现类
 */
public class MainPresenter implements MainContract.MainPresenter{


    private MainContract.MainView mainView;

    public MainPresenter(MainContract.MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void loadDataRequest() {

//        BaseApplication.newsAPIService

        //加载数据操作
    }


}

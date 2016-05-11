package cn.edu.hpu.yuan.yuannews.news.newslist;

import java.util.ArrayList;

import cn.edu.hpu.yuan.yuannews.main.base.BasePresenter;
import cn.edu.hpu.yuan.yuannews.main.base.BaseView;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;

/**
 * Created by yuan on 16-5-9.
 * 新闻列表类
 * 1.Presenter接口
 * 2.View接口
 */
public interface NewsContract {

    interface Presenter extends BasePresenter {

        //初始化
         void initNewsListData(String title,int type,int nType);

        //下一页
         void nextNewsListData(String title,int type,int nType);

    }

    interface View extends BaseView<Presenter> {

        void showDialog();

        void showNewsList(ArrayList<NewsCustom> newsCustoms);

        void showInitNewsList(); //第一页

        void dismssDiolog();

        void showNotData();//完成

        void showSnackBar(String msg);
    }

}

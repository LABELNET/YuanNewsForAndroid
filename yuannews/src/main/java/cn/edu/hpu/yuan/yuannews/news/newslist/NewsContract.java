package cn.edu.hpu.yuan.yuannews.news.newslist;

import java.util.ArrayList;

import cn.edu.hpu.yuan.yuannews.main.BasePresenter;
import cn.edu.hpu.yuan.yuannews.main.BaseView;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;

/**
 * Created by yuan on 16-5-9.
 * 新闻列表类
 * 1.Presenter接口
 * 2.View接口
 */
public interface NewsContract {

    interface Presenter extends BasePresenter{
         void showNewsListData(int p,int type,int nType);
    }

    interface View extends BaseView<Presenter>{
        void showToast();

        void showDialog();

        void showNewsList(ArrayList<NewsCustom> newsCustoms);

        void dismssDiolog();
        void showCompletion();
    }

}

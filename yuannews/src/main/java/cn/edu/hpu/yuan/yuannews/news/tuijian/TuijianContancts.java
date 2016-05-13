package cn.edu.hpu.yuan.yuannews.news.tuijian;

import java.util.ArrayList;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;

/**
 * Created by yuan on 16-5-13.
 */
public interface TuijianContancts {

    interface TuijianContanctsPresenter{

        void initNewsListData(int type);

        void nextNewsListData(int type);

    }

    interface TuijianContanctsView{

        void showDialog();

        void showErrorMsg(String msg);

        void showNewsData(ArrayList<NewsCustom> newsCustoms);

        void showNoData();

    }

}

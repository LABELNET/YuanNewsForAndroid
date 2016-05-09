package cn.edu.hpu.yuan.yuannews.main.news;

import cn.edu.hpu.yuan.yuannews.main.BasePresenter;
import cn.edu.hpu.yuan.yuannews.main.BaseView;

/**
 * Created by yuan on 16-5-9.
 * 新闻列表类
 * 1.Presenter接口
 * 2.View接口
 */
public interface NewsContract {

    interface Presenter extends BasePresenter{

    }

    interface View extends BaseView<Presenter>{

    }

}

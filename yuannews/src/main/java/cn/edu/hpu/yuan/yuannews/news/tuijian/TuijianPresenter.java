package cn.edu.hpu.yuan.yuannews.news.tuijian;

/**
 * Created by yuan on 16-5-13.
 */
public class TuijianPresenter implements TuijianContancts.TuijianContanctsPresenter{


    private TuijianContancts.TuijianContanctsView tuijianContanctsView;

    public TuijianPresenter(TuijianContancts.TuijianContanctsView tuijianContanctsView) {
        this.tuijianContanctsView = tuijianContanctsView;
    }

    @Override
    public void initNewsListData(int type) {

    }

    @Override
    public void nextNewsListData(int type) {

    }
}

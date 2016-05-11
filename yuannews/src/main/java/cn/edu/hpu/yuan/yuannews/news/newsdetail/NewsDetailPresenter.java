package cn.edu.hpu.yuan.yuannews.news.newsdetail;

/**
 * Created by yuan on 16-5-11.
 */
public class NewsDetailPresenter implements NewsDetailContancts.NewsDetailPresenter{


    private NewsDetailContancts.NewsDetailView newsDetailView;

    public NewsDetailPresenter(NewsDetailContancts.NewsDetailView newsDetailView) {
        this.newsDetailView = newsDetailView;
    }

    @Override
    public void getNewsDetailData() {

    }

    @Override
    public void updateNewsZan() {

    }

    @Override
    public void getNewsZansHeadData() {

    }
}

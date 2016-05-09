package cn.edu.hpu.yuan.yuannews.main.news;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuancore.util.LogUtil;
import cn.edu.hpu.yuan.yuannews.main.data.model.DataBean;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;
import cn.edu.hpu.yuan.yuannews.main.data.remote.NewsAPIService;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by yuan on 16-5-9.
 */
public class MewsPresenter implements NewsContract.Presenter{

    private NewsAPIService newsAPIService;
    private NewsContract.View newsPresenter;

    @Inject
    public MewsPresenter(NewsAPIService newsAPIService, NewsContract.View newsPresenter) {
        this.newsAPIService = newsAPIService;
        this.newsPresenter = newsPresenter;
    }

    @Override
    public void showNewsListData(int p, int type, int nType) {

        newsPresenter.showDialog();
        Call<DataBean<ArrayList<NewsCustom>>> newsList = newsAPIService.getNewsList(type, 20, p, nType);
        try {
            Response<DataBean<ArrayList<NewsCustom>>> datas = newsList.execute();
            if(datas.isSuccessful()){
                newsPresenter.dismssDiolog();
                DataBean<ArrayList<NewsCustom>> data = datas.body();
                LogUtil.v(data.toString());
                if(data.getCode()==0){
                    newsPresenter.showCompletion();
                    newsPresenter.showNewsList(data.getData());
                }else{
                    newsPresenter.showToast();
                }
            }else{
                newsPresenter.dismssDiolog();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

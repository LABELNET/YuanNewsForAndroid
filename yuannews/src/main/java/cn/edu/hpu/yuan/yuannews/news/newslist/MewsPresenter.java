package cn.edu.hpu.yuan.yuannews.news.newslist;

import java.util.ArrayList;
import java.util.Map;

import cn.edu.hpu.yuan.yuancore.util.LogUtil;
import cn.edu.hpu.yuan.yuannews.main.data.NewsAPIMapUtil;
import cn.edu.hpu.yuan.yuannews.main.data.model.DataBean;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;
import cn.edu.hpu.yuan.yuannews.main.data.remote.NewsAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuan on 16-5-9.
 */
public class MewsPresenter implements NewsContract.Presenter{

    private NewsAPIService newsAPIService;
    private NewsContract.View newsPresenter;

    public MewsPresenter(NewsAPIService newsAPIService, NewsContract.View newsPresenter) {
        this.newsAPIService = newsAPIService;
        this.newsPresenter = newsPresenter;
    }

    @Override
    public void showNewsListData(int p, int type, int nType) {

//            newsPresenter.showDialog();
             Map<String, Integer> options = NewsAPIMapUtil.getNewsListMap(type, p, nType);
             Call<DataBean<ArrayList<NewsCustom>>> newsList = newsAPIService.getNewsList(options);
             newsList.enqueue(new Callback<DataBean<ArrayList<NewsCustom>>>(){

                @Override
                public void onResponse(Call<DataBean<ArrayList<NewsCustom>>> call, Response<DataBean<ArrayList<NewsCustom>>> response) {
                    newsPresenter.dismssDiolog();
                    DataBean<ArrayList<NewsCustom>> data = response.body();
                    if(data!=null){
                        LogUtil.v(" data "+data.toString());
                        if(data.getCode()==0){
                            newsPresenter.showCompletion();
                            newsPresenter.showNewsList(data.getData());
                        }else{
                            newsPresenter.showToast();
                        }
                    }else{
                        LogUtil.v(" data is null");
                    }
                }

                @Override
                public void onFailure(Call<DataBean<ArrayList<NewsCustom>>> call, Throwable t) {
                    LogUtil.v("原民卓"+call);
                    newsPresenter.dismssDiolog();
                    t.printStackTrace();
                }
            });

    }

}

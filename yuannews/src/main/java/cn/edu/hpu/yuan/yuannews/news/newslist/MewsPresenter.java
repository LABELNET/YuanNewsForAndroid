package cn.edu.hpu.yuan.yuannews.news.newslist;

import java.util.ArrayList;
import java.util.Map;

import cn.edu.hpu.yuan.yuancore.util.LogUtil;
import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.data.NewsAPIMapUtil;
import cn.edu.hpu.yuan.yuannews.main.data.model.DataBean;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;
import cn.edu.hpu.yuan.yuannews.main.data.remote.NewsAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuan on 16-5-9.
 * 新闻列表Presenter
 */
public class MewsPresenter implements NewsContract.Presenter{

    private NewsContract.View newsPresenter;

    public MewsPresenter(NewsContract.View newsPresenter) {
        this.newsPresenter = newsPresenter;
    }

    private int p=1;

    private void showNewsListData(String title,int type, int nType) {
             newsPresenter.showDialog();
             Map<String, String> options = NewsAPIMapUtil.getNewsListMap(title,type, p, nType);
             Call<DataBean<ArrayList<NewsCustom>>> newsList = BaseApplication.newsAPIService.getNewsList(options);
             newsList.enqueue(new Callback<DataBean<ArrayList<NewsCustom>>>(){

                @Override
                public void onResponse(Call<DataBean<ArrayList<NewsCustom>>> call, Response<DataBean<ArrayList<NewsCustom>>> response) {
                    newsPresenter.dismssDiolog();
                    DataBean<ArrayList<NewsCustom>> data = response.body();
                    if(data!=null){
                        LogUtil.v(" data "+data.toString());
                        if(data.getCode()==0){
                            newsPresenter.showNewsList(data.getData());
                        }else{
                            newsPresenter.showSnackBar(data.getMsg());
                        }
                    }else{
                        LogUtil.v(" data is null");
                        newsPresenter.showNotData();
                    }
                }

                @Override
                public void onFailure(Call<DataBean<ArrayList<NewsCustom>>> call, Throwable t) {
                    newsPresenter.dismssDiolog();
                    t.printStackTrace();
                }
            });

    }

    @Override
    public void initNewsListData(String title, int type, int nType) {
        newsPresenter.showInitNewsList();
        p=1;
        showNewsListData(title,type,nType);
    }

    @Override
    public synchronized void nextNewsListData(String title, int type, int nType) {
        p+=1;
        showNewsListData(title,type,nType);
    }
}

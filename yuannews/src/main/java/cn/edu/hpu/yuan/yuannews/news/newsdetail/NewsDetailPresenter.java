package cn.edu.hpu.yuan.yuannews.news.newsdetail;

import java.util.ArrayList;

import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.data.model.DataBean;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.LikedVo;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuan on 16-5-11.
 */
public class NewsDetailPresenter implements NewsDetailContancts.NewsDetailPresenter{


    private NewsDetailContancts.NewsDetailView newsDetailView;

    public NewsDetailPresenter(NewsDetailContancts.NewsDetailView newsDetailView) {
        this.newsDetailView = newsDetailView;
    }

    @Override
    public void getNewsDetailData(int nid) {
        newsDetailView.showDialog();
        BaseApplication.newsAPIService.getNewsDetail(nid).enqueue(new Callback<DataBean<NewsCustom>>() {
            @Override
            public void onResponse(Call<DataBean<NewsCustom>> call, Response<DataBean<NewsCustom>> response) {
                newsDetailView.dimssDialog();
                if(response.isSuccessful()){
                    DataBean<NewsCustom> body = response.body();
                    if(body.getCode()==0){
                        NewsCustom data = body.getData();
                        if(data!=null){
                            newsDetailView.getNewsDetail(data);
                        }else{
                            newsDetailView.loadError();
                        }
                    }else{
                        newsDetailView.loadError();
                    }
                }else{
                    newsDetailView.loadError();
                }
            }

            @Override
            public void onFailure(Call<DataBean<NewsCustom>> call, Throwable t) {
                newsDetailView.loadError();
            }
        });
    }

    @Override
    public void updateNewsZan(int nid, int status) {
        //TODO 读取uid,进行操作

    }


    @Override
    public void getNewsZansHeadData(int nid) {
          BaseApplication.newsAPIService.getNewsLikedVos(nid).enqueue(new Callback<DataBean<ArrayList<LikedVo>>>() {
              @Override
              public void onResponse(Call<DataBean<ArrayList<LikedVo>>> call, Response<DataBean<ArrayList<LikedVo>>> response) {
                  if(response.isSuccessful()){
                      DataBean<ArrayList<LikedVo>> body = response.body();
                      if(body.getCode()==0){
                          newsDetailView.getZansHead(body.getData());
                      }else{
                          newsDetailView.getZansHeadError();
                      }
                  }else{
                      newsDetailView.getZansHeadError();
                  }
              }

              @Override
              public void onFailure(Call<DataBean<ArrayList<LikedVo>>> call, Throwable t) {
                  newsDetailView.getZansHeadError();
              }
          });
    }

    @Override
    public void getNewsZanStatus(int nid) {

    }

}

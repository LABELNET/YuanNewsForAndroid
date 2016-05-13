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

    private int uid;

    public NewsDetailPresenter(NewsDetailContancts.NewsDetailView newsDetailView) {
        this.newsDetailView = newsDetailView;
        uid=BaseApplication.newsAPIShared.getSharedUserID();
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
        BaseApplication.newsAPIService.postZan(uid,nid,status).enqueue(new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                if(response.isSuccessful()){
                    if(response.body().getCode()==0){
                        newsDetailView.updateZanSuccess();
                    }else {
                        newsDetailView.updateZanError();
                    }
                }else {
                    newsDetailView.updateZanError();
                }
            }

            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {
                newsDetailView.updateZanError();
            }
        });

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
        BaseApplication.newsAPIService.getNewsUserZanStatus(uid,nid).enqueue(new Callback<DataBean<Integer>>() {
            @Override
            public void onResponse(Call<DataBean<Integer>> call, Response<DataBean<Integer>> response) {
                if(response.isSuccessful()){
                    if(response.body().getCode()==0){
                        newsDetailView.getZanStatus(response.body().getData());
                    }else{
                        newsDetailView.loadError();
                    }
                }else{
                    newsDetailView.loadError();
                }
            }

            @Override
            public void onFailure(Call<DataBean<Integer>> call, Throwable t) {
                newsDetailView.loadError();
            }
        });
    }

}

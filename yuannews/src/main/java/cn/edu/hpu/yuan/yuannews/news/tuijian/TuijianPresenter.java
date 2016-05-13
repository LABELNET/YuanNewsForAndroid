package cn.edu.hpu.yuan.yuannews.news.tuijian;

import java.util.ArrayList;

import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.data.model.DataBean;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuan on 16-5-13.
 */
public class TuijianPresenter implements TuijianContancts.TuijianContanctsPresenter{


    private TuijianContancts.TuijianContanctsView tuijianContanctsView;

    public TuijianPresenter(TuijianContancts.TuijianContanctsView tuijianContanctsView) {
        this.tuijianContanctsView = tuijianContanctsView;
    }

    private int p=1;

    private void getTuijianData(int p,int type){
        tuijianContanctsView.showDialog();
        int uid = BaseApplication.newsAPIShared.getSharedUserID();
        BaseApplication.newsAPIService.getTuijianNews(uid,20,type,p).enqueue(new Callback<DataBean<ArrayList<NewsCustom>>>() {
            @Override
            public void onResponse(Call<DataBean<ArrayList<NewsCustom>>> call, Response<DataBean<ArrayList<NewsCustom>>> response) {

                if(response.isSuccessful()){
                    DataBean<ArrayList<NewsCustom>> body = response.body();
                    if(body.getCode()==0){
                        ArrayList<NewsCustom> data = body.getData();
                        if(data.size()==0){
                            tuijianContanctsView.showNoData();
                        }else{
                            tuijianContanctsView.showNewsData(data);
                        }

                    }else{
                        tuijianContanctsView.showErrorMsg(body.getMsg());
                    }

                }else{
                    tuijianContanctsView.showErrorMsg("网络不稳定");
                }

            }

            @Override
            public void onFailure(Call<DataBean<ArrayList<NewsCustom>>> call, Throwable t) {
                tuijianContanctsView.showErrorMsg("网络不稳定");
            }
        });

    }

    @Override
    public void initNewsListData(int type) {
        p=1;
        getTuijianData(p,type);
    }

    @Override
    public void nextNewsListData(int type) {
        p=p+1;
        getTuijianData(p,type);
    }
}

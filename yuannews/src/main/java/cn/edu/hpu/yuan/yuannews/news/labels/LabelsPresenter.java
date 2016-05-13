package cn.edu.hpu.yuan.yuannews.news.labels;

import java.util.List;

import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.data.model.DataBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuan on 16-5-13.
 */
public class LabelsPresenter implements LabelsContancts.LabelsContanctsPresenter{

    private LabelsContancts.LabelsContanctsView labelsContanctsView;


    private int uid;
    private int p;

    public LabelsPresenter(LabelsContancts.LabelsContanctsView labelsContanctsView) {
        this.labelsContanctsView = labelsContanctsView;
        uid= BaseApplication.newsAPIShared.getSharedUserID();
        p=0;
    }

    public void getLabelsPageData(int p) {
       labelsContanctsView.showDialog();
       BaseApplication.newsAPIService.getNewsTaste(uid,20,p).enqueue(new Callback<DataBean<List<String>>>() {
           @Override
           public void onResponse(Call<DataBean<List<String>>> call, Response<DataBean<List<String>>> response) {
               if(response.isSuccessful()){
                   if(response.body().getCode()==0){
                       labelsContanctsView.showLabelsData(response.body().getData());
                   }else{
                       labelsContanctsView.showErrorMsg(response.body().getMsg());
                   }
               }else{
                   labelsContanctsView.showErrorMsg("网络不稳定");
               }
           }

           @Override
           public void onFailure(Call<DataBean<List<String>>> call, Throwable t) {
               labelsContanctsView.showErrorMsg("网络不稳定");
           }
       });
    }

    @Override
    public void initgetTasteData() {
        p=1;
        getLabelsPageData(1);

    }

    @Override
    public void nextgetTasteData() {
         p+=1;
        getLabelsPageData(p);
    }
}

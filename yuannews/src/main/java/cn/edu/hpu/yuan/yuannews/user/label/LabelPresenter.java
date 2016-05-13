package cn.edu.hpu.yuan.yuannews.user.label;

import java.util.List;

import cn.edu.hpu.yuan.yuancore.util.LogUtil;
import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.data.model.DataBean;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.TasteVo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuan on 16-5-12.
 */
public class LabelPresenter implements LabelContancts.LabelContanctsPresenter{

    private LabelContancts.LabelContanctsView labelContanctsView;

    private int uid;

    private int result=-1;

    public LabelPresenter(LabelContancts.LabelContanctsView labelContanctsView) {
        this.labelContanctsView = labelContanctsView;
        uid= BaseApplication.newsAPIShared.getSharedUserID();
    }

    @Override
    public void postDeleteLabel(Integer tid) {
        labelContanctsView.showDialog();
         BaseApplication.newsAPIService.postDeleteLabel(tid).enqueue(new Callback<DataBean>() {
             @Override
             public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                 if(response.isSuccessful()){
                     LogUtil.v(response.body()+"");
                     if(response.body().getCode()==0){
                         labelContanctsView.deleteSuccess();
                     }else{
                         labelContanctsView.showMsg(response.body().getMsg());
                     }
                 }else{
                     labelContanctsView.showMsg("网络不稳定");
                 }
             }

             @Override
             public void onFailure(Call<DataBean> call, Throwable t) {
                 labelContanctsView.showMsg("网络不稳定");
             }
         });
    }

    @Override
    public void postAddLabel( String label) {
        labelContanctsView.showDialog();
        BaseApplication.newsAPIService.postAddLabel(uid,label).enqueue(new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                if(response.isSuccessful()){

                    if(response.body().getCode()==0){
                        labelContanctsView.addSuccess();
                    }else{
                        labelContanctsView.showMsg(response.body().getMsg());
                    }

                }else{
                    labelContanctsView.showMsg("网络不稳定");
                }
            }
            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {
                labelContanctsView.showMsg("网络不稳定");
            }
        });

    }

    @Override
    public void postAllLabel() {
        Integer id = BaseApplication.newsAPIShared.getSharedUserID();
        BaseApplication.newsAPIService.postTasteVos(id).enqueue(new Callback<DataBean<List<TasteVo>>>() {
            @Override
            public void onResponse(Call<DataBean<List<TasteVo>>> call, Response<DataBean<List<TasteVo>>> response) {

                if(response.isSuccessful()){
                    if(response.body().getCode()==0){
                        labelContanctsView.showAlldata(response.body().getData());
                    }else{
                        labelContanctsView.showMsg(response.body().getMsg());
                    }
                }else {
                    labelContanctsView.showMsg("网络不稳定");
                }

            }

            @Override
            public void onFailure(Call<DataBean<List<TasteVo>>> call, Throwable t) {
                labelContanctsView.showMsg("网络不稳定");
            }
        });
    }
}

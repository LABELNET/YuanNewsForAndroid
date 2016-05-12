package cn.edu.hpu.yuan.yuannews.user.label;

import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.data.model.DataBean;
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
}

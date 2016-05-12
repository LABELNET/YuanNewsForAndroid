package cn.edu.hpu.yuan.yuannews.user.center;

import java.util.List;

import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.data.model.DataBean;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.TasteVo;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.UserVo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuan on 16-5-12.
 */
public class CenterPersenter implements CenterContancts.CenterContanctsPresenter {

    private CenterContancts.CenterContanctsView centerContanctsView;


    public CenterPersenter(CenterContancts.CenterContanctsView centerContanctsView) {
        this.centerContanctsView = centerContanctsView;
    }

    @Override
    public void getUserDetailData() {
        centerContanctsView.showDialog();
        Integer id = BaseApplication.newsAPIShared.getSharedUserID();
        BaseApplication.newsAPIService.postUserDetail(id).enqueue(new Callback<DataBean<UserVo>>() {
            @Override
            public void onResponse(Call<DataBean<UserVo>> call, Response<DataBean<UserVo>> response) {
                if(response.isSuccessful()){
                    DataBean<UserVo> body = response.body();
                    if(body.getCode()==0){
                        UserVo data = body.getData();
                        if(data!=null) {
                            centerContanctsView.showUserDetail(data);
                        }else{
                            centerContanctsView.showError("数据加载失败");
                        }
                    }else{
                        centerContanctsView.showError(body.getMsg());
                    }

                }else{
                    centerContanctsView.showError("网络不稳定");
                }
            }

            @Override
            public void onFailure(Call<DataBean<UserVo>> call, Throwable t) {
                centerContanctsView.showError("网络不稳定");
            }
        });
    }

    @Override
    public void getUserAllLabels() {
        Integer id = BaseApplication.newsAPIShared.getSharedUserID();
        BaseApplication.newsAPIService.postTasteVos(id).enqueue(new Callback<DataBean<List<TasteVo>>>() {
            @Override
            public void onResponse(Call<DataBean<List<TasteVo>>> call, Response<DataBean<List<TasteVo>>> response) {

                if(response.isSuccessful()){
                    if(response.body().getCode()==0){
                        centerContanctsView.showAllLabels(response.body().getData());
                    }else{
                        centerContanctsView.showError(response.body().getMsg());
                    }
                }else {
                    centerContanctsView.showError("网络不稳定");
                }

            }

            @Override
            public void onFailure(Call<DataBean<List<TasteVo>>> call, Throwable t) {
                centerContanctsView.showError("网络不稳定");
            }
        });
    }
}

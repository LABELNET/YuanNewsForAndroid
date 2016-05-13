package cn.edu.hpu.yuan.yuannews.news.comment;

import java.util.ArrayList;

import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.data.model.DataBean;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.CommentJo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuan on 16-5-13.
 */
public class CommemPresenter implements CommenContancts.CommenContanctsPresenter {


    private CommenContancts.CommenContanctsView commenContanctsView;

    public CommemPresenter(CommenContancts.CommenContanctsView commenContanctsView) {
        this.commenContanctsView = commenContanctsView;
    }

    private int p=1;

    public void postComments(int nid,int p){
        commenContanctsView.showDialog();
        BaseApplication.newsAPIService.getCommentIfo(p,20,nid).enqueue(new Callback<DataBean<ArrayList<CommentJo>>>() {
            @Override
            public void onResponse(Call<DataBean<ArrayList<CommentJo>>> call, Response<DataBean<ArrayList<CommentJo>>> response) {
                if(response.isSuccessful()){
                    if(response.body().getCode()==0){
                        commenContanctsView.showComentData(response.body().getData());
                    }else{
                        commenContanctsView.showErrorMsg(response.body().getMsg());
                    }
                }else{
                    commenContanctsView.showErrorMsg("网络不稳定");
                }
            }

            @Override
            public void onFailure(Call<DataBean<ArrayList<CommentJo>>> call, Throwable t) {
                commenContanctsView.showErrorMsg("网络不稳定");
            }
        });
    }


    @Override
    public void initLoadCommentData(int nid) {
        //加载第一页
        p=1;
        postComments(nid,p);
    }

    @Override
    public void nextLoadCommentData(int nid) {
        //加载下一页
        p+=1;
        postComments(nid,p);
    }

    @Override
    public void userCommenData(String content, Integer nid) {
        //用户评论
        int uid=BaseApplication.newsAPIShared.getSharedUserID();
        commenContanctsView.showDialog();
        BaseApplication.newsAPIService.postComment(uid,nid,content).enqueue(new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                if(response.isSuccessful()){
                    if(response.body().getCode()==0){
                        commenContanctsView.showSuccess();
                    }else{
                        commenContanctsView.showErrorMsg(response.body().getMsg());
                    }
                }else{
                    commenContanctsView.showErrorMsg("网络不稳定");
                }
            }
            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {
                commenContanctsView.showErrorMsg("网络不稳定");
            }
        });
    }

}

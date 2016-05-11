package cn.edu.hpu.yuan.yuannews.news.main;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hpu.yuan.yuancore.util.LogUtil;
import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.data.model.DataBean;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.CateVo;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.SourceVo;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.CSCustom;
import cn.edu.hpu.yuan.yuannews.news.newslist.NewsFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuan on 16-5-10.
 * Presenter具体实现类
 */
public class MainPresenter implements MainContract.MainPresenter{


    private MainContract.MainView mainView;

    public MainPresenter(MainContract.MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void loadDataRequest() {
        //加载数据操作
        mainView.showLoadDialog();
        BaseApplication.newsAPIService.getCateSource().enqueue(new Callback<DataBean<CSCustom>>() {
            @Override
            public void onResponse(Call<DataBean<CSCustom>> call, Response<DataBean<CSCustom>> response) {
                mainView.finishLoadDialog();
                if(response.isSuccessful()){
                    if(response.body().getCode()==0){

                        List<Fragment> fragments=new ArrayList<>();
                        List<String> titles=new ArrayList<>();

                        for(CateVo cateVo:response.body().getData().getCateVos()){
                            //分类的nType=7
                            fragments.add(NewsFragment.getNewsFragmentInstance(cateVo.getContent(),7));
                            titles.add(cateVo.getContent());
                        }

                        for (SourceVo sourceVo:response.body().getData().getSourceVos()){
                            //来源的nType=8
                            fragments.add(NewsFragment.getNewsFragmentInstance(sourceVo.getSource(),8));
                            titles.add(sourceVo.getSource());
                        }
                        LogUtil.v(titles.toString());
                        mainView.showLoadData(fragments,titles);
                    }else{
                        mainView.loadMsg(response.body().getMsg());
                    }
                }else{
                    mainView.loadError();
                }
            }

            @Override
            public void onFailure(Call<DataBean<CSCustom>> call, Throwable t) {
                mainView.finishLoadDialog();
                mainView.loadError();
                t.printStackTrace();
            }
        });
    }


}

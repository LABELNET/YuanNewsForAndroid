package cn.edu.hpu.yuan.yuannews.main.news;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuancore.util.LogUtil;
import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;
import cn.edu.hpu.yuan.yuannews.main.data.remote.NewsAPIService;

/**
 * Created by yuan on 16-5-9.
 * 新闻列表Fragment
 */
public class NewsFragment extends BaseFragment implements NewsContract.View{

    @Inject
    protected NewsContract.Presenter newsPresenter;

    @Inject
    protected AlertDialog.Builder builder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.news_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DaggerNewsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .newsModule(new NewsModule(this,newsAPIService, getActivity()))
                .build()
                .injectNewsFragment(this);
        newsPresenter.showNewsListData(10,2,6);
    }

    @Override
    public void showToast() {
        Toast.makeText(getContext(),"success",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDialog() {
        builder.setMessage("加载中");
        builder.show();
    }

    @Override
    public void showNewsList(ArrayList<NewsCustom> newsCustoms) {

        LogUtil.v(" NewsFragment : "+newsCustoms.get(0).toString());

    }

    @Override
    public void dismssDiolog() {
        builder.setCancelable(true);

    }

    @Override
    public void showCompletion() {

    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {

    }
}

package cn.edu.hpu.yuan.yuannews.main.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;
import cn.edu.hpu.yuan.yuannews.main.data.remote.NewsAPIService;

/**
 * Created by yuan on 16-5-9.
 * 新闻列表Fragment
 */
public class NewsFragment extends BaseFragment implements NewsContract.View{

    @Inject
    protected NewsAPIService newsAPIService;

    @Inject
    protected MewsPresenter mewsPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.news_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showToast() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void showNewsList(ArrayList<NewsCustom> newsCustoms) {

    }

    @Override
    public void dismssDiolog() {

    }

    @Override
    public void showCompletion() {

    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {

    }
}

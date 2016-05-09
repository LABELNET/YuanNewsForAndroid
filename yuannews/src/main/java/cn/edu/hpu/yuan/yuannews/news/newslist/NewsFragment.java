package cn.edu.hpu.yuan.yuannews.news.newslist;

import android.app.AlertDialog;
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
import cn.edu.hpu.yuan.yuannews.databinding.NewsFragmentBinding;
import cn.edu.hpu.yuan.yuannews.main.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;

/**
 * Created by yuan on 16-5-9.
 * 新闻列表Fragment
 */
public class NewsFragment extends BaseFragment implements NewsContract.View{

    @Inject
    protected NewsContract.Presenter newsPresenter;

    @Inject
    protected AlertDialog.Builder builder;

    private NewsFragmentBinding bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, null);
         bind = NewsFragmentBinding.bind(view);
        return  bind.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaggerNewsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .newsModule(new NewsModule(this,newsAPIService,activity))
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

        LogUtil.v(newsCustoms.get(0).getTitle());
        Log.v(" NewsFragment "," NewsFragment --------------------------------: "+newsCustoms.get(0).toString());
        bind.setTitle(newsCustoms.get(0).getTitle());
    }

    @Override
    public void dismssDiolog() {
        builder.setCancelable(true);
        LogUtil.v("dismssDiolog : dismssDiolog");

    }

    @Override
    public void showCompletion() {

    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {

    }
}

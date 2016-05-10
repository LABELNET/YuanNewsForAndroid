package cn.edu.hpu.yuan.yuannews.news.newslist;

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
import cn.edu.hpu.yuan.yuannews.main.base.BaseFragment;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;


/**
 * Created by yuan on 16-5-9.
 * 新闻列表Fragment
 * 1.功能：加载新闻列表
 *
 */
public class NewsFragment extends BaseFragment implements NewsContract.View{

    @Inject
    protected NewsContract.Presenter newsPresenter;

    private  NewsFragmentBinding bind;

    private static final String NEWS_TYPE="news_type";


    public static NewsFragment getNewsFragmentInstance(String type){
        NewsFragment fragment = new NewsFragment();
        Bundle bundle=new Bundle();
        bundle.putString(NEWS_TYPE,type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.news_fragment, null);
         bind = NewsFragmentBinding.bind(view);
//         bind = NewsFragmentBinding.bind(view);
        return  bind.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaggerNewsComponent.builder()
                .newsModule(new NewsModule(this))
                .build()
                .injectNewsFragment(this);
        newsPresenter.showNewsListData(10,2,6);
        bind.setNewTitile("波什(#‵′)凸");
    }

    @Override
    public void showToast() {
        Toast.makeText(getContext(),"success",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDialog() {
    }

    @Override
    public void showNewsList(ArrayList<NewsCustom> newsCustoms) {

        LogUtil.v(newsCustoms.get(0).getTitle());
        Log.v(" NewsFragment "," NewsFragment --------------------------------: "+newsCustoms.get(0).toString());
    }

    @Override
    public void dismssDiolog() {
        LogUtil.v("dismssDiolog : dismssDiolog");
    }

    @Override
    public void showCompletion() {

    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {

    }
}

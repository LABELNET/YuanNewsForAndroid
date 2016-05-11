package cn.edu.hpu.yuan.yuannews.news.newsdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.NewsDetailFragmentBinding;
import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.LikedVo;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;

/**
 * Created by yuan on 16-5-11.
 */
public class NewsDetailFragment extends NorbalBackFragment
        implements NewsDetailContancts.NewsDetailView{


    @Inject
    protected NewsDetailContancts.NewsDetailPresenter newsDetailPresenter;

    private static final String NEWSDETAIL_FRAGMENT_NID_KEY="news_detail_nid_key";
    public static NewsDetailFragment getInstance(Integer nid){
        NewsDetailFragment newsDetailFragment=new NewsDetailFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(NEWSDETAIL_FRAGMENT_NID_KEY,nid);
        newsDetailFragment.setArguments(bundle);
        return newsDetailFragment;
    }

    private NewsDetailFragmentBinding bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_detail_fragment, container, false);
        bind = NewsDetailFragmentBinding.bind(view);
        return bind.getRoot();
    }


    @Override
    protected void initComponent() {
        DaggerNewsDetailComponent
                .builder()
                .newsDetailModule(new NewsDetailModule(this))
                .build()
                .injectNewsDetailFragment(this);
    }

    @Override
    protected void onloadData() {

        newsDetailPresenter.getNewsDetailData();
        newsDetailPresenter.getNewsZansHeadData();

        //TODO 用户点赞操作，后面补充
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void showDialog() {
         showProgress();
    }

    @Override
    public void getNewsDetail(NewsCustom newsCustom) {

    }

    @Override
    public void loadError() {
        showMsg("数据加载失败");
    }

    @Override
    public void dimssDialog() {
       hideProgress();
    }

    @Override
    public void updateZanSuccess() {
        //你已经点赞了
    }

    @Override
    public void updateZanError() {
        showMsg("点赞失败");
    }

    @Override
    public void getZansHead(List<LikedVo> likedVos) {

    }

    @Override
    public void getZansHeadError() {
        showMsg("获取点赞头像失败");
    }

    private void showMsg(String msg){
        Snackbar.make(bind.newsDetail,msg,Snackbar.LENGTH_SHORT).show();
    }

}

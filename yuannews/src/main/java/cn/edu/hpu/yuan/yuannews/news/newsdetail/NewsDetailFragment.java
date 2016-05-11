package cn.edu.hpu.yuan.yuannews.news.newsdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.NewsDetailFragmentBinding;
import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;
import cn.edu.hpu.yuan.yuannews.main.data.NewsAPI;
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
    private int newsStatus=0;
    private int nid;

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

        newsDetailPresenter.getNewsDetailData(nid);
        newsDetailPresenter.getNewsZansHeadData(nid);
        newsDetailPresenter.getNewsZanStatus();

        //TODO 用户点赞操作，后面补充
        //TODO 用户点赞状态，获取（接口遗漏：获取当前用户，对这条新闻的点赞状态）
        //TODO 可以暂时使用这个 /html/getLikedStatus uid,nid可以获得
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        //用户点赞
        nid=getArguments().getInt(NEWSDETAIL_FRAGMENT_NID_KEY);
        bind.newDetailZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 判断用户登陆没登陆
                newsDetailPresenter.updateNewsZan(nid,newsStatus);
            }
        });

        bind.newsDetailComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到评论列表
                showMsg("评论点击了");
            }
        });


    }

    @Override
    public void showDialog() {
         showProgress();
    }

    @Override
    public void getNewsDetail(NewsCustom newsCustom) {
        bind.setNewsCustom(newsCustom);
        Glide.with(getContext())
                .load(NewsAPI.BASE_IMAGE_URL+newsCustom.getImg())
                .into(bind.newDetailImage);

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
        showMsg("你已经点赞了");
        //点赞成功，更新头像和状态
        newsDetailPresenter.getNewsZansHeadData(nid);
        newsDetailPresenter.getNewsZanStatus();
    }

    @Override
    public void updateZanError() {
        showMsg("点赞失败");
    }

    @Override
    public void getZansHead(List<LikedVo> likedVos) {
       //点赞人头像
    }

    @Override
    public void getZansHeadError() {
        showMsg("获取点赞头像失败");
    }



    @Override
    public void getZanStatus(int status) {
        newsStatus=status;
        //当前用户的点赞状态获取
        switch (status){
            case 0:
                bind.newDetailZan.setImageResource(R.mipmap.zan);
                break;
            case 1:
                bind.newDetailZan.setImageResource(R.mipmap.normalzan);
                break;
            case 2:
                bind.newDetailZan.setImageResource(R.mipmap.superzan);
                break;
        }
    }

    private void showMsg(String msg){
        Snackbar.make(bind.newsDetail,msg,Snackbar.LENGTH_SHORT).show();
    }

}

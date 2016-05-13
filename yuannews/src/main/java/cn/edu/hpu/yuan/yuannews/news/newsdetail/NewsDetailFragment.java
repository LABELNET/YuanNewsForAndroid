package cn.edu.hpu.yuan.yuannews.news.newsdetail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuancore.util.LogUtil;
import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.NewsDetailFragmentBinding;
import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;
import cn.edu.hpu.yuan.yuannews.main.data.NewsAPI;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.LikedVo;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;
import cn.edu.hpu.yuan.yuannews.news.comment.CommenActivity;
import cn.edu.hpu.yuan.yuannews.news.newsdetail.adapter.NewsDetailGridViewAdapter;
import cn.edu.hpu.yuan.yuannews.user.login.LoginActivity;

/**
 * Created by yuan on 16-5-11.
 */
public class NewsDetailFragment extends NorbalBackFragment
        implements NewsDetailContancts.NewsDetailView{


    @Inject
    protected NewsDetailContancts.NewsDetailPresenter newsDetailPresenter;

    private final int REQUEST_CODE=2019;
    private final int LOGIN_SUCCESS=2016;
    private final int COMMENT_SUCCESS=2020;


    @Inject
    protected NewsDetailGridViewAdapter newsDetailGridViewAdapter;

    private static final String NEWSDETAIL_FRAGMENT_NID_KEY="news_detail_nid_key";

    public static NewsDetailFragment getInstance(){
        return new NewsDetailFragment();
    }

    private NewsDetailFragmentBinding bind;
    private int newsStatus=0;
    private int nid;

    private int uid;

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
                .newsDetailModule(new NewsDetailModule(this,getContext()))
                .build()
                .injectNewsDetailFragment(this);
    }

    @Override
    protected void onloadData() {
        newsDetailPresenter.getNewsDetailData(nid);
        newsDetailPresenter.getNewsZansHeadData(nid);
        newsDetailPresenter.getNewsZanStatus(nid);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        nid=getArguments().getInt(NEWSDETAIL_FRAGMENT_NID_KEY);
        bind.newDetailZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uid= BaseApplication.newsAPIShared.getSharedUserID();

                if(uid>0) {
                    if(newsStatus==2){
                        showMsg("超赞后,就不嫩取消了.");
                    }
                    else{
                        newsDetailPresenter.updateNewsZan(nid, newsStatus);
                    }
                }else{
                    showMsgAction("你还未登陆哦 ");
                }
            }
        });

        bind.newsDetailComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到评论列表
                Intent intent = new Intent(getActivity(), CommenActivity.class);
                intent.putExtra("nid",nid);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        bind.newDetailImages.setAdapter(newsDetailGridViewAdapter);
    }

    @Override
    public void showDialog() {
        showMsg("加载中...");
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
        showMsg("操作失败");
    }

    @Override
    public void dimssDialog() {
       hideProgress();
    }

    @Override
    public void updateZanSuccess() {
        onloadData();
    }

    @Override
    public void updateZanError() {
        showMsg("点赞失败");
    }

    @Override
    public void getZansHead(List<LikedVo> likedVos) {
        //点赞人头像
        newsDetailGridViewAdapter.clearData();
        newsDetailGridViewAdapter.initData(likedVos);
        newsDetailGridViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void getZansHeadError() {
        showMsg("获取点赞头像失败");
    }



    @Override
    public void getZanStatus(int status) {
        newsStatus=status;
        uid= BaseApplication.newsAPIShared.getSharedUserID();
        //当前用户的点赞状态获取
        switch (status){
            case 0:
                if(uid>0) {
                    showMsg("记得点赞哦");
                }
                bind.newDetailZan.setImageResource(R.mipmap.zan);
                break;
            case 1:
                if(uid>0) {
                    showMsg("你已经点赞了");
                }
                bind.newDetailZan.setImageResource(R.mipmap.normalzan);
                break;
            case 2:
                if(uid>0) {
                    showMsg("哇塞,你超赞了耶");
                }
                bind.newDetailZan.setImageResource(R.mipmap.superzan);
                break;
        }
    }

    private void showMsg(String msg){
        Snackbar.make(bind.newsDetail,msg,Snackbar.LENGTH_SHORT).show();
    }

    private void showMsgAction(String msg){
        Snackbar.make(bind.newsDetail,msg,Snackbar.LENGTH_SHORT).setAction("点我登陆", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到登陆
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        }).setActionTextColor(Color.YELLOW).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE){
            //登陆后，返回后的，检测是否登陆，登陆成功，刷新数据,更新uid的值
            if(LOGIN_SUCCESS==resultCode){
                uid=BaseApplication.newsAPIShared.getSharedUserID();
                onloadData();
            }
            //评论改变刷新数据，否则不刷新 resultCode
            if(COMMENT_SUCCESS==resultCode){
                onloadData();
            }

        }
    }
}

package cn.edu.hpu.yuan.yuannews.news.comment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuancore.util.LogUtil;
import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.CommensFragmentBinding;
import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.CommentJo;
import cn.edu.hpu.yuan.yuannews.news.comment.adapter.CommentRecyclerAdapter;
import cn.edu.hpu.yuan.yuannews.user.login.LoginActivity;

/**
 * Created by yuan on 16-5-13.
 */
public class CommentFragment extends NorbalBackFragment implements CommenContancts.CommenContanctsView {


    @Inject
    protected CommenContancts.CommenContanctsPresenter commenContanctsPresenter;

    private CommensFragmentBinding binding;
    private int nid;
    private SwipeRefreshLayout.OnRefreshListener swipeOnRefresh;

    @Inject
    protected CommentRecyclerAdapter commentRecyclerAdapter;

    @Override
    protected void initComponent() {
        DaggerCommentComponent
                .builder()
                .commentModule(new CommentModule(this,getContext()))
                .build()
                .injectCommentFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.commens_fragment,container,false);
        return binding.getRoot();
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        nid=getArguments().getInt("nid"); //初始化nid
        initSwipeRefreshLayout();
        initRecyclerView();
        binding.btnAddLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int uid= BaseApplication.newsAPIShared.getSharedUserID();
                if(uid>0){
                    addComment();
                }else{
                    showMsgAction("你还未登陆，请先登陆");
                }
            }
        });
    }


    /**
     * 初始化下拉刷新
     */
    private void initSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary
                ,R.color.colorPrimaryDark
                ,R.color.colorAccent);
        binding.swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        binding.swipeRefreshLayout.setProgressViewEndTarget(true,500);//进度条位置
        binding.swipeRefreshLayout.setOnRefreshListener(swipeOnRefresh=new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onloadDataComment();
            }
        });

        binding.swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                binding.swipeRefreshLayout.setRefreshing(true);
            }
        });
        swipeOnRefresh.onRefresh();
    }

    /**
     *  初始化RecyclerView
     */
    private void initRecyclerView() {
        binding.recyclerView.setAdapter(commentRecyclerAdapter);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
                int lastVisibleItem = ((LinearLayoutManager) lm).findLastVisibleItemPosition();
                int totalItemCount = lm.getItemCount();
                //最后一项
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem == totalItemCount-2) {
                    commenContanctsPresenter.nextLoadCommentData(nid);
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    /**
     * 添加评论
     */
    private void addComment() {

        String content=binding.btnEditIfo.getText().toString().trim();
        if(content.length()==0){
            showSnack("请输入内容");
            return;
        }
        commenContanctsPresenter.userCommenData(content,nid);
    }

    protected void onloadDataComment() {
        //调用adapter的清空方法
        commentRecyclerAdapter.clearComemntJos();
        commenContanctsPresenter.initLoadCommentData(nid);
    }

    @Override
    public void showDialog() {
        showSnack("加载中...");
    }

    @Override
    public void showComentData(ArrayList<CommentJo> comments) {

        LogUtil.v(comments.toString());

        if(binding.swipeRefreshLayout.isRefreshing()){
            binding.swipeRefreshLayout.setRefreshing(false);
        }

        showNoData();

        if(comments.size()==0){
            showSnack("没有更多评论了");
            return;
        }
       //给adapter添加数据,响应数据
        commentRecyclerAdapter.addComemntJo(comments);
        commentRecyclerAdapter.notifyDataSetChanged();
    }

    private void showNoData(){
        if(commentRecyclerAdapter.getItemCount()==0){
            binding.noComment.setVisibility(View.VISIBLE);
        }else{
            binding.noComment.setVisibility(View.GONE);
        }
    }

    @Override
    public void showErrorMsg(String msg) {
        showSnack(msg);
    }


    @Override
    public void showSuccess() {
        showSnack("评论成功");
        binding.btnEditIfo.setText("");
        onloadDataComment();
    }

    private void showSnack(String msg){
        Snackbar.make(binding.commentFragmentPage,msg,Snackbar.LENGTH_SHORT).show();
    }

    private void showMsgAction(String msg){
        Snackbar.make(binding.commentFragmentPage,msg,Snackbar.LENGTH_SHORT).setAction("点我登陆", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到登陆
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        }).setActionTextColor(Color.YELLOW).show();
    }

}

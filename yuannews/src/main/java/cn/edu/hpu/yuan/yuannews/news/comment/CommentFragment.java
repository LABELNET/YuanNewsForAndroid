package cn.edu.hpu.yuan.yuannews.news.comment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

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
        binding.btnAddLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击评论
                int uid= BaseApplication.newsAPIShared.getSharedUserID();
                if(uid>0){

                }else{
                    showMsgAction("你还未登陆，请先登陆");
                }
            }
        });

        //初始化nid

    }

    @Override
    protected void onloadData() {
        //调用adapter的清空方法
        commenContanctsPresenter.initLoadCommentData(nid);
    }

    @Override
    public void showDialog() {
        showSnack("加载中...");
    }

    @Override
    public void showComentData(ArrayList<CommentJo> comments) {
       //给adapter添加数据,响应数据
    }

    @Override
    public void showErrorMsg(String msg) {
        showSnack(msg);
    }


    @Override
    public void showSuccess() {
        showSnack("评论成功");
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

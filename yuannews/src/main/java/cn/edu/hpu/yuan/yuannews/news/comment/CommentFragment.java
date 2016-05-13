package cn.edu.hpu.yuan.yuannews.news.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.CommentJo;

/**
 * Created by yuan on 16-5-13.
 */
public class CommentFragment extends NorbalBackFragment implements CommenContancts.CommenContanctsView {




    @Inject
    protected CommenContancts.CommenContanctsPresenter commenContanctsPresenter;

    @Override
    protected void initComponent() {
        DaggerCommentComponent
                .builder()
                .commentModule(new CommentModule(this))
                .build()
                .injectCommentFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void showComentData(ArrayList<CommentJo> comments) {

    }

    @Override
    public void showErrorMsg(String msg) {

    }


    @Override
    public void showSuccess() {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }
}

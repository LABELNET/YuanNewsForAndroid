package cn.edu.hpu.yuan.yuannews.news.comment;

import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;

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

    @Override
    public void showDialog() {

    }

    @Override
    public void showComentData() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showSuccess() {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }
}

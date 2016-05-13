package cn.edu.hpu.yuan.yuannews.news.comment;

import android.content.Context;

import cn.edu.hpu.yuan.yuannews.news.comment.adapter.CommentRecyclerAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-13.
 */

@Module
public class CommentModule {


    public CommentModule(){}

    private CommenContancts.CommenContanctsView commenContanctsView;
    private Context context;

    public CommentModule(CommenContancts.CommenContanctsView commenContanctsView,Context context) {
        this.commenContanctsView = commenContanctsView;
        this.context=context;
    }


    @Provides
    CommentFragment provideCommentFragment(){
        return new CommentFragment();
    }

    @Provides
    CommenContancts.CommenContanctsPresenter provideCommenContanctsPresenter(){
        return new CommemPresenter(commenContanctsView);
    }

    @Provides
    CommentRecyclerAdapter provideCommentRecyclerAdapter(){
        return new CommentRecyclerAdapter(context);
    }

}

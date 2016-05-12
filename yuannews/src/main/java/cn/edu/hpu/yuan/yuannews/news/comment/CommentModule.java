package cn.edu.hpu.yuan.yuannews.news.comment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-13.
 */

@Module
public class CommentModule {


    public CommentModule(){}

    private CommenContancts.CommenContanctsView commenContanctsView;

    public CommentModule(CommenContancts.CommenContanctsView commenContanctsView) {
        this.commenContanctsView = commenContanctsView;
    }


    @Provides
    CommentFragment provideCommentFragment(){
        return new CommentFragment();
    }

    @Provides
    CommenContancts.CommenContanctsPresenter provideCommenContanctsPresenter(){
        return new CommemPresenter(commenContanctsView);
    }

}

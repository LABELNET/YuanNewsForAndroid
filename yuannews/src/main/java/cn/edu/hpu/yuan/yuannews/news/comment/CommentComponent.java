package cn.edu.hpu.yuan.yuannews.news.comment;

import dagger.Component;

/**
 * Created by yuan on 16-5-13.
 */
@Component(modules = CommentModule.class)
public interface CommentComponent {

    void injectCommentActivity(CommenActivity commenActivity);
    
    void injectCommentFragment(CommentFragment commentFragment);

}

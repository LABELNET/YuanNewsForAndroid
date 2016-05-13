package cn.edu.hpu.yuan.yuannews.news.comment;

import java.util.ArrayList;

import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.CommentJo;

/**
 * Created by yuan on 16-5-13.
 */
public interface CommenContancts {

    interface  CommenContanctsPresenter{


        void initLoadCommentData(int nid);

        void nextLoadCommentData(int nid);

        //用户评论
        void userCommenData(String content,Integer nid);
    }

    interface CommenContanctsView{
        void showDialog();

        void showComentData(ArrayList<CommentJo>  comments);

        void showErrorMsg(String msg);

        void showSuccess();
    }

}

package cn.edu.hpu.yuan.yuannews.news.comment;

/**
 * Created by yuan on 16-5-13.
 */
public interface CommenContancts {

    interface  CommenContanctsPresenter{

        //分页加载评论
        void getLoadCommenData(int p);

        //用户评论
        void userCommenData();
    }

    interface CommenContanctsView{
        void showDialog();

        void showComentData();

        void showError(String msg);

        void showSuccess();
    }

}

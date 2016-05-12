package cn.edu.hpu.yuan.yuannews.news.comment;

/**
 * Created by yuan on 16-5-13.
 */
public class CommemPresenter implements CommenContancts.CommenContanctsPresenter {


    private CommenContancts.CommenContanctsView commenContanctsView;

    public CommemPresenter(CommenContancts.CommenContanctsView commenContanctsView) {
        this.commenContanctsView = commenContanctsView;
    }

    @Override
    public void getLoadCommenData(int p) {

    }

    @Override
    public void userCommenData() {

    }
}

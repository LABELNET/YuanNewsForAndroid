package cn.edu.hpu.yuan.yuannews.news.newsdetail;

import java.util.List;

import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.LikedVo;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;

/**
 * Created by yuan on 16-5-11.
 * Presenter
 */
public interface NewsDetailContancts {


    interface NewsDetailPresenter{

        void getNewsDetailData();

        void updateNewsZan();

        void getNewsZansHeadData();
    }

    interface NewsDetailView{

        void showDialog();

        void getNewsDetail(NewsCustom newsCustom);

        void loadError();

        void dimssDialog();

        void updateZanSuccess();

        void updateZanError();

        void getZansHead(List<LikedVo> likedVos);

        void getZansHeadError();
    }

}

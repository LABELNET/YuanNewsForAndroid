package cn.edu.hpu.yuan.yuannews.user.center;

import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.TasteVo;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.UserVo;

/**
 * Created by yuan on 16-5-12.
 */
public interface CenterContancts {

    interface CenterContanctsPresenter{

         void getUserDetailData();

         void getUserAllLabels();

    }

    interface CenterContanctsView{

          void showDialog();

          void showError(String msg);

          void showUserDetail(UserVo userVo);

          void showAllLabels(TasteVo tasteVo);
    }

}

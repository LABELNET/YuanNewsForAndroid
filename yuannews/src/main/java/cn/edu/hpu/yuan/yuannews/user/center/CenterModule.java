package cn.edu.hpu.yuan.yuannews.user.center;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-12.
 */
@Module
public class CenterModule {


    private CenterContancts.CenterContanctsView centerContanctsView;

    public CenterModule(CenterContancts.CenterContanctsView centerContanctsView) {
        this.centerContanctsView = centerContanctsView;
    }


    @Provides
    CenterFragment provideCenterFragment(){
        return new CenterFragment();
    }


    @Provides
    CenterContancts.CenterContanctsPresenter provideCenterPersenter(){
        return new CenterPersenter(centerContanctsView);
    }

}

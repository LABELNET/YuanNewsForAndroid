package cn.edu.hpu.yuan.yuannews.user.center;

import android.widget.LinearLayout;

import dagger.Module;
import dagger.Provides;
import retrofit2.http.POST;

/**
 * Created by yuan on 16-5-12.
 */
@Module
public class CenterModule {


    public CenterModule(){}

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

    @Provides
    LinearLayout.LayoutParams provideLayoutParams(){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10,10,0,0);
        return layoutParams;
    }

}

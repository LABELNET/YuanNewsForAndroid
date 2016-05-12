package cn.edu.hpu.yuan.yuannews.user.label;

import cn.edu.hpu.yuan.yuannews.user.label.adapter.LabelIfoAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-12.
 */
@Module
public class LabelModule {

    public LabelModule(){}

    LabelContancts.LabelContanctsView labelContanctsView;

    public LabelModule(LabelContancts.LabelContanctsView labelContanctsView) {
        this.labelContanctsView = labelContanctsView;
    }

    @Provides
    LabelFragment provideLabelFragment(){
        return new LabelFragment();
    }


    @Provides
    LabelContancts.LabelContanctsPresenter provideLabelContractsPresenter(){
        return new LabelPresenter(labelContanctsView);
    }

    @Provides
    LabelIfoAdapter provideLabelIfoAdapter(){
        return new LabelIfoAdapter();
    }

}

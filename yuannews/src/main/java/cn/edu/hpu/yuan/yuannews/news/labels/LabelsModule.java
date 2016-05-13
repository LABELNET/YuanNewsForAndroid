package cn.edu.hpu.yuan.yuannews.news.labels;

import android.content.Context;

import cn.edu.hpu.yuan.yuannews.news.labels.adapter.LabelsAdapter;
import cn.edu.hpu.yuan.yuannews.user.label.adapter.LabelIfoAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-13.
 */
@Module
public class LabelsModule {


    public LabelsModule(){}

    private LabelsContancts.LabelsContanctsView labelsContanctsView;
    private Context context;

    public LabelsModule(LabelsContancts.LabelsContanctsView labelsContanctsView, Context context) {
        this.labelsContanctsView = labelsContanctsView;
        this.context=context;
    }

    @Provides
    LabelsFragment provideLabelsFragment(){
        return new LabelsFragment();
    }

    @Provides
    LabelsContancts.LabelsContanctsPresenter provideLabelsContanctsPresenter(){
        return new LabelsPresenter(labelsContanctsView);
    }

    @Provides
    LabelsAdapter provideLabelsIfoAdapter(){
        return new LabelsAdapter(context);
    }

}

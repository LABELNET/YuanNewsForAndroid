package cn.edu.hpu.yuan.yuannews.news.tuijian;

import android.content.Context;

import cn.edu.hpu.yuan.yuannews.news.newslist.adapter.CustomRecyclerViewAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-13.
 */
@Module
public class TuijianModule {


    public TuijianModule(){}

    private TuijianContancts.TuijianContanctsView tuijianContanctsView;
    private Context context;

    public TuijianModule(TuijianContancts.TuijianContanctsView tuijianContanctsView,Context context) {
        this.context=context;
        this.tuijianContanctsView = tuijianContanctsView;
    }

    @Provides
    TuijianFragment provideTuijianFragment(){
       return new TuijianFragment();
    }

    @Provides
    TuijianContancts.TuijianContanctsPresenter provideTuijianContanctsPresenter(){
        return new TuijianPresenter(tuijianContanctsView);
    }

    @Provides
    CustomRecyclerViewAdapter provideCustomRecyclerViewAdapter(){
        return new CustomRecyclerViewAdapter(context);
    }

}

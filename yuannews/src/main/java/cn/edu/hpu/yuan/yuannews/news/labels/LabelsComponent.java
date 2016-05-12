package cn.edu.hpu.yuan.yuannews.news.labels;

import dagger.Component;

/**
 * Created by yuan on 16-5-13.
 */
@Component(modules = LabelsModule.class)
public interface LabelsComponent {

    void injectLabelsActivity(LabelsActivity labelsActivity);

    void injectLabelsFragment(LabelsFragment labelsFragment);
}

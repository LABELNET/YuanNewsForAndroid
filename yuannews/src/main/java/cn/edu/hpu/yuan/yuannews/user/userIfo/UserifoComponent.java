package cn.edu.hpu.yuan.yuannews.user.userIfo;

import dagger.Component;

/**
 * Created by yuan on 16-5-12.
 */
@Component(modules = UserIfoModule.class)
public interface UserifoComponent {

    void injectUserIfoActivity(UserifoActivity userifoActivity);

    void injectUserIfoFragment(UserIfoFragment userIfoFragment);

}

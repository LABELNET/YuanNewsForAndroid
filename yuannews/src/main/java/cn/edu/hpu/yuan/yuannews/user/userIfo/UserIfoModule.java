package cn.edu.hpu.yuan.yuannews.user.userIfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-12.
 */
@Module
public class UserIfoModule {

    public UserIfoModule(){}

    protected UserIfoContrancts.UserIfoContranctsView userIfoContranctsView;

    public UserIfoModule(UserIfoContrancts.UserIfoContranctsView userIfoContranctsView) {
        this.userIfoContranctsView = userIfoContranctsView;
    }

    @Provides
    UserIfoFragment provideUserIfoFragment(){
        return new UserIfoFragment();
    }

    @Provides
    UserIfoContrancts.UserIfoContranctsPresenter provideUserContranctsPresenter(){
        return new UserIfoPresenter(userIfoContranctsView);
    }

}

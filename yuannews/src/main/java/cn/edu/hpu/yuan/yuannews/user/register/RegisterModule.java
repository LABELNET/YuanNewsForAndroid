package cn.edu.hpu.yuan.yuannews.user.register;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yuan on 16-5-12.
 */

@Module
public class RegisterModule {


    protected RegisterContancts.RegisterCOntanctsView registerCOntanctsView;

    public RegisterModule(RegisterContancts.RegisterCOntanctsView registerCOntanctsView) {
        this.registerCOntanctsView = registerCOntanctsView;
    }

    public RegisterModule() {
    }

    @Provides
    RegisterFragment provideRegisterFragment(){
        return new RegisterFragment();
    }

    @Provides
    RegisterContancts.RegisterContanctsPresenter provideRegsiterPresenter(){
        return new RegisterPresenter(registerCOntanctsView);
    }



}

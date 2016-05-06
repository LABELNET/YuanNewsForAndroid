package cn.edu.hpu.yuan.yuancore;

import android.app.Application;
import android.content.Context;

import cn.edu.hpu.yuan.yuancore.util.CrashHandler;
import cn.edu.hpu.yuan.yuannet.image.ImageNet;

/**
 * Created by yuan on 16-5-6.
 * Application 类，app总控
 */
public class BaseApplication extends Application{


    private Context context;
    private CrashHandler crashHandler = CrashHandler.getInstance();

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        initApp();

    }



    private void initApp() {
        crashHandler.init(getApplicationContext());
        ImageNet.newInstance(context);
    }


}

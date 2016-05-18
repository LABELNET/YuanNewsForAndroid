package cn.edu.hpu.yuan.yuannews.main.util;

import android.app.Notification;
import android.content.Context;

/**
 * Created by yuan on 16-5-18.
 * 通知util - 实现通知的初始化，更新，删除操作；
 * （1）显示通知
 * （2）隐藏通知
 * （3）更新通知实现
 */
public class NotificationUtil {


    private Notification.Builder builder;
    private static  NotificationUtil notificationUtil;

    public NotificationUtil(Context context) {
        builder=new Notification.Builder(context);
    }

    public static NotificationUtil newInstance(Context context) {
        NotificationUtil util = new NotificationUtil(context);
        return util;
    }


}

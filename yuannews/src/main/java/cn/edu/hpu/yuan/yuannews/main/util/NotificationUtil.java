package cn.edu.hpu.yuan.yuannews.main.util;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.base.BaseActivity;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.TuijianModel;

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
    private static Context context;
    private final int NOTIFICATION_ID=1;

    public NotificationUtil(Context context) {
        builder=new Notification.Builder(context);
    }

    public static NotificationUtil newInstance(Context c) {
        NotificationUtil util = new NotificationUtil(c);
        context=c;
        return util;
    }

    /**
     * 初始化通知
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void initNotification(){
        builder.setSmallIcon(R.mipmap.ic_news_no)
               .setPriority(Notification.PRIORITY_DEFAULT)
               .setCategory(Notification.CATEGORY_MESSAGE);
    }

    /**
     * 显示通知
     * @param model
     */
    public void showNotification(TuijianModel model){
        NewsCustom newsCustom = model.getNewsCustom();
        String title=newsCustom.getTitle();
        title=title.length()>20?title.substring(0,20)+"...":title;
        builder.setContentText(title);
        builder.setContentTitle("你收到了"+model.getCount()+"条推荐新闻");


        Intent intent=new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context,BaseActivity.class);

        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        builder.setContentText("你收到了"+model.getCount()+"条推荐新闻");
        builder.setFullScreenIntent(pendingIntent,true);

        NotificationManager manager= (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            manager.notify(NOTIFICATION_ID,builder.build());
        }else{
            manager.notify(NOTIFICATION_ID,builder.getNotification());
        }
    }



}

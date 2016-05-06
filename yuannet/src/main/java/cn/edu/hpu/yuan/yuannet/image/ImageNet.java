package cn.edu.hpu.yuan.yuannet.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by yuan on 16-4-29.
 * 请求网络图片，封装
 * 1.简单图片请求
 * 2.图片缓存 lruCache
 * 3.
 */
public class ImageNet {

    //上下文
    private Context context;

    private static LruCache<String,Bitmap> cache=null;

    private static ImageNet instance=null;

    public ImageNet(Context context){
        this.context=context;
    }
    /**
     * 初始化
     * @param context 上下文
     * @return ImageNet对象
     */
    public static ImageNet newInstance(Context context){
        if(instance==null){
            instance=new ImageNet(context);
            //获取系统分配给每个应用程序的最大内存，每个应用系统分配32M
            int maxMemory = (int) Runtime.getRuntime().maxMemory();
            int mCacheSize = maxMemory / 8;
            //给LruCache分配1/8 4M
            cache = new LruCache<String, Bitmap>(mCacheSize){

                //必须重写此方法，来测量Bitmap的大小
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }

            };
        }
        return instance;
    }









}

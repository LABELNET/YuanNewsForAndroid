package cn.edu.hpu.yuan.yuannews.main.data.remote;

import java.util.ArrayList;
import java.util.Map;

import cn.edu.hpu.yuan.yuannews.main.data.model.DataBean;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.CSCustom;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by yuan on 16-5-9.
 * Retrofit数据请求
 *
 */
public interface NewsAPIService {

    /**
     * 新闻主页数据请求
     *  type 类型，2,3,4,5
     *  num 请求数量
     *   p 当前页数
     *  nType 操作类型：6，7,8
     * @return
     */
    @GET("getNesList")
    Call<DataBean<ArrayList<NewsCustom>>> getNewsList(@QueryMap Map<String,String> options);


    @POST("getCateSources")
    Call<DataBean<CSCustom>> getCateSource();


}

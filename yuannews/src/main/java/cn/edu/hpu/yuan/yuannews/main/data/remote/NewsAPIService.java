package cn.edu.hpu.yuan.yuannews.main.data.remote;

import java.util.ArrayList;

import cn.edu.hpu.yuan.yuannews.main.data.model.DataBean;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yuan on 16-5-9.
 * Retrofit数据请求
 *
 */
public interface NewsAPIService {

    /**
     * 新闻主页数据请求
     * @param type 类型，2,3,4,5
     * @param num 请求数量
     * @param p 当前页数
     * @param nType 操作类型：6，7,8
     * @return
     */
    @GET("getNesList?type={type}&num={num}&p={p}&nType={nType}")
    Call<DataBean<ArrayList<NewsCustom>>> getNewsList(@Path("type") Integer type,
                                                      @Path("num") Integer num,
                                                      @Path("p") Integer p,
                                                      @Path("nType") Integer nType);


}

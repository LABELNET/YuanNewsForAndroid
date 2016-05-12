package cn.edu.hpu.yuan.yuannews.main.data.remote;

import android.databinding.tool.util.L;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import cn.edu.hpu.yuan.yuannews.main.data.model.DataBean;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.CommentJo;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.CommentVo;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.LikedVo;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.TasteVo;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.UserVo;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.CSCustom;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
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


    /**
     * 分类，来源信息
     * @return
     */
    @POST("getCateSources")
    Call<DataBean<CSCustom>> getCateSource();

    /**
     * 新闻详情
     * @param nid
     * @return
     */
    @POST("getNewsDetail")
    Call<DataBean<NewsCustom>> getNewsDetail(@Query("nid") Integer nid);

    /**
     * 获取点赞列表
     * @return
     */
    @POST("getLikedHead")
    Call<DataBean<ArrayList<LikedVo>>> getNewsLikedVos(@Query("nid") Integer nid);


    /**
     * 用户登陆
     * @param num
     * @param pass
     * @return
     */
    @POST("login")
    Call<DataBean<UserVo>> postUserLogin(@Query("unum") String num,@Query("pass") String pass);


    /**
     * 用户注册
     * @param num
     * @param pass
     * @param nick
     * @return
     */
    @POST("register")
    Call<DataBean> postUserRegister(@Query("unum") String num,@Query("pass") String pass,@Query("nick")String nick);


    /**
     * 获取用户详情信息
     * @param uid 用户id
     * @return
     */
    @POST("getUserDetail")
    Call<DataBean<UserVo>> postUserDetail(@Query("uid") Integer uid);


    /**
     * 获取用户所有兴趣标签
     * @param uid
     * @return
     */
    @POST("allLabels")
    Call<DataBean<List<TasteVo>>> postTasteVos(@Query("uid") Integer uid);


    /**
     * 添加兴趣标签
     * @param uid
     * @param label
     * @return
     */
    @POST("addTasteLabel")
    Call<DataBean> postAddLabel(@Query("uid") Integer uid,@Query("label") String label);


    /**
     * 删除兴趣标签
     * @param tid
     * @return
     */
    @POST("deleteLabel")
    Call<DataBean> postDeleteLabel(@Query("tid") Integer tid);

    /**
     * 修改用户信息
     * @param uid
     * @param sex
     * @param nick
     * @return
     */
    @POST("updateUserIfo")
    Call<DataBean> postUpdateUserIfo(@Query("uid") Integer uid,@Query("sex") Integer sex,@Query("nick") String nick);

    /**
     * 修改用户密码
     * @param uid
     * @param pass
     * @return
     */
    @POST("updateUserPass")
    Call<DataBean> postupdateUserPass(@Query("uid") Integer uid,@Query("pass") String pass);


    //TODO 上传头像 和 获取当前新闻当前用户的点赞状态（服务器端）

    /**
     * 用户点赞
     * @param uid
     * @param nid
     * @param status
     * @return
     */
    @POST("userZanNews")
    Call<DataBean> postZan(@Query("uid") Integer uid,@Query("nid") Integer nid,@Query("status") Integer status);


    /**
     * 用户评论
     * @param uid
     * @param nid
     * @param content
     * @return
     */
    @POST("userCommentNews")
    Call<DataBean> postComment(@Query("uid") Integer uid,@Query("nid") Integer nid,@Query("content") String content);


    /**
     * 用户评论分页查询
     * @param p
     * @param num
     * @param nid
     * @return
     */
    @POST("getNewsComment")
    Call<DataBean<ArrayList<CommentJo>>> getCommentIfo(@Query("p") Integer p,@Query("num") Integer num,@Query("nid") Integer nid);














}

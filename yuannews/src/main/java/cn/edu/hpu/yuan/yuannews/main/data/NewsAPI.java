package cn.edu.hpu.yuan.yuannews.main.data;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by yuan on 16-5-9.
 * 数据获取总类
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface NewsAPI {

   String BASE_IP="192.168.199.150";

   String BASE_URL="http://"+BASE_IP+":8088/yuannews/api/";

}

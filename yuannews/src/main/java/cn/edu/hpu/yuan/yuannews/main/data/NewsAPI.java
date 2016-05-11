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

   // TODO    每次修改IP 地址进行数据请求
//   String BASE_IP="192.168.199.150";
   String BASE_IP="10.42.0.1";

   String BASE_URL="http://"+BASE_IP+":8088/yuannews/api/";

}

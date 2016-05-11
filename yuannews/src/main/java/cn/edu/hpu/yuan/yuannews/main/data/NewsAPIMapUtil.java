package cn.edu.hpu.yuan.yuannews.main.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuan on 16-5-9.
 * 请求参数封装为map
 */
public class NewsAPIMapUtil {

    private static final Integer PAGE_NUM=20;

    public static Map<String,String> getNewsListMap(String title,Integer type,Integer p,Integer nType){
        Map<String,String> maps=new HashMap<>();
        maps.put("type",String.valueOf(type));
        maps.put("p",String.valueOf(p));
        maps.put("nType",String.valueOf(nType));
        maps.put("title",title);
        maps.put("num",PAGE_NUM+"");
        return maps;
    }

}

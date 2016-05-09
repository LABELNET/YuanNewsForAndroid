package cn.edu.hpu.yuan.yuannews.main.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuan on 16-5-9.
 * 请求参数封装为map
 */
public class NewsAPIMapUtil {

    private static final Integer PAGE_NUM=20;

    public static Map<String,Integer> getNewsListMap(Integer type,Integer p,Integer nType){
        Map<String,Integer> maps=new HashMap<>();
        maps.put("type",type);
        maps.put("p",p);
        maps.put("nType",nType);
        return maps;
    }

}

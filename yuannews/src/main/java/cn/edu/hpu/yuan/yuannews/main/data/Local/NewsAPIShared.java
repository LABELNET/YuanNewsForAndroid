package cn.edu.hpu.yuan.yuannews.main.data.Local;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;

import cn.edu.hpu.yuan.yuancore.util.LogUtil;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.UserVo;

/**
 * Created by yuan on 16-5-12.
 * SharedPresence 本地持久化，用户数据
 */
public class NewsAPIShared {

    private final String SHARED_PREFERENCES_NAME="yuannews";
    private final String SHRAED_USER_IFO_JSON_KEY="shared_user_ifo_json_key";
    private final String SHRAED_USER_ID_KEY="shared_user_id_key";
    private final String SHRAED_MOREN_USER_IFNO="No Data";

    private Context context;
    private SharedPreferences  sharedPreferences;
    private SharedPreferences.Editor editor;


    public NewsAPIShared(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void putSharedUser(UserVo userVo){
        putSharedUserID(userVo.getId());
        String userVoJson= JSON.toJSONString(userVo);
        editor.putString(SHRAED_USER_IFO_JSON_KEY,userVoJson);
        editor.commit();
    }

    public UserVo getSharedUser(){
        String json = sharedPreferences.getString(SHRAED_USER_IFO_JSON_KEY,SHRAED_MOREN_USER_IFNO);
        if(SHRAED_MOREN_USER_IFNO.equals(json)){
            LogUtil.v(" SHRAED_MOREN_USER_IFNO " + SHRAED_MOREN_USER_IFNO);
            return null;
        }
        return (UserVo) JSON.parseArray(json,UserVo.class);
    }

    private void putSharedUserID(Integer uid){
        editor.putInt(SHRAED_USER_ID_KEY,uid);
        editor.commit();
    }

    public Integer getSharedUserID(){
       return  sharedPreferences.getInt(SHRAED_USER_ID_KEY,0);
    }

}

package cn.edu.hpu.yuan.yuannews.main.data.Local;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by yuan on 16-5-12.
 * SharedPresence 本地持久化，用户数据
 */
public class NewsAPIShared {

    private final String SHARED_PREFERENCES_NAME="yuannews";
    private final String SHRAED_USER_IFO_HEAD_KEY="shared_user_ifo_json_key";
    private final String SHRAED_USER_IFO_NICK_KEY="shared_user_ifo_json_key";
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

    public void putSharedUserIfo(String headUrl,Integer uid,String nick){
        editor.putString(SHRAED_USER_IFO_HEAD_KEY,headUrl);
        editor.putInt(SHRAED_USER_ID_KEY,uid);
        editor.putString(SHRAED_USER_IFO_NICK_KEY,nick);
        editor.commit();
    }

    public String getSharedUserHead(){
        return sharedPreferences.getString(SHRAED_USER_IFO_HEAD_KEY,SHRAED_MOREN_USER_IFNO);
    }

    public String getSharedUserNick(){
        return sharedPreferences.getString(SHRAED_USER_IFO_NICK_KEY,SHRAED_MOREN_USER_IFNO);
    }

    public Integer getSharedUserID(){
       return  sharedPreferences.getInt(SHRAED_USER_ID_KEY,0);
    }

}

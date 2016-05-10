package cn.edu.hpu.yuan.yuannews.main.data.model.basevo;

import cn.edu.hpu.yuan.yuannews.main.data.model.base.Liked;

/**
 * Created by yuan on 16-4-3.
 * 留言拓展类
 */
public class LikedVo extends Liked {

    private Integer uid;
    private String head;
    private String nick;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}

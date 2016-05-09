package cn.edu.hpu.yuan.yuannews.main.data.model.base;


/**
 * Created by yuan on 16-4-1.
 */
public class User {
    private int id;
    private String unum;
    private String head;
    private String nick;
    private String pass;
    private int sex;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUnum() {
        return unum;
    }

    public void setUnum(String unum) {
        this.unum = unum;
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


    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (sex != user.sex) return false;
        if (status != user.status) return false;
        if (unum != null ? !unum.equals(user.unum) : user.unum != null) return false;
        if (head != null ? !head.equals(user.head) : user.head != null) return false;
        if (nick != null ? !nick.equals(user.nick) : user.nick != null) return false;
        if (pass != null ? !pass.equals(user.pass) : user.pass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (unum != null ? unum.hashCode() : 0);
        result = 31 * result + (head != null ? head.hashCode() : 0);
        result = 31 * result + (nick != null ? nick.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + sex;
        result = 31 * result + status;
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", unum='" + unum + '\'' +
                ", head='" + head + '\'' +
                ", nick='" + nick + '\'' +
                ", pass='" + pass + '\'' +
                ", sex=" + sex +
                ", status=" + status +
                '}';
    }
}

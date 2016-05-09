package cn.edu.hpu.yuan.yuannews.main.data.model.base;

/**
 * Created by yuan on 16-4-1.
 */
public class Cate {
    private int id;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cate cate = (Cate) o;

        if (id != cate.id) return false;
        if (content != null ? !content.equals(cate.content) : cate.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cate{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}

package cn.edu.hpu.yuan.yuannews.main.data.model.base;


/**
 * Created by yuan on 16-4-3.
 */
public class Liked {
    private int id;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        Liked liked = (Liked) o;

        if (id != liked.id) return false;
        if (status != liked.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + status;
        return result;
    }

    @Override
    public String toString() {
        return "Liked{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}

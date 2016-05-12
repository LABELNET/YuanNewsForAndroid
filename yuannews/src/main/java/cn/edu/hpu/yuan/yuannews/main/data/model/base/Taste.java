package cn.edu.hpu.yuan.yuannews.main.data.model.base;


import java.io.Serializable;

/**
 * Created by yuan on 16-4-1.
 */
public class Taste implements Serializable{
    private int id;
    private String label;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Taste taste = (Taste) o;

        if (id != taste.id) return false;
        if (label != null ? !label.equals(taste.label) : taste.label != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Taste{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}

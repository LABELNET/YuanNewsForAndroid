package cn.edu.hpu.yuan.yuannews.main.data.model.base;


/**
 * Created by yuan on 16-4-1.
 */

public class Source {
    private int id;
    private String source;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Source source1 = (Source) o;

        if (id != source1.id) return false;
        if (source != null ? !source.equals(source1.source) : source1.source != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", source='" + source + '\'' +
                '}';
    }
}

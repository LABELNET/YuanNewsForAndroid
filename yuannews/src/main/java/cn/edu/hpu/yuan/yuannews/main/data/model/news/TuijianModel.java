package cn.edu.hpu.yuan.yuannews.main.data.model.news;


/**
 * ==================================================
 * <p/>
 * 通知推荐的model ， 显示数量和 第一条新的新闻
 * <p>
 * ==================================================
 */
public class TuijianModel {

    private Integer count;

    private NewsCustom newsCustom;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public NewsCustom getNewsCustom() {
        return newsCustom;
    }

    public void setNewsCustom(NewsCustom newsCustom) {
        this.newsCustom = newsCustom;
    }
}

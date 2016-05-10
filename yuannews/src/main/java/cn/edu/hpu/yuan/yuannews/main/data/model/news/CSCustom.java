package cn.edu.hpu.yuan.yuannews.main.data.model.news;


import java.util.List;

import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.CateVo;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.SourceVo;

/**
 * ==================================================
 * <p/>
 * 版权：　软件工程.net12-1 原明卓
 * <p/>
 * 项目：　基于用户兴趣标签的新闻推荐系统
 * <p/>
 * 作者：　原明卓
 * <p/>
 * 版本：　1.0
 * <p/>
 * 创建日期：　16-4-25 下午5:20
 * <p/>
 * 功能描述：分类信息和来源信息，用来显示菜单使用
 * <p>
 * <p/>
 * 功能更新历史：
 * <p>
 * ==================================================
 */
public class CSCustom {

    private List<CateVo> cateVos;
    private List<SourceVo> sourceVos;

    public List<CateVo> getCateVos() {
        return cateVos;
    }

    public void setCateVos(List<CateVo> cateVos) {
        this.cateVos = cateVos;
    }

    public List<SourceVo> getSourceVos() {
        return sourceVos;
    }

    public void setSourceVos(List<SourceVo> sourceVos) {
        this.sourceVos = sourceVos;
    }
}

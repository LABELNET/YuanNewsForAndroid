# YuanNewsForAndroid
新闻推荐系统-Android客户端


# 2016.05.09
 * 架构基本实现
 * 主页,详情页布局采用开源 ： 
    * https://github.com/ksoichiro/Android-ObservableScrollView
    
 * Dialog 
    * https://github.com/andyxialm/ColorDialog
 * 排序规则 ： https://github.com/konifar/fab-transformation
 
 * 新闻界面实现思路：
      * 列表实现，head 分类和来源 ，默认显示正常情况
      * 默认显示正常情况：下面四种情况菜单-》推荐（需要登录），热度，评论，点赞

# 2016.05.06
 * Android 基本架构
    * dagger2 + mvp + Retrofit + data Binding 

# 2016.04.29
 * 添加 yuancore 模块
    * LogUtil ： 日志打印类
    * DensityUtil ： 获取屏幕的宽度等
    * DateUtils : 日期处理类，几分钟以前等
    * CrashHandler ： 异常捕捉类
 * 添加网络 yuannet 模块
    * 图片请求及其封装 glide
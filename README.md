# YuanNewsForAndroid
新闻推荐系统-Android客户端

# 2016.05.13
  * 接口：1.获取当前新闻当前用户的新闻状态，2.分页查询所有的新闻标签
  * 优先：1.接口实现 2.新闻详情跑通（评论及点赞）3.新闻推荐 4.信息修改 5.引导页，启动页，关于页
  * 优化

# 2016.05.12
 * 用户登陆 ：账户/密码 
 * 用户注册 ：账户/密码/昵称；
 * 兴趣标签管理
 * 新闻推荐实现
 * 用户详情页标签布局实现：
    * https://github.com/lankton/android-flowlayout

# 2016.05.11
 * 新闻列表请求，包括分类，来源等请求，浮动按钮进行排序请求
 * 新闻详情页实现，包括评论，点赞，详情显示
 * 分页加载及封装

# 2016.05.10
 * 新闻主界面实现

# 2016.05.09
 * 架构基本实现
 * 主页,详情页布局采用开源 ： 
    * https://github.com/ksoichiro/Android-ObservableScrollView
    
 * Dialog 
    * https://github.com/andyxialm/ColorDialog
 * 排序规则 ： https://github.com/konifar/fab-transformation
 
 * 新闻界面实现思路：
      * 列表实现，head 分类和来源 ，默认显示正常情况
      * 默认显示正常情况：下面四种情况菜单-》推荐（需要登录），热度，评论，点赞;
      
 * 引导页
    * https://github.com/PaoloRotolo/AppIntro
 * 透明高亮指示层
    * https://github.com/hongyangAndroid/Highlight

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
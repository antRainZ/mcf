### 文件夹说明
+ article: 文章管理这块的相关类
+ common: 公共类
  + annotation: 定义数据源、excel表格、日志、防止表单重复提交主键
  + config: 项目名称、版本等信息
  + constant: 定义通用、代码生成、任务调度、用户常量信息
  + core: 核心类
    + controller: BaseController web层通用数据处理
    + domain: 实体类，树实体
      + entity: 系统核心一些实体类
      + model: 登录模型
      + ajaxResult: 返回给前台的结果类
      + BaseEntity：用于被继承的通用实体类
    + page: 分页信息
    + redis: redis缓存操作
  + enums: 操作状态、业务操作类型、请求方式、用户状态等枚举类
  + exception: 文件、调度、用户等异常信息类
  + filter: 表单重复提交、XSS攻击过滤器
  + utils: 文件、http、ip、excel、反射、spring、sql、日期、字典等工具类
    + SecurityUtils: 获取用户账户、获取用户、获取Authentication、加密
    + ServletUtils：获取String、Integer参数（设置默认值）、request、response、session、将字符串渲染到客户端、判断是否是Ajax异步请求
    + Threads：线程相关工具类 sleep、停止线程池、打印线程异常信息
+ community: 社区版本相关内容
+ framework: 框架相关处理
  + aspectj: 数据过滤处理、多数据源处理、操作日志记录处理
  + config: druid 配置属性
    + ApplicationConfig：时区配置
    + CaptchaConfig：验证码配置(hutool 相关功能)
    + FastJson2JsonRedisSerializer：Redis使用FastJson序列化
    + FilterConfig：过滤器配置
    + MybatisPlusConfig：分页、乐观锁、阻断插件
    + RedisConfig
    + ResourcesConfig： 本地文件上传路径、swagger配置、自定义拦截规则、跨域配置
    + SecurityConfig： 拦截的路径、强散列哈希加密实现、身份认证接口
    + ServerConfig：获取完整的请求路径，包括：域名，端口，上下文访问路径
    + ThreadPoolConfig：线程池配置
    + ValidatorConfig
  + datasource：数据源切换处理
  + interceptor：防止重复提交拦截器
  + manager：
    + AsyncFactory：异步工厂（产生任务用）
    + AsyncManager：异步任务管理器
    + ShutdownManager：确保应用退出时能关闭后台线程
  + MetaObjectHandler
  + security
    + JwtAuthenticationTokenFilter：验证token有效性
    + AuthenticationEntryPointImpl：认证失败处理类 返回未授权
    + LogoutSuccessHandlerImpl：自定义退出处理类
  + web
    + domain: 服务器相关信息
    + GlobalExceptionHandler: 全局异常处理器
    + service:
      + PermissionService: 自定义权限实现
      + SysLoginService: 登录校验方法
      + SysPermissionService: 用户权限处理
      + TokenService: token验证处理
      + UserDetailsServiceImpl: 用户验证处理
+ generator: 代码生成器相关
+ quartz: 定时任务
+ system: 系统相关mvc
+ user: 社区前端用户、积分版块
+ web: 
  + common
    + CaptchaController: 验证码操作处理
    + CommonController: 通用请求处理
  + monitor
    + CacheController: 缓存监控
    + ServerController: 服务器监控
    + SysLogininforController: 系统访问记录
    + SysOperlogController: 操作日志记录
    + SysUserOnlineController: 在线用户监控
  + system: 系统相关controller
  + config: SwaggerConfig

### 

@[TOC](springboot+vue 问题记录)

记录近期使用springboot+vue开发项目的遇到问题，及其解决方案

## mysql8安装
+ mysql8 的 `my.ini ` 配置信息

```bash
[client]
# 设置mysql客户端默认字符集
default-character-set=utf8
 
[mysqld]
# 设置3306端口
port = 3306
# 设置mysql的安装目录
basedir=
# 设置 mysql数据库的数据的存放目录，MySQL 8+ 不需要以下配置，系统自己生成即可，否则有可能报错
# datadir=
# 允许最大连接数
max_connections=1000
# 服务端使用的字符集默认为8比特编码的latin1字符集
character-set-server=utf8
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
```
+ 注意执行mysql命令，包含的文件路径的参数中最好不要带中文，可能会出错的
+ mysql8 一些语法在mysql5上不兼容，重新安装mysql8, 使用zip安装mysql8 步骤

```bash
# 已管理员的身份运行cmd, 切换目录mysql的bin目录下
# 初始化数据库, 需要删除mysql的data目录， 记录下输出的临时密码
mysqld --initialize --console
# 如果遇到问题可以移除之前mysqld， 并且在任务管理器结束旧版本的 mysqld 运行任务
mysqld remove
# 安装命令
mysqld install
# 启动mysql服务
net start mysql
```
+ 登录并修改密码

```sql
-- 格式 mysql -h 主机名 -u 用户名 -p 回车后输入密码
mysql -u root -p 
-- 为了后面方便的连接，使用原生的加密方式
alter user 'root'@'localhost' identified with mysql_native_password by '654321';
-- 刷新
flush privileges;
-- 数据导出
mysqldump -u root -p 数据库名 > 输出文件路径及文件名
```

+ java 连接 `jdbc:mysql://127.0.0.1:3306/databaseName?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8&autoReconnect=true&&useAffectedRows=true` 
	+ 需要设置时区，不然会报错 `serverTimezone=GMT%2B8` 
	+ 默认情况下，mybatis 的 update 操作的返回值是 matched 的记录数，并不是受影响的记录数。`useAffectedRows=true` 表示使用受影响的行的数量进行返回
	+ mysql8 推荐连接驱动 `com.mysql.cj.jdbc.Driver`
+  在为表格，字段起名的时候注意不要和mysql的关键字和保留字冲突，可以在sql语句中使用反引号 

## redis连接
+ [windows安装](https://github.com/tporadowski/redis/releases)
+ 启动命令：`redis-server.exe redis.windows.conf`
+ 使用java连接，注意存放redis文件路径中不要包含中文名，可能会出错的
+ 连接超时：`io.lettuce.core.RedisCommandTimeoutException: Command timed out`  尝试重启一下redis服务
+ 配置信息

```yml
spring:
  redis:
    # Redis数据库索引（默认为0）
    database: 0
    # Redis服务器地址，单机
    host: 127.0.0.1
    # Redis服务器连接端口，单机
    port: 6379
    # Redis服务器连接密码（默认为空）
    password:
    # 连接超时时间（毫秒）
    timeout: 5000
```

## 邮件服务
+ 如果在使用 qq邮箱中的POP3/SMTP，发完短信后，点击我已发送， 弹框页面一直空白，尝试使用 win+r 运行 `regsvr32 jscript.dll` 即可
+ axios 默认不发送cookie，所以在使用session 存储验证码信息的信息，需要设置 `axios.defaults.withCredentials = true`

## mybatisplus
+ queryWrapper.select(),只有一次有效，eq() 可以多次调用，链式调用

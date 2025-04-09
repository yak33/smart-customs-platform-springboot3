# 智慧关务平台 (Smart Customs Platform)

## 项目介绍

智慧关务平台是一套基于 SpringBoot 3 和 Java 21 构建的现代化海关业务管理系统。该平台旨在通过数字化手段优化海关业务流程，提高通关效率，加强监管能力，为关务人员和进出口企业提供全方位的信息化支持。

## 技术架构

### 开发环境

- JDK 21
- SpringBoot 3
- MySQL 8 or 5.7
- Redis
- Maven

### 项目模块

项目采用多模块架构设计，各模块职责明确，便于维护和扩展：

1. **基础模块 (smart-customs-base)**

   - 通用工具类和基础功能封装
   - 数据库相关配置(MySQL, Redis, MyBatis Plus)
   - 对象存储服务(OSS)
   - 身份认证(Sa-Token)
   - 定时任务(Quartz)
   - 代码生成器

2. **基础设施模块 (smart-customs-infra)**

   - 提供系统基础设施支持
   - 服务整合与集成
   - 外部系统对接

3. **系统模块 (smart-customs-system)**

   - 系统管理功能
   - 用户权限管理
   - 系统监控
   - 开发工具

4. **业务模块 (smart-customs-biz)**

   - 核心业务功能实现
   - 关务业务处理
   - 业务流程管理

5. **服务入口 (smart-customs-starter)**
   - 应用启动入口
   - 全局配置

## 主要功能

- 用户与权限管理：基于 RBAC 模型的细粒度权限控制
- 系统监控：实时监控服务器性能与应用状态
- 数据管理：支持多数据源配置和动态数据源切换
- 定时任务：可视化任务调度与管理
- 日志管理：操作日志、异常日志记录与查询
- 文件管理：基于 Minio 的文件上传与管理
- API 文档：集成 Knife4j 提供在线 API 文档
- 代码生成：快速生成标准化代码，提高开发效率

## 快速开始

### 环境准备

1. 安装 JDK 21
2. 安装 MySQL 5.7+
3. 安装 Redis
4. 安装 Maven

### 数据库初始化

1. 创建数据库：smart-customs-platform
2. 执行初始化脚本：
   - MySQL 8.x: `db/smart-customs-platform.sql`
   - MySQL 5.7: `db/smart-customs-platform_mysql5.7.sql`

### 配置修改

根据实际环境修改配置文件：

```
application.yml
application-dev.yml
application-prod.yml
```

主要配置项：

- 数据库连接信息
- Redis 连接信息
- 文件存储配置
- 系统参数配置

### 编译与运行

```bash
# 项目编译
mvn clean package -DskipTests

# 开发环境运行
java -jar smart-customs-starter/target/smart-customs-starter-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev

# 生产环境运行
java -jar smart-customs-starter/target/smart-customs-starter-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

## 开发指南

### 代码生成

系统集成了代码生成器，可以根据数据库表结构自动生成标准化的控制器、服务、实体类、映射器等代码，大幅提高开发效率。

### 命名规范

- 类名：驼峰命名法，首字母大写
- 方法名：驼峰命名法，首字母小写
- 变量名：驼峰命名法，首字母小写
- 常量名：全大写，下划线分隔
- 包名：全小写

### 提交规范

提交代码时，请遵循以下规范：

- feat: 新功能
- fix: 修复 bug
- docs: 文档变更
- style: 代码格式调整
- refactor: 代码重构
- perf: 性能优化
- test: 测试相关
- chore: 构建过程或辅助工具的变动

## 部署指南

### 单机部署

参考"快速开始"章节的运行命令，使用 nohup 或 systemd 等方式保证服务持久运行。

### 集群部署

1. 配置负载均衡(如 Nginx)
2. 配置多实例共享会话(基于 Redis)
3. 调整各节点配置保持一致

## 常见问题

1. **Q: 如何修改默认端口?**  
   A: 在 application.yml 中修改 server.port 配置项

2. **Q: 如何添加新的业务模块?**  
   A: 在 smart-customs-biz 下创建新的子模块，并在主 pom.xml 中添加模块引用

3. **Q: 如何自定义用户认证逻辑?**  
   A: 在 smart-customs-base-sa-token 模块中修改相关配置和实现类

## 参与贡献

1. Fork 本项目
2. 创建新的功能分支 (git checkout -b feature/your-feature-name)
3. 提交变更 (git commit -m 'Add some feature')
4. 推送到远程分支 (git push origin feature/your-feature-name)
5. 创建 Pull Request

## 许可证

[MIT License](LICENSE)

## 联系方式

- 邮箱：woaimamaa@foxmail.com

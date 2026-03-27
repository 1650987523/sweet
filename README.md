# Sweet 项目文档

## 项目简介

Sweet 是一个基于 Spring Boot 3 的**智慧餐饮/零售管理系统**，支持多门店运营、商品管理、订单处理、退款管理等核心业务功能。系统采用前后端分离架构，提供管理后台（sweet-admin）和小程序端（sweet-app）两个应用。

## 技术栈

### 核心框架
- **JDK 21**
- **Spring Boot 3.5.8**
- **MyBatis-Plus 3.5.15** - ORM 框架
- **Sa-Token 1.44.0** - 权限认证框架

### 数据库
- **PostgreSQL 42.7.8** - 主数据库
- **Dynamic-Datasource 4.5.0** - 多数据源支持

### 工具与依赖
- **Lombok** - 简化 Java 代码
- **Hutool 5.8.5** - Java 工具类库
- **SpringDoc 2.8.14** - OpenAPI 3/Swagger 文档
- **微信小程序 SDK** - 微信登录、支付
- **Cloudflare R2** - 对象存储（图片等）

### 构建工具
- **Maven** - 项目构建与依赖管理

## 项目结构

```
sweet/
├── sweet-admin/          # 管理后台服务端
│   ├── controller/       # 管理后台控制器
│   ├── service/          # 管理后台业务层
│   ├── entity/           # 实体类
│   ├── dto/              # 数据传输对象
│   └── vo/               # 视图对象
├── sweet-app/            # 小程序端服务端
│   ├── controller/       # 小程序控制器
│   ├── service/          # 小程序业务层
│   └── dto/vo/           # 数据传输对象
├── sweet-service/        # 核心业务服务层（共享）
│   ├── entity/           # 核心实体类
│   ├── service/          # 核心业务接口与实现
│   ├── mapper/           # MyBatis 映射器
│   └── dto/vo/           # 共享 DTO/VO
├── sweet-common/         # 公共模块
│   ├── constant/         # 常量定义
│   ├── enums/            # 枚举类
│   ├── response/         # 统一响应
│   └── util/             # 工具类
├── sweet-security/       # 安全认证模块
├── sweet-doc/            # 文档配置模块
├── sweet-oss/            # 对象存储服务模块
└── pom.xml               # Maven 父工程
```

## 核心功能模块

### 1. 订单管理
- 订单创建、查询、取消
- 订单状态流转（待支付→制作中→已完成/已取消）
- 退款申请与审核（待审核→审核通过/拒绝→已退款/驳回）
- 支持多种订单类型（到店自提、外卖配送、堂食）

### 2. 商品管理
- 商品分类（支持多级分类）
- 商品 SKU 管理
- 商品属性与规格
- 库存管理

### 3. 用户管理
- 微信小程序登录
- 用户信息管理
- 用户等级体系

### 4. 门店管理
- 多门店支持
- 门店信息配置
- 门店订单隔离

### 5. 退款管理
- 用户申请退款
- 门店审核退款
- 退款状态跟踪

### 6. 其他功能
- 轮播图管理
- 桌台二维码
- 优惠券

## 数据库设计

- **Schema**: `app`
- **表命名规范**: 复数形式，如 `order_main`、`order_detail`、`product_sku`

### 核心表
| 表名 | 说明 |
|------|------|
| `order_main` | 订单主表 |
| `order_detail` | 订单明细表 |
| `order_refund` | 退款记录表 |
| `product` | 商品表 |
| `product_sku` | 商品 SKU 表 |
| `product_category` | 商品分类表 |
| `app_user` | 用户表 |
| `store` | 门店表 |

## 订单状态流转

```
待支付 (0) → 制作中 (1) → 已完成 (2)
    ↓            ↓
  已取消 (3)   退款中 (4) → 已退款 (5) / 驳回 (6)
```

## 退款状态

| 状态码 | 说明 |
|-------|------|
| 0 | 待审核 |
| 1 | 审核通过 |
| 2 | 审核拒绝 |
| 3 | 退款成功 |
| 4 | 退款失败 |

## 开发与部署

### 环境要求
- JDK 21+
- Maven 3.8+
- PostgreSQL 14+

### 构建命令
```bash
# 编译项目
mvn clean install

# 启动管理后台
cd sweet-admin && mvn spring-boot:run

# 启动小程序端
cd sweet-app && mvn spring-boot:run
```

### 配置说明
项目使用 YAML 配置文件，主要配置项：
- 数据库连接（PostgreSQL）
- Sa-Token 认证配置
- 微信小程序配置（AppID、AppSecret）
- Cloudflare R2 存储配置

## API 文档
启动项目后访问：`http://localhost:8080/swagger-ui.html`

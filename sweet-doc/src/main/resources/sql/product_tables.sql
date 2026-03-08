-- 商品表结构重构 SQL 脚本
-- PostgreSQL DDL for product tables
-- Created: 2026-03-01

-- ============================================
-- 1. 商品分类表 (product_category)
-- ============================================
CREATE TABLE IF NOT EXISTS app.product_category (
    id BIGSERIAL PRIMARY KEY,
    store_id BIGINT NOT NULL DEFAULT 0,
    parent_id INTEGER NOT NULL DEFAULT 0,
    category_name VARCHAR(100) NOT NULL,
    icon TEXT,
    sort INTEGER NOT NULL DEFAULT 0,
    status SMALLINT NOT NULL DEFAULT 1,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE app.product_category IS '商品分类表';
COMMENT ON COLUMN app.product_category.id IS '分类 ID（自增主键）';
COMMENT ON COLUMN app.product_category.store_id IS '店铺 ID';
COMMENT ON COLUMN app.product_category.parent_id IS '父分类 ID（0 表示一级分类）';
COMMENT ON COLUMN app.product_category.category_name IS '分类名称';
COMMENT ON COLUMN app.product_category.icon IS '分类图标 (iconify 图标名称，如 ri:coffee-line)';
COMMENT ON COLUMN app.product_category.sort IS '排序序号（数字越小越靠前）';
COMMENT ON COLUMN app.product_category.status IS '分类状态（1=启用，2=禁用）';
COMMENT ON COLUMN app.product_category.create_time IS '创建时间';
COMMENT ON COLUMN app.product_category.update_time IS '更新时间';

CREATE INDEX idx_product_category_store_id ON app.product_category(store_id);
CREATE INDEX idx_product_category_parent_id ON app.product_category(parent_id);
CREATE INDEX idx_product_category_status ON app.product_category(status);
CREATE INDEX idx_product_category_sort ON app.product_category(sort);

-- ============================================
-- 2. 商品表 (product)
-- ============================================
CREATE TABLE IF NOT EXISTS app.product (
    id BIGSERIAL PRIMARY KEY,
    store_id BIGINT NOT NULL DEFAULT 0,
    product_name VARCHAR(200) NOT NULL,
    sub_title VARCHAR(500),
    category_id BIGINT,
    description TEXT,
    price BIGINT,
    market_price BIGINT,
    member_price BIGINT,
    cost_price BIGINT,
    stock INTEGER NOT NULL DEFAULT 0,
    sales INTEGER NOT NULL DEFAULT 0,
    unit VARCHAR(50),
    images TEXT,
    detail TEXT,
    tags JSONB,
    sort INTEGER NOT NULL DEFAULT 0,
    is_hot BOOLEAN NOT NULL DEFAULT FALSE,
    is_recommend BOOLEAN NOT NULL DEFAULT FALSE,
    is_new BOOLEAN NOT NULL DEFAULT FALSE,
    spec_names JSONB,
    status SMALLINT NOT NULL DEFAULT 1,
    delete_time TIMESTAMP,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    create_by VARCHAR(100),
    update_by VARCHAR(100)
);

COMMENT ON TABLE app.product IS '商品表';
COMMENT ON COLUMN app.product.id IS '商品 ID（自增主键）';
COMMENT ON COLUMN app.product.store_id IS '店铺 ID';
COMMENT ON COLUMN app.product.product_name IS '商品名称';
COMMENT ON COLUMN app.product.sub_title IS '商品副标题（简短描述）';
COMMENT ON COLUMN app.product.category_id IS '分类 ID';
COMMENT ON COLUMN app.product.description IS '商品详细描述';
COMMENT ON COLUMN app.product.price IS '售价（单位：分）';
COMMENT ON COLUMN app.product.market_price IS '市场价（单位：分）';
COMMENT ON COLUMN app.product.member_price IS '会员价（单位：分）';
COMMENT ON COLUMN app.product.cost_price IS '成本价（单位：分）';
COMMENT ON COLUMN app.product.stock IS '库存数量';
COMMENT ON COLUMN app.product.sales IS '销量';
COMMENT ON COLUMN app.product.unit IS '计量单位';
COMMENT ON COLUMN app.product.images IS '商品图片 URL（多个图片用逗号分隔）';
COMMENT ON COLUMN app.product.detail IS '商品详细描述（HTML）';
COMMENT ON COLUMN app.product.tags IS '商品标签（JSON 数组）';
COMMENT ON COLUMN app.product.sort IS '排序序号（数字越小越靠前）';
COMMENT ON COLUMN app.product.is_hot IS '是否热卖（0=否，1=是）';
COMMENT ON COLUMN app.product.is_recommend IS '是否推荐（0=否，1=是）';
COMMENT ON COLUMN app.product.is_new IS '是否新品（0=否，1=是）';
COMMENT ON COLUMN app.product.spec_names IS '商品规格定义（JSON 格式）';
COMMENT ON COLUMN app.product.status IS '商品状态（0=禁用，1=正常，2=下架）';
COMMENT ON COLUMN app.product.delete_time IS '删除时间（软删除）';
COMMENT ON COLUMN app.product.create_time IS '创建时间';
COMMENT ON COLUMN app.product.update_time IS '更新时间';
COMMENT ON COLUMN app.product.create_by IS '创建人（用户名/ID）';
COMMENT ON COLUMN app.product.update_by IS '更新人（用户名/ID）';

CREATE INDEX idx_product_store_id ON app.product(store_id);
CREATE INDEX idx_product_category_id ON app.product(category_id);
CREATE INDEX idx_product_status ON app.product(status);
CREATE INDEX idx_product_sort ON app.product(sort);
CREATE INDEX idx_product_is_hot ON app.product(is_hot);
CREATE INDEX idx_product_is_recommend ON app.product(is_recommend);
CREATE INDEX idx_product_is_new ON app.product(is_new);
CREATE INDEX idx_product_delete_time ON app.product(delete_time);

-- ============================================
-- 3. 商品 SKU 表 (product_sku)
-- ============================================
CREATE TABLE IF NOT EXISTS app.product_sku (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    title VARCHAR(200) NOT NULL,
    image VARCHAR(500),
    stock INTEGER NOT NULL DEFAULT 0,
    price BIGINT,
    specs JSONB,
    status SMALLINT NOT NULL DEFAULT 0,
    sort INTEGER NOT NULL DEFAULT 0,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    create_by VARCHAR(100),
    update_by VARCHAR(100)
);

COMMENT ON TABLE app.product_sku IS '商品 SKU 表';
COMMENT ON COLUMN app.product_sku.id IS 'SKU ID（自增主键）';
COMMENT ON COLUMN app.product_sku.product_id IS '关联商品 ID';
COMMENT ON COLUMN app.product_sku.title IS 'SKU 标题（规格组合描述）';
COMMENT ON COLUMN app.product_sku.image IS 'SKU 图片 URL';
COMMENT ON COLUMN app.product_sku.stock IS '库存数量';
COMMENT ON COLUMN app.product_sku.price IS '售价（单位：分）';
COMMENT ON COLUMN app.product_sku.specs IS 'SKU 规格定义（JSON 格式）';
COMMENT ON COLUMN app.product_sku.status IS 'SKU 状态（0=禁用，1=正常，2=售罄）';
COMMENT ON COLUMN app.product_sku.sort IS '排序序号';
COMMENT ON COLUMN app.product_sku.create_time IS '创建时间';
COMMENT ON COLUMN app.product_sku.update_time IS '更新时间';
COMMENT ON COLUMN app.product_sku.create_by IS '创建人（用户名/ID）';
COMMENT ON COLUMN app.product_sku.update_by IS '更新人（用户名/ID）';

CREATE INDEX idx_product_sku_product_id ON app.product_sku(product_id);
CREATE INDEX idx_product_sku_status ON app.product_sku(status);
CREATE INDEX idx_product_sku_sort ON app.product_sku(sort);

-- ============================================
-- 4. 商品属性关联表 (product_attribute)
-- ============================================
CREATE TABLE IF NOT EXISTS app.product_attribute (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    attr_name VARCHAR(100) NOT NULL,
    attr_values JSONB,
    required BOOLEAN NOT NULL DEFAULT FALSE,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE app.product_attribute IS '商品属性关联表';
COMMENT ON COLUMN app.product_attribute.id IS '属性 ID（自增主键）';
COMMENT ON COLUMN app.product_attribute.product_id IS '关联商品 ID';
COMMENT ON COLUMN app.product_attribute.attr_name IS '属性名（如：辣度、甜度、温度）';
COMMENT ON COLUMN app.product_attribute.attr_values IS '属性值列表（JSONB）';
COMMENT ON COLUMN app.product_attribute.required IS '是否必填';
COMMENT ON COLUMN app.product_attribute.create_time IS '创建时间';

CREATE INDEX idx_product_attribute_product_id ON app.product_attribute(product_id);
CREATE INDEX idx_product_attribute_attr_name ON app.product_attribute(attr_name);

-- ============================================
-- 5. 商品操作日志表 (product_log)
-- ============================================
CREATE TABLE IF NOT EXISTS app.product_log (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    operator_id BIGINT,
    operator_name VARCHAR(100),
    action VARCHAR(50) NOT NULL,
    content TEXT,
    before_data JSONB,
    after_data JSONB,
    ip VARCHAR(50),
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE app.product_log IS '商品操作日志表';
COMMENT ON COLUMN app.product_log.id IS '日志 ID（自增主键）';
COMMENT ON COLUMN app.product_log.product_id IS '关联商品 ID';
COMMENT ON COLUMN app.product_log.operator_id IS '操作人 ID';
COMMENT ON COLUMN app.product_log.operator_name IS '操作人名称';
COMMENT ON COLUMN app.product_log.action IS '操作类型（如：创建、更新、删除、上下架）';
COMMENT ON COLUMN app.product_log.content IS '操作内容描述';
COMMENT ON COLUMN app.product_log.before_data IS '操作前数据（JSONB）';
COMMENT ON COLUMN app.product_log.after_data IS '操作后数据（JSONB）';
COMMENT ON COLUMN app.product_log.ip IS '操作 IP';
COMMENT ON COLUMN app.product_log.create_time IS '创建时间';

CREATE INDEX idx_product_log_product_id ON app.product_log(product_id);
CREATE INDEX idx_product_log_operator_id ON app.product_log(operator_id);
CREATE INDEX idx_product_log_action ON app.product_log(action);
CREATE INDEX idx_product_log_create_time ON app.product_log(create_time);

-- ============================================
-- 初始化示例数据
-- ============================================

-- 商品分类示例数据
INSERT INTO app.product_category (store_id, parent_id, category_name, icon, sort, status) VALUES
(1, 0, '奶茶系列', '', 1, 1),
(1, 0, '果茶系列', '', 2, 1),
(1, 0, '咖啡系列', '', 3, 1),
(1, 0, '小食系列', '', 4, 1),
(1, 1, '经典奶茶', '', 1, 1),
(1, 1, '鲜奶奶茶', '', 2, 1),
(1, 2, '柠檬茶', '', 1, 1),
(1, 2, '水果茶', '', 2, 1);

-- 商品示例数据
INSERT INTO app.product (store_id, product_name, sub_title, category_id, description, price, market_price, member_price, cost_price, stock, sales, unit, images, detail, tags, sort, is_hot, is_recommend, is_new, spec_names, status) VALUES
(1, '珍珠奶茶', '经典口味，Q 弹珍珠', 5, '选用优质红茶和牛奶，搭配Q 弹珍珠，口感丰富', 1500, 1800, 1350, 800, 500, 1230, '杯', 'image1.jpg,image2.jpg', '<p>详细描述</p>', '{"tags": ["推荐", "经典"]}', 1, TRUE, TRUE, FALSE, '{"甜度":["全糖","半糖","无糖"],"冰度":["正常冰","少冰","去冰","热饮"],"杯型":["中杯","大杯"]}', 1),
(1, '柠檬红茶', '清新解腻', 7, '新鲜柠檬搭配精选红茶，清新爽口', 1200, 1500, 1080, 600, 300, 856, '杯', 'lemon-tea.jpg', '<p>详细描述</p>', '{"tags": ["清爽"]}', 2, FALSE, TRUE, FALSE, '{"甜度":["全糖","半糖","微糖"],"冰度":["正常冰","少冰","去冰"]}', 1),
(1, '拿铁咖啡', '现磨咖啡豆', 3, '100% 阿拉比卡咖啡豆，现磨现做', 2200, 2800, 1980, 1200, 200, 432, '杯', 'latte.jpg', '<p>详细描述</p>', '{"tags": ["咖啡", "推荐"]}', 3, TRUE, FALSE, TRUE, '{"杯型":["中杯","大杯"],"浓度":["单份","双份"]}', 1);

-- 商品 SKU 示例数据
INSERT INTO app.product_sku (product_id, title, image, stock, price, specs, status, sort) VALUES
(1, '珍珠奶茶 - 全糖 - 正常冰 - 中杯', '', 100, 1500, '{"甜度":"全糖","冰度":"正常冰","杯型":"中杯"}', 1, 1),
(1, '珍珠奶茶 - 半糖 - 少冰 - 中杯', '', 100, 1500, '{"甜度":"半糖","冰度":"少冰","杯型":"中杯"}', 1, 2),
(1, '珍珠奶茶 - 无糖 - 去冰 - 大杯', '', 80, 1800, '{"甜度":"无糖","冰度":"去冰","杯型":"大杯"}', 1, 3),
(2, '柠檬红茶 - 全糖 - 正常冰', '', 50, 1200, '{"甜度":"全糖","冰度":"正常冰"}', 1, 1),
(2, '柠檬红茶 - 半糖 - 少冰', '', 50, 1200, '{"甜度":"半糖","冰度":"少冰"}', 1, 2),
(3, '拿铁咖啡 - 中杯 - 单份', '', 30, 2200, '{"杯型":"中杯","浓度":"单份"}', 1, 1),
(3, '拿铁咖啡 - 大杯 - 双份', '', 30, 2600, '{"杯型":"大杯","浓度":"双份"}', 1, 2);

-- 商品属性示例数据
INSERT INTO app.product_attribute (product_id, attr_name, attr_values, required) VALUES
(1, '辣度', '{"values": ["不辣", "微辣", "中辣", "特辣"]}', FALSE),
(1, '甜度', '{"values": ["全糖", "半糖", "无糖"]}', TRUE),
(2, '甜度', '{"values": ["全糖", "半糖", "微糖"]}', TRUE),
(3, '浓度', '{"values": ["单份", "双份"]}', FALSE);

-- 商品操作日志示例数据
INSERT INTO app.product_log (product_id, operator_id, operator_name, action, content, before_data, after_data, ip) VALUES
(1, 1, 'admin', 'CREATE', '创建商品：珍珠奶茶', NULL, '{"id": 1, "name": "珍珠奶茶"}', '192.168.1.100'),
(1, 1, 'admin', 'UPDATE', '更新商品价格', '{"price": 1800}', '{"price": 1500}', '192.168.1.100'),
(2, 1, 'admin', 'CREATE', '创建商品：柠檬红茶', NULL, '{"id": 2, "name": "柠檬红茶"}', '192.168.1.100'),
(3, 2, 'operator2', 'CREATE', '创建商品：拿铁咖啡', NULL, '{"id": 3, "name": "拿铁咖啡"}', '192.168.1.101');

-- 订单主表
CREATE SCHEMA IF NOT EXISTS app;

CREATE TABLE app.order_main (
    id SERIAL PRIMARY KEY,
    order_no VARCHAR(32) NOT NULL UNIQUE COMMENT '订单编号',
    user_id INT NOT NULL COMMENT '用户 ID',
    store_id INT NOT NULL COMMENT '门店 ID',
    username VARCHAR(50) COMMENT '用户姓名',
    mobile VARCHAR(20) NOT NULL COMMENT '联系电话',
    total_amount BIGINT NOT NULL COMMENT '订单总金额（分）',
    goods_amount BIGINT NOT NULL COMMENT '商品金额（分）',
    delivery_fee BIGINT DEFAULT 0 COMMENT '配送费（分）',
    discount_amount BIGINT DEFAULT 0 COMMENT '优惠金额（分）',
    pay_amount BIGINT NOT NULL COMMENT '实付金额（分）',
    pay_type INT DEFAULT 0 COMMENT '支付方式',
    pay_status INT DEFAULT 0 COMMENT '支付状态',
    order_status INT DEFAULT 0 COMMENT '订单状态',
    order_type INT DEFAULT 1 COMMENT '订单类型',
    take_address VARCHAR(255) COMMENT '取餐/配送地址',
    take_time TIMESTAMP COMMENT '预计取餐/送达时间',
    store_name VARCHAR(100) COMMENT '门店名称',
    coupon_id VARCHAR(100) COMMENT '优惠券 ID',
    remark VARCHAR(255) COMMENT '订单备注',
    cancel_reason VARCHAR(255) COMMENT '取消原因',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    create_by VARCHAR(50) COMMENT '创建人',
    update_by VARCHAR(50) COMMENT '更新人'
);

COMMENT ON TABLE app.order_main IS '订单主表';
COMMENT ON COLUMN app.order_main.order_no IS '订单编号（唯一标识）';
COMMENT ON COLUMN app.order_main.pay_type IS '支付方式：0=未选择，1=微信支付，2=支付宝支付，3=现金支付';
COMMENT ON COLUMN app.order_main.pay_status IS '支付状态：0=未支付，1=已支付，2=支付失败，3=退款中，4=已退款';
COMMENT ON COLUMN app.order_main.order_status IS '订单状态：0=待支付，1=待接单，2=待配送，3=已完成，4=已取消，5=退款中';
COMMENT ON COLUMN app.order_main.order_type IS '订单类型：1=到店自提，2=外卖配送，3=堂食订单';

-- 订单明细表
CREATE TABLE app.order_detail (
    id SERIAL PRIMARY KEY,
    order_no VARCHAR(32) NOT NULL COMMENT '订单编号',
    product_id INT NOT NULL COMMENT '商品 ID',
    sku_id INT NOT NULL COMMENT 'SKU ID',
    product_name VARCHAR(100) NOT NULL COMMENT '商品名称',
    sku_title VARCHAR(200) NOT NULL COMMENT 'SKU 标题',
    sku_spec JSONB COMMENT 'SKU 规格详情',
    price BIGINT NOT NULL COMMENT '商品单价（分）',
    quantity INT DEFAULT 1 COMMENT '购买数量',
    subtotal BIGINT NOT NULL COMMENT '小计金额（分）',
    product_image VARCHAR(255) COMMENT '商品图片 URL',
    item_remark VARCHAR(255) COMMENT '商品项备注',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
);

COMMENT ON TABLE app.order_detail IS '订单明细表';
COMMENT ON COLUMN app.order_detail.order_no IS '订单编号（关联 order_main 表的 order_no）';
COMMENT ON COLUMN app.order_detail.sku_spec IS 'SKU 规格详情（JSON 格式）';

-- 索引
CREATE INDEX idx_order_main_order_no ON app.order_main(order_no);
CREATE INDEX idx_order_main_user_id ON app.order_main(user_id);
CREATE INDEX idx_order_main_store_id ON app.order_main(store_id);
CREATE INDEX idx_order_main_order_status ON app.order_main(order_status);
CREATE INDEX idx_order_main_create_time ON app.order_main(create_time);

CREATE INDEX idx_order_detail_order_no ON app.order_detail(order_no);
CREATE INDEX idx_order_detail_product_id ON app.order_detail(product_id);
CREATE INDEX idx_order_detail_sku_id ON app.order_detail(sku_id);
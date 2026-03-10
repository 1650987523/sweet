-- 轮播图表 SQL 脚本
-- PostgreSQL DDL for banner table
-- Created: 2026-03-10

-- ============================================
-- 1. 轮播图表 (banner)
-- ============================================
CREATE TABLE IF NOT EXISTS app.banner (
    id BIGSERIAL PRIMARY KEY,
    store_id BIGINT NOT NULL DEFAULT 0,
    title VARCHAR(200) NOT NULL,
    image_url TEXT,
    link_url TEXT,
    link_type SMALLINT NOT NULL DEFAULT 1,
    link_params JSONB,
    sort_order INTEGER NOT NULL DEFAULT 0,
    status SMALLINT NOT NULL DEFAULT 1,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    create_by VARCHAR(100),
    update_by VARCHAR(100)
);

COMMENT ON TABLE app.banner IS '轮播图表';
COMMENT ON COLUMN app.banner.id IS '轮播图 ID（自增主键）';
COMMENT ON COLUMN app.banner.store_id IS '门店 ID：0=所有门店通用，>0=特定门店';
COMMENT ON COLUMN app.banner.title IS '轮播图标题';
COMMENT ON COLUMN app.banner.image_url IS '图片 URL';
COMMENT ON COLUMN app.banner.link_url IS '跳转链接 URL';
COMMENT ON COLUMN app.banner.link_type IS '跳转类型：1-商品 2-分类 3-活动 4-外链 5-页面';
COMMENT ON COLUMN app.banner.link_params IS '跳转参数（JSON 格式）';
COMMENT ON COLUMN app.banner.sort_order IS '排序序号（越大越靠前）';
COMMENT ON COLUMN app.banner.status IS '状态：0-下架 1-上架';
COMMENT ON COLUMN app.banner.start_time IS '开始时间';
COMMENT ON COLUMN app.banner.end_time IS '结束时间';
COMMENT ON COLUMN app.banner.create_time IS '创建时间';
COMMENT ON COLUMN app.banner.update_time IS '更新时间';
COMMENT ON COLUMN app.banner.create_by IS '创建者';
COMMENT ON COLUMN app.banner.update_by IS '更新者';

CREATE INDEX idx_banner_store_id ON app.banner(store_id);
CREATE INDEX idx_banner_status ON app.banner(status);
CREATE INDEX idx_banner_sort_order ON app.banner(sort_order);
CREATE INDEX idx_banner_start_time ON app.banner(start_time);
CREATE INDEX idx_banner_end_time ON app.banner(end_time);

-- ============================================
-- 初始化示例数据
-- ============================================
INSERT INTO app.banner (store_id, title, image_url, link_url, link_type, link_params, sort_order, status, start_time, end_time) VALUES
(0, '新品上市 - 草莓奶昔', 'https://example.com/banners/strawberry-milkshake.jpg', '/product/101', 1, '{"productId": 101}', 10, 1, NULL, NULL),
(0, '限时优惠 - 第二杯半价', 'https://example.com/banners/half-price-promo.jpg', '/activity/summer-promo', 3, '{"activityId": "summer-promo"}', 9, 1, NULL, NULL),
(1, '门店专享 - 满 20 减 5', 'https://example.com/banners/store-discount.jpg', '/coupon/store-5off', 3, '{"couponId": "store-5off"}', 8, 1, NULL, NULL),
(0, '会员专享折扣', 'https://example.com/banners/member-discount.jpg', '/member/benefits', 5, '{"page": "member-benefits"}', 7, 1, NULL, NULL),
(0, '外部链接 - 官方网站', 'https://example.com/banners/official-website.jpg', 'https://www.example.com', 4, NULL, 6, 1, NULL, NULL);

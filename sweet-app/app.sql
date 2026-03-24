--
-- PostgreSQL database dump
--


-- Dumped from database version 18.1 (Homebrew)
-- Dumped by pg_dump version 18.1 (Homebrew)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: app; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA app;


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: app_user; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.app_user (
                              id bigint NOT NULL,
                              username text,
                              password text,
                              nickname text,
                              email text,
                              mobile text,
                              douyin_openid text,
                              wechat_openid text,
                              alipay_openid text,
                              avatar text,
                              status integer DEFAULT 0,
                              create_time timestamp without time zone,
                              update_time timestamp without time zone,
                              create_by text,
                              update_by text,
                              login_type character varying(255),
                              level smallint
);


--
-- Name: COLUMN app_user.id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.id IS '主键ID（自增）';


--
-- Name: COLUMN app_user.username; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.username IS '用户名';


--
-- Name: COLUMN app_user.password; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.password IS '密码（BCrypt 加密存储，推荐优先使用BCrypt而非MD5）';


--
-- Name: COLUMN app_user.nickname; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.nickname IS '用户昵称';


--
-- Name: COLUMN app_user.email; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.email IS '用户邮箱';


--
-- Name: COLUMN app_user.mobile; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.mobile IS '手机号';


--
-- Name: COLUMN app_user.douyin_openid; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.douyin_openid IS '抖音openid';


--
-- Name: COLUMN app_user.wechat_openid; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.wechat_openid IS '微信openid';


--
-- Name: COLUMN app_user.alipay_openid; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.alipay_openid IS '支付宝openid';


--
-- Name: COLUMN app_user.avatar; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.avatar IS '用户头像url';


--
-- Name: COLUMN app_user.status; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.status IS '账号状态：0=正常，1=禁用，2=已注销';


--
-- Name: COLUMN app_user.create_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.create_time IS '创建时间';


--
-- Name: COLUMN app_user.update_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.update_time IS '更新时间';


--
-- Name: COLUMN app_user.create_by; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.create_by IS '创建人（用户名或ID）';


--
-- Name: COLUMN app_user.update_by; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.update_by IS '更新人（用户名或ID）';


--
-- Name: COLUMN app_user.login_type; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.login_type IS '登录方式 wechat alipay';


--
-- Name: COLUMN app_user.level; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.app_user.level IS '用户等级';


--
-- Name: app_user_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.app_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: app_user_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.app_user_id_seq OWNED BY app.app_user.id;


--
-- Name: attribute; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.attribute (
                               id bigint NOT NULL,
                               name character varying(50) NOT NULL,
                               type smallint NOT NULL,
                               status smallint DEFAULT 1,
                               create_time timestamp without time zone,
                               sort smallint DEFAULT 0,
                               update_time timestamp without time zone,
                               create_by text,
                               update_by text,
                               store_id bigint NOT NULL
);


--
-- Name: attribute_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.attribute_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: attribute_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.attribute_id_seq OWNED BY app.attribute.id;


--
-- Name: attribute_value; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.attribute_value (
                                     id bigint NOT NULL,
                                     attribute_id bigint NOT NULL,
                                     value character varying(50) NOT NULL,
                                     sort integer DEFAULT 0,
                                     status smallint DEFAULT 1,
                                     create_time timestamp without time zone,
                                     update_time timestamp without time zone,
                                     create_by text,
                                     update_by text
);


--
-- Name: attribute_value_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.attribute_value_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: attribute_value_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.attribute_value_id_seq OWNED BY app.attribute_value.id;


--
-- Name: banner; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.banner (
                            id bigint NOT NULL,
                            store_id bigint DEFAULT 0,
                            title text DEFAULT ''::text,
                            image_url text NOT NULL,
                            link_type smallint DEFAULT 1,
                            link_params jsonb DEFAULT '{}'::jsonb,
                            sort_order integer DEFAULT 0,
                            status smallint DEFAULT 1,
                            start_time timestamp without time zone,
                            end_time timestamp without time zone,
                            create_time timestamp without time zone,
                            update_time timestamp without time zone,
                            create_by text,
                            update_by text,
                            link_url text
);


--
-- Name: TABLE banner; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON TABLE app.banner IS '首页轮播图';


--
-- Name: COLUMN banner.id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.banner.id IS '主键 ID';


--
-- Name: COLUMN banner.store_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.banner.store_id IS '门店 ID：0=所有门店通用，>0=特定门店';


--
-- Name: COLUMN banner.title; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.banner.title IS '轮播图标题';


--
-- Name: COLUMN banner.image_url; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.banner.image_url IS '图片 URL';


--
-- Name: COLUMN banner.link_type; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.banner.link_type IS '跳转类型：1-商品 2-分类 3-活动 4-外链 5-页面';


--
-- Name: COLUMN banner.link_params; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.banner.link_params IS '跳转参数（JSON 格式）';


--
-- Name: COLUMN banner.sort_order; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.banner.sort_order IS '排序（越大越靠前）';


--
-- Name: COLUMN banner.status; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.banner.status IS '状态：0-下架 1-上架';


--
-- Name: COLUMN banner.start_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.banner.start_time IS '开始时间';


--
-- Name: COLUMN banner.end_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.banner.end_time IS '结束时间';


--
-- Name: COLUMN banner.create_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.banner.create_time IS '创建时间';


--
-- Name: COLUMN banner.update_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.banner.update_time IS '更新时间';


--
-- Name: COLUMN banner.create_by; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.banner.create_by IS '创建者';


--
-- Name: COLUMN banner.update_by; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.banner.update_by IS '更新者';


--
-- Name: COLUMN banner.link_url; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.banner.link_url IS '跳转地址';


--
-- Name: banner_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.banner_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: banner_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.banner_id_seq OWNED BY app.banner.id;


--
-- Name: order_detail; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.order_detail (
                                  id bigint NOT NULL,
                                  order_no character varying(32) NOT NULL,
                                  product_id bigint NOT NULL,
                                  product_name character varying(100) NOT NULL,
                                  sku_id bigint NOT NULL,
                                  sku_specs jsonb,
                                  price bigint NOT NULL,
                                  quantity integer DEFAULT 1 NOT NULL,
                                  subtotal bigint NOT NULL,
                                  item_remark character varying(255),
                                  create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                  sku_images jsonb
);


--
-- Name: TABLE order_detail; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON TABLE app.order_detail IS '订单明细表';


--
-- Name: COLUMN order_detail.id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_detail.id IS '明细 ID（自增主键）';


--
-- Name: COLUMN order_detail.order_no; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_detail.order_no IS '订单编号（关联 order_main）';


--
-- Name: COLUMN order_detail.product_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_detail.product_id IS '商品 ID';


--
-- Name: COLUMN order_detail.product_name; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_detail.product_name IS '商品名称（冗余字段）';


--
-- Name: COLUMN order_detail.sku_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_detail.sku_id IS 'SKU ID';


--
-- Name: COLUMN order_detail.sku_specs; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_detail.sku_specs IS 'SKU 规格（JSON 格式，如：{"甜度":"全糖"}）';


--
-- Name: COLUMN order_detail.price; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_detail.price IS '单价（单位：分）';


--
-- Name: COLUMN order_detail.quantity; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_detail.quantity IS '数量';


--
-- Name: COLUMN order_detail.subtotal; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_detail.subtotal IS '小计金额（单位：分）';


--
-- Name: COLUMN order_detail.item_remark; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_detail.item_remark IS '单项备注';


--
-- Name: COLUMN order_detail.create_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_detail.create_time IS '创建时间';


--
-- Name: COLUMN order_detail.sku_images; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_detail.sku_images IS 'sku图片';


--
-- Name: order_detail_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.order_detail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: order_detail_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.order_detail_id_seq OWNED BY app.order_detail.id;


--
-- Name: order_main; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.order_main (
                                id bigint NOT NULL,
                                order_no character varying(32) NOT NULL,
                                user_id bigint NOT NULL,
                                store_id bigint NOT NULL,
                                order_type smallint DEFAULT 1 NOT NULL,
                                order_status smallint DEFAULT 0 NOT NULL,
                                pay_type smallint DEFAULT 0,
                                pay_status smallint DEFAULT 0 NOT NULL,
                                goods_amount bigint DEFAULT 0 NOT NULL,
                                delivery_fee bigint DEFAULT 0 NOT NULL,
                                discount_amount bigint DEFAULT 0 NOT NULL,
                                total_amount bigint DEFAULT 0 NOT NULL,
                                pay_amount bigint DEFAULT 0 NOT NULL,
                                customer_name character varying(50),
                                customer_phone character varying(20),
                                delivery_addr character varying(255),
                                remark character varying(255),
                                cancel_reason character varying(255),
                                cancel_time timestamp without time zone,
                                pay_time timestamp without time zone,
                                confirm_time timestamp without time zone,
                                complete_time timestamp without time zone,
                                create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                store_name text,
                                coupon_id text,
                                create_by text,
                                update_by text,
                                take_time timestamp without time zone,
                                table_no text
);


--
-- Name: TABLE order_main; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON TABLE app.order_main IS '订单主表';


--
-- Name: COLUMN order_main.id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.id IS '订单 ID（自增主键）';


--
-- Name: COLUMN order_main.order_no; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.order_no IS '订单编号（唯一标识）';


--
-- Name: COLUMN order_main.user_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.user_id IS '用户 ID';


--
-- Name: COLUMN order_main.store_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.store_id IS '门店 ID';


--
-- Name: COLUMN order_main.order_type; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.order_type IS '订单类型：1=堂食 2=自提 3=外卖';


--
-- Name: COLUMN order_main.order_status; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.order_status IS '订单状态：0=待支付 1=待接单 2=制作中 3=待配送 4=已完成 5=已取消';


--
-- Name: COLUMN order_main.pay_type; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.pay_type IS '支付方式：0=未选择 1=微信 2=支付宝 3=现金';


--
-- Name: COLUMN order_main.pay_status; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.pay_status IS '支付状态：0=未支付 1=已支付 2=退款中 3=已退款';


--
-- Name: COLUMN order_main.goods_amount; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.goods_amount IS '商品金额（单位：分）';


--
-- Name: COLUMN order_main.delivery_fee; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.delivery_fee IS '配送费（单位：分）';


--
-- Name: COLUMN order_main.discount_amount; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.discount_amount IS '优惠金额（单位：分）';


--
-- Name: COLUMN order_main.total_amount; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.total_amount IS '订单总金额（单位：分）';


--
-- Name: COLUMN order_main.pay_amount; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.pay_amount IS '实付金额（单位：分）';


--
-- Name: COLUMN order_main.customer_name; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.customer_name IS '顾客姓名';


--
-- Name: COLUMN order_main.customer_phone; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.customer_phone IS '顾客电话';


--
-- Name: COLUMN order_main.delivery_addr; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.delivery_addr IS '配送地址';


--
-- Name: COLUMN order_main.remark; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.remark IS '订单备注';


--
-- Name: COLUMN order_main.cancel_reason; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.cancel_reason IS '取消原因';


--
-- Name: COLUMN order_main.cancel_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.cancel_time IS '取消时间';


--
-- Name: COLUMN order_main.pay_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.pay_time IS '支付时间';


--
-- Name: COLUMN order_main.confirm_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.confirm_time IS '接单时间';


--
-- Name: COLUMN order_main.complete_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.complete_time IS '完成时间';


--
-- Name: COLUMN order_main.create_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.create_time IS '创建时间';


--
-- Name: COLUMN order_main.update_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.update_time IS '更新时间';


--
-- Name: COLUMN order_main.store_name; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.store_name IS '冗余字段 门店名称';


--
-- Name: COLUMN order_main.coupon_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.coupon_id IS '优惠卷ID 多个使用逗号分隔';


--
-- Name: COLUMN order_main.create_by; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.create_by IS '创建者';


--
-- Name: COLUMN order_main.update_by; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.update_by IS '更新者';


--
-- Name: COLUMN order_main.take_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.take_time IS '预计取餐时间';


--
-- Name: COLUMN order_main.table_no; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.order_main.table_no IS '桌号';


--
-- Name: order_main_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.order_main_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: order_main_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.order_main_id_seq OWNED BY app.order_main.id;


--
-- Name: order_refund; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.order_refund (
                                  id bigint NOT NULL,
                                  refund_no text NOT NULL,
                                  order_no character varying(64) NOT NULL,
                                  user_id integer NOT NULL,
                                  store_id integer NOT NULL,
                                  order_amount bigint NOT NULL,
                                  refund_amount bigint,
                                  actual_refund_amount bigint,
                                  refund_type integer DEFAULT 1,
                                  refund_reason text,
                                  refund_desc text,
                                  refund_images jsonb,
                                  refund_status integer,
                                  audit_user_id integer,
                                  audit_user_name text,
                                  audit_time timestamp without time zone,
                                  audit_remark text,
                                  refund_success_time timestamp without time zone,
                                  refund_fail_reason text,
                                  create_time timestamp without time zone,
                                  update_time timestamp without time zone,
                                  create_by character varying(64),
                                  update_by character varying(64)
);


--
-- Name: order_refund_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.order_refund_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: order_refund_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.order_refund_id_seq OWNED BY app.order_refund.id;


--
-- Name: product; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.product (
                             id bigint NOT NULL,
                             store_id bigint NOT NULL,
                             category_id bigint CONSTRAINT product_category_id_not_null1 NOT NULL,
                             product_name character varying(200) NOT NULL,
                             sub_title character varying(500),
                             description text,
                             price numeric(10,2) DEFAULT 0 NOT NULL,
                             market_price numeric(10,2),
                             member_price numeric(10,2),
                             cost_price numeric(10,2),
                             stock integer DEFAULT '-1'::integer NOT NULL,
                             sales integer DEFAULT 0 NOT NULL,
                             unit character varying(20) DEFAULT '份'::character varying,
                             images jsonb DEFAULT '[]'::jsonb,
                             detail text,
                             tags jsonb DEFAULT '[]'::jsonb,
                             sort integer DEFAULT 0 NOT NULL,
                             status smallint DEFAULT 1 NOT NULL,
                             is_hot boolean DEFAULT false NOT NULL,
                             is_recommend boolean DEFAULT false NOT NULL,
                             is_new boolean DEFAULT false NOT NULL,
                             spec_names character varying(200),
                             create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
                             update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
                             delete_time timestamp without time zone,
                             create_by text,
                             update_by text,
                             slider_images jsonb
);


--
-- Name: TABLE product; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON TABLE app.product IS '商品主表';


--
-- Name: COLUMN product.id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.id IS '商品 ID';


--
-- Name: COLUMN product.store_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.store_id IS '门店 ID';


--
-- Name: COLUMN product.category_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.category_id IS '分类 ID';


--
-- Name: COLUMN product.product_name; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.product_name IS '商品名称';


--
-- Name: COLUMN product.sub_title; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.sub_title IS '商品副标题/卖点';


--
-- Name: COLUMN product.description; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.description IS '商品描述';


--
-- Name: COLUMN product.price; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.price IS '默认售价';


--
-- Name: COLUMN product.market_price; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.market_price IS '市场价/划线价';


--
-- Name: COLUMN product.member_price; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.member_price IS '会员价';


--
-- Name: COLUMN product.cost_price; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.cost_price IS '成本价（用于统计）';


--
-- Name: COLUMN product.stock; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.stock IS '库存：-1 表示无限';


--
-- Name: COLUMN product.sales; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.sales IS '销量';


--
-- Name: COLUMN product.unit; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.unit IS '单位：份、碗、杯';


--
-- Name: COLUMN product.images; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.images IS '商品图片列表 JSON';


--
-- Name: COLUMN product.detail; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.detail IS '商品详情（HTML）';


--
-- Name: COLUMN product.tags; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.tags IS '标签 JSON：["招牌","辣","推荐"]';


--
-- Name: COLUMN product.sort; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.sort IS '排序';


--
-- Name: COLUMN product.status; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.status IS '状态：1-上架 2-下架 3-售罄';


--
-- Name: COLUMN product.is_hot; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.is_hot IS '是否热卖';


--
-- Name: COLUMN product.is_recommend; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.is_recommend IS '是否推荐';


--
-- Name: COLUMN product.is_new; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.is_new IS '是否新品';


--
-- Name: COLUMN product.spec_names; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.spec_names IS '规格名列表："口味，甜度"';


--
-- Name: COLUMN product.create_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.create_time IS '创建时间';


--
-- Name: COLUMN product.update_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.update_time IS '更新时间';


--
-- Name: COLUMN product.delete_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.delete_time IS '删除时间（软删除）';


--
-- Name: COLUMN product.slider_images; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product.slider_images IS '商品轮播图';


--
-- Name: product_attribute_relation; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.product_attribute_relation (
                                                id bigint CONSTRAINT product_attribute_id_not_null NOT NULL,
                                                product_id bigint CONSTRAINT product_attribute_product_id_not_null NOT NULL,
                                                attribute_id bigint CONSTRAINT product_attribute_attribute_id_not_null NOT NULL,
                                                attribute_value_id bigint CONSTRAINT product_attribute_attribute_value_id_not_null NOT NULL,
                                                create_time timestamp without time zone,
                                                required smallint,
                                                update_time timestamp without time zone,
                                                create_by text,
                                                update_by text,
                                                sort integer DEFAULT 0
);


--
-- Name: product_attribute_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.product_attribute_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: product_attribute_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.product_attribute_id_seq OWNED BY app.product_attribute_relation.id;


--
-- Name: product_category; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.product_category (
                                      id bigint NOT NULL,
                                      store_id bigint NOT NULL,
                                      parent_id bigint DEFAULT 0 NOT NULL,
                                      category_name character varying(100) NOT NULL,
                                      icon character varying(500),
                                      sort integer DEFAULT 0 NOT NULL,
                                      status smallint DEFAULT 1 NOT NULL,
                                      create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                      update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


--
-- Name: TABLE product_category; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON TABLE app.product_category IS '商品分类表';


--
-- Name: COLUMN product_category.id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_category.id IS '分类 ID';


--
-- Name: COLUMN product_category.store_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_category.store_id IS '门店 ID';


--
-- Name: COLUMN product_category.parent_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_category.parent_id IS '父分类 ID，0 表示一级分类';


--
-- Name: COLUMN product_category.category_name; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_category.category_name IS '分类名称';


--
-- Name: COLUMN product_category.icon; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_category.icon IS '分类图标 URL';


--
-- Name: COLUMN product_category.sort; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_category.sort IS '排序：数值越小越靠前';


--
-- Name: COLUMN product_category.status; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_category.status IS '状态：1-启用 2-禁用';


--
-- Name: COLUMN product_category.create_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_category.create_time IS '创建时间';


--
-- Name: COLUMN product_category.update_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_category.update_time IS '更新时间';


--
-- Name: product_category_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.product_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: product_category_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.product_category_id_seq OWNED BY app.product_category.id;


--
-- Name: product_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.product_id_seq OWNED BY app.product.id;


--
-- Name: product_log; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.product_log (
                                 id bigint NOT NULL,
                                 product_id bigint NOT NULL,
                                 operator_id bigint NOT NULL,
                                 operator_name character varying(50),
                                 action character varying(50) NOT NULL,
                                 content text,
                                 before_data jsonb,
                                 after_data jsonb,
                                 ip character varying(50),
                                 create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


--
-- Name: TABLE product_log; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON TABLE app.product_log IS '商品操作日志表';


--
-- Name: COLUMN product_log.id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_log.id IS '日志 ID';


--
-- Name: COLUMN product_log.product_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_log.product_id IS '商品 ID';


--
-- Name: COLUMN product_log.operator_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_log.operator_id IS '操作人 ID';


--
-- Name: COLUMN product_log.operator_name; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_log.operator_name IS '操作人姓名';


--
-- Name: COLUMN product_log.action; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_log.action IS '操作类型';


--
-- Name: COLUMN product_log.content; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_log.content IS '操作内容/变更详情';


--
-- Name: COLUMN product_log.before_data; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_log.before_data IS '操作前数据';


--
-- Name: COLUMN product_log.after_data; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_log.after_data IS '操作后数据';


--
-- Name: COLUMN product_log.ip; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_log.ip IS '操作 IP';


--
-- Name: COLUMN product_log.create_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_log.create_time IS '创建时间';


--
-- Name: product_log_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.product_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: product_log_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.product_log_id_seq OWNED BY app.product_log.id;


--
-- Name: product_sku; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.product_sku (
                                 id bigint NOT NULL,
                                 product_id bigint NOT NULL,
                                 specs jsonb NOT NULL,
                                 price bigint DEFAULT 0 NOT NULL,
                                 stock integer DEFAULT '-1'::integer NOT NULL,
                                 sales integer DEFAULT 0 NOT NULL,
                                 images jsonb,
                                 status smallint DEFAULT 1 NOT NULL,
                                 create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                 update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


--
-- Name: TABLE product_sku; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON TABLE app.product_sku IS '商品 SKU 表';


--
-- Name: COLUMN product_sku.id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku.id IS 'SKU ID';


--
-- Name: COLUMN product_sku.product_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku.product_id IS '商品 ID';


--
-- Name: COLUMN product_sku.specs; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku.specs IS '规格组合 JSON';


--
-- Name: COLUMN product_sku.price; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku.price IS 'SKU 售价';


--
-- Name: COLUMN product_sku.stock; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku.stock IS 'SKU 库存：-1 表示无限';


--
-- Name: COLUMN product_sku.sales; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku.sales IS 'SKU 销量';


--
-- Name: COLUMN product_sku.images; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku.images IS 'SKU 图片';


--
-- Name: COLUMN product_sku.status; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku.status IS '状态：1-启用 2-禁用';


--
-- Name: COLUMN product_sku.create_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku.create_time IS '创建时间';


--
-- Name: COLUMN product_sku.update_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku.update_time IS '更新时间';


--
-- Name: product_sku_attribute_relation; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.product_sku_attribute_relation (
                                                    id bigint NOT NULL,
                                                    sku_id bigint NOT NULL,
                                                    attribute_id bigint NOT NULL,
                                                    attribute_value_id bigint NOT NULL,
                                                    sort integer DEFAULT 0,
                                                    create_time timestamp(6) without time zone,
                                                    update_time timestamp(6) without time zone,
                                                    create_by text,
                                                    update_by text
);


--
-- Name: TABLE product_sku_attribute_relation; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON TABLE app.product_sku_attribute_relation IS '商品 SKU 属性关联表';


--
-- Name: COLUMN product_sku_attribute_relation.id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku_attribute_relation.id IS '关联 ID（自增主键）';


--
-- Name: COLUMN product_sku_attribute_relation.sku_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku_attribute_relation.sku_id IS '商品 SKU ID（关联 product_sku 表）';


--
-- Name: COLUMN product_sku_attribute_relation.attribute_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku_attribute_relation.attribute_id IS '属性模板 ID（关联 attribute 表）';


--
-- Name: COLUMN product_sku_attribute_relation.attribute_value_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku_attribute_relation.attribute_value_id IS '属性值 ID（关联 attribute_value 表）';


--
-- Name: COLUMN product_sku_attribute_relation.sort; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku_attribute_relation.sort IS '排序序号';


--
-- Name: COLUMN product_sku_attribute_relation.create_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku_attribute_relation.create_time IS '创建时间';


--
-- Name: COLUMN product_sku_attribute_relation.update_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku_attribute_relation.update_time IS '更新时间';


--
-- Name: COLUMN product_sku_attribute_relation.create_by; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku_attribute_relation.create_by IS '创建人';


--
-- Name: COLUMN product_sku_attribute_relation.update_by; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.product_sku_attribute_relation.update_by IS '更新人';


--
-- Name: product_sku_attribute_relation_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.product_sku_attribute_relation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: product_sku_attribute_relation_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.product_sku_attribute_relation_id_seq OWNED BY app.product_sku_attribute_relation.id;


--
-- Name: product_sku_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.product_sku_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: product_sku_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.product_sku_id_seq OWNED BY app.product_sku.id;


--
-- Name: store; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.store (
                           id integer NOT NULL,
                           name text NOT NULL,
                           address text NOT NULL,
                           contact_mobile text NOT NULL,
                           business_hours text NOT NULL,
                           logo text DEFAULT ''::text,
                           status smallint DEFAULT 0,
                           is_support_scan boolean DEFAULT true,
                           is_support_takeaway boolean DEFAULT false,
                           is_support_selfpick boolean DEFAULT false,
                           delivery_range integer DEFAULT 3,
                           delivery_min_amount bigint DEFAULT 2000,
                           delivery_fee_rule text,
                           remark text,
                           create_time timestamp without time zone,
                           update_time timestamp without time zone
);


--
-- Name: COLUMN store.id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.store.id IS '门店主键ID（自增）';


--
-- Name: COLUMN store.name; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.store.name IS '门店名称（如：XX奶茶店（朝阳店））';


--
-- Name: COLUMN store.address; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.store.address IS '门店详细地址（外卖配送起点、自提地址）';


--
-- Name: COLUMN store.contact_mobile; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.store.contact_mobile IS '门店联系电话';


--
-- Name: COLUMN store.business_hours; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.store.business_hours IS '营业时间（如：09:00-22:00）';


--
-- Name: COLUMN store.logo; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.store.logo IS '门店Logo（前端展示）';


--
-- Name: COLUMN store.status; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.store.status IS '门店状态（1=营业，0=休息/停业）';


--
-- Name: COLUMN store.is_support_scan; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.store.is_support_scan IS '是否支持扫码堂食';


--
-- Name: COLUMN store.is_support_takeaway; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.store.is_support_takeaway IS '是否支持外卖配送';


--
-- Name: COLUMN store.is_support_selfpick; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.store.is_support_selfpick IS '是否支持到店自提';


--
-- Name: COLUMN store.delivery_range; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.store.delivery_range IS '外卖配送范围（单位：公里）';


--
-- Name: COLUMN store.delivery_min_amount; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.store.delivery_min_amount IS '外卖起送金额（单位：分，2000=20元）';


--
-- Name: COLUMN store.delivery_fee_rule; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.store.delivery_fee_rule IS '配送费规则（如：3公里内5元，超3公里每公里加1元）';


--
-- Name: COLUMN store.remark; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.store.remark IS '备注（如：朝阳店，外卖配送覆盖国贸、三里屯）';


--
-- Name: COLUMN store.create_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.store.create_time IS '创建时间';


--
-- Name: COLUMN store.update_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.store.update_time IS '更新时间';


--
-- Name: store_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.store_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: store_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.store_id_seq OWNED BY app.store.id;


--
-- Name: table_qrcode; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.table_qrcode (
                                  id integer NOT NULL,
                                  store_id integer NOT NULL,
                                  qrcode_name text CONSTRAINT table_qrcode_table_num_not_null NOT NULL,
                                  qrcode_url text,
                                  qrcode_content text NOT NULL,
                                  qrcode_no text NOT NULL,
                                  status smallint DEFAULT 0 NOT NULL,
                                  is_vip boolean DEFAULT false,
                                  max_people integer DEFAULT 4,
                                  area text,
                                  remark text,
                                  create_time timestamp without time zone,
                                  update_time timestamp without time zone,
                                  create_by text,
                                  update_by text,
                                  qrcode_info jsonb
);


--
-- Name: COLUMN table_qrcode.id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.table_qrcode.id IS '桌码主键ID（自增）';


--
-- Name: COLUMN table_qrcode.store_id; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.table_qrcode.store_id IS '关联门店ID（关联app.store.id，标记桌码所属门店）';


--
-- Name: COLUMN table_qrcode.qrcode_name; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.table_qrcode.qrcode_name IS '桌号名称（前端展示用，如：1号桌、靠窗2号、VIP包间3、吧台4）';


--
-- Name: COLUMN table_qrcode.qrcode_url; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.table_qrcode.qrcode_url IS '桌码图片URL（生成的二维码地址，包含qrcode_no，打印张贴用）';


--
-- Name: COLUMN table_qrcode.qrcode_content; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.table_qrcode.qrcode_content IS '二维码内容（小程序跳转链接+qrcode_no，如：https://xxx.com/scan?no=STORE1-TABLE001）';


--
-- Name: COLUMN table_qrcode.qrcode_no; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.table_qrcode.qrcode_no IS '桌码编号';


--
-- Name: COLUMN table_qrcode.status; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.table_qrcode.status IS '桌码状态（0=可用，1=禁用/损坏，2=维护中，禁用后扫码提示“该桌暂不可用”）';


--
-- Name: COLUMN table_qrcode.is_vip; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.table_qrcode.is_vip IS '是否VIP桌（true=是，如包间、靠窗桌，可用于差异化服务）';


--
-- Name: COLUMN table_qrcode.max_people; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.table_qrcode.max_people IS '最大容纳人数（如：2人桌、4人桌、6人桌，前端可展示推荐人数）';


--
-- Name: COLUMN table_qrcode.area; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.table_qrcode.area IS '所属区域（如：一楼大厅、二楼包间、户外露台，便于门店分区管理）';


--
-- Name: COLUMN table_qrcode.remark; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.table_qrcode.remark IS '备注（如：1号桌-门口区域，靠近取餐台；VIP包间3-需提前预约）';


--
-- Name: COLUMN table_qrcode.create_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.table_qrcode.create_time IS '创建时间（生成桌码时）';


--
-- Name: COLUMN table_qrcode.update_time; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.table_qrcode.update_time IS '更新时间（状态变更/信息修改时）';


--
-- Name: COLUMN table_qrcode.create_by; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.table_qrcode.create_by IS '创建者';


--
-- Name: COLUMN table_qrcode.update_by; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.table_qrcode.update_by IS '更新者';


--
-- Name: COLUMN table_qrcode.qrcode_info; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON COLUMN app.table_qrcode.qrcode_info IS '微信码json信息j';


--
-- Name: table_qrcode_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.table_qrcode_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: table_qrcode_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.table_qrcode_id_seq OWNED BY app.table_qrcode.id;


--
-- Name: app_user id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.app_user ALTER COLUMN id SET DEFAULT nextval('app.app_user_id_seq'::regclass);


--
-- Name: attribute id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.attribute ALTER COLUMN id SET DEFAULT nextval('app.attribute_id_seq'::regclass);


--
-- Name: attribute_value id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.attribute_value ALTER COLUMN id SET DEFAULT nextval('app.attribute_value_id_seq'::regclass);


--
-- Name: banner id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.banner ALTER COLUMN id SET DEFAULT nextval('app.banner_id_seq'::regclass);


--
-- Name: order_detail id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.order_detail ALTER COLUMN id SET DEFAULT nextval('app.order_detail_id_seq'::regclass);


--
-- Name: order_main id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.order_main ALTER COLUMN id SET DEFAULT nextval('app.order_main_id_seq'::regclass);


--
-- Name: order_refund id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.order_refund ALTER COLUMN id SET DEFAULT nextval('app.order_refund_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.product ALTER COLUMN id SET DEFAULT nextval('app.product_id_seq'::regclass);


--
-- Name: product_attribute_relation id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.product_attribute_relation ALTER COLUMN id SET DEFAULT nextval('app.product_attribute_id_seq'::regclass);


--
-- Name: product_category id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.product_category ALTER COLUMN id SET DEFAULT nextval('app.product_category_id_seq'::regclass);


--
-- Name: product_log id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.product_log ALTER COLUMN id SET DEFAULT nextval('app.product_log_id_seq'::regclass);


--
-- Name: product_sku id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.product_sku ALTER COLUMN id SET DEFAULT nextval('app.product_sku_id_seq'::regclass);


--
-- Name: product_sku_attribute_relation id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.product_sku_attribute_relation ALTER COLUMN id SET DEFAULT nextval('app.product_sku_attribute_relation_id_seq'::regclass);


--
-- Name: store id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.store ALTER COLUMN id SET DEFAULT nextval('app.store_id_seq'::regclass);


--
-- Name: table_qrcode id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.table_qrcode ALTER COLUMN id SET DEFAULT nextval('app.table_qrcode_id_seq'::regclass);


--
-- Data for Name: app_user; Type: TABLE DATA; Schema: app; Owner: -
--

INSERT INTO app.app_user VALUES (13, NULL, NULL, '新宝宝', NULL, NULL, NULL, 'o1l9X41yb2qIyIxBJMqHJ83ORaeQ', NULL, 'https://wanghengrun.cn/app-user/20220515203009_01133.jpeg', 0, NULL, NULL, NULL, NULL, NULL, 1);


--
-- Data for Name: attribute; Type: TABLE DATA; Schema: app; Owner: -
--

INSERT INTO app.attribute VALUES (1, '杯型', 1, 1, '2026-03-03 16:26:08.033734', 1, '2026-03-03 16:26:08.0345', NULL, NULL, 1);
INSERT INTO app.attribute VALUES (2, '温度', 1, 1, '2026-03-04 09:40:12.594949', 2, '2026-03-04 09:40:12.596959', NULL, NULL, 1);
INSERT INTO app.attribute VALUES (3, '甜度', 1, 1, '2026-03-04 12:53:16.822531', 3, '2026-03-04 12:53:16.824027', NULL, NULL, 1);


--
-- Data for Name: attribute_value; Type: TABLE DATA; Schema: app; Owner: -
--

INSERT INTO app.attribute_value VALUES (1, 1, '小杯', 1, 1, '2026-03-03 16:26:08.045217', '2026-03-03 16:26:08.045253', NULL, NULL);
INSERT INTO app.attribute_value VALUES (2, 1, '中杯', 2, 1, '2026-03-03 16:26:08.046522', '2026-03-03 16:26:08.046534', NULL, NULL);
INSERT INTO app.attribute_value VALUES (3, 1, '大杯', 3, 1, '2026-03-03 16:26:08.04666', '2026-03-03 16:26:08.04667', NULL, NULL);
INSERT INTO app.attribute_value VALUES (4, 2, '常温', 0, 1, '2026-03-04 09:40:12.611698', '2026-03-04 09:40:12.611767', NULL, NULL);
INSERT INTO app.attribute_value VALUES (5, 2, '热', 0, 1, '2026-03-04 09:40:12.612797', '2026-03-04 09:40:12.612817', NULL, NULL);
INSERT INTO app.attribute_value VALUES (6, 2, '加冰', 0, 1, '2026-03-04 09:40:12.613066', '2026-03-04 09:40:12.613085', NULL, NULL);
INSERT INTO app.attribute_value VALUES (7, 3, '七分糖', 1, 1, '2026-03-04 12:53:16.835669', '2026-03-04 12:53:16.835719', NULL, NULL);
INSERT INTO app.attribute_value VALUES (8, 3, '标准', 2, 1, '2026-03-04 12:53:16.836373', '2026-03-04 12:53:16.836389', NULL, NULL);
INSERT INTO app.attribute_value VALUES (9, 3, '三分糖', 3, 1, '2026-03-04 12:53:16.836546', '2026-03-04 12:53:16.836562', NULL, NULL);


--
-- Data for Name: banner; Type: TABLE DATA; Schema: app; Owner: -
--

INSERT INTO app.banner VALUES (3, 0, '时令上新', 'https://wanghengrun.cn/product/banner/0-1773162090434.jpg', 1, '{"productId": 101}', 3, 1, '2026-03-11 01:01:44', '2026-03-25 00:00:00', '2026-03-11 01:01:50.251401', '2026-03-13 11:52:49.111038', NULL, NULL, '');
INSERT INTO app.banner VALUES (9, 0, '新鲜果茶', 'https://wanghengrun.cn/product/banner/0-1773166261646.jpg', 2, '{"categoryId": 4}', 0, 1, '2026-03-11 02:11:09', '2026-03-24 00:00:00', '2026-03-11 02:11:14.955233', '2026-03-13 11:53:02.693257', NULL, NULL, '');
INSERT INTO app.banner VALUES (4, 0, '热卖推荐', 'https://wanghengrun.cn/product/banner/0-1773162094836.jpg', 1, '{"productId": 101}', 3, 0, '2026-03-11 01:01:44', '2026-03-25 00:00:00', '2026-03-11 01:01:50.252868', '2026-03-13 11:52:34.596835', NULL, NULL, '');


--
-- Data for Name: order_detail; Type: TABLE DATA; Schema: app; Owner: -
--

INSERT INTO app.order_detail VALUES (52, '1-13-A02-1774266597342', 104, '手打柠檬', 483, '[{"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1, 2, 2, NULL, '2026-03-23 19:49:57.354579', '[{"url": "https://wanghengrun.cn/product/spu/1-39-main-20260319114423.png", "sort": 0, "description": null}, {"url": "https://wanghengrun.cn/product/spu/1-39-main-20260319114431.png", "sort": 1, "description": null}]');
INSERT INTO app.order_detail VALUES (53, '1-13-A02-1774266597342', 101, '宁檬果茶', 420, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 500, 2, 1000, NULL, '2026-03-23 19:49:57.356006', '[{"url": "https://wanghengrun.cn/product/spu/1-39-main-20260307191858.png", "sort": 0, "description": null}, {"url": "https://wanghengrun.cn/product/spu/1-39-main-20260307192322.png", "sort": 1, "description": null}]');
INSERT INTO app.order_detail VALUES (54, '1-13-A02-1774267181293', 104, '手打柠檬', 483, '[{"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1, 2, 2, NULL, '2026-03-23 19:59:41.298852', '[{"url": "https://wanghengrun.cn/product/spu/1-39-main-20260319114423.png", "sort": 0, "description": null}, {"url": "https://wanghengrun.cn/product/spu/1-39-main-20260319114431.png", "sort": 1, "description": null}]');
INSERT INTO app.order_detail VALUES (55, '1-13-A02-1774267181293', 102, '啵啵奶茶', 453, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 1, 1000, NULL, '2026-03-23 19:59:41.299747', '[{"url": "https://wanghengrun.cn/product/spu/1-38-main-20260307192416.png", "sort": 0, "description": null}, {"url": "https://wanghengrun.cn/product/spu/1-38-main-20260307192422.png", "sort": 1, "description": null}]');
INSERT INTO app.order_detail VALUES (56, '1-13-A02-1774269980807', 104, '手打柠檬', 483, '[{"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1, 2, 2, NULL, '2026-03-23 20:46:20.815134', '[{"url": "https://wanghengrun.cn/product/spu/1-39-main-20260319114423.png", "sort": 0, "description": null}, {"url": "https://wanghengrun.cn/product/spu/1-39-main-20260319114431.png", "sort": 1, "description": null}]');
INSERT INTO app.order_detail VALUES (57, '1-13-A02-1774269980807', 102, '啵啵奶茶', 453, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 1, 1000, NULL, '2026-03-23 20:46:20.816156', '[{"url": "https://wanghengrun.cn/product/spu/1-38-main-20260307192416.png", "sort": 0, "description": null}, {"url": "https://wanghengrun.cn/product/spu/1-38-main-20260307192422.png", "sort": 1, "description": null}]');
INSERT INTO app.order_detail VALUES (58, '1-13-A02-1774269980807', 101, '宁檬果茶', 420, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 500, 2, 1000, NULL, '2026-03-23 20:46:20.816536', '[{"url": "https://wanghengrun.cn/product/spu/1-39-main-20260307191858.png", "sort": 0, "description": null}, {"url": "https://wanghengrun.cn/product/spu/1-39-main-20260307192322.png", "sort": 1, "description": null}]');
INSERT INTO app.order_detail VALUES (59, '1-13-A02-1774269980807', 101, '宁檬果茶', 438, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 1, 1000, NULL, '2026-03-23 20:46:20.816803', '[{"url": "https://wanghengrun.cn/product/spu/1-39-main-20260307191858.png", "sort": 0, "description": null}, {"url": "https://wanghengrun.cn/product/spu/1-39-main-20260307192322.png", "sort": 1, "description": null}]');
INSERT INTO app.order_detail VALUES (60, '1-13-A02-1774269980807', 102, '啵啵奶茶', 456, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 1, 1000, NULL, '2026-03-23 20:46:20.817101', '[{"url": "https://wanghengrun.cn/product/spu/1-38-main-20260307192416.png", "sort": 0, "description": null}, {"url": "https://wanghengrun.cn/product/spu/1-38-main-20260307192422.png", "sort": 1, "description": null}]');


--
-- Data for Name: order_main; Type: TABLE DATA; Schema: app; Owner: -
--

INSERT INTO app.order_main VALUES (37, '1-13-A02-1774266597342', 13, 1, 3, 1, 1, 1, 1002, 0, 0, 1002, 1002, NULL, NULL, '堂食 - 桌台号：A02', '测试桌号转入', NULL, NULL, NULL, NULL, NULL, '2026-03-23 19:49:57.345852', '2026-03-23 19:49:57.346466', '甜趣奶茶店 (万达店)', '', NULL, 'system', NULL, 'A02');
INSERT INTO app.order_main VALUES (38, '1-13-A02-1774267181293', 13, 1, 3, 3, 1, 0, 1002, 0, 0, 1002, 1002, NULL, NULL, '堂食 - 桌台号：A02', '我不要辣椒', NULL, NULL, NULL, NULL, NULL, '2026-03-23 19:59:41.294346', '2026-03-23 19:59:41.294488', '甜趣奶茶店 (万达店)', '', NULL, NULL, NULL, 'A02');
INSERT INTO app.order_main VALUES (39, '1-13-A02-1774269980807', 13, 1, 3, 1, 1, 1, 4002, 0, 0, 4002, 4002, NULL, NULL, '堂食 - 桌台号：A02', '', NULL, NULL, NULL, NULL, NULL, '2026-03-23 20:46:20.808634', '2026-03-23 20:46:20.808744', '甜趣奶茶店 (万达店)', '', NULL, 'system', NULL, 'A02');


--
-- Data for Name: order_refund; Type: TABLE DATA; Schema: app; Owner: -
--



--
-- Data for Name: product; Type: TABLE DATA; Schema: app; Owner: -
--

INSERT INTO app.product VALUES (102, 1, 12, '啵啵奶茶', '啵啵奶茶', '这个🍵无敌好喝 无敌了', 1000.00, 1000.00, 1000.00, 1000.00, 10, 0, '杯', '[{"url": "https://wanghengrun.cn/product/spu/1-38-main-20260307192416.png", "sort": 0, "description": null}, {"url": "https://wanghengrun.cn/product/spu/1-38-main-20260307192422.png", "sort": 1, "description": null}]', '# 🍑 满杯红柚·清新上市
### <span style="color: #ff6b81; font-weight: bold;">第二杯半价 | 限时尝鲜价 ¥18.00</span>

---

## 🌟 为什么这杯果茶必喝？

> 💡 **一口爆汁，仿佛咬开整个夏天！**

- 🍊 **真果肉·看得见**：精选福建琯溪红柚，手工剥肉，每一口都能吃到饱满果粒。
- 🍵 **高山茶底**：选用海拔 800m+ 的高山茉莉绿茶，茶香幽雅，清爽不涩。
- 🧊 **冰爽口感**：现摇现制，冰块撞击灵魂，解腻神器，火锅烧烤最佳搭档。
- 🌿 **0 负担**：可选代糖（赤藓糖醇），好喝不怕胖，减脂期也能放心冲！

---

##  视觉与味觉的双重享受

![果茶海报](https://images.unsplash.com/photo-1546173159-315724a31696?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80)
*(此处为示意图：金黄透亮的茶汤中，悬浮着粉嫩的红柚果粒，杯壁挂着晶莹水珠)*

**口感层次：**
1. **初闻**：清新的茉莉花香扑鼻而来。
2. **入口**：先是冰糖的甘甜，紧接着是红柚的微酸爆汁。
3. **回甘**：茶底悠长，喉间留有淡淡花果香。

---

## 🛠️ 定制您的专属口味

| 甜度选择 | 推荐人群 |
| :--- | :--- |
| 🍬 **正常糖** | 嗜甜爱好者，快乐加倍 |
| 🍭 **七分糖** | **🔥 店长推荐**，甜而不腻 |
| 🍃 **三分糖** | 清淡口味，突出果酸 |
| 💧 **不另外加糖** | 健康达人，纯粹果味 |

| 温度选择 | 说明 |
| :--- | :--- |
| 🧊 **少冰/正常冰** | 口感最佳，激爽透心凉 |
| 🌡️ **去冰** | 保留风味，不想太冰选这个 |
| 🔥 **温热 (55℃)** | 暖胃呵护，冬日首选 |

---

## 🎁 限时福利

- ✅ **新人礼包**：新用户下单立减 **¥5**
- ✅ **分享有礼**：邀请好友拼单，双方得 **免配料券**
- ✅ **积分翻倍**：本周购买果茶系列，积分 **x2**

---

<div align="center">

### 🛒 立即下单，开启清新时刻！
👇👇
[ **点击购买** ](javascript:void(0))

</div>', '["1", "2"]', 0, 1, true, true, true, NULL, '2026-03-07 19:26:52.050197', '2026-03-19 11:43:13.544237', NULL, NULL, NULL, '[{"url": "https://wanghengrun.cn/product/spu/1-38-slider-20260307192419.png", "sort": 0, "description": null}, {"url": "https://wanghengrun.cn/product/spu/1-38-slider-20260307192425.png", "sort": 1, "description": null}]');
INSERT INTO app.product VALUES (104, 1, 1, '手打柠檬', '手打柠檬', '哇 清爽柠檬和果茶的配合', 1490.00, 2500.00, 1000.00, 2000.00, 10, 0, '杯', '[{"url": "https://wanghengrun.cn/product/spu/1-39-main-20260319114423.png", "sort": 0, "description": null}, {"url": "https://wanghengrun.cn/product/spu/1-39-main-20260319114431.png", "sort": 1, "description": null}]', '# 🍑 满杯红柚·清新上市
### <span style="color: #ff6b81; font-weight: bold;">第二杯半价 | 限时尝鲜价 ¥18.00</span>

---

## 🌟 为什么这杯果茶必喝？

> 💡 **一口爆汁，仿佛咬开整个夏天！**

- 🍊 **真果肉·看得见**：精选福建琯溪红柚，手工剥肉，每一口都能吃到饱满果粒。
- 🍵 **高山茶底**：选用海拔 800m+ 的高山茉莉绿茶，茶香幽雅，清爽不涩。
- 🧊 **冰爽口感**：现摇现制，冰块撞击灵魂，解腻神器，火锅烧烤最佳搭档。
- 🌿 **0 负担**：可选代糖（赤藓糖醇），好喝不怕胖，减脂期也能放心冲！

---

##  视觉与味觉的双重享受

![果茶海报](https://images.unsplash.com/photo-1546173159-315724a31696?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80)
*(此处为示意图：金黄透亮的茶汤中，悬浮着粉嫩的红柚果粒，杯壁挂着晶莹水珠)*

**口感层次：**
1. **初闻**：清新的茉莉花香扑鼻而来。
2. **入口**：先是冰糖的甘甜，紧接着是红柚的微酸爆汁。
3. **回甘**：茶底悠长，喉间留有淡淡花果香。

---

## 🛠️ 定制您的专属口味

| 甜度选择 | 推荐人群 |
| :--- | :--- |
| 🍬 **正常糖** | 嗜甜爱好者，快乐加倍 |
| 🍭 **七分糖** | **🔥 店长推荐**，甜而不腻 |
| 🍃 **三分糖** | 清淡口味，突出果酸 |
| 💧 **不另外加糖** | 健康达人，纯粹果味 |

| 温度选择 | 说明 |
| :--- | :--- |
| 🧊 **少冰/正常冰** | 口感最佳，激爽透心凉 |
| 🌡️ **去冰** | 保留风味，不想太冰选这个 |
| 🔥 **温热 (55℃)** | 暖胃呵护，冬日首选 |

---

## 🎁 限时福利

- ✅ **新人礼包**：新用户下单立减 **¥5**
- ✅ **分享有礼**：邀请好友拼单，双方得 **免配料券**
- ✅ **积分翻倍**：本周购买果茶系列，积分 **x2**

---

<div align="center">

### 🛒 立即下单，开启清新时刻！
👇👇
[ **点击购买** ](javascript:void(0))

</div>', '["1"]', 0, 1, true, true, true, NULL, '2026-03-19 11:45:29.876408', '2026-03-19 11:47:54.951093', NULL, NULL, NULL, '[{"url": "https://wanghengrun.cn/product/spu/1-39-slider-20260319114427.png", "sort": 0, "description": null}]');
INSERT INTO app.product VALUES (105, 1, 2, '桃园三结义', '桃园三结义', '当前最火热的桃子果茶', 800.00, 1500.00, 2000.00, 300.00, 4, 0, '杯', '[{"url": "https://wanghengrun.cn/product/spu/1-2-main-20260319115131.png", "sort": 0, "description": null}]', '# 🍑 满杯红柚·清新上市
### <span style="color: #ff6b81; font-weight: bold;">第二杯半价 | 限时尝鲜价 ¥18.00</span>

---

## 🌟 为什么这杯果茶必喝？

> 💡 **一口爆汁，仿佛咬开整个夏天！**

- 🍊 **真果肉·看得见**：精选福建琯溪红柚，手工剥肉，每一口都能吃到饱满果粒。
- 🍵 **高山茶底**：选用海拔 800m+ 的高山茉莉绿茶，茶香幽雅，清爽不涩。
- 🧊 **冰爽口感**：现摇现制，冰块撞击灵魂，解腻神器，火锅烧烤最佳搭档。
- 🌿 **0 负担**：可选代糖（赤藓糖醇），好喝不怕胖，减脂期也能放心冲！

---

##  视觉与味觉的双重享受

![果茶海报](https://images.unsplash.com/photo-1546173159-315724a31696?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80)
*(此处为示意图：金黄透亮的茶汤中，悬浮着粉嫩的红柚果粒，杯壁挂着晶莹水珠)*

**口感层次：**
1. **初闻**：清新的茉莉花香扑鼻而来。
2. **入口**：先是冰糖的甘甜，紧接着是红柚的微酸爆汁。
3. **回甘**：茶底悠长，喉间留有淡淡花果香。

---

## 🛠️ 定制您的专属口味

| 甜度选择 | 推荐人群 |
| :--- | :--- |
| 🍬 **正常糖** | 嗜甜爱好者，快乐加倍 |
| 🍭 **七分糖** | **🔥 店长推荐**，甜而不腻 |
| 🍃 **三分糖** | 清淡口味，突出果酸 |
| 💧 **不另外加糖** | 健康达人，纯粹果味 |

| 温度选择 | 说明 |
| :--- | :--- |
| 🧊 **少冰/正常冰** | 口感最佳，激爽透心凉 |
| 🌡️ **去冰** | 保留风味，不想太冰选这个 |
| 🔥 **温热 (55℃)** | 暖胃呵护，冬日首选 |

---

## 🎁 限时福利

- ✅ **新人礼包**：新用户下单立减 **¥5**
- ✅ **分享有礼**：邀请好友拼单，双方得 **免配料券**
- ✅ **积分翻倍**：本周购买果茶系列，积分 **x2**

---

<div align="center">

### 🛒 立即下单，开启清新时刻！
👇👇
[ **点击购买** ](javascript:void(0))

</div>', '["2"]', 0, 1, true, true, true, NULL, '2026-03-19 11:52:38.805775', '2026-03-19 11:52:38.805839', NULL, NULL, NULL, '[{"url": "https://wanghengrun.cn/product/spu/1-2-slider-20260319115135.png", "sort": 0, "description": null}]');
INSERT INTO app.product VALUES (101, 1, 1, '宁檬果茶', '宁檬果茶', '柠檬果茶口感酸甜解渴', 1000.00, 1000.00, 1000.00, 1000.00, 10, 0, '杯', '[{"url": "https://wanghengrun.cn/product/spu/1-39-main-20260307191858.png", "sort": 0, "description": null}, {"url": "https://wanghengrun.cn/product/spu/1-39-main-20260307192322.png", "sort": 1, "description": null}]', '# 🍑 满杯红柚·清新上市
### <span style="color: #ff6b81; font-weight: bold;">第二杯半价 | 限时尝鲜价 ¥18.00</span>

---

## 🌟 为什么这杯果茶必喝？

> 💡 **一口爆汁，仿佛咬开整个夏天！**

- 🍊 **真果肉·看得见**：精选福建琯溪红柚，手工剥肉，每一口都能吃到饱满果粒。
- 🍵 **高山茶底**：选用海拔 800m+ 的高山茉莉绿茶，茶香幽雅，清爽不涩。
- 🧊 **冰爽口感**：现摇现制，冰块撞击灵魂，解腻神器，火锅烧烤最佳搭档。
- 🌿 **0 负担**：可选代糖（赤藓糖醇），好喝不怕胖，减脂期也能放心冲！

---

##  视觉与味觉的双重享受

![果茶海报](https://images.unsplash.com/photo-1546173159-315724a31696?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80)
*(此处为示意图：金黄透亮的茶汤中，悬浮着粉嫩的红柚果粒，杯壁挂着晶莹水珠)*

**口感层次：**
1. **初闻**：清新的茉莉花香扑鼻而来。
2. **入口**：先是冰糖的甘甜，紧接着是红柚的微酸爆汁。
3. **回甘**：茶底悠长，喉间留有淡淡花果香。

---

## 🛠️ 定制您的专属口味

| 甜度选择 | 推荐人群 |
| :--- | :--- |
| 🍬 **正常糖** | 嗜甜爱好者，快乐加倍 |
| 🍭 **七分糖** | **🔥 店长推荐**，甜而不腻 |
| 🍃 **三分糖** | 清淡口味，突出果酸 |
| 💧 **不另外加糖** | 健康达人，纯粹果味 |

| 温度选择 | 说明 |
| :--- | :--- |
| 🧊 **少冰/正常冰** | 口感最佳，激爽透心凉 |
| 🌡️ **去冰** | 保留风味，不想太冰选这个 |
| 🔥 **温热 (55℃)** | 暖胃呵护，冬日首选 |

---

## 🎁 限时福利

- ✅ **新人礼包**：新用户下单立减 **¥5**
- ✅ **分享有礼**：邀请好友拼单，双方得 **免配料券**
- ✅ **积分翻倍**：本周购买果茶系列，积分 **x2**

---

<div align="center">

### 🛒 立即下单，开启清新时刻！
👇👇
[ **点击购买** ](javascript:void(0))

</div>', '["1", "2"]', 1, 1, true, true, true, NULL, '2026-03-07 18:53:05.097466', '2026-03-19 11:41:36.489206', NULL, NULL, NULL, '[{"url": "https://wanghengrun.cn/product/spu/1-39-slider-20260307191903.png", "sort": 0, "description": null}, {"url": "https://wanghengrun.cn/product/spu/1-39-slider-20260307192325.png", "sort": 1, "description": null}]');


--
-- Data for Name: product_attribute_relation; Type: TABLE DATA; Schema: app; Owner: -
--

INSERT INTO app.product_attribute_relation VALUES (98, 101, 1, 1, '2026-03-19 11:41:36.516817', 1, '2026-03-19 11:41:36.516835', NULL, NULL, 0);
INSERT INTO app.product_attribute_relation VALUES (99, 101, 1, 2, '2026-03-19 11:41:36.517224', 1, '2026-03-19 11:41:36.517231', NULL, NULL, 1);
INSERT INTO app.product_attribute_relation VALUES (100, 101, 1, 3, '2026-03-19 11:41:36.517395', 1, '2026-03-19 11:41:36.5174', NULL, NULL, 2);
INSERT INTO app.product_attribute_relation VALUES (101, 101, 2, 4, '2026-03-19 11:41:36.517488', 1, '2026-03-19 11:41:36.517493', NULL, NULL, 3);
INSERT INTO app.product_attribute_relation VALUES (102, 101, 2, 5, '2026-03-19 11:41:36.51758', 1, '2026-03-19 11:41:36.517585', NULL, NULL, 4);
INSERT INTO app.product_attribute_relation VALUES (103, 101, 2, 6, '2026-03-19 11:41:36.517673', 1, '2026-03-19 11:41:36.517678', NULL, NULL, 5);
INSERT INTO app.product_attribute_relation VALUES (104, 101, 3, 7, '2026-03-19 11:41:36.522977', 1, '2026-03-19 11:41:36.522996', NULL, NULL, 6);
INSERT INTO app.product_attribute_relation VALUES (105, 101, 3, 8, '2026-03-19 11:41:36.523181', 1, '2026-03-19 11:41:36.523186', NULL, NULL, 7);
INSERT INTO app.product_attribute_relation VALUES (106, 101, 3, 9, '2026-03-19 11:41:36.523271', 1, '2026-03-19 11:41:36.523274', NULL, NULL, 8);
INSERT INTO app.product_attribute_relation VALUES (107, 102, 1, 1, '2026-03-19 11:43:13.550494', 1, '2026-03-19 11:43:13.550507', NULL, NULL, 0);
INSERT INTO app.product_attribute_relation VALUES (108, 102, 1, 2, '2026-03-19 11:43:13.550837', 1, '2026-03-19 11:43:13.550841', NULL, NULL, 1);
INSERT INTO app.product_attribute_relation VALUES (109, 102, 1, 3, '2026-03-19 11:43:13.550925', 1, '2026-03-19 11:43:13.550929', NULL, NULL, 2);
INSERT INTO app.product_attribute_relation VALUES (110, 102, 2, 4, '2026-03-19 11:43:13.551508', 1, '2026-03-19 11:43:13.551526', NULL, NULL, 3);
INSERT INTO app.product_attribute_relation VALUES (111, 102, 2, 5, '2026-03-19 11:43:13.552862', 1, '2026-03-19 11:43:13.552879', NULL, NULL, 4);
INSERT INTO app.product_attribute_relation VALUES (112, 102, 2, 6, '2026-03-19 11:43:13.553013', 1, '2026-03-19 11:43:13.553017', NULL, NULL, 5);
INSERT INTO app.product_attribute_relation VALUES (113, 102, 3, 7, '2026-03-19 11:43:13.553095', 1, '2026-03-19 11:43:13.553098', NULL, NULL, 6);
INSERT INTO app.product_attribute_relation VALUES (114, 102, 3, 8, '2026-03-19 11:43:13.553259', 1, '2026-03-19 11:43:13.553263', NULL, NULL, 7);
INSERT INTO app.product_attribute_relation VALUES (115, 102, 3, 9, '2026-03-19 11:43:13.553367', 1, '2026-03-19 11:43:13.553394', NULL, NULL, 8);
INSERT INTO app.product_attribute_relation VALUES (122, 104, 2, 4, '2026-03-19 11:47:54.962664', 1, '2026-03-19 11:47:54.962686', NULL, NULL, 0);
INSERT INTO app.product_attribute_relation VALUES (123, 104, 2, 5, '2026-03-19 11:47:54.962959', 1, '2026-03-19 11:47:54.962967', NULL, NULL, 1);
INSERT INTO app.product_attribute_relation VALUES (124, 104, 2, 6, '2026-03-19 11:47:54.963079', 1, '2026-03-19 11:47:54.963085', NULL, NULL, 2);
INSERT INTO app.product_attribute_relation VALUES (125, 104, 3, 7, '2026-03-19 11:47:54.963182', 1, '2026-03-19 11:47:54.963187', NULL, NULL, 3);
INSERT INTO app.product_attribute_relation VALUES (126, 104, 3, 8, '2026-03-19 11:47:54.963291', 1, '2026-03-19 11:47:54.963297', NULL, NULL, 4);
INSERT INTO app.product_attribute_relation VALUES (127, 104, 3, 9, '2026-03-19 11:47:54.963392', 1, '2026-03-19 11:47:54.963398', NULL, NULL, 5);
INSERT INTO app.product_attribute_relation VALUES (128, 105, 1, 1, '2026-03-19 11:52:38.814488', 1, '2026-03-19 11:52:38.814521', NULL, NULL, 0);
INSERT INTO app.product_attribute_relation VALUES (129, 105, 1, 2, '2026-03-19 11:52:38.81475', 1, '2026-03-19 11:52:38.814755', NULL, NULL, 1);
INSERT INTO app.product_attribute_relation VALUES (130, 105, 1, 3, '2026-03-19 11:52:38.81488', 1, '2026-03-19 11:52:38.814885', NULL, NULL, 2);
INSERT INTO app.product_attribute_relation VALUES (131, 105, 2, 4, '2026-03-19 11:52:38.814963', 1, '2026-03-19 11:52:38.814968', NULL, NULL, 3);
INSERT INTO app.product_attribute_relation VALUES (132, 105, 2, 5, '2026-03-19 11:52:38.815043', 1, '2026-03-19 11:52:38.816299', NULL, NULL, 4);
INSERT INTO app.product_attribute_relation VALUES (133, 105, 2, 6, '2026-03-19 11:52:38.817378', 1, '2026-03-19 11:52:38.817395', NULL, NULL, 5);
INSERT INTO app.product_attribute_relation VALUES (134, 105, 3, 7, '2026-03-19 11:52:38.817546', 1, '2026-03-19 11:52:38.817553', NULL, NULL, 6);
INSERT INTO app.product_attribute_relation VALUES (135, 105, 3, 8, '2026-03-19 11:52:38.817647', 1, '2026-03-19 11:52:38.817653', NULL, NULL, 7);
INSERT INTO app.product_attribute_relation VALUES (136, 105, 3, 9, '2026-03-19 11:52:38.817738', 1, '2026-03-19 11:52:38.817742', NULL, NULL, 8);


--
-- Data for Name: product_category; Type: TABLE DATA; Schema: app; Owner: -
--

INSERT INTO app.product_category VALUES (38, 1, 1, '啵啵奶茶', NULL, 1, 1, '2026-03-01 16:05:37.46048', '2026-03-05 11:10:23.060753');
INSERT INTO app.product_category VALUES (39, 1, 1, '柠檬果茶', NULL, 2, 1, '2026-03-01 16:10:54.719636', '2026-03-05 11:10:40.749585');
INSERT INTO app.product_category VALUES (1, 1, 0, '时令上新', 'https://wanghengrun.cn/product/category/1-1.jpg', 1, 1, '2026-03-01 10:41:44.372841', '2026-03-01 15:58:16.940962');
INSERT INTO app.product_category VALUES (12, 1, 0, '人气推荐', 'https://wanghengrun.cn/product/category/1-12.jpg', 2, 1, '2026-03-01 11:45:53.925755', '2026-03-01 16:02:53.163638');
INSERT INTO app.product_category VALUES (2, 1, 0, '热饮推荐', 'https://wanghengrun.cn/product/category/1-2.jpg', 3, 1, '2026-03-01 10:41:44.372841', '2026-03-01 16:03:18.934067');
INSERT INTO app.product_category VALUES (13, 1, 0, '植物茶/鲜果茶', 'https://wanghengrun.cn/product/category/1-13.jpg', 4, 1, '2026-03-01 11:45:53.925755', '2026-03-01 16:03:27.115683');
INSERT INTO app.product_category VALUES (3, 1, 0, '咖啡', 'https://wanghengrun.cn/product/category/1-3.jpg', 5, 1, '2026-03-01 10:41:44.372841', '2026-03-01 16:03:34.686651');
INSERT INTO app.product_category VALUES (37, 1, 0, '小食', 'https://wanghengrun.cn/product/category/1-37.jpg', 6, 1, '2026-03-01 15:24:43.491084', '2026-03-01 16:03:41.729802');
INSERT INTO app.product_category VALUES (4, 2, 0, '意式咖啡', NULL, 1, 1, '2026-03-01 10:41:44.374404', '2026-03-01 10:41:44.374404');
INSERT INTO app.product_category VALUES (5, 2, 0, '手冲咖啡', NULL, 2, 1, '2026-03-01 10:41:44.374404', '2026-03-01 10:41:44.374404');
INSERT INTO app.product_category VALUES (6, 2, 0, '烘焙甜点', NULL, 3, 1, '2026-03-01 10:41:44.374404', '2026-03-01 10:41:44.374404');
INSERT INTO app.product_category VALUES (7, 3, 0, '热销主食', NULL, 1, 1, '2026-03-01 10:41:44.374612', '2026-03-01 10:41:44.374612');
INSERT INTO app.product_category VALUES (8, 3, 0, '精选炒菜', NULL, 2, 1, '2026-03-01 10:41:44.374612', '2026-03-01 10:41:44.374612');
INSERT INTO app.product_category VALUES (9, 3, 0, '清爽凉菜', NULL, 3, 1, '2026-03-01 10:41:44.374612', '2026-03-01 10:41:44.374612');
INSERT INTO app.product_category VALUES (10, 3, 0, '营养汤品', NULL, 4, 1, '2026-03-01 10:41:44.374612', '2026-03-01 10:41:44.374612');
INSERT INTO app.product_category VALUES (11, 3, 0, '米饭小食', NULL, 5, 1, '2026-03-01 10:41:44.374612', '2026-03-01 10:41:44.374612');
INSERT INTO app.product_category VALUES (17, 2, 0, '意式咖啡', NULL, 1, 1, '2026-03-01 11:45:53.928939', '2026-03-01 11:45:53.928939');
INSERT INTO app.product_category VALUES (18, 2, 0, '手冲咖啡', NULL, 2, 1, '2026-03-01 11:45:53.928939', '2026-03-01 11:45:53.928939');
INSERT INTO app.product_category VALUES (19, 2, 0, '冷萃咖啡', NULL, 3, 1, '2026-03-01 11:45:53.928939', '2026-03-01 11:45:53.928939');
INSERT INTO app.product_category VALUES (20, 2, 0, '非咖啡饮品', NULL, 4, 1, '2026-03-01 11:45:53.928939', '2026-03-01 11:45:53.928939');
INSERT INTO app.product_category VALUES (21, 2, 0, '烘焙甜点', NULL, 5, 1, '2026-03-01 11:45:53.928939', '2026-03-01 11:45:53.928939');
INSERT INTO app.product_category VALUES (22, 2, 0, '轻食简餐', NULL, 6, 1, '2026-03-01 11:45:53.928939', '2026-03-01 11:45:53.928939');
INSERT INTO app.product_category VALUES (23, 3, 0, '蛋糕系列', NULL, 1, 1, '2026-03-01 11:45:53.929696', '2026-03-01 11:45:53.929696');
INSERT INTO app.product_category VALUES (24, 3, 0, '布丁慕斯', NULL, 2, 1, '2026-03-01 11:45:53.929696', '2026-03-01 11:45:53.929696');
INSERT INTO app.product_category VALUES (25, 3, 0, '冰淇淋', NULL, 3, 1, '2026-03-01 11:45:53.929696', '2026-03-01 11:45:53.929696');
INSERT INTO app.product_category VALUES (26, 3, 0, '糖水炖品', NULL, 4, 1, '2026-03-01 11:45:53.929696', '2026-03-01 11:45:53.929696');
INSERT INTO app.product_category VALUES (27, 3, 0, '饮品', NULL, 5, 1, '2026-03-01 11:45:53.929696', '2026-03-01 11:45:53.929696');
INSERT INTO app.product_category VALUES (28, 4, 0, '热销主食', NULL, 1, 1, '2026-03-01 11:45:53.930137', '2026-03-01 11:45:53.930137');
INSERT INTO app.product_category VALUES (29, 4, 0, '精选炒菜', NULL, 2, 1, '2026-03-01 11:45:53.930137', '2026-03-01 11:45:53.930137');
INSERT INTO app.product_category VALUES (30, 4, 0, '清爽凉菜', NULL, 3, 1, '2026-03-01 11:45:53.930137', '2026-03-01 11:45:53.930137');
INSERT INTO app.product_category VALUES (31, 4, 0, '营养汤品', NULL, 4, 1, '2026-03-01 11:45:53.930137', '2026-03-01 11:45:53.930137');
INSERT INTO app.product_category VALUES (32, 4, 0, '米饭小食', NULL, 5, 1, '2026-03-01 11:45:53.930137', '2026-03-01 11:45:53.930137');
INSERT INTO app.product_category VALUES (33, 5, 0, '招牌拉面', NULL, 1, 1, '2026-03-01 11:45:53.930507', '2026-03-01 11:45:53.930507');
INSERT INTO app.product_category VALUES (34, 5, 0, '炒饭炒面', NULL, 2, 1, '2026-03-01 11:45:53.930507', '2026-03-01 11:45:53.930507');
INSERT INTO app.product_category VALUES (35, 5, 0, '日式小食', NULL, 3, 1, '2026-03-01 11:45:53.930507', '2026-03-01 11:45:53.930507');
INSERT INTO app.product_category VALUES (36, 5, 0, '饮料', NULL, 4, 1, '2026-03-01 11:45:53.930507', '2026-03-01 11:45:53.930507');


--
-- Data for Name: product_log; Type: TABLE DATA; Schema: app; Owner: -
--



--
-- Data for Name: product_sku; Type: TABLE DATA; Schema: app; Owner: -
--

INSERT INTO app.product_sku VALUES (453, 102, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 19, 0, NULL, 1, '2026-03-19 11:43:13.565688', '2026-03-23 20:46:20.797901');
INSERT INTO app.product_sku VALUES (438, 101, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 19, 0, NULL, 1, '2026-03-19 11:41:36.57871', '2026-03-23 20:46:20.797901');
INSERT INTO app.product_sku VALUES (456, 102, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 19, 0, NULL, 1, '2026-03-19 11:43:13.566834', '2026-03-23 20:46:20.797901');
INSERT INTO app.product_sku VALUES (488, 104, '[{"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1, 20, 0, NULL, 1, '2026-03-19 11:47:54.981346', '2026-03-19 11:47:54.981366');
INSERT INTO app.product_sku VALUES (490, 104, '[{"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1, 20, 0, NULL, 1, '2026-03-19 11:47:54.983354', '2026-03-19 11:47:54.983366');
INSERT INTO app.product_sku VALUES (433, 101, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.552131', '2026-03-19 11:41:36.552172');
INSERT INTO app.product_sku VALUES (518, 105, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.83286', '2026-03-19 11:52:38.832863');
INSERT INTO app.product_sku VALUES (466, 102, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.572264', '2026-03-19 11:43:13.572267');
INSERT INTO app.product_sku VALUES (467, 102, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.572669', '2026-03-19 11:43:13.572673');
INSERT INTO app.product_sku VALUES (468, 102, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.573021', '2026-03-19 11:43:13.573024');
INSERT INTO app.product_sku VALUES (469, 102, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.573346', '2026-03-19 11:43:13.573349');
INSERT INTO app.product_sku VALUES (470, 102, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.573685', '2026-03-19 11:43:13.573688');
INSERT INTO app.product_sku VALUES (471, 102, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.574268', '2026-03-19 11:43:13.574271');
INSERT INTO app.product_sku VALUES (472, 102, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.574597', '2026-03-19 11:43:13.5746');
INSERT INTO app.product_sku VALUES (473, 102, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.574941', '2026-03-19 11:43:13.574944');
INSERT INTO app.product_sku VALUES (483, 104, '[{"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1, 18, 0, NULL, 1, '2026-03-19 11:47:54.975514', '2026-03-23 20:46:20.797901');
INSERT INTO app.product_sku VALUES (420, 101, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 500, 18, 0, NULL, 1, '2026-03-19 11:41:36.543876', '2026-03-23 20:46:20.797901');
INSERT INTO app.product_sku VALUES (450, 102, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.564327', '2026-03-23 14:59:46.44707');
INSERT INTO app.product_sku VALUES (492, 105, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.820348', '2026-03-22 16:05:47.730648');
INSERT INTO app.product_sku VALUES (491, 104, '[{"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1, 20, 0, NULL, 1, '2026-03-19 11:47:54.984211', '2026-03-19 11:47:54.984225');
INSERT INTO app.product_sku VALUES (421, 101, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 500, 20, 0, NULL, 1, '2026-03-19 11:41:36.546188', '2026-03-19 11:41:36.546196');
INSERT INTO app.product_sku VALUES (422, 101, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 500, 20, 0, NULL, 1, '2026-03-19 11:41:36.546901', '2026-03-19 11:41:36.546907');
INSERT INTO app.product_sku VALUES (423, 101, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.547362', '2026-03-19 11:41:36.547366');
INSERT INTO app.product_sku VALUES (424, 101, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.547888', '2026-03-19 11:41:36.547893');
INSERT INTO app.product_sku VALUES (425, 101, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.548545', '2026-03-19 11:41:36.54855');
INSERT INTO app.product_sku VALUES (426, 101, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.548946', '2026-03-19 11:41:36.54895');
INSERT INTO app.product_sku VALUES (427, 101, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.549407', '2026-03-19 11:41:36.549412');
INSERT INTO app.product_sku VALUES (428, 101, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.549823', '2026-03-19 11:41:36.549828');
INSERT INTO app.product_sku VALUES (429, 101, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.550259', '2026-03-19 11:41:36.550632');
INSERT INTO app.product_sku VALUES (430, 101, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.551006', '2026-03-19 11:41:36.551011');
INSERT INTO app.product_sku VALUES (431, 101, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.551366', '2026-03-19 11:41:36.551371');
INSERT INTO app.product_sku VALUES (484, 104, '[{"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1, 20, 0, NULL, 1, '2026-03-19 11:47:54.977715', '2026-03-23 15:35:23.938468');
INSERT INTO app.product_sku VALUES (485, 104, '[{"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1, 20, 0, NULL, 1, '2026-03-19 11:47:54.978607', '2026-03-23 13:23:55.422654');
INSERT INTO app.product_sku VALUES (432, 101, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.551731', '2026-03-23 14:59:46.44707');
INSERT INTO app.product_sku VALUES (486, 104, '[{"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1, 20, 0, NULL, 1, '2026-03-19 11:47:54.979513', '2026-03-23 14:59:46.44707');
INSERT INTO app.product_sku VALUES (434, 101, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.552709', '2026-03-19 11:41:36.552724');
INSERT INTO app.product_sku VALUES (435, 101, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.553134', '2026-03-19 11:41:36.553141');
INSERT INTO app.product_sku VALUES (436, 101, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.577809', '2026-03-19 11:41:36.577826');
INSERT INTO app.product_sku VALUES (437, 101, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.578376', '2026-03-19 11:41:36.578381');
INSERT INTO app.product_sku VALUES (439, 101, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.579079', '2026-03-19 11:41:36.579083');
INSERT INTO app.product_sku VALUES (440, 101, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.579371', '2026-03-19 11:41:36.579374');
INSERT INTO app.product_sku VALUES (441, 101, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.579646', '2026-03-19 11:41:36.579649');
INSERT INTO app.product_sku VALUES (442, 101, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.579872', '2026-03-19 11:41:36.579875');
INSERT INTO app.product_sku VALUES (443, 101, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.580096', '2026-03-19 11:41:36.580099');
INSERT INTO app.product_sku VALUES (444, 101, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.580301', '2026-03-19 11:41:36.580304');
INSERT INTO app.product_sku VALUES (445, 101, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.580485', '2026-03-19 11:41:36.580488');
INSERT INTO app.product_sku VALUES (446, 101, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:41:36.580703', '2026-03-19 11:41:36.580706');
INSERT INTO app.product_sku VALUES (493, 105, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.821852', '2026-03-19 11:52:38.821863');
INSERT INTO app.product_sku VALUES (494, 105, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.822506', '2026-03-19 11:52:38.822513');
INSERT INTO app.product_sku VALUES (495, 105, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.822971', '2026-03-19 11:52:38.822976');
INSERT INTO app.product_sku VALUES (487, 104, '[{"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1, 20, 0, NULL, 1, '2026-03-19 11:47:54.980305', '2026-03-22 18:09:51.923657');
INSERT INTO app.product_sku VALUES (496, 105, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.823432', '2026-03-19 11:52:38.823439');
INSERT INTO app.product_sku VALUES (497, 105, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.823882', '2026-03-19 11:52:38.823887');
INSERT INTO app.product_sku VALUES (498, 105, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.824365', '2026-03-19 11:52:38.824376');
INSERT INTO app.product_sku VALUES (499, 105, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.825061', '2026-03-19 11:52:38.82507');
INSERT INTO app.product_sku VALUES (500, 105, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.825691', '2026-03-19 11:52:38.825712');
INSERT INTO app.product_sku VALUES (501, 105, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.826281', '2026-03-19 11:52:38.826288');
INSERT INTO app.product_sku VALUES (502, 105, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.826634', '2026-03-19 11:52:38.826638');
INSERT INTO app.product_sku VALUES (503, 105, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.827824', '2026-03-19 11:52:38.827827');
INSERT INTO app.product_sku VALUES (504, 105, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.828115', '2026-03-19 11:52:38.828118');
INSERT INTO app.product_sku VALUES (505, 105, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.828375', '2026-03-19 11:52:38.828379');
INSERT INTO app.product_sku VALUES (506, 105, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.828726', '2026-03-19 11:52:38.828728');
INSERT INTO app.product_sku VALUES (507, 105, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.829053', '2026-03-19 11:52:38.829056');
INSERT INTO app.product_sku VALUES (508, 105, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.829392', '2026-03-19 11:52:38.829394');
INSERT INTO app.product_sku VALUES (509, 105, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.829722', '2026-03-19 11:52:38.829724');
INSERT INTO app.product_sku VALUES (510, 105, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.830042', '2026-03-19 11:52:38.830045');
INSERT INTO app.product_sku VALUES (511, 105, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.830372', '2026-03-19 11:52:38.830375');
INSERT INTO app.product_sku VALUES (512, 105, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.830701', '2026-03-19 11:52:38.830703');
INSERT INTO app.product_sku VALUES (513, 105, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.831021', '2026-03-19 11:52:38.831024');
INSERT INTO app.product_sku VALUES (514, 105, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.831329', '2026-03-19 11:52:38.831331');
INSERT INTO app.product_sku VALUES (515, 105, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.831659', '2026-03-19 11:52:38.831661');
INSERT INTO app.product_sku VALUES (516, 105, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.832018', '2026-03-19 11:52:38.83202');
INSERT INTO app.product_sku VALUES (517, 105, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 800, 20, 0, NULL, 1, '2026-03-19 11:52:38.83243', '2026-03-19 11:52:38.832435');
INSERT INTO app.product_sku VALUES (447, 102, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.561557', '2026-03-19 11:43:13.561584');
INSERT INTO app.product_sku VALUES (448, 102, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.563247', '2026-03-19 11:43:13.563253');
INSERT INTO app.product_sku VALUES (449, 102, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.563826', '2026-03-19 11:43:13.563829');
INSERT INTO app.product_sku VALUES (451, 102, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.564758', '2026-03-19 11:43:13.564761');
INSERT INTO app.product_sku VALUES (452, 102, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.565274', '2026-03-19 11:43:13.56528');
INSERT INTO app.product_sku VALUES (454, 102, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.566072', '2026-03-19 11:43:13.566075');
INSERT INTO app.product_sku VALUES (455, 102, '[{"value": "小杯", "attrId": 1, "attrName": "杯型", "attrValueId": 1}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.566415', '2026-03-19 11:43:13.566418');
INSERT INTO app.product_sku VALUES (457, 102, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.567211', '2026-03-19 11:43:13.567214');
INSERT INTO app.product_sku VALUES (458, 102, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.567589', '2026-03-19 11:43:13.567594');
INSERT INTO app.product_sku VALUES (459, 102, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.567945', '2026-03-19 11:43:13.567948');
INSERT INTO app.product_sku VALUES (460, 102, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.568317', '2026-03-19 11:43:13.56832');
INSERT INTO app.product_sku VALUES (461, 102, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "热", "attrId": 2, "attrName": "温度", "attrValueId": 5}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.569174', '2026-03-19 11:43:13.569196');
INSERT INTO app.product_sku VALUES (462, 102, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.569966', '2026-03-19 11:43:13.569972');
INSERT INTO app.product_sku VALUES (463, 102, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "标准", "attrId": 3, "attrName": "甜度", "attrValueId": 8}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.570328', '2026-03-19 11:43:13.570331');
INSERT INTO app.product_sku VALUES (464, 102, '[{"value": "中杯", "attrId": 1, "attrName": "杯型", "attrValueId": 2}, {"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "三分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 9}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.570975', '2026-03-19 11:43:13.570978');
INSERT INTO app.product_sku VALUES (465, 102, '[{"value": "大杯", "attrId": 1, "attrName": "杯型", "attrValueId": 3}, {"value": "常温", "attrId": 2, "attrName": "温度", "attrValueId": 4}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1000, 20, 0, NULL, 1, '2026-03-19 11:43:13.571228', '2026-03-19 11:43:13.571231');
INSERT INTO app.product_sku VALUES (489, 104, '[{"value": "加冰", "attrId": 2, "attrName": "温度", "attrValueId": 6}, {"value": "七分糖", "attrId": 3, "attrName": "甜度", "attrValueId": 7}]', 1, 20, 0, NULL, 1, '2026-03-19 11:47:54.982469', '2026-03-22 20:04:57.998929');


--
-- Data for Name: product_sku_attribute_relation; Type: TABLE DATA; Schema: app; Owner: -
--

INSERT INTO app.product_sku_attribute_relation VALUES (1126, 420, 1, 1, 0, '2026-03-19 11:41:36.581043', '2026-03-19 11:41:36.581048', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1127, 420, 2, 4, 1, '2026-03-19 11:41:36.581135', '2026-03-19 11:41:36.581138', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1128, 420, 3, 7, 2, '2026-03-19 11:41:36.58118', '2026-03-19 11:41:36.581182', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1129, 421, 1, 1, 0, '2026-03-19 11:41:36.581224', '2026-03-19 11:41:36.581226', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1130, 421, 2, 4, 1, '2026-03-19 11:41:36.581263', '2026-03-19 11:41:36.581265', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1131, 421, 3, 8, 2, '2026-03-19 11:41:36.581302', '2026-03-19 11:41:36.581304', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1132, 422, 1, 1, 0, '2026-03-19 11:41:36.581346', '2026-03-19 11:41:36.581349', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1133, 422, 2, 4, 1, '2026-03-19 11:41:36.581385', '2026-03-19 11:41:36.581388', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1134, 422, 3, 9, 2, '2026-03-19 11:41:36.581424', '2026-03-19 11:41:36.581426', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1135, 423, 1, 1, 0, '2026-03-19 11:41:36.581458', '2026-03-19 11:41:36.58146', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1136, 423, 2, 5, 1, '2026-03-19 11:41:36.581501', '2026-03-19 11:41:36.581503', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1137, 423, 3, 7, 2, '2026-03-19 11:41:36.581552', '2026-03-19 11:41:36.581554', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1138, 424, 1, 1, 0, '2026-03-19 11:41:36.581605', '2026-03-19 11:41:36.581607', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1139, 424, 2, 5, 1, '2026-03-19 11:41:36.581663', '2026-03-19 11:41:36.581665', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1140, 424, 3, 8, 2, '2026-03-19 11:41:36.581704', '2026-03-19 11:41:36.581706', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1141, 425, 1, 1, 0, '2026-03-19 11:41:36.581748', '2026-03-19 11:41:36.58175', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1142, 425, 2, 5, 1, '2026-03-19 11:41:36.58179', '2026-03-19 11:41:36.581792', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1143, 425, 3, 9, 2, '2026-03-19 11:41:36.581832', '2026-03-19 11:41:36.581834', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1144, 426, 1, 1, 0, '2026-03-19 11:41:36.58187', '2026-03-19 11:41:36.581877', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1145, 426, 2, 6, 1, '2026-03-19 11:41:36.581914', '2026-03-19 11:41:36.581916', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1146, 426, 3, 7, 2, '2026-03-19 11:41:36.581956', '2026-03-19 11:41:36.581959', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1147, 427, 1, 1, 0, '2026-03-19 11:41:36.581995', '2026-03-19 11:41:36.581997', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1148, 427, 2, 6, 1, '2026-03-19 11:41:36.582034', '2026-03-19 11:41:36.582036', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1149, 427, 3, 8, 2, '2026-03-19 11:41:36.582072', '2026-03-19 11:41:36.582074', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1150, 428, 1, 1, 0, '2026-03-19 11:41:36.582111', '2026-03-19 11:41:36.582113', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1151, 428, 2, 6, 1, '2026-03-19 11:41:36.582156', '2026-03-19 11:41:36.582159', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1152, 428, 3, 9, 2, '2026-03-19 11:41:36.582191', '2026-03-19 11:41:36.582193', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1153, 429, 1, 2, 0, '2026-03-19 11:41:36.582224', '2026-03-19 11:41:36.582226', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1154, 429, 2, 4, 1, '2026-03-19 11:41:36.582263', '2026-03-19 11:41:36.582265', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1155, 429, 3, 7, 2, '2026-03-19 11:41:36.582301', '2026-03-19 11:41:36.582303', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1156, 430, 1, 2, 0, '2026-03-19 11:41:36.582338', '2026-03-19 11:41:36.58234', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1157, 430, 2, 4, 1, '2026-03-19 11:41:36.582376', '2026-03-19 11:41:36.582378', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1158, 430, 3, 8, 2, '2026-03-19 11:41:36.582416', '2026-03-19 11:41:36.582419', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1159, 431, 1, 2, 0, '2026-03-19 11:41:36.582452', '2026-03-19 11:41:36.582454', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1160, 431, 2, 4, 1, '2026-03-19 11:41:36.582491', '2026-03-19 11:41:36.582493', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1161, 431, 3, 9, 2, '2026-03-19 11:41:36.582529', '2026-03-19 11:41:36.582531', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1162, 432, 1, 2, 0, '2026-03-19 11:41:36.582568', '2026-03-19 11:41:36.58257', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1163, 432, 2, 5, 1, '2026-03-19 11:41:36.582607', '2026-03-19 11:41:36.58261', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1164, 432, 3, 7, 2, '2026-03-19 11:41:36.582649', '2026-03-19 11:41:36.582652', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1165, 433, 1, 2, 0, '2026-03-19 11:41:36.582694', '2026-03-19 11:41:36.582697', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1166, 433, 2, 5, 1, '2026-03-19 11:41:36.582735', '2026-03-19 11:41:36.582737', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1167, 433, 3, 8, 2, '2026-03-19 11:41:36.582782', '2026-03-19 11:41:36.582793', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1168, 434, 1, 2, 0, '2026-03-19 11:41:36.582834', '2026-03-19 11:41:36.582837', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1169, 434, 2, 5, 1, '2026-03-19 11:41:36.582874', '2026-03-19 11:41:36.582876', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1170, 434, 3, 9, 2, '2026-03-19 11:41:36.582911', '2026-03-19 11:41:36.582913', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1171, 435, 1, 2, 0, '2026-03-19 11:41:36.582953', '2026-03-19 11:41:36.582955', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1172, 435, 2, 6, 1, '2026-03-19 11:41:36.58299', '2026-03-19 11:41:36.582992', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1173, 435, 3, 7, 2, '2026-03-19 11:41:36.583028', '2026-03-19 11:41:36.58303', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1174, 436, 1, 2, 0, '2026-03-19 11:41:36.583067', '2026-03-19 11:41:36.583069', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1175, 436, 2, 6, 1, '2026-03-19 11:41:36.583106', '2026-03-19 11:41:36.583108', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1176, 436, 3, 8, 2, '2026-03-19 11:41:36.583144', '2026-03-19 11:41:36.583146', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1177, 437, 1, 2, 0, '2026-03-19 11:41:36.583186', '2026-03-19 11:41:36.583188', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1178, 437, 2, 6, 1, '2026-03-19 11:41:36.583223', '2026-03-19 11:41:36.583225', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1179, 437, 3, 9, 2, '2026-03-19 11:41:36.583261', '2026-03-19 11:41:36.583263', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1180, 438, 1, 3, 0, '2026-03-19 11:41:36.5833', '2026-03-19 11:41:36.583302', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1181, 438, 2, 4, 1, '2026-03-19 11:41:36.583338', '2026-03-19 11:41:36.58334', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1182, 438, 3, 7, 2, '2026-03-19 11:41:36.583387', '2026-03-19 11:41:36.583389', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1183, 439, 1, 3, 0, '2026-03-19 11:41:36.583424', '2026-03-19 11:41:36.583426', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1184, 439, 2, 4, 1, '2026-03-19 11:41:36.583466', '2026-03-19 11:41:36.583468', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1185, 439, 3, 8, 2, '2026-03-19 11:41:36.583505', '2026-03-19 11:41:36.583507', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1186, 440, 1, 3, 0, '2026-03-19 11:41:36.583542', '2026-03-19 11:41:36.583544', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1187, 440, 2, 4, 1, '2026-03-19 11:41:36.58358', '2026-03-19 11:41:36.583582', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1188, 440, 3, 9, 2, '2026-03-19 11:41:36.583618', '2026-03-19 11:41:36.58362', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1189, 441, 1, 3, 0, '2026-03-19 11:41:36.583657', '2026-03-19 11:41:36.583658', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1190, 441, 2, 5, 1, '2026-03-19 11:41:36.583694', '2026-03-19 11:41:36.583696', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1191, 441, 3, 7, 2, '2026-03-19 11:41:36.583735', '2026-03-19 11:41:36.583737', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1192, 442, 1, 3, 0, '2026-03-19 11:41:36.583772', '2026-03-19 11:41:36.583774', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1193, 442, 2, 5, 1, '2026-03-19 11:41:36.58381', '2026-03-19 11:41:36.583811', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1194, 442, 3, 8, 2, '2026-03-19 11:41:36.583849', '2026-03-19 11:41:36.583851', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1195, 443, 1, 3, 0, '2026-03-19 11:41:36.583885', '2026-03-19 11:41:36.583887', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1196, 443, 2, 5, 1, '2026-03-19 11:41:36.583922', '2026-03-19 11:41:36.583924', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1197, 443, 3, 9, 2, '2026-03-19 11:41:36.583959', '2026-03-19 11:41:36.583965', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1198, 444, 1, 3, 0, '2026-03-19 11:41:36.584027', '2026-03-19 11:41:36.584029', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1199, 444, 2, 6, 1, '2026-03-19 11:41:36.584066', '2026-03-19 11:41:36.584068', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1200, 444, 3, 7, 2, '2026-03-19 11:41:36.584104', '2026-03-19 11:41:36.584106', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1201, 445, 1, 3, 0, '2026-03-19 11:41:36.58415', '2026-03-19 11:41:36.584152', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1202, 445, 2, 6, 1, '2026-03-19 11:41:36.584188', '2026-03-19 11:41:36.58419', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1203, 445, 3, 8, 2, '2026-03-19 11:41:36.584226', '2026-03-19 11:41:36.584232', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1204, 446, 1, 3, 0, '2026-03-19 11:41:36.58427', '2026-03-19 11:41:36.584272', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1205, 446, 2, 6, 1, '2026-03-19 11:41:36.584306', '2026-03-19 11:41:36.584308', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1206, 446, 3, 9, 2, '2026-03-19 11:41:36.584342', '2026-03-19 11:41:36.584344', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1207, 447, 1, 1, 0, '2026-03-19 11:43:13.575429', '2026-03-19 11:43:13.575436', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1208, 447, 2, 4, 1, '2026-03-19 11:43:13.575539', '2026-03-19 11:43:13.575541', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1209, 447, 3, 7, 2, '2026-03-19 11:43:13.57562', '2026-03-19 11:43:13.575624', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1210, 448, 1, 1, 0, '2026-03-19 11:43:13.57568', '2026-03-19 11:43:13.575684', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1211, 448, 2, 4, 1, '2026-03-19 11:43:13.575729', '2026-03-19 11:43:13.575749', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1212, 448, 3, 8, 2, '2026-03-19 11:43:13.575795', '2026-03-19 11:43:13.575797', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1213, 449, 1, 1, 0, '2026-03-19 11:43:13.575845', '2026-03-19 11:43:13.575848', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1214, 449, 2, 4, 1, '2026-03-19 11:43:13.575893', '2026-03-19 11:43:13.575902', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1215, 449, 3, 9, 2, '2026-03-19 11:43:13.575948', '2026-03-19 11:43:13.575951', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1216, 450, 1, 1, 0, '2026-03-19 11:43:13.575993', '2026-03-19 11:43:13.575995', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1217, 450, 2, 5, 1, '2026-03-19 11:43:13.576037', '2026-03-19 11:43:13.57604', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1218, 450, 3, 7, 2, '2026-03-19 11:43:13.576082', '2026-03-19 11:43:13.576085', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1219, 451, 1, 1, 0, '2026-03-19 11:43:13.57613', '2026-03-19 11:43:13.576133', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1220, 451, 2, 5, 1, '2026-03-19 11:43:13.576176', '2026-03-19 11:43:13.576179', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1221, 451, 3, 8, 2, '2026-03-19 11:43:13.576224', '2026-03-19 11:43:13.576227', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1222, 452, 1, 1, 0, '2026-03-19 11:43:13.57627', '2026-03-19 11:43:13.576273', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1223, 452, 2, 5, 1, '2026-03-19 11:43:13.576316', '2026-03-19 11:43:13.576318', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1224, 452, 3, 9, 2, '2026-03-19 11:43:13.57637', '2026-03-19 11:43:13.576373', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1225, 453, 1, 1, 0, '2026-03-19 11:43:13.576449', '2026-03-19 11:43:13.576452', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1226, 453, 2, 6, 1, '2026-03-19 11:43:13.576499', '2026-03-19 11:43:13.576501', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1227, 453, 3, 7, 2, '2026-03-19 11:43:13.576547', '2026-03-19 11:43:13.57655', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1228, 454, 1, 1, 0, '2026-03-19 11:43:13.576601', '2026-03-19 11:43:13.576603', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1229, 454, 2, 6, 1, '2026-03-19 11:43:13.576647', '2026-03-19 11:43:13.576649', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1230, 454, 3, 8, 2, '2026-03-19 11:43:13.576697', '2026-03-19 11:43:13.5767', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1231, 455, 1, 1, 0, '2026-03-19 11:43:13.576743', '2026-03-19 11:43:13.576745', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1232, 455, 2, 6, 1, '2026-03-19 11:43:13.576856', '2026-03-19 11:43:13.576858', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1233, 455, 3, 9, 2, '2026-03-19 11:43:13.576901', '2026-03-19 11:43:13.576903', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1234, 456, 1, 2, 0, '2026-03-19 11:43:13.57696', '2026-03-19 11:43:13.576963', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1235, 456, 2, 4, 1, '2026-03-19 11:43:13.577007', '2026-03-19 11:43:13.577009', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1236, 456, 3, 7, 2, '2026-03-19 11:43:13.577052', '2026-03-19 11:43:13.577054', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1237, 457, 1, 2, 0, '2026-03-19 11:43:13.577097', '2026-03-19 11:43:13.5771', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1238, 457, 2, 4, 1, '2026-03-19 11:43:13.57714', '2026-03-19 11:43:13.577143', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1239, 457, 3, 8, 2, '2026-03-19 11:43:13.577188', '2026-03-19 11:43:13.57719', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1240, 458, 1, 2, 0, '2026-03-19 11:43:13.577233', '2026-03-19 11:43:13.577236', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1241, 458, 2, 4, 1, '2026-03-19 11:43:13.577283', '2026-03-19 11:43:13.577286', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1242, 458, 3, 9, 2, '2026-03-19 11:43:13.577329', '2026-03-19 11:43:13.577331', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1243, 459, 1, 2, 0, '2026-03-19 11:43:13.577373', '2026-03-19 11:43:13.577376', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1244, 459, 2, 5, 1, '2026-03-19 11:43:13.577421', '2026-03-19 11:43:13.577423', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1245, 459, 3, 7, 2, '2026-03-19 11:43:13.577467', '2026-03-19 11:43:13.57747', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1246, 460, 1, 2, 0, '2026-03-19 11:43:13.577516', '2026-03-19 11:43:13.577518', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1247, 460, 2, 5, 1, '2026-03-19 11:43:13.577575', '2026-03-19 11:43:13.577578', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1248, 460, 3, 8, 2, '2026-03-19 11:43:13.57762', '2026-03-19 11:43:13.577623', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1249, 461, 1, 2, 0, '2026-03-19 11:43:13.577666', '2026-03-19 11:43:13.577668', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1250, 461, 2, 5, 1, '2026-03-19 11:43:13.577717', '2026-03-19 11:43:13.57772', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1251, 461, 3, 9, 2, '2026-03-19 11:43:13.577778', '2026-03-19 11:43:13.577781', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1252, 462, 1, 2, 0, '2026-03-19 11:43:13.577832', '2026-03-19 11:43:13.577834', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1253, 462, 2, 6, 1, '2026-03-19 11:43:13.577876', '2026-03-19 11:43:13.577878', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1254, 462, 3, 7, 2, '2026-03-19 11:43:13.577926', '2026-03-19 11:43:13.577929', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1255, 463, 1, 2, 0, '2026-03-19 11:43:13.57797', '2026-03-19 11:43:13.577972', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1256, 463, 2, 6, 1, '2026-03-19 11:43:13.578025', '2026-03-19 11:43:13.578027', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1257, 463, 3, 8, 2, '2026-03-19 11:43:13.578072', '2026-03-19 11:43:13.578075', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1258, 464, 1, 2, 0, '2026-03-19 11:43:13.578117', '2026-03-19 11:43:13.57812', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1259, 464, 2, 6, 1, '2026-03-19 11:43:13.578161', '2026-03-19 11:43:13.578163', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1260, 464, 3, 9, 2, '2026-03-19 11:43:13.57821', '2026-03-19 11:43:13.578213', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1261, 465, 1, 3, 0, '2026-03-19 11:43:13.578256', '2026-03-19 11:43:13.578258', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1262, 465, 2, 4, 1, '2026-03-19 11:43:13.578299', '2026-03-19 11:43:13.578301', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1263, 465, 3, 7, 2, '2026-03-19 11:43:13.578343', '2026-03-19 11:43:13.578345', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1264, 466, 1, 3, 0, '2026-03-19 11:43:13.578385', '2026-03-19 11:43:13.578387', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1265, 466, 2, 4, 1, '2026-03-19 11:43:13.578428', '2026-03-19 11:43:13.57843', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1266, 466, 3, 8, 2, '2026-03-19 11:43:13.578471', '2026-03-19 11:43:13.578473', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1267, 467, 1, 3, 0, '2026-03-19 11:43:13.578517', '2026-03-19 11:43:13.57852', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1268, 467, 2, 4, 1, '2026-03-19 11:43:13.57856', '2026-03-19 11:43:13.578563', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1269, 467, 3, 9, 2, '2026-03-19 11:43:13.578604', '2026-03-19 11:43:13.578607', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1270, 468, 1, 3, 0, '2026-03-19 11:43:13.578651', '2026-03-19 11:43:13.578653', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1271, 468, 2, 5, 1, '2026-03-19 11:43:13.578694', '2026-03-19 11:43:13.578696', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1272, 468, 3, 7, 2, '2026-03-19 11:43:13.578737', '2026-03-19 11:43:13.578739', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1273, 469, 1, 3, 0, '2026-03-19 11:43:13.5788', '2026-03-19 11:43:13.578802', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1274, 469, 2, 5, 1, '2026-03-19 11:43:13.578847', '2026-03-19 11:43:13.578849', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1275, 469, 3, 8, 2, '2026-03-19 11:43:13.578889', '2026-03-19 11:43:13.578892', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1276, 470, 1, 3, 0, '2026-03-19 11:43:13.578935', '2026-03-19 11:43:13.578937', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1277, 470, 2, 5, 1, '2026-03-19 11:43:13.578979', '2026-03-19 11:43:13.578981', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1278, 470, 3, 9, 2, '2026-03-19 11:43:13.57902', '2026-03-19 11:43:13.579022', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1279, 471, 1, 3, 0, '2026-03-19 11:43:13.579066', '2026-03-19 11:43:13.579068', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1280, 471, 2, 6, 1, '2026-03-19 11:43:13.579108', '2026-03-19 11:43:13.579118', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1306, 483, 2, 4, 0, '2026-03-19 11:47:54.985283', '2026-03-19 11:47:54.985303', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1307, 483, 3, 7, 1, '2026-03-19 11:47:54.985572', '2026-03-19 11:47:54.985582', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1308, 484, 2, 4, 0, '2026-03-19 11:47:54.985726', '2026-03-19 11:47:54.985735', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1309, 484, 3, 8, 1, '2026-03-19 11:47:54.985877', '2026-03-19 11:47:54.985886', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1310, 485, 2, 4, 0, '2026-03-19 11:47:54.986024', '2026-03-19 11:47:54.986033', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1311, 485, 3, 9, 1, '2026-03-19 11:47:54.986175', '2026-03-19 11:47:54.986184', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1312, 486, 2, 5, 0, '2026-03-19 11:47:54.986323', '2026-03-19 11:47:54.986331', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1313, 486, 3, 7, 1, '2026-03-19 11:47:54.986472', '2026-03-19 11:47:54.98648', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1314, 487, 2, 5, 0, '2026-03-19 11:47:54.986639', '2026-03-19 11:47:54.986648', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1315, 487, 3, 8, 1, '2026-03-19 11:47:54.986791', '2026-03-19 11:47:54.9868', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1316, 488, 2, 5, 0, '2026-03-19 11:47:54.986942', '2026-03-19 11:47:54.986951', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1317, 488, 3, 9, 1, '2026-03-19 11:47:54.987087', '2026-03-19 11:47:54.987092', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1318, 489, 2, 6, 0, '2026-03-19 11:47:54.987193', '2026-03-19 11:47:54.987197', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1319, 489, 3, 7, 1, '2026-03-19 11:47:54.987282', '2026-03-19 11:47:54.987287', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1320, 490, 2, 6, 0, '2026-03-19 11:47:54.98738', '2026-03-19 11:47:54.987395', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1321, 490, 3, 8, 1, '2026-03-19 11:47:54.98748', '2026-03-19 11:47:54.987485', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1322, 491, 2, 6, 0, '2026-03-19 11:47:54.987567', '2026-03-19 11:47:54.987573', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1323, 491, 3, 9, 1, '2026-03-19 11:47:54.987658', '2026-03-19 11:47:54.987663', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1324, 492, 1, 1, 0, '2026-03-19 11:52:38.833475', '2026-03-19 11:52:38.83349', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1325, 492, 2, 4, 1, '2026-03-19 11:52:38.833595', '2026-03-19 11:52:38.833597', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1326, 492, 3, 7, 2, '2026-03-19 11:52:38.833645', '2026-03-19 11:52:38.833647', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1327, 493, 1, 1, 0, '2026-03-19 11:52:38.833686', '2026-03-19 11:52:38.833688', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1328, 493, 2, 4, 1, '2026-03-19 11:52:38.833729', '2026-03-19 11:52:38.833731', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1329, 493, 3, 8, 2, '2026-03-19 11:52:38.833769', '2026-03-19 11:52:38.833779', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1330, 494, 1, 1, 0, '2026-03-19 11:52:38.833816', '2026-03-19 11:52:38.833818', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1331, 494, 2, 4, 1, '2026-03-19 11:52:38.833854', '2026-03-19 11:52:38.833856', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1332, 494, 3, 9, 2, '2026-03-19 11:52:38.833894', '2026-03-19 11:52:38.833896', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1333, 495, 1, 1, 0, '2026-03-19 11:52:38.833933', '2026-03-19 11:52:38.833935', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1334, 495, 2, 5, 1, '2026-03-19 11:52:38.833972', '2026-03-19 11:52:38.833974', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1335, 495, 3, 7, 2, '2026-03-19 11:52:38.847004', '2026-03-19 11:52:38.847018', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1336, 496, 1, 1, 0, '2026-03-19 11:52:38.847124', '2026-03-19 11:52:38.847129', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1337, 496, 2, 5, 1, '2026-03-19 11:52:38.847173', '2026-03-19 11:52:38.847175', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1338, 496, 3, 8, 2, '2026-03-19 11:52:38.847207', '2026-03-19 11:52:38.847209', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1339, 497, 1, 1, 0, '2026-03-19 11:52:38.847242', '2026-03-19 11:52:38.847244', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1340, 497, 2, 5, 1, '2026-03-19 11:52:38.847275', '2026-03-19 11:52:38.847277', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1341, 497, 3, 9, 2, '2026-03-19 11:52:38.847306', '2026-03-19 11:52:38.847308', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1342, 498, 1, 1, 0, '2026-03-19 11:52:38.847338', '2026-03-19 11:52:38.847344', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1343, 498, 2, 6, 1, '2026-03-19 11:52:38.847376', '2026-03-19 11:52:38.847377', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1344, 498, 3, 7, 2, '2026-03-19 11:52:38.847409', '2026-03-19 11:52:38.84741', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1345, 499, 1, 1, 0, '2026-03-19 11:52:38.847446', '2026-03-19 11:52:38.847448', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1346, 499, 2, 6, 1, '2026-03-19 11:52:38.847479', '2026-03-19 11:52:38.84748', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1347, 499, 3, 8, 2, '2026-03-19 11:52:38.847513', '2026-03-19 11:52:38.847514', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1348, 500, 1, 1, 0, '2026-03-19 11:52:38.847547', '2026-03-19 11:52:38.847549', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1349, 500, 2, 6, 1, '2026-03-19 11:52:38.847583', '2026-03-19 11:52:38.847585', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1350, 500, 3, 9, 2, '2026-03-19 11:52:38.847616', '2026-03-19 11:52:38.847618', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1351, 501, 1, 2, 0, '2026-03-19 11:52:38.847649', '2026-03-19 11:52:38.847651', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1352, 501, 2, 4, 1, '2026-03-19 11:52:38.847682', '2026-03-19 11:52:38.847684', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1353, 501, 3, 7, 2, '2026-03-19 11:52:38.847715', '2026-03-19 11:52:38.847717', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1354, 502, 1, 2, 0, '2026-03-19 11:52:38.847749', '2026-03-19 11:52:38.84775', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1355, 502, 2, 4, 1, '2026-03-19 11:52:38.847784', '2026-03-19 11:52:38.847789', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1356, 502, 3, 8, 2, '2026-03-19 11:52:38.847818', '2026-03-19 11:52:38.84782', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1357, 503, 1, 2, 0, '2026-03-19 11:52:38.84785', '2026-03-19 11:52:38.847852', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1358, 503, 2, 4, 1, '2026-03-19 11:52:38.847883', '2026-03-19 11:52:38.847885', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1359, 503, 3, 9, 2, '2026-03-19 11:52:38.847921', '2026-03-19 11:52:38.847923', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1360, 504, 1, 2, 0, '2026-03-19 11:52:38.847962', '2026-03-19 11:52:38.847964', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1361, 504, 2, 5, 1, '2026-03-19 11:52:38.847994', '2026-03-19 11:52:38.847995', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1362, 504, 3, 7, 2, '2026-03-19 11:52:38.848028', '2026-03-19 11:52:38.84803', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1363, 505, 1, 2, 0, '2026-03-19 11:52:38.848057', '2026-03-19 11:52:38.848058', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1364, 505, 2, 5, 1, '2026-03-19 11:52:38.848088', '2026-03-19 11:52:38.848089', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1365, 505, 3, 8, 2, '2026-03-19 11:52:38.848117', '2026-03-19 11:52:38.848119', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1366, 506, 1, 2, 0, '2026-03-19 11:52:38.848148', '2026-03-19 11:52:38.84815', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1367, 506, 2, 5, 1, '2026-03-19 11:52:38.848181', '2026-03-19 11:52:38.848183', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1368, 506, 3, 9, 2, '2026-03-19 11:52:38.848212', '2026-03-19 11:52:38.848214', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1369, 507, 1, 2, 0, '2026-03-19 11:52:38.84825', '2026-03-19 11:52:38.848252', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1370, 507, 2, 6, 1, '2026-03-19 11:52:38.848283', '2026-03-19 11:52:38.848284', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1371, 507, 3, 7, 2, '2026-03-19 11:52:38.848314', '2026-03-19 11:52:38.848315', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1372, 508, 1, 2, 0, '2026-03-19 11:52:38.848341', '2026-03-19 11:52:38.848342', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1373, 508, 2, 6, 1, '2026-03-19 11:52:38.848373', '2026-03-19 11:52:38.848374', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1374, 508, 3, 8, 2, '2026-03-19 11:52:38.848404', '2026-03-19 11:52:38.848406', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1375, 509, 1, 2, 0, '2026-03-19 11:52:38.848435', '2026-03-19 11:52:38.848437', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1376, 509, 2, 6, 1, '2026-03-19 11:52:38.848468', '2026-03-19 11:52:38.84847', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1377, 509, 3, 9, 2, '2026-03-19 11:52:38.848501', '2026-03-19 11:52:38.848503', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1378, 510, 1, 3, 0, '2026-03-19 11:52:38.848528', '2026-03-19 11:52:38.84853', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1379, 510, 2, 4, 1, '2026-03-19 11:52:38.848555', '2026-03-19 11:52:38.848556', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1380, 510, 3, 7, 2, '2026-03-19 11:52:38.848585', '2026-03-19 11:52:38.848586', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1381, 511, 1, 3, 0, '2026-03-19 11:52:38.848614', '2026-03-19 11:52:38.848616', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1382, 511, 2, 4, 1, '2026-03-19 11:52:38.848646', '2026-03-19 11:52:38.848647', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1383, 511, 3, 8, 2, '2026-03-19 11:52:38.848672', '2026-03-19 11:52:38.848673', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1281, 471, 3, 7, 2, '2026-03-19 11:43:13.579159', '2026-03-19 11:43:13.579162', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1282, 472, 1, 3, 0, '2026-03-19 11:43:13.579205', '2026-03-19 11:43:13.579207', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1283, 472, 2, 6, 1, '2026-03-19 11:43:13.579248', '2026-03-19 11:43:13.57925', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1284, 472, 3, 8, 2, '2026-03-19 11:43:13.579292', '2026-03-19 11:43:13.579294', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1285, 473, 1, 3, 0, '2026-03-19 11:43:13.579335', '2026-03-19 11:43:13.579337', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1286, 473, 2, 6, 1, '2026-03-19 11:43:13.579379', '2026-03-19 11:43:13.579385', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1287, 473, 3, 9, 2, '2026-03-19 11:43:13.579426', '2026-03-19 11:43:13.579429', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1384, 512, 1, 3, 0, '2026-03-19 11:52:38.848697', '2026-03-19 11:52:38.848699', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1385, 512, 2, 4, 1, '2026-03-19 11:52:38.848723', '2026-03-19 11:52:38.848725', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1386, 512, 3, 9, 2, '2026-03-19 11:52:38.848749', '2026-03-19 11:52:38.848751', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1387, 513, 1, 3, 0, '2026-03-19 11:52:38.848776', '2026-03-19 11:52:38.848777', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1388, 513, 2, 5, 1, '2026-03-19 11:52:38.848802', '2026-03-19 11:52:38.848803', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1389, 513, 3, 7, 2, '2026-03-19 11:52:38.848828', '2026-03-19 11:52:38.848829', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1390, 514, 1, 3, 0, '2026-03-19 11:52:38.848854', '2026-03-19 11:52:38.848855', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1391, 514, 2, 5, 1, '2026-03-19 11:52:38.84888', '2026-03-19 11:52:38.848881', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1392, 514, 3, 8, 2, '2026-03-19 11:52:38.848906', '2026-03-19 11:52:38.848907', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1393, 515, 1, 3, 0, '2026-03-19 11:52:38.848932', '2026-03-19 11:52:38.848933', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1394, 515, 2, 5, 1, '2026-03-19 11:52:38.848957', '2026-03-19 11:52:38.848959', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1395, 515, 3, 9, 2, '2026-03-19 11:52:38.848983', '2026-03-19 11:52:38.848985', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1396, 516, 1, 3, 0, '2026-03-19 11:52:38.849009', '2026-03-19 11:52:38.84901', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1397, 516, 2, 6, 1, '2026-03-19 11:52:38.849035', '2026-03-19 11:52:38.849036', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1398, 516, 3, 7, 2, '2026-03-19 11:52:38.849061', '2026-03-19 11:52:38.849062', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1399, 517, 1, 3, 0, '2026-03-19 11:52:38.849087', '2026-03-19 11:52:38.849089', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1400, 517, 2, 6, 1, '2026-03-19 11:52:38.849119', '2026-03-19 11:52:38.849121', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1401, 517, 3, 8, 2, '2026-03-19 11:52:38.84915', '2026-03-19 11:52:38.849152', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1402, 518, 1, 3, 0, '2026-03-19 11:52:38.84918', '2026-03-19 11:52:38.849182', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1403, 518, 2, 6, 1, '2026-03-19 11:52:38.849209', '2026-03-19 11:52:38.849211', NULL, NULL);
INSERT INTO app.product_sku_attribute_relation VALUES (1404, 518, 3, 9, 2, '2026-03-19 11:52:38.849237', '2026-03-19 11:52:38.849239', NULL, NULL);


--
-- Data for Name: store; Type: TABLE DATA; Schema: app; Owner: -
--

INSERT INTO app.store VALUES (1, '甜趣奶茶店 (万达店)', '北京市朝阳区建国路 93 号万达广场 1 楼 101 铺', '13800138001', '10:00-22:00', '', 1, true, true, true, 5000, 2000, '满 20 元免配送费，不满收取 3 元', '招牌奶茶店，主打新鲜现做奶茶', '2026-03-01 11:45:53.923656', '2026-03-01 11:45:53.923656');
INSERT INTO app.store VALUES (2, '香醇咖啡馆 (国贸店)', '北京市朝阳区建国门外大街 1 号国贸商城 3 楼 302 室', '13800138002', '08:00-21:00', '', 1, true, true, true, 3000, 3000, '满 50 元免配送费，不满收取 5 元', '精品咖啡馆，现磨咖啡豆', '2026-03-01 11:45:53.923656', '2026-03-01 11:45:53.923656');
INSERT INTO app.store VALUES (3, '甜品小筑 (三里屯店)', '北京市朝阳区三里屯路 19 号院三里屯太古里南区 2 楼', '13800138003', '11:00-23:00', '', 1, true, true, false, 2000, 2500, '满 30 元免配送费，不满收取 4 元', '手工甜品店，每日现做', '2026-03-01 11:45:53.923656', '2026-03-01 11:45:53.923656');
INSERT INTO app.store VALUES (4, '家常小厨 (中关村店)', '北京市海淀区中关村大街 1 号海龙大厦 1 楼', '13800138004', '10:30-21:30', '', 1, true, true, true, 4000, 1500, '满 25 元免配送费，不满收取 4 元', '家常菜馆，现炒现卖', '2026-03-01 11:45:53.923656', '2026-03-01 11:45:53.923656');
INSERT INTO app.store VALUES (5, '日式拉面屋 (五道口店)', '北京市海淀区成府路 28 号五道口购物中心 4 楼', '13800138005', '11:00-22:00', '', 1, true, false, true, 0, 0, '仅限到店自取', '正宗日式拉面，手工制面', '2026-03-01 11:45:53.923656', '2026-03-01 11:45:53.923656');


--
-- Data for Name: table_qrcode; Type: TABLE DATA; Schema: app; Owner: -
--

INSERT INTO app.table_qrcode VALUES (23, 1, '大厅4号桌', 'https://wanghengrun.cn/product/qrcode/1-A04.png', '', 'A04', 1, false, 4, '大厅4号桌', '444', '2026-03-14 11:07:13.199254', '2026-03-14 11:07:13.199488', NULL, NULL, '{"page": "pages/scan/index", "scene": "1-A04", "width": 430, "autoColor": true, "checkPath": false, "isHyaline": true, "envVersion": "develop"}');
INSERT INTO app.table_qrcode VALUES (21, 1, '大厅一号桌', 'https://wanghengrun.cn/product/qrcode/1-A01.png', '', 'A01', 1, false, 4, '大厅', '测试', '2026-03-14 10:58:03.516741', '2026-03-14 12:27:10.592632', NULL, NULL, '{"page": "pages/index/index", "scene": "1-A01", "width": 430, "autoColor": true, "checkPath": false, "isHyaline": true, "envVersion": "develop"}');
INSERT INTO app.table_qrcode VALUES (22, 1, '大厅二号桌', 'https://wanghengrun.cn/product/qrcode/1-A02.png', '', 'A02', 1, false, 4, '大厅二号桌', '测试', '2026-03-14 11:06:17.683898', '2026-03-14 12:27:17.809875', NULL, NULL, '{"page": "pages/index/index", "scene": "1-A02", "width": 430, "autoColor": true, "checkPath": false, "isHyaline": true, "envVersion": "develop"}');
INSERT INTO app.table_qrcode VALUES (20, 1, '大厅03', 'https://wanghengrun.cn/product/qrcode/1-A03.png', '', 'A03', 1, false, 4, '大厅', '1111', '2026-03-14 10:42:15.487457', '2026-03-14 12:27:25.331328', NULL, NULL, '{"page": "pages/index/index", "scene": "1-A03", "width": 430, "autoColor": true, "checkPath": false, "isHyaline": true, "envVersion": "develop"}');


--
-- Name: app_user_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.app_user_id_seq', 13, true);


--
-- Name: attribute_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.attribute_id_seq', 5, true);


--
-- Name: attribute_value_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.attribute_value_id_seq', 21, true);


--
-- Name: banner_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.banner_id_seq', 9, true);


--
-- Name: order_detail_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.order_detail_id_seq', 60, true);


--
-- Name: order_main_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.order_main_id_seq', 39, true);


--
-- Name: order_refund_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.order_refund_id_seq', 1, false);


--
-- Name: product_attribute_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.product_attribute_id_seq', 136, true);


--
-- Name: product_category_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.product_category_id_seq', 39, true);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.product_id_seq', 105, true);


--
-- Name: product_log_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.product_log_id_seq', 1, false);


--
-- Name: product_sku_attribute_relation_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.product_sku_attribute_relation_id_seq', 1404, true);


--
-- Name: product_sku_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.product_sku_id_seq', 518, true);


--
-- Name: store_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.store_id_seq', 3, true);


--
-- Name: table_qrcode_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.table_qrcode_id_seq', 24, true);


--
-- Name: app_user app_user_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.app_user
    ADD CONSTRAINT app_user_pkey PRIMARY KEY (id);


--
-- Name: attribute attribute_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.attribute
    ADD CONSTRAINT attribute_pkey PRIMARY KEY (id);


--
-- Name: attribute_value attribute_value_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.attribute_value
    ADD CONSTRAINT attribute_value_pkey PRIMARY KEY (id);


--
-- Name: banner banner_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.banner
    ADD CONSTRAINT banner_pkey PRIMARY KEY (id);


--
-- Name: order_detail order_detail_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.order_detail
    ADD CONSTRAINT order_detail_pkey PRIMARY KEY (id);


--
-- Name: order_main order_main_order_no_key; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.order_main
    ADD CONSTRAINT order_main_order_no_key UNIQUE (order_no);


--
-- Name: order_main order_main_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.order_main
    ADD CONSTRAINT order_main_pkey PRIMARY KEY (id);


--
-- Name: order_refund order_refund_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.order_refund
    ADD CONSTRAINT order_refund_pkey PRIMARY KEY (id);


--
-- Name: product_attribute_relation product_attribute_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.product_attribute_relation
    ADD CONSTRAINT product_attribute_pkey PRIMARY KEY (id);


--
-- Name: product_attribute_relation product_attribute_product_id_attribute_id_attribute_value_i_key; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.product_attribute_relation
    ADD CONSTRAINT product_attribute_product_id_attribute_id_attribute_value_i_key UNIQUE (product_id, attribute_id, attribute_value_id);


--
-- Name: product_category product_category_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.product_category
    ADD CONSTRAINT product_category_pkey PRIMARY KEY (id);


--
-- Name: product_log product_log_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.product_log
    ADD CONSTRAINT product_log_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: product_sku_attribute_relation product_sku_attribute_relation_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.product_sku_attribute_relation
    ADD CONSTRAINT product_sku_attribute_relation_pkey PRIMARY KEY (id);


--
-- Name: product_sku product_sku_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.product_sku
    ADD CONSTRAINT product_sku_pkey PRIMARY KEY (id);


--
-- Name: store store_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.store
    ADD CONSTRAINT store_pkey PRIMARY KEY (id);


--
-- Name: table_qrcode table_qrcode_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.table_qrcode
    ADD CONSTRAINT table_qrcode_pkey PRIMARY KEY (id);


--
-- Name: order_refund uk_refund_no; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.order_refund
    ADD CONSTRAINT uk_refund_no UNIQUE (refund_no);


--
-- Name: product_sku_attribute_relation uk_sku_attr_value_id; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.product_sku_attribute_relation
    ADD CONSTRAINT uk_sku_attr_value_id UNIQUE (sku_id, attribute_id, attribute_value_id);


--
-- Name: table_qrcode uk_store_qrcode_no; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.table_qrcode
    ADD CONSTRAINT uk_store_qrcode_no UNIQUE (qrcode_no, store_id);


--
-- Name: CONSTRAINT uk_store_qrcode_no ON table_qrcode; Type: COMMENT; Schema: app; Owner: -
--

COMMENT ON CONSTRAINT uk_store_qrcode_no ON app.table_qrcode IS '相同门店下桌码编号是唯一的';


--
-- Name: app_user uk_wechat_openid; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.app_user
    ADD CONSTRAINT uk_wechat_openid UNIQUE (wechat_openid);


--
-- Name: idx_category_parent_id; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_category_parent_id ON app.product_category USING btree (parent_id);


--
-- Name: idx_category_status; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_category_status ON app.product_category USING btree (status);


--
-- Name: idx_category_store_id; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_category_store_id ON app.product_category USING btree (store_id);


--
-- Name: idx_order_no; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_order_no ON app.order_detail USING btree (order_no);


--
-- Name: idx_order_status; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_order_status ON app.order_main USING btree (order_status);


--
-- Name: idx_order_store; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_order_store ON app.order_main USING btree (store_id);


--
-- Name: idx_order_user; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_order_user ON app.order_main USING btree (user_id);


--
-- Name: idx_product_category_id; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_product_category_id ON app.product USING btree (category_id);


--
-- Name: idx_product_create_time; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_product_create_time ON app.product USING btree (create_time DESC);


--
-- Name: idx_product_is_hot; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_product_is_hot ON app.product USING btree (is_hot);


--
-- Name: idx_product_is_recommend; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_product_is_recommend ON app.product USING btree (is_recommend);


--
-- Name: idx_product_log_create_time; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_product_log_create_time ON app.product_log USING btree (create_time DESC);


--
-- Name: idx_product_log_product_id; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_product_log_product_id ON app.product_log USING btree (product_id);


--
-- Name: idx_product_name_search; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_product_name_search ON app.product USING gin (to_tsvector('simple'::regconfig, (product_name)::text));


--
-- Name: idx_product_status; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_product_status ON app.product USING btree (status);


--
-- Name: idx_product_store_id; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_product_store_id ON app.product USING btree (store_id);


--
-- Name: idx_product_store_status; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_product_store_status ON app.product USING btree (store_id, status);


--
-- Name: idx_sku_attr_attribute_id; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_sku_attr_attribute_id ON app.product_sku_attribute_relation USING btree (attribute_id);


--
-- Name: idx_sku_attr_sku_id; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_sku_attr_sku_id ON app.product_sku_attribute_relation USING btree (sku_id);


--
-- Name: idx_sku_product_id; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_sku_product_id ON app.product_sku USING btree (product_id);


--
-- Name: idx_sku_specs; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_sku_specs ON app.product_sku USING gin (specs);


--
-- Name: idx_sku_status; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_sku_status ON app.product_sku USING btree (status);


--
-- Name: idx_table_qrcode_qrcode_no; Type: INDEX; Schema: app; Owner: -
--

CREATE INDEX idx_table_qrcode_qrcode_no ON app.table_qrcode USING btree (qrcode_no);


--
-- Name: uk_app_user_alipay_openid; Type: INDEX; Schema: app; Owner: -
--

CREATE UNIQUE INDEX uk_app_user_alipay_openid ON app.app_user USING btree (alipay_openid);


--
-- Name: uk_app_user_douyin_openid; Type: INDEX; Schema: app; Owner: -
--

CREATE UNIQUE INDEX uk_app_user_douyin_openid ON app.app_user USING btree (douyin_openid);


--
-- Name: uk_app_user_name; Type: INDEX; Schema: app; Owner: -
--

CREATE UNIQUE INDEX uk_app_user_name ON app.app_user USING btree (username);


--
-- Name: uk_app_user_wechat_openid; Type: INDEX; Schema: app; Owner: -
--

CREATE UNIQUE INDEX uk_app_user_wechat_openid ON app.app_user USING btree (wechat_openid);


--
-- Name: attribute_value attribute_value_attribute_id_fkey; Type: FK CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.attribute_value
    ADD CONSTRAINT attribute_value_attribute_id_fkey FOREIGN KEY (attribute_id) REFERENCES app.attribute(id);


--
-- PostgreSQL database dump complete
--



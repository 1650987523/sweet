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
-- Name: admin; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA admin;


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: admin_dict; Type: TABLE; Schema: admin; Owner: -
--


-- 创建部门表
CREATE TABLE admin.admin_dept (
                                  id SERIAL PRIMARY KEY,              -- int 自增
                                  parent_id INTEGER DEFAULT 0,
                                  department_name VARCHAR(100) NOT NULL,
                                  status SMALLINT DEFAULT 1,
                                  remark VARCHAR(500),
                                  create_time TIMESTAMP DEFAULT NOW(),
                                  update_time TIMESTAMP DEFAULT NOW(),
                                  create_by VARCHAR(50),
                                  update_by VARCHAR(50)
);

-- 添加注释
COMMENT ON TABLE admin.admin_dept IS '部门表';
COMMENT ON COLUMN admin.admin_dept.id IS '部门 ID（自增）';
COMMENT ON COLUMN admin.admin_dept.parent_id IS '父部门 ID';
COMMENT ON COLUMN admin.admin_dept.department_name IS '部门名称';
COMMENT ON COLUMN admin.admin_dept.status IS '状态：1=正常，0=禁用';

CREATE TABLE admin.admin_dict (
                                  id bigint NOT NULL,
                                  dict_type character varying(50) NOT NULL,
                                  dict_label character varying(100) NOT NULL,
                                  dict_value character varying(50) NOT NULL,
                                  sort integer DEFAULT 0 NOT NULL,
                                  tag_type character varying(20),
                                  is_default boolean DEFAULT false NOT NULL,
                                  status smallint DEFAULT 1 NOT NULL,
                                  remark character varying(500),
                                  create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                  update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


--
-- Name: TABLE admin_dict; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON TABLE admin.admin_dict IS '数据字典表';


--
-- Name: COLUMN admin_dict.dict_type; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_dict.dict_type IS '字典类型标识';


--
-- Name: COLUMN admin_dict.dict_label; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_dict.dict_label IS '字典标签（显示文本）';


--
-- Name: COLUMN admin_dict.dict_value; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_dict.dict_value IS '字典值（存储值）';


--
-- Name: COLUMN admin_dict.sort; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_dict.sort IS '排序';


--
-- Name: COLUMN admin_dict.tag_type; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_dict.tag_type IS '标签样式 (Element Plus: primary/success/warning/danger/info)';


--
-- Name: COLUMN admin_dict.is_default; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_dict.is_default IS '是否默认值';


--
-- Name: COLUMN admin_dict.status; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_dict.status IS '状态：1-启用 2-禁用';


--
-- Name: admin_dict_id_seq; Type: SEQUENCE; Schema: admin; Owner: -
--

CREATE SEQUENCE admin.admin_dict_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: admin_dict_id_seq; Type: SEQUENCE OWNED BY; Schema: admin; Owner: -
--

ALTER SEQUENCE admin.admin_dict_id_seq OWNED BY admin.admin_dict.id;


--
-- Name: admin_menu; Type: TABLE; Schema: admin; Owner: -
--

CREATE TABLE admin.admin_menu (
                                  id integer NOT NULL,
                                  parent_id integer,
                                  path text,
                                  component text,
                                  name text,
                                  redirect text,
                                  title text,
                                  menu_type character(1) DEFAULT 'C'::bpchar NOT NULL,
                                  menu_status smallint DEFAULT 1,
                                  visible smallint DEFAULT 1,
                                  meta jsonb,
                                  order_num integer,
                                  perms text,
                                  permission_id integer,
                                  is_frame smallint DEFAULT 1,
                                  is_cache smallint DEFAULT 0,
                                  query text,
                                  remark text,
                                  create_time timestamp without time zone,
                                  update_time timestamp without time zone,
                                  create_by text,
                                  update_by text
);


--
-- Name: TABLE admin_menu; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON TABLE admin.admin_menu IS '菜单权限表（存储前端路由、菜单结构及权限关联）';


--
-- Name: COLUMN admin_menu.id; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.id IS '菜单ID（自增主键）';


--
-- Name: COLUMN admin_menu.parent_id; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.parent_id IS '父菜单ID';


--
-- Name: COLUMN admin_menu.path; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.path IS '前端路由路径（如：/dashboard、/product/list）';


--
-- Name: COLUMN admin_menu.component; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.component IS '前端组件路径（如："#"、"views/Product/List/index"）';


--
-- Name: COLUMN admin_menu.name; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.name IS '路由名称（如：Dashboard、ProductList，唯一）';


--
-- Name: COLUMN admin_menu.redirect; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.redirect IS '路由重定向地址（如：/dashboard/analysis）';


--
-- Name: COLUMN admin_menu.title; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.title IS '菜单显示名称（如："首页"、"商品管理"）';


--
-- Name: COLUMN admin_menu.menu_type; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.menu_type IS '菜单类型（M=目录，C=菜单，F=按钮）';


--
-- Name: COLUMN admin_menu.menu_status; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.menu_status IS '菜单可用状态（0=禁用，1=正常）';


--
-- Name: COLUMN admin_menu.visible; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.visible IS '菜单显示状态（0=隐藏，1=显示）';


--
-- Name: COLUMN admin_menu.meta; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.meta IS '菜单元信息（JSONB类型，存储icon、alwaysShow等扩展属性）';


--
-- Name: COLUMN admin_menu.order_num; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.order_num IS '显示顺序（数字越小越靠前）';


--
-- Name: COLUMN admin_menu.perms; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.perms IS '权限标识（如product:list，前端/后端权限校验用）';


--
-- Name: COLUMN admin_menu.permission_id; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.permission_id IS '关联权限表ID（兼容旧逻辑，优先用perms字段）';


--
-- Name: COLUMN admin_menu.is_frame; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.is_frame IS '是否为外链（0=是，1=否）';


--
-- Name: COLUMN admin_menu.is_cache; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.is_cache IS '是否缓存路由（0=缓存，1=不缓存）';


--
-- Name: COLUMN admin_menu.query; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.query IS '路由参数（如?id=1&name=test）';


--
-- Name: COLUMN admin_menu.remark; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.remark IS '菜单备注/说明';


--
-- Name: COLUMN admin_menu.create_time; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.create_time IS '创建时间（自动填充）';


--
-- Name: COLUMN admin_menu.update_time; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.update_time IS '更新时间（自动填充）';


--
-- Name: COLUMN admin_menu.create_by; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.create_by IS '创建人（用户名/ID）';


--
-- Name: COLUMN admin_menu.update_by; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_menu.update_by IS '更新人（用户名/ID）';


--
-- Name: admin_menu_id_seq; Type: SEQUENCE; Schema: admin; Owner: -
--

CREATE SEQUENCE admin.admin_menu_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: admin_menu_id_seq; Type: SEQUENCE OWNED BY; Schema: admin; Owner: -
--

ALTER SEQUENCE admin.admin_menu_id_seq OWNED BY admin.admin_menu.id;


--
-- Name: admin_permission; Type: TABLE; Schema: admin; Owner: -
--

CREATE TABLE admin.admin_permission (
                                        id integer NOT NULL,
                                        perm_name text NOT NULL,
                                        perm_code text NOT NULL,
                                        perm_type smallint NOT NULL,
                                        url text,
                                        method text,
                                        parent_id integer DEFAULT 0,
                                        sort integer DEFAULT 0,
                                        create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                        update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                        create_by text,
                                        update_by text
);


--
-- Name: TABLE admin_permission; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON TABLE admin.admin_permission IS '权限表（权限最小单元，存储菜单、按钮、接口级权限）';


--
-- Name: COLUMN admin_permission.id; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_permission.id IS '权限ID（自增主键）';


--
-- Name: COLUMN admin_permission.perm_name; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_permission.perm_name IS '权限名称（如：门店编辑、商品查询）';


--
-- Name: COLUMN admin_permission.perm_code; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_permission.perm_code IS '权限编码（唯一，如：STORE_EDIT、PRODUCT_LIST，用于代码权限校验）';


--
-- Name: COLUMN admin_permission.perm_type; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_permission.perm_type IS '权限类型（1=菜单权限，2=按钮权限，3=接口权限）';


--
-- Name: COLUMN admin_permission.url; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_permission.url IS '接口URL（权限类型为3时必填，如：/api/store/update）';


--
-- Name: COLUMN admin_permission.method; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_permission.method IS '请求方法（GET/POST/PUT/DELETE，接口权限配套使用）';


--
-- Name: COLUMN admin_permission.parent_id; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_permission.parent_id IS '父权限ID（0=一级权限，自关联实现菜单层级）';


--
-- Name: COLUMN admin_permission.sort; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_permission.sort IS '排序序号（数字越小越靠前，控制菜单/权限展示顺序）';


--
-- Name: COLUMN admin_permission.create_time; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_permission.create_time IS '创建时间（自动填充）';


--
-- Name: COLUMN admin_permission.update_time; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_permission.update_time IS '更新时间（自动填充）';


--
-- Name: COLUMN admin_permission.create_by; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_permission.create_by IS '创建人（用户名/ID）';


--
-- Name: COLUMN admin_permission.update_by; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_permission.update_by IS '更新人（用户名/ID）';


--
-- Name: admin_permission_id_seq; Type: SEQUENCE; Schema: admin; Owner: -
--

CREATE SEQUENCE admin.admin_permission_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: admin_permission_id_seq; Type: SEQUENCE OWNED BY; Schema: admin; Owner: -
--

ALTER SEQUENCE admin.admin_permission_id_seq OWNED BY admin.admin_permission.id;


--
-- Name: admin_role; Type: TABLE; Schema: admin; Owner: -
--

CREATE TABLE admin.admin_role (
                                  id integer NOT NULL,
                                  role_name text NOT NULL,
                                  role_code text NOT NULL,
                                  description text,
                                  status smallint DEFAULT 1,
                                  create_time timestamp without time zone,
                                  update_time timestamp without time zone,
                                  create_by text,
                                  update_by text
);


--
-- Name: TABLE admin_role; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON TABLE admin.admin_role IS '角色表';


--
-- Name: COLUMN admin_role.id; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_role.id IS '角色ID（自增主键）';


--
-- Name: COLUMN admin_role.role_name; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_role.role_name IS '角色名称（唯一，如：超级管理员、门店管理员）';


--
-- Name: COLUMN admin_role.role_code; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_role.role_code IS '角色编码（唯一，如：SUPER_ADMIN、STORE_ADMIN，用于代码权限判断）';


--
-- Name: COLUMN admin_role.description; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_role.description IS '角色描述（说明角色的权限范围和适用人群）';


--
-- Name: COLUMN admin_role.status; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_role.status IS '角色状态（0=禁用，1=正常）';


--
-- Name: COLUMN admin_role.create_time; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_role.create_time IS '创建时间（自动填充）';


--
-- Name: COLUMN admin_role.update_time; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_role.update_time IS '更新时间（自动填充）';


--
-- Name: COLUMN admin_role.create_by; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_role.create_by IS '创建人（用户名/ID）';


--
-- Name: COLUMN admin_role.update_by; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_role.update_by IS '更新人（用户名/ID）';


--
-- Name: admin_role_id_seq; Type: SEQUENCE; Schema: admin; Owner: -
--

CREATE SEQUENCE admin.admin_role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: admin_role_id_seq; Type: SEQUENCE OWNED BY; Schema: admin; Owner: -
--

ALTER SEQUENCE admin.admin_role_id_seq OWNED BY admin.admin_role.id;


--
-- Name: admin_role_permission; Type: TABLE; Schema: admin; Owner: -
--

CREATE TABLE admin.admin_role_permission (
                                             id integer NOT NULL,
                                             role_id integer NOT NULL,
                                             permission_id integer NOT NULL,
                                             create_time timestamp without time zone,
                                             create_by text
);


--
-- Name: TABLE admin_role_permission; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON TABLE admin.admin_role_permission IS '角色-权限关联表（实现角色与权限的多对多关系）';


--
-- Name: COLUMN admin_role_permission.id; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_role_permission.id IS '关联ID（自增主键）';


--
-- Name: COLUMN admin_role_permission.role_id; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_role_permission.role_id IS '角色ID（关联app.admin_role表的id）';


--
-- Name: COLUMN admin_role_permission.permission_id; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_role_permission.permission_id IS '权限ID（关联app.admin_permission表的id）';


--
-- Name: COLUMN admin_role_permission.create_time; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_role_permission.create_time IS '创建时间（自动填充）';


--
-- Name: COLUMN admin_role_permission.create_by; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_role_permission.create_by IS '创建人（用户名/ID）';


--
-- Name: admin_role_permission_id_seq; Type: SEQUENCE; Schema: admin; Owner: -
--

CREATE SEQUENCE admin.admin_role_permission_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: admin_role_permission_id_seq; Type: SEQUENCE OWNED BY; Schema: admin; Owner: -
--

ALTER SEQUENCE admin.admin_role_permission_id_seq OWNED BY admin.admin_role_permission.id;


--
-- Name: admin_user; Type: TABLE; Schema: admin; Owner: -
--

CREATE TABLE admin.admin_user (
                                  id integer NOT NULL,
                                  username text NOT NULL,
                                  password text NOT NULL,
                                  mobile text,
                                  real_name text,
                                  status smallint DEFAULT 1,
                                  create_time timestamp without time zone,
                                  update_time timestamp without time zone,
                                  create_by text,
                                  update_by text,
                                  gender smallint,
                                  store_id integer,
                                  type smallint NOT NULL
);


--
-- Name: TABLE admin_user; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON TABLE admin.admin_user IS '系统用户表';


--
-- Name: COLUMN admin_user.id; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user.id IS '用户ID（自增主键）';


--
-- Name: COLUMN admin_user.username; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user.username IS '登录用户名（唯一，用于系统登录）';


--
-- Name: COLUMN admin_user.password; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user.password IS '密码（BCrypt加密存储）';


--
-- Name: COLUMN admin_user.mobile; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user.mobile IS '联系电话';


--
-- Name: COLUMN admin_user.real_name; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user.real_name IS '用户真实姓名';


--
-- Name: COLUMN admin_user.status; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user.status IS '用户状态（0=禁用，1=正常）';


--
-- Name: COLUMN admin_user.create_time; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user.create_time IS '创建时间';


--
-- Name: COLUMN admin_user.update_time; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user.update_time IS '更新时间';


--
-- Name: COLUMN admin_user.create_by; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user.create_by IS '创建人';


--
-- Name: COLUMN admin_user.update_by; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user.update_by IS '更新人';


--
-- Name: COLUMN admin_user.gender; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user.gender IS '性别 1男 2女';


--
-- Name: COLUMN admin_user.store_id; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user.store_id IS '门店ID';


--
-- Name: COLUMN admin_user.type; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user.type IS '账号类型 1管理员账户 2 用户账户';


--
-- Name: admin_user_dept; Type: TABLE; Schema: admin; Owner: -
--

CREATE TABLE admin.admin_user_dept (
                                       id integer NOT NULL,
                                       user_id integer NOT NULL,
                                       dept_id integer NOT NULL,
                                       create_time timestamp without time zone,
                                       create_by text
);


--
-- Name: admin_user_dept_id_seq; Type: SEQUENCE; Schema: admin; Owner: -
--

CREATE SEQUENCE admin.admin_user_dept_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: admin_user_dept_id_seq; Type: SEQUENCE OWNED BY; Schema: admin; Owner: -
--

ALTER SEQUENCE admin.admin_user_dept_id_seq OWNED BY admin.admin_user_dept.id;


--
-- Name: admin_user_id_seq; Type: SEQUENCE; Schema: admin; Owner: -
--

CREATE SEQUENCE admin.admin_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: admin_user_id_seq; Type: SEQUENCE OWNED BY; Schema: admin; Owner: -
--

ALTER SEQUENCE admin.admin_user_id_seq OWNED BY admin.admin_user.id;


--
-- Name: admin_user_role; Type: TABLE; Schema: admin; Owner: -
--

CREATE TABLE admin.admin_user_role (
                                       id integer NOT NULL,
                                       user_id integer NOT NULL,
                                       role_id integer NOT NULL,
                                       create_time timestamp without time zone,
                                       create_by text
);


--
-- Name: TABLE admin_user_role; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON TABLE admin.admin_user_role IS '用户-角色关联表（实现用户与角色的多对多关系）';


--
-- Name: COLUMN admin_user_role.id; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user_role.id IS '关联ID（自增主键）';


--
-- Name: COLUMN admin_user_role.user_id; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user_role.user_id IS '用户ID（关联app.admin_user表的id）';


--
-- Name: COLUMN admin_user_role.role_id; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user_role.role_id IS '角色ID（关联app.admin_role表的id）';


--
-- Name: COLUMN admin_user_role.create_time; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user_role.create_time IS '创建时间（自动填充）';


--
-- Name: COLUMN admin_user_role.create_by; Type: COMMENT; Schema: admin; Owner: -
--

COMMENT ON COLUMN admin.admin_user_role.create_by IS '创建人（用户名/ID）';


--
-- Name: admin_user_role_id_seq; Type: SEQUENCE; Schema: admin; Owner: -
--

CREATE SEQUENCE admin.admin_user_role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: admin_user_role_id_seq; Type: SEQUENCE OWNED BY; Schema: admin; Owner: -
--

ALTER SEQUENCE admin.admin_user_role_id_seq OWNED BY admin.admin_user_role.id;


--
-- Name: admin_dict id; Type: DEFAULT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_dict ALTER COLUMN id SET DEFAULT nextval('admin.admin_dict_id_seq'::regclass);


--
-- Name: admin_menu id; Type: DEFAULT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_menu ALTER COLUMN id SET DEFAULT nextval('admin.admin_menu_id_seq'::regclass);


--
-- Name: admin_permission id; Type: DEFAULT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_permission ALTER COLUMN id SET DEFAULT nextval('admin.admin_permission_id_seq'::regclass);


--
-- Name: admin_role id; Type: DEFAULT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_role ALTER COLUMN id SET DEFAULT nextval('admin.admin_role_id_seq'::regclass);


--
-- Name: admin_role_permission id; Type: DEFAULT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_role_permission ALTER COLUMN id SET DEFAULT nextval('admin.admin_role_permission_id_seq'::regclass);


--
-- Name: admin_user id; Type: DEFAULT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_user ALTER COLUMN id SET DEFAULT nextval('admin.admin_user_id_seq'::regclass);


--
-- Name: admin_user_dept id; Type: DEFAULT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_user_dept ALTER COLUMN id SET DEFAULT nextval('admin.admin_user_dept_id_seq'::regclass);


--
-- Name: admin_user_role id; Type: DEFAULT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_user_role ALTER COLUMN id SET DEFAULT nextval('admin.admin_user_role_id_seq'::regclass);


--
-- Data for Name: admin_dict; Type: TABLE DATA; Schema: admin; Owner: -
--

INSERT INTO admin.admin_dict VALUES (1, 'sys_status', '正常', '1', 1, 'success', false, 1, '正常状态', '2026-03-01 11:54:14.799946', '2026-03-01 11:54:14.799946');
INSERT INTO admin.admin_dict VALUES (2, 'sys_status', '停用', '2', 2, 'danger', false, 1, '停用状态', '2026-03-01 11:54:14.799946', '2026-03-01 11:54:14.799946');
INSERT INTO admin.admin_dict VALUES (3, 'sys_yes_no', '是', 'Y', 1, 'primary', false, 1, '是', '2026-03-01 11:54:14.799946', '2026-03-01 11:54:14.799946');
INSERT INTO admin.admin_dict VALUES (4, 'sys_yes_no', '否', 'N', 2, 'info', false, 1, '否', '2026-03-01 11:54:14.799946', '2026-03-01 11:54:14.799946');
INSERT INTO admin.admin_dict VALUES (5, 'sys_user_sex', '男', '1', 1, 'primary', false, 1, '男性', '2026-03-01 11:54:14.799946', '2026-03-01 11:54:14.799946');
INSERT INTO admin.admin_dict VALUES (6, 'sys_user_sex', '女', '2', 2, 'danger', false, 1, '女性', '2026-03-01 11:54:14.799946', '2026-03-01 11:54:14.799946');
INSERT INTO admin.admin_dict VALUES (7, 'sys_user_sex', '未知', '0', 3, 'info', false, 1, '未知', '2026-03-01 11:54:14.799946', '2026-03-01 11:54:14.799946');
INSERT INTO admin.admin_dict VALUES (8, 'user_status', '正常', '0', 1, 'success', false, 1, '正常', '2026-03-01 11:54:14.802056', '2026-03-01 11:54:14.802056');
INSERT INTO admin.admin_dict VALUES (9, 'user_status', '禁用', '1', 2, 'danger', false, 1, '禁用', '2026-03-01 11:54:14.802056', '2026-03-01 11:54:14.802056');
INSERT INTO admin.admin_dict VALUES (10, 'user_status', '已注销', '2', 3, 'info', false, 1, '已注销', '2026-03-01 11:54:14.802056', '2026-03-01 11:54:14.802056');
INSERT INTO admin.admin_dict VALUES (11, 'store_status', '营业中', '1', 1, 'success', false, 1, '正常营业', '2026-03-01 11:54:14.802482', '2026-03-01 11:54:14.802482');
INSERT INTO admin.admin_dict VALUES (12, 'store_status', '休息中', '0', 2, 'warning', false, 1, '暂停营业', '2026-03-01 11:54:14.802482', '2026-03-01 11:54:14.802482');
INSERT INTO admin.admin_dict VALUES (13, 'product_status', '上架', '1', 1, 'success', false, 1, '正常销售', '2026-03-01 11:54:14.802797', '2026-03-01 11:54:14.802797');
INSERT INTO admin.admin_dict VALUES (14, 'product_status', '下架', '2', 2, 'info', false, 1, '已下架', '2026-03-01 11:54:14.802797', '2026-03-01 11:54:14.802797');
INSERT INTO admin.admin_dict VALUES (15, 'product_status', '售罄', '3', 3, 'warning', false, 1, '已售罄', '2026-03-01 11:54:14.802797', '2026-03-01 11:54:14.802797');
INSERT INTO admin.admin_dict VALUES (16, 'category_status', '启用', '1', 1, 'success', false, 1, '启用', '2026-03-01 11:54:14.802797', '2026-03-01 11:54:14.802797');
INSERT INTO admin.admin_dict VALUES (17, 'category_status', '禁用', '2', 2, 'danger', false, 1, '禁用', '2026-03-01 11:54:14.802797', '2026-03-01 11:54:14.802797');
INSERT INTO admin.admin_dict VALUES (18, 'order_type', '堂食', '1', 1, 'primary', false, 1, '扫码堂食', '2026-03-01 11:54:14.803241', '2026-03-01 11:54:14.803241');
INSERT INTO admin.admin_dict VALUES (19, 'order_type', '自提', '2', 2, 'success', false, 1, '到店自提', '2026-03-01 11:54:14.803241', '2026-03-01 11:54:14.803241');
INSERT INTO admin.admin_dict VALUES (20, 'order_type', '外卖', '3', 3, 'warning', false, 1, '外卖配送', '2026-03-01 11:54:14.803241', '2026-03-01 11:54:14.803241');
INSERT INTO admin.admin_dict VALUES (21, 'order_status', '待支付', '0', 1, 'info', false, 1, '等待支付', '2026-03-01 11:54:14.803241', '2026-03-01 11:54:14.803241');
INSERT INTO admin.admin_dict VALUES (22, 'order_status', '待接单', '1', 2, 'primary', false, 1, '已支付待接单', '2026-03-01 11:54:14.803241', '2026-03-01 11:54:14.803241');
INSERT INTO admin.admin_dict VALUES (23, 'order_status', '制作中', '2', 3, 'warning', false, 1, '制作中', '2026-03-01 11:54:14.803241', '2026-03-01 11:54:14.803241');
INSERT INTO admin.admin_dict VALUES (24, 'order_status', '待配送', '3', 4, 'info', false, 1, '待配送', '2026-03-01 11:54:14.803241', '2026-03-01 11:54:14.803241');
INSERT INTO admin.admin_dict VALUES (25, 'order_status', '已完成', '4', 5, 'success', false, 1, '已完成', '2026-03-01 11:54:14.803241', '2026-03-01 11:54:14.803241');
INSERT INTO admin.admin_dict VALUES (26, 'order_status', '已取消', '5', 6, 'danger', false, 1, '已取消', '2026-03-01 11:54:14.803241', '2026-03-01 11:54:14.803241');
INSERT INTO admin.admin_dict VALUES (27, 'pay_type', '微信', '1', 1, 'success', false, 1, '微信支付', '2026-03-01 11:54:14.803241', '2026-03-01 11:54:14.803241');
INSERT INTO admin.admin_dict VALUES (28, 'pay_type', '支付宝', '2', 2, 'primary', false, 1, '支付宝', '2026-03-01 11:54:14.803241', '2026-03-01 11:54:14.803241');
INSERT INTO admin.admin_dict VALUES (29, 'pay_type', '现金', '3', 3, 'warning', false, 1, '现金', '2026-03-01 11:54:14.803241', '2026-03-01 11:54:14.803241');
INSERT INTO admin.admin_dict VALUES (30, 'pay_status', '未支付', '0', 1, 'info', false, 1, '未支付', '2026-03-01 11:54:14.803241', '2026-03-01 11:54:14.803241');
INSERT INTO admin.admin_dict VALUES (31, 'pay_status', '已支付', '1', 2, 'success', false, 1, '已支付', '2026-03-01 11:54:14.803241', '2026-03-01 11:54:14.803241');
INSERT INTO admin.admin_dict VALUES (32, 'pay_status', '退款中', '2', 3, 'warning', false, 1, '退款中', '2026-03-01 11:54:14.803241', '2026-03-01 11:54:14.803241');
INSERT INTO admin.admin_dict VALUES (33, 'pay_status', '已退款', '3', 4, 'danger', false, 1, '已退款', '2026-03-01 11:54:14.803241', '2026-03-01 11:54:14.803241');
INSERT INTO admin.admin_dict VALUES (34, 'table_status', '可用', '0', 1, 'success', false, 1, '空闲', '2026-03-01 11:54:14.803975', '2026-03-01 11:54:14.803975');
INSERT INTO admin.admin_dict VALUES (35, 'table_status', '使用中', '1', 2, 'primary', false, 1, '使用中', '2026-03-01 11:54:14.803975', '2026-03-01 11:54:14.803975');
INSERT INTO admin.admin_dict VALUES (36, 'table_status', '已预订', '2', 3, 'warning', false, 1, '已预订', '2026-03-01 11:54:14.803975', '2026-03-01 11:54:14.803975');
INSERT INTO admin.admin_dict VALUES (37, 'table_status', '维护中', '3', 4, 'danger', false, 1, '维护中', '2026-03-01 11:54:14.803975', '2026-03-01 11:54:14.803975');
INSERT INTO admin.admin_dict VALUES (38, 'product_tag', '上新', '1', 0, 'primary', false, 1, '', '2026-03-05 11:09:21.922103', '2026-03-05 11:09:21.923744');
INSERT INTO admin.admin_dict VALUES (39, 'product_tag', '时令', '2', 0, 'primary', false, 1, '', '2026-03-05 11:09:36.42297', '2026-03-05 11:09:36.423112');


--
-- Data for Name: admin_menu; Type: TABLE DATA; Schema: admin; Owner: -
--

INSERT INTO admin.admin_menu VALUES (3, 1, 'role', '/system/role', 'role', NULL, 'menus.system.role', 'C', 1, 1, '{"roles": ["R_SUPER"], "title": "menus.system.role", "keepAlive": true}', 3, 'system:role:view', NULL, 1, 1, NULL, '角色管理菜单', '2026-01-20 21:54:36.040766', '2026-01-20 21:54:36.040766', 'admin', 'admin');
INSERT INTO admin.admin_menu VALUES (5, 1, 'menu', '/system/menu', 'menu', NULL, 'menus.system.menu', 'C', 1, 1, '{"roles": ["R_SUPER"], "title": "menus.system.menu", "keepAlive": true}', 5, 'system:menu:view', NULL, 1, 1, NULL, '菜单管理菜单（含按钮权限标记）', '2026-01-20 21:54:36.041331', '2026-01-20 21:54:36.041331', 'admin', 'admin');
INSERT INTO admin.admin_menu VALUES (15, 11, 'category', '/product/category', 'category', '', 'menus.product.category', 'C', 1, 1, '{"title": "menus.product.category", "isHide": false, "isHideTab": false, "keepAlive": false}', 5, 'product:category:view', NULL, 0, 0, '', '', '2026-02-11 12:26:25.605793', '2026-03-01 19:05:17.602471', NULL, NULL);
INSERT INTO admin.admin_menu VALUES (20, 11, 'attribute', '/product/attribute', 'Attribute', '', 'menus.product.attribute', 'C', 1, 1, '{"title": "menus.product.attribute", "isHide": false, "isHideTab": false, "keepAlive": false}', 7, 'product:attribute:view', NULL, 0, 0, '', '', '2026-03-01 19:00:33.413204', '2026-03-02 14:09:01.007877', NULL, NULL);
INSERT INTO admin.admin_menu VALUES (2, 1, 'user', '/system/user', 'user', NULL, 'menus.system.user', 'C', 1, 1, '{"roles": ["R_SUPER", "R_ADMIN"], "title": "menus.system.user", "keepAlive": true}', 2, 'system:user:view', NULL, 1, 1, NULL, '用户管理菜单', '2026-01-20 21:54:36.040189', '2026-01-20 21:54:36.040189', 'admin', 'admin');
INSERT INTO admin.admin_menu VALUES (1, 0, 'system', '/index/index', 'system', NULL, 'menus.system.title', 'M', 1, 1, '{"icon": "ri:user-3-line", "roles": ["R_SUPER", "R_ADMIN"], "title": "menus.system.title"}', 1, 'system:index:view', NULL, 1, 0, NULL, '系统管理顶级目录', '2026-01-20 21:54:36.034965', '2026-01-20 21:54:36.034965', 'admin', 'admin');
INSERT INTO admin.admin_menu VALUES (6, 5, NULL, NULL, 'menu-add', NULL, 'buttons.add', 'F', 1, 1, NULL, NULL, 'system:menu:add', NULL, 1, 0, NULL, NULL, '2026-01-27 08:41:47', '2026-01-27 08:41:52', 'admin', 'admin');
INSERT INTO admin.admin_menu VALUES (7, 1, 'permission', '/system/permission', 'permission', NULL, 'menus.system.permission', 'C', 1, 1, '{"title": "menus.system.permission", "isHide": false, "isHideTab": false, "keepAlive": true}', NULL, 'system:permission:view', NULL, 1, 0, NULL, '权限管理菜单', '2026-01-31 15:18:29', '2026-01-31 15:18:32', 'admin', 'admin');
INSERT INTO admin.admin_menu VALUES (21, 11, 'banner', '/product/banner', 'Banner', '', 'menus.product.banner', 'C', 1, 1, '{"title": "menus.product.banner", "isHide": false, "isHideTab": false, "keepAlive": false}', 4, 'product:banner:view', NULL, 0, 0, '', '', '2026-03-10 16:34:56.453918', '2026-03-10 17:55:51.024836', NULL, NULL);
INSERT INTO admin.admin_menu VALUES (8, 5, '', '', '', '', 'buttons.delete', 'F', 1, 1, '{}', 1, 'system:menu:delete', NULL, 0, 0, '', '测试按钮新增', '2026-01-31 16:26:59.262422', '2026-01-31 16:26:59.264379', NULL, NULL);
INSERT INTO admin.admin_menu VALUES (10, 1, 'dept', '/system/dept', 'dept', '', 'menus.system.dept', 'C', 1, 1, '{"title": "menus.system.dept", "isHide": false, "isHideTab": false, "keepAlive": true}', 1, 'system:dept:view', NULL, 0, 0, '', '', '2026-02-02 18:51:02.119958', '2026-02-02 18:59:04.305645', NULL, NULL);
INSERT INTO admin.admin_menu VALUES (9, 2, '', '', '', '', 'buttons.delete', 'F', 1, 1, '{}', 1, 'system:user:delete', NULL, 0, 0, '', '测试按钮新增', '2026-01-31 16:27:45.045413', '2026-01-31 16:27:45.045467', NULL, NULL);
INSERT INTO admin.admin_menu VALUES (11, 0, 'product', '/index/index', 'product', '', 'menus.product.title', 'M', 1, 1, '{"title": "menus.product.title", "isHide": false, "isHideTab": false, "keepAlive": false}', 2, 'product:index:view', NULL, 0, 0, '', '', '2026-02-06 09:37:51.212005', '2026-02-28 16:38:21.162697', NULL, NULL);
INSERT INTO admin.admin_menu VALUES (16, 0, 'order', '/index/index', 'order', '', 'menus.order.title', 'C', 1, 1, '{"title": "menus.order.title", "isHide": false, "isHideTab": false, "keepAlive": false}', 3, 'order:index:view', NULL, 0, 0, '', '', '2026-02-28 15:51:31.705488', '2026-02-28 16:38:31.53717', NULL, NULL);
INSERT INTO admin.admin_menu VALUES (14, 11, 'qrcode', '/product/qrcode', 'qrcode', '', 'menus.product.qrcode', 'C', 1, 1, '{"title": "menus.product.qrcode", "isHide": false, "isHideTab": false, "keepAlive": false}', 2, 'product:qr:view', NULL, 0, 0, '', '', '2026-02-06 10:17:41.725234', '2026-02-28 16:49:02.143471', NULL, NULL);
INSERT INTO admin.admin_menu VALUES (13, 11, 'store', '/product/store', 'store', '', 'menus.product.store', 'C', 1, 1, '{"title": "menus.product.store", "isHide": false, "isHideTab": false, "keepAlive": false}', 1, 'product:store:view', NULL, 0, 0, '', '', '2026-02-06 10:11:13.33409', '2026-02-06 18:44:27.982626', NULL, NULL);
INSERT INTO admin.admin_menu VALUES (12, 11, 'goods', '/product/goods', '', '', 'menus.product.goods', 'C', 1, 1, '{"title": "menus.product.goods", "isHide": false, "isHideTab": false, "keepAlive": false}', 3, 'product:goods:view', NULL, 0, 0, '', '', '2026-02-06 09:57:27.888071', '2026-02-28 16:49:18.82555', NULL, NULL);
INSERT INTO admin.admin_menu VALUES (18, 16, 'detail', '/order/detail', '', '', '订单明细管理', 'C', 1, 1, '{"title": "订单明细管理", "isHide": false, "isHideTab": false, "keepAlive": false}', 2, 'order:detail:view', NULL, 0, 0, '', '', '2026-02-28 16:08:55.806704', '2026-02-28 16:52:07.747081', NULL, NULL);
INSERT INTO admin.admin_menu VALUES (17, 16, 'main', '/order/main', '', '', '订单管理', 'C', 1, 1, '{"title": "订单管理", "isHide": false, "isHideTab": false, "keepAlive": false}', 1, 'order:main:view', NULL, 0, 0, '', '', '2026-02-28 16:08:21.30263', '2026-02-28 16:08:21.302896', NULL, NULL);
INSERT INTO admin.admin_menu VALUES (19, 1, 'dict', '/system/dict', 'dict', '', '字典管理', 'C', 1, 1, '{"title": "字典管理", "isHide": false, "isHideTab": false, "keepAlive": false}', 1, 'system:dict:view', NULL, 0, 0, '', '', '2026-03-01 12:25:47.828088', '2026-03-01 12:26:45.279239', NULL, NULL);


--
-- Data for Name: admin_permission; Type: TABLE DATA; Schema: admin; Owner: -
--

INSERT INTO admin.admin_permission VALUES (1, '系统管理', 'system:index:view', 1, '', NULL, 0, NULL, '2026-01-16 14:35:36.873286', '2026-01-16 14:35:36.873286', 'admin', 'admin');
INSERT INTO admin.admin_permission VALUES (11, '菜单新增', 'system:menu:add', 1, NULL, NULL, 10, NULL, '2026-01-27 09:18:52', '2026-01-27 09:18:55', 'admin', 'admin');
INSERT INTO admin.admin_permission VALUES (12, '角色管理', 'system:role:view', 1, NULL, NULL, 1, 0, '2026-01-31 15:53:40.363145', '2026-01-31 15:53:40.363145', NULL, NULL);
INSERT INTO admin.admin_permission VALUES (13, '权限管理', 'system:permission:view', 1, NULL, NULL, 1, 0, '2026-01-31 15:54:04.709892', '2026-01-31 15:54:04.709892', NULL, NULL);
INSERT INTO admin.admin_permission VALUES (10, '菜单管理', 'system:menu:view', 1, '', NULL, 1, NULL, '2026-01-21 00:40:46.379341', '2026-01-21 00:40:46.379341', NULL, NULL);
INSERT INTO admin.admin_permission VALUES (14, '菜单删除', 'system:menu:delete', 1, NULL, NULL, 10, 0, '2026-01-31 16:26:14.168753', '2026-01-31 16:26:14.168753', NULL, NULL);
INSERT INTO admin.admin_permission VALUES (9, '商品上下架', 'product:goods:status', 2, '', '', 7, NULL, '2026-01-16 14:35:36.878566', '2026-01-16 14:35:36.878566', 'admin', 'admin');
INSERT INTO admin.admin_permission VALUES (3, '用户列表查询', 'system:user:query', 2, '', '', 2, NULL, '2026-01-16 14:35:36.876674', '2026-01-16 14:35:36.876674', 'admin', 'admin');
INSERT INTO admin.admin_permission VALUES (4, '用户新增', 'system:user:add', 2, '', '', 2, NULL, '2026-01-16 14:35:36.877069', '2026-01-16 14:35:36.877069', 'admin', 'admin');
INSERT INTO admin.admin_permission VALUES (5, '用户删除', 'system:user:delete', 2, '', '', 2, NULL, '2026-01-16 14:35:36.877413', '2026-01-16 14:35:36.877413', 'admin', 'admin');
INSERT INTO admin.admin_permission VALUES (2, '用户管理', 'system:user:view', 1, '', NULL, 1, NULL, '2026-01-16 14:35:36.876121', '2026-01-16 14:35:36.876121', 'admin', 'admin');
INSERT INTO admin.admin_permission VALUES (7, '商品管理', 'product:goods:view', 1, '', NULL, 6, NULL, '2026-01-16 14:35:36.878002', '2026-01-16 14:35:36.878002', 'admin', 'admin');
INSERT INTO admin.admin_permission VALUES (15, '商品新增', 'product:goods:add', 2, '', '', 6, 0, '2026-02-02 14:29:29.248058', '2026-02-02 14:29:29.248171', NULL, NULL);
INSERT INTO admin.admin_permission VALUES (17, '部门管理', 'system:dept:view', 1, '', '', 1, 0, '2026-02-02 18:42:23.469277', '2026-02-02 18:42:23.470193', NULL, NULL);
INSERT INTO admin.admin_permission VALUES (18, '部门管理-新增', 'system:dept:add', 1, '', '', 17, 0, '2026-02-02 22:28:11.312093', '2026-02-02 22:28:11.312973', NULL, NULL);
INSERT INTO admin.admin_permission VALUES (6, '产品管理', 'product:index:view', 1, '', '', 0, 0, '2026-01-16 14:35:36.877714', '2026-02-03 00:57:53.455413', 'admin', 'admin');
INSERT INTO admin.admin_permission VALUES (19, '桌码管理', 'product:qr:view', 1, '', '', 6, 0, '2026-02-03 01:00:39.780204', '2026-02-03 01:00:39.780392', NULL, NULL);
INSERT INTO admin.admin_permission VALUES (20, '门店管理', 'product:store:view', 1, '', '', 6, 0, '2026-02-03 01:01:04.758629', '2026-02-03 01:01:04.758671', NULL, NULL);
INSERT INTO admin.admin_permission VALUES (23, '商品分类管理', 'product:category:view', 1, '', '', 6, 0, '2026-02-11 12:25:07.503696', '2026-02-11 12:25:07.503726', NULL, NULL);
INSERT INTO admin.admin_permission VALUES (24, '订单管理', 'order:main:view', 1, '', '', 21, 0, '2026-02-28 15:49:33.316088', '2026-02-28 15:49:33.316155', NULL, NULL);
INSERT INTO admin.admin_permission VALUES (25, '订单明细管理', 'order:detail:view', 1, '', '', 21, 0, '2026-02-28 15:50:08.68099', '2026-02-28 15:50:08.681038', NULL, NULL);
INSERT INTO admin.admin_permission VALUES (21, '订单管理(目录)', 'order:index:view', 1, '', '', 0, 0, '2026-02-06 10:11:45.410119', '2026-02-28 15:50:17.200811', NULL, NULL);
INSERT INTO admin.admin_permission VALUES (26, '字典管理', 'system:dict:view', 1, '', '', 1, 0, '2026-03-01 12:26:22.793056', '2026-03-01 12:26:22.793119', NULL, NULL);
INSERT INTO admin.admin_permission VALUES (27, '商品属性(规格)管理', 'product:attribute:view', 1, '', '', 6, 0, '2026-03-01 19:03:45.307677', '2026-03-01 19:03:45.307828', NULL, NULL);
INSERT INTO admin.admin_permission VALUES (28, '轮播图管理', 'product:banner:view', 1, '', '', 6, 4, '2026-03-10 16:31:24.094919', '2026-03-10 16:31:24.095859', NULL, NULL);


--
-- Data for Name: admin_role; Type: TABLE DATA; Schema: admin; Owner: -
--

INSERT INTO admin.admin_role VALUES (3, '系统管理员', 'R_SYS_ADMIN', '负责系统管理目录', 1, '2026-01-16 13:59:31.442467', '2026-01-16 13:59:31.442467', 'admin', 'admin');
INSERT INTO admin.admin_role VALUES (1, '超级管理员', 'R_SUPER_ADMIN', '系统最高权限，可操作所有功能', 1, '2026-01-16 13:59:31.43832', '2026-03-01 17:57:34.819498', 'admin', 'admin');
INSERT INTO admin.admin_role VALUES (4, '普通用户', 'R_USER', '仅可查看授权的菜单和数据，无任何管理权限', 1, '2026-01-16 13:59:31.442784', '2026-03-01 17:57:51.234336', 'admin', 'admin');
INSERT INTO admin.admin_role VALUES (9, '会员用户', 'R_USER_VIP', '', 1, '2026-03-01 17:58:12.705347', '2026-03-01 17:58:12.705443', NULL, NULL);


--
-- Data for Name: admin_role_permission; Type: TABLE DATA; Schema: admin; Owner: -
--

INSERT INTO admin.admin_role_permission VALUES (415, 1, 1, '2026-03-01 19:04:26.995662', NULL);
INSERT INTO admin.admin_role_permission VALUES (416, 1, 11, '2026-03-01 19:04:26.99613', NULL);
INSERT INTO admin.admin_role_permission VALUES (417, 1, 12, '2026-03-01 19:04:26.996306', NULL);
INSERT INTO admin.admin_role_permission VALUES (418, 1, 13, '2026-03-01 19:04:26.996427', NULL);
INSERT INTO admin.admin_role_permission VALUES (419, 1, 10, '2026-03-01 19:04:26.996571', NULL);
INSERT INTO admin.admin_role_permission VALUES (420, 1, 14, '2026-03-01 19:04:26.99671', NULL);
INSERT INTO admin.admin_role_permission VALUES (421, 1, 5, '2026-03-01 19:04:26.996875', NULL);
INSERT INTO admin.admin_role_permission VALUES (422, 1, 2, '2026-03-01 19:04:26.997039', NULL);
INSERT INTO admin.admin_role_permission VALUES (423, 1, 7, '2026-03-01 19:04:26.997226', NULL);
INSERT INTO admin.admin_role_permission VALUES (424, 1, 17, '2026-03-01 19:04:26.99736', NULL);
INSERT INTO admin.admin_role_permission VALUES (425, 1, 6, '2026-03-01 19:04:26.997475', NULL);
INSERT INTO admin.admin_role_permission VALUES (426, 1, 19, '2026-03-01 19:04:26.997625', NULL);
INSERT INTO admin.admin_role_permission VALUES (427, 1, 20, '2026-03-01 19:04:26.997738', NULL);
INSERT INTO admin.admin_role_permission VALUES (428, 1, 23, '2026-03-01 19:04:26.99785', NULL);
INSERT INTO admin.admin_role_permission VALUES (429, 1, 24, '2026-03-01 19:04:26.997962', NULL);
INSERT INTO admin.admin_role_permission VALUES (430, 1, 25, '2026-03-01 19:04:26.998073', NULL);
INSERT INTO admin.admin_role_permission VALUES (431, 1, 21, '2026-03-01 19:04:26.998186', NULL);
INSERT INTO admin.admin_role_permission VALUES (432, 1, 26, '2026-03-01 19:04:26.998289', NULL);
INSERT INTO admin.admin_role_permission VALUES (433, 1, 27, '2026-03-01 19:04:26.998395', NULL);
INSERT INTO admin.admin_role_permission VALUES (434, 3, 1, '2026-03-10 16:35:11.15546', NULL);
INSERT INTO admin.admin_role_permission VALUES (435, 3, 11, '2026-03-10 16:35:11.157156', NULL);
INSERT INTO admin.admin_role_permission VALUES (436, 3, 12, '2026-03-10 16:35:11.15736', NULL);
INSERT INTO admin.admin_role_permission VALUES (437, 3, 13, '2026-03-10 16:35:11.157611', NULL);
INSERT INTO admin.admin_role_permission VALUES (438, 3, 10, '2026-03-10 16:35:11.157781', NULL);
INSERT INTO admin.admin_role_permission VALUES (439, 3, 14, '2026-03-10 16:35:11.158022', NULL);
INSERT INTO admin.admin_role_permission VALUES (440, 3, 5, '2026-03-10 16:35:11.158172', NULL);
INSERT INTO admin.admin_role_permission VALUES (441, 3, 2, '2026-03-10 16:35:11.158311', NULL);
INSERT INTO admin.admin_role_permission VALUES (442, 3, 7, '2026-03-10 16:35:11.158445', NULL);
INSERT INTO admin.admin_role_permission VALUES (443, 3, 17, '2026-03-10 16:35:11.158589', NULL);
INSERT INTO admin.admin_role_permission VALUES (444, 3, 6, '2026-03-10 16:35:11.15878', NULL);
INSERT INTO admin.admin_role_permission VALUES (445, 3, 19, '2026-03-10 16:35:11.159077', NULL);
INSERT INTO admin.admin_role_permission VALUES (446, 3, 20, '2026-03-10 16:35:11.159226', NULL);
INSERT INTO admin.admin_role_permission VALUES (447, 3, 23, '2026-03-10 16:35:11.159371', NULL);
INSERT INTO admin.admin_role_permission VALUES (448, 3, 24, '2026-03-10 16:35:11.159529', NULL);
INSERT INTO admin.admin_role_permission VALUES (449, 3, 25, '2026-03-10 16:35:11.159669', NULL);
INSERT INTO admin.admin_role_permission VALUES (450, 3, 21, '2026-03-10 16:35:11.159806', NULL);
INSERT INTO admin.admin_role_permission VALUES (451, 3, 26, '2026-03-10 16:35:11.159943', NULL);
INSERT INTO admin.admin_role_permission VALUES (452, 3, 27, '2026-03-10 16:35:11.160099', NULL);
INSERT INTO admin.admin_role_permission VALUES (453, 3, 28, '2026-03-10 16:35:11.160233', NULL);
INSERT INTO admin.admin_role_permission VALUES (389, 4, 7, '2026-03-01 18:24:47.07227', NULL);
INSERT INTO admin.admin_role_permission VALUES (390, 4, 6, '2026-03-01 18:24:47.078818', NULL);
INSERT INTO admin.admin_role_permission VALUES (391, 4, 19, '2026-03-01 18:24:47.079055', NULL);
INSERT INTO admin.admin_role_permission VALUES (392, 4, 23, '2026-03-01 18:24:47.079139', NULL);
INSERT INTO admin.admin_role_permission VALUES (393, 4, 24, '2026-03-01 18:24:47.079199', NULL);
INSERT INTO admin.admin_role_permission VALUES (394, 4, 25, '2026-03-01 18:24:47.079282', NULL);
INSERT INTO admin.admin_role_permission VALUES (395, 4, 21, '2026-03-01 18:24:47.079356', NULL);


--
-- Data for Name: admin_user; Type: TABLE DATA; Schema: admin; Owner: -
--

INSERT INTO admin.admin_user VALUES (2, 'sweet', '$2a$10$vr23AIKeqd2gj.bOHY89ZuKLapJGacH/hxE26.8nzmZcZqCjNELIu', '13900139000', '甜心派对', 1, '2026-01-16 13:55:49.023398', '2026-01-16 13:55:49.023398', 'admin', 'admin', 1, 2, 2);
INSERT INTO admin.admin_user VALUES (3, 'admin', '$2a$10$vr23AIKeqd2gj.bOHY89ZuKLapJGacH/hxE26.8nzmZcZqCjNELIu', '15839864467', 'admin', 1, '2026-02-02 23:19:32.445395', '2026-02-02 23:19:32.456134', 'admin', 'admin', 1, NULL, 1);
INSERT INTO admin.admin_user VALUES (1, 'root', '$2a$10$vr23AIKeqd2gj.bOHY89ZuKLapJGacH/hxE26.8nzmZcZqCjNELIu', '13800138000', 'root', 1, '2026-01-16 13:55:49.021192', '2026-01-16 13:55:49.021192', 'admin', 'admin', 1, NULL, 1);


--
-- Data for Name: admin_user_dept; Type: TABLE DATA; Schema: admin; Owner: -
--

INSERT INTO admin.admin_user_dept VALUES (1, 1, 2, '2026-01-16 16:31:31.683097', 'admin');
INSERT INTO admin.admin_user_dept VALUES (2, 2, 6, '2026-01-16 16:31:31.683097', 'admin');


--
-- Data for Name: admin_user_role; Type: TABLE DATA; Schema: admin; Owner: -
--

INSERT INTO admin.admin_user_role VALUES (3, 2, 4, '2026-01-16 17:03:52.557714', 'admin');
INSERT INTO admin.admin_user_role VALUES (15, 1, 1, '2026-03-01 17:58:23.572535', NULL);
INSERT INTO admin.admin_user_role VALUES (16, 3, 3, '2026-03-01 17:58:35.551469', NULL);


--
-- Name: admin_dict_id_seq; Type: SEQUENCE SET; Schema: admin; Owner: -
--

SELECT pg_catalog.setval('admin.admin_dict_id_seq', 39, true);


--
-- Name: admin_menu_id_seq; Type: SEQUENCE SET; Schema: admin; Owner: -
--

SELECT pg_catalog.setval('admin.admin_menu_id_seq', 21, true);


--
-- Name: admin_permission_id_seq; Type: SEQUENCE SET; Schema: admin; Owner: -
--

SELECT pg_catalog.setval('admin.admin_permission_id_seq', 28, true);


--
-- Name: admin_role_id_seq; Type: SEQUENCE SET; Schema: admin; Owner: -
--

SELECT pg_catalog.setval('admin.admin_role_id_seq', 9, true);


--
-- Name: admin_role_permission_id_seq; Type: SEQUENCE SET; Schema: admin; Owner: -
--

SELECT pg_catalog.setval('admin.admin_role_permission_id_seq', 453, true);


--
-- Name: admin_user_dept_id_seq; Type: SEQUENCE SET; Schema: admin; Owner: -
--

SELECT pg_catalog.setval('admin.admin_user_dept_id_seq', 2, true);


--
-- Name: admin_user_id_seq; Type: SEQUENCE SET; Schema: admin; Owner: -
--

SELECT pg_catalog.setval('admin.admin_user_id_seq', 4, true);


--
-- Name: admin_user_role_id_seq; Type: SEQUENCE SET; Schema: admin; Owner: -
--

SELECT pg_catalog.setval('admin.admin_user_role_id_seq', 16, true);


--
-- Name: admin_dict admin_dict_pkey; Type: CONSTRAINT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_dict
    ADD CONSTRAINT admin_dict_pkey PRIMARY KEY (id);


--
-- Name: admin_menu admin_menu_pkey; Type: CONSTRAINT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_menu
    ADD CONSTRAINT admin_menu_pkey PRIMARY KEY (id);


--
-- Name: admin_permission admin_permission_pkey; Type: CONSTRAINT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_permission
    ADD CONSTRAINT admin_permission_pkey PRIMARY KEY (id);


--
-- Name: admin_role_permission admin_role_permission_pkey; Type: CONSTRAINT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_role_permission
    ADD CONSTRAINT admin_role_permission_pkey PRIMARY KEY (id);


--
-- Name: admin_role_permission admin_role_permission_role_id_permission_id_key; Type: CONSTRAINT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_role_permission
    ADD CONSTRAINT admin_role_permission_role_id_permission_id_key UNIQUE (role_id, permission_id);


--
-- Name: admin_role admin_role_pkey; Type: CONSTRAINT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_role
    ADD CONSTRAINT admin_role_pkey PRIMARY KEY (id);


--
-- Name: admin_user_dept admin_user_dept_pkey; Type: CONSTRAINT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_user_dept
    ADD CONSTRAINT admin_user_dept_pkey PRIMARY KEY (id);


--
-- Name: admin_user admin_user_pkey; Type: CONSTRAINT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_user
    ADD CONSTRAINT admin_user_pkey PRIMARY KEY (id);


--
-- Name: admin_user_role admin_user_role_pkey; Type: CONSTRAINT; Schema: admin; Owner: -
--

ALTER TABLE ONLY admin.admin_user_role
    ADD CONSTRAINT admin_user_role_pkey PRIMARY KEY (id);


--
-- Name: idx_dict_status; Type: INDEX; Schema: admin; Owner: -
--

CREATE INDEX idx_dict_status ON admin.admin_dict USING btree (status);


--
-- Name: idx_dict_type; Type: INDEX; Schema: admin; Owner: -
--

CREATE INDEX idx_dict_type ON admin.admin_dict USING btree (dict_type);


--
-- Name: idx_dict_value; Type: INDEX; Schema: admin; Owner: -
--

CREATE INDEX idx_dict_value ON admin.admin_dict USING btree (dict_value);


--
-- Name: uk_admin_perm_code; Type: INDEX; Schema: admin; Owner: -
--

CREATE UNIQUE INDEX uk_admin_perm_code ON admin.admin_permission USING btree (perm_code);


--
-- Name: uk_admin_role_role_code; Type: INDEX; Schema: admin; Owner: -
--

CREATE UNIQUE INDEX uk_admin_role_role_code ON admin.admin_role USING btree (role_code);


--
-- Name: uk_admin_role_role_name; Type: INDEX; Schema: admin; Owner: -
--

CREATE UNIQUE INDEX uk_admin_role_role_name ON admin.admin_role USING btree (role_name);


--
-- Name: uk_admin_user_username; Type: INDEX; Schema: admin; Owner: -
--

CREATE UNIQUE INDEX uk_admin_user_username ON admin.admin_user USING btree (username);




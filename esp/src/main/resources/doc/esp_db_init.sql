-- 创建并使用 esp_db 数据库
drop database if exists esp_db;
create database esp_db character set 'utf8mb4';
use esp_db;

-- 角色信息表：sys_role
drop table if exists sys_role;
create table sys_role(
    id int (10) not null auto_increment comment '主键',
    name varchar (100) not null comment '名称',
    status int (2) not null default 1 comment '状态：0-禁用，1-启用',
    create_time timestamp not null default current_timestamp comment '创建时间',
    update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key(id)
)Engine=InnoDB charset='utf8mb4' comment='角色信息表';
insert into sys_role(name) values('超级管理员');
insert into sys_role(name) values('部门助理');
insert into sys_role(name) values('采购经理');
insert into sys_role(name) values('采购员');
insert into sys_role(name) values('库管员');

-- 菜单功能表：sys_menu
drop table if exists sys_menu;
create table sys_menu(
    id int (10) not null auto_increment comment '主键',
    parent_id int (10) not null comment '上级菜单',
    text varchar(100) not null comment '菜单文本',
    url varchar(100) not null comment '链接地址',
    icon varchar(100) not null comment '菜单样式',
    status int (2) not null default 1 comment '状态：0-禁用，1-启用',
    create_time timestamp not null default current_timestamp comment '创建时间',
    update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key(id)
)Engine=InnoDB charset='utf8mb4' comment='菜单功能表';
insert into sys_menu(parent_id, text, url, icon) values(0, '系统管理', '', '');
insert into sys_menu(parent_id, text, url, icon) values(0, '采购管理', '', '');
insert into sys_menu(parent_id, text, url, icon) values(0, '历史信息', '', '');
insert into sys_menu(parent_id, text, url, icon) values(1, '用户管理', '', '');
insert into sys_menu(parent_id, text, url, icon) values(1, '角色管理', '', '');
insert into sys_menu(parent_id, text, url, icon) values(1, '我的信息', '', '');
insert into sys_menu(parent_id, text, url, icon) values(2, '采购申请', '', '');
insert into sys_menu(parent_id, text, url, icon) values(2, '采购审核', '', '');
insert into sys_menu(parent_id, text, url, icon) values(2, '物资采购', '', '');
insert into sys_menu(parent_id, text, url, icon) values(2, '物资入库', '', '');
insert into sys_menu(parent_id, text, url, icon) values(2, '物资领取', '', '');
insert into sys_menu(parent_id, text, url, icon) values(3, '历史采购', '', '');


-- 角色功能表：sys_role_menu
drop table if exists sys_role_menu;
create table sys_role_menu(
    role_id int (10) comment '角色主键',
    menu_id int (10) comment '菜单主键'
)Engine=InnoDB charset='utf8mb4' comment='角色功能表';
insert into sys_role_menu(role_id, menu_id) values (1, 1);
insert into sys_role_menu(role_id, menu_id) values (1, 2);
insert into sys_role_menu(role_id, menu_id) values (1, 3);
insert into sys_role_menu(role_id, menu_id) values (1, 4);
insert into sys_role_menu(role_id, menu_id) values (1, 5);
insert into sys_role_menu(role_id, menu_id) values (1, 6);
insert into sys_role_menu(role_id, menu_id) values (1, 7);
insert into sys_role_menu(role_id, menu_id) values (1, 8);
insert into sys_role_menu(role_id, menu_id) values (1, 9);
insert into sys_role_menu(role_id, menu_id) values (1, 10);
insert into sys_role_menu(role_id, menu_id) values (1, 11);
insert into sys_role_menu(role_id, menu_id) values (1, 12);

-- 用户信息表：sys_user
drop table if exists sys_user;
create table sys_user(
    id int (10) not null auto_increment comment '主键',
    name varchar(100) not null comment '名称',
    cellphone varchar(100) not null comment '手机号码',
    password varchar(500) not null comment '登录密码，采用 MD5 加密',
    role_id int (10) not null comment '用户角色',
    id_card varchar(100) not null comment '身份证号码',
    avatar varchar(500) comment '照片',
    status int (2) not null default 1 comment '状态：0-禁用，1-启用',
    create_time timestamp not null default current_timestamp comment '创建时间',
    update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key(id)
)Engine=InnoDB charset='utf8mb4' comment='用户信息表';
insert into sys_user(name, cellphone, password, role_id, id_card) values('刘博', '17829164302', '21232f297a57a5a743894a0e4a801fc3', 1, '610502199707131111');
insert into sys_user(name, cellphone, password, role_id, id_card) values('李栋', '15235134818', '62bc1de1ed50474dbdeb7cb18df666b3', 2, '142302199910053333');
insert into sys_user(name, cellphone, password, role_id, id_card) values('杨成', '15148137171', 'c908aa7ed26160dab1fb85692b1863a2', 3, '411325199209025555');
insert into sys_user(name, cellphone, password, role_id, id_card) values('刘帆', '17629287397', 'e4faab4bec297378741247d8582c878e', 4, '610523199707277777');
insert into sys_user(name, cellphone, password, role_id, id_card) values('王宇航', '13772484761', 'f39bd6381c1db8be35005612206dee75', 5, '610502199402079999');

-- 物资采购表：sys_purchase_info
drop table if exists sys_purchase_info;
create table sys_purchase_info(
    id int (10) not null auto_increment comment '主键',
    no varchar(100) not null comment '采购编号',
    apply_name varchar(100) not null comment '物资名称',
    apply_quantity decimal(10, 2) not null comment '物资数量',
    apply_unit varchar(100) not null comment '物资单位',
    apply_remark varchar(500) comment '物资备注',
    apply_user_id int (10) not null comment '申请人',
    apply_time timestamp not null default current_timestamp comment '申请时间',
    check_suggestion varchar(500) comment '审核意见',
    check_user_id int (10) not null comment '审核人',
    check_time timestamp comment '审核时间',
    goods_name varchar(100) comment '购买名称',
    goods_quantity decimal(10, 2) comment '购买数量',
    goods_unit varchar(100) comment '购买单位',
    goods_pre_price decimal(10, 2) comment '购买单价',
    goods_discount decimal(10, 2) comment '购买优惠',
    goods_total_amount decimal(10, 2) comment '购买总金额',
    goods_company varchar(100) comment '生产厂商',
    purchase_remake varchar(500) comment '采购备注',
    purchase_user_id int (10) comment '采购人',
    purchase_time timestamp comment '采购时间',
    store_user_id int (10) comment '入库人',
    store_time timestamp comment '入库时间',
    draw_user_id int (10) comment '领取人',
    draw_time timestamp comment '领取时间',
    status int (2) not null default 1 comment '采购状态：1-已提交，待审核；2-已审核、待购买；3-已购买、待入库；4、已入库、待领取；5、已领取；6、申请已撤回；7、申请已驳回',
    create_time timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
    update_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '修改时间',
    primary key(id)
)Engine=InnoDB charset='utf8mb4' comment='物资采购表';
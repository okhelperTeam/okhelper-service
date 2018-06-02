/*
 Navicat Premium Data Transfer

 Source Server         : okhelper
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : 139.199.30.155:3306
 Source Schema         : okhelper

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 19/05/2018 10:46:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `super_id` bigint(20) NOT NULL COMMENT '父类Id ',
  `category_name` varchar(40) NOT NULL COMMENT '类别名称',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `operator` bigint(20) DEFAULT NULL COMMENT '创建者Id ',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` int(1) NOT NULL DEFAULT '1' COMMENT '状态 0不启用，1启用',
  `store_id` bigint(20) NOT NULL COMMENT '商店Id ',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `category_store_id` (`store_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='类别表';

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_name` varchar(40) NOT NULL COMMENT '客户姓名',
  `customer_store_name` varchar(40) NOT NULL COMMENT '客户店名',
  `customer_phone` char(11) NOT NULL COMMENT '客户手机号',
  `customer_email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `customer_score` int(11) NOT NULL DEFAULT '0' COMMENT '客户积分(每次消费取整)',
  `customer_level` int(10) NOT NULL DEFAULT '0' COMMENT 'vip级别 0->5 默认0',
  `customer_address` varchar(40) NOT NULL COMMENT '客户地址',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` int(1) NOT NULL DEFAULT '1' COMMENT '状态 0不启用，1启用',
  `store_id` bigint(20) NOT NULL COMMENT '所属商店Id ',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `customer_store_id` (`store_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户表';

-- ----------------------------
-- Table structure for delivery_order
-- ----------------------------
DROP TABLE IF EXISTS `delivery_order`;
CREATE TABLE `delivery_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_number` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '出库单号',
  `sale_order_id` bigint(20) NOT NULL COMMENT '销售单id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期(出库时间)',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` int(1) DEFAULT '1' COMMENT '状态 0废除，1激活',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `stockouter` bigint(20) NOT NULL COMMENT '出库员',
  `store_id` bigint(20) NOT NULL COMMENT '商铺Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='出库单';

-- ----------------------------
-- Table structure for delivery_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `delivery_order_detail`;
CREATE TABLE `delivery_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `delivery_order_id` bigint(20) NOT NULL COMMENT '出库单id',
  `product_id` bigint(20) NOT NULL COMMENT '商品ID',
  `warehouse_id` bigint(20) NOT NULL COMMENT '仓库ID',
  `delivery_count` int(10) NOT NULL COMMENT '出库数量',
  `product_date` timestamp NULL DEFAULT NULL COMMENT '生产日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='出库单详情';

-- ----------------------------
-- Table structure for logging_event
-- ----------------------------
DROP TABLE IF EXISTS `logging_event`;
CREATE TABLE `logging_event` (
  `timestmp` bigint(20) NOT NULL,
  `occurrence_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `formatted_message` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `logger_name` varchar(254) COLLATE utf8mb4_unicode_ci NOT NULL,
  `level_string` varchar(254) COLLATE utf8mb4_unicode_ci NOT NULL,
  `thread_name` varchar(254) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `reference_flag` smallint(6) DEFAULT NULL,
  `arg0` varchar(254) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `arg1` varchar(254) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `arg2` varchar(254) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `arg3` varchar(254) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `caller_filename` varchar(254) COLLATE utf8mb4_unicode_ci NOT NULL,
  `caller_class` varchar(254) COLLATE utf8mb4_unicode_ci NOT NULL,
  `caller_method` varchar(254) COLLATE utf8mb4_unicode_ci NOT NULL,
  `caller_line` char(4) COLLATE utf8mb4_unicode_ci NOT NULL,
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1706 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for logging_event_exception
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_exception`;
CREATE TABLE `logging_event_exception` (
  `event_id` bigint(20) NOT NULL,
  `i` smallint(6) NOT NULL,
  `trace_line` varchar(254) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`event_id`,`i`),
  CONSTRAINT `logging_event_exception_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for logging_event_property
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_property`;
CREATE TABLE `logging_event_property` (
  `event_id` bigint(20) NOT NULL,
  `mapped_key` varchar(254) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mapped_value` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`event_id`,`mapped_key`),
  CONSTRAINT `logging_event_property_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_name` varchar(30) NOT NULL COMMENT '商品名',
  `product_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品标题',
  `product_attribute` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '商品属性(使用json存储)',
  `category_id` bigint(20) NOT NULL COMMENT '类别Id',
  `sales_stock` int(10) NOT NULL DEFAULT '0' COMMENT '可销售库存(小于等于真是库存)',
  `specification` varchar(20) DEFAULT NULL COMMENT '规格',
  `unit` char(1) NOT NULL COMMENT '规格单位',
  `retail_price` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '零售价',
  `main_img` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '主图',
  `sub_imgs` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '副图(数组)',
  `article_number` varchar(20) DEFAULT NULL COMMENT '货号',
  `bar_code` varchar(20) NOT NULL COMMENT '条码',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` int(1) NOT NULL DEFAULT '1' COMMENT '状态 0下架，1上架',
  `store_id` bigint(20) NOT NULL COMMENT '所属商店Id ',
  PRIMARY KEY (`id`),
  KEY `goods_store_id` (`store_id`) USING BTREE,
  KEY `goods_name_storeId` (`product_name`,`store_id`) USING BTREE,
  KEY `goods_category` (`category_id`) USING BTREE,
  KEY `goods_barCode` (`bar_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品表';

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_code` varchar(255) DEFAULT '' COMMENT '归属菜单,前端判断并展示菜单使用',
  `menu_name` varchar(255) DEFAULT '' COMMENT '菜单的中文释义',
  `permission_code` varchar(255) DEFAULT '' COMMENT '权限的代码/通配符,对应代码中@RequiresPermissions 的value',
  `permission_name` varchar(255) DEFAULT '' COMMENT '本权限的中文释义',
  `operator` bigint(20) NOT NULL COMMENT '操作者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` int(1) NOT NULL DEFAULT '1' COMMENT '状态 0废除，1激活',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` VALUES (1, 'ALL', '全部权限', '*:*', 'ALL', 1, '2018-04-14 16:58:36', '2018-04-20 23:20:50', 1, NULL);
INSERT INTO `permission` VALUES (2, 'warehouse', '仓库', 'warehouse:view', '查看', 1, '2018-04-20 22:27:41', '2018-04-20 23:21:19', 1, '查看仓库信息');
INSERT INTO `permission` VALUES (3, 'warehouse', '仓库', 'warehouse:edit', '编辑', 1, '2018-04-20 22:46:36', '2018-04-20 23:21:26', 1, NULL);
INSERT INTO `permission` VALUES (4, 'stock', '库存', 'stock:view', '查看', 1, '2018-04-20 22:52:20', '2018-04-20 23:21:27', 1, NULL);
INSERT INTO `permission` VALUES (5, 'stock', '库存', 'stock:edit', '编辑', 1, '2018-04-20 22:52:56', '2018-04-20 23:21:28', 1, NULL);
INSERT INTO `permission` VALUES (6, 'storage_order', '入库单', 'storage_order:view', '查看', 1, '2018-04-20 22:55:12', '2018-04-20 22:55:12', 1, NULL);
INSERT INTO `permission` VALUES (7, 'storage_order', '入库单', 'storage_order:add', '入库', 1, '2018-04-20 22:56:02', '2018-04-20 23:00:28', 1, '采购/入库');
INSERT INTO `permission` VALUES (8, 'storage_order', '入库单', 'storage_order:delete', '撤销', 1, '2018-04-20 22:57:15', '2018-04-20 22:57:15', 1, NULL);
INSERT INTO `permission` VALUES (9, 'storage_order', '入库单', 'storage_order:print', '导出打印', 1, '2018-04-20 23:02:38', '2018-04-20 23:02:38', 1, NULL);
INSERT INTO `permission` VALUES (10, 'sales_order_history', '销售单', 'sale_order:view', '查看', 1, '2018-04-20 22:55:12', '2018-05-19 13:53:26', 1, NULL);
INSERT INTO `permission` VALUES (11, 'sales_order_add', '销售单', 'sale_order:add', '下单', 1, '2018-04-20 22:56:02', '2018-05-19 13:53:25', 1, '下单/出库');
INSERT INTO `permission` VALUES (12, 'sales_order', '销售单', 'sale_order:delete', '撤销', 1, '2018-04-20 22:57:15', '2018-05-19 13:53:28', 1, NULL);
INSERT INTO `permission` VALUES (13, 'sales_order', '销售单', 'sale_order:print', '导出打印', 1, '2018-04-20 23:03:40', '2018-05-19 13:53:31', 1, NULL);
INSERT INTO `permission` VALUES (14, 'category', '分类管理', 'category:view', '查看', 1, '2018-04-20 23:09:15', '2018-04-20 23:30:51', 1, NULL);
INSERT INTO `permission` VALUES (15, 'category', '分类管理', 'category:edit', '编辑', 1, '2018-04-20 23:11:16', '2018-04-20 23:30:59', 1, NULL);
INSERT INTO `permission` VALUES (16, 'product', '商品管理', 'product:view', '查看', 1, '2018-04-20 23:29:29', '2018-05-16 12:05:46', 1, NULL);
INSERT INTO `permission` VALUES (17, 'product', '商品管理', 'product:edit', '编辑', 1, '2018-04-20 23:30:08', '2018-05-16 12:05:50', 1, NULL);
INSERT INTO `permission` VALUES (18, 'product', '商品管理', 'product:print', '导出打印', 1, '2018-04-20 23:33:02', '2018-05-16 12:05:53', 1, NULL);
INSERT INTO `permission` VALUES (19, 'supplier', '供应商管理', 'supplier:view', '查看', 1, '2018-04-20 23:39:33', '2018-04-20 23:39:33', 1, NULL);
INSERT INTO `permission` VALUES (20, 'supplier', '供应商管理', 'supplier:edit', '编辑', 1, '2018-04-20 23:41:04', '2018-04-20 23:41:04', 1, NULL);
INSERT INTO `permission` VALUES (21, 'supplier', '供应商管理', 'supplier:print', '导出打印', 1, '2018-04-20 23:41:49', '2018-04-20 23:41:49', 1, NULL);
INSERT INTO `permission` VALUES (22, 'customer', '客户管理', 'customer:view', '查看', 1, '2018-04-20 23:44:10', '2018-04-20 23:44:10', 1, NULL);
INSERT INTO `permission` VALUES (23, 'customer', '客户管理', 'customer:edit', '编辑', 1, '2018-04-20 23:44:57', '2018-04-20 23:44:57', 1, NULL);
INSERT INTO `permission` VALUES (24, 'employee', '员工管理', 'employee:view', '查看', 1, '2018-04-20 23:46:39', '2018-04-20 23:46:39', 1, NULL);
INSERT INTO `permission` VALUES (25, 'employee', '员工管理', 'employee:edit', '编辑', 1, '2018-04-20 23:47:18', '2018-04-20 23:47:18', 1, NULL);
INSERT INTO `permission` VALUES (26, 'role', '角色管理', 'role:view', '查看', 1, '2018-04-20 23:48:22', '2018-04-20 23:48:22', 1, NULL);
INSERT INTO `permission` VALUES (27, 'role', '角色管理', 'role:edit', '编辑', 1, '2018-04-20 23:48:54', '2018-04-20 23:48:54', 1, NULL);
INSERT INTO `permission` VALUES (28, 'role', '角色管理', 'user:role:change', '变更角色', 1, '2018-04-20 23:54:36', '2018-04-20 23:55:03', 1, NULL);
INSERT INTO `permission` VALUES (29, 'store', '店铺管理', 'store:view', '查看', 1, '2018-04-20 23:56:09', '2018-04-20 23:56:09', 1, NULL);
INSERT INTO `permission` VALUES (30, 'store', '店铺管理', 'store:edit', '编辑', 1, '2018-04-20 23:58:09', '2018-04-21 00:13:41', 1, '包括系统设置');
INSERT INTO `permission` VALUES (31, 'report', '统计报表', 'report:procurement:view', '采购报表', 1, '2018-04-21 00:03:33', '2018-04-21 00:09:48', 1, NULL);
INSERT INTO `permission` VALUES (32, 'report', '统计报表', 'report:sales:view', '销售报表', 1, '2018-04-21 00:05:21', '2018-04-21 14:41:31', 1, NULL);
INSERT INTO `permission` VALUES (33, 'report', '统计报表', 'report:product:view', '商品销量报表', 1, '2018-04-21 00:09:41', '2018-05-16 12:05:15', 1, NULL);
INSERT INTO `permission` VALUES (34, 'report', '统计报表', 'report:employee:view', '员工业绩报表', 1, '2018-04-21 00:12:03', '2018-04-21 00:12:03', 1, NULL);
INSERT INTO `permission` VALUES (35, 'delivery_order', '出库单', 'delivery_order:view', '查看', 1, '2018-04-27 21:12:39', '2018-05-18 08:10:42', 1, NULL);
INSERT INTO `permission` VALUES (36, 'delivery_order', '出库单', 'delivery_order:add', '出库', 1, '2018-04-27 21:14:01', '2018-05-18 08:10:45', 1, NULL);
INSERT INTO `permission` VALUES (37, 'user', '个人信息', 'user:update', '修改', 1, '2018-05-15 10:59:43', '2018-05-15 10:59:51', 1, NULL);
INSERT INTO `permission` VALUES (38, 'sales_order', '销售单', 'sale_order:unsend:view', '查看未发货订单', 1, '2018-05-19 13:46:03', '2018-05-19 13:53:43', 1, NULL);
INSERT INTO `permission` VALUES (39, 'sales_order', '销售单', 'sale_order:edit', '编辑', 1, '2018-05-19 13:55:34', '2018-06-02 12:36:58', 1, '编辑销售单');
INSERT INTO `permission` VALUES (40, 'sales:order', '销售单', 'sale_order:pay', '支付', 1, '2018-05-19 13:58:30', '2018-05-19 13:58:30', 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(40) NOT NULL COMMENT '角色名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `operator` bigint(20) NOT NULL COMMENT '操作者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` int(1) NOT NULL DEFAULT '1' COMMENT '状态 0废除，1激活',
  `store_id` bigint(20) DEFAULT NULL COMMENT '店铺id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, '平台管理员', '超级权限组(角色1可以访问所有数据)', 1, '2018-04-14 16:56:34', '2018-04-15 09:01:31', 1, 0);
INSERT INTO `role` VALUES (2, '注册者', '注册人拥有最大权限，并且不可修改（系统）', 1, '2018-04-09 06:35:32', '2018-05-17 13:05:00', 1, 0);
INSERT INTO `role` VALUES (3, '仓管员', '负责库存管理、库存盘点、库存调拨等(系统)', 1, '2018-04-09 06:35:32', '2018-05-17 13:06:11', 1, 0);
INSERT INTO `role` VALUES (4, '采购员', '负责采购事宜，包括新建商品、采购单、采购退货等(系统)', 1, '2018-04-09 06:35:32', '2018-05-17 13:05:28', 1, 0);
INSERT INTO `role` VALUES (5, '销售员', '负责销售业务(系统)', 1, '2018-04-14 17:24:32', '2018-05-17 13:06:00', 1, 0);
INSERT INTO `role` VALUES (6, '会计', '财务管理权限者(系统)', 1, '2018-04-14 17:26:24', '2018-05-17 17:33:05', 1, 0);
INSERT INTO `role` VALUES (7, '收银', '收银员(系统)', 1, '2018-04-15 11:34:57', '2018-05-17 17:33:11', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` int(1) DEFAULT '1' COMMENT '状态 0废除，1激活',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `operator` bigint(20) NOT NULL COMMENT '操作者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_permission` (`role_id`,`permission_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
INSERT INTO `role_permission` VALUES (1, 1, 1, '2018-04-21 13:58:43', '2018-04-21 13:58:43', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (2, 1, 2, '2018-04-21 13:58:43', '2018-04-21 13:58:43', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (3, 1, 3, '2018-04-21 13:58:43', '2018-04-21 13:58:43', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (4, 1, 4, '2018-04-21 13:58:43', '2018-04-21 13:58:43', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (5, 1, 5, '2018-04-21 13:58:43', '2018-04-21 13:58:43', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (6, 1, 6, '2018-04-21 13:58:43', '2018-04-21 13:58:43', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (7, 1, 7, '2018-04-21 13:58:43', '2018-04-21 13:58:43', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (8, 1, 8, '2018-04-21 13:58:43', '2018-04-21 13:58:43', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (9, 1, 9, '2018-04-21 13:58:43', '2018-04-21 13:58:43', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (10, 1, 10, '2018-04-21 13:58:43', '2018-04-21 13:58:43', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (11, 1, 11, '2018-04-21 13:58:43', '2018-04-21 13:58:43', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (12, 1, 12, '2018-04-21 13:58:43', '2018-04-21 13:58:43', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (13, 1, 13, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (14, 1, 14, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (15, 1, 15, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (16, 1, 16, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (17, 1, 17, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (18, 1, 18, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (19, 1, 19, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (20, 1, 20, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (21, 1, 21, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (22, 1, 22, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (23, 1, 23, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (24, 1, 24, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (25, 1, 25, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (26, 1, 26, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (27, 1, 27, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (28, 1, 28, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (29, 1, 29, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (30, 1, 30, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (31, 1, 31, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (32, 1, 32, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (33, 1, 33, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (34, 1, 34, '2018-04-21 13:58:44', '2018-04-21 13:58:44', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (35, 2, 1, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (36, 2, 2, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (37, 2, 3, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (38, 2, 4, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (39, 2, 5, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (40, 2, 6, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (41, 2, 7, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (42, 2, 8, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (43, 2, 9, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (44, 2, 10, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (45, 2, 11, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (46, 2, 12, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (47, 2, 13, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (48, 2, 14, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (49, 2, 15, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (50, 2, 16, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (51, 2, 17, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (52, 2, 18, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (53, 2, 19, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (54, 2, 20, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (55, 2, 21, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (56, 2, 22, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (57, 2, 23, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (58, 2, 24, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (59, 2, 25, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (60, 2, 26, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (61, 2, 27, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (62, 2, 28, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (63, 2, 29, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (64, 2, 30, '2018-04-21 13:59:25', '2018-04-21 13:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (65, 2, 31, '2018-04-21 13:59:26', '2018-04-21 13:59:26', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (66, 2, 32, '2018-04-21 13:59:26', '2018-04-21 13:59:26', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (67, 2, 33, '2018-04-21 13:59:26', '2018-04-21 13:59:26', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (68, 2, 34, '2018-04-21 13:59:26', '2018-04-21 13:59:26', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (69, 3, 2, '2018-05-16 11:47:59', '2018-05-16 11:47:59', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (70, 3, 3, '2018-05-16 11:47:59', '2018-05-16 11:47:59', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (71, 3, 4, '2018-05-16 11:48:00', '2018-05-16 11:48:00', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (72, 3, 5, '2018-05-16 11:48:00', '2018-05-16 11:48:00', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (73, 3, 6, '2018-05-16 11:48:00', '2018-05-16 11:48:00', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (74, 3, 7, '2018-05-16 11:48:00', '2018-05-16 11:48:00', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (75, 3, 8, '2018-05-16 11:48:00', '2018-05-16 11:48:00', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (76, 3, 9, '2018-05-16 11:48:00', '2018-05-16 11:48:00', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (77, 3, 35, '2018-05-16 11:48:00', '2018-05-16 11:48:00', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (78, 3, 36, '2018-05-16 11:48:00', '2018-05-16 11:48:00', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (79, 3, 16, '2018-05-16 11:51:41', '2018-05-16 11:51:41', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (80, 4, 16, '2018-05-16 11:59:25', '2018-05-16 11:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (81, 4, 19, '2018-05-16 11:59:25', '2018-05-16 11:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (82, 4, 20, '2018-05-16 11:59:25', '2018-05-16 11:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (83, 4, 21, '2018-05-16 11:59:25', '2018-05-16 11:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (84, 4, 6, '2018-05-16 11:59:25', '2018-05-16 11:59:25', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (85, 4, 7, '2018-05-16 11:59:26', '2018-05-16 11:59:26', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (86, 4, 8, '2018-05-16 11:59:26', '2018-05-16 11:59:26', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (87, 4, 9, '2018-05-16 11:59:26', '2018-05-16 11:59:26', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (88, 5, 16, '2018-05-16 12:04:29', '2018-05-16 12:04:29', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (89, 5, 10, '2018-05-16 12:04:29', '2018-05-16 12:04:29', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (90, 5, 11, '2018-05-16 12:04:29', '2018-05-16 12:04:29', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (91, 5, 12, '2018-05-16 12:04:30', '2018-05-16 12:04:30', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (92, 5, 13, '2018-05-16 12:04:30', '2018-05-16 12:04:30', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (93, 5, 14, '2018-05-16 12:04:30', '2018-05-16 12:04:30', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (94, 5, 15, '2018-05-16 12:04:30', '2018-05-16 12:04:30', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (95, 5, 17, '2018-05-16 12:04:30', '2018-05-16 12:04:30', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (96, 5, 18, '2018-05-16 12:04:30', '2018-05-16 12:04:30', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (97, 5, 22, '2018-05-16 12:04:30', '2018-05-16 12:04:30', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (98, 5, 23, '2018-05-16 12:04:30', '2018-05-16 12:04:30', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (99, 5, 32, '2018-05-16 12:04:30', '2018-05-16 12:04:30', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (100, 5, 33, '2018-05-16 12:04:30', '2018-05-16 12:04:30', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (101, 4, 31, '2018-05-16 12:13:34', '2018-05-16 12:13:34', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (102, 1, 35, '2018-05-16 12:16:19', '2018-05-16 12:16:19', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (103, 2, 35, '2018-05-16 12:16:19', '2018-05-16 12:16:19', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (104, 1, 36, '2018-05-16 12:16:20', '2018-05-16 12:16:20', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (105, 2, 36, '2018-05-16 12:16:20', '2018-05-16 12:16:20', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (106, 3, 38, '2018-05-19 13:46:50', '2018-05-19 13:46:50', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (107, 5, 38, '2018-05-19 13:48:00', '2018-05-19 13:48:00', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (108, 5, 39, '2018-05-19 13:55:53', '2018-05-19 13:55:53', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (109, 5, 40, '2018-05-19 13:58:45', '2018-05-19 13:58:45', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (110, 1, 38, '2018-05-19 13:59:08', '2018-05-19 13:59:08', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (111, 1, 39, '2018-05-19 13:59:19', '2018-05-19 13:59:19', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (112, 1, 40, '2018-05-19 13:59:28', '2018-05-19 13:59:28', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (113, 2, 38, '2018-05-19 13:59:35', '2018-05-19 13:59:35', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (114, 2, 39, '2018-05-19 13:59:49', '2018-05-19 13:59:49', 1, NULL, 1);
INSERT INTO `role_permission` VALUES (115, 2, 40, '2018-05-19 13:59:57', '2018-05-19 13:59:57', 1, NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for sale_order
-- ----------------------------
DROP TABLE IF EXISTS `sale_order`;
CREATE TABLE `sale_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_number` varchar(40) NOT NULL COMMENT '销售单号',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户Id ',
  `seller` bigint(20) NOT NULL COMMENT '销售员',
  `stockouter` bigint(20) DEFAULT NULL COMMENT '出库员',
  `sum_price` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '订单总价（应付款）',
  `real_pay` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '实付款 ',
  `discount_price` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '优惠金额 ',
  `to_be_paid` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '待支付金额 （欠款金额）',
  `pay_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '{"1":"0.00","2":"0.00","3":"0.00","4":"0.00","5":"0.00","6":"0.00"}' COMMENT '付款方式  (1-现金,2-支付宝条码支付,3-微信,4-支付宝收款码,5-微信收款码，6-刷卡 json表示)',
  `order_status` int(1) NOT NULL DEFAULT '1' COMMENT '订单状态（1-未付款，2-未付全款，3-已付款，4-交易成功，5-订单关闭）',
  `logistics_status` int(1) DEFAULT NULL COMMENT '物流状态（1-未发货 2-已发货 3-确认收货）',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期(下单时间)',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '最后一次付款时间',
  `send_time` timestamp NULL DEFAULT NULL COMMENT '出库时间',
  `success_time` timestamp NULL DEFAULT NULL COMMENT '交易完成时间',
  `close_time` timestamp NULL DEFAULT NULL COMMENT '交易关闭时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `store_id` bigint(20) NOT NULL COMMENT '商店Id ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sales_num_uk` (`order_number`) USING BTREE,
  KEY `sales_store` (`store_id`) USING BTREE,
  KEY `sale_sum_price_store` (`sum_price`,`store_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4138 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='销售表';

-- ----------------------------
-- Table structure for sale_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `sale_order_detail`;
CREATE TABLE `sale_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sale_order_id` bigint(20) NOT NULL COMMENT '销售Id ',
  `product_id` bigint(20) NOT NULL COMMENT '商品Id ',
  `product_name` varchar(30) NOT NULL COMMENT '商品名',
  `product_title` varchar(65) DEFAULT NULL COMMENT '商品标题',
  `main_img` varchar(200) DEFAULT NULL COMMENT '商品主图',
  `sale_count` int(11) NOT NULL COMMENT '商品数量(最小单位)',
  `sale_price` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '商品单价 (销售时单价&最小单位)',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4215 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='销售详情表';

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `warehouse_id` bigint(20) NOT NULL COMMENT '仓库Id ',
  `product_id` bigint(20) NOT NULL COMMENT '商品Id ',
  `product_date` timestamp NULL DEFAULT NULL COMMENT '生产日期',
  `shelf_life` int(11) DEFAULT NULL COMMENT '保质期',
  `stock_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '库存数量 ',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `store_id` bigint(20) NOT NULL COMMENT '所属商店Id ',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `stock_store` (`store_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='库存表';

-- ----------------------------
-- Table structure for storage_order
-- ----------------------------
DROP TABLE IF EXISTS `storage_order`;
CREATE TABLE `storage_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_number` varchar(40) NOT NULL COMMENT '入库单号',
  `supplier_id` bigint(20) NOT NULL COMMENT '供应商Id ',
  `stockiner` bigint(20) NOT NULL COMMENT '入库员',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `store_id` bigint(20) NOT NULL COMMENT '所属商店Id ',
  `total_price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `storage_num_uk` (`order_number`) USING BTREE,
  KEY `storage_store` (`store_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入库表';

-- ----------------------------
-- Table structure for storage_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `storage_order_detail`;
CREATE TABLE `storage_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `supplier_id` bigint(20) DEFAULT NULL COMMENT '供应商Id ',
  `storage_in_id` bigint(20) NOT NULL COMMENT '入库Id ',
  `warehouse_id` bigint(20) NOT NULL COMMENT '仓库Id ',
  `product_id` bigint(20) NOT NULL COMMENT '商品Id ',
  `storage_count` int(11) NOT NULL COMMENT '商品数量(最小单位) ',
  `storage_price` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '进价 (最小单位)',
  `product_date` timestamp NULL DEFAULT NULL COMMENT '生产日期',
  `shelf_life` int(11) DEFAULT NULL COMMENT '保质期',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入库详情表';

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `store_name` varchar(40) NOT NULL COMMENT '店铺名称',
  `store_phone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话',
  `store_address` varchar(200) DEFAULT NULL COMMENT '店铺地址',
  `store_logo` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '店铺logo',
  `money_code` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '收款码',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` int(1) NOT NULL DEFAULT '1' COMMENT '状态 0废除，1激活',
  `leader_id` bigint(20) NOT NULL COMMENT '负责人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_store_leader` (`leader_id`) USING BTREE,
  KEY `uk_store_name` (`store_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='店铺表';

-- ----------------------------
-- Table structure for store_user
-- ----------------------------
DROP TABLE IF EXISTS `store_user`;
CREATE TABLE `store_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `store_id` bigint(20) NOT NULL COMMENT '店铺id',
  `operator` bigint(20) NOT NULL COMMENT '操作者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` int(1) NOT NULL DEFAULT '1' COMMENT '状态 0废除，1激活',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='店铺用户表';

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `supplier_name` varchar(40) NOT NULL COMMENT '供应商名称',
  `supplier_phone` char(11) NOT NULL COMMENT '供应商手机号',
  `supplier_address` varchar(40) NOT NULL COMMENT '供应商地址',
  `supplier_contacts` varchar(10) NOT NULL COMMENT '供应商联系人姓名',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` int(1) NOT NULL DEFAULT '1' COMMENT '状态 0禁用，1可以',
  `store_id` bigint(20) NOT NULL COMMENT '所属商店Id ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='供应商表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(30) NOT NULL COMMENT '登录账号-手机号',
  `user_password` varchar(65) NOT NULL COMMENT '登录密码',
  `pass_problem` varchar(65) DEFAULT NULL COMMENT '密保问题',
  `pass_answer` varchar(65) DEFAULT NULL COMMENT '密保答案',
  `user_nick` varchar(20) DEFAULT NULL COMMENT '昵称',
  `user_avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像',
  `user_email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `user_sex` char(2) DEFAULT '保密' COMMENT '性别',
  `user_phone` char(11) DEFAULT NULL COMMENT '手机号',
  `user_birthday` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生日',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '最后一次登陆IP',
  `operator` bigint(20) DEFAULT '1' COMMENT '操作者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` int(1) DEFAULT '1' COMMENT '状态 0废除，1激活',
  `store_id` bigint(20) DEFAULT NULL COMMENT '所属商店Id  0平台管理员',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_sys_user_name` (`user_name`),
  KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` int(20) NOT NULL COMMENT '角色id',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` int(1) NOT NULL DEFAULT '1' COMMENT '状态 0删除，1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色表';

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `warehouse_name` varchar(40) NOT NULL COMMENT '仓库名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `store_keeper` bigint(20) DEFAULT NULL COMMENT '仓管员',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` int(1) NOT NULL DEFAULT '1' COMMENT '状态 0不可用，1可用',
  `store_id` bigint(20) NOT NULL COMMENT '所属商店Id ',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `warehouse_store` (`store_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='仓库表';

SET FOREIGN_KEY_CHECKS = 1;

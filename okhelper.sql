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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';

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
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限表';

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
  `pay_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '{"1":"0.00","2":"0.00","3":"0.00","4":"0.00","5":"0.00","6":"0.00"}' COMMENT '付款方式  (1-现金,2-支付宝条码支付,3-微信,4-支付宝收款码,5-微信收款码，6-刷卡json)',
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

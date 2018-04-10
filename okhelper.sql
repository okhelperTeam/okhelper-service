/*
 Navicat Premium Data Transfer

 Source Server         : okhelper
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 139.199.30.155:3306
 Source Schema         : okhelper

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 10/04/2018 17:15:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_code` varchar(255) DEFAULT '' COMMENT '归属菜单,前端判断并展示菜单使用,',
  `menu_name` varchar(255) DEFAULT '' COMMENT '菜单的中文释义',
  `permission_code` varchar(255) DEFAULT '' COMMENT '权限的代码/通配符,对应代码中@RequiresPermissions 的value',
  `permission_name` varchar(255) DEFAULT '' COMMENT '本权限的中文释义',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` char(1) DEFAULT '1' COMMENT '状态 0废除，1激活',
  `operator` bigint(20) NOT NULL COMMENT '操作者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES (1, 'warehouse', '仓库', 'warehouse:post', '入仓', '2018-04-10 13:07:04', '2018-04-10 17:14:34', '1', 1);
INSERT INTO `sys_permission` VALUES (2, 'product', '商品', 'product:post', '添加商品', '2018-04-10 13:07:04', '2018-04-10 17:14:40', '1', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(40) NOT NULL COMMENT '角色名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` char(1) DEFAULT '1' COMMENT '状态 0废除，1激活',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `operator` bigint(20) NOT NULL COMMENT '操作者',
  `store_id` varchar(20) DEFAULT NULL COMMENT '店铺id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '店长', '2018-04-09 06:35:32', '2018-04-09 06:35:32', '1', '店铺最高权限者', 1, '1');
INSERT INTO `sys_role` VALUES (2, '仓管员', '2018-04-09 06:35:32', '2018-04-09 06:35:32', '1', '仓库管理权限者', 1, '1');
INSERT INTO `sys_role` VALUES (3, '业务员', '2018-04-09 06:35:32', '2018-04-09 06:35:32', '1', '销售管理权限者', 1, '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` char(1) DEFAULT '1' COMMENT '状态 0废除，1激活',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `operator` bigint(20) NOT NULL COMMENT '操作者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` VALUES (1, 1, 1, '2018-04-09 06:35:32', '2018-04-09 06:35:32', '1', '', 1);
INSERT INTO `sys_role_permission` VALUES (2, 1, 2, '2018-04-09 06:35:32', '2018-04-09 06:35:32', '1', '', 1);
INSERT INTO `sys_role_permission` VALUES (3, 1, 3, '2018-04-09 06:35:32', '2018-04-09 06:35:32', '1', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_store
-- ----------------------------
DROP TABLE IF EXISTS `sys_store`;
CREATE TABLE `sys_store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `store_name` varchar(40) NOT NULL COMMENT '店铺名称',
  `store_address` varchar(200) DEFAULT NULL COMMENT '店铺地址',
  `store_phtoo` varchar(40) DEFAULT NULL COMMENT '店铺图像',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` char(1) DEFAULT '1' COMMENT '状态 0废除，1激活',
  `operator` bigint(20) NOT NULL COMMENT '操作者',
  `leader_id` bigint(20) NOT NULL COMMENT '负责人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='店铺表';

-- ----------------------------
-- Records of sys_store
-- ----------------------------
BEGIN;
INSERT INTO `sys_store` VALUES (1, 'OK帮店铺', '青岛大学', NULL, '最牛', '2018-04-09 06:35:32', '2018-04-09 06:35:32', '1', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_store_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_store_user`;
CREATE TABLE `sys_store_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `store_id` bigint(20) NOT NULL COMMENT '店铺id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `operator` bigint(20) NOT NULL COMMENT '操作者',
  `delete_status` char(1) DEFAULT '1' COMMENT '状态 0废除，1激活',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='店铺用户表';

-- ----------------------------
-- Records of sys_store_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_store_user` VALUES (1, 1, 1, '2018-04-09 06:35:32', '2018-04-09 06:35:32', 1, '1');
INSERT INTO `sys_store_user` VALUES (2, 2, 1, '2018-04-09 06:35:32', '2018-04-09 06:35:32', 1, '1');
INSERT INTO `sys_store_user` VALUES (3, 3, 1, '2018-04-09 06:35:32', '2018-04-09 06:35:32', 1, '1');
INSERT INTO `sys_store_user` VALUES (4, 4, 1, '2018-04-09 06:35:32', '2018-04-09 06:35:32', 1, '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(30) NOT NULL COMMENT '登录账号',
  `user_password` varchar(65) NOT NULL COMMENT '登录密码',
  `pass_problem` varchar(65) DEFAULT NULL COMMENT '密保问题',
  `pass_answer` varchar(65) DEFAULT NULL COMMENT '密保答案',
  `user_nick` varchar(20) DEFAULT NULL COMMENT '昵称',
  `user_avatar` varchar(30) DEFAULT NULL COMMENT '头像',
  `user_phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `user_email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `user_sex` char(1) DEFAULT '女' COMMENT '性别',
  `user_birthday` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生日',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `delete_status` char(1) DEFAULT '1' COMMENT '状态 0废除，1激活',
  `operator` bigint(20) NOT NULL COMMENT '操作者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_sys_user_name` (`user_name`),
  KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'ztt', '12345', NULL, NULL, '小美', '', '13206493505', NULL, '女', '2018-04-09 06:35:32', '2018-04-09 06:35:32', '2018-04-09 06:35:32', '1', 1);
INSERT INTO `sys_user` VALUES (2, 'zc', '12345', NULL, NULL, '小笨', '', '13296390792', NULL, '女', '2017-01-18 14:39:23', '2018-04-09 06:35:32', '2018-04-09 06:35:32', '1', 1);
INSERT INTO `sys_user` VALUES (3, 'zxa', '12345', NULL, NULL, '小绿', '', '17853252520', NULL, '女', '2017-01-18 15:25:08', '2018-04-09 06:35:32', '2018-04-09 06:35:32', '1', 1);
INSERT INTO `sys_user` VALUES (4, 'pll', '12345', NULL, NULL, '小黄', '', '17854268056', NULL, '女', '2017-01-18 15:25:47', '2018-04-09 06:35:32', '2018-04-09 06:35:32', '1', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` int(20) NOT NULL COMMENT '角色id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `operator` bigint(20) NOT NULL COMMENT '操作者',
  `delete_status` char(1) DEFAULT '1' COMMENT '状态 0删除，1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1, 1, '2018-04-09 06:35:32', '2018-04-09 06:35:32', 1, '1');
INSERT INTO `sys_user_role` VALUES (2, 2, 1, '2018-04-09 06:35:32', '2018-04-09 06:35:32', 1, '1');
INSERT INTO `sys_user_role` VALUES (3, 3, 1, '2018-04-09 06:35:32', '2018-04-09 06:35:32', 1, '1');
INSERT INTO `sys_user_role` VALUES (4, 4, 1, '2018-04-09 06:35:32', '2018-04-09 06:35:32', 1, '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

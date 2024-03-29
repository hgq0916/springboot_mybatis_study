/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50644
 Source Host           : localhost:3306
 Source Schema         : mybatis_test

 Target Server Type    : MySQL
 Target Server Version : 50644
 File Encoding         : 65001

 Date: 12/08/2019 18:23:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `country` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `province` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_address
-- ----------------------------
INSERT INTO `tb_address` VALUES (11, '上海', '中国', '上海');
INSERT INTO `tb_address` VALUES (22, '武汉', '中国', '湖北');
INSERT INTO `tb_address` VALUES (33, '信阳', '中国', '河南');
INSERT INTO `tb_address` VALUES (35, '郑州', '中国', '河南');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` smallint(64) NULL DEFAULT NULL COMMENT '年龄',
  `birthday` timestamp(0) NULL DEFAULT NULL COMMENT '生日',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `address_id` bigint(20) NULL DEFAULT NULL COMMENT '地址id',
  `create_date` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '张三', 35, '2008-09-16 00:00:00', 'hu@qq.com', '156989989', 11, '2019-08-05 21:50:01', '2019-08-07 21:46:11');
INSERT INTO `tb_user` VALUES (2, '狄仁杰', 50, '1988-09-16 00:00:00', 'di@qq.com', '158789989', 22, '2019-07-05 21:50:01', '2019-08-05 22:20:48');
INSERT INTO `tb_user` VALUES (3, '诸葛亮', 54, '2001-09-16 00:00:00', 'zhu@qq.com', '158989989', 22, '2019-09-05 21:50:01', '2019-08-07 21:46:17');
INSERT INTO `tb_user` VALUES (4, '王五', 30, '2012-01-01 04:12:12', 'wangwu@qq.com', '18789786789', 35, '2019-08-12 08:03:10', '2019-08-12 17:57:25');

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : this
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : esbookmark

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 22/06/2024 15:58:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
  `sex` int(2) NULL DEFAULT 0 COMMENT '性别 1男 2女',
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证号',
  `status` int(2) NULL DEFAULT 0 COMMENT '是否禁用,0 否 1 禁用',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES (1, 'http://images:19205/82e8a6ef-c54e-4ecf-a456-a89d81b4fa6a.jpg', '用户1', 'user1', '123456', '12345678901', 2, '123456789012345678', 0, '2021-01-10 16:00:00', '2024-06-17 12:07:23');
INSERT INTO `administrator` VALUES (2, 'http://images:19205/82e8a6ef-c54e-4ecf-a456-a89d81b4fa6a.jpg', '管理员2', 'admin2', '123456', '12345678902', 1, '123456789012345679', 0, '2021-01-01 00:00:00', '2021-01-01 00:00:00');
INSERT INTO `administrator` VALUES (3, 'http://images:19205/82e8a6ef-c54e-4ecf-a456-a89d81b4fa6a.jpg', '用户3', 'mjq', '123456', '15638098333', 2, '144525488965321547', 0, '2024-06-16 19:58:38', '2024-06-16 22:35:26');

-- ----------------------------
-- Table structure for bm_type
-- ----------------------------
DROP TABLE IF EXISTS `bm_type`;
CREATE TABLE `bm_type`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '书签类型',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '书签类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bm_type
-- ----------------------------
INSERT INTO `bm_type` VALUES (1, '未分类', '2024-05-28 17:41:28', '2024-05-28 17:41:28');
INSERT INTO `bm_type` VALUES (2, '生活类', '2024-05-28 17:41:28', '2024-05-28 17:41:28');
INSERT INTO `bm_type` VALUES (3, '工作类', '2024-05-28 17:41:28', '2024-05-28 17:41:28');
INSERT INTO `bm_type` VALUES (4, '情感类', '2024-05-28 17:41:28', '2024-05-28 17:41:28');
INSERT INTO `bm_type` VALUES (5, '游戏类', '2024-05-28 17:41:28', '2024-05-28 17:41:28');
INSERT INTO `bm_type` VALUES (6, '工具类', '2024-05-28 17:42:15', '2024-05-28 17:42:15');
INSERT INTO `bm_type` VALUES (7, '影视类', '2024-05-28 17:42:15', '2024-05-28 17:42:15');
INSERT INTO `bm_type` VALUES (8, '音乐类', '2024-05-28 17:42:15', '2024-05-28 17:42:15');

-- ----------------------------
-- Table structure for bookmark
-- ----------------------------
DROP TABLE IF EXISTS `bookmark`;
CREATE TABLE `bookmark`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(10) NOT NULL COMMENT '上传用户ID',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'url',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '网页图片',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述信息',
  `type_id` int(10) NULL DEFAULT 1 COMMENT '书签类型ID',
  `status` int(2) NULL DEFAULT 0 COMMENT '是否共享,0 私密 1 共享',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '书签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bookmark
-- ----------------------------
INSERT INTO `bookmark` VALUES (1, 1, '这是一个描述 1', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 1', 1, 0, '2023-09-15 10:00:00', '2023-09-15 10:00:00');
INSERT INTO `bookmark` VALUES (2, 2, '这是一个描述2', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 2', 2, 1, '2023-09-15 11:00:00', '2023-09-15 11:00:00');
INSERT INTO `bookmark` VALUES (3, 2, '这是一个描述 1', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 3', 3, 0, '2023-09-15 12:00:00', '2023-09-15 12:00:00');
INSERT INTO `bookmark` VALUES (4, 1, '这是一个描述 4', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 4', 4, 1, '2023-09-15 13:00:00', '2023-09-15 13:00:00');
INSERT INTO `bookmark` VALUES (5, 2, '这是一个描述 1', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 5', 5, 0, '2023-09-15 14:00:00', '2023-09-15 14:00:00');
INSERT INTO `bookmark` VALUES (6, 1, '这是一个描述 6', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 6', 1, 1, '2023-09-15 15:00:00', '2023-09-15 15:00:00');
INSERT INTO `bookmark` VALUES (7, 1, '这是一个描述 1', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 7', 2, 0, '2023-09-15 16:00:00', '2023-09-15 16:00:00');
INSERT INTO `bookmark` VALUES (8, 2, '这是一个描述 8', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 8', 3, 1, '2023-09-15 17:00:00', '2023-09-15 17:00:00');
INSERT INTO `bookmark` VALUES (9, 2, '这是一个描述 1', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 9', 4, 0, '2023-09-15 18:00:00', '2023-09-15 18:00:00');
INSERT INTO `bookmark` VALUES (10, 1, '这是一个描述 10', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 10', 5, 1, '2023-09-15 19:00:00', '2023-09-15 19:00:00');
INSERT INTO `bookmark` VALUES (11, 2, '这是一个描述 1', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 11', 2, 0, '2023-09-15 20:00:00', '2023-09-15 20:00:00');
INSERT INTO `bookmark` VALUES (12, 1, '这是一个描述 12', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 12', 3, 1, '2023-09-15 21:00:00', '2023-09-15 21:00:00');
INSERT INTO `bookmark` VALUES (13, 1, '这是一个描述 1', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 13', 4, 0, '2023-09-15 22:00:00', '2023-09-15 22:00:00');
INSERT INTO `bookmark` VALUES (16, 2, '这是一个描述 16', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 16', 2, 1, '2023-09-16 01:00:00', '2023-09-16 01:00:00');
INSERT INTO `bookmark` VALUES (17, 1, '这是一个描述 1', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 17', 3, 0, '2023-09-16 02:00:00', '2023-09-16 02:00:00');
INSERT INTO `bookmark` VALUES (18, 2, '这是一个描述 18', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 18', 4, 1, '2023-09-16 03:00:00', '2023-09-16 03:00:00');
INSERT INTO `bookmark` VALUES (19, 1, '这是一个描述 1', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 19', 5, 0, '2023-09-16 04:00:00', '2023-09-16 04:00:00');
INSERT INTO `bookmark` VALUES (20, 2, '这是一个描述 20', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个', 1, 1, '2023-09-16 05:00:00', '2024-06-17 12:46:19');
INSERT INTO `bookmark` VALUES (27, 10, '这是一个描述 27', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个', 1, 1, '2024-06-17 23:27:54', '2024-06-17 23:27:54');
INSERT INTO `bookmark` VALUES (28, 1, '这是一个描述 1', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 1', 1, 0, '2024-06-17 23:28:57', '2024-06-17 23:28:57');
INSERT INTO `bookmark` VALUES (29, 26, '这是一个描述 1', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 1', 1, 0, '2024-06-17 23:31:26', '2024-06-17 23:31:26');
INSERT INTO `bookmark` VALUES (30, 12, '这是一个描述 1', 'https://www.bilibili.com/', 'http://images:19205/2becb2aa-698f-48ba-9084-19e19cd81ee7.jpg', '这是一个描述 1', 1, 0, '2024-06-18 08:27:14', '2024-06-18 08:27:14');
INSERT INTO `bookmark` VALUES (34, 27, '哔哩哔哩', 'https://www.bilibili.com/', 'http://images:19205/12f2657e-d09a-4c3e-8d39-7a4bd755c3f5.jpg', 'B站', 2, 0, '2024-06-18 09:14:23', '2024-06-18 09:14:23');
INSERT INTO `bookmark` VALUES (48, 3, '测试48', 'www.jqjkds.com', 'http://images:19205/f1b5a5eb-5c41-4b39-9176-587b63bee08e.jpg', 'ces', 4, 1, '2024-06-19 16:13:20', '2024-06-19 16:13:20');
INSERT INTO `bookmark` VALUES (51, 12, '测试', 'www.jqjkds.com', 'http://images:19205/f1b5a5eb-5c41-4b39-9176-587b63bee08e.jpg', 'ces', 4, 0, '2024-06-21 15:50:24', '2024-06-21 15:50:24');
INSERT INTO `bookmark` VALUES (52, 12, '测试', 'www.jqjkds.com', 'http://images:19205/f1b5a5eb-5c41-4b39-9176-587b63bee08e.jpg', 'ces', 4, 0, '2024-06-21 15:54:02', '2024-06-21 15:54:02');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
  `sex` int(2) NULL DEFAULT 0 COMMENT '性别 1男 2女',
  `status` int(2) NULL DEFAULT 0 COMMENT '是否禁用,0 否 1 禁用',
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证号',
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '普通用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'user1', '123456', '用户1', '12345678911', 1, 0, '123456789012345678', 'http://images:19205/1d3b0abc-e25c-40c2-9d0e-05b50f3e46b3.jpg', '2021-01-01 00:00:00', '2024-06-17 11:43:59');
INSERT INTO `user` VALUES (2, 'user5', '123456', '用户2', '12345678912', 1, 0, '123456789012345679', 'http://images:19205/1d3b0abc-e25c-40c2-9d0e-05b50f3e46b3.jpg', '2021-01-01 00:00:00', '2024-06-16 23:27:45');
INSERT INTO `user` VALUES (12, 'mjq', '123456', '马家奇', NULL, 1, 0, '15615645645612156', 'http://images:19205/f2c93efa-85f5-449f-a657-ddf330992a47.jpg', '2024-06-16 23:44:36', '2024-06-19 16:09:43');
INSERT INTO `user` VALUES (33, 'mjq222', '123456', NULL, NULL, NULL, 0, NULL, NULL, '2024-06-19 16:05:27', '2024-06-19 16:05:27');
INSERT INTO `user` VALUES (34, 'mjq1', '123456', NULL, NULL, NULL, 0, NULL, NULL, '2024-06-21 22:14:55', '2024-06-21 22:14:55');

SET FOREIGN_KEY_CHECKS = 1;

/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : beisi

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-02-09 11:45:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` varchar(255) NOT NULL,
  `pname` varchar(255) DEFAULT NULL,
  `pathUrl` varchar(255) DEFAULT NULL,
  `sortNum` int(11) NOT NULL COMMENT '排序',
  `add_date` datetime DEFAULT NULL,
  `up_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('268793129a5c4ef9861c4b79e9d342f7', 'logout', '/logout.html', '2', '2019-01-28 12:24:45', null);
INSERT INTO `t_permission` VALUES ('45b4d55b15c84dfc91dd627189d4ffbd', 'authc', '/admin/**', '6', '2019-01-28 12:26:02', null);
INSERT INTO `t_permission` VALUES ('99ec8ff1470d41e3bae7755240a90f41', 'p:userlist', '/admin/userManager.html', '3', '2019-01-28 12:25:09', null);
INSERT INTO `t_permission` VALUES ('b5e833fa35d94e9d884fe53f8461a9df', 'anon', '/**', '7', '2019-01-28 12:26:21', null);
INSERT INTO `t_permission` VALUES ('d5fde8914cc14282b9b0d9e2178c7433', 'user', '/admin/main.html', '5', '2019-01-28 12:25:47', null);
INSERT INTO `t_permission` VALUES ('d8a6581267054211b452ae57df5f5e29', 'p:adduser', '/admin/addUser.html', '4', '2019-01-28 12:25:28', null);
INSERT INTO `t_permission` VALUES ('e5bc14bc25d94365b9569ab4f1a3bdcd', 'anon', '/login.html', '1', '2019-01-28 12:24:27', null);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(255) NOT NULL,
  `rname` varchar(255) DEFAULT NULL,
  `rcode` varchar(255) DEFAULT NULL,
  `add_date` datetime DEFAULT NULL,
  `up_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('10f2863479874c1490f4c00b46184765', '超级管理员', 'admin', '2019-01-28 12:23:05', null);
INSERT INTO `t_role` VALUES ('10f2863479874c1490f4c99b46184765', '管理员', 'root', '2019-02-08 11:39:48', null);

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` varchar(255) NOT NULL,
  `roleId` varchar(255) DEFAULT NULL,
  `perId` varchar(255) DEFAULT NULL,
  `add_date` datetime DEFAULT NULL,
  `up_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('93474092dcd0458590e1bc4b905eb2ee', '10f2863479874c1490f4c00b46184765', 'd8a6581267054211b452ae57df5f5e29', '2019-01-28 12:28:51', null);
INSERT INTO `t_role_permission` VALUES ('f97d454373f84fa1b6126b6aaef10c48', '10f2863479874c1490f4c00b46184765', '99ec8ff1470d41e3bae7755240a90f41', '2019-01-28 12:27:21', null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `enable` int(1) DEFAULT NULL COMMENT '是否激活',
  `add_date` datetime DEFAULT NULL COMMENT '创建时间',
  `up_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('833de7c8bf0f4328b75d709002705c49', 'test', '22f4fdae4b5448b69a07a25352757415', 'Adrianzpc@163.com', '13333333333', '1', '2019-02-09 11:16:37', null);
INSERT INTO `t_user` VALUES ('9c06bcaaa1794c74ad395cd0ec5d6a25', 'admin', 'a7c4a89393dd48e3b755211351c49ffa', 'pop@163.com', '18888888888', '1', '2019-01-17 09:01:03', null);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` varchar(255) NOT NULL,
  `userId` varchar(255) DEFAULT NULL,
  `roleId` varchar(255) DEFAULT NULL,
  `add_date` datetime DEFAULT NULL,
  `up_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('7de8eda4ab904637a05cdf37e00e6871', '833de7c8bf0f4328b75d709002705c49', '10f2863479874c1490f4c99b46184765', '2019-02-09 11:16:37', null);
INSERT INTO `t_user_role` VALUES ('8f3b0da967654103b073fe1424e19787', '9c06bcaaa1794c74ad395cd0ec5d6a25', '10f2863479874c1490f4c00b46184765', '2019-01-28 12:23:51', null);
INSERT INTO `t_user_role` VALUES ('8f3b0da967654103b073fe1424e19799', '9c06bcaaa1794c74ad395cd0ec5d6a25', '10f2863479874c1490f4c99b46184765', '2019-02-08 11:40:35', null);

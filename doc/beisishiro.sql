/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : beisishiro

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-02-19 18:09:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_schedule_job_list
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule_job_list`;
CREATE TABLE `t_schedule_job_list` (
  `JOB_ID` varchar(50) NOT NULL COMMENT '任务ID',
  `BEAN_NAME` varchar(255) DEFAULT NULL COMMENT 'spring bean名称',
  `METHOD_NAME` varchar(255) DEFAULT NULL COMMENT '方法名',
  `THE_PARAMS` varchar(2000) DEFAULT NULL COMMENT '参数',
  `CRON_EXPRESSION` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `JOB_STATUS` int(11) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `JOB_REMARK` varchar(255) DEFAULT NULL COMMENT '任务备注',
  `CREATE_USER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(50) DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`JOB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务列表';

-- ----------------------------
-- Records of t_schedule_job_list
-- ----------------------------
INSERT INTO `t_schedule_job_list` VALUES ('1', 'testTask', 'test', 'fomagic', '0 0/1 * * * ?', '1', '有参数测试', '2016-12-01 23:16:46', null, null, null);
INSERT INTO `t_schedule_job_list` VALUES ('2', 'testTask', 'test2', '', '0 0/1 * * * ?', '1', '无参数测试', '2018-01-02 14:44:34', null, null, null);

-- ----------------------------
-- Table structure for t_schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule_job_log`;
CREATE TABLE `t_schedule_job_log` (
  `LOG_ID` varchar(50) NOT NULL COMMENT '日志ID',
  `JOB_ID` varchar(50) NOT NULL COMMENT '任务ID',
  `BEAN_NAME` varchar(255) DEFAULT NULL COMMENT 'spring bean名称',
  `METHOD_NAME` varchar(255) DEFAULT NULL COMMENT '方法名',
  `THE_PARAMS` varchar(2000) DEFAULT NULL COMMENT '参数',
  `JOB_STATUS` int(11) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `ERROR_MSG` varchar(4000) DEFAULT NULL COMMENT '失败信息',
  `THE_TIMES` int(11) NOT NULL COMMENT '耗时',
  `CREATE_USER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Records of t_schedule_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log` (
  `LOG_ID` varchar(50) NOT NULL COMMENT '行ID',
  `C_USR_NAME` varchar(50) DEFAULT NULL COMMENT '用户名',
  `C_USR_OPERATION` varchar(255) DEFAULT NULL COMMENT '用户操作',
  `METHOD_NAME` varchar(255) DEFAULT NULL COMMENT '请求方法',
  `THE_PARAMS` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `THE_TIMES` int(11) NOT NULL COMMENT '执行时长(毫秒)',
  `THE_IP` varchar(100) DEFAULT NULL COMMENT 'IP地址',
  `CREATE_USER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(50) DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改的当前时间',
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `MENU_ID` varchar(50) NOT NULL COMMENT '菜单ID',
  `PARENT_ID` varchar(50) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `MENU_NAME` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `MENU_URL` varchar(255) DEFAULT NULL COMMENT '菜单URL',
  `MENU_PERMS` varchar(1000) DEFAULT NULL COMMENT '权限（多个逗号分隔，如：user:add,user:delete）',
  `MENU_TYPE` int(11) DEFAULT NULL COMMENT '类型  0：目录   1：菜单   2：按钮',
  `MENU_ICON` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `ORDER_NUM` int(11) DEFAULT NULL COMMENT '排序',
  `CREATE_USER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(50) DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改的当前时间',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单管理表';

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('1', '0', '系统管理', '', '', '0', 'fa fa-cog', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('10', '6', '删除', '', 'sys:schedule:delete', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('11', '6', '暂停', '', 'sys:schedule:pause', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('12', '6', '恢复', '', 'sys:schedule:resume', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('13', '6', '立即执行', '', 'sys:schedule:run', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('14', '6', '日志列表', '', 'sys:schedule:log', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('15', '2', '查看', '', 'sys:user:list,sys:user:info', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('16', '2', '新增', '', 'sys:user:save,sys:role:select', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('17', '2', '修改', '', 'sys:user:update,sys:role:select', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('18', '2', '删除', '', 'sys:user:delete', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('19', '3', '查看', '', 'sys:role:list,sys:role:info', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('2', '1', '管理员列表', 'sys/user', '', '1', 'fa fa-user', '1', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('20', '3', '新增', '', 'sys:role:save,sys:menu:list', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('21', '3', '修改', '', 'sys:role:update,sys:menu:list', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('22', '3', '删除', '', 'sys:role:delete', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('23', '4', '查看', '', 'sys:menu:list,sys:menu:info', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('24', '4', '新增', '', 'sys:menu:save,sys:menu:select', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('25', '4', '修改', '', 'sys:menu:update,sys:menu:select', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('26', '4', '删除', '', 'sys:menu:delete', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('27', '1', '参数管理', 'sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'fa fa-sun-o', '6', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('28', '1', '系统日志', 'sys/log', 'sys:log:list', '1', 'fa fa-file-text-o', '7', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('29', '1', '文件上传', 'sys/oss', 'sys:oss:all', '1', 'fa fa-file-image-o', '6', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('3', '1', '角色管理', 'sys/role', '', '1', 'fa fa-user-secret', '2', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('4', '1', '菜单管理', 'sys/menu', '', '1', 'fa fa-th-list', '3', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('5', '1', 'SQL监控', 'druid/sql.html', '', '1', 'fa fa-bug', '4', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('6', '1', '定时任务', 'sys/schedule', '', '1', 'fa fa-tasks', '5', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('7', '6', '查看', '', 'sys:schedule:list,sys:schedule:info', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('8', '6', '新增', '', 'sys:schedule:save', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);
INSERT INTO `t_sys_menu` VALUES ('9', '6', '修改', '', 'sys:schedule:update', '2', '', '0', '1', '2019-02-19 16:22:43', null, null);

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `ROLE_ID` varchar(50) NOT NULL COMMENT '用户ID',
  `ROLE_NAME` varchar(100) DEFAULT NULL COMMENT '角色名',
  `ROLE_REMARK` varchar(100) DEFAULT NULL COMMENT '备注',
  `CREATE_USER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(50) DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改的当前时间',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('5c0f4890043648ccad095c06d2a34d00', '平台管理员', '平台管理员', '1', '2019-02-19 10:07:15', null, null);

-- ----------------------------
-- Table structure for t_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_menu`;
CREATE TABLE `t_sys_role_menu` (
  `ROW_ID` varchar(50) NOT NULL COMMENT '行ID',
  `ROLE_ID` varchar(50) DEFAULT NULL COMMENT '角色ID',
  `MENU_ID` varchar(50) DEFAULT NULL COMMENT '菜单ID',
  `CREATE_USER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(50) DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改的当前时间',
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of t_sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `C_USR_ID` varchar(50) NOT NULL COMMENT '用户ID',
  `C_USR_NAME` varchar(50) NOT NULL COMMENT '用户名',
  `C_USR_PASSWORD` varchar(100) DEFAULT NULL COMMENT '密码',
  `C_USR_REAL_NAME` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `C_USR_EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `C_USR_PHONE` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `C_USR_STATUS` int(11) DEFAULT NULL COMMENT '用户状态  0：禁用  1：正常',
  `CREATE_USER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(50) DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改的当前时间',
  PRIMARY KEY (`C_USR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理员或用户';

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'admin', '6127d5ef08ce6ec13c780fa2063cff79', '管理员', 'pop@163.com', '13333333333', '1', '1', '2019-02-19 10:06:26', null, null);

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `ROW_ID` varchar(50) NOT NULL COMMENT '行ID',
  `C_USR_ID` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(50) DEFAULT NULL COMMENT '角色ID',
  `CREATE_USER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(50) DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改的当前时间',
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色的对应关系';

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES ('074026804ac346d69ff1f23bbbf45d42', '1', '5c0f4890043648ccad095c06d2a34d00', '1', '2019-02-19 10:08:11', null, null);

/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : smarterp

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2016-10-09 23:47:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `phone` char(40) default NULL,
  `status` int(11) default NULL,
  `email` varchar(255) default NULL,
  `log` varchar(255) default NULL,
  `createTime` varchar(255) default NULL,
  `updateTime` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', '201', '王云鹏', 'root', '187777772', '1', '3333@qq.com', null, null, null);
INSERT INTO `user` VALUES ('4', '202', '王明旭', 'root', '1877772377', '1', null, null, null, null);
INSERT INTO `user` VALUES ('5', '203', '王程菲', 'root', '18777123777', '1', null, null, null, null);
INSERT INTO `user` VALUES ('6', '204', '王筱希', 'root', '1873777277', '1', null, null, null, null);
INSERT INTO `user` VALUES ('7', '205', '杨梦滢', 'root', '187777277', '1', null, null, null, null);
INSERT INTO `user` VALUES ('8', '206', '杨程菲', 'root', '18777777', '1', null, null, null, null);
INSERT INTO `user` VALUES ('9', '207', '杨亚娟', 'root', '1877227777', '1', null, null, null, null);
INSERT INTO `user` VALUES ('10', '208', '杨玉伦', 'root', '18777777', '1', null, null, null, null);
INSERT INTO `user` VALUES ('11', '209', '杨志杰', 'root', '187177577', '1', null, null, null, null);
INSERT INTO `user` VALUES ('12', '301', '罗美佳', 'root', '18777777', '1', null, null, null, null);
INSERT INTO `user` VALUES ('13', '302', '罗明轩', 'root', '187775777', '1', null, null, null, null);
INSERT INTO `user` VALUES ('14', '303', '罗楠', 'root', '18777777', '1', null, null, null, null);
INSERT INTO `user` VALUES ('15', '304', '罗辰阳', 'root', '181777777', '1', null, null, null, null);
INSERT INTO `user` VALUES ('16', '305', '左怡雪', 'root', '187775777', '1', null, null, null, null);
INSERT INTO `user` VALUES ('17', '211', '左宇琪', 'root', '18777777', '1', null, null, null, null);
INSERT INTO `user` VALUES ('18', '221', '左天毓', 'root', '1877177477', '1', null, null, null, null);
INSERT INTO `user` VALUES ('19', '231', '左永军', 'root', '18777777', '1', null, null, null, null);
INSERT INTO `user` VALUES ('20', '241', '张小刚', 'root', '187773777', '1', null, null, null, null);
INSERT INTO `user` VALUES ('21', '251', '张恩阅', 'root', '18777777', '1', null, null, null, null);
INSERT INTO `user` VALUES ('22', '261', '张宏雷', 'root', '187727777', '1', null, null, null, null);
INSERT INTO `user` VALUES ('23', '271', '张文敏', 'root', '18777777', '1', null, null, null, null);
INSERT INTO `user` VALUES ('630', '2028', '超级管理员', '123456', '18677112630', '1', null, null, null, null);

/*
MySQL Data Transfer
Source Host: localhost
Source Database: smarterp
Target Host: localhost
Target Database: smarterp
Date: 2016/8/14 23:30:20
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(200) default NULL,
  `name` varchar(200) default NULL,
  `password` varchar(200) default NULL,
  `sex` int(11) default '1',
  `phone` char(20) default NULL,
  `level` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `user` VALUES ('1', '2028', 'zhangsan', '123456', '1', '18677112630', '1');
INSERT INTO `user` VALUES ('2', '201', 'lisi', 'root', '0', '18777777', '2');
INSERT INTO `user` VALUES ('3', '201', '王云鹏', 'root', '1', '187777772', '2');
INSERT INTO `user` VALUES ('4', '202', '王明旭', 'root', '1', '1877772377', '2');
INSERT INTO `user` VALUES ('5', '203', '王程菲', 'root', '0', '18777123777', '2');
INSERT INTO `user` VALUES ('6', '204', '王筱希', 'root', '0', '1873777277', '2');
INSERT INTO `user` VALUES ('7', '205', '杨梦滢', 'root', '0', '187777277', '2');
INSERT INTO `user` VALUES ('8', '206', '杨程菲', 'root', '0', '18777777', '2');
INSERT INTO `user` VALUES ('9', '207', '杨亚娟', 'root', '0', '1877227777', '2');
INSERT INTO `user` VALUES ('10', '208', '杨玉伦', 'root', '1', '18777777', '2');
INSERT INTO `user` VALUES ('11', '209', '杨志杰', 'root', '1', '187177577', '2');
INSERT INTO `user` VALUES ('12', '301', '罗美佳', 'root', '0', '18777777', '2');
INSERT INTO `user` VALUES ('13', '302', '罗明轩', 'root', '1', '187775777', '2');
INSERT INTO `user` VALUES ('14', '303', '罗楠', 'root', '1', '18777777', '2');
INSERT INTO `user` VALUES ('15', '304', '罗辰阳', 'root', '1', '181777777', '2');
INSERT INTO `user` VALUES ('16', '305', '左怡雪', 'root', '0', '187775777', '2');
INSERT INTO `user` VALUES ('17', '211', '左宇琪', 'root', '0', '18777777', '2');
INSERT INTO `user` VALUES ('18', '221', '左天毓', 'root', '0', '1877177477', '2');
INSERT INTO `user` VALUES ('19', '231', '左永军', 'root', '0', '18777777', '2');
INSERT INTO `user` VALUES ('20', '241', '张小刚', 'root', '0', '187773777', '2');
INSERT INTO `user` VALUES ('21', '251', '张恩阅', 'root', '0', '18777777', '2');
INSERT INTO `user` VALUES ('22', '261', '张宏雷', 'root', '0', '187727777', '2');
INSERT INTO `user` VALUES ('23', '271', '张文敏', 'root', '0', '18777777', '2');

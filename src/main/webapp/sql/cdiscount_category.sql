/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : smarterp

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2016-08-24 20:48:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cdiscount_category
-- ----------------------------
DROP TABLE IF EXISTS `cdiscount_category`;
CREATE TABLE `cdiscount_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `categoryCode` char(50) DEFAULT NULL,
  `isParent` int(4) DEFAULT NULL,
  `name` char(100) DEFAULT NULL,
  `categoryLevel` int(4) DEFAULT NULL,
  `updateTime` char(40) DEFAULT NULL,
  `cdiscountApiId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

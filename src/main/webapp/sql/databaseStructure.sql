/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : smarterp

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2016-11-10 15:36:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cdiscount_api_config
-- ----------------------------
DROP TABLE IF EXISTS `cdiscount_api_config`;
CREATE TABLE `cdiscount_api_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `apiAccount` varchar(255) DEFAULT NULL,
  `apiPassword` varchar(255) DEFAULT NULL,
  `receivablesEmail` varchar(255) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `systemLog` text,
  `createDate` varchar(255) DEFAULT NULL,
  `lastUpdateTime` varchar(255) DEFAULT NULL,
  `closeStatus` int(11) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `tokenTimeOut` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cdiscount_category
-- ----------------------------
DROP TABLE IF EXISTS `cdiscount_category`;
CREATE TABLE `cdiscount_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `categoryCode` char(50) DEFAULT NULL,
  `isParent` int(4) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `categoryLevel` int(4) DEFAULT NULL,
  `updateTime` char(40) DEFAULT NULL,
  `apiId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cdiscount_defaults_value
-- ----------------------------
DROP TABLE IF EXISTS `cdiscount_defaults_value`;
CREATE TABLE `cdiscount_defaults_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `templateName` varchar(255) DEFAULT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `dea` double DEFAULT NULL,
  `vat` double DEFAULT NULL,
  `ecoPart` double DEFAULT NULL,
  `stockingTime` int(11) DEFAULT NULL,
  `isDefaults` int(11) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cdiscount_ean
-- ----------------------------
DROP TABLE IF EXISTS `cdiscount_ean`;
CREATE TABLE `cdiscount_ean` (
  `id` int(11) NOT NULL,
  `ean` varchar(255) DEFAULT NULL,
  `apiId` int(11) DEFAULT NULL,
  `isUsed` int(11) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `usedTime` varchar(255) DEFAULT NULL,
  `createTime` varchar(255) DEFAULT NULL,
  `sku` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cdiscount_publish
-- ----------------------------
DROP TABLE IF EXISTS `cdiscount_publish`;
CREATE TABLE `cdiscount_publish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apiId` int(11) DEFAULT NULL,
  `productId` int(11) DEFAULT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `ean` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `productKind` varchar(255) DEFAULT NULL,
  `shortLabel` varchar(255) DEFAULT NULL,
  `longLabel` varchar(255) DEFAULT NULL,
  `navigation` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `categoryCode` varchar(255) DEFAULT NULL,
  `categoryName` varchar(255) DEFAULT NULL,
  `marketingDescription` text,
  `stockQty` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `vat` double DEFAULT NULL,
  `dea` double DEFAULT NULL,
  `ecoPart` double DEFAULT NULL,
  `preparationTime` int(11) DEFAULT NULL,
  `productCondition` varchar(255) DEFAULT NULL,
  `publishStatus` int(11) DEFAULT NULL,
  `creator` int(11) NOT NULL,
  `createTime` varchar(255) DEFAULT NULL,
  `updateTime` varchar(255) DEFAULT NULL,
  `log` text,
  `mainImage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`creator`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cdiscount_publish_image
-- ----------------------------
DROP TABLE IF EXISTS `cdiscount_publish_image`;
CREATE TABLE `cdiscount_publish_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `publishId` int(11) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for defaults_delivery_mode
-- ----------------------------
DROP TABLE IF EXISTS `defaults_delivery_mode`;
CREATE TABLE `defaults_delivery_mode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deliveryMode` varchar(255) DEFAULT NULL,
  `addShippingCharges` double DEFAULT NULL,
  `shippingCharges` double DEFAULT NULL,
  `defaultsId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for delivery_mode_infor
-- ----------------------------
DROP TABLE IF EXISTS `delivery_mode_infor`;
CREATE TABLE `delivery_mode_infor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `deliveryModeType` int(11) DEFAULT NULL,
  `modeNameFR` varchar(255) DEFAULT NULL,
  `modeNameEN` varchar(255) DEFAULT NULL,
  `apiId` int(11) DEFAULT NULL,
  `updateTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dictionary_type
-- ----------------------------
DROP TABLE IF EXISTS `dictionary_type`;
CREATE TABLE `dictionary_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `moduleId` varchar(255) DEFAULT NULL,
  `moduleName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sku` varchar(255) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `createTime` varchar(255) DEFAULT NULL,
  `updateTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_image
-- ----------------------------
DROP TABLE IF EXISTS `product_image`;
CREATE TABLE `product_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for publish_delivery_mode
-- ----------------------------
DROP TABLE IF EXISTS `publish_delivery_mode`;
CREATE TABLE `publish_delivery_mode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deliveryMode` varchar(255) DEFAULT NULL,
  `addShippingCharges` double DEFAULT NULL,
  `shippingCharges` double DEFAULT NULL,
  `publishId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for script_config
-- ----------------------------
DROP TABLE IF EXISTS `script_config`;
CREATE TABLE `script_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crontab` varchar(255) DEFAULT NULL,
  `scriptUrl` varchar(255) DEFAULT NULL,
  `scriptType` int(11) DEFAULT NULL,
  `randomRange` int(11) DEFAULT NULL,
  `isOpened` int(11) DEFAULT NULL,
  `creatorId` int(11) DEFAULT NULL,
  `scriptName` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `createTime` varchar(255) DEFAULT NULL,
  `updateTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` char(40) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `log` text,
  `createTime` varchar(255) DEFAULT NULL,
  `updateTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for system_prompt
-- ----------------------------
DROP TABLE IF EXISTS `system_prompt`;
CREATE TABLE `system_prompt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `height` char(10) DEFAULT NULL,
  `width` char(10) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `content` text,
  `createTime` varchar(255) DEFAULT NULL,
  `updateTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for defaults_delivery_mode
-- ----------------------------
DROP TABLE IF EXISTS `defaults_delivery_mode`;
CREATE TABLE `defaults_delivery_mode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deliveryMode` varchar(255) DEFAULT NULL,
  `addShippingCharges` double DEFAULT NULL,
  `shippingCharges` double DEFAULT NULL,
  `defaultsId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cdiscount_defaults_value
-- ----------------------------
DROP TABLE IF EXISTS `cdiscount_defaults_value`;
CREATE TABLE `cdiscount_defaults_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `templateName` varchar(255) DEFAULT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `dea` double DEFAULT NULL,
  `vat` double DEFAULT NULL,
  `ecoPart` double DEFAULT NULL,
  `stockingTime` int(11) DEFAULT NULL,
  `isDefaults` int(11) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

alter table cdiscount_publish modify column description text;

/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : vendmachine

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-08-22 17:18:59
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `menuitem`
-- ----------------------------
DROP TABLE IF EXISTS `menuitem`;
CREATE TABLE `menuitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `menu_name` varchar(200) DEFAULT NULL COMMENT '菜单名',
  `uri` varchar(100) DEFAULT NULL COMMENT '访问地址',
  `icon` varchar(32) DEFAULT NULL COMMENT '菜单图标',
  `valid` varchar(2) DEFAULT NULL COMMENT '有效标志（0无效，1有效）',
  `create_time` datetime DEFAULT NULL,
  `updata_time` datetime DEFAULT NULL,
  `extend1` varchar(200) DEFAULT NULL,
  `extend2` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menuitem
-- ----------------------------

-- ----------------------------
-- Table structure for `vend_account`
-- ----------------------------
DROP TABLE IF EXISTS `vend_account`;
CREATE TABLE `vend_account` (
  `usercode` varchar(50) NOT NULL COMMENT '用户code',
  `own_amount` decimal(11,2) DEFAULT NULL COMMENT '账户余额',
  `moneyencrypt` varchar(50) DEFAULT NULL COMMENT '资金加密串',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `extend1` varchar(100) DEFAULT NULL,
  `extend2` varchar(100) DEFAULT NULL,
  `extend3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`usercode`),
  UNIQUE KEY `idx_usercode` (`usercode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户资金账户信息表';

-- ----------------------------
-- Records of vend_account
-- ----------------------------

-- ----------------------------
-- Table structure for `vend_accountdetail`
-- ----------------------------
DROP TABLE IF EXISTS `vend_accountdetail`;
CREATE TABLE `vend_accountdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usercode` varchar(50) DEFAULT NULL COMMENT '用户code',
  `type` varchar(10) DEFAULT NULL COMMENT '1,充值。2，提现',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '充值或提现金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `extend1` varchar(100) DEFAULT NULL,
  `extend2` varchar(100) DEFAULT NULL,
  `extend3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消费用户的充值提现记录表';

-- ----------------------------
-- Records of vend_accountdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `vend_ad`
-- ----------------------------
DROP TABLE IF EXISTS `vend_ad`;
CREATE TABLE `vend_ad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ad_name` varchar(100) DEFAULT NULL COMMENT '广告名字',
  `ad_type` varchar(100) DEFAULT NULL COMMENT '广告类型（0，文字 。1，图片。 2，视频',
  `ad_arealist` varchar(255) DEFAULT NULL COMMENT '广告投放几个机器还是整体',
  `ad_url` varchar(255) DEFAULT NULL COMMENT '广告地址',
  `ad_screen` int(11) DEFAULT NULL COMMENT '屏幕样式ID',
  `start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `extend1` varchar(100) DEFAULT NULL,
  `extend2` varchar(100) DEFAULT NULL,
  `extend3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告表';

-- ----------------------------
-- Records of vend_ad
-- ----------------------------

-- ----------------------------
-- Table structure for `vend_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `vend_coupon`;
CREATE TABLE `vend_coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_name` varchar(100) DEFAULT NULL COMMENT '优惠券名字',
  `coupon_info` varchar(600) DEFAULT NULL COMMENT '优惠券信息',
  `coupon_scale` decimal(11,2) DEFAULT NULL COMMENT '优惠比例',
  `goods_id` int(11) DEFAULT NULL COMMENT '对应商品id',
  `area_id` int(11) DEFAULT NULL COMMENT '投放区域',
  `start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `extend1` varchar(100) DEFAULT NULL,
  `extend2` varchar(100) DEFAULT NULL,
  `extend3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券表';

-- ----------------------------
-- Records of vend_coupon
-- ----------------------------

-- ----------------------------
-- Table structure for `vend_goods`
-- ----------------------------
DROP TABLE IF EXISTS `vend_goods`;
CREATE TABLE `vend_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(100) DEFAULT NULL COMMENT '商品名字',
  `pic` varchar(100) DEFAULT NULL COMMENT '图片',
  `price` decimal(11,2) DEFAULT NULL COMMENT '价格',
  `goods_info` varchar(600) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `extend1` varchar(100) DEFAULT NULL,
  `extend2` varchar(100) DEFAULT NULL,
  `extend3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='商品信息表';

-- ----------------------------
-- Records of vend_goods
-- ----------------------------
INSERT INTO vend_goods VALUES ('23', '雪碧', '/userfiles/pic/201708221429509354.jpg', '5.00', '雪碧美国发展速度领先的主要非酒精饮料产品之一', '2017-08-22 14:29:50', '2017-08-22 14:29:50', null, null, null);
INSERT INTO vend_goods VALUES ('24', '芬达', '/userfiles/pic/201708221431220330.jpg', '4.00', '芬达汽水（Fanta）是1940年代在欧洲开始风行的饮料', '2017-08-22 14:31:22', '2017-08-22 14:31:22', null, null, null);
INSERT INTO vend_goods VALUES ('25', '王老吉', '/userfiles/pic/201708221432088297.jpg', '6.00', '王老吉是王老吉凉茶的品牌，创立于清道光年间（1828年），被公认为凉茶始祖', '2017-08-22 14:32:08', '2017-08-22 14:32:08', null, null, null);

-- ----------------------------
-- Table structure for `vend_order`
-- ----------------------------
DROP TABLE IF EXISTS `vend_order`;
CREATE TABLE `vend_order` (
  `order_id` varchar(32) NOT NULL COMMENT '订单ID',
  `usercode` varchar(50) DEFAULT NULL COMMENT '购买用户',
  `shopusercode` varchar(50) DEFAULT NULL COMMENT '商家代码',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `num` int(11) DEFAULT NULL COMMENT '购买数量',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '订单金额',
  `orderstate` varchar(50) DEFAULT NULL COMMENT '订单状态（1，已支付。2，未支付）',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `extend1` varchar(200) DEFAULT NULL,
  `extend2` varchar(200) DEFAULT NULL,
  `extend3` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品订单表';

-- ----------------------------
-- Records of vend_order
-- ----------------------------

-- ----------------------------
-- Table structure for `vend_permission`
-- ----------------------------
DROP TABLE IF EXISTS `vend_permission`;
CREATE TABLE `vend_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '父ID',
  `permission_name` varchar(200) DEFAULT NULL COMMENT '权限名字',
  `permission_description` varchar(200) DEFAULT NULL COMMENT '权限描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `extend1` varchar(200) DEFAULT NULL,
  `extend2` varchar(200) DEFAULT NULL,
  `extend3` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_permission_name` (`permission_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理后台权限表\r\n\r\n\r\n';

-- ----------------------------
-- Records of vend_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `vend_qrcode_attend`
-- ----------------------------
DROP TABLE IF EXISTS `vend_qrcode_attend`;
CREATE TABLE `vend_qrcode_attend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qrcode_id` int(11) DEFAULT NULL COMMENT '关注的二维码ID',
  `usercode` varchar(50) DEFAULT NULL COMMENT '关注二维码的用户',
  `attend_time` datetime DEFAULT NULL COMMENT '关注时间',
  `extend1` varchar(100) DEFAULT NULL,
  `extend2` varchar(100) DEFAULT NULL,
  `extend3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家二维码关注情况表';

-- ----------------------------
-- Records of vend_qrcode_attend
-- ----------------------------

-- ----------------------------
-- Table structure for `vend_role`
-- ----------------------------
DROP TABLE IF EXISTS `vend_role`;
CREATE TABLE `vend_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `role_description` varchar(1000) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime DEFAULT NULL COMMENT '表创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '表最后一次更新时间',
  `extend1` varchar(200) DEFAULT NULL,
  `extend2` varchar(200) DEFAULT NULL,
  `state` tinyint(4) DEFAULT '1' COMMENT '表示角色当前的状态，0表示冻结，1表示正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='管理后台角色类型';

-- ----------------------------
-- Records of vend_role
-- ----------------------------
INSERT INTO vend_role VALUES ('2', '总后台', '最高权限用户', '2017-08-22 14:33:17', '2017-08-22 14:33:17', null, null, '1');
INSERT INTO vend_role VALUES ('3', '代理后台', '代理总后台管理一部分商户', '2017-08-22 14:33:44', '2017-08-22 14:33:44', null, null, '1');
INSERT INTO vend_role VALUES ('4', '商家', '普通商户', '2017-08-22 14:33:58', '2017-08-22 14:33:58', null, null, '1');
INSERT INTO vend_role VALUES ('5', '消费用户', '利用客户端购买商品的消费用户', '2017-08-22 14:34:25', '2017-08-22 14:34:25', null, null, '1');

-- ----------------------------
-- Table structure for `vend_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `vend_role_permission`;
CREATE TABLE `vend_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限ID',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `extend1` varchar(100) DEFAULT NULL,
  `extend2` varchar(100) DEFAULT NULL,
  `extend3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_role_permission` (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限对照表';

-- ----------------------------
-- Records of vend_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `vend_shop_qrcode`
-- ----------------------------
DROP TABLE IF EXISTS `vend_shop_qrcode`;
CREATE TABLE `vend_shop_qrcode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usercode` varchar(50) DEFAULT NULL COMMENT '对应商家code',
  `qrcode` varchar(100) DEFAULT NULL COMMENT '二维码信息',
  `atten_num` int(11) DEFAULT NULL COMMENT '关注数量',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `extend1` varchar(100) DEFAULT NULL,
  `extend2` varchar(100) DEFAULT NULL,
  `extend3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家二维码信息表';

-- ----------------------------
-- Records of vend_shop_qrcode
-- ----------------------------

-- ----------------------------
-- Table structure for `vend_syslog`
-- ----------------------------
DROP TABLE IF EXISTS `vend_syslog`;
CREATE TABLE `vend_syslog` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键,32未序列',
  `user_id` int(11) NOT NULL COMMENT '操作ID',
  `user_name` varchar(100) NOT NULL COMMENT '操作人',
  `oper_ip` varchar(30) NOT NULL COMMENT '操作会员IP',
  `oper_time` datetime NOT NULL COMMENT '操作日期',
  `oper_url` text COMMENT '操作URL',
  `oper_description` text COMMENT '操作描述',
  `oper_type` varchar(255) DEFAULT NULL COMMENT '类型',
  `EXTEND0` varchar(255) DEFAULT NULL COMMENT '扩展字段0',
  `EXTEND1` varchar(255) DEFAULT NULL COMMENT '扩展字段1',
  `EXTEND2` varchar(255) DEFAULT NULL COMMENT '扩展字段2',
  `EXTEND3` varchar(255) DEFAULT NULL COMMENT '扩展字段3',
  `EXTEND4` varchar(255) DEFAULT NULL COMMENT '扩展字段4',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Records of vend_syslog
-- ----------------------------

-- ----------------------------
-- Table structure for `vend_user`
-- ----------------------------
DROP TABLE IF EXISTS `vend_user`;
CREATE TABLE `vend_user` (
  `usercode` varchar(50) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role_id` int(12) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `extend1` varchar(100) DEFAULT NULL,
  `extend2` varchar(100) DEFAULT NULL,
  `extend3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`usercode`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of vend_user
-- ----------------------------
INSERT INTO vend_user VALUES ('VM2017-08-22', '总后台用户', '123456', '2', '2017-08-22 16:12:39', '2017-08-22 16:12:39', null, null, null);

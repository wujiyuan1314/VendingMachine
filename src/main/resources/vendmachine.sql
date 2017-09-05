/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : vendmachine

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-09-05 18:01:50
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `code_catalog`
-- ----------------------------
DROP TABLE IF EXISTS `code_catalog`;
CREATE TABLE `code_catalog` (
  `CODENO` varchar(50) NOT NULL COMMENT '类别编号',
  `CODENAME` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `CODEDESCRIBE` varchar(50) DEFAULT NULL COMMENT '类别描述',
  `ITEMNOLENGTH` int(11) DEFAULT NULL COMMENT '编码长度',
  `KIND` varchar(50) DEFAULT NULL COMMENT '种类',
  `PARENTCODENO` varchar(50) DEFAULT NULL COMMENT '上级编码',
  PRIMARY KEY (`CODENO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code_catalog
-- ----------------------------
INSERT INTO code_catalog VALUES ('ACCOUNTTYPE', '账户操作类型', '农户的账户操作类型', null, null, null);
INSERT INTO code_catalog VALUES ('ADSCREEN', '广告屏幕样式', '广告的屏幕样式', null, null, null);
INSERT INTO code_catalog VALUES ('ADTYPE', '广告类型', '广告类型', null, null, null);
INSERT INTO code_catalog VALUES ('CLEANSTATUS', '是否自动清洗', '是否自动清洗', null, null, null);
INSERT INTO code_catalog VALUES ('CUPSTATUS', '储杯情况', '储杯情况', null, null, null);
INSERT INTO code_catalog VALUES ('HEATSTATUS', '加热状态', '加热状态', null, null, null);
INSERT INTO code_catalog VALUES ('QRCODETYPE', '二维码类型', '二维码类型', null, null, null);
INSERT INTO code_catalog VALUES ('UPFILETYPE', '文件类型', '上传的文件类型', null, null, null);
INSERT INTO code_catalog VALUES ('UPPICTYPE', '图片类型', '上传的图片类型', null, null, null);
INSERT INTO code_catalog VALUES ('UPVIDEOTYPE', '视频类型', '上传的视频类型', null, null, null);
INSERT INTO code_catalog VALUES ('USESTATUS', '机器状态', '机器的状态', null, null, null);
INSERT INTO code_catalog VALUES ('WATERSTATUS', '储水情况', '储水情况', null, null, null);

-- ----------------------------
-- Table structure for `code_library`
-- ----------------------------
DROP TABLE IF EXISTS `code_library`;
CREATE TABLE `code_library` (
  `id` varchar(32) NOT NULL,
  `codeno` varchar(32) DEFAULT NULL COMMENT '类别代码',
  `itemno` varchar(11) DEFAULT NULL COMMENT '序号',
  `itemname` varchar(120) DEFAULT NULL COMMENT '分类名字',
  `addtime` datetime DEFAULT NULL,
  `extend2` varchar(120) DEFAULT NULL,
  `extend3` varchar(120) DEFAULT NULL,
  `extend4` varchar(120) DEFAULT NULL,
  `extend1` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code_library
-- ----------------------------
INSERT INTO code_library VALUES ('06d1e53f7ccf4a7297465e8efca02101', 'UPFILETYPE', '5', 'xlsx', '2017-08-28 10:28:31', null, null, null, null);
INSERT INTO code_library VALUES ('0779e016cc324a2b8315427c12c70503', 'ADSCREEN', '9', '风格9', '2017-09-04 14:25:13', null, null, null, '上面二维码和机器码，下面视频');
INSERT INTO code_library VALUES ('13971e88639248f4a1d00bcd488740ef', 'USESTATUS', '3', '待审核', '2017-09-05 16:15:02', null, null, null, '机器待审核');
INSERT INTO code_library VALUES ('16a5f9620529489892f8ac87cf8d94aa', 'UPPICTYPE', '2', 'JPG', '2017-08-28 10:22:21', null, null, null, null);
INSERT INTO code_library VALUES ('1c6920ae56354383b607aad8532f3002', 'QRCODETYPE', '2', '小程序二维码', '2017-08-28 15:19:43', null, null, null, null);
INSERT INTO code_library VALUES ('1e48d87e4c6b4af4abbc454e36ddbfaa', 'HEATSTATUS', '3', '加热完成', '2017-09-05 15:51:48', null, null, null, '');
INSERT INTO code_library VALUES ('20b702be56834dc2a8861b166ac2a884', 'ACCOUNTTYPE', '3', '购买', '2017-08-29 14:01:20', null, null, null, null);
INSERT INTO code_library VALUES ('241d8e3c7b724ffaacb4d92eb8b627b2', 'CUPSTATUS', '2', '无杯', '2017-09-05 15:51:04', null, null, null, '');
INSERT INTO code_library VALUES ('24fdaea96ed240b2bf2b41931e1d8a18', 'UPFILETYPE', '8', 'docx', '2017-08-28 10:29:11', null, null, null, null);
INSERT INTO code_library VALUES ('25e1d3cf4e514a8c94458b38df3e7a56', 'UPFILETYPE', '1', 'txt', '2017-08-28 10:26:31', null, null, null, null);
INSERT INTO code_library VALUES ('2e612f5a29ca4c0d9e8784bae5cf445d', 'UPPICTYPE', '3', 'BMP', '2017-08-28 10:23:30', null, null, null, null);
INSERT INTO code_library VALUES ('306c9f59a93f4fab925d7a3f65bd05f8', 'ADSCREEN', '5', '风格5', '2017-09-04 14:21:32', null, null, null, '上面图片，中间视频，下面二维码和机器码');
INSERT INTO code_library VALUES ('310b77bae3854805913cc71e5eae00da', 'UPVIDEOTYPE', '6', 'RMVB', '2017-08-28 10:31:10', null, null, null, null);
INSERT INTO code_library VALUES ('31bf5f7385674f0493dc1d83c9a67aaf', 'CUPSTATUS', '1', '有杯', '2017-09-05 15:50:55', null, null, null, '');
INSERT INTO code_library VALUES ('372cc3a779834463a9773ba844c60fd7', 'ADSCREEN', '4', '风格4', '2017-09-04 14:14:36', null, null, null, '上面机器码和二维码，中间图片，下面视频');
INSERT INTO code_library VALUES ('3e7cddd7636a40b7929634d5d7eecb7b', 'UPFILETYPE', '2', 'pdf', '2017-08-28 10:26:38', null, null, null, null);
INSERT INTO code_library VALUES ('417a14adf8e44b6eb1482a74eda56579', 'UPVIDEOTYPE', '4', 'MKV', '2017-08-28 10:30:40', null, null, null, null);
INSERT INTO code_library VALUES ('41e629947d73400f9a0aa271ddc94a5d', 'WATERSTATUS', '2', '无水', '2017-09-05 15:35:12', null, null, null, '无水');
INSERT INTO code_library VALUES ('48d9f64bc2344d28b10a6cd7000fc69b', 'ADSCREEN', '10', '风格10', '2017-09-04 14:24:52', null, null, null, '上面视频，下面二维码和机器码');
INSERT INTO code_library VALUES ('496e0906dc894027bd5d504ea548c615', 'UPPICTYPE', '4', 'PNG', '2017-08-28 10:23:38', null, null, null, null);
INSERT INTO code_library VALUES ('4a79f1a48fb54d5b8730e7a5fda16860', 'UPVIDEOTYPE', '5', 'FLV', '2017-08-28 10:30:57', null, null, null, null);
INSERT INTO code_library VALUES ('500e15976e0e429990a612adde08150a', 'UPFILETYPE', '7', 'zip', '2017-08-28 10:29:01', null, null, null, null);
INSERT INTO code_library VALUES ('58793f71315e4f76b30705578cfe55c2', 'CLEANSTATUS', '1', '自动清洗', '2017-09-05 15:52:37', null, null, null, '');
INSERT INTO code_library VALUES ('58883f88c5fc46159e805396a6bbd964', 'ADTYPE', '3', '视频广告', '2017-08-25 11:31:01', null, null, null, null);
INSERT INTO code_library VALUES ('6081dc2c17a64173916003506e6f7f1e', 'ADSCREEN', '2', '风格2', '2017-09-04 14:08:34', null, null, null, '上面视频，中间图片，下面二维码和机器码');
INSERT INTO code_library VALUES ('63335262275847249428310d2f31a951', 'WATERSTATUS', '1', '有水', '2017-09-05 15:35:01', null, null, null, '有水');
INSERT INTO code_library VALUES ('687d27951d50426b9e6b00a95a53e21d', 'ADSCREEN', '7', '风格7', '2017-09-04 14:23:10', null, null, null, '上面图片，下面二维码和机器码');
INSERT INTO code_library VALUES ('74017c8f29d74ca3923ddd30cf72d086', 'USESTATUS', '2', '未启用', '2017-09-04 20:20:25', null, null, null, '机器关机');
INSERT INTO code_library VALUES ('74fe8bb8ff5543a5b65ed7c7f2970df7', 'ADSCREEN', '3', '风格3', '2017-09-04 14:13:59', null, null, null, '上面机器码和二维码，中间视频，下面图片');
INSERT INTO code_library VALUES ('7d2a9fffba54435281931903c3c29f6f', 'UPVIDEOTYPE', '3', '3GP', '2017-08-28 10:30:29', null, null, null, null);
INSERT INTO code_library VALUES ('7ebc0bd02fc44e3fb71a84dd797133f0', 'QRCODETYPE', '1', '商户二维码', '2017-08-28 15:19:34', null, null, null, null);
INSERT INTO code_library VALUES ('919eb58bde734131b1bc7f8b6607a6fe', 'HEATSTATUS', '1', '未加热', '2017-09-05 15:51:28', null, null, null, '');
INSERT INTO code_library VALUES ('920ee5c12aa24151b6f8573ace909d5d', 'ADTYPE', '1', '文字广告', '2017-08-25 11:30:37', null, null, null, null);
INSERT INTO code_library VALUES ('971be2d525484f6cb12a54fd232dbce8', 'ACCOUNTTYPE', '1', '充值', '2017-08-29 14:00:54', null, null, null, null);
INSERT INTO code_library VALUES ('99f6924f5a684541809b58e38181538c', 'UPVIDEOTYPE', '2', 'WMV', '2017-08-28 10:30:20', null, null, null, null);
INSERT INTO code_library VALUES ('a0efb4fdf23545dda7e9466f0f258981', 'UPPICTYPE', '1', 'JPEG', '2017-08-28 10:22:13', null, null, null, null);
INSERT INTO code_library VALUES ('a77ca80193d7479bbad84fba2109e241', 'UPVIDEOTYPE', '7', 'MP4', '2017-08-28 10:31:58', null, null, null, null);
INSERT INTO code_library VALUES ('a93d8afbdd274de3ba3c17d680a71468', 'ADSCREEN', '6', '风格6', '2017-09-04 14:22:08', null, null, null, '上面图片，中间二维码和机器码，下面视频');
INSERT INTO code_library VALUES ('b5ec7f65025a475a928c34731131af3b', 'UPFILETYPE', '6', 'rar', '2017-08-28 10:28:43', null, null, null, null);
INSERT INTO code_library VALUES ('bb1de1fd6b044c2b8c34a7817b7df215', 'UPPICTYPE', '5', 'GIF', '2017-08-28 10:23:58', null, null, null, null);
INSERT INTO code_library VALUES ('bd6ee3a9cb7c4710a9f58ab4e087abb0', 'ADSCREEN', '1', '风格1', '2017-09-04 14:08:18', null, null, null, '上面视频，中间二维码和机器码，下面图片');
INSERT INTO code_library VALUES ('caf868e9bac046239cfd8a02dbb84f66', 'HEATSTATUS', '2', '加热中', '2017-09-05 15:51:38', null, null, null, '');
INSERT INTO code_library VALUES ('cfd726aa3f944dddaf2e7ac16916ae4b', 'ACCOUNTTYPE', '2', '提现', '2017-08-29 14:01:04', null, null, null, null);
INSERT INTO code_library VALUES ('d2bea9a04ca247ae84e0bafcadccf67b', 'ADSCREEN', '8', '风格8', '2017-09-04 14:23:31', null, null, null, '上面二维码和机器码，下面图片');
INSERT INTO code_library VALUES ('d506876ed6584ac5ae86ff48c37b4aab', 'USESTATUS', '1', '启用', '2017-09-04 20:20:15', null, null, null, '机器正在使用');
INSERT INTO code_library VALUES ('de379d2f392945ad9756dca39b3349d3', 'UPFILETYPE', '3', 'doc', '2017-08-28 10:26:49', null, null, null, null);
INSERT INTO code_library VALUES ('e33ab7b219de4629b6976ea930917f87', 'ADTYPE', '2', '图片广告', '2017-08-25 11:30:51', null, null, null, null);
INSERT INTO code_library VALUES ('e57f1d63a95544e0a1da2b196e38b4bc', 'UPVIDEOTYPE', '1', 'AVI', '2017-08-28 10:29:38', null, null, null, null);
INSERT INTO code_library VALUES ('f4805ab0197b42f291084b969fee27a9', 'UPFILETYPE', '4', 'xls', '2017-08-28 10:28:15', null, null, null, null);
INSERT INTO code_library VALUES ('f68cb7a8039146e482360bd72570e8d1', 'CLEANSTATUS', '2', '不自动清洗', '2017-09-05 15:52:44', null, null, null, '');

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menuitem
-- ----------------------------
INSERT INTO menuitem VALUES ('1', '0', '根目录', '-', '-', null, null, null, null, null);
INSERT INTO menuitem VALUES ('2', '1', '首页', '-', 'icon-home', null, '2017-08-25 15:47:30', '2017-08-25 15:47:33', null, null);
INSERT INTO menuitem VALUES ('3', '1', '商品管理', '-', 'icon-briefcase', null, '2017-08-25 15:04:08', '2017-08-25 15:04:08', null, null);
INSERT INTO menuitem VALUES ('4', '3', '商品列表', 'goods/goodss', '-', null, '2017-08-25 15:19:53', '2017-08-25 15:19:53', null, null);
INSERT INTO menuitem VALUES ('5', '1', '广告管理', '-', 'icon-volume-up', null, '2017-08-25 15:20:53', '2017-08-25 15:20:53', null, null);
INSERT INTO menuitem VALUES ('6', '5', '广告列表', 'ad/ads', '-', null, '2017-08-25 15:21:23', '2017-08-25 15:21:23', null, null);
INSERT INTO menuitem VALUES ('7', '1', '菜单管理', '-', 'icon-align-justify', null, '2017-08-25 15:21:49', '2017-08-25 15:21:49', null, null);
INSERT INTO menuitem VALUES ('8', '7', '菜单列表', 'menuitem/menuitems', '-', null, '2017-08-25 15:22:09', '2017-08-25 15:22:09', null, null);
INSERT INTO menuitem VALUES ('9', '1', '用户权限管理', '-', 'icon-user', null, '2017-08-25 15:22:42', '2017-08-25 15:22:42', null, null);
INSERT INTO menuitem VALUES ('10', '9', '用户管理', 'user/users', '-', null, '2017-08-25 15:23:15', '2017-08-25 15:23:15', null, null);
INSERT INTO menuitem VALUES ('11', '9', '角色管理', 'role/roles', '-', null, '2017-08-25 15:50:38', '2017-08-25 15:50:38', null, null);
INSERT INTO menuitem VALUES ('12', '9', '权限设置', 'permission/permissions', '-', null, '2017-08-25 15:51:27', '2017-08-25 15:51:27', null, null);
INSERT INTO menuitem VALUES ('13', '1', '订单管理', '-', 'icon-file-alt', null, '2017-08-25 15:52:01', '2017-08-25 15:52:01', null, null);
INSERT INTO menuitem VALUES ('14', '13', '订单列表', 'order/orders', '-', null, '2017-08-25 15:52:21', '2017-08-25 15:52:21', null, null);
INSERT INTO menuitem VALUES ('15', '1', '分类管理', '-', 'icon-th', null, '2017-08-25 15:52:44', '2017-08-25 15:52:44', null, null);
INSERT INTO menuitem VALUES ('16', '15', '参数列表', 'codeCatalog/codeCatalogs', '-', null, '2017-08-25 15:53:07', '2017-08-25 15:53:07', null, null);
INSERT INTO menuitem VALUES ('17', '1', '优惠券管理', '-', 'icon-strikethrough', null, '2017-08-26 17:04:46', '2017-08-26 17:04:46', null, null);
INSERT INTO menuitem VALUES ('18', '17', '优惠券列表', 'coupon/coupons', '-', null, '2017-08-26 17:05:26', '2017-08-26 17:05:26', null, null);
INSERT INTO menuitem VALUES ('19', '1', '二维码管理', '-', 'icon-qrcode', null, '2017-08-28 14:25:03', '2017-08-28 14:25:03', null, null);
INSERT INTO menuitem VALUES ('20', '19', '二维码列表', 'qrcode/qrcodes', '-', null, '2017-08-28 14:25:32', '2017-08-28 14:25:32', null, null);
INSERT INTO menuitem VALUES ('21', '19', '二维码关注列表', 'qrcodeAtt/qrcodeAtts', '-', null, '2017-08-28 17:04:00', '2017-08-28 17:04:00', null, null);
INSERT INTO menuitem VALUES ('22', '1', '用户账户管理', '-', 'icon-credit-card', null, '2017-08-29 10:44:19', '2017-08-29 10:44:19', null, null);
INSERT INTO menuitem VALUES ('23', '22', '账户列表', 'account/accounts', '-', null, '2017-08-29 10:44:44', '2017-08-29 10:44:44', null, null);
INSERT INTO menuitem VALUES ('24', '22', '账户操作纪录', 'accountdetail/accountdetails', '-', null, '2017-08-29 14:00:05', '2017-08-29 14:00:05', null, null);
INSERT INTO menuitem VALUES ('25', '5', '广告屏样式', 'codeLibrary/adscreen', '-', null, '2017-09-04 11:44:28', '2017-09-04 11:44:28', null, null);
INSERT INTO menuitem VALUES ('26', '1', '机器管理', '-', 'icon-coffee', null, '2017-09-04 19:29:50', '2017-09-04 19:29:50', null, null);
INSERT INTO menuitem VALUES ('27', '26', '机器列表', 'machine/machines', '-', null, '2017-09-04 19:30:18', '2017-09-04 19:30:18', null, null);

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
INSERT INTO vend_account VALUES ('VM2017083016442563', '0.00', '51fe78b0a1d09990', '2017-08-30 16:44:25', '2017-08-30 16:44:25', null, null, null);
INSERT INTO vend_account VALUES ('VM2017083017395351', '0.00', '51fe78b0a1d09990', '2017-08-30 17:39:53', '2017-08-30 17:39:53', null, null, null);
INSERT INTO vend_account VALUES ('VM2017083017401488', '0.00', '51fe78b0a1d09990', '2017-08-30 17:40:14', '2017-08-30 17:40:14', null, null, null);
INSERT INTO vend_account VALUES ('VM2017083017402876', '0.00', '51fe78b0a1d09990', '2017-08-30 17:40:28', '2017-08-30 17:40:28', null, null, null);
INSERT INTO vend_account VALUES ('VM2017083114593107', '0.00', '51fe78b0a1d09990', '2017-08-31 14:59:31', '2017-08-31 14:59:31', null, null, null);
INSERT INTO vend_account VALUES ('VM2017083115191427', '0.00', '51fe78b0a1d09990', '2017-08-31 15:19:14', '2017-08-31 15:19:14', null, null, null);
INSERT INTO vend_account VALUES ('VM2017083115230747', '0.00', '51fe78b0a1d09990', '2017-08-31 15:23:07', '2017-08-31 15:23:07', null, null, null);
INSERT INTO vend_account VALUES ('VM2017083115295071', '0.00', '51fe78b0a1d09990', '2017-08-31 15:29:50', '2017-08-31 15:29:50', null, null, null);
INSERT INTO vend_account VALUES ('VM2017083115300222', '0.00', '51fe78b0a1d09990', '2017-08-31 15:30:02', '2017-08-31 15:30:02', null, null, null);
INSERT INTO vend_account VALUES ('VM2017083115300625', '0.00', '51fe78b0a1d09990', '2017-08-31 15:30:06', '2017-08-31 15:30:06', null, null, null);

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
  `pic_interval` int(11) DEFAULT NULL COMMENT '图片轮播时间间隔',
  `pic1` varchar(200) DEFAULT NULL COMMENT '图片1地址',
  `pic2` varchar(200) DEFAULT NULL COMMENT '图片2地址',
  `pic3` varchar(200) DEFAULT NULL COMMENT '图片3地址',
  `pic4` varchar(200) DEFAULT NULL COMMENT '图片4地址',
  `pic5` varchar(200) DEFAULT NULL COMMENT '图片5地址',
  `pic6` varchar(200) DEFAULT NULL COMMENT '图片6地址',
  `video` varchar(255) DEFAULT NULL COMMENT '视频地址',
  `height` varchar(100) DEFAULT NULL COMMENT '高度设置',
  `width` varchar(100) DEFAULT NULL COMMENT '宽度设置',
  `start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `extend1` varchar(100) DEFAULT NULL,
  `extend2` varchar(100) DEFAULT NULL,
  `extend3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='广告表';

-- ----------------------------
-- Records of vend_ad
-- ----------------------------
INSERT INTO vend_ad VALUES ('1', '广告2', '3', '/userfiles/pic/201709041457232286.jpg', '/userfiles/pic/201709041457278635.jpg', '', '', '', '', '/userfiles/video/201709041457476423.mp4', '45', '45', '2017-09-04 15:04:16', '2017-09-04 15:04:16', '2017-09-04 14:57:56', '2017-09-04 14:57:56', '水电费', '4', null);
INSERT INTO vend_ad VALUES ('2', '广告1', '6', '/userfiles/pic/201709041522151457.jpg', '/userfiles/pic/201709041522182234.jpg', '', '', '', '', '/userfiles/video/201709041522380132.mp4', '67', '67', '2017-09-04 15:22:03', '2017-09-04 15:22:03', '2017-09-04 15:23:17', '2017-09-04 15:23:17', '电饭锅', '5', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='优惠券表';

-- ----------------------------
-- Records of vend_coupon
-- ----------------------------
INSERT INTO vend_coupon VALUES ('4', '324', '234', '234.00', null, null, '2017-08-26 00:00:00', '2017-08-26 00:00:00', '2017-08-26 17:54:21', '2017-08-26 17:54:21', null, null, null);
INSERT INTO vend_coupon VALUES ('5', '发生的', '434', '2.00', null, null, '2017-08-26 00:00:00', '2017-08-26 00:00:00', '2017-08-26 17:54:43', '2017-08-26 17:54:43', null, null, null);

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
-- Table structure for `vend_machine`
-- ----------------------------
DROP TABLE IF EXISTS `vend_machine`;
CREATE TABLE `vend_machine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `machine_name` varchar(100) DEFAULT NULL COMMENT '机器名',
  `machine_code` varchar(100) DEFAULT NULL COMMENT '机器码',
  `usercode` varchar(32) DEFAULT NULL COMMENT '所属账号',
  `machine_type` varchar(32) DEFAULT NULL COMMENT '咖啡机型号',
  `use_status` varchar(11) DEFAULT NULL COMMENT '机器使用状态',
  `water_status` varchar(11) DEFAULT NULL COMMENT '放水情况',
  `cup_status` varchar(11) DEFAULT NULL COMMENT '储杯情况',
  `heat_status` varchar(11) DEFAULT NULL COMMENT '加热状态',
  `clean_status` varchar(11) DEFAULT NULL COMMENT '清洗状态',
  `client_qrcode` int(11) DEFAULT NULL COMMENT '客户端固定二维码',
  `shop_qrcode` int(11) DEFAULT NULL COMMENT '商户二维码',
  `ad_id` int(11) DEFAULT NULL COMMENT '所选广告',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `extend1` varchar(100) DEFAULT NULL,
  `extend2` varchar(100) DEFAULT NULL,
  `extend3` varchar(100) DEFAULT NULL,
  `extend4` varchar(100) DEFAULT NULL,
  `extend5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_machine_code` (`machine_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vend_machine
-- ----------------------------
INSERT INTO vend_machine VALUES ('1', null, '30101', 'VM2017082419502601', '2', '2', '1', '1', '1', '1', null, null, null, '2017-09-04 19:48:54', '2017-09-04 19:48:57', null, null, null, null, null);
INSERT INTO vend_machine VALUES ('2', null, '30102', 'VM2017082419502602', '4', '2', '1', '1', '1', '1', null, null, null, '2017-09-04 19:50:43', '2017-09-04 19:50:46', null, null, null, null, null);

-- ----------------------------
-- Table structure for `vend_order`
-- ----------------------------
DROP TABLE IF EXISTS `vend_order`;
CREATE TABLE `vend_order` (
  `order_id` varchar(32) NOT NULL COMMENT '订单ID',
  `machine_code` varchar(32) DEFAULT NULL COMMENT '机器码',
  `usercode` varchar(50) DEFAULT NULL COMMENT '购买用户',
  `shopusercode` varchar(50) DEFAULT NULL COMMENT '商家代码',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `pay_type` varchar(11) DEFAULT NULL COMMENT '支付方式',
  `num` int(11) DEFAULT NULL COMMENT '购买数量',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '订单金额',
  `free_status` varchar(11) DEFAULT NULL COMMENT '免费状态 0表示免费，1表示是不免费',
  `orderstate` varchar(50) DEFAULT NULL COMMENT '订单状态（1，已支付。2，未支付）',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `extend1` varchar(200) DEFAULT NULL,
  `extend2` varchar(200) DEFAULT NULL,
  `extend3` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
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
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='管理后台权限表\r\n\r\n\r\n';

-- ----------------------------
-- Records of vend_permission
-- ----------------------------
INSERT INTO vend_permission VALUES ('1', '0', 'root', '根目录', null, null, null, null, null);
INSERT INTO vend_permission VALUES ('12', '1', 'good', '商品', '2017-09-04 11:09:53', '2017-09-04 11:09:53', null, null, null);
INSERT INTO vend_permission VALUES ('13', '1', 'ad', '广告', '2017-09-04 11:10:13', '2017-09-04 11:10:13', null, null, null);
INSERT INTO vend_permission VALUES ('14', '1', 'menuitem', '菜单', '2017-09-04 11:10:27', '2017-09-04 11:10:27', null, null, null);
INSERT INTO vend_permission VALUES ('15', '1', 'permission', '权限', '2017-09-04 11:11:18', '2017-09-04 11:11:18', null, null, null);
INSERT INTO vend_permission VALUES ('16', '1', 'order', '订单', '2017-09-04 11:11:29', '2017-09-04 11:11:29', null, null, null);
INSERT INTO vend_permission VALUES ('17', '1', 'codeCatalog', '分类', '2017-09-04 11:11:59', '2017-09-04 11:11:59', null, null, null);
INSERT INTO vend_permission VALUES ('18', '1', 'coupon', '优惠券', '2017-09-04 11:12:14', '2017-09-04 11:12:14', null, null, null);
INSERT INTO vend_permission VALUES ('19', '1', 'qrcode', '二维码', '2017-09-04 11:12:23', '2017-09-04 11:12:23', null, null, null);
INSERT INTO vend_permission VALUES ('20', '1', 'account', '用户账户', '2017-09-04 11:12:37', '2017-09-04 11:12:37', null, null, null);
INSERT INTO vend_permission VALUES ('21', '12', 'goods:goodss', '商品列表', '2017-09-04 11:13:01', '2017-09-04 11:13:01', null, null, null);
INSERT INTO vend_permission VALUES ('22', '12', 'goods:add', '商品添加', '2017-09-04 11:13:11', '2017-09-04 11:13:11', null, null, null);
INSERT INTO vend_permission VALUES ('23', '12', 'goods:edit', '商品修改', '2017-09-04 11:13:19', '2017-09-04 11:13:19', null, null, null);
INSERT INTO vend_permission VALUES ('24', '12', 'good:dels', '商品批量删除', '2017-09-04 11:14:06', '2017-09-04 11:14:06', null, null, null);
INSERT INTO vend_permission VALUES ('25', '13', 'ad:ads', '广告列表', '2017-09-04 11:14:28', '2017-09-04 11:14:28', null, null, null);
INSERT INTO vend_permission VALUES ('26', '13', 'ad:add', '广告添加', '2017-09-04 11:14:40', '2017-09-04 11:14:40', null, null, null);
INSERT INTO vend_permission VALUES ('27', '13', 'ad:edit', '广告修改', '2017-09-04 11:14:50', '2017-09-04 11:14:50', null, null, null);
INSERT INTO vend_permission VALUES ('28', '13', 'ad:del', '广告删除', '2017-09-04 11:15:00', '2017-09-04 11:15:00', null, null, null);
INSERT INTO vend_permission VALUES ('29', '13', 'ad:dels', '广告批量删除', '2017-09-04 11:15:08', '2017-09-04 11:15:08', null, null, null);
INSERT INTO vend_permission VALUES ('30', '12', 'goods:del', '商品删除', '2017-09-04 11:15:33', '2017-09-04 11:15:33', null, null, null);
INSERT INTO vend_permission VALUES ('31', '14', 'menuitem:menuitems', '菜单列表', '2017-09-04 11:17:09', '2017-09-04 11:17:09', null, null, null);
INSERT INTO vend_permission VALUES ('32', '14', 'menuitem:add', '菜单添加', '2017-09-04 11:17:19', '2017-09-04 11:17:19', null, null, null);
INSERT INTO vend_permission VALUES ('33', '14', 'menuitem:edit', '菜单修改', '2017-09-04 11:17:31', '2017-09-04 11:17:31', null, null, null);
INSERT INTO vend_permission VALUES ('34', '14', 'menuitem:del', '菜单删除', '2017-09-04 11:17:44', '2017-09-04 11:17:44', null, null, null);
INSERT INTO vend_permission VALUES ('35', '14', 'menuitem:dels', '菜单批量删除', '2017-09-04 11:17:54', '2017-09-04 11:17:54', null, null, null);
INSERT INTO vend_permission VALUES ('36', '15', 'permission:permissions', '权限列表', '2017-09-04 11:18:13', '2017-09-04 11:18:13', null, null, null);
INSERT INTO vend_permission VALUES ('37', '15', 'permission:add', '权限添加', '2017-09-04 11:18:24', '2017-09-04 11:18:24', null, null, null);
INSERT INTO vend_permission VALUES ('38', '15', 'permission:edit', '权限修改', '2017-09-04 11:18:34', '2017-09-04 11:18:34', null, null, null);
INSERT INTO vend_permission VALUES ('39', '15', 'permission:del', '权限删除', '2017-09-04 11:18:44', '2017-09-04 11:18:44', null, null, null);
INSERT INTO vend_permission VALUES ('40', '15', 'permission:dels', '权限批量删除', '2017-09-04 11:19:08', '2017-09-04 11:19:08', null, null, null);
INSERT INTO vend_permission VALUES ('41', '16', 'order:orders', '订单列表', '2017-09-04 11:22:35', '2017-09-04 11:22:35', null, null, null);
INSERT INTO vend_permission VALUES ('42', '16', 'order:add', '订单添加', '2017-09-04 11:22:45', '2017-09-04 11:22:45', null, null, null);
INSERT INTO vend_permission VALUES ('43', '16', 'order:edit', '订单修改', '2017-09-04 11:22:54', '2017-09-04 11:22:54', null, null, null);
INSERT INTO vend_permission VALUES ('44', '16', 'order:del', '订单删除', '2017-09-04 11:23:13', '2017-09-04 11:23:13', null, null, null);
INSERT INTO vend_permission VALUES ('45', '16', 'order:dels', '订单批量删除', '2017-09-04 11:23:22', '2017-09-04 11:23:22', null, null, null);
INSERT INTO vend_permission VALUES ('46', '17', 'codeCatalog:codeCatalogs', '分类列表', '2017-09-04 11:23:44', '2017-09-04 11:23:44', null, null, null);
INSERT INTO vend_permission VALUES ('47', '17', 'codeCatalog:add', '分类添加', '2017-09-04 11:24:07', '2017-09-04 11:24:07', null, null, null);
INSERT INTO vend_permission VALUES ('48', '17', 'codeCatalog:edit', '分类修改', '2017-09-04 11:24:18', '2017-09-04 11:24:18', null, null, null);
INSERT INTO vend_permission VALUES ('49', '17', 'codeCatalog:del', '分类删除', '2017-09-04 11:24:29', '2017-09-04 11:24:29', null, null, null);
INSERT INTO vend_permission VALUES ('50', '17', 'codeCatalog:dels', '分类批量删除', '2017-09-04 11:24:39', '2017-09-04 11:24:39', null, null, null);
INSERT INTO vend_permission VALUES ('51', '18', 'coupon:coupons', '优惠券列表', '2017-09-04 11:25:12', '2017-09-04 11:25:12', null, null, null);
INSERT INTO vend_permission VALUES ('52', '18', 'coupon:add', '优惠券添加', '2017-09-04 11:25:21', '2017-09-04 11:25:21', null, null, null);
INSERT INTO vend_permission VALUES ('53', '18', 'coupon:edit', '优惠券修改', '2017-09-04 11:25:29', '2017-09-04 11:25:29', null, null, null);
INSERT INTO vend_permission VALUES ('54', '18', 'coupon:del', '优惠券删除', '2017-09-04 11:25:39', '2017-09-04 11:25:39', null, null, null);
INSERT INTO vend_permission VALUES ('55', '18', 'coupon:dels', '优惠券批量删除', '2017-09-04 11:25:50', '2017-09-04 11:25:50', null, null, null);
INSERT INTO vend_permission VALUES ('56', '19', 'qrcode:qrcodes', '二维码列表', '2017-09-04 11:26:12', '2017-09-04 11:26:12', null, null, null);
INSERT INTO vend_permission VALUES ('57', '19', 'qrcode:add', '二维码添加', '2017-09-04 11:26:20', '2017-09-04 11:26:20', null, null, null);
INSERT INTO vend_permission VALUES ('58', '19', 'qrcode:edit', '二维码修改', '2017-09-04 11:26:32', '2017-09-04 11:26:32', null, null, null);
INSERT INTO vend_permission VALUES ('59', '19', 'qrcode:del', '二维码删除', '2017-09-04 11:26:44', '2017-09-04 11:26:44', null, null, null);
INSERT INTO vend_permission VALUES ('60', '19', 'qrcode:dels', '二维码批量删除', '2017-09-04 11:26:51', '2017-09-04 11:26:51', null, null, null);
INSERT INTO vend_permission VALUES ('61', '20', 'account:accounts', '用户账户列表', '2017-09-04 11:27:17', '2017-09-04 11:27:17', null, null, null);
INSERT INTO vend_permission VALUES ('62', '20', 'account:add', '用户账户添加', '2017-09-04 11:27:28', '2017-09-04 11:27:28', null, null, null);
INSERT INTO vend_permission VALUES ('63', '20', 'account:edit', '用户账户修改', '2017-09-04 11:27:39', '2017-09-04 11:27:39', null, null, null);
INSERT INTO vend_permission VALUES ('64', '20', 'account:del', '用户账户删除', '2017-09-04 11:27:48', '2017-09-04 11:27:48', null, null, null);
INSERT INTO vend_permission VALUES ('65', '20', 'account:dels', '用户账户批量删除', '2017-09-04 11:27:58', '2017-09-04 11:27:58', null, null, null);
INSERT INTO vend_permission VALUES ('66', '1', 'codeLibrary', '分类参数', '2017-09-04 13:38:27', '2017-09-04 13:38:29', null, null, null);
INSERT INTO vend_permission VALUES ('67', '66', 'codeLibrary:codeLibrarys', '分类参数列表', '2017-09-04 13:39:16', '2017-09-04 13:39:16', null, null, null);
INSERT INTO vend_permission VALUES ('68', '66', 'codeLibrary:add', '分类参数添加', '2017-09-04 13:39:25', '2017-09-04 13:39:25', null, null, null);
INSERT INTO vend_permission VALUES ('69', '66', 'codeLibrary:edit', '分类参数修改', '2017-09-04 13:39:33', '2017-09-04 13:39:33', null, null, null);
INSERT INTO vend_permission VALUES ('70', '66', 'codeLibrary:del', '分类参数删除', '2017-09-04 13:39:40', '2017-09-04 13:39:40', null, null, null);
INSERT INTO vend_permission VALUES ('71', '66', 'codeLibrary:dels', '分类参数批量删除', '2017-09-04 13:39:48', '2017-09-04 13:39:48', null, null, null);
INSERT INTO vend_permission VALUES ('72', '17', 'codeCatalogs:codelibrarylist', '某参数的分类情况查看', '2017-09-04 14:11:41', '2017-09-04 14:11:41', null, null, null);
INSERT INTO vend_permission VALUES ('73', '1', 'user', '用户', '2017-09-04 15:15:24', '2017-09-04 15:15:24', null, null, null);
INSERT INTO vend_permission VALUES ('74', '73', 'user:users', '用户列表', '2017-09-04 15:15:40', '2017-09-04 15:15:40', null, null, null);
INSERT INTO vend_permission VALUES ('75', '73', 'user:add', '用户添加', '2017-09-04 15:15:51', '2017-09-04 15:15:51', null, null, null);
INSERT INTO vend_permission VALUES ('76', '73', 'user:edit', '用户修改', '2017-09-04 15:16:02', '2017-09-04 15:16:02', null, null, null);
INSERT INTO vend_permission VALUES ('77', '73', 'user:del', '用户删除', '2017-09-04 15:16:14', '2017-09-04 15:16:14', null, null, null);
INSERT INTO vend_permission VALUES ('78', '73', 'user:dels', '用户批量删除', '2017-09-04 15:16:22', '2017-09-04 15:16:22', null, null, null);
INSERT INTO vend_permission VALUES ('79', '1', 'machine', '机器', '2017-09-04 19:31:31', '2017-09-04 19:31:31', null, null, null);
INSERT INTO vend_permission VALUES ('80', '79', 'machine:machines', '机器列表', '2017-09-04 19:31:47', '2017-09-04 19:31:47', null, null, null);
INSERT INTO vend_permission VALUES ('81', '79', 'machine:add', '机器添加', '2017-09-04 19:32:00', '2017-09-04 19:32:00', null, null, null);
INSERT INTO vend_permission VALUES ('82', '79', 'machine:edit', '机器修改', '2017-09-04 19:32:09', '2017-09-04 19:32:09', null, null, null);
INSERT INTO vend_permission VALUES ('83', '79', 'machine:dels', '机器批量删除', '2017-09-04 19:32:29', '2017-09-04 19:32:29', null, null, null);

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
  `parent_id` int(20) DEFAULT NULL,
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `role_description` varchar(1000) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime DEFAULT NULL COMMENT '表创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '表最后一次更新时间',
  `extend1` varchar(600) DEFAULT NULL,
  `extend2` varchar(600) DEFAULT NULL,
  `state` tinyint(4) DEFAULT '1' COMMENT '表示角色当前的状态，0表示冻结，1表示正常',
  `proportion
proportion
proportion` decimal(2,2) DEFAULT NULL,
  `extend3` varchar(600) DEFAULT NULL,
  `extend4` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='管理后台角色类型';

-- ----------------------------
-- Records of vend_role
-- ----------------------------
INSERT INTO vend_role VALUES ('1', '0', '开发者后台', '最高权限', '2017-08-24 13:33:16', '2017-08-24 13:33:19', '1,2,3,4,5,6,25,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,26,27', null, '1', null, null, null);
INSERT INTO vend_role VALUES ('2', '1', '总后台', '客户总权限用户', '2017-08-22 14:33:17', '2017-08-22 14:33:17', '2,3,4,5,6,25,7,8,13,14,15,16,17,18,19,20,21', null, '1', null, null, null);
INSERT INTO vend_role VALUES ('3', '2', '代理后台', '代理总后台管理一部分商户', '2017-08-22 14:33:44', '2017-08-22 14:33:44', '3,4,5,6,25', null, '1', null, null, null);
INSERT INTO vend_role VALUES ('4', '3', '商家', '普通商户', '2017-08-22 14:33:58', '2017-08-22 14:33:58', null, null, '1', null, null, null);
INSERT INTO vend_role VALUES ('5', '4', '消费用户', '利用客户端购买商品的消费用户', '2017-08-22 14:34:25', '2017-08-22 14:34:25', null, null, '1', null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=309 DEFAULT CHARSET=utf8 COMMENT='角色权限对照表';

-- ----------------------------
-- Records of vend_role_permission
-- ----------------------------
INSERT INTO vend_role_permission VALUES ('99', '1', '12', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('100', '1', '21', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('101', '1', '22', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('102', '1', '23', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('103', '1', '24', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('104', '1', '30', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('105', '1', '13', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('106', '1', '25', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('107', '1', '26', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('108', '1', '27', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('109', '1', '28', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('110', '1', '29', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('111', '1', '14', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('112', '1', '31', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('113', '1', '32', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('114', '1', '33', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('115', '1', '34', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('116', '1', '35', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('129', '1', '17', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('130', '1', '46', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('131', '1', '47', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('132', '1', '48', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('133', '1', '49', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('134', '1', '50', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('137', '1', '52', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('138', '1', '53', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('139', '1', '54', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('140', '1', '55', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('141', '1', '19', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('142', '1', '56', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('143', '1', '57', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('144', '1', '58', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('145', '1', '59', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('146', '1', '60', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('147', '1', '20', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('148', '1', '61', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('149', '1', '62', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('150', '1', '63', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('151', '1', '64', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('152', '1', '65', '2017-09-04 11:34:09', '2017-09-04 11:34:09', null, null, null);
INSERT INTO vend_role_permission VALUES ('153', '1', '66', '2017-09-04 13:40:07', '2017-09-04 13:40:07', null, null, null);
INSERT INTO vend_role_permission VALUES ('154', '1', '67', '2017-09-04 13:40:07', '2017-09-04 13:40:07', null, null, null);
INSERT INTO vend_role_permission VALUES ('155', '1', '68', '2017-09-04 13:40:07', '2017-09-04 13:40:07', null, null, null);
INSERT INTO vend_role_permission VALUES ('156', '1', '69', '2017-09-04 13:40:07', '2017-09-04 13:40:07', null, null, null);
INSERT INTO vend_role_permission VALUES ('157', '1', '70', '2017-09-04 13:40:07', '2017-09-04 13:40:07', null, null, null);
INSERT INTO vend_role_permission VALUES ('158', '1', '71', '2017-09-04 13:40:07', '2017-09-04 13:40:07', null, null, null);
INSERT INTO vend_role_permission VALUES ('159', '1', '72', '2017-09-04 14:12:02', '2017-09-04 14:12:02', null, null, null);
INSERT INTO vend_role_permission VALUES ('160', '1', '73', '2017-09-04 15:16:36', '2017-09-04 15:16:36', null, null, null);
INSERT INTO vend_role_permission VALUES ('161', '1', '74', '2017-09-04 15:16:36', '2017-09-04 15:16:36', null, null, null);
INSERT INTO vend_role_permission VALUES ('162', '1', '75', '2017-09-04 15:16:36', '2017-09-04 15:16:36', null, null, null);
INSERT INTO vend_role_permission VALUES ('163', '1', '76', '2017-09-04 15:16:36', '2017-09-04 15:16:36', null, null, null);
INSERT INTO vend_role_permission VALUES ('164', '1', '77', '2017-09-04 15:16:36', '2017-09-04 15:16:36', null, null, null);
INSERT INTO vend_role_permission VALUES ('165', '1', '78', '2017-09-04 15:16:36', '2017-09-04 15:16:36', null, null, null);
INSERT INTO vend_role_permission VALUES ('166', '1', '79', '2017-09-04 19:32:41', '2017-09-04 19:32:41', null, null, null);
INSERT INTO vend_role_permission VALUES ('167', '1', '80', '2017-09-04 19:32:41', '2017-09-04 19:32:41', null, null, null);
INSERT INTO vend_role_permission VALUES ('168', '1', '81', '2017-09-04 19:32:41', '2017-09-04 19:32:41', null, null, null);
INSERT INTO vend_role_permission VALUES ('169', '1', '82', '2017-09-04 19:32:41', '2017-09-04 19:32:41', null, null, null);
INSERT INTO vend_role_permission VALUES ('170', '1', '83', '2017-09-04 19:32:41', '2017-09-04 19:32:41', null, null, null);
INSERT INTO vend_role_permission VALUES ('303', '2', '16', '2017-09-05 13:43:01', '2017-09-05 13:43:01', null, null, null);
INSERT INTO vend_role_permission VALUES ('304', '2', '41', '2017-09-05 13:43:01', '2017-09-05 13:43:01', null, null, null);
INSERT INTO vend_role_permission VALUES ('305', '2', '42', '2017-09-05 13:43:01', '2017-09-05 13:43:01', null, null, null);
INSERT INTO vend_role_permission VALUES ('306', '2', '43', '2017-09-05 13:43:01', '2017-09-05 13:43:01', null, null, null);
INSERT INTO vend_role_permission VALUES ('307', '2', '44', '2017-09-05 13:43:01', '2017-09-05 13:43:01', null, null, null);
INSERT INTO vend_role_permission VALUES ('308', '2', '45', '2017-09-05 13:43:01', '2017-09-05 13:43:01', null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商家二维码信息表';

-- ----------------------------
-- Records of vend_shop_qrcode
-- ----------------------------
INSERT INTO vend_shop_qrcode VALUES ('1', '', '/userfiles/pic/201709041459537781.jpg', null, null, null, '23', '1', null);
INSERT INTO vend_shop_qrcode VALUES ('2', '', '/userfiles/pic/201709041500074560.jpg', null, null, null, '挂号费', '1', null);

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
  `mobile` varchar(22) DEFAULT NULL COMMENT '电话',
  `address` varchar(400) DEFAULT NULL COMMENT '地址',
  `linkman` varchar(100) DEFAULT NULL COMMENT '联系人',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `extend1` varchar(150) DEFAULT NULL,
  `extend2` varchar(100) DEFAULT NULL,
  `extend3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`usercode`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of vend_user
-- ----------------------------
INSERT INTO vend_user VALUES ('VM2017082419502601', 'admin', '123456', '1', null, null, null, '2017-08-22 16:12:39', '2017-08-22 16:12:39', null, null, null);
INSERT INTO vend_user VALUES ('VM2017082419502602', '345', '345', '3', null, null, null, '2017-08-24 19:50:26', '2017-08-24 19:50:26', null, null, null);
INSERT INTO vend_user VALUES ('VM2017082419562383', '546', '564', '2', null, null, null, '2017-08-24 19:56:23', '2017-08-24 19:56:23', null, null, null);
INSERT INTO vend_user VALUES ('VM2017083115230747', 'wujiyuan', '123456', '5', '18640214798', 'Zhengzhou', null, '2017-08-31 15:23:07', '2017-08-31 15:23:07', null, null, null);

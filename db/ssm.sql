/*
Navicat MySQL Data Transfer

Source Server         : phuan
Source Server Version : 50638
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-01-30 11:56:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for common
-- ----------------------------
DROP TABLE IF EXISTS `common`;
CREATE TABLE `common` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `type` varchar(3) DEFAULT NULL,
  `belong_id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `teaching_age` int(11) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `tag` varchar(200) DEFAULT NULL,
  `introduce` varchar(1000) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `show_homepage` int(11) DEFAULT NULL,
  `teachers` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of common
-- ----------------------------
INSERT INTO `common` VALUES ('1', '1', '2', '0', '文博驾校', '0', 'img/4.png', '1', '好驾校好驾校好驾校好驾校好驾校好驾校好驾校', '人民路', '89898989', null, null);
INSERT INTO `common` VALUES ('2', '1', '2', '0', '文博驾校', '0', 'img/4.png', '1', '好驾校好驾校好驾校好驾校好驾校好驾校好驾校', '人民路', '89898989', null, null);
INSERT INTO `common` VALUES ('3', null, '2', '0', 'weibo', null, 'img/4.png', null, 'jiashao', 'renminlu', '', null, null);
INSERT INTO `common` VALUES ('4', null, '3', '1', '库尔班-热合曼', '10', 'img/9.png', '1,2,3', '技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好', '人民路', null, '1', null);
INSERT INTO `common` VALUES ('5', null, '3', '1', '库尔班-热合曼', '10', 'img/9.png', '1,2,3', '技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好', '人民路', '', null, null);
INSERT INTO `common` VALUES ('6', null, '3', '1', '库尔班-热合曼', '10', 'img/9.png', '1,2,3', '技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好', '人民路', '', '1', null);
INSERT INTO `common` VALUES ('7', null, '3', '1', '库尔班-热合曼', '10', 'img/9.png', '1,2,3', '技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好', '人民路', '', null, null);
INSERT INTO `common` VALUES ('8', null, '3', '1', '库尔班-热合曼', '10', 'img/9.png', '1,2,3', '技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好', '人民路', '', '1', null);
INSERT INTO `common` VALUES ('9', null, '3', '1', '库尔班-热合曼', '10', 'img/9.png', '1,2,3', '技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好', '人民路', '', null, null);
INSERT INTO `common` VALUES ('10', null, '3', '1', '库尔班-热合曼', '10', 'img/9.png', '1,2,3', '技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好', '人民路', '', '1', null);
INSERT INTO `common` VALUES ('11', null, '3', '1', '库尔班-热合曼', '10', 'img/9.png', '1,2,3', '技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好', '人民路', '', null, null);
INSERT INTO `common` VALUES ('12', null, '3', '1', '库尔班-热合曼', '10', 'img/9.png', '1,2,3', '技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好', '人民路', '', '1', null);
INSERT INTO `common` VALUES ('13', null, '3', '1', '库尔班-热合曼', '10', 'img/9.png', '1,2,3', '技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好', '人民路', '', null, null);
INSERT INTO `common` VALUES ('14', null, '3', '1', '库尔班-热合曼', '10', 'img/9.png', '1,2,3', '技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好', '人民路', '', '1', null);
INSERT INTO `common` VALUES ('15', null, '3', '1', '库尔班-热合曼', '10', 'img/9.png', '1,2,3', '技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好技术好', '人民路', '', null, null);

-- ----------------------------
-- Table structure for course_offline
-- ----------------------------
DROP TABLE IF EXISTS `course_offline`;
CREATE TABLE `course_offline` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `platform_service_network_id` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `image_path` varchar(200) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `length` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `show_homepage` int(11) DEFAULT NULL,
  `teachers` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_offline
-- ----------------------------
INSERT INTO `course_offline` VALUES ('1', '1', '线下1', '5909', '内容1', 'abc', '2018-06-06 08:00:00', '', '人民路100号', '1', '4,5,6');
INSERT INTO `course_offline` VALUES ('2', '1', '线下2', '5909', '内容1', 'abc', '2018-06-06 00:00:00', '1天', '人民路100号', '1', '4,5,6');
INSERT INTO `course_offline` VALUES ('3', '1', '线下3', '5909', '内容1', 'abc', '2018-06-06 00:00:00', '3天', '人民路100号', '1', '4,5,6');
INSERT INTO `course_offline` VALUES ('4', '1', '线下4', '5909', '内容1', 'abc', '2018-06-06 00:00:00', '2天', '人民路100号', '1', '4,5,6');
INSERT INTO `course_offline` VALUES ('5', '1', '线下5', '5909', '内容1', 'abc', '2018-06-06 00:00:00', '1周', '人民路100号', '1', '4,5,6');
INSERT INTO `course_offline` VALUES ('6', '1', '线下6', '5909', '内容1', 'abc', '2018-06-06 00:00:00', '1周', '人民路100号', '1', '4,5,6');
INSERT INTO `course_offline` VALUES ('7', '1', '线下7', '5909', '内容1', 'abc', '2018-06-06 00:00:00', '3天', '人民路100号', '1', '4,5');
INSERT INTO `course_offline` VALUES ('8', '1', '线下8', '5909', '内容1', 'abc', '2018-06-06 00:00:00', '2天', '人民路100号', '1', null);
INSERT INTO `course_offline` VALUES ('9', '1', '线下9', '5909', '内容1', 'abc', '2018-06-06 00:00:00', '1周', '人民路100号', null, null);
INSERT INTO `course_offline` VALUES ('10', '1', '线下10', '5909', '内容1', 'abc', '2018-06-06 00:00:00', '1周', '人民路100号', null, null);

-- ----------------------------
-- Table structure for course_online
-- ----------------------------
DROP TABLE IF EXISTS `course_online`;
CREATE TABLE `course_online` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `video_path` varchar(200) DEFAULT NULL,
  `image_path` varchar(200) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `is_hot_and_charge` int(1) DEFAULT NULL COMMENT '是否热点和付费',
  `is_recommended` bit(1) DEFAULT NULL COMMENT '是否推荐',
  `hour` int(11) DEFAULT NULL,
  `minute` int(11) DEFAULT NULL,
  `second` int(11) DEFAULT NULL,
  `play_number` int(11) DEFAULT NULL,
  `show_homepage` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_online
-- ----------------------------
INSERT INTO `course_online` VALUES ('1', '4', '1', '线上1', '内容1236789', null, 'abc', '2018-01-19', '1', '', '1', '12', '23', '0', '1');
INSERT INTO `course_online` VALUES ('2', '4', '2', '线上2', '内容1236789', null, 'abc', '2018-01-19', '2', '', '2', '13', '34', '11', '1');
INSERT INTO `course_online` VALUES ('3', null, '3', '线上3', '内容1236789', '', 'abc', '2018-01-19', '1', '\0', '3', '14', '56', '0', '1');
INSERT INTO `course_online` VALUES ('4', null, '4', '线上4', '内容1236789', null, 'abc', '2018-01-19', '2', '\0', '4', '1', '7', '0', null);
INSERT INTO `course_online` VALUES ('5', null, '3', '线上5', '内容1236789', '', 'abc', '2018-01-19', '1', '', '3', '14', '56', '0', '1');
INSERT INTO `course_online` VALUES ('6', null, '3', '线上6', '内容1236789', '', 'abc', '2018-01-19', '1', '', '3', '14', '56', '0', '0');
INSERT INTO `course_online` VALUES ('7', null, '3', '线上7', '内容1236789', '', 'abc', '2018-01-19', '3', '', '3', '14', '56', '0', '0');
INSERT INTO `course_online` VALUES ('8', null, '3', '线上8', '内容1236789', '', 'abc', '2018-01-19', '1', '', '3', '14', '56', '0', '0');
INSERT INTO `course_online` VALUES ('9', null, '3', '线上9', '内容1236789', '', 'abc', '2018-01-19', '1', '', '3', '14', '56', '0', '1');
INSERT INTO `course_online` VALUES ('10', null, '3', '线上10', '内容1236789', '', 'abc', '2018-01-19', '1', '', '3', '14', '56', '0', '1');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `pwdagain` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '张三', '13918643756', '123', '123');

-- ----------------------------
-- Table structure for customer_buy_course_online
-- ----------------------------
DROP TABLE IF EXISTS `customer_buy_course_online`;
CREATE TABLE `customer_buy_course_online` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `course_online_id` int(11) DEFAULT NULL,
  `buy_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_buy_course_online
-- ----------------------------
INSERT INTO `customer_buy_course_online` VALUES ('1', '1', '1', '2018-01-30 11:37:52');

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of demo
-- ----------------------------
INSERT INTO `demo` VALUES ('1', '1', '1');
INSERT INTO `demo` VALUES ('2', '2', '2');
INSERT INTO `demo` VALUES ('3', '3', '3');
INSERT INTO `demo` VALUES ('4', 'name', 'value');
INSERT INTO `demo` VALUES ('5', '123', '456');
INSERT INTO `demo` VALUES ('6', '123', '456');
INSERT INTO `demo` VALUES ('7', '123', '456');
INSERT INTO `demo` VALUES ('8', '123', '456');
INSERT INTO `demo` VALUES ('9', '123', '456');
INSERT INTO `demo` VALUES ('10', '123', '456');

-- ----------------------------
-- Table structure for driving_policy
-- ----------------------------
DROP TABLE IF EXISTS `driving_policy`;
CREATE TABLE `driving_policy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `image_path` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of driving_policy
-- ----------------------------
INSERT INTO `driving_policy` VALUES ('1', '驾考政策1', '驾考政策内容', '');
INSERT INTO `driving_policy` VALUES ('2', '驾考政策2', '驾考政策内容', '');
INSERT INTO `driving_policy` VALUES ('3', '驾考政策3', '驾考政策内容', '');
INSERT INTO `driving_policy` VALUES ('4', '驾考政策4', '驾考政策内容', '');
INSERT INTO `driving_policy` VALUES ('5', '驾考政策5', '驾考政策内容', '');

-- ----------------------------
-- Table structure for platform_service_network
-- ----------------------------
DROP TABLE IF EXISTS `platform_service_network`;
CREATE TABLE `platform_service_network` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `image_path` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of platform_service_network
-- ----------------------------
INSERT INTO `platform_service_network` VALUES ('1', '文博驾校考场', '人民路88号', '89898989', 'abc');

-- ----------------------------
-- Table structure for platform_service_network_image
-- ----------------------------
DROP TABLE IF EXISTS `platform_service_network_image`;
CREATE TABLE `platform_service_network_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_id` int(11) DEFAULT NULL,
  `image_path` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of platform_service_network_image
-- ----------------------------
INSERT INTO `platform_service_network_image` VALUES ('1', '1', 'add');
INSERT INTO `platform_service_network_image` VALUES ('2', '1', 'add');

-- ----------------------------
-- Table structure for plat_area
-- ----------------------------
DROP TABLE IF EXISTS `plat_area`;
CREATE TABLE `plat_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dmz` varchar(10) DEFAULT NULL,
  `dmmc1` varchar(255) DEFAULT NULL,
  `dmmc2` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=149 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plat_area
-- ----------------------------
INSERT INTO `plat_area` VALUES ('1', '650100', '乌鲁木齐市', '新疆乌鲁木齐市', null);
INSERT INTO `plat_area` VALUES ('2', '650102', '天山区', '新疆乌鲁木齐市天山区', '1');
INSERT INTO `plat_area` VALUES ('3', '650103', '沙依巴克区', '新疆乌鲁木齐市沙依巴克区', '1');
INSERT INTO `plat_area` VALUES ('4', '650104', '高新区（新市区）', '新疆乌鲁木齐市高新区（新市区）', '1');
INSERT INTO `plat_area` VALUES ('5', '650105', '水磨沟区', '新疆乌鲁木齐市水磨沟区', '1');
INSERT INTO `plat_area` VALUES ('6', '650106', '经济技术开发区（头屯河区）', '新疆乌鲁木齐市经济技术开发区（头屯河区）', '1');
INSERT INTO `plat_area` VALUES ('7', '650107', '达坂城区', '新疆乌鲁木齐市达坂城区', '1');
INSERT INTO `plat_area` VALUES ('8', '650108', '米东区', '新疆乌鲁木齐市米东区', '1');
INSERT INTO `plat_area` VALUES ('9', '650121', '乌鲁木齐县', '新疆乌鲁木齐市乌鲁木齐县', '1');
INSERT INTO `plat_area` VALUES ('10', '650200', '克拉玛依市', '新疆克拉玛依市', null);
INSERT INTO `plat_area` VALUES ('11', '650201', '克拉玛依市市辖区', '新疆克拉玛依市市辖区', '10');
INSERT INTO `plat_area` VALUES ('12', '650202', '克拉玛依市独山子区', '新疆克拉玛依市独山子区', '10');
INSERT INTO `plat_area` VALUES ('13', '650203', '克拉玛依市克拉玛依区', '新疆克拉玛依市克拉玛依区', '10');
INSERT INTO `plat_area` VALUES ('14', '650204', '克拉玛依市白碱滩区', '新疆克拉玛依市白碱滩区', '10');
INSERT INTO `plat_area` VALUES ('15', '650205', '克拉玛依市乌尔禾区', '新疆克拉玛依市乌尔禾区', '10');
INSERT INTO `plat_area` VALUES ('16', '650206', '独山子九公里大队', '新疆克拉玛依支队独山子九公里大队', '10');
INSERT INTO `plat_area` VALUES ('17', '650207', '克拉玛依市五五新镇辖区', '新疆克拉玛依市五五新镇辖区', '10');
INSERT INTO `plat_area` VALUES ('18', '650208', '克拉玛依九公里大队', '新疆克拉玛依支队九公里大队', '10');
INSERT INTO `plat_area` VALUES ('19', '650300', '石河子市', '新疆石河子市', null);
INSERT INTO `plat_area` VALUES ('20', '650301', '石河子市城区', '新疆石河子市城区', '19');
INSERT INTO `plat_area` VALUES ('21', '650302', '石河子市郊区', '新疆石河子市郊区', '19');
INSERT INTO `plat_area` VALUES ('22', '650303', '石河子市下野地', '新疆石河子市下野地', '19');
INSERT INTO `plat_area` VALUES ('23', '650304', '石河子市莫索湾', '新疆石河子市莫索湾', '19');
INSERT INTO `plat_area` VALUES ('24', '650305', '石河子市紫泥泉', '新疆石河子市紫泥泉', '19');
INSERT INTO `plat_area` VALUES ('25', '650306', '石河子市开发区', '新疆石河子市开发区', '19');
INSERT INTO `plat_area` VALUES ('26', '650307', '石河子市特勤大队', '新疆石河子市特勤大队', '19');
INSERT INTO `plat_area` VALUES ('27', '652100', '吐鲁番地区', '新疆吐鲁番地区', null);
INSERT INTO `plat_area` VALUES ('28', '652101', '吐鲁番市', '新疆吐鲁番市', '27');
INSERT INTO `plat_area` VALUES ('29', '652122', '鄯善县', '新疆吐鲁番市鄯善县', '27');
INSERT INTO `plat_area` VALUES ('30', '652123', '托克逊县', '新疆吐鲁番市托克逊县', '27');
INSERT INTO `plat_area` VALUES ('31', '652124', '吐哈交警大队', '新疆吐鲁番支队吐哈交警大队', '27');
INSERT INTO `plat_area` VALUES ('32', '652200', '哈密地区', '新疆哈密地区', null);
INSERT INTO `plat_area` VALUES ('33', '652201', '哈密市', '新疆哈密市', '32');
INSERT INTO `plat_area` VALUES ('34', '652222', '巴里坤县', '新疆哈密地区巴里坤县', '32');
INSERT INTO `plat_area` VALUES ('35', '652223', '伊吾县', '新疆哈密地区伊吾县', '32');
INSERT INTO `plat_area` VALUES ('36', '652224', '三道岭矿区', '新疆哈密地区三道岭矿区', '32');
INSERT INTO `plat_area` VALUES ('37', '652300', '昌吉地区', '新疆昌吉地区', null);
INSERT INTO `plat_area` VALUES ('38', '652301', '昌吉市', '新疆昌吉市', '37');
INSERT INTO `plat_area` VALUES ('39', '652322', '米泉市', '新疆昌吉州米泉市', '37');
INSERT INTO `plat_area` VALUES ('40', '652323', '呼图壁县', '新疆昌吉州呼图壁县', '37');
INSERT INTO `plat_area` VALUES ('41', '652324', '玛纳斯县', '新疆昌吉州玛纳斯县', '37');
INSERT INTO `plat_area` VALUES ('42', '652325', '奇台县', '新疆昌吉州奇台县', '37');
INSERT INTO `plat_area` VALUES ('43', '652326', '阜康市', '新疆昌吉州阜康市', '37');
INSERT INTO `plat_area` VALUES ('44', '652327', '吉木萨尔县', '新疆昌吉州吉木萨尔县', '37');
INSERT INTO `plat_area` VALUES ('45', '652328', '木垒县', '新疆昌吉州木垒县', '37');
INSERT INTO `plat_area` VALUES ('46', '652329', '准噶尔', '新疆昌吉州准噶尔', '37');
INSERT INTO `plat_area` VALUES ('47', '652330', '五家渠市', '新疆昌吉州五家渠市', '37');
INSERT INTO `plat_area` VALUES ('48', '652700', '博尔塔拉蒙古自治州', '新疆维吾尔自治区博尔塔拉蒙古自治州', null);
INSERT INTO `plat_area` VALUES ('49', '652721', '博乐市', '新疆维吾尔自治区博尔塔拉蒙古自治州博乐市', '48');
INSERT INTO `plat_area` VALUES ('50', '652722', '精河县', '新疆维吾尔自治区博尔塔拉蒙古自治州', '48');
INSERT INTO `plat_area` VALUES ('51', '652723', '温泉县', '新疆维吾尔自治区博尔塔拉蒙古自治州温泉县', '48');
INSERT INTO `plat_area` VALUES ('52', '652724', '阿拉山口', '新疆维吾尔自治区博尔塔拉蒙古自治州阿拉山口', '48');
INSERT INTO `plat_area` VALUES ('53', '652725', '五台大队', '博尔塔拉蒙古自治州五台高速交警大队', '48');
INSERT INTO `plat_area` VALUES ('54', '652726', '八家户大队', '博尔塔拉蒙古自治州八家户高速交警大队', '48');
INSERT INTO `plat_area` VALUES ('55', '652727', '博州支队特勤大队', '博尔塔拉蒙古自治州交警支队特勤大队', '48');
INSERT INTO `plat_area` VALUES ('56', '652728', '赛里木湖景区', '博尔塔拉蒙古自治州赛里木湖景区大队', '48');
INSERT INTO `plat_area` VALUES ('57', '652800', '巴音郭楞蒙古自治州', '巴音郭楞蒙古自治州', null);
INSERT INTO `plat_area` VALUES ('58', '652801', '巴州库尔勒市', '巴音郭楞蒙古自治州库尔勒市', '57');
INSERT INTO `plat_area` VALUES ('59', '652822', '巴州轮台县', '巴音郭楞蒙古自治州库尔勒市轮台县', '57');
INSERT INTO `plat_area` VALUES ('60', '652823', '巴州尉犁县', '巴音郭楞蒙古自治州库尔勒市尉犁县', '57');
INSERT INTO `plat_area` VALUES ('61', '652824', '巴州若羌县', '巴音郭楞蒙古自治州库尔勒市若羌县', '57');
INSERT INTO `plat_area` VALUES ('62', '652825', '巴州且末县', '巴音郭楞蒙古自治州库尔勒市且末县', '57');
INSERT INTO `plat_area` VALUES ('63', '652826', '巴州焉耆县', '巴音郭楞蒙古自治州库尔勒市焉耆县', '57');
INSERT INTO `plat_area` VALUES ('64', '652827', '巴州和静县', '巴音郭楞蒙古自治州库尔勒市和静县', '57');
INSERT INTO `plat_area` VALUES ('65', '652828', '巴州和硕县', '巴音郭楞蒙古自治州库尔勒市和硕县', '57');
INSERT INTO `plat_area` VALUES ('66', '652829', '巴州博湖县', '巴音郭楞蒙古自治州库尔勒市博湖县', '57');
INSERT INTO `plat_area` VALUES ('67', '652830', '巴州塔里木', '巴音郭楞蒙古自治州库尔勒市巴塔里木', '57');
INSERT INTO `plat_area` VALUES ('68', '652831', '巴州库尔勒高速公路大队', '巴音郭楞蒙古自治州库尔勒高速公路大队', '57');
INSERT INTO `plat_area` VALUES ('69', '652832', '巴州和硕高速公路大队', '巴音郭楞蒙古自治州和硕高速公路大队', '57');
INSERT INTO `plat_area` VALUES ('70', '652900', '阿克苏地区', '新疆阿克苏地区', null);
INSERT INTO `plat_area` VALUES ('71', '652901', '阿克苏市', '新疆阿克苏市', '70');
INSERT INTO `plat_area` VALUES ('72', '652922', '温宿县', '新疆阿克苏市温宿县', '70');
INSERT INTO `plat_area` VALUES ('73', '652923', '库车县', '新疆阿克苏市库车县', '70');
INSERT INTO `plat_area` VALUES ('74', '652924', '沙雅县', '新疆阿克苏市沙雅县', '70');
INSERT INTO `plat_area` VALUES ('75', '652925', '新和县', '新疆阿克苏市新和县', '70');
INSERT INTO `plat_area` VALUES ('76', '652926', '拜城县', '新疆阿克苏市拜城县', '70');
INSERT INTO `plat_area` VALUES ('77', '652927', '乌什县', '新疆阿克苏市乌什县', '70');
INSERT INTO `plat_area` VALUES ('78', '652928', '阿瓦提县', '新疆阿克苏市阿瓦提县', '70');
INSERT INTO `plat_area` VALUES ('79', '652929', '柯坪县', '新疆阿克苏市柯坪县', '70');
INSERT INTO `plat_area` VALUES ('80', '652932', '阿拉尔市', '新疆阿克苏地区阿拉尔市', '70');
INSERT INTO `plat_area` VALUES ('81', '653000', '克州', '新疆克孜勒苏柯尔克孜自治州', null);
INSERT INTO `plat_area` VALUES ('82', '653001', '阿图什市', '新疆克孜勒苏柯尔克孜自治州阿图什市', '81');
INSERT INTO `plat_area` VALUES ('83', '653022', '阿克陶县', '新疆克孜勒苏柯尔克孜自治州阿克陶县', '81');
INSERT INTO `plat_area` VALUES ('84', '653023', '阿合奇县', '新疆克孜勒苏柯尔克孜自治州阿合奇县', '81');
INSERT INTO `plat_area` VALUES ('85', '653024', '乌恰县', '新疆克孜勒苏柯尔克孜自治州乌恰县', '81');
INSERT INTO `plat_area` VALUES ('86', '653025', '托帕口岸', '新疆克孜勒苏柯尔克孜自治州托帕口岸', '81');
INSERT INTO `plat_area` VALUES ('87', '653100', '喀什地区交警支队', '新疆喀什地区公安交通警察支队', null);
INSERT INTO `plat_area` VALUES ('88', '653101', '喀什市', '新疆喀什市交警大队', '87');
INSERT INTO `plat_area` VALUES ('89', '653121', '疏附县', '新疆喀什地区疏附县', '87');
INSERT INTO `plat_area` VALUES ('90', '653122', '疏勒县', '疏勒县', '87');
INSERT INTO `plat_area` VALUES ('91', '653123', '英吉沙县', '新疆喀什地区英吉沙县', '87');
INSERT INTO `plat_area` VALUES ('92', '653124', '泽普县', '新疆喀什地区泽普县', '87');
INSERT INTO `plat_area` VALUES ('93', '653125', '莎车县', '新疆喀什地区莎车县', '87');
INSERT INTO `plat_area` VALUES ('94', '653126', '叶城县', '新疆喀什地区叶城县', '87');
INSERT INTO `plat_area` VALUES ('95', '653127', '麦盖提县', '新疆喀什地区麦盖提县', '87');
INSERT INTO `plat_area` VALUES ('96', '653128', '岳普湖县', '新疆喀什地区岳普湖县', '87');
INSERT INTO `plat_area` VALUES ('97', '653129', '伽师县', '新疆喀什地区伽师县', '87');
INSERT INTO `plat_area` VALUES ('98', '653130', '巴楚县', '新疆喀什地区巴楚县', '87');
INSERT INTO `plat_area` VALUES ('99', '653131', '塔什库尔干县', '新疆喀什地区塔什库尔干县', '87');
INSERT INTO `plat_area` VALUES ('100', '653132', '塔西南石油', '新疆喀什地区塔西南石油', '87');
INSERT INTO `plat_area` VALUES ('101', '653133', '图木舒克市', '新疆喀什地区图木舒克市', '87');
INSERT INTO `plat_area` VALUES ('102', '653200', '和田地区', '新疆和田地区', null);
INSERT INTO `plat_area` VALUES ('103', '653201', '和田市', '新疆和田地区和田市', '102');
INSERT INTO `plat_area` VALUES ('104', '653221', '和田县', '新疆和田市和田县', '102');
INSERT INTO `plat_area` VALUES ('105', '653222', '墨玉县', '新疆和田市墨玉县', '102');
INSERT INTO `plat_area` VALUES ('106', '653223', '皮山县', '新疆和田市皮山县', '102');
INSERT INTO `plat_area` VALUES ('107', '653224', '洛浦县', '新疆和田市洛浦县', '102');
INSERT INTO `plat_area` VALUES ('108', '653225', '策勒县', '新疆和田市策勒县', '102');
INSERT INTO `plat_area` VALUES ('109', '653226', '于田县', '新疆和田市于田县', '102');
INSERT INTO `plat_area` VALUES ('110', '653227', '民丰县', '新疆和田市民丰县', '102');
INSERT INTO `plat_area` VALUES ('111', '653228', '直属大队', '新疆和田市直属大队', '102');
INSERT INTO `plat_area` VALUES ('112', '654100', '伊犁哈萨克自治州', '新疆伊犁哈萨克自治州', null);
INSERT INTO `plat_area` VALUES ('113', '654101', '伊宁市', '新疆伊宁市', '112');
INSERT INTO `plat_area` VALUES ('114', '654104', '奎屯市', '新疆伊犁州奎屯市', '112');
INSERT INTO `plat_area` VALUES ('115', '654121', '伊宁县', '新疆伊宁县', '112');
INSERT INTO `plat_area` VALUES ('116', '654122', '察布查尔县', '新疆察布查尔县', '112');
INSERT INTO `plat_area` VALUES ('117', '654123', '霍城县', '新疆霍城县', '112');
INSERT INTO `plat_area` VALUES ('118', '654124', '巩留县', '新疆巩留县', '112');
INSERT INTO `plat_area` VALUES ('119', '654125', '新源县', '新疆新源县', '112');
INSERT INTO `plat_area` VALUES ('120', '654126', '昭苏县', '新疆昭苏县', '112');
INSERT INTO `plat_area` VALUES ('121', '654127', '特克斯县', '新疆特克斯县', '112');
INSERT INTO `plat_area` VALUES ('122', '654128', '尼勒克县', '新疆尼勒克县', '112');
INSERT INTO `plat_area` VALUES ('123', '654129', '霍尔果斯口岸', '新疆霍尔果斯口岸', '112');
INSERT INTO `plat_area` VALUES ('124', '654130', '伊犁州公安局交警支队特勤大队', '伊犁州公安局交警支队特勤大队', '112');
INSERT INTO `plat_area` VALUES ('125', '654133', '高速公路果子沟交警大队', '伊犁州公安局交通警察支队高速公路果子沟交通警察大队', '112');
INSERT INTO `plat_area` VALUES ('126', '654134', '高速公路清水河交警大队', '伊犁州公安局交通警察支队高速公路清水河交通警察大队', '112');
INSERT INTO `plat_area` VALUES ('127', '654135', '高速公路巴彦岱交警大队', '伊犁州公安局交通警察支队高速公路巴彦岱交通警察大队', '112');
INSERT INTO `plat_area` VALUES ('128', '654200', '塔城地区', '新疆塔城地区', null);
INSERT INTO `plat_area` VALUES ('129', '654201', '塔城市', '新疆塔城地区塔城市', '128');
INSERT INTO `plat_area` VALUES ('130', '654221', '额敏县', '新疆塔城市额敏县', '128');
INSERT INTO `plat_area` VALUES ('131', '654224', '托里县', '新疆塔城市托里县', '128');
INSERT INTO `plat_area` VALUES ('132', '654225', '裕民县', '新疆塔城市裕民县', '128');
INSERT INTO `plat_area` VALUES ('133', '654227', '巴克图', '新疆塔城市巴克图', '128');
INSERT INTO `plat_area` VALUES ('134', '654300', '阿勒泰地区', '新疆阿勒泰地区', null);
INSERT INTO `plat_area` VALUES ('135', '654301', '阿勒泰市', '新疆阿勒泰市', '134');
INSERT INTO `plat_area` VALUES ('136', '654320', '新疆阿勒泰市北屯镇', '新疆阿勒泰市北屯镇', '134');
INSERT INTO `plat_area` VALUES ('137', '654321', '布尔津县', '新疆阿勒泰市布尔津县', '134');
INSERT INTO `plat_area` VALUES ('138', '654322', '富蕴县', '新疆阿勒泰市富蕴县', '134');
INSERT INTO `plat_area` VALUES ('139', '654323', '福海县', '新疆阿勒泰市福海县', '134');
INSERT INTO `plat_area` VALUES ('140', '654324', '哈巴河县', '新疆阿勒泰市哈巴河县', '134');
INSERT INTO `plat_area` VALUES ('141', '654325', '青河县', '新疆阿勒泰市青河县', '134');
INSERT INTO `plat_area` VALUES ('142', '654326', '吉木乃县', '新疆阿勒泰市吉木乃县', '134');
INSERT INTO `plat_area` VALUES ('143', '654327', '喀纳斯景区分局', '新疆阿勒泰市喀纳斯景区分局', '134');
INSERT INTO `plat_area` VALUES ('144', '654328', '北屯市', '', '134');
INSERT INTO `plat_area` VALUES ('145', '659700', '乌苏车辆管理所', '', null);
INSERT INTO `plat_area` VALUES ('146', '659701', '乌苏市', '新疆乌苏市', '145');
INSERT INTO `plat_area` VALUES ('147', '659702', '和布克赛尔蒙古自治县', '新疆和布克赛尔蒙古自治县', '145');
INSERT INTO `plat_area` VALUES ('148', '659703', '沙湾县', '新疆沙湾县', '145');

-- ----------------------------
-- Table structure for school_class
-- ----------------------------
DROP TABLE IF EXISTS `school_class`;
CREATE TABLE `school_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `school_id` int(11) DEFAULT NULL,
  `car_model_code` varchar(5) DEFAULT NULL,
  `car_model` varchar(10) DEFAULT NULL,
  `start_time` date DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school_class
-- ----------------------------
INSERT INTO `school_class` VALUES ('1', '1', 'A1', '大型车', '2018-01-31', '9997');
INSERT INTO `school_class` VALUES ('2', '2', 'C1', '小型车', '2018-01-30', '7999');

-- ----------------------------
-- Table structure for shuffling
-- ----------------------------
DROP TABLE IF EXISTS `shuffling`;
CREATE TABLE `shuffling` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `image_path` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shuffling
-- ----------------------------
INSERT INTO `shuffling` VALUES ('1', '百度', 'http://ww.baidu.com', 'img/1.png');
INSERT INTO `shuffling` VALUES ('2', '阿里', 'http://alibaba.com', 'img/1.png');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `nation` varchar(20) DEFAULT NULL COMMENT '民族',
  `certificate_type` varchar(2) DEFAULT NULL,
  `certificate_code` varchar(30) DEFAULT NULL,
  `birth_day` date DEFAULT NULL,
  `country_code` varchar(5) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `car_model_code` varchar(4) DEFAULT NULL,
  `car_model` varchar(20) DEFAULT NULL,
  `driving_school_id` int(11) DEFAULT NULL,
  `driving_school` varchar(50) DEFAULT NULL,
  `register_area` varchar(50) DEFAULT NULL,
  `register_address` varchar(50) DEFAULT NULL,
  `source` varchar(4) DEFAULT NULL,
  `temporary_residence_code` varchar(30) DEFAULT NULL COMMENT '暂住证编号',
  `contact_area` varchar(50) DEFAULT NULL,
  `contact_address` varchar(50) DEFAULT NULL,
  `contact_jurisdiction` varchar(50) DEFAULT NULL,
  `contact_zip` varchar(6) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `mobile1` varchar(13) DEFAULT NULL,
  `mobile2` varchar(30) DEFAULT NULL,
  `idcard_before` varchar(5000) DEFAULT NULL,
  `idcard_back` varchar(5000) DEFAULT NULL,
  `scene_picture` varchar(5000) DEFAULT NULL,
  `fingerprint` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '123', '1', null, 'C1', '123', '2017-01-01', '123', '123', '123', '123', '1', '123', '123', '123', '123', '123', '123', '123', '123', '123', '123', '123', '123', null, null, null, null);
INSERT INTO `student` VALUES ('2', '123', '1', null, 'C1', '123', '2017-01-01', '123', '123', '123', '123', '1', '123', '123', '123', '123', '123', '123', '123', '123', '123', '123', '123', '123', null, null, null, null);
INSERT INTO `student` VALUES ('3', '123', '1', null, 'C1', '123', '2017-01-01', '123', '123', '123', '123', '1', '123', '123', '123', '123', '123', '123', '123', '123', '123', '123', '123', '123', null, null, null, null);
INSERT INTO `student` VALUES ('4', '123', '1', null, 'C1', '123', '2017-01-01', '123', '123', '123', '123', '1', '123', '123', '123', '123', '123', '123', '123', '123', '123', '123', '123', '123', null, null, null, null);
INSERT INTO `student` VALUES ('5', '樊运哲', '1', '汉', 'C1', '410482199311192315', '2017-01-01', '123', '123', '123', '123', '1', '123', '123', '河南省汝州市杨楼乡杨楼村教育办', '123', '123', '123', '123', '123', '123', '123', '123', '123', null, null, null, null);
INSERT INTO `student` VALUES ('6', '汪瑞源', '1', '汉', 'C1', '41152419840817471X', '2017-01-01', '123', '123', '123', '123', '1', '123', '123', '河南省商城县鲇鱼山乡街道市民组', '123', '123', '123', '123', '123', '123', '123', '123', '123', null, null, null, null);
INSERT INTO `student` VALUES ('7', '汪瑞源', '1', '汉', 'A', '41152419840817471X', '1984-08-17', '156', '中国/CHN', 'C1', '小型汽车', '1', '乌鲁木齐文博驾校', '登记住所行政区域', '河南省商城县鲇鱼山乡街道市民组', '2', '暂住证编号', '联系住所行政区域', '联系住所详细地址', '650102', '465350', '021-89898989', '13989898989', '13789898989', null, null, null, null);
INSERT INTO `student` VALUES ('8', '汪瑞源', '1', '汉', 'A', '41152419840817471X', '1984-08-17', '156', '中国/CHN', 'C1', '小型汽车', '1', '叫姐姐', '登记住所行政区域', '河南省商城县鲇鱼山乡街道市民组', '2', '暂住证编号', '联系住所行政区域', '联系住所详细地址', '650102', '465350', '021-89898989', '13989898989', '13789898989', null, null, null, null);
INSERT INTO `student` VALUES ('9', '汪瑞源', '1', '汉', 'A', '41152419840817471X', '1984-08-17', '156', '中国/CHN', 'C1', '小型汽车', '1', '叫姐姐', '登记住所行政区域', '河南省商城县鲇鱼山乡街道市民组', '2', '暂住证编号', '联系住所行政区域', '联系住所详细地址', '650102', '465350', '021-89898989', '13989898989', '13789898989', null, null, null, null);
INSERT INTO `student` VALUES ('10', '汪瑞源', '1', '汉', 'A', '41152419840817471X', '1984-08-17', '156', '中国/CHN', 'C1', '小型汽车', '1', '乌鲁木齐文博驾校', '', '河南省商城县鲇鱼山乡街道市民组', '1', '', '', '', '650102', '', '', '', '', null, null, null, '464132413833303105934C988228006E0160080AE405E005125E158002130F25C00C09E04C60051A8C54200F08DB7C20052ADBA9800533E7C4800632E5E4A0063DE108A1064CE420E10649D0344101183E59010011965CA113063F7121021674A1E109101F2C820C0F27386214088358420B322971020D149D74C213072D7C4213069D84A20F0C2D9002100A4F95E2071B31C4C20C093CE482080E3E30C30B0AAB34C30E07AC58C31203386C431304AB740314053C84630F0745B0C30E0645D0830C0540D4831406B8D8830D05BD24E40D09BC24E40F040F15E1C60305C0C45D74A1EBECC0C360697501040D1316C0C25758637377050D15171BC0C24F55647277050E18191CC0C14B46445C707708101A1B1F1FC0C155584A5974010F19A1B9C8C07E474A504252760318A1F6CA24C07E40444B475C7705161FA15D9BC0C13E3F7E667501111CA17E8CC0C575770F1FA14E8BC0C6751824A15C7BC0C6362AA1C5A72DC0C63D32A176972EC0C53EA253678831C0C53FA274667632C0C53FA287567735C0C54081A1777438C0C542A29876763BC0C543A2997676E0');
INSERT INTO `student` VALUES ('11', '汪瑞源', '1', '汉', 'A', '41152419840817471X', '1984-08-17', '156', '中国/CHN', 'C1', '小型汽车', '1', '乌鲁木齐文博驾校', '', '河南省商城县鲇鱼山乡街道市民组', '1', '', '', '', '650102', '', '', '', '', null, null, null, '46413241383330310F8E3278832300CC01C008565815C0062381502011085D60C00618D08440011167A9400A13E1B1A0082B06C1200E08C8DD60082C4DE9600523D1EC20021EBFF1000008BBF140000A84F9C01108C201A10827D520C10936B83821010BDF7C410A498AE4A11608C10802050E494042070EB9494202037157A20D13B279020101EC82C20C0B16DE02110F810A431020241B0312133223830E505E36830D332A4E63110C306FC30F0D3CAEC30D1436B2E3110D3C0064150601311D230E0F15E1C5445377010406C0C457485B72A1CEBCC0C35157505C6EA1FEDA0CC0C24B5258555F6FA2DEFBAAC0C14247565953536B7301090E1012C0C140434E55444A6973770A12131517C13F43514E40415771010913151819C2454C4E403F4771050D1517191AC247A1A546401BA215D9A8C241A17CA9475D10A1AC6B18C23DA18CEC596B020DA1E7D9C23F3E414C52616F730A14151B1DC341417E415F70740F1A1B2121C75664701C24252827C74B4B402DA177A7C746A243066830C83FA2457687C83FA2757887C83F823E3D3DC940A19877E0010058C9B0C347BE403659CE445357B05E4D5943BD4CC54AB84C354BDC5352715F6E7B645965655A5C6150504B4B4E5459584646414F36333226361938140E080F01030E0B121728');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `resource_type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  `available` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '运维菜单', 'url', null, null, null, null, '1');
INSERT INTO `sys_permission` VALUES ('5', '线下课程', 'url', 'ops/courseoffline/index', 'ops:course_offline', '1', null, '1');
INSERT INTO `sys_permission` VALUES ('6', '教师管理', 'url', 'ops/teacher/index', 'ops:teacher', '1', null, '1');
INSERT INTO `sys_permission` VALUES ('7', '线上课程', 'url', 'ops/courseonline/index', 'ops:course_online', '1', null, '1');
INSERT INTO `sys_permission` VALUES ('8', '交通安全法律法规', 'url', 'ops/trafficlaw/index', 'ops:trafficlaw', '1', '', '1');
INSERT INTO `sys_permission` VALUES ('9', '驾考相关政策规定', 'url', 'ops/drivingpolicy/index', 'ops:drivingpolicy', '1', '', '1');
INSERT INTO `sys_permission` VALUES ('10', '平台网点', 'url', 'ops/platformservicenetwork/index', 'ops:platformservicenetwork', '1', '', '1');
INSERT INTO `sys_permission` VALUES ('11', '驾校信息', 'url', 'ops/school/index', 'ops:school', '1', '', '1');
INSERT INTO `sys_permission` VALUES ('12', '学员信息', 'url', 'ops/student/index', 'ops:student', '1', '', '1');
INSERT INTO `sys_permission` VALUES ('21', '驾校管理', 'url', '', '', null, '', '1');
INSERT INTO `sys_permission` VALUES ('22', '学员信息管理', 'url', 'sch/student/index', 'sch:student', '21', '', '1');
INSERT INTO `sys_permission` VALUES ('23', '开班信息管理', 'url', 'sch/schoolclass/index', 'sch:schoolclass', '21', '', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `available` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '管理员', 'true');
INSERT INTO `sys_role` VALUES ('2', 'drivingschool', '驾校', 'true');
INSERT INTO `sys_role` VALUES ('3', 'teacher', '教师', 'true');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('4', '1', '1');
INSERT INTO `sys_role_permission` VALUES ('5', '1', '5');
INSERT INTO `sys_role_permission` VALUES ('6', '1', '6');
INSERT INTO `sys_role_permission` VALUES ('7', '1', '7');
INSERT INTO `sys_role_permission` VALUES ('8', '1', '8');
INSERT INTO `sys_role_permission` VALUES ('9', '1', '9');
INSERT INTO `sys_role_permission` VALUES ('10', '1', '10');
INSERT INTO `sys_role_permission` VALUES ('11', '1', '11');
INSERT INTO `sys_role_permission` VALUES ('12', '1', '12');
INSERT INTO `sys_role_permission` VALUES ('22', '4', '21');
INSERT INTO `sys_role_permission` VALUES ('23', '4', '22');
INSERT INTO `sys_role_permission` VALUES ('24', '4', '23');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `state` int(2) DEFAULT NULL,
  `user_type` int(1) DEFAULT 0 COMMENT '用户类型 0：管理员 1：驾校 2：老师',
  `relate_id` int(11) DEFAULT 0 COMMENT '关联驾校或者老师id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'zhang', '123', '张', '123', '1');
INSERT INTO `sys_user` VALUES ('2', 'jiaxiao1', '123', '文博', '123', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '1', '2');
INSERT INTO `sys_user_role` VALUES ('3', '2', '4');

-- ----------------------------
-- Table structure for s_m_s
-- ----------------------------
DROP TABLE IF EXISTS `s_m_s`;
CREATE TABLE `s_m_s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(50) DEFAULT NULL,
  `code` varchar(2000) DEFAULT NULL,
  `type` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_m_s
-- ----------------------------
INSERT INTO `s_m_s` VALUES ('1', '13918643756', '3004', '1', '2018-01-23 09:37:56');
INSERT INTO `s_m_s` VALUES ('2', '13918643756', '4225', '1', '2018-01-23 15:28:09');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', 'C1');
INSERT INTO `tag` VALUES ('2', 'C2');
INSERT INTO `tag` VALUES ('3', '拿证快');
INSERT INTO `tag` VALUES ('4', '有耐心');
INSERT INTO `tag` VALUES ('5', '方法科学');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `school_id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `teaching_age` int(11) DEFAULT NULL,
  `tag` varchar(200) DEFAULT NULL,
  `avator` varchar(200) DEFAULT NULL,
  `introduce` varchar(1000) DEFAULT NULL,
  `teaching_address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '1', '李明', '9', '1,2,3', 'abc', null, '');
INSERT INTO `teacher` VALUES ('2', '1', '王二', '10', '1,2,3', 'abc', null, '人民路');
INSERT INTO `teacher` VALUES ('3', '1', '3', '10', '1,2,3', 'abc', null, null);
INSERT INTO `teacher` VALUES ('4', '1', '库尔班-热合曼4', '10', '1,2,3', 'abc', null, null);
INSERT INTO `teacher` VALUES ('5', '1', '库尔班-热合曼5', '10', '1,2,3', 'abc', null, null);

-- ----------------------------
-- Table structure for teacher_tag
-- ----------------------------
DROP TABLE IF EXISTS `teacher_tag`;
CREATE TABLE `teacher_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_tag
-- ----------------------------
INSERT INTO `teacher_tag` VALUES ('1', 'C1');
INSERT INTO `teacher_tag` VALUES ('2', 'C2');
INSERT INTO `teacher_tag` VALUES ('3', '拿证快');
INSERT INTO `teacher_tag` VALUES ('4', '耐心');
INSERT INTO `teacher_tag` VALUES ('5', null);

-- ----------------------------
-- Table structure for traffic_law
-- ----------------------------
DROP TABLE IF EXISTS `traffic_law`;
CREATE TABLE `traffic_law` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `image_path` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of traffic_law
-- ----------------------------
INSERT INTO `traffic_law` VALUES ('1', '驾考法规1', '内容1第三方撒打算', 'abc');
INSERT INTO `traffic_law` VALUES ('2', '驾考法规1', '内容1', 'abc');
INSERT INTO `traffic_law` VALUES ('3', '驾考法规1', '内容1', 'abc');
INSERT INTO `traffic_law` VALUES ('4', '驾考法规1', '内容1', 'abc');
INSERT INTO `traffic_law` VALUES ('5', '驾考法规1', '内容1', 'abc');

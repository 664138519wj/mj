DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `school_id` int(11) DEFAULT NULL COMMENT '所属驾校',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `teaching_age` int(11) DEFAULT NULL COMMENT '教龄',
  `tag` varchar(200) DEFAULT NULL COMMENT '标签 逗号分隔',
  `avator` varchar(200) DEFAULT NULL COMMENT '头像地址',
  `introduce` varchar(1000) DEFAULT NULL COMMENT '简介',
  `teaching_address` varchar(100) DEFAULT NULL COMMENT '授课地址',
  `is_delete` int(1) DEFAULT 0 COMMENT '是否删除',
  `recommand` int(1) DEFAULT 0 COMMENT '1：首页推荐， 0：不推荐',
  `sort` int(11) DEFAULT 0 COMMENT '排序，越大排名靠前',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '1', '李明', '9', '1,2,3', '/resource/image/aa.jpg', '十年教龄，老司机', '北京人民大会堂', 0, 1,10, now(), now());
INSERT INTO `teacher` VALUES ('2', '1', '王二', '10', '1,2,3', '/resource/image/aa.jpg', '十年教龄，老司机', '人民路', 0, 1, 10, now(), now());
INSERT INTO `teacher` VALUES ('3', '1', '丽霞龙', '10', '1,2,3', '/resource/image/aa.jpg', '十年教龄，老司机', '北京人民大会堂', 0, 1, 10, now(), now());
INSERT INTO `teacher` VALUES ('4', '1', '库尔班-热合曼4', '10', '1,2,3', '/resource/image/aa.jpg', '十年教龄，老司机', '北京人民大会堂', 0, 1, 10, now(), now());
INSERT INTO `teacher` VALUES ('5', '1', '库尔班-热合曼5', '10', '1,2,3', '/resource/image/aa.jpg', '十年教龄，老司机', '北京人民大会堂', 0, 1, 10, now(), now());

-- ----------------------------
-- Table structure for shuffling
-- ----------------------------
DROP TABLE IF EXISTS `shuffling`;
CREATE TABLE `shuffling` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL COMMENT '名称',
  `url` varchar(200) DEFAULT NULL COMMENT '链接',
  `image_path` varchar(200) DEFAULT NULL COMMENT '图片路径',
  `is_delete` int(1) DEFAULT 0 COMMENT '是否删除',
  `sort` int(11) DEFAULT NULL COMMENT '排序，越大排名靠前',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shuffling
-- ----------------------------
INSERT INTO `shuffling` VALUES ('1', '百度', 'http://ww.baidu.com', '/resource/image/1.png', 0, 1, now(), now());
INSERT INTO `shuffling` VALUES ('2', '阿里', 'http://alibaba.com', '/resource/image/2.png', 0, 2, now(), now());
INSERT INTO `shuffling` VALUES ('3', '百度', 'http://ww.baidu.com', '/resource/image/1.png', 0, 3, now(), now());
INSERT INTO `shuffling` VALUES ('4', '阿里', 'http://alibaba.com', '/resource/image/2.png', 0, 4, now(), now());
INSERT INTO `shuffling` VALUES ('5', '百度', 'http://ww.baidu.com', '/resource/image/1.png', 0, 5, now(), now());

-- ----------------------------
-- Table structure for course_offline
-- ----------------------------
DROP TABLE IF EXISTS `course_offline`;
CREATE TABLE `course_offline` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plat_id` int(11) DEFAULT NULL COMMENT '线下网点id',
  `title` varchar(50) DEFAULT NULL COMMENT '课程标题',
  `price` float DEFAULT 0 COMMENT '价格',
  `type` int(10) DEFAULT 0 COMMENT '1：科目一 2：科目二 3：科目三 4：科目四',
  `content` varchar(2000) DEFAULT NULL COMMENT '内容',
  `image_path` varchar(200) DEFAULT NULL COMMENT '图片',
  `start_time` datetime DEFAULT NULL COMMENT '开课时间',
  `hours` varchar(50) DEFAULT NULL COMMENT '课时',
  `address` varchar(100) DEFAULT NULL COMMENT '开课地址',
  `recommand` int(1) DEFAULT 0 COMMENT '1:首页推荐 0：不推荐',
  `teachers` varchar(100) DEFAULT NULL COMMENT '课程关联老师 老师id逗号分隔',
  `is_delete` int(1) DEFAULT 0 COMMENT '是否删除',
  `sort` int(11) DEFAULT NULL COMMENT '排序，越大排名靠前',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of course_offline
-- ----------------------------
INSERT INTO `course_offline` VALUES (1,1, '线下1', 5909, 1,'内容1','/resource/image/2.png','2018-06-06 08:00:00', '5小时', '人民路100号', 1, '4,5,6', 0, 1, now(), now());
INSERT INTO `course_offline` VALUES (2,1, '线下2', 5909, 1,'内容1','/resource/image/2.png','2018-06-06 08:00:00', '5小时', '人民路100号', 1, '4,5,6', 0, 1, now(), now());
INSERT INTO `course_offline` VALUES (3,1, '线下3', 5909, 1,'内容1','/resource/image/2.png','2018-06-06 08:00:00', '5小时', '人民路100号', 1, '4,5,6', 0, 1, now(), now());
INSERT INTO `course_offline` VALUES (4,1, '线下1', 5909, 1,'内容1','/resource/image/2.png','2018-06-06 08:00:00', '5小时', '人民路100号', 1, '4,5,6', 0, 1, now(), now());

-- ----------------------------
-- Table structure for course_online
-- ----------------------------
DROP TABLE IF EXISTS `course_online`;
CREATE TABLE `course_online` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_id` int(11) DEFAULT NULL COMMENT '教师id',
  `type` int(11) DEFAULT NULL COMMENT '1：科目一 2：科目二 3：科目三 4：科目四',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) DEFAULT NULL COMMENT '内容描述',
  `video_path` varchar(200) DEFAULT NULL COMMENT '视频地址',
  `small_video_path` varchar(200) DEFAULT NULL COMMENT '短视频地址',
  `image_path` varchar(200) DEFAULT NULL COMMENT '图片地址',
  `is_hot_and_charge` int(1) DEFAULT NULL COMMENT '是否热点和付费 0-都不是,1-热点,2-付费,3-热点且付费',
  `hour` int(11) DEFAULT NULL COMMENT '时',
  `minute` int(11) DEFAULT NULL COMMENT '分',
  `second` int(11) DEFAULT NULL COMMENT '秒',
  `play_number` int(11) DEFAULT NULL COMMENT '点播次数',
  `price` DECIMAL(6,2) DEFAULT 0.00 COMMENT '价格',
  `recommand` int(1) DEFAULT 0 COMMENT '1:首页推荐 0：不推荐',
  `is_list_rec` int(1) DEFAULT 0 COMMENT '1:列表页推荐 0：不推荐',
  `is_delete` int(1) DEFAULT 0 COMMENT '是否删除',
  `sort` int(11) DEFAULT NULL COMMENT '排序，越大排名靠前',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_online
-- ----------------------------
INSERT INTO `course_online` VALUES (1, 1, 1, '线上1', '内容1236789', '/resouce/video/1.mp3', '/resouce/image/2.png', 0, 1, 1, 1, 1, 1, 0, 0, 1, now(), now());
INSERT INTO `course_online` VALUES (2, 1, 1, '线上1', '内容1236789', '/resouce/video/1.mp3', '/resouce/image/2.png', 1, 1, 1, 1, 1, 1, 00, 1, now(), now());
INSERT INTO `course_online` VALUES (3, 1, 1, '线上1', '内容1236789', '/resouce/video/1.mp3', '/resouce/image/2.png', 2, 1, 1, 1, 1, 1, 0, 1, now(), now());
INSERT INTO `course_online` VALUES (4, 1, 1, '线上1', '内容1236789', '/resouce/video/1.mp3', '/resouce/image/2.png', 3, 1, 1, 1, 1, 1, 0, 1, now(), now());


-- ----------------------------
-- Table structure for traffic_law
-- ----------------------------
DROP TABLE IF EXISTS `traffic_law`;
CREATE TABLE `traffic_law` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `summary` varchar(5000) DEFAULT NULL COMMENT '缩略内容',
  `content` varchar(100000) DEFAULT NULL COMMENT '详情内容',
  `image_path` varchar(200) DEFAULT NULL COMMENT '图片',
  `topic` int(5) DEFAULT NULL COMMENT '章节',
  `is_delete` int(1) DEFAULT 0 COMMENT '是否删除',
  `relate_id` varchar(30) COMMENT '上下关联文章 分号分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of traffic_law
-- ----------------------------
INSERT INTO `traffic_law` VALUES (1, '驾考法规1', '内容1第三方撒打算','内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算','/upload/1.png', 1, 0);
INSERT INTO `traffic_law` VALUES (2, '驾考法规2','内容2第三方撒打算','内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算', '/upload/1.png', 2, 0);
INSERT INTO `traffic_law` VALUES (3, '驾考法规3', '内容3第三方撒打算','内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算', '/upload/1.png', 3, 0);
INSERT INTO `traffic_law` VALUES (4, '驾考法规4', '内容4第三方撒打算','内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算', '/upload/1.png', 4, 0);
INSERT INTO `traffic_law` VALUES (5, '驾考法规5', '内容5第三方撒打算','内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算', '/upload/1.png', 5, 0);

-- ----------------------------
-- Table structure for driving_policy
-- ----------------------------
DROP TABLE IF EXISTS `driving_policy`;
CREATE TABLE `driving_policy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `summary` varchar(1000) DEFAULT NULL COMMENT '缩略内容',
  `content` varchar(5000) DEFAULT NULL COMMENT '详情内容',
  `image_path` varchar(200) DEFAULT NULL COMMENT '图片',
  `is_delete` int(1) DEFAULT 0 COMMENT '是否删除',
  `relate_id` varchar(30) COMMENT '上下关联文章 分号分隔',
  `update_time` datetime COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of driving_policy
-- ----------------------------
INSERT INTO `driving_policy` VALUES (1, '驾考法规1', '内容1第三方撒打算','内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算','/upload/1.png', 0);
INSERT INTO `driving_policy` VALUES (2, '驾考法规2', '内容1第三方撒打算','内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算','/upload/1.png', 0);
INSERT INTO `driving_policy` VALUES (3, '驾考法规3', '内容1第三方撒打算','内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算','/upload/1.png', 0);
INSERT INTO `driving_policy` VALUES (4, '驾考法规4', '内容1第三方撒打算','内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算','/upload/1.png', 0);
INSERT INTO `driving_policy` VALUES (5, '驾考法规5', '内容1第三方撒打算','内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算','/upload/1.png', 1);


-- ----------------------------
-- Table structure for platform_network
-- ----------------------------
DROP TABLE IF EXISTS `platform_network`;
CREATE TABLE `platform_network` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名字',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `tel` varchar(20) DEFAULT NULL  COMMENT '电话',
  `school_id` int(11) DEFAULT NULL COMMENT '驾校id',
  `image_path` varchar(200) DEFAULT NULL COMMENT '图片',
  `shuffling_image` varchar(2000) DEFAULT NULL COMMENT '图片轮播表 分号分隔',
  `is_delete` int(1) DEFAULT 0 COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of platform_network
-- ----------------------------

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(50) DEFAULT NULL COMMENT '城市', 
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of area
-- ----------------------------
insert into area values(1, '乌鲁木齐');
insert into area values(2, '昌吉');
insert into area values(3, '石河子');
insert into area values(4, '奎屯');
insert into area values(5, '博州');
insert into area values(6, '伊犁');
insert into area values(7, '塔城');
insert into area values(8, '乌苏');

-- ----------------------------
-- Table structure for licence
-- ----------------------------
DROP TABLE IF EXISTS `licence`;
CREATE TABLE `licence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL COMMENT '驾照code', 
  `name` varchar(50) DEFAULT NULL COMMENT '车型', 
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of licence
-- ----------------------------
insert into licence values(1, 'A1', '大型客车');
insert into licence values(2, 'A2', '牵引车');
insert into licence values(3, 'A3', '城市公共汽车');
insert into licence values(4, 'B1', '中型客车');
insert into licence values(5, 'B2', '大型货车');
insert into licence values(6, 'C1', '小型汽车');
insert into licence values(7, 'C2', '小型自动挡汽车');
insert into licence values(8, 'C3', '三轮汽车');


-- ----------------------------
-- Table structure for driving_school
-- ----------------------------
DROP TABLE IF EXISTS `driving_school`;
CREATE TABLE `driving_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_s` varchar(50) DEFAULT NULL COMMENT '缩略名',
  `name_l` varchar(50) DEFAULT NULL COMMENT '详情名字',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `area_id` int(11) DEFAULT NULL COMMENT '地区id',
  `tel` varchar(20) DEFAULT NULL  COMMENT '电话',
  `price` Decimal(6,2) DEFAULT 0.00 COMMENT '价格',
  `image_path` varchar(200) DEFAULT NULL COMMENT '图片',
  `shuffling_image` varchar(2000) DEFAULT NULL COMMENT '图片轮播表 分号分隔',
  `licence` varchar(200) DEFAULT NULL COMMENT '培训课程类型: 逗号分隔 A1(大型客车)，A2(牵引车)',
  `is_delete` int(1) DEFAULT 0 COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert into driving_school values (1, '海天驾校', '【海天驾校】波尔塔拉相逢-风向驾校', '新疆xx小区',1,'11212121',5000,'/upload/1.png','/upload/1.png;/upload/1.png','A1(大型客车)，A2(牵引车)',0,now(),now());
insert into driving_school values (2, '三联驾校', '【三联驾校】波尔塔拉相逢-风向驾校', '新疆xx小区',1,'11212121',5000,'/upload/1.png','/upload/1.png;/upload/1.png','A1(大型客车)，A2(牵引车)',0,now(),now());
insert into driving_school values (3, '昨天驾校', '【昨天驾校】波尔塔拉相逢-风向驾校', '新疆xx小区',1,'11212121',5000,'/upload/1.png','/upload/1.png;/upload/1.png','A1(大型客车)，A2(牵引车)',0,now(),now());
insert into driving_school values (4, '不知驾校', '【不知驾校】波尔塔拉相逢-风向驾校', '新疆xx小区',1,'11212121',5000,'/upload/1.png','/upload/1.png;/upload/1.png','A1(大型客车)，A2(牵引车)',0,now(),now());
insert into driving_school values (5, '哪里驾校', '【哪里驾校】波尔塔拉相逢-风向驾校', '新疆xx小区',1,'11212121',5000,'/upload/1.png','/upload/1.png;/upload/1.png','A1(大型客车)，A2(牵引车)',0,now(),now());
insert into driving_school values (6, '一般驾校', '【一般驾校】波尔塔拉相逢-风向驾校', '新疆xx小区',1,'11212121',5000,'/upload/1.png','/upload/1.png;/upload/1.png','A1(大型客车)，A2(牵引车)',0,now(),now());

-- ----------------------------
-- Records of driving_school
-- ----------------------------

-- ----------------------------
-- Table structure for driving_school
-- ----------------------------
DROP TABLE IF EXISTS `driving_school_lesson`;
CREATE TABLE `driving_school_lesson` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `school_id` int(11) NOT NULL COMMENT '驾校id',
  `name` varchar(50)DEFAULT NULL COMMENT '课程名字',
  `licence` varchar(50) NOT null COMMENT '驾照类型',
  `price` Decimal(6,2) DEFAULT 0.00 COMMENT '价格',
  `is_delete` int(1) DEFAULT 0 COMMENT '是否删除',
  `start_time` datetime DEFAULT NULL COMMENT '开班时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert into driving_school_lesson values(1,1,'A1(大型客车)','A1',3000,0,now(),now());
insert into driving_school_lesson values(2,1,'A2(牵引车)','A2',3000,0,now(),now());
insert into driving_school_lesson values(3,1,'B2(大型货车)','B2',3000,0,now(),now());
insert into driving_school_lesson values(4,1,'C1(小型汽车)','C1',3000,0,now(),now());



-- ----------------------------
-- Records of driving_school
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `mobile` varchar(20) COMMENT '手机号',
  `pwd` varchar(50) DEFAULT NULL COMMENT '密码',
  `avatar` varchar(50) DEFAULT NULL COMMENT '头像',
  `is_delete` int(1) DEFAULT 0 COMMENT '是否删除',
  `session_id` varchar(50) DEFAULT NULL COMMENT '登录sessionId',
  `app_no` varchar(50) DEFAULT NULL COMMENT '登录设备号',
  `last_login_time` datetime DEFAULT NULL COMMENT '最新登录时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `real_name` varchar(50) DEFAULT NULL COMMENT '用户名字',
  `id_no` varchar(50) DEFAULT NULL COMMENT '身份证号码',
  PRIMARY KEY (`id`),
  unique key uk (mobile)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三', '13918643756', '123', '0', '','',now(),now(),now());


-- ----------------------------
-- Table structure for user_course
-- ----------------------------
DROP TABLE IF EXISTS `user_course`;
CREATE TABLE `user_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `user_name` varchar(20) NOT NULL COMMENT '用户name',
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `course_type` int(2) DEFAULT NULL COMMENT '课程类型 1：线上课程 2：线下课程 3:驾校课程',
  `price` decimal(6,2) DEFAULT NULL COMMENT '购买价格',
  `pay_status` int(2) DEFAULT 0 COMMENT '0:未支付 1：支付等待反馈 2:支付成功',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_code` varchar(100) DEFAULT NULL COMMENT '支付流水号',
  `is_delete` int(1) DEFAULT 0 COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `refund_status` int(2) DEFAULT 0 COMMENT '0:无退款 1：申请退款 2：退款成功',
  `refund_bank_no` varchar(30) COMMENT '退款银行卡号',
  `refund_bank_user_name` varchar(50) COMMENT '持卡人姓名',
  `refund_bank_address` varchar(100)  COMMENT '开户行地址',
  `out_trade_no` varchar(30) COMMENT '订单号',
  `refund_pay_code` varchar(30) DEFAULT NULL COMMENT '退款流水号',
  `trade_type` varchar(20) DEFAULT NULL COMMENT '支付方式  微信：JSAPI、NATIVE、APP 支付宝：',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_out_trade_no` (`out_trade_no`) 
  INDEX index_user_id (user_id)  
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert into user_course values (1,1,1,1,1000,2,now(),'ALI00000001',0,now(),now());
insert into user_course values (2,1,1,1,1000,2,now(),'ALI00000001',0,now(),now());
insert into user_course values (3,1,1,1,1000,2,now(),'ALI00000001',0,now(),now());
insert into user_course values (4,1,1,1,1000,2,now(),'ALI00000001',0,now(),now());
insert into user_course values (5,1,2,1,1000,2,now(),'ALI00000001',0,now(),now());
insert into user_course values (6,1,2,1,1000,2,now(),'ALI00000001',0,now(),now());
insert into user_course values (7,1,2,1,1000,2,now(),'ALI00000001',0,now(),now());
insert into user_course values (8,1,2,1,1000,2,now(),'ALI00000001',0,now(),now());
insert into user_course values (9,2,1,1,1000,2,now(),'ALI00000001',0,now(),now());

-- ----------------------------
-- Table structure for user_school_course
-- ----------------------------
DROP TABLE IF EXISTS `user_course_info`;
CREATE TABLE `user_course_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `user_course_id` int(11) NOT NULL COMMENT '用户报名课程id',
  `user_name` varchar(30) NOT NULL COMMENT '用户姓名',
  `sex` int(1) NOT NULL COMMENT '性别 0：男 1：女',
  `first_apply` int(1) NOT NULL COMMENT '是否初次申领 0:否 1：是',
  `pay_type` int(1) NOT NULL COMMENT '付费方式 0:在线支付 1：线下支付',
  `tel_no` varchar(11) NOT NULL COMMENT '手机号码',
  `id_up` varchar(500) COMMENT '身份证正面',
  `id_down` varchar(500) COMMENT '身份证反面',
  `is_delete` int(1) DEFAULT 0 COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX index_user_id (user_id)  
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT '用户报名课程个人信息';

-- ----------------------------
-- Table structure for user_appointment
-- ----------------------------
DROP TABLE IF EXISTS `user_appointment`;
CREATE TABLE `user_appointment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `user_name` varchar(30) NOT NULL COMMENT '用户name',
  `appoint_id` int(11) NOT NULL COMMENT '预约驾校id',
  `licence_type` varchar(20) NOT NULL COMMENT '驾照类型',
  `kemu` int(1) NOT NULL COMMENT '考试科目 1~4',
  `price` decimal(6,2) DEFAULT NULL COMMENT '购买价格',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `pay_type` int(1) NOT NULL COMMENT '付费方式 0:在线支付 1：线下支付',
  `exam_place_id` int(11) NOT NULL COMMENT '考场id',
  `exam_place_name` varchar(30) NULL COMMENT '考场名字',
  `exam_place_address` varchar(200) NULL COMMENT '考场地址',
  `real_name` varchar(100) NOT NULL COMMENT '报考姓名',
  `id_no` varchar(100) NOT NULL COMMENT '身份证号', 
  `score` int(3) DEFAULT 0 COMMENT '得分', 
  `status` int(1) NULL DEFAULT 0 COMMENT '预约状态 0:预约成功 1：线下支付完成 2：线上支付完成 3：完成考试 4：取消 5:申请退款 6:退款成功',  
  `is_delete` int(1) DEFAULT 0 COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX index_user_id (user_id)  
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT '用户预约考试';


-- ----------------------------
-- Table structure for exam_place
-- ----------------------------
DROP TABLE IF EXISTS `exam_place`;
CREATE TABLE `exam_place` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '考场名字', 
  `address` varchar(500) DEFAULT NULL COMMENT '详细地址', 

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT '考场';

-- ----------------------------
-- Records of area
-- ----------------------------
insert into exam_place values(1, '乌鲁木齐铁设驾校', '新疆某个地区名字');
insert into exam_place values(2, '克拉玛依石油运输驾校', '新疆某个地区名字');
insert into exam_place values(3, '和田亚中驾校', '新疆某个地区名字');
insert into exam_place values(4, '石河子恒泰达驾校', '新疆某个地区名字');
insert into exam_place values(5, '托里凯兴驾校', '新疆某个地区名字');
insert into exam_place values(6, '乌鲁木齐平安驾校', '新疆某个地区名字');
insert into exam_place values(7, '新疆宏图远征驾校', '新疆某个地区名字');
insert into exam_place values(8, '新疆惊喜友好驾校', '新疆某个地区名字');

-- ----------------------------
-- Table structure for platform_notice
-- ----------------------------
DROP TABLE IF EXISTS `platform_notice`;
CREATE TABLE `platform_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `summary` varchar(1000) DEFAULT NULL COMMENT '缩略内容',
  `content` varchar(5000) DEFAULT NULL COMMENT '详情内容',
  `image_path` varchar(200) DEFAULT NULL COMMENT '图片',
  `is_delete` int(1) DEFAULT 0 COMMENT '是否删除',
  `update_time` datetime COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of platform_notice
-- ----------------------------
INSERT INTO `platform_notice` VALUES (1, '驾考法规1', '内容1第三方撒打算','内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算','/upload/1.png', 0, now());
INSERT INTO `platform_notice` VALUES (2, '驾考法规2', '内容1第三方撒打算','内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算','/upload/1.png', 0, now());
INSERT INTO `platform_notice` VALUES (3, '驾考法规3', '内容1第三方撒打算','内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算','/upload/1.png', 0, now());
INSERT INTO `platform_notice` VALUES (4, '驾考法规4', '内容1第三方撒打算','内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算','/upload/1.png', 0, now());
INSERT INTO `platform_notice` VALUES (5, '驾考法规5', '内容1第三方撒打算','内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算内容1第三方撒打算','/upload/1.png', 1, now());


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
INSERT INTO `sys_permission` (`id`, `name`, `resource_type`, `url`, `permission`, `parent_id`, `parent_ids`, `available`)
VALUES
	(1,'运维菜单','url',NULL,NULL,NULL,NULL,'1'),
	(5,'线下课程','url','ops/courseoffline/index','ops:course_offline',1,NULL,'1'),
	(6,'教师管理','url','ops/teacher/index','ops:teacher',1,NULL,'1'),
	(7,'线上课程','url','ops/courseonline/index','ops:course_online',1,NULL,'1'),
	(8,'交通安全法律法规','url','ops/trafficlaw/index','ops:trafficlaw',1,'','1'),
	(9,'驾考相关政策规定','url','ops/drivingpolicy/index','ops:drivingpolicy',1,'','1'),
	(10,'平台网点','url','ops/platformnetwork/index','ops:platformnetwork',1,'','1'),
	(11,'驾校信息','url','ops/school/index','ops:school',1,'','1'),
	(12,'注册用户管理','url','ops/user/index','ops:student',1,'','1'),
    (13,'平台公告','url','ops/platformnotice/index','ops:platformnotice',1,'','1'),
    (14,'用户权限管理','url','ops/sysusermanage/index','ops:sysusermanage',1,'','1'),
    (15,'用户购买课程管理','url','ops/usercourse/index','ops:usercourse',1,'','1'),
    (16,'用户预约课程管理','url','ops/userappointment/index','ops:userappointment',1,'','1'),
    (17,'预约管理','url','ops/appointment/index','ops:appointment',1,'','1'),
    (18,'考场管理','url','ops/examplace/index','ops:examplace',1,'','1'),
	(21,'驾校管理','url','','',NULL,'','1'),
	(22,'学员信息管理','url','sch/student/index','sch:student',21,'','1'),
	(23,'开班信息管理','url','sch/schoolclass/index','sch:schoolclass',21,'','1');
	
	INSERT INTO `sys_role_permission` VALUES ('13', '1', '13');
	INSERT INTO `sys_role_permission` VALUES ('14', '1', '14');
	INSERT INTO `sys_role_permission` VALUES ('15', '1', '15');
	INSERT INTO `sys_role_permission` VALUES ('16', '1', '16');
	INSERT INTO `sys_role_permission` VALUES ('17', '1', '17');
	INSERT INTO `sys_role_permission` VALUES ('18', '1', '18');
	
	
	-- ----------------------------
-- Table structure for user_video_record
-- ----------------------------
DROP TABLE IF EXISTS `user_video_record`;
CREATE TABLE `user_video_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `user_id` int(11) COMMENT '用户id',
  `user_name` varchar(30) COMMENT '用户手机号', 
  `ip` varchar(30) COMMENT 'ip 地址', 
  `create_time` datetime COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT '用户观看视频记录';

	-- ----------------------------
-- Table structure for user_exam_record
-- ----------------------------
DROP TABLE IF EXISTS `user_exam_record`;
CREATE TABLE `user_exam_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) COMMENT '用户id',
  `user_name` varchar(30) COMMENT '用户手机号', 
  `exam_map` mediumtext COMMENT '考题矩阵', 
  `score` int(3) DEFAULT 0 COMMENT '得分', 
  `count` int(3) DEFAULT 0 COMMENT '考题总数', 
  `kemu` int(1) DEFAULT 0 COMMENT '科目 1：科目1 4：科目4', 
  `type` int(1) DEFAULT 0 COMMENT '0:模拟考试 1：自由练习', 
  `language` int(1) DEFAULT 0 COMMENT '0:中文 1：蒙语 2：哈语 3：维语', 
  `licType` int(1) DEFAULT 0 COMMENT '驾照类型 1:AB 2:C 3:DEF', 
  `status` int(2) DEFAULT 0 COMMENT '0:答题中 1：完成考试 2：放弃考试', 
  `create_time` datetime COMMENT '创建时间',
  `update_time` datetime COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT '用户模拟考试记录';

	-- ----------------------------
-- Table structure for practice_card
-- ----------------------------
DROP TABLE IF EXISTS `practice_card`;
CREATE TABLE `practice_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` decimal(6,2) COMMENT '购买价格',
  `des` varchar(30) COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT '练习卡管理';


	-- ----------------------------
-- Table structure for appointment_manage
-- ----------------------------
DROP TABLE IF EXISTS `appointment_manage`;
CREATE TABLE `appointment_manage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `licence_type` varchar(20) NOT NULL COMMENT '驾照类型',
  `kemu` int(1) NOT NULL COMMENT '考试科目 1~4',
  `price` decimal(6,2) NOT NULL COMMENT '购买价格',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `exam_place_id` int(11) NOT NULL COMMENT '考场id',
  `limit_count` int(4) COMMENT '限制数量',
  `left_count` int(4)  COMMENT '剩余数量',
  `is_delete` int(1) DEFAULT 0 COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT '预约管理';







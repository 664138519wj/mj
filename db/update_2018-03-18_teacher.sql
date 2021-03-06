# -- 若重置数据库的执行以下代码
# DROP TABLE IF EXISTS `sys_permission`;
#
# CREATE TABLE `sys_permission` (
#   `id`            INT(11) NOT NULL AUTO_INCREMENT,
#   `name`          VARCHAR(255)     DEFAULT NULL,
#   `resource_type` VARCHAR(255)     DEFAULT NULL,
#   `url`           VARCHAR(255)     DEFAULT NULL,
#   `permission`    VARCHAR(255)     DEFAULT NULL,
#   `parent_id`     INT(11)          DEFAULT NULL,
#   `parent_ids`    VARCHAR(255)     DEFAULT NULL,
#   `available`     VARCHAR(255)     DEFAULT NULL,
#   PRIMARY KEY (`id`) USING BTREE
# )
#   ENGINE = InnoDB
#   DEFAULT CHARSET = utf8
#   ROW_FORMAT = COMPACT;
#
#
# INSERT INTO `sys_permission` (`id`, `name`, `resource_type`, `url`, `permission`, `parent_id`, `parent_ids`, `available`)
# VALUES
#   (1, '运维菜单', 'url', NULL, NULL, NULL, NULL, 1),
#   (5, '线下课程', 'url', 'ops/courseoffline/index', 'ops:course_offline', 1, NULL, 1),
#   (6, '教师管理', 'url', 'ops/teacher/index', 'ops:teacher', 1, NULL, 1),
#   (7, '线上课程', 'url', 'ops/courseonline/index', 'ops:course_online', 1, NULL, 1),
#   (8, '交通安全法律法规', 'url', 'ops/trafficlaw/index', 'ops:trafficlaw', 1, '', 1),
#   (9, '驾考相关政策规定', 'url', 'ops/drivingpolicy/index', 'ops:drivingpolicy', 1, '', 1),
#   (10, '平台网点', 'url', 'ops/platformnetwork/index', 'ops:platformnetwork', 1, '', 1),
#   (11, '驾校信息', 'url', 'ops/school/index', 'ops:school', 1, '', 1),
#   (12, '注册用户管理', 'url', 'ops/user/index', 'ops:student', 1, '', 1),
#   (13, '平台公告', 'url', 'ops/platformnotice/index', 'ops:platformnotice', 1, '', 1),
#   (14, '用户权限管理', 'url', 'ops/sysusermanage/index', 'ops:sysusermanage', 1, '', 1),
#   (15, '用户购买课程管理', 'url', 'ops/usercourse/index', 'ops:usercourse', 1, '', 1),
#   (16, '用户预约课程管理', 'url', 'ops/userappointment/index', 'ops:userappointment', 1, '', 1),
#   (17, '预约管理', 'url', 'ops/appointment/index', 'ops:appointment', 1, '', 1),
#   (18, '考场管理', 'url', 'ops/examplace/index', 'ops:examplace', 1, '', 1),
#   (21, '驾校管理', 'url', '', '', NULL, '', 1),
#   (22, '学员信息管理', 'url', 'sch/student/index', 'sch:student', 21, '', 1),
#   (23, '开班信息管理', 'url', 'sch/schoolclass/index', 'sch:schoolclass', 21, '', 1),
#
#   (41, '教师系统', 'url', NULL, NULL, NULL, NULL, 1),
#   (42, '个人信息', 'url', 'tch/myinfo/index', 'tch:myinfo', 41, '', 1),
#   (43, '视频管理', 'url', 'tch/video/index', 'tch:video', 41, '', 1),
#   (44, '收益明细', 'url', 'tch/income/index', 'tch:income', 41, '', 1),
#   (45, '提现', 'url', 'tch/withdraw/index', 'tch:withdraw', 41, '', 1),
#   (46, '修改密码', 'url', 'tch/passwd/index', 'tch:passwd', 41, '', 1);
#
#
# INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
# VALUES (3, 41), (3, 42), (3, 43), (3, 44), (3, 45), (3, 46);
#
#
# ALTER TABLE  course_online ADD COLUMN release_time DATETIME DEFAULT NULL COMMENT '发布时间' AFTER update_time;
# UPDATE course_online SET release_time = '2018-03-18 00:00:00';
#
#





-- 若只更新数据库则执行以下代码
INSERT INTO `sys_permission` (`id`, `name`, `resource_type`, `url`, `permission`, `parent_id`, `parent_ids`, `available`)
VALUES
  (41, '教师系统', 'url', NULL, NULL, NULL, NULL, 1),
  (42, '个人信息', 'url', 'tch/myinfo/index', 'tch:myinfo', 41, '', 1),
  (43, '视频管理', 'url', 'tch/video/index', 'tch:video', 41, '', 1),
  (44, '收益明细', 'url', 'tch/income/index', 'tch:income', 41, '', 1),
  (45, '提现', 'url', 'tch/withdraw/index', 'tch:withdraw', 41, '', 1),
  (46, '修改密码', 'url', 'tch/passwd/index', 'tch:passwd', 41, '', 1);

-- 给admin用户添加教师系统访问权限
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES (3, 41), (3, 42), (3, 43), (3, 44), (3, 45), (3, 46);

-- 添加发布时间, 并设置所有历史记录发布时间为 2018-03-18
ALTER TABLE course_online
  ADD COLUMN release_time DATETIME DEFAULT NULL
COMMENT '发布时间'
  AFTER update_time;
UPDATE course_online
SET release_time = '2018-03-18 00:00:00';

-- admin用户关联到teacher_id
UPDATE SYS_USER
SET relate_id = 1, user_type = 2
WHERE id = 3;

-- 2018-03-20 更新
-- teacher表添加分润比例和身份证号字段
ALTER TABLE teacher
  ADD COLUMN benefit_share DOUBLE DEFAULT 0
COMMENT '分润比例'
  AFTER sort;
UPDATE teacher
SET benefit_share = 0.5
WHERE id = 1;
ALTER TABLE teacher
  ADD COLUMN id_no VARCHAR(100) COMMENT '身份证号'
  AFTER name;
UPDATE teacher
SET id_no = '1234567890123456'
WHERE id = 1;

-- mock课程订单数据
INSERT INTO user_course (user_id, course_id, course_type, price, pay_status, pay_time, pay_code, is_delete, create_time, update_time, user_name, refund_status, refund_bank_no, refund_bank_user_name, refund_bank_address, out_trade_no, refund_pay_code, trade_type)
VALUES
  (13, 1, 1, 1.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O135051097020xx', NULL, 'APP'),
  (13, 1, 1, 2.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O13505109702', NULL, 'APP'),
  (13, 1, 1, 3.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O1350510970', NULL, 'APP'),
  (13, 1, 1, 4.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O135051097', NULL, 'APP'),
  (13, 1, 1, 5.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O13505109', NULL, 'APP'),
  (13, 1, 1, 6.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O1350510', NULL, 'APP'),
  (13, 1, 1, 7.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O1350bb51', NULL, 'APP'),
  (13, 1, 1, 8.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O1350cc5', NULL, 'APP'),
  (13, 1, 1, 9.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O13aaa505', NULL, 'APP'),
  (13, 1, 1, 10.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O1350dd5', NULL, 'APP');



-- 创建教师提现表
CREATE TABLE `teacher_withdraw` (
  `id` int(11) NOT NULL auto_increment,
  `teacher_id` int(11) default NULL COMMENT '教师ID',
  `amount` decimal(6,2) default NULL COMMENT '提现金额',
  `reason` varchar(1000) default NULL COMMENT '提现缘由',
  `bank_name` varchar(100) default NULL COMMENT '提现银行',
  `bank_no` varchar(100) default NULL COMMENT '银行卡号',
  `card_holder` varchar(100) default NULL COMMENT '持卡人姓名',
  `status` int(2) default 0 COMMENT '0:未审核 1:提现中 2:提现成功 3:提现失败',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_time` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`),
  KEY `index_teacher_id` (`teacher_id`)
) COMMENT '教师提现表' ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- mock提现表数据
INSERT INTO teacher_withdraw (teacher_id, amount, reason, bank_name, bank_no, card_holder, status, create_time, update_time) VALUES
  (1, 1.0, '穷', '工商银行', '1234567890123456', '喵喵喵？', 0, '2018-03-13 20:51:03', '2018-03-13 20:51:03'),
  (1, 1.1, '穷', '工商银行', '1234567890123456', '喵喵喵？', 0, '2018-03-13 20:51:03', '2018-03-13 20:51:03'),
  (1, 1.2, '穷', '工商银行', '1234567890123456', '喵喵喵？', 0, '2018-03-13 20:51:03', '2018-03-13 20:51:03'),
  (1, 1.3, '穷', '工商银行', '1234567890123456', '喵喵喵？', 1, '2018-03-13 20:51:03', '2018-03-13 20:51:03'),
  (1, 1.4, '穷', '工商银行', '1234567890123456', '喵喵喵？', 1, '2018-03-13 20:51:03', '2018-03-13 20:51:03'),
  (1, 1.5, '穷', '工商银行', '1234567890123456', '喵喵喵？', 1, '2018-03-13 20:51:03', '2018-03-13 20:51:03'),
  (1, 1.6, '穷', '工商银行', '1234567890123456', '喵喵喵？', 2, '2018-03-13 20:51:03', '2018-03-13 20:51:03'),
  (1, 1.7, '穷', '工商银行', '1234567890123456', '喵喵喵？', 2, '2018-03-13 20:51:03', '2018-03-13 20:51:03'),
  (1, 1.8, '穷', '工商银行', '1234567890123456', '喵喵喵？', 2, '2018-03-13 20:51:03', '2018-03-13 20:51:03'),
  (1, 1.9, '穷', '工商银行', '1234567890123456', '喵喵喵？', 3, '2018-03-13 20:51:03', '2018-03-13 20:51:03'),
  (1, 2.0, '穷', '工商银行', '1234567890123456', '喵喵喵？', 3, '2018-03-13 20:51:03', '2018-03-13 20:51:03');






-- 2018-03-21 更新
INSERT INTO `sys_permission` (`id`, `name`, `resource_type`, `url`, `permission`, `parent_id`, `parent_ids`, `available`) values
  (19, '教师提现管理', 'url', 'ops/teacherwithdraw/index', 'ops:examplace', 1, '', 1);

-- 给admin用户添加教师提现访问权限
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (3, 19);

ALTER TABLE teacher ADD COLUMN mobile varchar(20) DEFAULT NULL COMMENT '手机号' AFTER id_no;
UPDATE teacher set mobile = '12345678901' WHERE id = 1;





-- 2018-03-27 更新
-- mock线上课程订单
INSERT INTO user_course (user_id, course_id, course_type, price, pay_status, pay_time, pay_code, is_delete, create_time, update_time, user_name, refund_status, refund_bank_no, refund_bank_user_name, refund_bank_address, out_trade_no, refund_pay_code, trade_type)
VALUES
  (13, 1, 1, 1.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O135051097020xx', NULL, 'APP'),
  (13, 1, 1, 2.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O13505109702', NULL, 'APP'),
  (13, 1, 1, 3.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O1350510970', NULL, 'APP'),
  (13, 1, 1, 4.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O135051097', NULL, 'APP'),
  (13, 1, 1, 5.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O13505109', NULL, 'APP'),
  (13, 1, 1, 6.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O1350510', NULL, 'APP'),
  (13, 1, 1, 7.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O1350bb51', NULL, 'APP'),
  (13, 1, 1, 8.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O1350cc5', NULL, 'APP'),
  (13, 1, 1, 9.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O13aaa505', NULL, 'APP'),
  (13, 1, 1, 10.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O1350dd5', NULL, 'APP');

-- mock线下课程订单
INSERT INTO user_course (user_id, course_id, course_type, price, pay_status, pay_time, pay_code, is_delete, create_time, update_time, user_name, refund_status, refund_bank_no, refund_bank_user_name, refund_bank_address, out_trade_no, refund_pay_code, trade_type)
VALUES
(13, 11, 2, 1.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O1350510asdf97020xx', NULL, 'APP'),
(13, 11, 2, 2.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O135fgfdhg05109702', NULL, 'APP'),
(13, 11, 2, 3.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O135asdf0510970', NULL, 'APP'),
(13, 11, 2, 4.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O135asdfadsf051097', NULL, 'APP'),
(13, 12, 2, 5.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O13aasdfas505109', NULL, 'APP'),
(13, 12, 2, 6.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O13505fghfdg10', NULL, 'APP'),
(13, 12, 2, 7.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O135asdfa0bb51', NULL, 'APP'),
(13, 12, 2, 8.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O135sadfgfda0cc5', NULL, 'APP'),
(13, 12, 2, 9.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O13aasdfaaa505', NULL, 'APP'),
(13, 12, 2, 10.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'O135asdfasdf0dd5', NULL, 'APP'),
(13, 12, 2, 10.01, 2, '2018-03-13 20:51:03', '4200000065201803138170166124', 0, '2018-03-13 20:50:51', '2018-03-13 20:51:05', '17612163374', '0', NULL, NULL, NULL, 'xxxxO135asdfasdf0dd5', NULL, 'APP');



-- 2018-03-28 更新
-- 添加线下课程分润比例，修改原有的分润比例为线上课程分润比例
alter table teacher add column offline_benefit_share double default '0' COMMENT '线下课程分润比例' after benefit_share;
alter table teacher change column benefit_share online_benefit_share double default '0' COMMENT '现场课程分润比例';
update teacher set offline_benefit_share = 0.8 where id = 1;

-- 修改线上课程菜单名
update sys_permission set name='线上课程管理' where id=43;

-- 添加教师管理系统线下课程菜单页
-- 移位
INSERT INTO `sys_permission` (`id`, `name`, `resource_type`, `url`, `permission`, `parent_id`, `available`)
VALUES (55, '修改密码', 'url', 'tch/passwd/index', 'tch:passwd', 41, 1);
DELETE FROM sys_permission where id=46;
UPDATE sys_permission set id = 46 where id = 45;
UPDATE sys_permission set id = 45 where id = 44;
-- 插入
INSERT INTO `sys_permission` (`id`, `name`, `resource_type`, `url`, `permission`, `parent_id`, `available`)
VALUES (44, '线下课程管理', 'url', 'tch/offlinecourse/index', 'tch:passwd', 41, 1);


INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES (3, 55);


-- 2018-03-29 更新
update sys_permission set name = '授课视频管理' where id = 43;
update sys_permission set name = '网点课程管理' where id = 44;


-- 2018-04-03 更新
alter table  teacher add column teacher_no varchar(128) comment '教练员证件号' after id_no;

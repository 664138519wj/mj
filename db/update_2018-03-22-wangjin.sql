INSERT INTO `sys_permission` (`id`, `name`, `resource_type`, `url`, `permission`, `parent_id`, `parent_ids`, `available`)
VALUES
  (51, '我的收益', 'url', 'ops/schoolIncome/index', 'sch:schoolincome', 47, '', 1),
  (52, '提现', 'url', 'ops/schoolIncome/schoolWithdraw', 'sch:schoolwithdraw', 47, '', 1),
  (53, '修改密码', 'url', 'ops/schoolpasswd/index', 'sch:schoolpassword', 47, '', 1);

-- 创建驾校提现表
CREATE TABLE `school_withdraw` (
  `id` int(11) NOT NULL auto_increment,
  `school_id` int(11) default NULL COMMENT '驾校ID',
  `amount` decimal(6,2) default NULL COMMENT '提现金额',
  `reason` varchar(1000) default NULL COMMENT '提现缘由',
  `bank_name` varchar(100) default NULL COMMENT '提现银行',
  `bank_no` varchar(100) default NULL COMMENT '银行卡号',
  `card_holder` varchar(100) default NULL COMMENT '持卡人姓名',
  `status` int(2) default 0 COMMENT '0:未审核 1:提现中 2:提现成功 3:提现失败',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_time` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`),
  KEY `index_school_id` (`school_id`)
) COMMENT '教师提现表' ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- mock提现表数据
INSERT INTO school_withdraw (school_id, amount, reason, bank_name, bank_no, card_holder, status, create_time, update_time) VALUES
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


-- 给admin用户添加驾校系统访问权限
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES (2, 51), (2, 52), (2, 53);

-- 驾校报名
ALTER TABLE `ssm`.`user_course_info` ADD COLUMN `status` int(1) DEFAULT 0 COMMENT '1:报名中 2:报名成功 3:报名失败（费用原路返回）';
ALTER TABLE `ssm`.`user_course_info` ADD COLUMN `reason` varchar(1000) DEFAULT '' COMMENT '拒绝理由';
ALTER TABLE `ssm`.`driving_school_lesson` ADD COLUMN `left_num` int(11) COMMENT '可报名人数';
update `ssm`.`driving_school_lesson` set left_num = limit_num;

-- 给admin用户添加驾校系统访问权限
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES (1, 19);



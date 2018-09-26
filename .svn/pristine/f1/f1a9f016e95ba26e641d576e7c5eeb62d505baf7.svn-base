ALTER TABLE `ssm`.`driving_school_lesson` ADD COLUMN `limit_num` int(11) COMMENT '报名人数上限' ;
ALTER TABLE `ssm`.`driving_school_lesson` ADD COLUMN `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';

-- 若只更新数据库则执行以下代码
INSERT INTO `sys_permission` (`id`, `name`, `resource_type`, `url`, `permission`, `parent_id`, `parent_ids`, `available`)
VALUES
  (47, '驾校系统', 'url', NULL, NULL, NULL, NULL, 1),
  (48, '开班课程管理', 'url', 'ops/schoolLesson/index', 'sch:schoollesson', 47, '', 1),
  (49, '报名驾校学员管理', 'url', 'ops/schoolStudent/index', 'sch:schoolstudent', 47, '', 1),
  (50, '学员申请退款管理', 'url', 'ops/schoolStudent/school_student_refund', 'sch:schoolstudentrefund', 47, '', 1);


-- 给admin用户添加驾校系统访问权限
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES  (2, 47), (2, 48), (2, 49), (2, 50);

-- 用户预约表增加支付状态
ALTER TABLE `ssm`.`user_appointment` ADD COLUMN `pay_status` int(1) NULL DEFAULT 0 COMMENT '支付状态 0：未付款 1：已付款 2:退款成功';





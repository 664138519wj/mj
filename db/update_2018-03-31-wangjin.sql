update sys_permission t set t.`name`='班级管理' where id=48;
update sys_permission t set t.`name`='学员统计' where id=49;

alter table driving_school add column school_code varchar(20) not null comment '驾校编码';
INSERT INTO `sys_permission` (id, `name`, `resource_type`, `url`, `permission`, `parent_id`, `parent_ids`, `available`) values
  (56,'历史报名学员信息', 'url', 'ops/schoolHisStu/index', 'ops:school', 47, '', 1);

-- 给admin用户添加驾校提现访问权限
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (2, 56);
#权限管理新增字段
alter table sys_user add column `user_type` int(1) DEFAULT 0 COMMENT '用户类型 0：管理员 1：驾校 2：老师';
alter table sys_user add column `relate_id` int(11) DEFAULT 0 COMMENT '关联驾校或者老师id';
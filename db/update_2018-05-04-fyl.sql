alter table course_online add column teacher_name varchar(50) comment '教师名字';

#初始化教师名字数据
update course_online a join teacher b on a.teacher_id=b.id set a.teacher_name=b.name;



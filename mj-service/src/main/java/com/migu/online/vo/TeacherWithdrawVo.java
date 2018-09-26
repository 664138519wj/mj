package com.migu.online.vo;

import com.migu.online.model.TeacherWithdraw;


public class TeacherWithdrawVo extends TeacherWithdraw {
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    private String teacherName;
}

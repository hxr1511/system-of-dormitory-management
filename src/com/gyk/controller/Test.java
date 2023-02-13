package com.gyk.controller;

import com.gyk.Dao.StudentDao;
import com.gyk.Dao.TeacherDao;
import com.gyk.bean.StudentBean;
import com.gyk.bean.TeacherBean;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        int result = 0;
        StudentDao studentDao = new StudentDao();
//        TeacherDao teacherDao = new TeacherDao();
        int i = 1;
//        StudentBean student = studentDao.GetStudentId(i);
//        TeacherBean teacher = teacherDao.GetTeacherId(i);
//        StudentBean studentBean = new StudentBean(1,"1","1","1","1","1","1");
        List<StudentBean> list = studentDao.GetList(0, "");
//        result= studentDao.UpdateSave(studentBean);
        System.out.println(list);
    }
}


package com.levent_j.timetable.bean;

/**
 * Created by levent_j on 16-11-17.
 * used :课程表界面
 */
public class TableCourse {
    public int tid;     //课程编号
    public int sid;     //学生ID
    public int cid;     //课程ID
    public int  date;  //代表星期，周一至周天
    public int location;  //代表课的位置 1-12节课
    public int status;    //表示这节课的状态，0为空，1为有
    public String cname; //课程名称
    public String teacher_name;  //教师名称
    public String classroom;      //教室
    public String test_time;       //考试时间
}

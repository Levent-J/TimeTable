package com.levent_j.timetable.net;

import com.levent_j.timetable.bean.Login;
import com.levent_j.timetable.bean.Course;
import com.levent_j.timetable.bean.ResultAddCourse;
import com.levent_j.timetable.bean.TableCourse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by levent_j on 16-11-9.
 */
public interface ApiService {
    //TODO:在此添加所有api
    //登陆接口
    //http://119.29.100.225:8080/timetable/check_user?name=wangdong&password=550965989
    @GET("check_user")
    Observable<Login> login(@Query("name") String name, @Query("password") String password);

    //查询一周课表
    //url :http://119.29.100.225:8080/timetable/get_timetable
    @GET("get_timetable")
    Observable<List<TableCourse>> getTimeTable(@Query("sid") String sid);

    //查询课程列表
    //http://119.29.100.225:8080/timetable/get_timetable?sid=1
    @GET("get_course")
    Observable<List<Course>> getCourseList(@Query("sid") String sid);

    //添加课程
    //http://119.29.100.225:8080/timetable/add_course?sid=1&cid=9&date=5&begin=3&end=4
    @GET("add_course")
    Observable<ResultAddCourse> addCourse(@Query("sid") String sid,
                                          @Query("cid") String cid,
                                          @Query("date") String data,
                                          @Query("begin") String begin,
                                          @Query("end") String end);

}

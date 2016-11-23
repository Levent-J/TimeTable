package com.levent_j.timetable.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.levent_j.timetable.R;
import com.levent_j.timetable.adapter.CourseListAdapter;
import com.levent_j.timetable.base.BaseActivity;
import com.levent_j.timetable.bean.Course;
import com.levent_j.timetable.net.Api;

import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by levent_j on 16-11-19.
 */
public class CourseSelectActivity extends BaseActivity{
    @Bind(R.id.recyclerView_course_list) RecyclerView recyclerView;

    private static final String TAG = "CourseSelect";
    private CourseListAdapter adapter;

    public static final String KET_DATA = "data";
    public static final String KET_BEGIN = "begin";
    public static final String KET_END = "end";


    public static String data;
    public static String begin;
    public static String end;


    @Override
    protected void initialize() {
       // EventBus.getDefault().register(this);

        adapter = new CourseListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //获取课程列表
        getCourseList();

        data = getIntent().getStringExtra(KET_DATA);
        begin = getIntent().getStringExtra(KET_BEGIN);
        end = getIntent().getStringExtra(KET_END);
    }


    private void getCourseList() {
        Api.getINSTANCE().getCourseList(LoginActivity.SID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Course>>() {
                    @Override
                    public void call(List<Course> list) {
                        if (list!=null){
                            if (list.size()>0){
                                adapter.initDate(list);
                            }else {
                                Log.e(TAG,"size < 0");
                            }
                        }else {
                            Log.e(TAG,"list is null");
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        recyclerView.setAdapter(adapter);
                    }
                });

    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void reciveCourse(CourseEvent event){
//        data = event.getData();
//        begin = event.getBegin();
//        end = event.getEnd();
//    }

    @Override
    protected int setLayout() {
        return R.layout.activity_course_select;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
    }
}

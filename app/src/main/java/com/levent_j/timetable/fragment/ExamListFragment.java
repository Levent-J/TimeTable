package com.levent_j.timetable.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.levent_j.timetable.R;
import com.levent_j.timetable.adapter.ExamListAdapter;
import com.levent_j.timetable.base.BaseFragment;
import com.levent_j.timetable.bean.Course;
import com.levent_j.timetable.bean.TableCourse;
import com.levent_j.timetable.net.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by levent_j on 16-11-9.
 */
public class ExamListFragment extends BaseFragment{
    @Bind(R.id.recyclerView_exam) RecyclerView recyclerView;
    @Bind(R.id.refresh_exam) SwipeRefreshLayout refresh;

    private ExamListAdapter mAdapter;

    public static ExamListFragment newInstance(){
        return new ExamListFragment();
    }

    @Override
    protected void initialize() {
        mAdapter = new ExamListAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        getExamList();

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getExamList();
            }
        });
    }

    private void getExamList() {
        Api.getINSTANCE().getCourseList("1")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Course>>() {
                    @Override
                    public void call(List<Course> courses) {
                        mAdapter.initData(courses);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        refresh.setRefreshing(false);
                    }
                });
    }

    @Override
    protected int setRootLayout() {
        return R.layout.fragment_exam;
    }

}

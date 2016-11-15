package com.levent_j.timetable.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.levent_j.timetable.R;
import com.levent_j.timetable.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by levent_j on 16-11-9.
 */
public class ExamListFragment extends BaseFragment{
    //TODO:考试列表Fragment(陈文卓)

    public static ExamListFragment newInstance(){
        return new ExamListFragment();
    }

    @Override
    protected void initialize() {
     
    }

    @Override
    protected int setRootLayout() {
        return R.layout.fragment_exam;
    }

}

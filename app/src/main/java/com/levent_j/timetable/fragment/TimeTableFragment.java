package com.levent_j.timetable.fragment;

import com.levent_j.timetable.R;
import com.levent_j.timetable.base.BaseFragment;

/**
 * Created by levent_j on 16-11-9.
 */
public class TimeTableFragment extends BaseFragment{

    public static TimeTableFragment newInstance(){
        return new TimeTableFragment();
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected int setRootLayout() {
        return R.layout.fragment_time_table;
    }
}

package com.levent_j.timetable.fragment;

import com.levent_j.timetable.R;
import com.levent_j.timetable.base.BaseFragment;

/**
 * Created by levent_j on 16-11-9.
 */
public class AboutFragment extends BaseFragment {

    public static AboutFragment newInstance(){
        return new AboutFragment();
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected int setRootLayout() {
        return R.layout.fragment_about;
    }
}

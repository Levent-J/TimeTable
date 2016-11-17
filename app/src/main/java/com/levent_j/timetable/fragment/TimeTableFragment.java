package com.levent_j.timetable.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.levent_j.timetable.R;
import com.levent_j.timetable.adapter.TimeTableAdapter;
import com.levent_j.timetable.base.BaseFragment;
import com.levent_j.timetable.widget.SpaceItemDecoration;

import butterknife.Bind;

/**
 * Created by levent_j on 16-11-9.
 */
public class TimeTableFragment extends BaseFragment{
    @Bind(R.id.recyclerView_time_table) RecyclerView recyclerView;
    private TimeTableAdapter mAdapter;

    public static TimeTableFragment newInstance(){
        return new TimeTableFragment();
    }

    @Override
    protected void initialize() {
        mAdapter = new TimeTableAdapter(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),8));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.space)));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int setRootLayout() {
        return R.layout.fragment_time_table;
    }
}

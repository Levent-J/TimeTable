package com.levent_j.timetable.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.levent_j.timetable.R;
import com.levent_j.timetable.adapter.TimeTableAdapter;
import com.levent_j.timetable.base.BaseFragment;
import com.levent_j.timetable.bean.TableCourse;
import com.levent_j.timetable.net.Api;
import com.levent_j.timetable.widget.SpaceItemDecoration;

import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by levent_j on 16-11-9.
 */
public class TimeTableFragment extends BaseFragment{

    @Bind(R.id.recyclerView_time_table) RecyclerView recyclerView;
    private TimeTableAdapter mAdapter;
    private static final String TAG = "TimeTable";

    public static TimeTableFragment newInstance(){
        return new TimeTableFragment();
    }

    @Override
    protected void initialize() {
        mAdapter = new TimeTableAdapter(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),8));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.table_item_space)));


        Api.getINSTANCE()
                .getTimeTable("1")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<TableCourse>>() {
                    @Override
                    public void call(List<TableCourse> list) {
                        if (list!=null){
                            if (list.size()>0){
                                Log.d(TAG,"size="+list.size());
                                mAdapter.initData(list);
                            }else {
                                Log.e(TAG,"error message is : size <=0");
                            }
                        }else {
                            Log.e(TAG,"error message is : list is null");
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG,"error message is:"+throwable);
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        recyclerView.setAdapter(mAdapter);
//                        Toast.makeText(getActivity(),"ok",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected int setRootLayout() {
        return R.layout.fragment_time_table;
    }
}

package com.levent_j.timetable.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.levent_j.timetable.R;
import com.levent_j.timetable.activity.LoginActivity;
import com.levent_j.timetable.adapter.TimeTableAdapter;
import com.levent_j.timetable.base.BaseFragment;
import com.levent_j.timetable.bean.TableCourse;
import com.levent_j.timetable.net.Api;
import com.levent_j.timetable.utils.CourseEvent;
import com.levent_j.timetable.widget.SpaceItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by levent_j on 16-11-9.
 */
public class TimeTableFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recyclerView_time_table) RecyclerView recyclerView;
    @Bind(R.id.refresh_time_table) SwipeRefreshLayout refresh;

    private TimeTableAdapter mAdapter;
    private static final String TAG = "TimeTable";

    public static TimeTableFragment newInstance(){
        return new TimeTableFragment();
    }

    @Override
    protected void initialize() {
        //EventBus.getDefault().register(this);

        mAdapter = new TimeTableAdapter(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),8));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.table_item_space)));

        refresh.setOnRefreshListener(this);

        //获取课程表
        getTimeTable();

    }

    public void getTimeTable() {
        Api.getINSTANCE()
                .getTimeTable(LoginActivity.SID)
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
                        refresh.setRefreshing(false);
//                        Toast.makeText(getActivity(),"ok",Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    protected int setRootLayout() {
        return R.layout.fragment_time_table;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
    }

    @Override
    public void onRefresh() {
        getTimeTable();
    }


}

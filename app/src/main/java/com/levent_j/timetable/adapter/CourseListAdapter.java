package com.levent_j.timetable.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.levent_j.timetable.R;
import com.levent_j.timetable.activity.CourseSelectActivity;
import com.levent_j.timetable.bean.Course;
import com.levent_j.timetable.bean.CourseResult;
import com.levent_j.timetable.net.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by levent_j on 16-11-21.
 */
public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.mViewHolder>{

    private Context mContext;
    private List<Course> mCourses;

    public static int cid;

    public CourseListAdapter(Context context){
        this.mContext = context;
        mCourses = new ArrayList<>();
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_select_clouse,parent,false);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, final int position) {
        holder.cName.setText(mCourses.get(position).cname);
        holder.tName.setText(mCourses.get(position).teacher_name);
        holder.rName.setText(mCourses.get(position).classroom);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cid = mCourses.get(position).cid;
                //添加课程
                addCourse();
            }
        });

    }

    public void addCourse(){
        Api.getINSTANCE().addCourse("1",
                String.valueOf(cid),
                CourseSelectActivity.data,
                CourseSelectActivity.begin,
                CourseSelectActivity.end)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CourseResult>() {
                    @Override
                    public void call(CourseResult courseResult) {
                        if (courseResult.status .equals("true")) {
                            //添加成功
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
                        ((CourseSelectActivity)mContext).finish();
                    }
                });
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public void initDate(List<Course> list){
        this.mCourses.addAll(list);
        notifyDataSetChanged();
    }

    class mViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.layout_course)
        LinearLayout layout;
        @Bind(R.id.tv_course_cname)
        TextView cName;
        @Bind(R.id.tv_course_tname)
        TextView tName;
        @Bind(R.id.tv_course_rname)
        TextView rName;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}

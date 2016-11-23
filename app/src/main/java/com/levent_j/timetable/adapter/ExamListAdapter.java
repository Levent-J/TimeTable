package com.levent_j.timetable.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.levent_j.timetable.R;
import com.levent_j.timetable.bean.Course;
import com.levent_j.timetable.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by levent_j on 16-11-23.
 */
public class ExamListAdapter extends RecyclerView.Adapter<ExamListAdapter.mViewHolder>{

    private Context mContext;
    private List<Course> mCourses;

    public ExamListAdapter(Context context) {
        this.mContext = context;
        this.mCourses = new ArrayList<>();
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_exam,parent,false);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        Course course = mCourses.get(position);
        holder.bindViews(course);
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public void initData(List list){
        mCourses.clear();
        mCourses.addAll(list);
        notifyDataSetChanged();
    }

    class mViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_test_cname) TextView cname;
        @Bind(R.id.tv_test_rname) TextView rname;
        @Bind(R.id.tv_test_time) TextView time;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bindViews(Course course) {
            cname.setText(course.cname);
            rname.setText(course.classroom);
            time.setText(TimeUtil.format(course.test_time));
        }
    }
}

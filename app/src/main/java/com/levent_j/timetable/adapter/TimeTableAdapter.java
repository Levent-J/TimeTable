package com.levent_j.timetable.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.levent_j.timetable.R;
import com.levent_j.timetable.bean.TableCourse;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by levent_j on 16-11-17.
 */
public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.mViewHolder>{

    private Context mContext;
    private List<TableCourse> mTableCourses;

    public TimeTableAdapter(Context context){
        mContext = context;
        mTableCourses = new ArrayList<>();
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_table_clouse,parent,false);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        holder.bindViews(position);
    }

    @Override
    public int getItemCount() {
        return 96;
    }

    public void initData(List<TableCourse> list){
        mTableCourses.addAll(list);
        notifyDataSetChanged();
    }

    class mViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.txt)
        TextView textView;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bindViews(int position){
            if (position%8==0){
                textView.setText("第"+(position%7+(position<50?1:8))+"节课");
            }else {
                int index=0;
                if (position<8){
                    index=1;
                }else if (position<16){
                    index=2;
                }else if (position<24){
                    index=3;
                }else if (position<32){
                    index=4;
                }else if (position<40){
                    index=5;
                }else if (position<48){
                    index=6;
                }else if (position<56){
                    index=7;
                }else if (position<64){
                    index=8;
                }else if (position<72){
                    index=9;
                }else if (position<80){
                    index=10;
                }else if (position<88){
                    index=11;
                }else {
                    index=12;
                }
                TableCourse tableCourse = mTableCourses.get(position-index);
                if (tableCourse.status==1){
                    textView.setText("课程："+tableCourse.cname);
                }else {
                    textView.setText("课程："+position);
                }
            }

        }
    }
}

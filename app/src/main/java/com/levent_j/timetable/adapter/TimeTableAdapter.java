package com.levent_j.timetable.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
    private int[] colors = new int[]{R.color.colorSecondaryText,
            R.color.colorItemYellow,R.color.colorItemBlue,R.color.colorItemPink,
            R.color.colorItemCyn,R.color.colorItemPurple,R.color.colorItemOrange,
            R.color.colorAccent,R.color.colorItemPink,R.color.colorItemYellow,
            R.color.colorItemOrange,R.color.colorItemPurple,R.color.colorItemGreen};

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
        @Bind(R.id.item_background)
        RelativeLayout backgorund;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bindViews(int position){

            if (position%8==0){
                //时间
                textView.setText("第"+(position%7+(position<50?1:8))+"节课");
                textView.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryText));
                backgorund.setBackground(mContext.getResources().getDrawable(colors[0]));
            }else {
                int index = position/8+1;
                TableCourse tableCourse = mTableCourses.get(position-index);

                if (tableCourse.status==1){
                    //有课
                    textView.setTextColor(mContext.getResources().getColor(R.color.colorWhiteText));
                    backgorund.setBackground(mContext.getResources().getDrawable(colors[tableCourse.cid]));
                    textView.setText("课程："+tableCourse.cname);
                }else {
                    //无课
                    textView.setTextColor(mContext.getResources().getColor(R.color.colorWhiteText));
                    backgorund.setBackground(mContext.getResources().getDrawable(colors[0]));
                    textView.setText("今日无课");
                }
            }

        }
    }
}











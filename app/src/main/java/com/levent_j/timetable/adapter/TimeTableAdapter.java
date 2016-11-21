package com.levent_j.timetable.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.levent_j.timetable.R;
import com.levent_j.timetable.activity.CourseDetailActivity;
import com.levent_j.timetable.activity.CourseSelectActivity;
import com.levent_j.timetable.activity.MainActivity;
import com.levent_j.timetable.bean.TableCourse;
import com.levent_j.timetable.utils.CourseEvent;

import org.greenrobot.eventbus.EventBus;

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

    private int[] colors = new int[]{
            R.color.colorItemYellow,R.color.colorItemBlue,R.color.colorItemPink,
            R.color.colorItemCyn,R.color.colorItemPurple,R.color.colorItemOrange,
            R.color.colorItemLightGreen,R.color.colorItemRed,R.color.colorItemYellow,
            R.color.colorItemOrange,R.color.colorItemLightPurple,R.color.colorItemGreen};

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
        mTableCourses.clear();
        mTableCourses.addAll(list);
        notifyDataSetChanged();
    }

    public int itemType(int position){
        //0=时间|1=有课|2=无课
        if (position%8==0){
            return 0;
        }else {
            int index = position/8+1;
            TableCourse tableCourse = mTableCourses.get(position-index);
            if (tableCourse.status==1){
                return 1;
            }else {
                return 2;
            }
        }
    }

    class mViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.txt)
        TextView textView;
        @Bind(R.id.item_background)
        RelativeLayout backgorund;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            backgorund.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int type = itemType(getLayoutPosition());

                    switch (type){
                        case 1:
                            //有课
                            final Intent intentToDetail = new Intent(mContext, CourseDetailActivity.class);
                            mContext.startActivity(intentToDetail);
                            break;
                        case 2:
                            //无课
                            //TODO:弹窗提示：是否加入课程？

                            AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                                    .setTitle("提示")
                                    .setMessage("是否添加新的课程？")
                                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    })
                                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intentToSelect =
                                                    new Intent(mContext, CourseSelectActivity.class);
                                            intentToSelect.putExtra(CourseSelectActivity.KET_DATA,String.valueOf(getLayoutPosition()%8));
                                            intentToSelect.putExtra(CourseSelectActivity.KET_BEGIN,String.valueOf(getLayoutPosition()/8+1));
                                            intentToSelect.putExtra(CourseSelectActivity.KET_END,String.valueOf(getLayoutPosition()/8+1));
                                            mContext.startActivity(intentToSelect);
                                        }
                                    })
                                    .setCancelable(true)
                                    .create();
                            alertDialog.show();
                            break;
                    }
                }
            });


        }

        public void bindViews(int position){


            switch (itemType(position)){
                case 0:
                    //时间
                    textView.setText("第"+(position%7+(position<50?1:8))+"节课");
                    textView.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryText));
                    backgorund.setBackground(mContext.getResources().getDrawable(R.color.colorSecondaryText));
                    break;
                case 1:
                    //有课
                    int index = position/8+1;
                    TableCourse tableCourse = mTableCourses.get(position-index);

                    textView.setTextColor(mContext.getResources().getColor(R.color.colorWhiteText));
                    backgorund.setBackground(mContext.getResources().getDrawable(colors[tableCourse.cid%colors.length]));
                    textView.setText(tableCourse.cname+"@"+tableCourse.classroom);
                    break;
                case 2:
                    //无课
                    textView.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryText));
                    backgorund.setBackground(mContext.getResources().getDrawable(R.color.colorSecondaryText));
                    textView.setText("pos"+position);
                    break;
            }

        }
    }
}











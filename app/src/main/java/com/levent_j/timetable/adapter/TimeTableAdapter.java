package com.levent_j.timetable.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.levent_j.timetable.R;

import java.sql.Time;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by levent_j on 16-11-17.
 */
public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.mViewHolder>{

    private Context mContext;

    public TimeTableAdapter(Context context){
        mContext = context;
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
        return 87;
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
                textView.setText("第"+(position%7+1)+"节课");
            }else {
                textView.setText("课程："+position);
            }

        }
    }
}

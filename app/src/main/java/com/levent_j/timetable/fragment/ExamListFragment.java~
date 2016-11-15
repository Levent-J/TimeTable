package com.levent_j.timetable.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.levent_j.timetable.R;
import com.levent_j.timetable.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by levent_j on 16-11-9.
 */
public class ExamListFragment extends BaseFragment{
    //TODO:考试列表Fragment(陈文卓)
    @Bind(R.id.stuName) TextView stuNameText;
    @Bind(R.id.stuId) TextView stuIdText;
    @Bind(R.id.recyclerView) RecyclerView examListRV;

    ExamListAdapter examListAdapter;
    List<DataOfExam> examData;

    public static ExamListFragment newInstance(){
        return new ExamListFragment();
    }

    @Override
    protected void initialize() {
        //获取学生对象，填充到对应TextView中
        DataOfStu student = getStu();
        stuNameText.setText(student.stuName);
        stuIdText.setText(student.stuId);

        //初始化数据并为RecyclerView设置Adapter
        initData();
        examListAdapter = new ExamListAdapter(getActivity(), examData);
        examListRV.setAdapter(examListAdapter);

        //为RecyclerView设置布局管理
        examListRV.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));

        //为RecyclerView设置分隔线
        RecyclerView.ItemDecoration decoration = new ListDivider(
                getActivity(), ListDivider.VERTICAL_LIST);
        examListRV.addItemDecoration(decoration);
    }

    @Override
    protected int setRootLayout() {
        return R.layout.fragment_exam;
    }

    private void initData() {
        examData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DataOfExam exam = new DataOfExam();
            exam.courseName = "course  " + (i + 1);
            exam.examDateAndTime = "date time  " + (i + 1);
            exam.examPlace = "place  " + (i + 1);
            exam.seatNumber = "seat  " + (i + 1);
            examData.add(exam);
        }
    }

    private DataOfStu getStu() {
        DataOfStu oneStudent = new DataOfStu();
        oneStudent.stuName = "A student's name";
        oneStudent.stuId = "stu id 123456789";
        return oneStudent;
    }
}

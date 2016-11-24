package com.levent_j.timetable.fragment;

import android.widget.TextView;

import com.levent_j.timetable.R;
import com.levent_j.timetable.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by levent_j on 16-11-9.
 */
public class AboutFragment extends BaseFragment {
    @Bind(R.id.tv_about)TextView about;

    public static AboutFragment newInstance(){
        return new AboutFragment();
    }

    @Override
    protected void initialize() {

        String author = "小组成员：\n李文靖 陈文卓(android客户端开发)\n王东(后端开发)\n";
        String qq = "联系QQ：609148550";

        String aboutText = author+"\n \n"+qq;

        about.setText(aboutText);
    }

    @Override
    protected int setRootLayout() {
        return R.layout.fragment_about;
    }
}

package com.levent_j.timetable.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.levent_j.timetable.R;
import com.levent_j.timetable.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by levent_j on 16-11-9.
 */
public class LoginActivity extends BaseActivity{

    @Bind(R.id.til_password)
    TextInputLayout passwordWrapper;
    @Bind(R.id.til_username)
    TextInputLayout usernameWrapper;

    @Bind(R.id.et_username)
    EditText usernameText;
    @Bind(R.id.et_password)
    EditText passwordText;

    @Override
    protected void initialize() {

    }

    @OnClick(R.id.btn_login)
    public void login(View view){
        if (inputCorrect()){
            //TODO:发起网络请求，检测账号密码是否正确
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            //TODO:将学号传给MainActivity
            startActivity(intent);
        }
    }

    private boolean inputCorrect() {
        String username = usernameText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        if (TextUtils.isEmpty(username)){
            usernameWrapper.setError("学号不能为空");
            return false;
        }else {
            usernameWrapper.setErrorEnabled(false);
        }
        if (TextUtils.isEmpty(password)){
            passwordWrapper.setError("密码不能为空");
            return false;
        }else {
            passwordWrapper.setErrorEnabled(false);
        }
        return true;
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }
}

package com.levent_j.timetable.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.levent_j.timetable.R;
import com.levent_j.timetable.base.BaseActivity;
import com.levent_j.timetable.bean.Login;
import com.levent_j.timetable.net.Api;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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

    //TODO:最后要改为0的
    public static String SID = "1";

    @Override
    protected void initialize() {

    }

    @OnClick(R.id.btn_login)
    public void login(final View view){
        if (inputCorrect()){
            String username = usernameText.getText().toString().trim();
            String password = passwordText.getText().toString().trim();

            Api.getINSTANCE().login(username,password)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Login>() {
                        @Override
                        public void call(Login login) {
                            if (login.status==0){
                                //失败
                                Snackbar.make(view,"账号或密码错误",Snackbar.LENGTH_SHORT).show();
                                return;
                            }else {
                                //成功
                                SID = String.valueOf(login.user.sid);
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
                            if (!SID.equals("0")){
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
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

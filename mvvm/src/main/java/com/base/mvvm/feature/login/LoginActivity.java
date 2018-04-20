package com.base.mvvm.feature.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.base.mvvm.R;
import com.base.mvvm.bean.UserBean;
import com.base.mvvm.util.PrefrenceUtil;

public class LoginActivity extends AppCompatActivity {
    private com.base.mvvm.databinding.ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        CheckUserInfoUtil.checkUserNameAndUserPwdIsInvalidate(binding);// RxJava联合判断
        binding.setClickHandler(new LoginHandler(binding));// 绑定监听
        UserBean userBean = PrefrenceUtil.getUserBean();
        if(userBean != null) {// 是否记住密码
            binding.cbIsRecordPwd.setChecked(userBean.isRecordPwd);
        } else {
            userBean = new UserBean();
        }
        binding.setUserBean(userBean);// 绑定用户信息
    }
}

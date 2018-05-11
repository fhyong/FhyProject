package com.base.demo.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.base.demo.R
import com.base.demo.bean.UserBean
import com.base.demo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    var loginBinding: ActivityLoginBinding ?= null
    var vm: LoginViewModel ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        CheckUserInfoUtil.checkUserNameAndUserPwdIsInvalidate(loginBinding as ActivityLoginBinding)// RxJava联合判断
        vm = LoginViewModel(this)
        var userBean = UserBean()
        loginBinding?.userBean = userBean
        loginBinding?.loginHandler = LoginHandler(this)
    }
}

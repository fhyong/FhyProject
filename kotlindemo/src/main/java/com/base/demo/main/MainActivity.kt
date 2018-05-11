package com.base.demo.main

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.base.demo.R
import com.base.demo.bean.UserBean
import com.base.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var mainBinding: ActivityMainBinding ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        var userBean: UserBean = intent.getSerializableExtra("userBean") as UserBean
        mainBinding?.userBean = userBean
    }
}

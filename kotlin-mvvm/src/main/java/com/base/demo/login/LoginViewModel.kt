package com.base.demo.login

import android.content.Intent
import android.widget.Toast
import com.base.demo.bean.BaseBean
import com.base.demo.main.MainActivity

/**
 * 描述: 登陆模块ViewModel层
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-05-11 11:17
 */
class LoginViewModel {
    var act: LoginActivity
    var m: LoginModel

    constructor(act: LoginActivity) {
        this.act = act
        m = LoginModel(this)
    }

    fun login(userName: String, userPwd: String) {
        m.login(userName, userPwd)
    }

    fun loginResult(baseBean: BaseBean<Int>) {
        var intent = Intent(act, MainActivity::class.java)
        if ("success" == baseBean.status) {
            intent.putExtra("userBean", act.loginBinding?.userBean)
            act.startActivity(intent)
        } else {
            Toast.makeText(act, "登陆失败！请检查用户名和密码", Toast.LENGTH_SHORT).show()
        }
    }
}
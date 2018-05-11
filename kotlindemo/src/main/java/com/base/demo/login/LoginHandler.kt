package com.base.demo.login

import android.view.View

/**
 * 描述: 登陆处理
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-05-11 11:29
 */
class LoginHandler {
    var act: LoginActivity ?= null

    constructor(act: LoginActivity) {
        this.act = act
    }

    fun loginClick(view: View) {
        var userBean = act?.loginBinding?.userBean
        act?.vm?.login(userBean?.userName as String, userBean.userPwd)
    }
}

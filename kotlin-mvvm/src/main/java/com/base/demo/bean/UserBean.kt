package com.base.demo.bean

import java.io.Serializable

/**
 * 描述: 用户信息类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-05-11 10:44
 */
class UserBean : Serializable{
    var userName: String = ""
    var userPwd: String = ""
    constructor()
    constructor(userName: String, userPwd: String) {
        this.userName = userName
        this.userPwd = userPwd
    }

    override fun toString(): String {
        return "UserBean(userName='$userName', userPwd='$userPwd')"
    }

}
package com.base.demo.login

import com.base.demo.bean.BaseBean
import com.base.demo.net.BaseObserver
import com.base.fhykotlin.project.net.ApiServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 描述: 登陆模块Model层
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-05-11 11:17
 */
class LoginModel {
    var vm: LoginViewModel

    constructor(vm: LoginViewModel) {
        this.vm = vm
    }

    fun login(userName: String, userPwd: String) {
        ApiServiceFactory
                .apiService
                ?.appLogin(userName, userPwd)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : BaseObserver<BaseBean<Int>>(vm.act, "登陆中...") {
                    override fun callBack(t: BaseBean<Int>) {
                        vm.loginResult(t)
                    }
                })
    }
}
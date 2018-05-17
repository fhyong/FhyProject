package com.base.demo.login

import android.widget.TextView
import com.base.demo.databinding.ActivityLoginBinding
import com.base.util.common.StringUtils
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

/**
 * 描述: 用户登陆信息验证工具类

 * @author : [Fanhy](mailto:fanhuayong@yinli56.com)
 * *
 * @version : Ver 1.0
 * *
 * @date : 2018-04-18 15:32
 */

object CheckUserInfoUtil {
    /**
     * 验证文本框中文字是否为空

     * @param binding
     */
    fun checkUserNameAndUserPwdIsInvalidate(binding: ActivityLoginBinding) {
        val userNameObservable = RxTextView.textChanges(binding.edtUserName as TextView).skip(0)
        val userPwdObservable = RxTextView.textChanges(binding.edtUserPwd).skip(0)

        Observable.combineLatest(userNameObservable, userPwdObservable, BiFunction<CharSequence, CharSequence, Boolean> { charSequence, charSequence2 ->
            val isUserNameInvalidate = !StringUtils.isEmpty(binding.edtUserName.getText().toString())
            val isUserPwdInvalidate = !StringUtils.isEmpty(binding.edtUserPwd.getText().toString())
            isUserNameInvalidate && isUserPwdInvalidate
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { isInvalidate -> binding.btnLoginDo.setEnabled(isInvalidate) }
    }
}

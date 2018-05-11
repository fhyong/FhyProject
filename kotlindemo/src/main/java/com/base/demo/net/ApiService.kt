package com.base.demo.net

import com.base.demo.bean.BaseBean
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * 描述: Retrofit的Restful接口定义
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-05-11 13:21
 */
interface ApiService {
    companion object {
        val BASR_URL = "http://test.ylfood.com/"// 预发布环境
    }

    /**
     * 登陆：

     * @param account  用户名(account)
     * *
     * @param password 密码(password)
     * *
     * @return
     */
    @POST("/uias-web/login/applogin")
    fun appLogin(@Query("account") account: String,
                 @Query("password") password: String): Observable<BaseBean<Int>>
}

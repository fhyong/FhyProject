package com.base.fhykotlin.project.net

import com.base.demo.net.ApiService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * 描述: ApiService工厂

 * @author : [Fanhy](mailto:fanhuayong@yinli56.com)
 * *
 * @version : Ver 1.0
 * *
 * @date : 2018-03-19 18:01
 */

object ApiServiceFactory {
    val cookieStore = HashMap<HttpUrl, List<Cookie>>()
    var apiService: ApiService? = null
        private set

    init {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(60, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(60, TimeUnit.SECONDS)//设置写入超时时间
                .cookieJar(object : CookieJar {
                    //保存和添加Cookie
                    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                        cookieStore.put(url, cookies)
                        cookieStore.put(HttpUrl.parse(ApiService.Companion.BASR_URL), cookies)
                    }

                    override fun loadForRequest(url: HttpUrl): List<Cookie> {
                        var cookies: List<Cookie>? = cookieStore[HttpUrl.parse(ApiService.Companion.BASR_URL)]
                        if (cookies == null) {
                            cookies = ArrayList<Cookie>()
                        }
                        return cookies
                    }
                }).build()
        // 创建Retrofit对象
        val retrofit = Retrofit.Builder()
                .baseUrl(ApiService.Companion.BASR_URL) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .client(okHttpClient)
                .build()
        apiService = retrofit.create<ApiService>(ApiService::class.java)
    }
}

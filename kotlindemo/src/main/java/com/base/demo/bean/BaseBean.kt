package com.base.demo.bean

/**
 * 描述: 网络请求实体类基类

 * @author : [Fanhy](mailto:fanhuayong@yinli56.com)
 * *
 * @version : Ver 1.0
 * *
 * @date : 2018-03-19 17:51
 */

class BaseBean<T> {
    var status: String? = null// 状态：success    error
    var messages: String? = null// 信息
    var result: T? = null// 数据

    override fun toString(): String {
        return "BaseBean{" +
                "status='" + status + '\'' +
                ", messages='" + messages + '\'' +
                ", result=" + result +
                '}'
    }
}

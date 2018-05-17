package com.base.demo.test.extention

import android.util.Log

/**
 * 描述: 类的描述
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-05-10 13:48
 */

/**
 *
 * sealed修饰符修饰密封类。
 *
 * 密封类内部含有多个子类。
 */
sealed class BaseClass {
    class Test1: BaseClass() {
        fun test() {
            Log.d("fanhy", "Test1->test()")
        }
    }
    class Test2: BaseClass() {
        fun test() {
            Log.d("fanhy", "Test2->test()")
        }
    }
    object Test3: BaseClass() {
        fun test() {
            Log.d("fanhy", "Test3->test()")
        }
    }
}

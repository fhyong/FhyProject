package com.base.demo.test.oop

import android.util.Log

/**
 * 描述: Person类: open修饰类代表可以被继承
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-05-10 10:51
 */
open class Person constructor() {// 主构造器
    var pid: String = ""
    var pName: String = ""
        get() = field           // 重写set和get方法
        set(value) {
            field = if (value.length > 5) value.substring(0, 5) else value
        }
    var age: Int = 0
        get() = field           // 重写set和get方法
        set(value) {
            field = if (value in 0..200) value else 0
        }
    var sex: Char = '男'

    constructor(pid: String, pName: String, age: Int, sex: Char) : this() {// 次构造器
        this.pid = pid
        this.pName = pName
        this.age = age
        this.sex = sex
    }

    override fun toString(): String {
        return "Person(pid='$pid', pName=$pName, age=$age, sex=$sex)"
    }

    init {
        Log.d("fanhy","init相当构造代码块， 构造对象时会调用")
    }

    open fun jiaGui() {// open修饰方法代表该方法可以被重写
        Log.d("fanhy", "家规可以与时俱进！")
    }
}

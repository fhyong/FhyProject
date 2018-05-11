package com.base.demo.test.oop

import android.util.Log
import android.view.View

/**
 * 描述: 学生类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-05-10 11:23
 */
class Student(pid: String, pName: String, age: Int, sex: Char) : Person(pid, pName, age,sex) {

    override fun jiaGui() {
        super.jiaGui()
    }
    /*
    类的修饰符包括 classModifier 和_accessModifier_:

    classModifier: 类属性修饰符，标示类本身特性。

    abstract    // 抽象类
    final       // 类不可继承，默认属性
    enum        // 枚举类
    open        // 类可继承，类默认是final的
    annotation  // 注解类
    accessModifier: 访问权限修饰符

    private    // 仅在同一个文件中可见
    protected  // 同一个文件中或子类可见
    public     // 所有调用的地方都可见
    internal   // 同一个模块中可见
    */

    interface OnMyClickListener {
        fun onMyClick(view: View)
        fun default() {
            Log.d("fanhy", "默认实现：default")
        }

        fun default2() {
            Log.d("fanhy", "默认实现：default2")
        }
    }

    fun setOnMyClickListener(onMyClickListener: OnMyClickListener) {
        onMyClickListener.default()
    }
}

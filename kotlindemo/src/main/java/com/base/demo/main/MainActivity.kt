package com.base.demo.main

// Using R.layout.activity_main from the 'main' source set
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.base.adapter.CommonAdapter
import com.base.demo.R
import com.base.demo.bean.UserBean
import com.base.demo.databinding.ActivityMainBinding
import com.base.demo.databinding.DataItemLayoutBinding
import com.base.util.layoutmanager.WrapContentLinearLayoutManager
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var mainBinding: ActivityMainBinding ?= null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        var userBean: UserBean = intent.getSerializableExtra("userBean") as UserBean
        mainBinding?.userBean = userBean

        // test(userBean)
        var dataList: ArrayList<UserBean> = ArrayList()
        dataList.add(UserBean("张博", ""))
        dataList.add(UserBean("李思", ""))
        dataList.add(UserBean("王悟", ""))
        dataList.add(UserBean("赵柳", ""))
        dataList.add(UserBean("钱器", ""))
        var rvData: RecyclerView = rvData
        var adapter: CommonAdapter<UserBean, DataItemLayoutBinding>
                = object : CommonAdapter<UserBean, DataItemLayoutBinding>(R.layout.data_item_layout, dataList) {
            override fun convert(helper: BaseViewHolder?, item: Any?) {
                itemBinding.userBean = item as UserBean?
            }
        }
        rvData.layoutManager = WrapContentLinearLayoutManager(this)
        adapter.bindToRecyclerView(rvData)

        //testReflect(dataList)

        tvToast.setBackgroundColor(Color.argb(100, 255, 255, 255))
    }

    /**
     *
     * 测试反射Api
     */
    private fun testReflect(dataList: ArrayList<UserBean>) {
        Log.d("fanhy", "size = ${dataList.size}")
        val java = dataList::class.java
        val field = java.getDeclaredField("size")
        field.isAccessible = true
        field.set(dataList, 20)
        Log.d("fanhy", "size = ${dataList.size}")

        fun ArrayList<UserBean>.size(): Int = 0 // 扩展重写size方法
        Log.d("fanhy", "size = " + dataList.size())
    }

    /**
     *
     * 测试Lamda表达式及扩展库
     */
    private fun test(userBean: UserBean) {
        //        tvToast.setOnClickListener { view -> Toast.makeText(applicationContext, "点击", Toast.LENGTH_SHORT).show() }
        //        tvToast.setOnClickListener { view ->
        //            var hint = "点击了：" + userBean.userName
        //            Toast.makeText(applicationContext, hint, Toast.LENGTH_SHORT).show()
        //         }
        tvToast.setOnClickListener { view ->
            (view)
            var hint = "点击了：" + userBean.userName + ":" + view.id
            Toast.makeText(applicationContext, hint, Toast.LENGTH_SHORT).show()
        }
        tvToast.text = "呵呵呵" // 如果通过Databinding绑定了该属性，则kotlin的绑定无效
        tvToast.background = getDrawable(R.color.Blue)

        Handler().postDelayed(object : Runnable {
            override fun run() {
                Toast.makeText(applicationContext, "执行了个延时操作", Toast.LENGTH_SHORT).show()
            }
        }, 1000)

        Handler().postDelayed({ kotlin.run { Toast.makeText(applicationContext, "------又执行了个延时操作", Toast.LENGTH_SHORT).show() } }, 4000)
        // 扩展函数
        fun MainActivity.test() {
            tvToast.setOnClickListener { view -> Toast.makeText(applicationContext, "哈哈哈哈", Toast.LENGTH_SHORT).show() }
        }
        test()

        with(tvToast) {
            textSize = 20F
            background = getDrawable(R.color.Green)
        }

        tvToast.apply {
            setTextColor(Color.RED)
            setPadding(10, 10, 10, 10)
        }

        repeat(10) { Log.d("fanhy", "执行：$it") }
        repeat(10, fun(i: Int) {
            Log.d("fanhy", "打印：$i")
        })

        var list = listOf<String>("zhangsan", "lisi")
        Log.d("fanhy", "list = $list")
        var list2 = ArrayList<String>(list)
        list2.add("王五")
        Log.d("fanhy", "list2 = $list2")
    }

}

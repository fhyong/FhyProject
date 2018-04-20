package com.base.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 描述: Fragment基类
 * 主要处理Fragment的视图数据初始化流程，点击事件绑定
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-03-16 13:44
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    protected ViewDataBinding binding;// DataBinding实例
    protected View rootView;// 根视图
    protected Activity mActivity;// 附着Activity
    protected boolean isCreate = true;// 是否第一次加载

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();// 保存Fragment附着的Activity实例
        if (rootView == null) {
            binding = DataBindingUtil.inflate(inflater, getResource(), container,false);// 获取DataBinding对象
            rootView = binding.getRoot();// 获取Fragment根视图
            beforeInitView();// 执行初始化View之前的操作
            initView(rootView);// 初始化View视图
            initData();// 初始化数据
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);// 移除rootView
        }
        return rootView;
    }

    /**
     * 不用强制转换的findviewbyid
     *
     * @param id        View的id
     * @param <T>       返回泛型
     * @return
     */
    protected <T extends View> T findViewByIdNoCast(int id) {
        return rootView == null ? null : (T) rootView.findViewById(id);
    }

    /**
     * 获取Fragment布局文件id
     *
     * @return
     */
    protected abstract int getResource();

    /**
     * 初始化View之前的操作
     */
    protected abstract void beforeInitView();

    /**
     * 初始化View视图：
     * 一般可在该方法中执行一些View的初始化工作，
     * 比如findViewById， inflate， 给View绑定监听器等
     * @param rootView      根视图对象
     */
    protected abstract void initView(View rootView);

    /**
     * 初始化数据：
     * 一般可在该方法中执行一些数据初始化工作，
     * 比如全局成员的初始化，网络请求等
     */
    protected abstract void initData();

    @Override
    public void onStart() {
        super.onStart();
        if(!isCreate) {// 第一次已经在onCreate中调用
            initData();// 刷新数据
        }
        isCreate = false;
    }

    /**
     * 给指定id的View绑定点击监听
     *
     * @param ids 可变参数，要绑定的视图id
     */
    protected void setOnClick(int... ids) {

        for (int id : ids) {
            if (id != -1)
                findViewByIdNoCast(id).setOnClickListener(this);
        }
    }

    /**
     * 给指定的View绑定点击监听
     *
     * @param views 可变参数，要绑定的View
     */
    public void setOnClick(View... views) {
        for (View view : views)
            view.setOnClickListener(this);
    }
}

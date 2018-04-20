package com.base.util.common;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.base.util.layoutmanager.FullyLinearLayoutManager;
import com.base.util.layoutmanager.WrapContentLinearLayoutManager;

/**
 * Created by ${qianzhihe} on 2017/12/1.
 * RecyclerView初始化工具类
 */

public class RecyclerViewUtil {

    /**
     * 初始化RecyclerView垂直方向
     *
     * @param context
     * @param recyclerView
     */
    public static void initLinearLayoutVERTICAL(Context context, RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //调用此方法，刷新时使用commodityAreaAdapter.notifyItemChanged(position);可解决刷新数据时，图片闪烁问题
        recyclerView.setItemAnimator(new CustomItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);//解决ScrollView嵌套RecyclerView后滑动卡顿、粘连问题
    }

    /**
     * 初始化RecyclerView水平方向
     *
     * @param context
     * @param recyclerView
     */
    public static void initLinearLayoutHorizontal(Context context, RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new CustomItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }

    /**
     * 初始化RecyclerView带有Divid
     *
     * @param context
     * @param recyclerView
     */
    public static void initLinearLayoutWithoutDivid(Context context, RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new CustomItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }

    /**
     * 初始化RecyclerView, 使用自定义WrapContentLinearLayoutManager，
     * 解决原生RecyclerView无数据时滑动Crash问题
     *
     * @param layoutManager
     * @param recyclerView
     */
    public static void initLinearLayoutVERTICAL(WrapContentLinearLayoutManager layoutManager, RecyclerView recyclerView) {
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new CustomItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }

    public static void initStaggered(Context context, RecyclerView recyclerView) {
        StaggeredGridLayoutManager sgm = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(sgm);
        recyclerView.setItemAnimator(new CustomItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }

    /**
     * 初始化RecyclerView网格布局
     *
     * @param context
     * @param spanCount    每行数量
     * @param recyclerView
     */
    public static GridLayoutManager initLinearLayoutGridLayout(Context context, int spanCount, RecyclerView recyclerView) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new CustomItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        return gridLayoutManager;
    }

    public static void initFullLinearLayoutVERTICAL(Context context, RecyclerView recyclerView) {
        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(context);
        layoutManager.setOrientation(FullyLinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //调用此方法，刷新时使用commodityAreaAdapter.notifyItemChanged(position);可解决刷新数据时，图片闪烁问题
        recyclerView.setItemAnimator(new CustomItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);//解决ScrollView嵌套RecyclerView后滑动卡顿、粘连问题
    }

}

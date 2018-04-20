package com.base.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * 描述: 基于BaseQuickAdapter的简单封装，适用于单一风格Item的列表
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-19 14:34
 */

public abstract class CommonAdapter<T, VDB extends ViewDataBinding> extends BaseQuickAdapter {
    protected VDB itemBinding;

    public CommonAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        itemBinding = DataBindingUtil.inflate((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE),
                layoutResId, null, false);
        return itemBinding.getRoot();
    }
}

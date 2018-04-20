package com.base.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 描述: 基于BaseMultiItemQuickAdapter的简单封装，适用于多风格Item的列表
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-19 15:18
 */

public abstract class CommonMultiAdapter<T extends MultiItemEntity, VDB extends ViewDataBinding> extends BaseMultiItemQuickAdapter {
    protected VDB itemBinding;

    public CommonMultiAdapter(List<T> data, Map<Integer, Integer> layouts) {
        super(data);
        Set<Map.Entry<Integer, Integer>> entries = layouts.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            addItemType(entry.getKey(), entry.getValue());
        }
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        itemBinding = DataBindingUtil.inflate((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE),
                layoutResId, null, false);
        return itemBinding.getRoot();
    }
}

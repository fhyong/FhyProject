package com.base.view;

import android.content.Context;
import android.view.View;

import com.lcodecore.tkrefreshlayout.IBottomView;

/**
 * 描述: 上拉加载更多视图
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-03-19 15:44
 */

public class SimpleFooterView implements IBottomView {

    private Context context;
    private int layoutId;

    public SimpleFooterView(Context context, int layoutId) {
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public View getView() {
        View loadMoreView = View.inflate(context, layoutId, null);
        return loadMoreView;
    }

    @Override
    public void onPullingUp(float fraction, float maxBottomHeight, float bottomHeight) {

    }

    @Override
    public void startAnim(float maxBottomHeight, float bottomHeight) {

    }

    @Override
    public void onPullReleasing(float fraction, float maxBottomHeight, float bottomHeight) {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void reset() {

    }
}

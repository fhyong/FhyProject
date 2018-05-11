package com.base.mvvm.async;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述: 类的描述
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-27 16:52
 */

public class SimpleIdlingResource implements IdlingResource {
    private final AtomicInteger counter = new AtomicInteger(0);

    private volatile ResourceCallback resourceCallback;

    @Override
    public String getName() {
        return "SimpleIdlingResource";
    }

    /**
     * 如果counter的值等于0，说明当前是空闲状态
     */
    @Override
    public boolean isIdleNow() {
        return counter.get() == 0;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }

    /**
     * counter的值增长方法
     */
    public void increment() {
        counter.getAndIncrement();
    }

    /**
     *counter的值减少方法
     */
    public void decrement() {
        int counterVal = counter.decrementAndGet();
        if (counterVal == 0) {
            // 执行onTransitionToIdle()方法，告诉Espresso，当前是空闲状态。
            if (null != resourceCallback) {
                resourceCallback.onTransitionToIdle();
            }
        }

        if (counterVal < 0) {
            throw new IllegalArgumentException("Counter has been corrupted!");
        }
    }
}

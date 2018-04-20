package com.base.mvvm.feature.login;

import com.base.mvvm.bean.BaseBean;
import com.base.mvvm.bean.UserBean;
import com.base.mvvm.net.ApiServiceFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 描述: 登陆模块Model层实现
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-18 16:59
 */

public class LoginModel {
    private LoginViewModel viewModel;

    public LoginModel(LoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void login(UserBean userBean) {
        ApiServiceFactory
                .getApiService()
                .appLogin(userBean.userName, userBean.userPwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<Integer>>() {
                    @Override
                    public void accept(BaseBean<Integer> baseBean) throws Exception {
                        viewModel.setLoginResult(baseBean);
                    }
                });
    }
}

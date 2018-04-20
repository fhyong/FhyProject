package com.base.mvvm.feature.login;

import android.view.View;

import com.base.mvvm.databinding.ActivityLoginBinding;

/**
 * 描述: 点击事件
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-18 10:07
 */

public class LoginHandler {
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    public LoginHandler(ActivityLoginBinding binding) {
        this.binding = binding;
        viewModel = new LoginViewModel(binding);
    }

    public void loginDo(View view) {
        viewModel.login(binding.getUserBean());
    }
}

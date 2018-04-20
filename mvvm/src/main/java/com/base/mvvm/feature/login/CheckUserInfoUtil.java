package com.base.mvvm.feature.login;

import com.base.mvvm.databinding.ActivityLoginBinding;
import com.base.util.common.StringUtils;
import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 描述: 用户登陆信息验证工具类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-18 15:32
 */

public class CheckUserInfoUtil {
    /**
     * 验证文本框中文字是否为空
     *
     * @param binding
     */
    public static void checkUserNameAndUserPwdIsInvalidate(final ActivityLoginBinding binding) {
        Observable<CharSequence> userNameObservable = RxTextView.textChanges(binding.edtUserName).skip(0);
        Observable<CharSequence> userPwdObservable = RxTextView.textChanges(binding.edtUserPwd).skip(0);

        Observable.combineLatest(userNameObservable, userPwdObservable, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2) throws Exception {
                boolean isUserNameInvalidate = !StringUtils.isEmpty(binding.edtUserName.getText().toString());
                boolean isUserPwdInvalidate = !StringUtils.isEmpty(binding.edtUserPwd.getText().toString());
                return isUserNameInvalidate && isUserPwdInvalidate;
            }
        })
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean isInvalidate) throws Exception {
                binding.btnLoginDo.setEnabled(isInvalidate);
            }
        });
    }
}

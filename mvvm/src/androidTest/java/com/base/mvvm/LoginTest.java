package com.base.mvvm;

import android.test.ActivityInstrumentationTestCase2;

import com.base.mvvm.feature.login.LoginActivity;
import com.base.mvvm.util.PrefrenceUtil;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;


/**
 * 描述: 登陆模块测试
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-23 9:51
 */

public class LoginTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    private final String CORRECT_USER_NAME = "fanhuayong1";// 正确用户名
    private final String CORRECT_USER_PWD = "fanhuayong1";// 正确密码
    private final String WRONG_USER_NAME = "abcd";// 错误用户名
    private final String WRONG_USER_PWD = "abcd";// 错误密码

    public LoginTest() {
        super(LoginActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    /**
     * 测试：使用指定正确的账号和密码，登陆按钮是否：可以点击
     */
    public void testLoginByCorrectNameAndPwd() {
        onView(withId(R.id.edtUserName)).perform(clearText(), typeText(CORRECT_USER_NAME), closeSoftKeyboard());// 用户名设置
        onView(withId(R.id.edtUserPwd)).perform(clearText(), typeText(CORRECT_USER_PWD), closeSoftKeyboard());// 密码设置
        onView(withId(R.id.btnLoginDo)).check(matches(isEnabled()));// 登陆按钮是否可点击
        onView(withId(R.id.btnLoginDo)).perform(click());
    }

    /**
     * 测试：使用空用户名和空密码，，登陆按钮是否：不能点击
     */
    public void testLoginByNoneNameAndNonePwd() {
        onView(withId(R.id.edtUserName)).perform(clearText());// 用户名清空
        onView(withId(R.id.edtUserPwd)).perform(clearText());// 密码清空
        onView(withId(R.id.btnLoginDo)).check(matches(not(isEnabled())));// 登陆按钮是否可点击
    }

    /**
     * 测试：使用空用户名，登陆按钮是否：不能点击
     */
    public void testLoginByNoneNameAndPwd() {
        onView(withId(R.id.edtUserName)).perform(clearText());// 用户名清空
        onView(withId(R.id.edtUserPwd)).perform(clearText(), typeText(CORRECT_USER_PWD), closeSoftKeyboard());// 密码设置
        onView(withId(R.id.btnLoginDo)).check(matches(not(isEnabled())));// 登陆按钮是否可点击
    }

    /**
     * 测试：使用空密码，登陆按钮是否：不能点击
     */
    public void testLoginByNameAndNonePwd() {
        onView(withId(R.id.edtUserName)).perform(clearText(), typeText(CORRECT_USER_NAME), closeSoftKeyboard());// 用户名设置
        onView(withId(R.id.edtUserPwd)).perform(clearText());// 密码设置
        onView(withId(R.id.btnLoginDo)).check(matches(not(isEnabled())));// 登陆按钮是否可点击
    }

    /**
     * 测试：判断是否记住密码复选框是否正确设置选中与否状态
     */
    public void testLoginRecordPwdIsChecked() {
        boolean isRecord = PrefrenceUtil.getUserBean() != null;
        if (isRecord) {// 记住：是否选中
            onView(withId(R.id.cbIsRecordPwd)).check(matches(isChecked()));
        } else {// 没有记住：是否不选中
            onView(withId(R.id.cbIsRecordPwd)).check(matches(not(isChecked())));
        }
    }
}

package com.base.mvvm.async;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.base.mvvm.R;
import com.base.mvvm.feature.login.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * 描述: 类的描述
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-27 16:54
 */

public class IdlingResourceTest {
    private static final String CORRECT_USER_NAME = "fanhuayong1";
    private static final String CORRECT_USER_PWD = "fanhuayong1";
    @Rule
    public ActivityTestRule<LoginActivity> rule=new ActivityTestRule<>(LoginActivity.class,true);

    @Before
    public void registerIdlingResource(){
        Espresso.registerIdlingResources(rule.getActivity().sIdlingResource);
    }

    @Test
    public void avatarDisplayed(){
        onView(withId(R.id.edtUserName)).perform(clearText(), typeText(CORRECT_USER_NAME), closeSoftKeyboard());// 用户名设置
        onView(withId(R.id.edtUserPwd)).perform(clearText(), typeText(CORRECT_USER_PWD), closeSoftKeyboard());// 密码设置
        onView(withId(R.id.btnLoginDo)).check(matches(isEnabled()));// 登陆按钮是否可点击
        onView(withId(R.id.btnLoginDo)).perform(click());
        onView(withId(R.id.tvUserWelcome)).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(
                rule.getActivity().sIdlingResource);
    }
}

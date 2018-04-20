package com.base.util.common;

import android.content.Context;
import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author qianzhihe
 */
public final class StringUtils {

    // Email
    public static final String EMAIL_PATTERN = "\\w+@\\w+\\.[a-zA-Z]+(\\.[a-zA-Z]+)?";

    // Phone
    public static final String PHONE_PATTERN = "^(\\+?\\d+)?1[3456789]\\d{9}$";

    public static final String STRING_SPACE = "\u3000\u3000";//缩进两个字符
    private static final int DECIMAL_DIGITS = 2;//输入框小数的位数

    private StringUtils() {
    }

    public static boolean isEmpty(String source) {

        if (null == source || "".equals(source) || "null".equals(source))
            return true;

        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static boolean matches(String source, String pattern) {

        if (source == null || !source.matches(pattern)) {
            return false;
        }
        return true;
    }

    public static boolean isEmail(String source) {
        return matches(source, EMAIL_PATTERN);
    }

    public static boolean isPhone(String source) {
        return matches(source, PHONE_PATTERN);
    }

    public static String toNotNullString(String source) {

        return source == null ? "" : source;
    }

    public static String arrayToString(String[] array, String separator) {

        if (array == null || array.length == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (String temp : array) {
            result.append(temp).append(separator);
        }
        return result.substring(0, result.length() - 2);
    }

    public static String[] toArray(String source) {

        if (isEmpty(source)) {
            return null;
        }
        return source.split(",");
    }

    public static String[] toArray(Object[] sources) {

        if (sources == null || sources.length == 0) {
            return null;
        }
        String[] temps = new String[sources.length];
        String value = "";
        for (int i = 0; i < temps.length; i++) {

            if (sources[i] == null) {
                continue;
            }
            if (Date.class.equals(sources[i].getClass())) {
                value = DateUtils.toDateString((Date) sources[i], DateUtils.DATE_TIME_FORMAT);
            } else {
                value = sources[i].toString();
            }
            temps[i] = value;
        }
        return temps;
    }

    public static String hidePhone(String phone) {
        if (isEmpty(phone)) {
            return phone;
        }

        if (!isPhone(phone)) {
            return phone;
        }
        if (phone.length() != 11) {
            return phone;
        }
        return phone.substring(0, 3) + "****"
                + phone.substring(7, phone.length());
    }

    public static String capitalize(String str) {

        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        sb.append(Character.toUpperCase(str.charAt(0)));
        sb.append(str.substring(1));
        return sb.toString();
    }

    public static String getTimeName() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(date);
    }

    public static boolean larger(String source, int length) {
        return (source != null && source.length() > length);
    }

    public static String toString(Object source) {
        return (source == null ? null : source.toString());
    }

    public static String filterChinese(String str) {
        String reg = "[\u4E00-\u9FA5]";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        String repickStr = mat.replaceAll("");
        return repickStr;
    }


    /**
     * 获取url中的方法名
     *
     * @param url
     * @return
     */
    public static String getUrlMethodName(String url) {
        String uri = url.replace("bashigo://", "").trim();
        String methodName = "";
        if (uri.contains("?")) {
            String string[] = uri.split("\\?");
            methodName = string[0];
        } else {
            methodName = uri.trim();
        }
        return methodName;
    }

    /**
     * 获取url中的参数列表
     *
     * @param url
     * @return
     */
    public static HashMap<String, String> getUrlParams(String url) {
        String uri = url.replace("bashigo://", "").trim();
        HashMap<String, String> map = new HashMap<String, String>();
        if (uri.contains("?")) {//带参数
            String tempString[] = uri.split("\\?");//按?截取 （\\为转义符）
            //获取参数列表
            if (tempString[1].contains("&")) {
                String paString[] = tempString[1].split("&");
                for (int i = 0; i < paString.length; i++) {
                    String string2[] = paString[i].split("=");
                    map.put(string2[0], string2[1]);
                }
            } else {
                String temp[] = tempString[1].split("=");
                map.put(temp[0], temp[1]);
            }
            return map;
        } else {//不带参数
            return null;
        }
    }

    /**
     * 首行缩进
     *
     * @param value
     * @return
     */
    public static String stringSpace(String value) {
        return STRING_SPACE + value;
    }

    /**
     * 文字颜色
     */
    public static SpannableString addForeColorSpan(String str, int color, int start, int end) {
        SpannableString spanString = new SpannableString(str);
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }
    /**
     * 文字颜色
     */
    public static SpannableString addForeColorSpanWithSize(String str, int color, int start, int end, int sizeSpan) {
        SpannableString spanString = new SpannableString(str);
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        AbsoluteSizeSpan mSizeSpan = new AbsoluteSizeSpan(sizeSpan);
        spanString.setSpan(mSizeSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }
    /**
     * 文字颜色并且字体加粗
     */
    public static SpannableString addForeColorSpanWithBold(String str, int color, int start, int end) {
        SpannableString spanString = new SpannableString(str);
        StyleSpan styleSpan=new StyleSpan(Typeface.BOLD);
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanString.setSpan(styleSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

    /**
     * 文字颜色并且字体加粗和设置字体
     */
    public static SpannableString addForeColorSpanWithBoldAndSize(String str, int color, int start, int end, int sizeSpan) {
        SpannableString spanString = new SpannableString(str);
        StyleSpan styleSpan=new StyleSpan(Typeface.BOLD);
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanString.setSpan(styleSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        AbsoluteSizeSpan mSizeSpan = new AbsoluteSizeSpan(sizeSpan);
        spanString.setSpan(mSizeSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

    /**
     * 字体大小
     */
    public static SpannableString addFontSpan(String str, int sizeSpan, int start, int end) {
        SpannableString spanString = new SpannableString(str);
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(sizeSpan);
        spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

    /**
     * 判断是否为中文
     *
     * @param c
     * @return
     */
    public static final boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 计算字符串中中文字符数，一个中文算2个字符
     *
     * @param strName
     * @return
     */
    public static final int countChinese(String strName) {
        int count = 0;
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                count += 2;
            }
        }
        return count;
    }

    /**
     * 计算字符串中非中文字符数，一个非中文算一个字符
     *
     * @param strName
     * @return
     */
    public static final int countNotChinese(String strName) {
        int count = 0;
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!isChinese(c)) {
                count++;
            }
        }
        return count;
    }

//    /**
//     * 判断输入的数量是否超过最大限制100000
//     *
//     * @param numberStr
//     * @return
//     */
//    public static final boolean checkSubmitNumberIsMoreThanLimit(String numberStr) {
//        boolean isMoreThanLimit = false;
//        if (isEmpty(numberStr)) {
//            isMoreThanLimit = false;
//        } else {
//            if (Float.parseFloat(numberStr) > Constants.MAX_TOTAL) {
//                isMoreThanLimit = true;
//            }
//        }
//        return isMoreThanLimit;
//    }

//    public static final boolean checkSubmitNumberIsMoreThanLimit(float numberStr) {
//        boolean isMoreThanLimit = false;
//        if (numberStr > Constants.MAX_TOTAL) {
//            isMoreThanLimit = true;
//        }
//        return isMoreThanLimit;
//    }

    /**
     * 设置小数控制位数
     * @param editText
     * @param decimalDigits 输入框小数的位数
     * @return
     */
    public static InputFilter setEditInputFilter(EditText editText, final int decimalDigits) {
        InputFilter lengthfilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                // 删除等特殊字符,直接返回
                if ("".equals(source.toString())) {
                    return null;
                }
                String dValue = dest.toString(); String[] splitArray = dValue.split("\\.");
                if (splitArray.length > 1) {
                    String dotValue = splitArray[1];
                    int diff = dotValue.length() + 1 - decimalDigits;
//                    int diff = dotValue.length() + 1 - DECIMAL_DIGITS;
                    if (diff > 0) {
                        return source.subSequence(start, end - diff);
                    }
                }
                return null;
            }
        };
        return lengthfilter;
    }

//    public static UpdateCheckBean getUpdateCheckBean(String updateCheckStr){
//        UpdateCheckBean updateCheckBean=JSON.parseObject(updateCheckStr, UpdateCheckBean.class);
//        return updateCheckBean;
//    }

    /**
     * 转换成大写字母
     * @param data
     * @param start
     * @param len
     */
    public static void toUpperCase(byte[] data, int start, int len)
    {
        int end = start + len;
        int dist = 'A' - 'a';
        for (int i = start; i < end; i++)
        {
            if (data[i] >= 'a' && data[i] <= 'z')
            {
                data[i] += dist;
            }
        }
    }

    /**
     * 转换成小写字母
     * @param data
     * @param start
     * @param len
     */
    public static void toLowerCase(byte[] data, int start, int len)
    {
        int end = start + len;
        int dist = 'a' - 'A';
        for (int i = start; i < end; i++)
        {
            if (data[i] >= 'A' && data[i] <= 'Z')
            {
                data[i] += dist;
            }
        }
    }

    /**
     * 设置显示的字符串资源（从sting文件引用的资源文件）
     * @param context
     * @param resourcesId
     * @return
     */
    public static String setStringResources(Context context, int resourcesId){
        String showResourcesStr=context.getResources().getString(resourcesId);
        return showResourcesStr;
    }

    /**
     * 设置手机号中间几位显示*号
     * @param phoneNumberStr
     * @param startIndex *显示的起始位置
     * @param endIndex *显示的结束位置
     * @return
     */
    public static String setPhoneMiddleStar(String phoneNumberStr, int startIndex, int endIndex){
        if(!StringUtils.isEmpty(phoneNumberStr) && phoneNumberStr.length()==11){
            if (startIndex==-1 || endIndex==-1 || startIndex>endIndex){
                return phoneNumberStr;
            }
            StringBuilder sb  =new StringBuilder();
            for (int i = 0; i < phoneNumberStr.length(); i++) {
                char c = phoneNumberStr.charAt(i);
                if (i >= startIndex && i <= endIndex) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return phoneNumberStr;
    }

    /**
     * 设置字体加粗
     * @param textViewBold
     */
    public static void setTextViewBold(TextView textViewBold){
        textViewBold.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

}
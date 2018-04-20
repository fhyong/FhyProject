package com.base.util.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.base.application.BaseApplication;
import com.base.bean.PhoneInfoBean;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Enumeration;

/**
 * Created by 千纸鹤 on 2017/3/30.
 * 系统工具类
 */

public class SystemUtil {

    /**
     * 显示软键盘
     *
     * @param activity
     */
    public static void showSoftInput(Activity activity) {
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showSoftInput(Activity activity, View view) {
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        @SuppressLint("WrongConstant") InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏软键盘
     *
     * @param activity
     * @param view
     */
    public static void hideSoftInput(Activity activity, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    /**
     * 获取设备型号
     * @return
     */
    public static int getDeviceModel(){
        int deviceModelInt=0;
        Build bd = new Build();
        String deviceModelStr = bd.MODEL;
        if (deviceModelStr.equals("C4000")){//成为第4代
            deviceModelInt=4000;
        }else if (deviceModelStr.equals("CZ8800")){//CZ8800
            deviceModelInt=8800;
        }else if (deviceModelStr.equals("C6000")){//成为第6代
            deviceModelInt=6000;
        }
        return deviceModelInt;
    }

    /**
     * 处理数据保留2位小数，四舍五入
     * @param tempData
     * @return
     */
//    public static float dealDataForBigDecimal(String tempData){
//        if(tempData == null || tempData.equals("null")) {
//            return 0.00f;
//        }
//        float returndData=0f;
//        BigDecimal b  = new BigDecimal(tempData);
//        returndData=b.setScale(2,  BigDecimal.ROUND_HALF_UP).floatValue();//保留2位小数
//        return  returndData;
//    }
//
//    public static float dealDataForBigDecimal(float tempData){
//        float returndData=0.00f;
//        BigDecimal b  = new BigDecimal(tempData);
//        returndData=b.setScale(2,  BigDecimal.ROUND_HALF_UP).floatValue();//保留2位小数
//        return  returndData;
//    }

    /**
     * 将double格式化为指定小数位的String，不足小数位用0补全
     *
     * @param object 需要格式化的数字，可以为Float、String、BigDecimal类型
     * @param scale 小数点后保留几位
     * @return
     */
    public static String roundByScale(Object object, int scale) {
        Double dateDouble=0d;
        if (object instanceof Float){
            Float dataFloat= (Float) object;
            if (StringUtils.isEmpty(String.valueOf(dataFloat))){
                dateDouble=0d;
            }else {
                BigDecimal b = new BigDecimal(String.valueOf(dataFloat));
                dateDouble= b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }else if (object instanceof String){
            if (StringUtils.isEmpty(String.valueOf(object))){
                dateDouble=0d;
            }else {
                dateDouble=Double.parseDouble(String.valueOf(object));
            }
        }else if (object instanceof BigDecimal){
            if (object==null){
                dateDouble=0d;
            }else {
                BigDecimal bigDecimal= (BigDecimal) object;
                dateDouble= bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }else if(object instanceof Integer){
            Integer integer= (Integer) object;
            if (StringUtils.isEmpty(String.valueOf(integer))){
                dateDouble=0d;
            }else {
                BigDecimal b = new BigDecimal(String.valueOf(integer));
                dateDouble= b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }else{
            String string=String.valueOf(object);
            if (StringUtils.isEmpty(String.valueOf(string))){
                dateDouble=0d;
            }else {
                BigDecimal b = new BigDecimal(String.valueOf(string));
                dateDouble= b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }

        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The   scale   must   be   a   positive   integer   or   zero");
        }
        if(scale == 0){
            return new DecimalFormat("0").format(dateDouble);
        }
        String formatStr = "0.";
        for(int i=0;i<scale;i++){
            formatStr = formatStr + "0";
        }
        return new DecimalFormat(formatStr).format(dateDouble);
    }

    /**
     * 将double格式化为指定小数位的String，不足小数位用0补全
     * 保留2位有效数字，默认2位
     * @param object 需要格式化的数字，可以为Float、String、BigDecimal类型
     * @return
     */
    public static String roundByScale(Object object) {
        int scale=2;
        Double dateDouble=0d;
        if (object instanceof Float){
            Float dataFloat= (Float) object;
            if (StringUtils.isEmpty(String.valueOf(dataFloat))){
                dateDouble=0d;
            }else {
                BigDecimal b = new BigDecimal(String.valueOf(dataFloat));
                dateDouble= b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }else if (object instanceof String){
            if (StringUtils.isEmpty(String.valueOf(object))){
                dateDouble=0d;
            }else {
                dateDouble=Double.parseDouble(String.valueOf(object));
            }
        }else if (object instanceof BigDecimal){
            if (object==null){
                dateDouble=0d;
            }else {
                BigDecimal bigDecimal= (BigDecimal) object;
                dateDouble= bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }else if(object instanceof Integer){
            Integer integer= (Integer) object;
            if (StringUtils.isEmpty(String.valueOf(integer))){
                dateDouble=0d;
            }else {
                BigDecimal b = new BigDecimal(String.valueOf(integer));
                dateDouble= b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }else {
            String string=String.valueOf(object);
            if (StringUtils.isEmpty(String.valueOf(string))){
                dateDouble=0d;
            }else {
                BigDecimal b = new BigDecimal(String.valueOf(string));
                dateDouble= b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }

        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The   scale   must   be   a   positive   integer   or   zero");
        }
        if(scale == 0){
            return new DecimalFormat("0").format(dateDouble);
        }
        String formatStr = "0.";
        for(int i=0;i<scale;i++){
            formatStr = formatStr + "0";
        }
        return new DecimalFormat(formatStr).format(dateDouble);
    }


    /**
     * 求余数
     * @param b1
     * @param b2
     * @return b1%b2
     */
    public static BigDecimal getRemainder(String b1, String b2){
        BigDecimal bigDecimal1=new BigDecimal(b1);
        BigDecimal bigDecimal2=new BigDecimal(b2);
        BigDecimal remainder;
        remainder=bigDecimal1.remainder(bigDecimal2);
        return remainder;
    }

    /**
     * 将bigDecimal值转化成float值
     * @param bigDecimal
     * @return
     */
    public static float getFloatValueForBigDecimal(BigDecimal bigDecimal){
        return bigDecimal.floatValue();
    }

    /**
     * 获取手机上安装的apk版本号versionCode
     *
     * @return
     */
    public static int getLocalVersionCode(Context mContext) {
        PackageManager pm = mContext.getPackageManager();
        int vsesion = 0;
        try {
            PackageInfo info = pm.getPackageInfo(mContext.getPackageName(), 0);
            vsesion = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return vsesion;
    }

    /**
     * 获取手机上安装的apk版本号versionName
     *
     * @param mContext
     * @return
     */
    public static String getLocalVersionName(Context mContext) {
        PackageManager pm = mContext.getPackageManager();
        String vsesion = "";
        try {
            PackageInfo info = pm.getPackageInfo(mContext.getPackageName(), 0);
            vsesion = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return vsesion;
    }

    /**
     * 判断网络连接是否可用
     *
     * @param context
     * @return
     */
    public final static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {// 如果仅仅是用来判断网络连接.则可以使用 cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断是wifi还是3g网络,用户的体现性在这里了，wifi就可以建议下载或者在线播放 判断当前网络是否是wifi网络
     */
    public static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    // 下载完成后打开安装apk界面
    public static void installApk(File file, Context context) {
        //Log.i("UpdateApkDownService.installApk", "版本更新获取sd卡的安装包的路径=" + file.getAbsolutePath());
        Intent openFile = getFileIntent(file);
        context.startActivity(openFile);
        BaseApplication.isDownLoadApp=false;
    }

    public static Intent getFileIntent(File file) {
        Uri uri = Uri.fromFile(file);
        String type = getMIMEType(file);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, type);
        return intent;
    }

    public static String getMIMEType(File f) {
        String type = "";
        String fName = f.getName();
        // 取得扩展名
        String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length());
        if (end.equals("apk")) {
            type = "application/vnd.android.package-archive";
        } else {
            // /*如果无法直接打开，就跳出软件列表给用户选择 */
            type = "*/*";
        }
        return type;
    }

    /**
     * 获取当前手机的屏幕宽和高
     * @param activity
     * @return
     */
    public static PhoneInfoBean getPhoneWidthAndHeight(Activity activity){
        PhoneInfoBean bean=new PhoneInfoBean();
        // 通过WindowManager获取屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        bean.setDisWidth(dm.widthPixels);
        bean.setDisHeight(dm.heightPixels);
        return bean;
    }

    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     * @param anchorView  呼出window的view
     * @param contentView   window的内容布局
     * @return window显示的左上角的xOff,yOff坐标
     */
    public static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenHeight = ScreenUtils.getScreenHeight(anchorView.getContext());
        final int screenWidth = ScreenUtils.getScreenWidth(anchorView.getContext());
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
    }

    /**
     * 通过wifi调用打印机打印
     */
    public static void PrintWriterByWifi(){
        // Code in Activity
        try {
            Socket sock = new Socket("192.168.199.245", 9100); // ip and port of printer
            PrintWriter oStream = new PrintWriter(sock.getOutputStream());
            oStream.println("\t\t Text to The Printer");
            oStream.println("\n\n\n");
            oStream.close();
            sock.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在操作打印机之前还需要执行这么一行代码，那就是复位打印机
     */
    private static final byte[] RESET= {0x1b,0x40};

    public static void PrintWriterByWifi(final Activity activity){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Looper.prepare();
                    //创建一个Socket
                    //这里直接输入ip地址和端口号
                    Socket socket = new Socket("127.0.0.1", 9999);
                    //当前已经连接成功，并且是处于活跃状态
                    //判断是否建立通信
                    if (socket.isConnected() && socket.getKeepAlive()) {
                        //获取服务端的输入流,这里可用可不用，主要看产品
                        InputStream inputStream = socket.getInputStream();
                        //获取服务端的输出流，这个就一定要取到，因为这个关系到能不能向服务端发送出消息的操作
                        OutputStream os = socket.getOutputStream();
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                        //向服务器端发送一条消息，在打印机里面，\n的动作是必要的，如果没有\n的动作，打印机是不会打印出任何东西的
                        bw.write("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
                        bw.flush();
                        //读取服务器返回的消息
                        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                        String mess = br.readLine();

                        //直接使用输出流将你想要打印的文本打印出来就可以了
//                        try{
//                            byte[] data = text.getBytes("GB2312");
//                            outputStream.write(data,0, data.length);
//                            outputStream.flush();
//                        }catch(IOException e) {
//                            Log.e("printText "+ e);
//                        }

                        Toast.makeText(BaseApplication.appContext, "服务器：" + mess, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BaseApplication.appContext, "client 连接失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    Toast.makeText(BaseApplication.appContext, "client IOException：" + e, Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }

    /**
     * 获取当前时间截
     * @return
     */
    public static long getCurrentTimeMillis(){
        return System.currentTimeMillis();
    }

    /**
     * 分钟转换成毫秒
     * @param millis
     * @return
     */
    public static long getMillisToMinutes(int millis){
        long timeMillis=millis*60*1000;
        return timeMillis;
    }

    /**
     * 获取手机端IP地址
     * @param context
     * @return
     */
    public static String getPhoneIp(final Context context) {
        String ip = null;
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // mobile 3G Data Network
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        // wifi
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        // 如果3G网络和wifi网络都未连接，且不是处于正在连接状态 则进入Network Setting界面 由用户配置网络连接
        if (mobile == NetworkInfo.State.CONNECTED
                || mobile == NetworkInfo.State.CONNECTING) {
            ip =  getLocalIpAddress(context);
        }
        if (wifi == NetworkInfo.State.CONNECTED  || wifi == NetworkInfo.State.CONNECTING) {
            //获取wifi服务
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            //判断wifi是否开启
            if (!wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
            }
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();
            ip =(ipAddress & 0xFF ) + "." +
                    ((ipAddress >> 8 ) & 0xFF) + "." +
                    ((ipAddress >> 16 ) & 0xFF) + "." +
                    ( ipAddress >> 24 & 0xFF) ;
        }
        return ip;
    }

    /**
     * 手机GPRS网络的IP
     * @param context
     * @return
     */
    public static String getLocalIpAddress(Context context) {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Toast.makeText(BaseApplication.appContext, "WifiPreference IpAddress:"+ex.toString(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    /**
     * 使用默认浏览器打开
     * @param mContext
     * @param url
     */
    public static void OpenInterNet(Context mContext, String url){
        if (!url.contains("http://")) {
            url="http://"+url;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        mContext.startActivity(intent);
    }

}

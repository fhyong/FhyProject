# BaseProject使用手册
Android项目基础工具 --- baselibs
# github地址
https://github.com/fanson1/BaseProject
# 注释文档
doc
# android studio中gradle引用方式：
## Step 1. Add it in your root build.gradle at the end of repositories:
### 	allprojects {
### 		repositories {
### 			...
### 			maven { url 'https://jitpack.io' }
### 		}
### 	}
## Step 2. Add the dependency
### 	dependencies {
### 	        compile 'com.github.fanson1:BaseProject:v20180411'
### 	}
## 其他方式参照：
https://jitpack.io/#fanson1/BaseProject/1.0.0
# 目录
## 一、application：
Application抽取
## 二、bean：
实体类抽取
## 三、net：网络请求封装
### 1.ApiService：
接口定义
### 2.ApiServiceFactory：
okHttpClient，Retrofit，ApiService初始化
## 四、ui：BaseUI封装
### 1.BaseActivity：
主要处理Activity的视图数据初始化流程，键盘显示隐藏，沉浸式状态栏及Activity统一的栈管理
### 2.BaseFragment：
主要处理Fragment的视图数据初始化流程，点击事件绑定
## 五、util：工具类封装
### 1.app：
应用管理，界面跳转等
### 2.bezier：
贝塞尔曲线动画
### 3.bitmap：
位图工具
### 4.hint：
日志和吐司
### 5.img：
图片加载
### 6.layoutmanager：
自定义线性管理器
### 7.pinyin：
汉字转拼音工具封装
### 8.refresh：
列表刷新和加载更多封装
### 9.common：
通用工具
## 六、view：自定义View封装

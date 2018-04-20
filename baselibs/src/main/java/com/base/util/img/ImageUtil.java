package com.base.util.img;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.util.Base64;
import android.widget.ImageView;

import com.base.application.BaseApplication;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by ${qianzhihe} on 2017/6/12.
 */

public class ImageUtil {
    private static volatile ImageUtil mImageUtil;
    /**
     * 裁剪成圆角图片
     */
    public static final int TO_ROUND = 1;
    /**
     * 裁剪成圆形图片
     */
    public static final int TO_CIRCLE = 2;

    private static int placeImgId;// 加载占位图id
    private static int errorImgId;// 错误占位图id

    private ImageUtil() {
    }

    /**
     * 获取ImageUtil实例
     *
     * @return
     */
    public static ImageUtil getInstance() {
        if (mImageUtil == null) {
            synchronized (ImageUtil.class) {
                if (mImageUtil == null) {
                    mImageUtil = new ImageUtil();
                }
            }
        }
        return mImageUtil;
    }

    /**
     * 初始化占位图片和错误图片id：通常在Application的onCreate中调用一次即可
     *
     * @param placeImgId 加载占位图id
     * @param errorImgId 错误占位图id
     */
    public static void init(int placeImgId, int errorImgId) {
        ImageUtil.placeImgId = placeImgId;
        ImageUtil.errorImgId = errorImgId;
    }

    /**
     * 通过 uri seletion选择来获取图片的真实uri
     *
     * @param uri
     * @param seletion
     * @return
     */
    public static String getImagePath(Context mContext, Uri uri, String seletion) {
        String path = null;
        Cursor cursor = mContext.getContentResolver().query(uri, null, seletion, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    /**
     * @param imageView    图片设置控件
     * @param imageUrl     图片URL
     * @param defaultImage 默认图片
     * @param scaleType    裁剪类型：1代表圆角， 2代表圆形， 其他代表不裁剪
     */
    public void loadImage(ImageView imageView, String imageUrl, Drawable defaultImage, int scaleType) {
        Context mContext = imageView.getContext();
        DrawableRequestBuilder drb = Glide.with(mContext)
                .load(imageUrl)
                .placeholder(defaultImage == null ? mContext.getResources().getDrawable(placeImgId, null) : defaultImage)
                .error(defaultImage == null ? mContext.getResources().getDrawable(errorImgId, null) : defaultImage)
                .crossFade();
        if (scaleType == 1) {
            drb.transform(new GlideRoundTransform(mContext));
        } else if (scaleType == 2) {
            drb.transform(new GlideCircleTransform(mContext));
        }
        drb.into(imageView);
    }

    /**
     * 将bitmap转换成byte
     *
     * @param bitmap
     * @return
     */
    public static byte[] getBitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    /**
     * 图片路径转换成bitmap
     *
     * @param path 路径
     * @param w    图片宽
     * @param h    图片高
     * @return
     */
    public static Bitmap convertToBitmap(String path, int w, int h) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        // 返回为空
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        float scaleWidth = 0.f, scaleHeight = 0.f;
        if (width > w || height > h) {
            // 缩放
            scaleWidth = ((float) width) / w;
            scaleHeight = ((float) height) / h;
        }
        opts.inJustDecodeBounds = false;
        float scale = Math.max(scaleWidth, scaleHeight);
        opts.inSampleSize = (int) scale;
        WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(path, opts));
        return Bitmap.createScaledBitmap(weak.get(), w, h, true);
    }


    /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param file 源文件
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public static String getFileToBase64(File file) {
        FileInputStream inputFile = null;
        try {
            inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return Base64.encodeToString(buffer, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap 目标图片
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

}

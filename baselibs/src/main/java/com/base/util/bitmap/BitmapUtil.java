package com.base.util.bitmap;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 描述: Bitmap图片压缩处理工具类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-01-09 18:31
 */

public class BitmapUtil {
    /**
     * 压缩图片并上传， 目标图片默认压缩到不超过300KB
     *
     * @param tempPic 源文件
     * @return
     */
    public static File getCompressImage(final File tempPic) {
        final String pic_path = tempPic.getPath();
        String targetPath = Environment.getExternalStorageDirectory().getPath() + File.separator + tempPic.getName();
        final String compressImage = compressImage(pic_path, targetPath, 300);// 目标图片默认压缩到质量为30
        final File compressedPic = new File(compressImage);
        return compressedPic;
    }

    /**
     * 压缩图片并上传， 目标图片压缩到指定大小之下，参数以KB为单位
     *
     * @param tempPic       源文件
     * @param maxTargetSize 最大目标值KB
     * @return
     */
    public static File getCompressImage(final File tempPic, int maxTargetSize) {
        final String pic_path = tempPic.getPath();
        String targetPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + File.separator + "98linju" + File.separator + tempPic.getName();
        final String compressImage = compressImage(pic_path, targetPath, maxTargetSize);// 目标图片不超过matTargetSize
        final File compressedPic = new File(compressImage);
        if (!compressedPic.getParentFile().exists()) {
            compressedPic.getParentFile().mkdirs();
        }
        return compressedPic;
    }

    /**
     * 图片压缩到指定大小
     *
     * @param filePath   源文件路径
     * @param targetPath 目标文件路径
     * @param size       最大目标值KB
     * @return
     */
    public static String compressImage(String filePath, String targetPath, int size) {
        Bitmap image = getSmallBitmap(filePath, 720f, 1280f);// 先进行比例压缩
        //Bitmap image = BitmapFactory.decodeFile(filePath);
        File outputFile = new File(targetPath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;

        while (baos.toByteArray().length / 1024f > size) { // 循环判断如果压缩后图片是否大于目标大小,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }

        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm);// 把ByteArrayInputStream数据生成图片
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outputFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, out);// 进行质量压缩
            bitmap.recycle();
            bitmap = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return outputFile.getPath();
    }

    /**
     * 计算压缩比例
     *
     * @param width   目标宽度
     * @param height  目标高度
     * @param options 解码参数
     * @return
     */
    private static int computeScale(float width, float height, BitmapFactory.Options options) {
        // 计算缩放比
        int w = options.outWidth;
        int h = options.outHeight;

        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > width) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (options.outWidth / width);
        } else if (w < h && h > height) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (options.outHeight / height);
        }
        if (be <= 0)
            be = 1;
        return be;
    }

    /**
     * 根据指定路径的图片文件做宽高比例压缩
     *
     * @param filePath 源文件路径
     * @param width    目标宽度
     * @param height   目标高度
     * @return 返回压缩后的Bitmap
     */
    public static Bitmap getSmallBitmap(String filePath, float width, float height) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只解析图片边沿，获取宽高
        BitmapFactory.decodeFile(filePath, options);
        int be = computeScale(width, height, options);

        options.inSampleSize = be;
        // 完整解析图片返回bitmap
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 图片宽高比例压缩
     *
     * @param inputStream 源文件输入流
     * @param width       目标文件宽度
     * @param height      目标文件高度
     * @return 返回压缩后的Bitmap
     */
    public static Bitmap getSmallBitmap(InputStream inputStream, float width, float height) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只解析图片边沿，获取宽高
        BitmapFactory.decodeStream(inputStream, null, options);
        int be = computeScale(width, height, options);

        options.inSampleSize = be;
        // 完整解析图片返回bitmap
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    /**
     * 根据Uri获取图片的绝对路径
     *
     * @param context 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    public static String getRealPathFromUri(Context context, Uri uri) {
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion >= 19) { // api >= 19
            return getRealPathFromUriAboveApi19(context, uri);
        } else { // api < 19
            return getRealPathFromUriBelowAPI19(context, uri);
        }
    }

    /**
     * 适配api19以下(不包括api19),根据uri获取图片的绝对路径
     *
     * @param context 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    private static String getRealPathFromUriBelowAPI19(Context context, Uri uri) {
        return getDataColumn(context, uri, null, null);
    }

    /**
     * 适配api19及以上,根据uri获取图片的绝对路径
     *
     * @param context 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    @SuppressLint("NewApi")
    private static String getRealPathFromUriAboveApi19(Context context, Uri uri) {
        String filePath = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // 如果是document类型的 uri, 则通过document id来进行处理
            String documentId = DocumentsContract.getDocumentId(uri);
            if (isMediaDocument(uri)) { // MediaProvider
                // 使用':'分割
                String id = documentId.split(":")[1];

                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = {id};
                filePath = getDataColumn(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, selectionArgs);
            } else if (isDownloadsDocument(uri)) { // DownloadsProvider
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                filePath = getDataColumn(context, contentUri, null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是 content 类型的 Uri
            filePath = getDataColumn(context, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // 如果是 file 类型的 Uri,直接获取图片对应的路径
            filePath = uri.getPath();
        }
        return filePath;
    }

    /**
     * 获取数据库表中的 _data 列，即返回Uri对应的文件路径
     *
     * @param context       上下文
     * @param uri           源文件uri
     * @param selection     图片字段名
     * @param selectionArgs 占位参数
     * @return 返回对应文件的真实路径
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        String path = null;

        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                path = cursor.getString(columnIndex);
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return path;
    }

    /**
     * 判断指定uri对应的文件是否为多媒体文件
     *
     * @param uri 文件uri
     * @return
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * 判断指定uri对应的文件是否为文档文件
     *
     * @param uri 文件uri
     * @return
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param file 源文件
     * @return
     */
    public static String getImageStr(File file) {
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
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param filePath 源文件路径
     * @return
     */
    public static String getImageStr(String filePath) {
        return getImageStr(new File(filePath));
    }
}

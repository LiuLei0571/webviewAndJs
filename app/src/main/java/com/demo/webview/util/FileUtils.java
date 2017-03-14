package com.demo.webview.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import com.demo.App;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * 用途：
 * Created by milk on 17/3/14.
 * 邮箱：649444395@qq.com
 */

public class FileUtils {
    /**
     * 把图片压缩到200K
     *
     * @param oldpath
     *            压缩前的图片路径
     * @param newPath
     *            压缩后的图片路径
     * @return
     */
    /**
     * 把图片压缩到200K
     *
     * @param oldpath 压缩前的图片路径
     * @param newPath 压缩后的图片路径
     * @return
     */
    public static File compressFile(String oldpath, String newPath) {
        if (TextUtils.isEmpty(oldpath) || TextUtils.isEmpty(newPath)) {
            return null;
        }

        File f = new File(oldpath);
        if (!f.exists()) {
            return null;
        }

        Bitmap compressBitmap = FileUtils.decodeBitmapFromFile(oldpath);
        Bitmap newBitmap = ratingImage(oldpath, compressBitmap);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        newBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        byte[] bytes = os.toByteArray();

        File file = null;
        try {
            file = FileUtils.getFileFromBytes(bytes, newPath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!newBitmap.isRecycled()) {
                newBitmap.recycle();
            }
            if (compressBitmap != null) {
                if (!compressBitmap.isRecycled()) {
                    compressBitmap.recycle();
                }
            }
        }
        return file;
    }

    public static Bitmap getRatingImage(String oldpath, int reqWidth, int reqHeight) {
        Bitmap compressBitmap = FileUtils.decodeFile(oldpath, reqWidth, reqHeight);
        return ratingImage(oldpath, compressBitmap);
    }

    public static Bitmap getRatingImage(String oldpath) {
        Bitmap compressBitmap = FileUtils.decodeFile(oldpath);
        return ratingImage(oldpath, compressBitmap);
    }

    private static Bitmap ratingImage(String filePath, Bitmap bitmap) {
        int degree = readPictureDegree(filePath);
        return rotaingImageView(degree, bitmap);
    }

    /**
     * 旋转图片
     *
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 把字节数组保存为一个文件
     *
     * @param b
     * @param outputFile
     * @return
     */
    public static File getFileFromBytes(byte[] b, String outputFile) {
        File ret = null;
        BufferedOutputStream stream = null;
        try {
            ret = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(ret);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            // log.error("helper:get file from byte process error!");
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // log.error("helper:get file from byte process error!");
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    /**
     * 图片压缩
     *
     * @param fPath
     * @return
     */
    public static Bitmap decodeFile(String fPath) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        opts.inDither = false; // Disable Dithering mode
        opts.inPurgeable = true; // Tell to gc that whether it needs free
        opts.inInputShareable = true; // Which kind of reference will be used to
        BitmapFactory.decodeFile(fPath, opts);
        final int REQUIRED_SIZE = 600;
        int scale = 1;
        if (opts.outHeight > REQUIRED_SIZE || opts.outWidth > REQUIRED_SIZE) {
            final int heightRatio = Math.round((float) opts.outHeight
                    / (float) REQUIRED_SIZE);
            final int widthRatio = Math.round((float) opts.outWidth
                    / (float) REQUIRED_SIZE);
            scale = heightRatio < widthRatio ? heightRatio : widthRatio;//
        }
        Log.i("scale", "scal =" + scale);
        opts.inJustDecodeBounds = false;
        opts.inSampleSize = scale;
        Bitmap bm = BitmapFactory.decodeFile(fPath, opts);
        return bm;
    }

    public static Bitmap decodeFile(String fPath, int reqWidth, int reqHeight) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        opts.inDither = false; // Disable Dithering mode
        opts.inPurgeable = true; // Tell to gc that whether it needs free
        opts.inInputShareable = true; // Which kind of reference will be used to
        BitmapFactory.decodeFile(fPath, opts);
        opts.inSampleSize = calculateInSampleSize(opts, reqWidth, reqHeight);
//        Bitmap bitmap = BitmapFactory.decodeFile(fPath, opts).copy(Bitmap.Config.ARGB_8888, false);
//        Bitmap bitmap = BitmapFactory.decodeFile(fPath, opts);
        Bitmap bitmap = BitmapFactory.decodeFile(fPath, opts);
        DisplayMetrics dm = App.getContext().getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int w_width = w_screen;
        int b_width = bitmap.getWidth();
        int b_height = bitmap.getHeight();
        int w_height = w_width * b_height / b_width;
        bitmap = Bitmap.createScaledBitmap(bitmap, w_width, w_height, false);
        return bitmap;
    }

    //计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 创建目录
     *
     * @param path
     */
    public static void setMkdirs(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
            Log.d("file", "目录不存在  创建目录    ");
        } else {
            Log.d("file", "目录存在" + path);
        }
    }

    /**
     * 获取目录名称
     *
     * @param url
     * @return FileName
     */
    public static String getFileName(String url) {
        int lastIndexStart = url.lastIndexOf("/");
        if (lastIndexStart != -1) {
            return url.substring(lastIndexStart + 1, url.length());
        } else {
            return new Date().getTime() + "";
        }
    }

    /**
     * 删除该目录下的文件
     *
     * @param path
     */
    public static void delFile(String path) {
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 删除该目录下的文件
     *
     * @param file
     */
    public static void delFile(File file) {
        if (file != null) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static boolean isExist(String url) {
        File file = new File(getCachePath(App.getContext()) + "/" + getFileName(url));
        return file.exists();
    }

    public static String getCachePath(@NonNull Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        if (null != externalCacheDir) {
            String path = externalCacheDir.getAbsolutePath() + "/uploadImg/";
            setMkdirs(path);
            return path;
        } else {
            File cacheDir = context.getCacheDir();
            String path = cacheDir.getAbsolutePath() + "/uploadImg/";
            setMkdirs(path);
            return path;
        }
    }

    public static String getCachePath(@NonNull Context context, String fileLast) {
        File externalCacheDir = context.getExternalCacheDir();
        if (null != externalCacheDir) {
            String path = externalCacheDir.getAbsolutePath() + "/" + fileLast + "/";
            setMkdirs(path);
            return path;
        } else {
            File cacheDir = context.getCacheDir();
            String path = cacheDir.getAbsolutePath() + "/" + fileLast + "/";
            setMkdirs(path);
            return path;
        }
    }

    public static String getImgName(String fileType) {
        return new Date().getTime() + fileType;
    }

    /**
     * 照片压缩存放路径
     */
    public static String getB2CImgPath(Context context) {
        return getCachePath(context) + getImgName(".jpg");
    }

    public static Bitmap decodeBitmapFromFile(String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;//不加载图片到内存，仅获得图片宽高
            BitmapFactory.decodeFile(imagePath, options);
            if (options.outHeight == -1 || options.outWidth == -1) {
                try {
                    ExifInterface exifInterface = new ExifInterface(imagePath);
                    int height = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.ORIENTATION_NORMAL);//获取图片的高度
                    int width = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, ExifInterface.ORIENTATION_NORMAL);//获取图片的宽度
                    options.outWidth = width;
                    options.outHeight = height;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            options.inSampleSize = calculateInSampleSize(options);
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            return BitmapFactory.decodeFile(imagePath, options);

        } else {
            return null;
        }
    }

    private static int calculateInSampleSize(BitmapFactory.Options options) {
        final int REQUIRED_SIZE = 1350;
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > REQUIRED_SIZE || width > REQUIRED_SIZE) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > REQUIRED_SIZE && (halfWidth / inSampleSize) > REQUIRED_SIZE) {
                inSampleSize *= 2;
            }

            long totalPixels = width * height / inSampleSize;

            final long totalReqPixelsCap = REQUIRED_SIZE * REQUIRED_SIZE * 2;
            while (totalPixels > totalReqPixelsCap) {
                inSampleSize *= 2;
                totalPixels /= 2;
            }
        }
        return inSampleSize;
    }

}

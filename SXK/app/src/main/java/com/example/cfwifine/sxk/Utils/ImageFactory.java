package com.example.cfwifine.sxk.Utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ImageFactory {

    private Context mContext;

    public ImageFactory(Context con) {
        this.mContext = con;
    }

    /**
     * »ñÈ¡resÀïµÄÍ¼Æ¬×ÊÔ´
     *
     * @param name
     * @return
     */
    public Bitmap getRes(String name) {
        ApplicationInfo appInfo = mContext.getApplicationInfo();
        int resID = mContext.getResources().getIdentifier(name, "drawable",
                appInfo.packageName);
        return BitmapFactory.decodeResource(mContext.getResources(), resID);
    }

    /**
     * »ñÈ¡resÀïµÄÍ¼Æ¬×ÊÔ´
     *
     * @param name
     * @return
     */
    public int getResID(String name) {
        ApplicationInfo appInfo = mContext.getApplicationInfo();
        int resID = mContext.getResources().getIdentifier(name, "drawable",
                appInfo.packageName);
        return resID;
    }

    /**
     * ¸ù¾ÝÍ¼Æ¬£¬½øÐÐÑ¹Ëõ
     *
     * @param image
     * @return
     */
    public Bitmap comp(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {// ÅÐ¶ÏÈç¹ûÍ¼Æ¬´óÓÚ1M,½øÐÐÑ¹Ëõ±ÜÃâÔÚÉú³ÉÍ¼Æ¬£¨BitmapFactory.decodeStream£©Ê±Òç³ö
            baos.reset();// ÖØÖÃbaos¼´Çå¿Õbaos
            image.compress(Bitmap.CompressFormat.JPEG, 80, baos);// ÕâÀïÑ¹Ëõ80%£¬°ÑÑ¹ËõºóµÄÊý¾Ý´æ·Åµ½baosÖÐ
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // ¿ªÊ¼¶ÁÈëÍ¼Æ¬£¬´ËÊ±°Ñoptions.inJustDecodeBounds Éè»ØtrueÁË
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // ÏÖÔÚÖ÷Á÷ÊÖ»ú±È½Ï¶àÊÇ800*480·Ö±æÂÊ£¬ËùÒÔ¸ßºÍ¿íÎÒÃÇÉèÖÃÎª
        float hh = 800f;// ÕâÀïÉèÖÃ¸ß¶ÈÎª800f
        float ww = 480f;// ÕâÀïÉèÖÃ¿í¶ÈÎª480f
        // Ëõ·Å±È¡£ÓÉÓÚÊÇ¹Ì¶¨±ÈÀýËõ·Å£¬Ö»ÓÃ¸ß»òÕß¿íÆäÖÐÒ»¸öÊý¾Ý½øÐÐ¼ÆËã¼´¿É
        int be = 1;// be=1±íÊ¾²»Ëõ·Å
        if (w > h && w > ww) {// Èç¹û¿í¶È´óµÄ»°¸ù¾Ý¿í¶È¹Ì¶¨´óÐ¡Ëõ·Å
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// Èç¹û¸ß¶È¸ßµÄ»°¸ù¾Ý¿í¶È¹Ì¶¨´óÐ¡Ëõ·Å
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// ÉèÖÃËõ·Å±ÈÀý
        // ÖØÐÂ¶ÁÈëÍ¼Æ¬£¬×¢Òâ´ËÊ±ÒÑ¾­°Ñoptions.inJustDecodeBounds Éè»ØfalseÁË
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressImage(bitmap);// Ñ¹ËõºÃ±ÈÀý´óÐ¡ºóÔÙ½øÐÐÖÊÁ¿Ñ¹Ëõ
    }

    /**
     * ´Ë·½·¨»áÊ¹Í¼Æ¬Ê§Õæ
     *
     * @param image
     * @return
     */
    public Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// ÖÊÁ¿Ñ¹Ëõ·½·¨£¬ÕâÀï100±íÊ¾²»Ñ¹Ëõ£¬°ÑÑ¹ËõºóµÄÊý¾Ý´æ·Åµ½baosÖÐ
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) { // Ñ­»·ÅÐ¶ÏÈç¹ûÑ¹ËõºóÍ¼Æ¬ÊÇ·ñ´óÓÚ100kb,´óÓÚ¼ÌÐøÑ¹Ëõ
            baos.reset();// ÖØÖÃbaos¼´Çå¿Õbaos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// ÕâÀïÑ¹Ëõoptions%£¬°ÑÑ¹ËõºóµÄÊý¾Ý´æ·Åµ½baosÖÐ
            options -= 20;// Ã¿´Î¶¼¼õÉÙ10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// °ÑÑ¹ËõºóµÄÊý¾Ýbaos´æ·Åµ½ByteArrayInputStreamÖÐ
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// °ÑByteArrayInputStreamÊý¾ÝÉú³ÉÍ¼Æ¬
        return bitmap;
    }

    /**
     * »ñÈ¡ÊÖ»úÉÏÍ¼Æ¬£¬²¢Ñ¹Ëõ
     *
     * @param filePath
     * @return
     */
    public Bitmap getBitmapCompression(String filePath) {
        Bitmap photo_temp = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        photo_temp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 100) {
            Bitmap photo;
            BitmapFactory.Options option = new BitmapFactory.Options();
            option.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, option);
            option.inSampleSize = calculateInSampleSize(option, 250, 250);
            option.inJustDecodeBounds = false;
            photo = BitmapFactory.decodeFile(filePath, option);
            return photo;
        } else {
            return photo_temp;
        }
    }

    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap getBitmapFormUri(Context c, Uri uri, boolean original) {

        InputStream is = null;
        try {
            is = c.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (is == null) {
            return null;
        }

        // ²âÁ¿Í¼Æ¬¸ß¿í
        BitmapFactory.Options factoryOptions = new BitmapFactory.Options();
        factoryOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, factoryOptions);
        int imageWidth = factoryOptions.outWidth;

        // »ñÈ¡Ëõ·ÅÖµ
        int width = 600;
        float imageWidthF = imageWidth;
        float widthF = width;
        int scaleFactor = Math.round(imageWidthF / widthF);

        if (scaleFactor == 3) {
            scaleFactor = 4;
        } else if (scaleFactor > 5 && scaleFactor < 8) {
            scaleFactor = 8;
        }

        factoryOptions.inJustDecodeBounds = false;
        factoryOptions.inSampleSize = scaleFactor;
        factoryOptions.inPurgeable = true;

        // ÈôÊÇÉÏ´«Ôò¶ÁÈ¡Ô­Í¼£¬ÈôÊÇÔ¤ÀÀÔò¶ÁÈ¡Ñ¹ËõÍ¼
        try {
            is = c.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (is == null) {
            return null;
        }

        Bitmap bitmap = null;
        try {
            if (original) {
                bitmap = BitmapFactory.decodeStream(is);
            } else {
                bitmap = BitmapFactory.decodeStream(is, null, factoryOptions);
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }

    public static byte[] bitmapToByteAAA(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.size() / 1024 < 80)
            return baos.toByteArray();
        if (baos.size() > 1024) {// ÅÐ¶ÏÈç¹ûÍ¼Æ¬´óÓÚ1M,½øÐÐÑ¹Ëõ±ÜÃâÔÚÉú³ÉÍ¼Æ¬£¨BitmapFactory.decodeStream£©Ê±Òç³ö
            baos.reset();// ÖØÖÃbaos¼´Çå¿Õbaos
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);// ÕâÀïÑ¹Ëõ50%£¬°ÑÑ¹ËõºóµÄÊý¾Ý´æ·Åµ½baosÖÐ
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        try {
            baos.flush();
            baos.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // ¿ªÊ¼¶ÁÈëÍ¼Æ¬£¬´ËÊ±°Ñoptions.inJustDecodeBounds Éè»ØtrueÁË
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // ÏÖÔÚÖ÷Á÷ÊÖ»ú±È½Ï¶àÊÇ800*480·Ö±æÂÊ£¬ËùÒÔ¸ßºÍ¿íÎÒÃÇÉèÖÃÎª
        float hh = 800f;// ÕâÀïÉèÖÃ¸ß¶ÈÎª800f
        float ww = 480f;// ÕâÀïÉèÖÃ¿í¶ÈÎª480f
        // Ëõ·Å±È¡£ÓÉÓÚÊÇ¹Ì¶¨±ÈÀýËõ·Å£¬Ö»ÓÃ¸ß»òÕß¿íÆäÖÐÒ»¸öÊý¾Ý½øÐÐ¼ÆËã¼´¿É
        int be = 1;// be=1±íÊ¾²»Ëõ·Å
        if (w > h && w > ww) {// Èç¹û¿í¶È´óµÄ»°¸ù¾Ý¿í¶È¹Ì¶¨´óÐ¡Ëõ·Å
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// Èç¹û¸ß¶È¸ßµÄ»°¸ù¾Ý¿í¶È¹Ì¶¨´óÐ¡Ëõ·Å
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// ÉèÖÃËõ·Å±ÈÀý
        // ÖØÐÂ¶ÁÈëÍ¼Æ¬£¬×¢Òâ´ËÊ±ÒÑ¾­°Ñoptions.inJustDecodeBounds Éè»ØfalseÁË
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        try {
            isBm.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        byte[] bytes2 = compressBitmap(bitmap);
        try {
            bitmap.recycle();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return bytes2;
    }

    public static byte[] compressBitmap(Bitmap bitmap) {
        int size = 80;
        if (bitmap == null) {
            return null;// Èç¹ûÍ¼Æ¬±¾ÉíµÄ´óÐ¡ÒÑ¾­Ð¡ÓÚÕâ¸ö´óÐ¡ÁË£¬¾ÍÃ»±ØÒª½øÐÐÑ¹Ëõ
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// Èç¹ûÇ©ÃûÊÇpngµÄ»°£¬Ôò²»¹ÜqualityÊÇ¶àÉÙ£¬¶¼²»»á½øÐÐÖÊÁ¿µÄÑ¹Ëõ
        if (getSizeOfBitmap(bitmap) <= size) {
            return baos.toByteArray();
        }
        int quality = 100;
        while (baos.toByteArray().length / 1024f > size) {
            if (baos.toByteArray().length / 1024 - size < 50) {
                quality = quality - 1;// Ã¿´Î¶¼¼õÉÙ4
            } else {
                quality = quality - 4;// Ã¿´Î¶¼¼õÉÙ4
            }
            if (quality <= 0) {
                break;
            }
            baos.reset();// ÖØÖÃbaos¼´Çå¿Õbaos
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        }
        try {
            baos.flush();
            baos.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return baos.toByteArray();
    }

    public static float getSizeOfBitmap(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// ÕâÀï100µÄ»°±íÊ¾²»Ñ¹ËõÖÊÁ¿
        long length = baos.toByteArray().length / 1024;// ¶Á³öÍ¼Æ¬µÄkb´óÐ¡
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return length;
    }

    public static byte[] bitmapToByteBBB(Bitmap bmp) {
        if (bmp != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            return baos.toByteArray();
        }
        return null;
    }
}


//package com.example.cfwifine.selectpicdemo;
//
//        import java.io.ByteArrayInputStream;
//        import java.io.ByteArrayOutputStream;
//        import java.io.File;
//        import java.io.FileNotFoundException;
//        import java.io.FileOutputStream;
//        import java.io.IOException;
//
//        import android.graphics.Bitmap;
//        import android.graphics.Bitmap.Config;
//        import android.graphics.BitmapFactory;
//
///**
// * Image compress factory class
// *
// * @author
// *
// */
//public class ImageFactory {
//
//    /**
//     * Get bitmap from specified image path
//     *
//     * @param imgPath
//     * @return
//     */
//    public Bitmap getBitmap(String imgPath) {
//        // Get bitmap through image path
//        BitmapFactory.Options newOpts = new BitmapFactory.Options();
//        newOpts.inJustDecodeBounds = false;
//        newOpts.inPurgeable = true;
//        newOpts.inInputShareable = true;
//        // Do not compress
//        newOpts.inSampleSize = 1;
//        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
//        return BitmapFactory.decodeFile(imgPath, newOpts);
//    }
//
//    /**
//     * Store bitmap into specified image path
//     *
//     * @param bitmap
//     * @param outPath
//     * @throws FileNotFoundException
//     */
//    public void storeImage(Bitmap bitmap, String outPath) throws FileNotFoundException {
//        FileOutputStream os = new FileOutputStream(outPath);
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
//    }
//
//    /**
//     * Compress image by pixel, this will modify image width/height.
//     * Used to get thumbnail
//     *
//     * @param imgPath image path
//     * @param pixelW target pixel of width
//     * @param pixelH target pixel of height
//     * @return
//     */
//    public Bitmap ratio(String imgPath, float pixelW, float pixelH) {
//        BitmapFactory.Options newOpts = new BitmapFactory.Options();
//        // 开始读入图片，此时把options.inJustDecodeBounds 设回true，即只读边不读内容
//        newOpts.inJustDecodeBounds = true;
//        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
//        // Get bitmap info, but notice that bitmap is null now
//        Bitmap bitmap = BitmapFactory.decodeFile(imgPath,newOpts);
//
//        newOpts.inJustDecodeBounds = false;
//        int w = newOpts.outWidth;
//        int h = newOpts.outHeight;
//        // 想要缩放的目标尺寸
//        float hh = pixelH;// 设置高度为240f时，可以明显看到图片缩小了
//        float ww = pixelW;// 设置宽度为120f，可以明显看到图片缩小了
//        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
//        int be = 1;//be=1表示不缩放
//        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
//            be = (int) (newOpts.outWidth / ww);
//        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
//            be = (int) (newOpts.outHeight / hh);
//        }
//        if (be <= 0) be = 1;
//        newOpts.inSampleSize = be;//设置缩放比例
//        // 开始压缩图片，注意此时已经把options.inJustDecodeBounds 设回false了
//        bitmap = BitmapFactory.decodeFile(imgPath, newOpts);
//        // 压缩好比例大小后再进行质量压缩
////        return compress(bitmap, maxSize); // 这里再进行质量压缩的意义不大，反而耗资源，删除
//        return bitmap;
//    }
//
//    /**
//     * Compress image by size, this will modify image width/height.
//     * Used to get thumbnail
//     *
//     * @param image
//     * @param pixelW target pixel of width
//     * @param pixelH target pixel of height
//     * @return
//     */
//    public Bitmap ratio(Bitmap image, float pixelW, float pixelH) {
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        image.compress(Bitmap.CompressFormat.JPEG, 100, os);
//        if( os.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
//            os.reset();//重置baos即清空baos
//            image.compress(Bitmap.CompressFormat.JPEG, 50, os);//这里压缩50%，把压缩后的数据存放到baos中
//        }
//        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
//        BitmapFactory.Options newOpts = new BitmapFactory.Options();
//        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
//        newOpts.inJustDecodeBounds = true;
//        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
//        Bitmap bitmap = BitmapFactory.decodeStream(is, null, newOpts);
//        newOpts.inJustDecodeBounds = false;
//        int w = newOpts.outWidth;
//        int h = newOpts.outHeight;
//        float hh = pixelH;// 设置高度为240f时，可以明显看到图片缩小了
//        float ww = pixelW;// 设置宽度为120f，可以明显看到图片缩小了
//        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
//        int be = 1;//be=1表示不缩放
//        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
//            be = (int) (newOpts.outWidth / ww);
//        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
//            be = (int) (newOpts.outHeight / hh);
//        }
//        if (be <= 0) be = 1;
//        newOpts.inSampleSize = be;//设置缩放比例
//        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
//        is = new ByteArrayInputStream(os.toByteArray());
//        bitmap = BitmapFactory.decodeStream(is, null, newOpts);
//        //压缩好比例大小后再进行质量压缩
////      return compress(bitmap, maxSize); // 这里再进行质量压缩的意义不大，反而耗资源，删除
//        return bitmap;
//    }
//
//    /**
//     * Compress by quality,  and generate image to the path specified
//     *
//     * @param image
//     * @param outPath
//     * @param maxSize target will be compressed to be smaller than this size.(kb)
//     * @throws IOException
//     */
//    public void compressAndGenImage(Bitmap image, String outPath, int maxSize) throws IOException {
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        // scale
//        int options = 100;
//        // Store the bitmap into output stream(no compress)
//        image.compress(Bitmap.CompressFormat.JPEG, options, os);
//        // Compress by loop
//        while ( os.toByteArray().length / 1024 > maxSize) {
//            // Clean up os
//            os.reset();
//            // interval 10
//            options -= 10;
//            image.compress(Bitmap.CompressFormat.JPEG, options, os);
//        }
//
//        // Generate compressed image file
//        FileOutputStream fos = new FileOutputStream(outPath);
//        fos.write(os.toByteArray());
//        fos.flush();
//        fos.close();
//    }
//
//    /**
//     * Compress by quality,  and generate image to the path specified
//     *
//     * @param imgPath
//     * @param outPath
//     * @param maxSize target will be compressed to be smaller than this size.(kb)
//     * @param needsDelete Whether delete original file after compress
//     * @throws IOException
//     */
//    public void compressAndGenImage(String imgPath, String outPath, int maxSize, boolean needsDelete) throws IOException {
//        compressAndGenImage(getBitmap(imgPath), outPath, maxSize);
//
//        // Delete original file
//        if (needsDelete) {
//            File file = new File (imgPath);
//            if (file.exists()) {
//                file.delete();
//            }
//        }
//    }
//
//    /**
//     * Ratio and generate thumb to the path specified
//     *
//     * @param image
//     * @param outPath
//     * @param pixelW target pixel of width
//     * @param pixelH target pixel of height
//     * @throws FileNotFoundException
//     */
//    public void ratioAndGenThumb(Bitmap image, String outPath, float pixelW, float pixelH) throws FileNotFoundException {
//        Bitmap bitmap = ratio(image, pixelW, pixelH);
//        storeImage( bitmap, outPath);
//    }
//
//    /**
//     * Ratio and generate thumb to the path specified
//     *
//     * @param image
//     * @param outPath
//     * @param pixelW target pixel of width
//     * @param pixelH target pixel of height
//     * @param needsDelete Whether delete original file after compress
//     * @throws FileNotFoundException
//     */
//    public void ratioAndGenThumb(String imgPath, String outPath, float pixelW, float pixelH, boolean needsDelete) throws FileNotFoundException {
//        Bitmap bitmap = ratio(imgPath, pixelW, pixelH);
//        storeImage( bitmap, outPath);
//
//        // Delete original file
//        if (needsDelete) {
//            File file = new File (imgPath);
//            if (file.exists()) {
//                file.delete();
//            }
//        }
//    }
//
//}
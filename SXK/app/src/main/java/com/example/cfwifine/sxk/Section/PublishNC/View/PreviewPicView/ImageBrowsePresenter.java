package com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static android.R.attr.path;


public class ImageBrowsePresenter {

    private ImageBrowseView view;
    private List<String> images;
    private int position;
    private String[] imageTypes = new String[] { ".jpg",".png", ".jpeg","webp"};

    public ImageBrowsePresenter(ImageBrowseView view) {
        this.view = view;
    }

    public void loadImage(){
        Intent intent = view.getDataIntent();

        int s = intent.getIntExtra("TYPE",0);
        if (s == 999){
            // 说明是预览穿过类的图
            images = intent.getStringArrayListExtra("images");
            Log.e("朋友圈images",""+images);
            position = intent.getIntExtra("position",0);
            Log.e("获取position",""+position);
            view.setImageBrowse(images,position,999);
        }else {
            images = intent.getStringArrayListExtra("images");
            Log.e("发朋友圈images",""+images);
            position = intent.getIntExtra("position",0);
            Log.e("获取position",""+position);
            view.setImageBrowse(images,position, 0);
        }



//        saveImage();
    }

    // 删除图片的方法
    public void delePosition(int position){
            images.remove(position);
            reloadImage();
    }

    private void reloadImage() {
        view.setImageBrowse(images,position, 0);
    }


    public void saveImage(int position, final ImageBrowseActivity imageBrowseActivity){

        LogUtil.e("保存一张图" + images.get(position).toString()+"");
        String url = images.get(position).toString();
        RequestQueue mQueue = Volley.newRequestQueue(imageBrowseActivity);
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                Log.e("Bitmap=====",bitmap.toString()+"" );

                // 首先保存图片
                File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                String fileName = System.currentTimeMillis() + ".jpg";
                File file = new File(appDir, fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // 其次把文件插入到系统图库
                try {
                    MediaStore.Images.Media.insertImage(imageBrowseActivity.getContentResolver(),
                            file.getAbsolutePath(), fileName, null);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // 最后通知图库更新
                imageBrowseActivity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));

            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        mQueue.add(request);
    }

    public String getPositionImage(){
        Log.e("weizhi",""+(position));
        return images.get(position);

    }

    public List<String> getImages() {
        return images;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getImageType(String imageUrl){
        String imageType = "";
        if(imageUrl.endsWith(imageTypes[0])){
            imageType = "jpg";
        }else if(imageUrl.endsWith(imageTypes[1])){
            imageType = "png";
        }else{
            imageType = "jpeg";
        }
        return imageType;
    }

}


package com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView;

import android.content.Intent;
import android.util.Log;

import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.List;


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


    public void saveImage(int position){
        //利用Picasso加载图片
        LogUtil.e("保存一张图" + position);


//        String imageUrl = images.get(position).toString();
//        Log.e("address",""+imageUrl);
//        Picasso.with(view.getMyContext()).load(new File(imageUrl)).error(R.drawable.image_selected).into(view.);

//        Picasso.with(view.getMyContext()).load(new File(imageUrl)).error(R.drawable.image_selected).into(new Target() {
//            @Override
//            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//
//            }
//
//            @Override
//            public void onBitmapFailed(Drawable errorDrawable) {
//
//            }
//
//            @Override
//            public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//            }
//        });

//        if (imageUrl.equals("TAG")){
//            .setImageResource(R.drawable.image_selected);
//        }else if (list.get(i).equals("MORE")){
//            iv.setImageResource(R.drawable.image_unselected);
//        }else {
//            Picasso.with(context).load(new File(list.get(i).toString())).error(R.drawable.image_selected).into(iv);
//        }


//        Picasso.with(view.getMyContext()).load(imageUrl).into(new Target() {
//            @Override
//            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                // 创建目录
//                File appDir = new File(Environment.getExternalStorageDirectory(), "JellyImage");
//                if (!appDir.exists()) {
//                    appDir.mkdir();
//                }
//
//                String imageType = getImageType(imageUrl); //获取图片类型
//                String fileName = System.currentTimeMillis() + "." + imageType;
//                File file = new File(appDir, fileName);
//                //保存图片
//                try {
//                    FileOutputStream fos = new FileOutputStream(file);
//                    if(TextUtils.equals(imageType,"jpg")) imageType = "jpeg";
//                    imageType = imageType.toUpperCase();
//                    bitmap.compress(Bitmap.CompressFormat.valueOf(imageType), 100, fos);
//                    fos.flush();
//                    fos.close();
//                    Toast.makeText(view.getMyContext(),"保存成功",Toast.LENGTH_SHORT).show(); //Toast
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                // 其次把文件插入到系统图库
//                try {
//                    MediaStore.Images.Media.insertImage(view.getMyContext().getContentResolver(), file.getAbsolutePath(), fileName, null);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                // 最后通知图库更新
//                view.getMyContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getPath())));
//            }
//
//            @Override
//            public void onBitmapFailed(Drawable errorDrawable) {
//
//            }
//
//            @Override
//            public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//            }
//        });
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


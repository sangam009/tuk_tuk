package com.traveldiary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by mohit on 26/08/17.
 */

public class CompressImage {

    public static Object getCompressedImage(Bitmap bitmap, int finalHeight, boolean weightCompress,CompressImage.ReturnType returnType ) {
        if (bitmap == null) {
            return null;
        }
        try {
            int targetHeight = 720;
            if (finalHeight > 100) {
                targetHeight = finalHeight;
            }
            float ratio = (float)targetHeight / (float) bitmap.getHeight();
            int targetWidth =(int)( (ratio) * bitmap.getWidth());
            bitmap = Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, false);

            if (weightCompress) {
                byte[] byteArray = null;

                try {
                    do {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
                        byteArray = byteArrayOutputStream.toByteArray();
                        bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(byteArray));

                    } while (byteArray.length > 220000);
                    Log.e("BYTEARRAY", byteArray.length + "");
                    if(returnType == ReturnType.BASE64){
                        String base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                        return base64;
                    }else{
                        return bitmap;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public enum ReturnType {
        BASE64,BITMAP
    }
}

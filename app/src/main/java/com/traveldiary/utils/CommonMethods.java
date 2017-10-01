package com.traveldiary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by mohit on 22/06/17.
 */

public class CommonMethods {

    public static String getErrorMessage(String Tag) {
        String msg = "";
        switch (Tag) {
            case Constants.Error.NETWORK_ERROR: {
                msg = "network";
                break;
            }
            case Constants.Error.LoginError.LOGIN_FACEBOOK_RESPONSE_ERROR: {
                msg = "fbResponseError";
                break;
            }
            case Constants.Error.LoginError.LOGIN_SERVER_RESPONSE_ERROR: {
                msg = "loginResponseError";
                break;
            }
            case Constants.Error.LoginError.USER_BLOCKED: {
                msg = "userBlocked";
                break;
            }
            default: {
                return "";
            }

        }
        return msg;
    }


    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public static Bitmap getBitmap(ImageView image) {
        image.setDrawingCacheEnabled(true);
        return image.getDrawingCache();
    }

    public static String bitmapToBase64(Bitmap bitmap, boolean compress) {
        if (bitmap == null) {
            return null;
        }
        byte[] byteArray = null;
        String base64 = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            if (compress) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
            } else {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

            }
            byteArray = byteArrayOutputStream.toByteArray();
            Log.e("BYTEARRAY", byteArray.length + "");

            base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

            return base64;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            byteArray = null;
            base64 = null;
        }
    }

    public static Bitmap base64ToBitmap(String base64) {
        byte[] decodedString = null;
        try {
            decodedString = Base64.decode(base64, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            decodedString = null;
        }
    }

    public static long getTimeAfterGivenSeconds(String timestamp, int getProfileById) {
        Long longValue = Long.parseLong(timestamp) + (getProfileById * 1000);
        return longValue;

    }

    public static String UriToBase64(Activity activity, String imageUri) {
        try {
            String image = (String) CompressImage.getCompressedImage(MediaStore.Images.Media.getBitmap(activity.getContentResolver(), Uri.parse(imageUri)),720, true, CompressImage.ReturnType.BASE64);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";

    }

    public static boolean isNetworkConnected(Context activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    // Returns age given the date of birth
    public static int calculateAge(Calendar dob) throws Exception {
        Calendar today = Calendar.getInstance();

        int curYear = today.get(Calendar.YEAR);
        int dobYear = dob.get(Calendar.YEAR);

        int age = curYear - dobYear;

        // if dob is month or day is behind today's month or day
        // reduce age by 1
        int curMonth = today.get(Calendar.MONTH);
        int dobMonth = dob.get(Calendar.MONTH);
        if (dobMonth > curMonth) { // this year can't be counted!
            age--;
        } else if (dobMonth == curMonth) { // same month? check for day
            int curDay = today.get(Calendar.DAY_OF_MONTH);
            int dobDay = dob.get(Calendar.DAY_OF_MONTH);
            if (dobDay > curDay) { // this year can't be counted!
                age--;
            }
        }

        return age;
    }

//    public static String getStringFiltersUniqueKey(String filters) {
//
//        ArrayList<String> keyArray = new ArrayList<>();
//        FilterModel filterModel = new ParseFilter().getParsedFilters(filters);
//        if (filterModel != null) {
//            keyArray.add(String.valueOf(filterModel.getMinAge()));
//            keyArray.add(String.valueOf(filterModel.getMaxAge()));
//            keyArray.add(filterModel.getGender());
//            keyArray.add(String.valueOf(filterModel.isRandom()));
//            if (filterModel.getCurrentlyAtCountry() != null && filterModel.getCurrentlyAtCountry().length() > 0) {
//                keyArray.add(filterModel.getCurrentlyAtCountry());
//            }
//
//            if (filterModel.getCurrentlyAtFbId() != null && filterModel.getCurrentlyAtFbId().length() > 0) {
//                keyArray.add(filterModel.getCurrentlyAtFbId());
//            }
//
//            if (filterModel.getCurrentlyAtLocations() != null && filterModel.getCurrentlyAtLocations().size() > 0) {
//                keyArray.addAll(filterModel.getCurrentlyAtLocations());
//            }
//
//            Collections.sort(keyArray, new Comparator<String>() {
//                @Override
//                public int compare(String o1, String o2) {
//                    return o1.compareToIgnoreCase(o2);
//                }
//            });
//            String keyVal = "";
//            for (String s :
//                    keyArray) {
//                keyVal += s;
//            }
//            return keyVal;
//        }
//
//
//        return null;
//    }

    public static String getUniqueKey(ArrayList<String> arrayList) {
        Collections.sort(arrayList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        String keyVal = "";
        for (String s :
                arrayList) {
            keyVal += s + "_";
        }
        return keyVal.substring(0, keyVal.length() - 1);
    }

//    public static String getImageUrl(UserImage userImage) {
//        if (userImage.getImageUrl() != null && userImage.getImageUrl().contains("http")) {
//            return userImage.getImageUrl();
//        } else {
//            return userImage.getImageBaseUrl() + "/" + userImage.getImageUrl();
//
//        }
//    }

    /*public static Uri BitmapToUri(Activity activity,Bitmap currImage) {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File mFileTemp = null;
        String root = activity.getDir("HotieeNotiee", Context.MODE_PRIVATE).getAbsolutePath();
        File myDir = new File(root + "/Images");
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        try {
            mFileTemp = File.createTempFile(imageFileName, ".jpg", myDir.getAbsoluteFile());

            if (mFileTemp != null) {
                FileOutputStream fout;
                try {
                    fout = new FileOutputStream(mFileTemp);
                    currImage.compress(Bitmap.CompressFormat.PNG, 100, fout);
                    fout.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Uri.fromFile(mFileTemp);
            }
            return null;
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }

    }*/


    public static int dpToPx(int dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int pxToDp(int px, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e("KEYBOARD", "CRASH");
        }
    }

    public static long getCurrentUtcTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        Date date = new Date();
//        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        Date date1 = new Date(dateFormat.format(date));
        return date.getTime();
    }

    public static String timer(long superhotEnabled) {
        try {


            long time = System.currentTimeMillis();
            long timeLeft = (superhotEnabled - time) / 1000;
            if (timeLeft < 0) {
                return "00 : 00 : 00";
            }
            int min = 0, hour = 0, sec = 0;
            String mins = "", hours = "", secs = "";
            sec = (int) (timeLeft % 60);
            timeLeft = timeLeft / 60;

            min = (int) (timeLeft % 60);
            timeLeft = timeLeft / 60;

            hour = (int) (timeLeft % 60);

            if (hour < 10) {
                hours = "0" + hour;
            } else {
                hours = String.valueOf(hour);
            }

            if (min < 10) {
                mins = "0" + min;
            } else {
                mins = String.valueOf(min);
            }
            if (sec < 10) {
                secs = "0" + sec;
            } else {
                secs = String.valueOf(sec);
            }
            return hours + " : " + mins + " : " + secs;
        } catch (Exception e) {
            return "00 : 00 : 00";
        }


    }
}

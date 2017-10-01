package com.traveldiary.firebase;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

//import com.traveldiary.login.ui.LoginActivity;
import com.traveldiary.R;
//import com.traveldiary.notification.HandleChatNotifications;
import com.traveldiary.home.MainActivity;
import com.traveldiary.utils.Constants;
import com.traveldiary.utils.LocalPreferenceManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;

/**
 * Created by mohit on 1/17/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "ON MESSAGE RECEIVED";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        //Logi("HASH MAP VALUES",remoteMessage.getData().toString());

        LocalPreferenceManager.getInstance().initialise(this);
        //Logd(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            //Logd(TAG, "Message data payload: " + remoteMessage.getData());

        }

//        if (remoteMessage.getNotification() == null) {
//            if (remoteMessage.getData().containsKey("type")) {
//                if (remoteMessage.getData().get("type").equalsIgnoreCase(Constants.Chat.CHAT_NOTIFICATION) && !remoteMessage.getData().get(Constants.Chat.SENDER_USERID).equalsIgnoreCase(LocalPreferenceManager.getInstance().getUserId())) {
//                    new HandleChatNotifications().chatNotifications(this, remoteMessage);
//                }
//            }
//        }else {
//            sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), "false");
//        }

    }

    private void sendNotification(String messageBody, String msg, String TrueOrFalse) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("AnotherActivity", TrueOrFalse);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(bitmap)/*Notification icon image*/
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(messageBody).setContentText(msg)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(10 /* ID of notification */, notificationBuilder.build());

/*
        .setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(bitmap))*/
/*Notification with Image*/


    }

    private boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {

                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }

}

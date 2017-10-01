package com.traveldiary.base;

import android.app.Activity;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;


import com.traveldiary.R;
import com.traveldiary.home.MainActivity;
import com.traveldiary.utils.CommonMethods;
import com.traveldiary.utils.HomeWatcher;


public class BaseActivity extends AppCompatActivity {


    private ProgressDialog progressDialog;
    protected static PowerManager powerManager;
    protected static PowerManager.WakeLock wakeLock;
    protected static KeyguardManager keyguardManager;
    protected static KeyguardManager.KeyguardLock lock;
    protected static final int FINISH = 0;
    private HomeWatcher mHomeWatcher;
    protected final int DATE_PICK = 123456;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        try {
            NotificationManager mNotificationManager = (NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);
            mNotificationManager.cancelAll();

        } catch (Exception e) {

        }



        powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNotDimScreen");

        //===========================screen lock test=======================


        keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
        lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);


        //===========================screen lock test=======================

        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                // do something here...
                onHomeBtnPressed();

            }

            @Override
            public void onHomeLongPressed() {
            }
        });
        mHomeWatcher.startWatch();


    }


    //==============================soundTest===================================


    protected void showOkAlert(String msg, String title, final int tag) {
        try {
            if (msg != null) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                if (title.length() != 0) {
                    dialog.setTitle(title);
                }
                dialog.setMessage(msg);
                dialog.setCancelable(false);
                dialog.setNeutralButton(R.string.Ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        onShowAlertOkPressed(tag, null);


                    }
                });
                dialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void showCustomDialog(String title, String message, final int tag) {
        if (message != null) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            if (title.length() != 0) {
                dialog.setTitle(title);
            }
            dialog.setMessage(message);
            dialog.setCancelable(false);
            dialog.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    onShowAlertOkPressed(tag, null);


                }
            });
            dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    onShowAlertNotOkPressed(tag);


                }
            });

            dialog.show();
        }
    }

    protected void onShowAlertNotOkPressed(int tag) {
        if (tag == FINISH) {
            finish();
        }
    }


    protected void onShowAlertOkPressed(int tag, Object date) {
        if (tag == FINISH) {
            finish();
        }
    }

    protected ProgressDialog getProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }
        return progressDialog;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHomeWatcher != null) {
            mHomeWatcher.stopWatch();
        }



    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    protected void showCustomDialog(String title, String message, final int tag, String positiveBtnText, String negativeBtnText) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton(positiveBtnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                onShowAlertOkPressed(tag, null);
            }
        });
        dialog.setNegativeButton(negativeBtnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    protected void onHomeBtnPressed() {

    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    protected void datePick(final int tag, long time){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Select Date");
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.pick_date);
        dialog.show();
        final DatePicker date = (DatePicker) dialog.findViewById(R.id.select_date);

        if(tag == MainActivity.SOURCE_DATE_PICK){
            if(time > 0) {
                date.setMaxDate(time);
            }
            date.setMinDate(System.currentTimeMillis());
        }else {
            date.setMinDate(time);
        }
        TextView submit = (TextView) dialog.findViewById(R.id.submit);
        TextView cancel = (TextView) dialog.findViewById(R.id.cancel);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onShowAlertOkPressed(tag, date);
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowAlertNotOkPressed(tag);
                dialog.dismiss();
            }
        });
    }




}

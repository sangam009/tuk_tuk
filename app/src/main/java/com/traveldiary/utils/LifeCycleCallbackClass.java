package com.traveldiary.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.traveldiary.base.BaseModel;

/**
 * Created by mohit on 24/08/17.
 */

public class LifeCycleCallbackClass implements Application.ActivityLifecycleCallbacks {
    int activityCount = 0;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activityCount == 0) {
            LocalPreferenceManager.getInstance().initialise(activity);
            Singleton.getInstance().initializeHelper(activity);
            if (LocalPreferenceManager.getInstance().getUserId() == null) {
                LocalPreferenceManager.getInstance().setUserId(Settings.Secure.getString(activity.getContentResolver()              , Settings.Secure.ANDROID_ID));
            }

        }
        activityCount++;
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (activityCount == 1) {
            Log.e("ACTIVITY_COUNTIN", activityCount + "");


        } else {
            Log.e("ACTIVITY_COUNTIN!", activityCount + "");
        }
        activityCount++;

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        activityCount--;
        if (activityCount == 1) {
            Log.e("ACTIVITY_COUNTOUT", activityCount + "");
        } else {
            Log.e("ACTIVITY_COUNTOUT1", activityCount + "");
        }

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityCount--;
    }
}

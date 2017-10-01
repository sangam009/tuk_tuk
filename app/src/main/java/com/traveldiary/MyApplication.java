package com.traveldiary;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.traveldiary.utils.LifeCycleCallbackClass;


/**
 * Created by mohit on 04/08/17.
 */

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new LifeCycleCallbackClass());
    }
}

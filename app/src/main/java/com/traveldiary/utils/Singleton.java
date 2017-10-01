package com.traveldiary.utils;

import android.content.Context;

import com.traveldiary.database.databaseInitialization.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * Created by mohit on 22/06/17.
 */

public class Singleton {
    private static final Singleton ourInstance = new Singleton();
    private DatabaseHelper databaseHelper;



    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
    }

   /* public void setImgUrl(String url){
        imgUrl=url;
    }*/

    public DatabaseHelper initializeHelper(Context activity) {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(activity,DatabaseHelper.class);
        }
        return databaseHelper;
    }
    public DatabaseHelper getHelper(){
        return databaseHelper;
    }


}

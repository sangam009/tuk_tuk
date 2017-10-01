package com.traveldiary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by mohit on 9/8/2016.
 */
public class LocalPreferenceManager {

    private static LocalPreferenceManager self;
    private SharedPreferences preferences = null;
    private SharedPreferences.Editor editor = null;
    private boolean isInitialised = false;
    private static final String MY_PREFERENCE = "mypreferencekey";

    public void initialise(Context context) {
        if (!isInitialised || preferences == null) {
            preferences = context.getSharedPreferences("MyPreferencePlace", Context.MODE_PRIVATE);
            editor = preferences.edit();
            isInitialised = true;
        }
    }

    public static LocalPreferenceManager getInstance() {
        if (self == null) {
            self = new LocalPreferenceManager();
        }
        return self;
    }

    private LocalPreferenceManager() {
    }

    public void setPreference(String key, String newPreferenceValue) {
        //this.myPreference = newPreferenceValue;
        editor.putString(key, newPreferenceValue);
        editor.commit();
    }

    public void setPreference(String key, boolean newPreferenceValue) {
        editor.putBoolean(key, newPreferenceValue);
        editor.commit();
    }

    public void setPreference(String key, int newPreferenceValue) {
        editor.putInt(key, newPreferenceValue);
        editor.commit();
    }


    public String getStringPreference(String key) {
        return preferences.getString(key, "");
    }

    public String getStringPreference(String key, String val) {
        return preferences.getString(key, val);
    }

    public int getIntPreference(String key, int val) {
        return preferences.getInt(key, val);
    }

    public boolean getBooleanPreference(String key) {
        return preferences.getBoolean(key, false);
    }

    public boolean getBooleanPreference(String key, boolean val) {
        return preferences.getBoolean(key, val);
    }

    public void setGuestIdPreference(String guestId, String s) {
        editor.putString(guestId, s);
        editor.commit();

    }

    public String getGuestIdPreference(String key) {
        return preferences.getString(key, "-1");
    }

    public String getUserId() {
        return preferences.getString("userId", null);
    }

    public void setUserId( String userId) {
        editor.putString("userId", userId);
        editor.commit();
    }


    public long getLongPreference(String key) {
        return preferences.getLong(key, 0);
    }

    public long getLongPreference(String key, long defValue) {
        return preferences.getLong(key, defValue);
    }


    public void setPreference(String key,long value) {
        editor.putLong(key,value);
        editor.commit();
    }

    public void setUserName(String displayName) {
        editor.putString("userDisplayName", displayName);
        editor.commit();
    }

    public String getUserName() {
        return preferences.getString("userDisplayName", null);
    }

    public void setUserImage(String displayImage) {
        editor.putString("userDisplayImage", displayImage);
        editor.commit();
    }

    public String getUserImage() {
        return preferences.getString("userDisplayImage", null);
    }

    public void setPreference(String key, Set<String> chats){
        editor.putStringSet(key,chats);
        editor.commit();
    }

    public Set<String> getStringSet(String key){
        return preferences.getStringSet(key,null);
    }

    public void removeKey(String key){
        editor.remove(key);
        editor.commit();
    }

    public String getSelfProfileJson(){
        return preferences.getString("selfProfile",null);
    }

    public void setSelfProfile(String jsonString){
        editor.putString("selfProfile",jsonString).commit();
    }

}
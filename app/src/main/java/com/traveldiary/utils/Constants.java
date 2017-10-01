package com.traveldiary.utils;

import android.app.Application;
import android.os.Build;

import com.traveldiary.BuildConfig;

/**
 * Created by mohit on 22/06/17.
 */

public class Constants {

    public static final String FB_ALBUMS_LIST = "FB_ALBUMS_LIST";
    public static final String FB_USER_INFO = "FB_USER_INFO";
    public static final String FB_USER_LIKES = "FB_USER_LIKES";
    public static final String SHARE_PUBLIC_PROFILE="Hey, i Found a really hot profile on hottie-nottie app.Check this out!";
    public static final String SHARE_My_PROFILE="Why don't you vote my profile and help me raising the temperatures.";
    public static final String FIRST_LOGIN = "isFirstLogin";
    public static final String PROFILE_SET = "profileSet";
    public static final String LAST_ACCESS_TOKEN_GENERATED = "lastAccessTokenGenerated";
    public static final String TYPE="Few";
    public static final String SHARE_PROFILE="shareProfile";
    public static final String DEVICE="ANDROID";
    public interface Chat{

        String CHAT_NOTIFICATION = "chatNotification";
        String SENDER_USERID = "senderUserId";
        String CHAT_ID = "chatId";
        String OTHER_USER_NAME = "otherUserName";
        String OTHER_USER_IMAGE = "otherUserImage";
        String OTHER_USER_TIMESTAMP = "timeStamp";
        String OTHER_USER_IS_NEW = "isNewUser";
        String BODY = "body";
        String TITLE = "title";
        String PREFERENCE_CHATSET_KEY = "chats";
        String PREFERENCE_CHATHEADS_KEY = "chatHeads";
        String PREFERENCE_CHAT_COUNT = "chatCount";
        String LOCAL_BROADCAST = "localBroadcast";
        String LAST_NEW_CHAT_TIME = "lastSeenTIme";
    }

    public enum ReceivedFrom {
        CACHE,
        SERVICE,
        CONNECTION_FAILURE_CACHE
    }

    public enum Modules {
        PERSONAL_PROFILE,
        IMAGE_MODULE,
        LOGIN_MODULE,
        LIKES_MODULE, INTEREST_MODULE,
        CHAT_MODULE,RATINGS
    }

    public interface Error {
        String NETWORK_ERROR = "NETWORK_ERROR";
        String JSON_EXCEPTION = "JSON_EXCEPTION";
        String NULL_RESPONSE_ERROR = "NULL_RESPONSE_ERROR";

        interface LoginError {
            String LOGIN_SERVER_RESPONSE_ERROR = "LOGIN_SERVER_RESPONSE_ERROR";
            String LOGIN_FACEBOOK_RESPONSE_ERROR = "LOGIN_FACEBOOK_RESPONSE_ERROR";
            String USER_BLOCKED = "USER_BLOCKED";
            String DATA_UPDATED = "Data Updated";
        }

        interface SettingsError {
            String PROFILE_SETTING_ERROR = "PROFILE_SETTING_ERROR";
            String PROFILE_NOT_AVAILABLE_ERROR = "PROFILE_NOT_AVAILABLE_ERROR";
        }

        interface GalleryError {
            String FACEBOOK_GALLERY_NULL = "FACEBOOK_GALLERY_NULL";
        }
    }

    public static class ServiceConfiguration {
        public static final int CHAT_UPDATE = 15 * 60;
        public static final int PACKAGES_TIME_FACTOR = 24 * 60 * 60;
        public static String DEFAULT_ACTION_ON_SUPERHOT = "SuperHot";
        public static String APP_SERVICE_OUTAGE_MESSAGE = "";
        public static boolean APP_IS_SERVICE_OUTAGE = false;
        public static String APP_DOWNLOAD_URL = "";
        public static String APP_UPDATE_MESSAGE = "New Update Available!";
        public static String APP_CURRENT_VERSION = BuildConfig.VERSION_NAME;
        // All settings are done on seconds bases
        public static boolean MANDATORY_UPDATE = false;
    }


}

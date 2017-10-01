package com.traveldiary.firebase;

import android.util.Log;

import com.traveldiary.utils.LocalPreferenceManager;
import com.google.firebase.iid.FirebaseInstanceId;

import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by sachingupta on 30/11/16.
 */

public class MyFirebaseIdService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";
    private static final String FRIENDLY_ENGAGE_TOPIC = "friendly_engage";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        LocalPreferenceManager.getInstance().initialise(this);
        sendRegistrationToServer(refreshedToken);
        FirebaseMessaging.getInstance()
                .subscribeToTopic(FRIENDLY_ENGAGE_TOPIC);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        LocalPreferenceManager.getInstance().setPreference("firebaseToken", refreshedToken);
    }
}

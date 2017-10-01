package com.traveldiary.firebase.services;

import android.app.Activity;
import android.os.Build;

import com.traveldiary.base.BaseModel;
import com.traveldiary.utils.Constants;
import com.traveldiary.utils.LocalPreferenceManager;
import com.traveldiary.base.BaseService;
import com.traveldiary.firebase.interfaces.FireBaseResponse;
import com.traveldiary.utils.URLs;
import com.traveldiary.webConnection.WebConnectionModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mohit on 21/07/17.
 */

public class FirebaseServices extends BaseService{
//    public void sendTokenToServer(Activity context, String userId, String refreshedToken, FireBaseResponse fireBaseResponse) {
//        JSONObject params = new JSONObject();
//
//        try {
//            params.put("id", userId);
//            params.put("deviceType", "ANDROID");
//            params.put("os", "android");
//            params.put("osVersion",Build.VERSION.SDK_INT);
//            params.put("deviceToken",refreshedToken);
//            params.put("deviceModel",Build.MODEL);
//            params.put("deviceUniqueIdentifier",Build.FINGERPRINT);
//
//            WebConnectionModel webConnectionModel =  new WebConnectionModel();
//            webConnectionModel.setForceRefresh(true)
//                    .setRequestType(WebConnectionModel.JsonRequestType.POST)
//                    .setShouldCache(false)
//                    .setShouldClearModule(false)
//                    .setUrl(URLs.REGISTER_DEVICE)
//                    .setJsonRequest(params.toString());
//
//            callService(context, webConnectionModel, new ParseUserFbInterest(), new FireBaseResponse() {
//                @Override
//                public void onResponse(BaseModel baseModel, String jsonString, String reasonOfError, Constants.ReceivedFrom receivedFrom) {
//                    LocalPreferenceManager.getInstance().removeKey("firebaseToken");
//                }
//            });
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//    }
}

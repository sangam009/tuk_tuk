package com.traveldiary.base;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.traveldiary.base.interfaces.BaseInterface;
import com.traveldiary.database.databaseInitialization.DatabaseHelper;
import com.traveldiary.utils.Constants;
import com.traveldiary.utils.Singleton;
import com.traveldiary.webConnection.WebConnectionHandler;
import com.traveldiary.webConnection.WebConnectionModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mohit on 03/07/17.
 */

public class BaseService {

    protected DatabaseHelper dbHelper  = Singleton.getInstance().getHelper();

    protected static final int NO_TIME_FACTOR_REQ = 0;


    protected void parseData(BaseInterface baseInterface, BaseParser parser, String jsonString, Constants.ReceivedFrom receivedFrom) {
        BaseModel baseModel = null;
        try {
            if (parser == null){
                baseInterface.onResponse(null,jsonString,null,receivedFrom);
            }else {
                baseModel = parser.parseJson(new JSONObject(jsonString));

                if (baseModel.error == null) {
                    baseInterface.onResponse(baseModel, jsonString, null, receivedFrom);
                } else {
                    baseInterface.onResponse(null, jsonString, baseModel.error, receivedFrom);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            baseInterface.onResponse(null,jsonString,Constants.Error.JSON_EXCEPTION,receivedFrom);
        }
    }


    protected void callService(final Context activity, final WebConnectionModel webConnectionModel, final BaseParser parser, final BaseInterface profileResponseListener){
        class Async extends AsyncTask<Void,Void,Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                WebConnectionHandler webConnectionHandler  = new WebConnectionHandler();
                webConnectionHandler.fetchAsyncRequestForModel(activity, webConnectionModel, new WebConnectionHandler.WebConnectionResponse() {
                    @Override
                    public void Response(String jsonObject, Constants.ReceivedFrom receivedFrom, WebConnectionModel webConnectionModel) {
                        parseData(profileResponseListener,parser,jsonObject,receivedFrom);
                    }
                });
                return null;
            }
        }
        new Async().execute();
    }
}

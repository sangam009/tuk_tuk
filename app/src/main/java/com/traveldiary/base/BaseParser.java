package com.traveldiary.base;


import android.util.Log;

import com.traveldiary.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by mohit on 12/23/2016.
 */

public class BaseParser {

    private Gson gson;

    private BaseModel baseModel = new BaseModel();

    public BaseModel parseJson(JSONObject response) throws JSONException {
        baseModel.error = Constants.Error.JSON_EXCEPTION;
        return baseModel;
    }

    protected JSONObject validate(JSONObject response) {
        try {

            // response.getJSONObject("response").
            if (response != null && response.isNull("error")) {
                if (response.has("status")) {
                    if (!response.getBoolean("status")) {
                        return null;
                    }
                    return new JSONObject();
                } else {
                    return response;
                }
                /*Log.e("Flow Test","control reached outside if");*/
            } else if (response.has("status") && response.getBoolean("status")) {
                return response.getJSONObject("response");
            }else{
                return null;
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("Exception", "json");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("Exception", "main");
            // return null;
        }
        return null;

    }

    protected Gson getGson() {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            return gsonBuilder.create();
        }
        return gson;
    }
}

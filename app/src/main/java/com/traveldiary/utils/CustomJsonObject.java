package com.traveldiary.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mohit on 1/23/2017.
 */

public class CustomJsonObject{


    private JSONObject jsonObject;

    public JSONObject getJsonObject(String key) {
        try{
            if(jsonObject.has(key) && jsonObject.getJSONObject(key) != null){
                return jsonObject.getJSONObject(key);
            }
        }catch (Exception e){
            return new JSONObject();
        }
        return new JSONObject();
    }
    public CustomJsonObject getCustomJsonObject(String key) {
        try{
            if(jsonObject.has(key) && jsonObject.getJSONObject(key) != null){
                return new CustomJsonObject(jsonObject.getJSONObject(key));
            }
        }catch (Exception e){
            return new CustomJsonObject();
        }
        return new CustomJsonObject();
    }
    public CustomJsonObject getCustomJsonObject(JSONObject key) {
        try{
            if(key != null){
                return new CustomJsonObject(key);
            }
        }catch (Exception e){
            return new CustomJsonObject();
        }
        return new CustomJsonObject();
    }

    public JSONArray getJsonArray(String key) {
        try{
            if(jsonObject.has(key) && jsonObject.getJSONArray(key) != null){
                return jsonObject.getJSONArray(key);
            }
        }catch (Exception e){
            return new JSONArray();
        }
        return new JSONArray();
    }


    public CustomJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
    public CustomJsonObject(){}
    public int getInt(String key){
        try {
            if(jsonObject.has(key) && !jsonObject.isNull(key)){
                return jsonObject.getInt(key);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }
    public String getString (String key){
        try {
            if(jsonObject.has(key) && !jsonObject.isNull(key)){
                return jsonObject.getString(key);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
        return "";
    }
    public boolean getBoolean(String key,boolean defValue){
        try {
            if(jsonObject.has(key)){
                return jsonObject.getBoolean(key);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return defValue;
        }
        return defValue;
    }
}

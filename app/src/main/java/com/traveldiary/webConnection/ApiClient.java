package com.traveldiary.webConnection;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.traveldiary.base.interfaces.BaseInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by mohit on 12/23/2016.
 */


public class ApiClient {
    private static final String DATA_ERROR = "DATA_ERROR";
    protected JSONObject params = new JSONObject();

    protected BaseInterface baseResponse;


  /*  protected void responseHandler(String jsonString, BaseParser parser) {
        if (jsonString.contains("qwaszx") || jsonString.contains("FAILURE")) {
            baseResponse.onResponse(null, jsonString, Constants.Error.NETWORK_ERROR, Constants.ReceivedFrom.SERVICE);
            return;
        } else {
            try {
               *//* JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.has("error")  && jsonObject.getJSONObject("error") == null) {*//*
               BaseModel baseModel = parser.parseJson(new JSONObject(jsonString));
               if(baseModel.error == null) {
                   baseResponse.onResponse(baseModel, jsonString, null, Constants.ReceivedFrom.SERVICE);
               }else{
                   baseResponse.onResponse(null, jsonString, baseModel.error,Constants.ReceivedFrom.SERVICE);
               }
                    *//*
                }else{
                    baseResponse.onResponse(null,DATA_ERROR);
                }*//*
            } catch (JSONException e) {
                e.printStackTrace();
                baseResponse.onResponse(null, jsonString,null,Constants.ReceivedFrom.SERVICE);
            } catch (Exception e) {
                baseResponse.onResponse(null, jsonString,null,Constants.ReceivedFrom.SERVICE);
            }

        }

    }*/

    /**
     * this is volley handler to call servers
     *
     * @param activity
     * @param url
     * @param params
     * @param RESPONSE_CLASS
     */
    protected void jsonRequest(final Context activity, String url, HashMap<String, Object> params, final int RESPONSE_CLASS) {
        Log.e("Class params", params.toString());
        Log.e("Class params", url);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Response", response.toString());
                        //ResponseClassCollection.getResponse(RESPONSE_CLASS,response,activity);//parseLoginJsonResponse(response);
                        //progressDialog.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Log.e("Volly Error", error.toString());
                //responseJsonBaseInterface.onServiceResponseHandler(null,false,error.toString());
                NetworkResponse networkResponse = error.networkResponse;

                if (networkResponse != null) {
                    Log.e("Status code", String.valueOf(networkResponse.statusCode));
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("Content-Encoding", "gzip");
                return headers;
            }
        };

        req.setShouldCache(false);
        //   RequestQueue requestQueue = Volley.newRequestQueue((Context)responseJsonBaseInterface);
        //  requestQueue.add(req);
    }


    /**
     * This method is called create the json and use volley service
     *
     * @param activity
     * @param url
     * @param params
     */
    public void jsonRequest(final Context activity, String url, String params, final WebConnectionHandler.ApiCallbackResponse apiCallbackResponse) {
        Log.e("Class params", params.toString());
        Log.e("Class params", url);
        JsonObjectRequest req = null;
        try {
            req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("Response", response.toString());
                            //              ResponseClassCollection.getResponse(RESPONSE_CLASS,response,responseJsonBaseInterface);//parseLoginJsonResponse(response);
                            //        progressDialog.dismiss();
                            //baseResponse.onResponse(null,response.toString());
                            //ApiClient.this.responseHandler(response.toString(), baseParser);
                            apiCallbackResponse.apiResponse(response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.e("Error: ", error.getMessage());
                    Log.e("Volly Error", error.toString());
                    //baseResponse.onResponse(null,"Error");
                    //ApiClient.this.responseHandler("qwaszx", baseParser);
                    apiCallbackResponse.apiResponse(null);

                    NetworkResponse networkResponse = error.networkResponse;

                    if (networkResponse != null) {
                        Log.e("Status code", String.valueOf(networkResponse.statusCode));
                    }
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");

                    headers.put("Content-Encoding", "gzip");
                    return headers;
                }

                @Override
                protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                    if (response.statusCode != 200) {
                        //      baseResponse.onResponse(null,null);
                    }
                    return super.parseNetworkResponse(response);
                }
            };
        } catch (JSONException e) {
            //progressDialog.dismiss();
            //loginResponse.onResponse(null,"Json Error");
            //ApiClient.this.responseHandler("Json Error", baseParser);
            apiCallbackResponse.apiResponse(null);

            e.printStackTrace();
        }

        req.setShouldCache(false);
        int socketTimeout = 30000;//10 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, 0/*DefaultRetryPolicy.DEFAULT_MAX_RETRIES*/, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(req);
    }

    public void jsonGetRequestWithHeaders(final Context activity, String url, final String sessionToken, final String profileCode, final String loginId, final WebConnectionHandler.ApiCallbackResponse apiCallbackResponse) {
        Log.e("Class params", url);
        StringRequest req = null;
        req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response", response.toString());
                        //              ResponseClassCollection.getResponse(RESPONSE_CLASS,response,responseJsonBaseInterface);//parseLoginJsonResponse(response);
                        //        progressDialog.dismiss();
                        //baseResponse.onResponse(null,response.toString());
                        //ApiClient.this.responseHandler(response.toString(), baseParser);
                        apiCallbackResponse.apiResponse(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Log.e("Volly Error", error.toString());
                //baseResponse.onResponse(null,"Error");
                //ApiClient.this.responseHandler("qwaszx", baseParser);
                apiCallbackResponse.apiResponse(null);

                NetworkResponse networkResponse = error.networkResponse;

                if (networkResponse != null) {
                    Log.e("Status code", String.valueOf(networkResponse.statusCode));
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("sessionToken", sessionToken);
                headers.put("profileCode", profileCode);
                headers.put("loginId", loginId);

               headers.put("Content-Encoding", "gzip");
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        req.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(req);

    }

    public void jsonGetRequest(final Context activity, String url, final WebConnectionHandler.ApiCallbackResponse apiCallbackResponse) {
        Log.e("Class params", url);
        StringRequest req = null;
        req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response", response.toString());
                        //              ResponseClassCollection.getResponse(RESPONSE_CLASS,response,responseJsonBaseInterface);//parseLoginJsonResponse(response);
                        //        progressDialog.dismiss();
                        //baseResponse.onResponse(null,response.toString());
                        //ApiClient.this.responseHandler(response.toString(), baseParser);

                        apiCallbackResponse.apiResponse(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Log.e("Volly Error", error.toString());
                //baseResponse.onResponse(null,"Error");
                //ApiClient.this.responseHandler("qwaszx", baseParser);
                apiCallbackResponse.apiResponse(null);

                NetworkResponse networkResponse = error.networkResponse;

                if (networkResponse != null) {
                    Log.e("Status code", String.valueOf(networkResponse.statusCode));
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

                headers.put("Content-Encoding", "gzip");
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        req.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(req);

    }

    public void jsonDeleteRequest(final Context activity, String url, final WebConnectionHandler.ApiCallbackResponse apiCallbackResponse) {
        Log.e("Class params", url);
        StringRequest req = null;
        req = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("Response", response.toString());
                //ApiClient.this.responseHandler(response.toString(), baseParser);
                apiCallbackResponse.apiResponse(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Log.e("Volly Error", error.toString());
                //baseResponse.onResponse(null,"Error");
                //ApiClient.this.responseHandler("qwaszx", baseParser);
                apiCallbackResponse.apiResponse(null);

                NetworkResponse networkResponse = error.networkResponse;

                if (networkResponse != null) {
                    Log.e("Status code", String.valueOf(networkResponse.statusCode));
                }
            }
        });
        req.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(req);
    }

//    public void getAlbumsImages(AccessToken accessToken, AlbumModel albumModel, final WebConnectionHandler.ApiCallbackResponse albumResponseListener) {
//
//        new GraphRequest(
//                accessToken,
//                "/" + albumModel.getId() + "?fields=photos.limit(100){picture,images}", null,
//                HttpMethod.GET,
//                new GraphRequest.Callback() {
//                    public void onCompleted(GraphResponse response) {
//                        // Intent intent = new Intent(LoginScreen.this,FriendsList.class);
//
//                        Log.e("album_list==", "" + response.toString());
//
//                        try {
//                            albumResponseListener.apiResponse(response.getJSONObject().toString());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            albumResponseListener.apiResponse(null);
//                        }
//
//                    }
//                }
//        ).executeAsync();
//    }
//
//    public void getAlbumsList(AccessToken accessToken, final WebConnectionHandler.ApiCallbackResponse albumResponseListener){
//
//        new GraphRequest(
//                accessToken,
//                "/me/albums?fields=photo_count,picture,name&limit=50", null,
//                HttpMethod.GET,
//                new GraphRequest.Callback() {
//                    public void onCompleted(GraphResponse response) {
//                        // Intent intent = new Intent(LoginScreen.this,FriendsList.class);
//                        Log.e("album_list==", "" + response.toString());
//                        if (response == null || response.getJSONObject() == null) {
//                            albumResponseListener.apiResponse(null);
//                        } else {
//                            albumResponseListener.apiResponse(response.getJSONObject().toString());
//                        }
//                    }
//                }
//        ).executeAsync();
//
//    }
//
//    public void getUserInfo(AccessToken accessToken, final WebConnectionHandler.ApiCallbackResponse facebookUserInfoResponse) {
//
//        GraphRequest data_request1 = GraphRequest.newMeRequest(
//                accessToken,
//                new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(
//                            JSONObject json_object,
//                            GraphResponse response) {
//
//                        /*Intent intent = new Intent(LoginScreen.this,HomeActivity.class);
//                        intent.putExtra("jsondata",json_object.toString());
//                        */
//                        Log.e("jsdon_obj==", "" + json_object.toString());
//
//                        facebookUserInfoResponse.apiResponse(response.getJSONObject().toString());
//                        //startActivity(intent);
//                    }
//                });
//        Bundle permission_param1 = new Bundle();
//        permission_param1.putString("fields", "id,name,gender,about,birthday,email,education,work,location{location{country_code, country, name, city}},hometown{location{country_code, country, name, city}},picture.width(720).height(720)");
//        data_request1.setParameters(permission_param1);
//        data_request1.executeAsync();
//
//    }
//
//    public void getUserLikes(AccessToken accessToken, final WebConnectionHandler.ApiCallbackResponse facebookLikesResponse) {
//
//        new GraphRequest(
//                accessToken,
//                "/me/likes?limit=250", null,
//                HttpMethod.GET,
//                new GraphRequest.Callback() {
//                    public void onCompleted(GraphResponse response) {
//                        // Intent intent = new Intent(LoginScreen.this,FriendsList.class);
//                        try {
//                            JSONArray rawName = response.getJSONObject().getJSONArray("data");
//                            Log.e("likes_list==", "" + response.toString());
//
//                            facebookLikesResponse.apiResponse(response.getJSONObject().toString());
//
//                            //intent.putExtra("jsondata", rawName.toString());
//                            //  startActivity(intent);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//        ).executeAsync();
//
//
//    }

    public void chatMessageNotification(Context activity, String url, String params, final WebConnectionHandler.ApiCallbackResponse apiCallbackResponse){
        Log.e("Class params", params.toString());
        Log.e("Class params", url);

        JsonObjectRequest req = null;
        try {
            req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("Response", response.toString());

                            apiCallbackResponse.apiResponse(response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.e("Error: ", error.getMessage());
                    Log.e("Volly Error", error.toString());
                    apiCallbackResponse.apiResponse(null);

                    NetworkResponse networkResponse = error.networkResponse;

                    if (networkResponse != null) {
                        Log.e("Status code", String.valueOf(networkResponse.statusCode));
                    }
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    headers.put("Authorization","key=AAAAfuMaYFE:APA91bHl1tTd1bbSahEFlzQuXWXzrd6XuqOf2u27k6HJ1PgBhaD9AvFdzebF2JxtRWokWqjJOlFqZ1cDSLUFWocqo4ti6JzyVClYy863zfLhjO4STkV-eQYjKtFTzWQuH1pXjFINXREy");
                    //headers.put("Content-Encoding", "gzip");
                    return headers;
                }

                @Override
                protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                    if (response.statusCode != 200) {

                    }
                    return super.parseNetworkResponse(response);
                }
            };
        } catch (JSONException e) {
            //progressDialog.dismiss();
            //loginResponse.onResponse(null,"Json Error");
            //ApiClient.this.responseHandler("Json Error", baseParser);
            apiCallbackResponse.apiResponse(null);

            e.printStackTrace();
        }

        req.setShouldCache(false);
        int socketTimeout = 30000;//10 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, 0/*DefaultRetryPolicy.DEFAULT_MAX_RETRIES*/, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(req);
    }
}

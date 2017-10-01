package com.traveldiary.webConnection;

import android.app.Activity;
import android.content.Context;

import com.traveldiary.database.databaseInitialization.DatabaseHelper;
import com.traveldiary.database.tables.BaseTable;
import com.traveldiary.database.tables.ServiceDataCache;
import com.traveldiary.utils.CommonMethods;
import com.traveldiary.utils.Constants;
import com.traveldiary.utils.Singleton;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static com.traveldiary.webConnection.WebConnectionModel.JsonRequestType.GET;

/**
 * Created by mohit on 06/07/17.
 */

public class WebConnectionHandler {



    public void fetchAsyncRequestForModel(Context activity, WebConnectionModel webConnectionModel, WebConnectionResponse webConnectionResponse) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url",webConnectionModel.getUrl());
        if (webConnectionModel.getRequestType() != GET) {
            params.put("params", webConnectionModel.getJsonRequest());
        }
        ServiceDataCache cacheData = (ServiceDataCache) fetchDataFromCacheTable(activity, params);

        if (cacheData != null) {

            if (!CommonMethods.isNetworkConnected(activity)) {
                webConnectionResponse.Response(cacheData.response, Constants.ReceivedFrom.CONNECTION_FAILURE_CACHE, webConnectionModel);
                return;
            }

            if (webConnectionModel.isShouldSendImmediateResult()) {
                webConnectionResponse.Response(cacheData.response, Constants.ReceivedFrom.CACHE, webConnectionModel);
                callService(activity, webConnectionModel.isShouldSendServiceResponseAfterImmediateCacheResponse(), cacheData, webConnectionModel, webConnectionResponse);

            } else if (!webConnectionModel.isForceRefresh()) {
                if (System.currentTimeMillis() > CommonMethods.getTimeAfterGivenSeconds(cacheData.timestamp, webConnectionModel.getTimeFactor()) && (cacheData.response == null || cacheData.response.length() < 2)) {
                    callService(activity, true, cacheData, webConnectionModel, webConnectionResponse);

                } else {
                    webConnectionResponse.Response(cacheData.response, Constants.ReceivedFrom.CACHE, webConnectionModel);
                }
            } else {
                callService(activity, true, cacheData, webConnectionModel, webConnectionResponse);
            }
        } else {
            callService(activity, true, cacheData, webConnectionModel, webConnectionResponse);
        }
    }

    public BaseTable fetchDataFromCacheTable(Context activity, HashMap<String, Object> params) {
        DatabaseHelper dbHandler = Singleton.getInstance().initializeHelper(activity);
        try {
            Dao<ServiceDataCache, Integer> cacheDao = dbHandler.getServiceDataCacheDao();
            List<ServiceDataCache> tableResponse = cacheDao.queryForFieldValues(params);
            if (tableResponse != null && tableResponse.size() > 0) {
                return tableResponse.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BaseTable deleteDataFromCacheTable(Activity activity, ServiceDataCache serviceDataCache) {
        DatabaseHelper dbHandler = Singleton.getInstance().initializeHelper(activity);
        try {
            Dao<ServiceDataCache, Integer> cacheDao = dbHandler.getServiceDataCacheDao();
            cacheDao.delete(serviceDataCache);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void callService(Context activity, final Boolean shouldSendResponse, final ServiceDataCache dataCache, final WebConnectionModel webConnectionModel, final WebConnectionResponse webConnectionResponse) {
        switch (webConnectionModel.getRequestType()) {
            case GET: {
                ApiClient apiClient = new ApiClient();
                apiClient.jsonGetRequest(activity, webConnectionModel.getUrl(), new ApiCallbackResponse() {
                    @Override
                    public void apiResponse(String jsonObject) {
                        checkToSaveDataAndRespond(jsonObject, shouldSendResponse, dataCache, webConnectionModel, webConnectionResponse);
                    }
                });
                break;
            }
            case POST: {
                ApiClient apiClient = new ApiClient();
                apiClient.jsonRequest(activity, webConnectionModel.getUrl(), webConnectionModel.getJsonRequest(), new ApiCallbackResponse() {
                    @Override
                    public void apiResponse(String jsonObject) {
                        checkToSaveDataAndRespond(jsonObject, shouldSendResponse, dataCache, webConnectionModel, webConnectionResponse);
                    }
                });
                break;
            }
            case DELETE: {
                ApiClient apiClient = new ApiClient();
                apiClient.jsonDeleteRequest(activity, webConnectionModel.getUrl(), new ApiCallbackResponse() {
                    @Override
                    public void apiResponse(String jsonObject) {
                        checkToSaveDataAndRespond(jsonObject, shouldSendResponse, dataCache, webConnectionModel, webConnectionResponse);
                    }
                });
                break;
            }

            case CHAT_MESSAGES_NOTIFICATION: {
                ApiClient apiClient = new ApiClient();
                apiClient.chatMessageNotification(activity, webConnectionModel.getUrl(), webConnectionModel.getJsonRequest(), new ApiCallbackResponse() {
                    @Override
                    public void apiResponse(String jsonObject) {
                        checkToSaveDataAndRespond(jsonObject, shouldSendResponse, dataCache, webConnectionModel, webConnectionResponse);
                    }
                });
                break;
            }
        }
    }

    private void checkToSaveDataAndRespond(String response, boolean sendResponse, ServiceDataCache dataCache, WebConnectionModel webConnectionModel, WebConnectionResponse webConnectionResponse) {
        DatabaseHelper dbHelper = Singleton.getInstance().getHelper();
        Dao<ServiceDataCache, Integer> cacheDao = null;
        try {
            cacheDao = dbHelper.getServiceDataCacheDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (webConnectionModel.isShouldClearModule() && cacheDao != null) {
            //webConnectionModel.getModule()
            HashMap<String, Object> params = new HashMap<>();
            params.put("module", webConnectionModel.getModule());
            try {
                List<ServiceDataCache> modules = cacheDao.queryForFieldValues(params);
                if (modules != null && modules.size() > 0) {
                    cacheDao.delete(modules);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (webConnectionModel.isShouldCache() && response != null) {
            try {
                if (dataCache != null) {
                    dataCache.response = response;
                } else {
                    dataCache = new ServiceDataCache(String.valueOf(System.currentTimeMillis()), webConnectionModel.getJsonRequest(), webConnectionModel.getUrl(), response,webConnectionModel.getModule());
                }
                cacheDao.createOrUpdate(dataCache);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        if (sendResponse) {
            if (response != null) {
                webConnectionResponse.Response(response, Constants.ReceivedFrom.SERVICE, webConnectionModel);
            } else if(dataCache != null && dataCache.response != null){
                webConnectionResponse.Response(dataCache.response, Constants.ReceivedFrom.SERVICE, webConnectionModel);
            }
        }

    }

    public interface WebConnectionResponse {
        void Response(String jsonObject, Constants.ReceivedFrom receivedFrom, WebConnectionModel webConnectionModel);
    }

    public interface ApiCallbackResponse {
        void apiResponse(String jsonObject);
    }
}

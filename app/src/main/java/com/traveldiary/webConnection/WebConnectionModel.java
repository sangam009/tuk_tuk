package com.traveldiary.webConnection;

/**
 * Created by mohit on 06/07/17.
 */

public class WebConnectionModel {

//    private AccessToken fbAccessToken;
//    private AlbumModel fbAlbumModel;



    public enum JsonRequestType{GET,POST,DELETE,GRAPH_ALBUM, GRAPH_DATA, GRAPH_IMAGES,GRAPH_LIKES,CHAT_MESSAGES_NOTIFICATION}
    private boolean shouldSendImmediateResult = false;
    private boolean shouldCache = true;
    private boolean forceRefresh = false;
    private boolean shouldSendServiceResponseAfterImmediateCacheResponse = false;
    private boolean shouldClearModule = true;


    private int timeFactor;

    private String module = "";
    private String url = "";
    private String jsonRequest = "";
    private JsonRequestType requestType = JsonRequestType.POST;

//    public AccessToken getFbAccessToken() {
//        return fbAccessToken;
//    }
//
//    public WebConnectionModel setFbAccessToken(AccessToken fbAccessToken) {
//        this.fbAccessToken = fbAccessToken;
//        return this;
//    }
//
//    public AlbumModel getFbAlbumModel() {
//        return fbAlbumModel;
//    }
//
//    public WebConnectionModel setFbAlbumModel(AlbumModel fbAlbumModel) {
//        this.fbAlbumModel = fbAlbumModel;
//        return this;
//    }

    public boolean isShouldClearModule() {
        return shouldClearModule;
    }

    public WebConnectionModel setShouldClearModule(boolean shouldClearModule) {
        this.shouldClearModule = shouldClearModule;
        return this;
    }

    public String getModule() {
        return module;
    }

    public WebConnectionModel setModule(String module) {
        this.module = module;
        return this;
    }

    public boolean isShouldSendImmediateResult() {
        return shouldSendImmediateResult;
    }

    public WebConnectionModel setShouldSendImmediateResult(boolean shouldSendImmediateResult) {
        this.shouldSendImmediateResult = shouldSendImmediateResult;
        return this;
    }

    public boolean isShouldCache() {
        return shouldCache;

    }

    public WebConnectionModel setShouldCache(boolean shouldCache) {
        this.shouldCache = shouldCache;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public WebConnectionModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getJsonRequest() {
        return jsonRequest;
    }

    public WebConnectionModel setJsonRequest(String jsonRequest) {
        this.jsonRequest = jsonRequest;
        return this;
    }

    public JsonRequestType getRequestType() {
        return requestType;
    }

    public WebConnectionModel setRequestType(JsonRequestType requestType) {
        this.requestType = requestType;
        return this;
    }

    public boolean isForceRefresh() {
        return forceRefresh;
    }

    public WebConnectionModel setForceRefresh(boolean forceRefresh) {
        this.forceRefresh = forceRefresh;
        return this;
    }

    public boolean isShouldSendServiceResponseAfterImmediateCacheResponse() {
        return shouldSendServiceResponseAfterImmediateCacheResponse;
    }

    public WebConnectionModel setShouldSendServiceResponseAfterImmediateCacheResponse(boolean shouldSendServiceResponseAfterImmediateCacheResponse) {
        this.shouldSendServiceResponseAfterImmediateCacheResponse = shouldSendServiceResponseAfterImmediateCacheResponse;

        return this;
    }

    public int getTimeFactor() {
        return timeFactor;
    }

    public WebConnectionModel setTimeFactor(int timeFactor) {
        this.timeFactor = timeFactor;
        return this;
    }
}

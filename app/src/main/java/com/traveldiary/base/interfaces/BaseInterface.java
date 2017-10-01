package com.traveldiary.base.interfaces;


import com.traveldiary.base.BaseModel;
import com.traveldiary.utils.Constants;

/**
 * Created by mohit on 12/16/2016.
 */

public interface BaseInterface {
    /**
     *  base interface to handle response fromm servers.
     * @param baseModel
     * @param jsonString
     */
    void onResponse(BaseModel baseModel, String jsonString, String reasonOfError, Constants.ReceivedFrom receivedFrom);
}

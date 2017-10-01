package com.traveldiary.home.services;

import android.content.Context;
import android.os.Bundle;

import com.traveldiary.R;
import com.traveldiary.base.BaseService;
import com.traveldiary.base.interfaces.BaseInterface;
import com.traveldiary.home.parsers.ParsePackages;
import com.traveldiary.utils.Constants;
import com.traveldiary.utils.LocalPreferenceManager;
import com.traveldiary.utils.URLs;
import com.traveldiary.webConnection.WebConnectionModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mohit on 30/09/17.
 */

public class HomeService extends BaseService {
    public enum RequestMode {WITHOUT_LOCATION}


    public void callPackages(Context context, RequestMode requestMode, Bundle bundle, PackagesListener packagesListener) {
        try {
            final JSONObject params = new JSONObject();
            final JSONObject source = new JSONObject();
            final JSONObject destination = new JSONObject();

            params.put("userUniqueId", LocalPreferenceManager.getInstance().getUserId());

            if(bundle != null && bundle.getString("sourceLat")!= null) {
                source.put("latitude", bundle.getString("sourceLat"));
                source.put("longitude",bundle.getString("sourceLng"));
            }
            params.put("source",source);

            if(bundle != null && bundle.getString("destinationLat")!= null) {
                destination.put("latitude", bundle.getString("destinationLat"));
                destination.put("longitude",bundle.getString("destinationLng"));
            }
            params.put("destination",destination);

            WebConnectionModel webConnectionModel = new WebConnectionModel();
            webConnectionModel.setForceRefresh(false)

                    .setShouldSendImmediateResult(true)
                    .setShouldSendServiceResponseAfterImmediateCacheResponse(true)
                    .setTimeFactor(Constants.ServiceConfiguration.PACKAGES_TIME_FACTOR)
                    .setRequestType(WebConnectionModel.JsonRequestType.POST)
                    .setJsonRequest(params.toString())
                    .setUrl(URLs.FETCH_PACKAGES);

            packagesListener.onResponse(new ParsePackages().parseJson(new JSONObject(context.getResources().getString(R.string.package_info))), null, null, null);
            //callService(context, webConnectionModel, new ParsePackages(), packagesListener);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public interface PackagesListener extends BaseInterface {
    }
}

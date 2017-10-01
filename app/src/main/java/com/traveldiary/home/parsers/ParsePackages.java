package com.traveldiary.home.parsers;

import com.traveldiary.base.BaseModel;
import com.traveldiary.base.BaseParser;
import com.traveldiary.base.Wrapper;
import com.traveldiary.home.model.PackageOverViewModel;
import com.traveldiary.utils.Constants;
import com.traveldiary.utils.CustomJsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mohit on 30/09/17.
 */

public class ParsePackages extends BaseParser {

    @Override
    public BaseModel parseJson(JSONObject response) throws JSONException {
        return parsePackages(response);
    }

    private BaseModel parsePackages(JSONObject response) {
        try {

            if (response != null) {
                Wrapper<PackageOverViewModel> wrapper = new Wrapper<>();

                CustomJsonObject jsonObject = new CustomJsonObject(response);
                JSONArray packages = jsonObject.getJsonArray("packages");
                for (int index = 0; index < packages.length(); index++) {
                    CustomJsonObject packageJson = new CustomJsonObject(packages.getJSONObject(index));
                    PackageOverViewModel packageOverViewModel = new PackageOverViewModel();
                    packageOverViewModel.setPackageId(packageJson.getString("packageId"));
                    packageOverViewModel.setPackageNamePrimary(packageJson.getString("packageNamePrimary"));
                    packageOverViewModel.setPackageNameSecondary(packageJson.getString("packageNameSecondary"));
                    packageOverViewModel.setEstimatedTravelTime(packageJson.getString("estimatedTravelTime"));
                    packageOverViewModel.setPackageDestinationImage(packageJson.getString("packageDestinationImage"));
                    wrapper.getWrapper().add(packageOverViewModel);
                }
                return wrapper;


            }

        } catch (JSONException e) {
            e.printStackTrace();
            return new BaseModel().setError(Constants.Error.JSON_EXCEPTION);
        }
        return new BaseModel().setError(Constants.Error.NULL_RESPONSE_ERROR);
    }
}

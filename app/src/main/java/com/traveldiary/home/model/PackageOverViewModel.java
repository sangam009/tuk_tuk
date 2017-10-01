package com.traveldiary.home.model;

import com.traveldiary.base.BaseModel;

/**
 * Created by mohit on 30/09/17.
 */

public class PackageOverViewModel extends BaseModel {

    private String packageId = null;
    private String packageNamePrimary = null;
    private String packageNameSecondary = null;
    private String estimatedTravelTime = null;
    private String packageDestinationImage = null;

    public String getPackageId() {
        return packageId;
    }

    public String getPackageNamePrimary() {
        return packageNamePrimary;
    }

    public String getPackageNameSecondary() {
        return packageNameSecondary;
    }

    public String getEstimatedTravelTime() {
        return estimatedTravelTime;
    }

    public String getPackageDestinationImage() {
        return packageDestinationImage;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public void setPackageNamePrimary(String packageNamePrimary) {
        this.packageNamePrimary = packageNamePrimary;
    }

    public void setPackageNameSecondary(String packageNameSecondary) {
        this.packageNameSecondary = packageNameSecondary;
    }

    public void setEstimatedTravelTime(String estimatedTravelTime) {
        this.estimatedTravelTime = estimatedTravelTime;
    }

    public void setPackageDestinationImage(String packageDestinationImage) {
        this.packageDestinationImage = packageDestinationImage;
    }
}

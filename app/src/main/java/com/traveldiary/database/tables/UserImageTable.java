package com.traveldiary.database.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by mohit on 27/06/17.
 */
@DatabaseTable(tableName = "userimagetable")
public class UserImageTable extends BaseTable implements Serializable {

    /**
     *  Model class for ImageTemplate database table
     */
    private static final long serialVersionUID = -222864131214757024L; 

    // Primary key defined as an auto generated integer
    // If the database table column name differs than the Model class variable name, the way to map to use columnName

    @DatabaseField(generatedId = true,columnName = "id")
    public int id;
    

    @DatabaseField(columnName = "image_url")
    public String imageUrl;
    
    
    @DatabaseField(columnName = "base_64_image")
    public String base64Image;
    
    
    @DatabaseField(columnName = "content_type")
    public String contentType;
    
    
    @DatabaseField(columnName = "image_base_url")
    public String imageBaseUrl;
    
    
    @DatabaseField(columnName = "image_uri")
    public String imageUri;
    
    
    @DatabaseField(columnName = "image_source")
    public String imageSource;
    
    
    @DatabaseField(columnName = "order")
    public String order;
    
    
    @DatabaseField(columnName = "timestamp")
    public String timestamp;

    @DatabaseField(columnName = "is_synced")
    public boolean isSynced;
    

    public UserImageTable(){
        
    }

    public UserImageTable setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl; 
        return this;
    }

    public UserImageTable setBase64Image(String base64Image) {
        this.base64Image = base64Image; 
        return this;
    }

    public UserImageTable setContentType(String contentType) {
        this.contentType = contentType; 
        return this;
    }

    public UserImageTable setImageBaseUrl(String imageBaseUrl) {
        this.imageBaseUrl = imageBaseUrl; 
        return this;
    }

    public UserImageTable setImageUri(String imageUri) {
        this.imageUri = imageUri; 
        return this;
    }

    public UserImageTable setImageSource(String imageSource) {
        this.imageSource = imageSource; 
        return this;
    }
    

    public UserImageTable setOrder(String order) {
        this.order = order; 
        return this;
    }

    public UserImageTable setTimeStamp(String timeStamp) {
        this.timestamp = timeStamp;
        return this;
    }

    public UserImageTable setSynced(boolean synced) {
        isSynced = synced;
        return this;
    }

    public String getImageUrl(){
        if(imageUrl != null && !imageUrl.contains("http")){
            return imageBaseUrl + "/" + imageUrl;
        }else{
            return imageUrl;
        }
    }
    public String getGenuineUrl(){
        return imageUrl;
    }
}

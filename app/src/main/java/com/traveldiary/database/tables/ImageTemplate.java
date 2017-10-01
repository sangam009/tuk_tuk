package com.traveldiary.database.tables;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by mohit on 3/23/2017.
 */

@DatabaseTable(tableName = "image_templates")
public class ImageTemplate implements Serializable {

    /**
     *  Model class for ImageTemplate database table
     */
    private static final long serialVersionUID = -222864131214757024L;

    // Primary key defined as an auto generated integer
    // If the database table column name differs than the Model class variable name, the way to map to use columnName
    /*@DatabaseField(generatedId = true, columnName = "_id")
    public int id;
*/
    // Define a String type field to hold ImageTemplate id
    @DatabaseField(generatedId = true,columnName = "id")
    public int id;


    // Define a String type field to hold ImageTemplate senderTitle
    @DatabaseField(columnName = "image_base_64")
    public String imageBase64;

    @DatabaseField(columnName = "fetched_from")
    public String fetchedFrom;

    @DatabaseField(columnName = "uri")
    public String uri;

    @DatabaseField(columnName = "timestamp")
    public String timestamp;

    // Default constructor is needed for the SQLite, so make sure you also have it
    public ImageTemplate(){

    }

    //For our own purpose, so it's easier to create a ImageTemplate object
    public ImageTemplate(final String timeStamp, final String imageBase64,final String uri, final String fetchedFrom){
        this.timestamp = timeStamp;
        this.imageBase64 = imageBase64;
        this.fetchedFrom = fetchedFrom;
        this.uri = uri;
    }


}

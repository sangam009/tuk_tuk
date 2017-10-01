package com.traveldiary.database.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by mohit on 03/07/17.
 */
@DatabaseTable(tableName = "profilebyid")
public class ServiceDataCache extends BaseTable implements Serializable{


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
    @DatabaseField(columnName = "params")
    public String params;

    @DatabaseField(columnName = "url")
    public String url;

    @DatabaseField(columnName = "response")
    public String response;


    @DatabaseField(columnName = "timestamp")
    public String timestamp;

    @DatabaseField(columnName = "module")
    public String module;


    // Default constructor is needed for the SQLite, so make sure you also have it
    public ServiceDataCache(){

    }

    //For our own purpose, so it's easier to create a ImageTemplate object
    public ServiceDataCache(final String timeStamp, final String params, final String url, final String module){
        this.timestamp = timeStamp;
        this.url = url;
        this.params = params;
        this.module = module;
    }

    public ServiceDataCache(final String timeStamp, final String params, final String url, String response,String module){
        this.timestamp = timeStamp;
        this.url = url;
        this.params = params;
        this.response = response;
        this.module = module;
    }


}

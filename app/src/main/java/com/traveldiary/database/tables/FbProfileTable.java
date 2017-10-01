package com.traveldiary.database.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by mohit on 21/07/17.
 */
@DatabaseTable(tableName = "fbprofiletable")
public class FbProfileTable extends BaseTable implements Serializable {

    /**
     * Model class for ImageTemplate database table
     */
    private static final long serialVersionUID = -222864131214757024L;

    // Primary key defined as an auto generated integer
    // If the database table column name differs than the Model class variable name, the way to map to use columnName

    @DatabaseField(generatedId = true, columnName = "id")
    public int id;


    @DatabaseField(columnName = "profile")
    public String profile;



    @DatabaseField(columnName = "timestamp")
    public long timestamp;


    public FbProfileTable() {

    }

    public String getProfile() {
        return profile;
    }

    public FbProfileTable setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public FbProfileTable setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}

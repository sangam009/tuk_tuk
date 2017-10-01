package com.traveldiary.database.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by mohit on 22/08/17.
 */
@DatabaseTable(tableName = "notificationsubscriptiontable")
public class NotificationSubscriptionTable extends BaseTable implements Serializable{

    /**
     *  Model class for ImageTemplate database table
     */
    private static final long serialVersionUID = -222864131214757024L;

    // Primary key defined as an auto generated integer
    // If the database table column name differs than the Model class variable name, the way to map to use columnName

    @DatabaseField(unique= true,uniqueIndex = true,id = true,columnName = "id")
    public String id;


    @DatabaseField(columnName = "subscribed")
    private boolean subscribed;


    @DatabaseField(columnName = "timestamp")
    private long timestamp;

    public NotificationSubscriptionTable(){

    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public NotificationSubscriptionTable setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public NotificationSubscriptionTable setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getId() {
        return id;
    }

    public NotificationSubscriptionTable setId(String id) {
        this.id = id;
        return this;
    }
}

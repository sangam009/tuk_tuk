package com.traveldiary.database.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by mohit on 21/07/17.
 */
@DatabaseTable(tableName = "profiletable")
public class ProfileTable extends BaseTable implements Serializable {

    /**
     * Model class for ImageTemplate database table
     */
    private static final long serialVersionUID = -222864131214757024L;

    public enum Type {Hot, SuperHot, Not, NOT_SEEN}
    // Primary key defined as an auto generated integer
    // If the database table column name differs than the Model class variable name, the way to map to use columnName

    @DatabaseField(columnName = "id",unique= true,uniqueIndex = true,id = true)
    public String id;


    @DatabaseField(columnName = "profile" )
    public String profile;


    @DatabaseField(columnName = "type", indexName = "fetch_profile_index")
    public String type;


    @DatabaseField(columnName = "is_synced")
    public boolean isSynced;


    @DatabaseField(columnName = "filter" ,indexName = "fetch_profile_index")
    public String filter;

    /*@DatabaseField(columnName = "is_matched" )
    public boolean isMatched;
*/

    @DatabaseField(columnName = "timestamp")
    public long timestamp;

    @DatabaseField(columnName = "profile_taken", indexName = "fetch_profile_index")
    public boolean profileTaken;

    public ProfileTable() {

    }



    public String getProfile() {
        return profile;
    }

    public ProfileTable setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public String getType() {
        return type;
    }

    public ProfileTable setType(String type) {
        this.type = type;
        return this;
    }

    public boolean isSynced() {
        return isSynced;
    }

    public ProfileTable setSynced(boolean synced) {
        isSynced = synced;
        return this;
    }

    public String getFilter() {
        return filter;
    }

    public ProfileTable setFilter(String filter) {
        this.filter = filter;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public ProfileTable setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public boolean isProfileTaken() {
        return profileTaken;
    }

    public ProfileTable setProfileTaken(String  profileTaken) {
        this.profileTaken = Boolean.parseBoolean(profileTaken);
        return this;
    }

    public String getId() {
        return id;
    }

    public ProfileTable setId(String id) {
        this.id = id;
        return this;
    }

    /* public boolean isMatched() {
        return isMatched;
    }

    public ProfileTable setMatched(boolean isMatched) {
        this.isMatched = isMatched;
        return this;
    }*/
}

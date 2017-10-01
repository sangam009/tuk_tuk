package com.traveldiary.database.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by mohit on 21/07/17.
 */
@DatabaseTable(tableName = "filtertable")
public class FilterTable extends BaseTable implements Serializable {

    /**
     * Model class for ImageTemplate database table
     */
    private static final long serialVersionUID = -222864131214757024L;

     // Primary key defined as an auto generated integer
    // If the database table column name differs than the Model class variable name, the way to map to use columnName

    @DatabaseField(generatedId = true, columnName = "id")
    public int id;


    @DatabaseField(columnName = "filter")
    public String filter;

    @DatabaseField(columnName = "filter_key")
    public String filterKey;


    @DatabaseField(columnName = "timestamp")
    public long timestamp;


    public FilterTable() {

    }

    public String getFilter() {
        return filter;
    }

    public FilterTable setFilter(String filter) {
        this.filter = filter;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public FilterTable setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getFilterKey() {
        return filterKey;
    }

    public FilterTable setFilterKey(String filterKey) {
        this.filterKey = filterKey;
        return this;
    }
}

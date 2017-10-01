package com.traveldiary.database.tables;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by HP on 21-Aug-17.
 */

public class SuperLikedTable extends BaseTable implements Serializable {
    @DatabaseField(columnName = "id",unique = true,uniqueIndex = true,id=true)
    public String id;

    @DatabaseField(columnName = "img_url")
    public String imgUrl;

    @DatabaseField(columnName = "name")
    public String name;

    @DatabaseField(columnName = "age")
    public String age;

    @DatabaseField(columnName = "country")
    public String country;

    @DatabaseField(columnName = "gender")
    public String gender;

    @DatabaseField(columnName = "is_match")
    public boolean isMatch;

    @DatabaseField(columnName = "timestamp")
    public long timestamp;

    public SuperLikedTable(){

    }
    public SuperLikedTable(String id, String imgUrl, String name, String age, String country,String gender,boolean isMatch,long timestamp) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.name = name;
        this.age = age;
        this.country = country;
        this.gender=gender;
        this.isMatch=isMatch;
        this.timestamp = timestamp;
    }
}

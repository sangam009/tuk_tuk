package com.traveldiary.base;

import com.traveldiary.base.BaseModel;

import java.util.ArrayList;

/**
 * Created by mohit on 30/09/17.
 */

public class Wrapper<T extends BaseModel> extends BaseModel {

    private ArrayList<T> wrapper = new ArrayList<>();

    public ArrayList<T> getWrapper() {
        if(wrapper == null){
            wrapper = new ArrayList<>();
        }
        return wrapper;
    }
}

package com.traveldiary.base;

import java.io.Serializable;

/**
 * Created by codeplay on 28/3/17.
 */

public class BaseModel implements Serializable{

    public String error = null;

    public BaseModel setError(String error) {
        this.error = error;
        return this;
    }
}

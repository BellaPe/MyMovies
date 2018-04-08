package com.example.android.sratim;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Android on 21/03/2018.
 */

class BasicMovieInfo {

    private String name;

    public BasicMovieInfo(){}

    public BasicMovieInfo(JSONObject jsonObj) throws JSONException{
        setName(jsonObj.getString("name"));
    }

    public BasicMovieInfo(String name){
        setName(name);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}

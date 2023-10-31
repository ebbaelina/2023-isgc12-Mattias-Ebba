package com.example.foodstore;

import org.json.JSONObject;

import java.util.List;

public interface VolleyCallback <T>{
    public void onSuccess(JSONObject object);
    public void onFailure(Exception e);
}

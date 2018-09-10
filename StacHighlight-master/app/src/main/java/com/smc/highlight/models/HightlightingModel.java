package com.smc.highlight.models;

import java.util.HashMap;
import java.util.Map;

public class HightlightingModel {
    String username = "";
    String comments = "";
    float x = 0;
    float y = 0;

    public HightlightingModel(){}

    public HightlightingModel(String username, String comments, float x, float y){
        this.username = username;
        this.comments = comments;
        this.x = x;
        this.y = y;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Username", username);
        result.put("Comments", comments);
        result.put("x", x);
        result.put("y", y);
        return result;
    }



}

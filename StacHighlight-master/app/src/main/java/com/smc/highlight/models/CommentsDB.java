package com.smc.highlight.models;

import java.util.HashMap;
import java.util.Map;

public class CommentsDB {
    public String Comment = "";

    public CommentsDB(){}

    public CommentsDB(String Comments){
        this.Comment = Comments;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Username", "UnKnown");
        result.put("Comments", Comment);
        return result;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

}

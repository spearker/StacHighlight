package com.smc.highlight.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostModel {
    String username = "";
    String userIamge = "";
    String postImage = "";
    String highlighting = "";
    String desc = "";
    Date date = null;
    List<CommentsDB> comments = new ArrayList<>();

    public PostModel(String username, String userImage, String postImage, String desc, int highlighting, Date date){
        this.username = username;
        this.userIamge = userImage;
        this.postImage = postImage;
        this.desc = desc;
        this.highlighting = highlighting + "개";
        this.date = date;
    }

    public PostModel(){}

    //get method

    public String getUsername() { return username; }
    public String getPostImage() {
        return this.postImage;
    }
    public String getUserIamge() {
        return userIamge;
    }
    public String getDesc(){ return desc; }
    public String getHighlighting(){ return highlighting; }
    public Date getDate() { return date; }

    //set method

    public void setUsername(String username){ this.username = username; }
    public void setUserIamge(String userImage){ this.userIamge = userImage; }
    public void setPostImage(String postImage){ this.postImage = postImage; }
    public void setDesc(String desc){ this.desc = desc; }
    public void setHighlighting(int highlighting){ this.highlighting = highlighting + "개"; }
    public void setDate(Date date){ this.date = date; }

}

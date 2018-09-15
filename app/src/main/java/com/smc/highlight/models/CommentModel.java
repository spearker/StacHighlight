package com.smc.highlight.models;

import java.util.HashMap;
import java.util.Map;

public class CommentModel {
    String username = "";
    String userImage = "";
    String comment = "";
    String commentColor = "";
    double startX = 0d, startY = 0d, lastX = 0d, lastY = 0d;

    public CommentModel(String username, String userImage, String comment, String commentColor, double startX, double startY, double lastX, double lastY) {
        this.username = username;
        this.userImage = userImage;
        this.comment = comment;
        this.commentColor = commentColor;
        this.startX = startX;
        this.startY = startY;
        this.lastX = lastX;
        this.lastY = lastY;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentColor() {
        return commentColor;
    }

    public void setCommentColor(String commentColor) {
        this.commentColor = commentColor;
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getLastX() {
        return lastX;
    }

    public void setLastX(double lastX) {
        this.lastX = lastX;
    }

    public double getLastY() {
        return lastY;
    }

    public void setLastY(double lastY) {
        this.lastY = lastY;
    }
}

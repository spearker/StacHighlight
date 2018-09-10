package com.smc.highlight.models;

import java.util.ArrayList;
import java.util.List;

public class Item {
    String writer = "";
    String detail = "";
    String contents = "";
    String highlight = "";
    List<CommentsDB> comments = new ArrayList<>();


    public String getWriter() {
        return this.writer;
    }

    public String getDetail() {
        return this.detail;
    }

    public String getContents() {
        return this.contents;
    }

    public String getHighlight() {
        return this.highlight;
    }

    public Item(String writer, String title, String contents, int highlight) {
        this.writer = writer;
        this.detail = title;
        this.contents = contents;
        this.highlight = "Highlight " + highlight + "개";
    }
}


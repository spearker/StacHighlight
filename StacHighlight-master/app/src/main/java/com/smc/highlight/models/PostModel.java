package com.smc.highlight.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostModel {
    String Name = "";
    String Image = "";
    Date date = null;
    List<CommentsDB> comments = new ArrayList<>();
}

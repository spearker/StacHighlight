package com.smc.highlight.Fragment.Post;

import android.annotation.SuppressLint;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.smc.highlight.Fragment.Fragment_Home;
import com.smc.highlight.R;
import com.smc.highlight.models.CommentsDB;
import com.smc.highlight.models.PostModel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity {
    EditText text_Comments;
    Button button_comments;

    private ListView comments_list;
    private View header;
    private ImageView postImage;
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Post");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        init();
    }



    void init(){
        postImage = (ImageView) findViewById(R.id.post_postimage);
        comments_list = (ListView) findViewById(R.id.comments_list);
        text_Comments = (EditText) findViewById(R.id.main_comments);
        button_comments = (Button) findViewById(R.id.comment_submit);

        header = getLayoutInflater().inflate(R.layout.header, null, false);
        comments_list.addHeaderView(header);


    }

}

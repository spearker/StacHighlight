package com.smc.highlight.Fragment.Post;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smc.highlight.Fragment.Recycler.RecyclerAdapter;
import com.smc.highlight.R;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostActivity extends AppCompatActivity {
    EditText text_Comments;
    Button button_comments;

    //각각의 개채 선언
    private RecyclerView comments_list;
    private View header;
    private ImageView postImage;

    Context context;

    //database 선언
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Post");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        init();
    }



    void init(){
        postImage = (ImageView) findViewById(R.id.post_postimage);
        comments_list = (RecyclerView) findViewById(R.id.comments_list);
        text_Comments = (EditText) findViewById(R.id.main_comments);
        button_comments = (Button) findViewById(R.id.comment_submit);

        RecyclerAdapter ra = new RecyclerAdapter();

        DatabaseReference postRef = myRef.child(ra.getPostID());

        postRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String dPostContents = dataSnapshot.child("desc").getValue(String.class);
                String dPostImage = dataSnapshot.child("postImage").getValue(String.class);

                TextView pc = (TextView)findViewById(R.id.post_contents);

                ArrayList<int[]> hashtagSpans = getSpans(dPostContents, '#');

                SpannableString commentsContent =
                        new SpannableString(dPostContents);

                for(int i = 0; i < hashtagSpans.size(); i++) {
                    int[] span = hashtagSpans.get(i);
                    int hashTagStart = span[0];
                    int hashTagEnd = span[1];

                    commentsContent.setSpan(new HashTag(context), hashTagStart, hashTagEnd, 0);

                }

                pc.setMovementMethod(LinkMovementMethod.getInstance());
                pc.setText(commentsContent);

                Glide.with(PostActivity.this)
                        .load(dPostImage)
                        .into(postImage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    public ArrayList<int[]> getSpans(String body, char prefix) {
        ArrayList<int[]> spans = new ArrayList<int[]>();
        Pattern pattern = Pattern.compile(prefix + "\\w+");
        Matcher matcher = pattern.matcher(body);
        // Check all occurrences
        while(matcher.find()) {
            int[] currentSpan = new int[2];
            currentSpan[0] = matcher.start();
            currentSpan[1] = matcher.end();
            spans.add(currentSpan);
        }
        return spans;
    }

}

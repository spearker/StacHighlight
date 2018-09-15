package com.smc.highlight.Fragment.Highlighting;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smc.highlight.Fragment.Recycler.RecyclerAdapter;
import com.smc.highlight.R;

import java.util.HashMap;
import java.util.Map;

public class HighlightingActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    TextView errorText;
    ImageView highlighting;
    CustomView customView;
    RadioButton colorSelected1, colorSelected2, colorSelected3, colorSelected4, colorSelected5;

    RecyclerAdapter ra = new RecyclerAdapter();

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Post");

    String username = "우리집";
    String comments = "안녕하세요";
    double x = 100.23f;
    double y = 100.23f;

    public static int count = 0;

    public static int color; // color변수를 static으로 해주어야 숫자가 바뀔 때 인식을 한다.

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highlighting);

        button = (Button)findViewById(R.id.highlighting_sendbutton);
        button.setOnClickListener(this);

        errorText = (TextView)findViewById(R.id.highlighting_comment);
        highlighting = (ImageView)findViewById(R.id.highlighting_image);


        DatabaseReference postRef = myRef.child(ra.getPostID());
        //DatabaseReference commentRef = myRef.child();

        postRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String highlightImage = dataSnapshot.child("postImage").getValue(String.class);

                Glide.with(HighlightingActivity.this)
                        .load(highlightImage)
                        .into(highlighting);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        //colorSelected1 = (RadioButton)findViewById(R.id.colorButton1);
        //colorSelected1.setOnClickListener(this);
//
        //colorSelected2 = (RadioButton)findViewById(R.id.colorButton2);
        //colorSelected2.setOnClickListener(this);
//
        //colorSelected3 = (RadioButton)findViewById(R.id.colorButton3);
        //colorSelected3.setOnClickListener(this);
//
        //colorSelected4 = (RadioButton)findViewById(R.id.colorButton4);
        //colorSelected4.setOnClickListener(this);
//
        //colorSelected5 = (RadioButton)findViewById(R.id.colorButton5);
        //colorSelected5.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.highlighting_sendbutton:

                finish();
                break;
            //case R.id.colorButton1: // 여기부터는 색 버튼을 눌렀을 시 CustomView 로 넘어가는 수를 지정해주는 switch문이다.
            //    color = 1;
            //    break;
            //case R.id.colorButton2:
            //    color = 2;
            //    break;
            //case R.id.colorButton3:
            //    color = 3;
            //    break;
            //case R.id.colorButton4:
            //    color = 4;
            //    break;
            //case R.id.colorButton5:
            //    color = 5;
            //    break;
        }
    }
}

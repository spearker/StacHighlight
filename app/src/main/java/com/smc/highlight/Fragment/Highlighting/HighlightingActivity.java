package com.smc.highlight.Fragment.Highlighting;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smc.highlight.R;
import com.smc.highlight.models.HightlightingModel;

import java.util.HashMap;
import java.util.Map;

public class HighlightingActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    TextView errorText;
    RadioButton colorSelected1;
    RadioButton colorSelected2;
    RadioButton colorSelected3;
    RadioButton colorSelected4;
    RadioButton colorSelected5;

    String username = "우리집";
    String comments = "안녕하세요";
    float x = 100.23f;
    float y = 100.23f;

    public static int count = 0;

    public static int color; // color변수를 static으로 해주어야 숫자가 바뀔 때 인식을 한다.

    //DatabaseReference dataref = FirebaseDatabase.getInstance().getReference("posts");
    //DatabaseReference highlightingRef = dataref.child("0").child("highlighting");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highlighting);

        button = (Button)findViewById(R.id.highlighting_sendbutton);
        button.setOnClickListener(this);

        errorText = (TextView)findViewById(R.id.highlighting_text);

        colorSelected1 = (RadioButton)findViewById(R.id.colorButton1);
        colorSelected1.setOnClickListener(this);

        colorSelected2 = (RadioButton)findViewById(R.id.colorButton2);
        colorSelected2.setOnClickListener(this);

        colorSelected3 = (RadioButton)findViewById(R.id.colorButton3);
        colorSelected3.setOnClickListener(this);

        colorSelected4 = (RadioButton)findViewById(R.id.colorButton4);
        colorSelected4.setOnClickListener(this);

        colorSelected5 = (RadioButton)findViewById(R.id.colorButton5);
        colorSelected5.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.highlighting_sendbutton:
                //String postNum = highlightingRef.push().getKey();
                //HightlightingModel hm = new HightlightingModel(username, comments, x, y);
                //Map<String, Object> highlightValue = hm.toMap();
//
                //Map<String, Object> childUpdate = new HashMap<>();
                //childUpdate.put("/"+postNum, highlightValue);
                finish();
                break;
            case R.id.colorButton1: // 여기부터는 색 버튼을 눌렀을 시 CustomView 로 넘어가는 수를 지정해주는 switch문이다.
                color = 1;
                break;
            case R.id.colorButton2:
                color = 2;
                break;
            case R.id.colorButton3:
                color = 3;
                break;
            case R.id.colorButton4:
                color = 4;
                break;
            case R.id.colorButton5:
                color = 5;
                break;
        }
    }
}

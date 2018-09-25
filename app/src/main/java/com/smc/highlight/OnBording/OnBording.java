package com.smc.highlight.OnBording;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.smc.highlight.Login.AdditionalActivity;
import com.smc.highlight.MainActivity;
import com.smc.highlight.R;

public class OnBording extends AppCompatActivity {

    private static final String MY_DB = "my_db";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_one);

        SharedPreferences sp = getSharedPreferences(MY_DB, Context.MODE_PRIVATE);

        boolean hasVisited = sp.getBoolean("hasVisited", false);

        if(hasVisited){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Button button = (Button)findViewById(R.id.button2);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(OnBording.this, OnBording2.class);
                    startActivity(intent);
                    finish();
                }
            });

            Button button1 = (Button)findViewById(R.id.button3);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(OnBording.this, AdditionalActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        }

    }
}

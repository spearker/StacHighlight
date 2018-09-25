package com.smc.highlight.OnBording;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.smc.highlight.Login.AdditionalActivity;
import com.smc.highlight.R;

public class OnBording2 extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_two);

        Button button = (Button)findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBording2.this, OnBording3.class);
                startActivity(intent);
                finish();
            }
        });
        Button button1 = (Button)findViewById(R.id.button6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBording2.this, AdditionalActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

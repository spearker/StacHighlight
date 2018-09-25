package com.smc.highlight.Login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smc.highlight.MainActivity;
import com.smc.highlight.R;
import com.smc.highlight.models.UserModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AdditionalActivity extends AppCompatActivity{
    ImageView addUserImage;
    Button addUserImageButton, addBirthDay, addInformation;
    CheckBox addGender;
    TextView addBirthdayText;
    Spinner addUserBelong, addUserClass, addInterest;
    EditText addUsername;
    RadioButton userImage1, userImage2, userImage3, userImage4, userImage5;
    RelativeLayout userImagepick;

    ArrayAdapter spinnerAdapter, userClassAdapter, userIntersetAdepter;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    String birthDay, userBelong, userInterest, str1, userImage;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = firebaseDatabase.getReference("User");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional);

        userImagepick = (RelativeLayout)findViewById(R.id.userimage_pick);

        addUserImage = (ImageView) findViewById(R.id.add_userimage);

        addUsername = (EditText) findViewById(R.id.add_username);

        addGender = (CheckBox)findViewById(R.id.add_gender);

        addUserBelong = (Spinner)findViewById(R.id.add_userbelong);
        //addUserClass = (Spinner)findViewById(R.id.add_userclass);
        addInterest = (Spinner)findViewById(R.id.add_userinterest);

        addBirthdayText = (TextView)findViewById(R.id.add_birthday_text);

        addUserImageButton = (Button)findViewById(R.id.add_userimage_button);
        addBirthDay = (Button) findViewById(R.id.add_birthday);
        addInformation = (Button)findViewById(R.id.add_information);

        final String[] list1 = new String[]{"중학교 1학년", "중학교 2학년", "중학교 3학년", "고등학교 1학년", "고등학교 2학년", "고등학교 3학년", "재수생", "공시생", "홈스쿨링", "선생님"};
        spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list1);
        addUserBelong.setAdapter(spinnerAdapter);

        String[] list3 = new String[]{"국어", "수학", "사회", "과학", "영어"};
        userIntersetAdepter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list3);
        addInterest.setAdapter(userIntersetAdepter);

        addUserBelong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userBelong = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        addUserImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userImagepick.setVisibility(View.VISIBLE);
            }
        });

        addInterest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userInterest = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        userImage1 = (RadioButton)findViewById(R.id.userimage1);
        userImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userImage = "userImage1.png";
                userImagepick.setVisibility(View.GONE);
            }
        });

        userImage2 = (RadioButton)findViewById(R.id.userimage2);
        userImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userImage = "userImage2.png";
                userImagepick.setVisibility(View.GONE);
            }
        });

        userImage3 = (RadioButton)findViewById(R.id.userimage3);
        userImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userImage = "userImage3.png";
                userImagepick.setVisibility(View.GONE);
            }
        });

        userImage4 = (RadioButton)findViewById(R.id.userimage4);
        userImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userImage = "userImage4.png";
                userImagepick.setVisibility(View.GONE);
            }
        });

        userImage5 = (RadioButton)findViewById(R.id.userimage5);
        userImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userImage = "userImage5.png";
                userImagepick.setVisibility(View.GONE);
            }
        });


        addBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(AdditionalActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
                        try {
                            birthDay = year+"년 "+(monthOfYear+1)+"월 "+dayOfMonth+"일";
                            addBirthdayText.setText(birthDay);
                        } catch (Exception e) {
                            // TODO: handle exception
                            e.printStackTrace();
                        }
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                datePickerDialog.show();
            }
        });
        addInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String username = addUsername.getText().toString();
                String userEmail = firebaseUser.getEmail();
                String userGender;
                if(addGender.isChecked()){
                    userGender = "여";
                }else{
                    userGender = "남";
                }
                UserModel um = new UserModel(username, userEmail,userImage, userGender, birthDay, userBelong, userInterest);
                myRef.child(firebaseUser.getUid()).setValue(um);
                Intent intent = new Intent(AdditionalActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

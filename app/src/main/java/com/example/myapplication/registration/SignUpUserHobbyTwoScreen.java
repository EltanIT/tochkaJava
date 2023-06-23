package com.example.myapplication.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.homescreen.HomePageScreen;

public class SignUpUserHobbyTwoScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user_hobby_two_screen);
    }

    public void btn_continue(View view) {
        startActivity(new Intent(SignUpUserHobbyTwoScreen.this, HomePageScreen.class));
        finish();
    }

    public void back_to_login_screen(View view) {
        startActivity(new Intent(SignUpUserHobbyTwoScreen.this, SignUpHobbyScreen.class));
        finish();
    }
}
package com.example.myapplication.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class SignUpScreen extends AppCompatActivity {

    private Button button_user;
    private Button button_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        button_user = findViewById(R.id.button_user);
        button_admin = findViewById(R.id.button_admin);

        button_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpScreen.this, SignUpHobbyScreen.class);
                intent.putExtra("codeRole","user");
                startActivity(intent);
                finish();
            }
        });
        button_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpScreen.this, SignUpHobbyScreen.class);
                intent.putExtra("codeRole","admin");
                startActivity(intent);
                finish();
            }
        });
    }

    public void back_to_login_screen(View view) {
        Intent intent = new Intent(SignUpScreen.this, MainActivity.class);
        startActivity(intent);
    }
}
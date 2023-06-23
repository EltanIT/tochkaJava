package com.example.myapplication.registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.homescreen.HomePageScreen;

public class SignUpAdminConfirmScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_admin_confirm_screen);
    }

    public void btn_continue(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View complete_registration_view = inflater.inflate(R.layout.complete_registration,null);

        final Button ok_btn = complete_registration_view.findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpAdminConfirmScreen.this, HomePageScreen.class));
//                finish();
            }
        });
        dialog.setView(complete_registration_view);
        dialog.show();


    }

    public void back_to_signup_screen(View view) {
        startActivity(new Intent(SignUpAdminConfirmScreen.this, SignUpHobbyScreen.class));
        finish();
    }
}
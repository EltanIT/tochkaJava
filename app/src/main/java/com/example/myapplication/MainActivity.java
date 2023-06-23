package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.homescreen.HomePageScreen;
import com.example.myapplication.registration.SignUpScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    private Button button_login, button_registration, button_login_poput;
    private LinearLayout poput_window_login_linearlayout;
    private ConstraintLayout main_layout;
    private EditText email_et,pass_et;
    private FirebaseAuth login;
    private FirebaseDatabase db;
    private DatabaseReference user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_login = findViewById(R.id.button_login);
        poput_window_login_linearlayout = findViewById(R.id.poput_window_login_linearlayout);
        LinearLayout click_close_poput = findViewById(R.id.click_close_poput);
        button_login_poput = findViewById(R.id.button_login_realy);
        button_registration = findViewById(R.id.button_registration);
        email_et = findViewById(R.id.email_et);
        pass_et = findViewById(R.id.pass_et);
        main_layout = findViewById(R.id.main_layout);

        login = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = db.getReference("User");

        FirebaseUser firebaseUser = login.getCurrentUser();
        if (firebaseUser != null){
            Intent intent = new Intent(MainActivity.this, HomePageScreen.class);
            startActivity(intent);
        }

        button_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpScreen.class);
                startActivity(intent);
                finish();
            }
        });

        click_close_poput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TranslateAnimation ctrlAnimation = new TranslateAnimation(
                        TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 1);
                ctrlAnimation.setDuration (200l); // Установить время перехода анимации
                poput_window_login_linearlayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (poput_window_login_linearlayout.getVisibility() == View.VISIBLE){
                            poput_window_login_linearlayout.startAnimation(ctrlAnimation);
                            poput_window_login_linearlayout.setVisibility(View.INVISIBLE);
                        }
                    }
                }, 200);
            }
        });


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TranslateAnimation ctrlAnimation = new TranslateAnimation(
                        TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, 1, TranslateAnimation.RELATIVE_TO_SELF, 0);
                ctrlAnimation.setDuration (300l); // Установить время перехода анимации
                poput_window_login_linearlayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (poput_window_login_linearlayout.getVisibility() == View.VISIBLE)
                            poput_window_login_linearlayout.setVisibility(View.INVISIBLE);
                        else{
                            poput_window_login_linearlayout.setVisibility(View.VISIBLE);
                            poput_window_login_linearlayout.startAnimation(ctrlAnimation);
                        }
                    }
                }, 200);

            }
        });
        button_login_poput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        if (TextUtils.isEmpty(pass_et.getText().toString())){
            Snackbar.make(main_layout,"Введите пароль", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(email_et.getText().toString())){
            Snackbar.make(main_layout,"Введите логин", Snackbar.LENGTH_SHORT).show();
            return;
        }
        login.signInWithEmailAndPassword(email_et.getText().toString(),pass_et.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent intent = new Intent(MainActivity.this, HomePageScreen.class);
                startActivity(intent);
                finish();

            }
        }).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete())
                            Toast.makeText(MainActivity.this,"Вы успешно авторизовались",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(main_layout,"ошибка авторизации: " + e.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    public void onHomePage(View view) {
        Intent intent = new Intent(MainActivity.this, HomePageScreen.class);
        startActivity(intent);
    }
}
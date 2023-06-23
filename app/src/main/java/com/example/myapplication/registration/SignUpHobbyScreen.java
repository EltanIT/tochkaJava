package com.example.myapplication.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.example.myapplication.R;
import com.example.myapplication.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Objects;

public class SignUpHobbyScreen extends AppCompatActivity {

    private String role = "user";
    private EditText name_edit_text, name_last_edit_text,email_edit_text,password_edit_text,password_again_edit_text;
    private Button btn_continue;
    private ScrollView root;

    FirebaseAuth signup;
    FirebaseDatabase db;
    DatabaseReference user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_hobby_screen);

        name_edit_text = findViewById(R.id.name_edit_text);
        name_last_edit_text = findViewById(R.id.name_last_edit_text);
        email_edit_text = findViewById(R.id.email_edit_text);
        password_edit_text = findViewById(R.id.password_edit_text);
        password_again_edit_text = findViewById(R.id.password_again_edit_text);
        btn_continue = findViewById(R.id.btn_continue);
        root = findViewById(R.id.root_view);

        Bundle arguments = getIntent().getExtras();
        role = arguments.getString("codeRole");

        signup = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = db.getReference("User");

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrationNewUser();
            }
        });
    }

    private void registrationNewUser() {
        if (TextUtils.isEmpty(email_edit_text.getText())){
            Snackbar.make(root,"Введите имя почты", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(name_edit_text.getText())){
            Snackbar.make(root,"Введите ваше имя", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(name_last_edit_text.getText())){
            Snackbar.make(root,"Введите вашу фамилию", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (password_edit_text.getText().toString().length() < 5 || password_edit_text.getText().toString().length() > 16){
            Snackbar.make(root,"Пароль должен содержать от 5 до 16 символов", Snackbar.LENGTH_SHORT).show();
            return;
        }
//        if (password_edit_text.getText().toString() != password_again_edit_text.getText().toString()){
//            Snackbar.make(root,"Пароли не совпадают", Snackbar.LENGTH_SHORT).show();
//            return;
//        }
        signup.createUserWithEmailAndPassword(email_edit_text.getText().toString(),password_edit_text.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        User user_men = new User();
                        user_men.setName(name_edit_text.getText().toString());
                        user_men.setLast_name(name_last_edit_text.getText().toString());
                        user_men.setEmail(email_edit_text.getText().toString());
                        user_men.setPassword(password_edit_text.getText().toString());
                        user_men.setRole(role);
                        user_men.setNickname("nickname");
                        user_men.setAvatar(R.drawable.logo_tochka_enter_screen);
                        try {
                            user_men.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01"));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        user_men.setTelephone("");

                        user.child(FirebaseAuth.getInstance().getUid())
                                .setValue(user_men)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        if (Objects.equals(role, "admin")){
                                            Intent intent = new Intent(SignUpHobbyScreen.this, SignUpAdminConfirmScreen.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else{
                                            Intent intent = new Intent(SignUpHobbyScreen.this, SignUpUserHobbyTwoScreen.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(root,"ошибка авторизации: " + e.getMessage(), Snackbar.LENGTH_LONG).show();
                                    }
                                });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(root,"ошибка авторизации: " + e.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void back_to_login_screen(View view) {
        Intent intent = new Intent(SignUpHobbyScreen.this, SignUpScreen.class);
        startActivity(intent);
        finish();
    }

}
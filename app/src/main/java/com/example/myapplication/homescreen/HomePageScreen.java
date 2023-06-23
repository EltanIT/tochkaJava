package com.example.myapplication.homescreen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.homescreen.fragments.fragment_appbar_main_screens;
import com.example.myapplication.homescreen.fragments.fragment_home;
import com.example.myapplication.homescreen.fragments.fragment_notification;
import com.example.myapplication.homescreen.fragments.fragment_search;
import com.example.myapplication.personal_account.fragment_profile_page;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePageScreen extends AppCompatActivity {

    ConstraintLayout appbar_cl;
    ImageView back_btn;

    FirebaseAuth mAuth;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_screen);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigate_view);

        replaceFragment(new fragment_home());
        replaceAppBarFragment(new fragment_appbar_main_screens());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 int id = item.getItemId();
                 if (id == R.id.main_page_fragment){
                     item.setChecked(true);
                     replaceFragment(new fragment_home());
                     replaceAppBarFragment(new fragment_appbar_main_screens());
//                     if (appbar_cl.getVisibility() == View.INVISIBLE){
//                         appbar_cv.setVisibility(View.VISIBLE);
//                     }
                 }
                 if (id == R.id.search_page_fragment){
                     item.setChecked(true);
                     replaceFragment(new fragment_search());
                     replaceAppBarFragment(new fragment_appbar_main_screens());
//                     if (appbar_cv.getVisibility() == View.INVISIBLE){
//                         appbar_cv.setVisibility(View.VISIBLE);
//                     }
                 }
                 else if (id == R.id.notification_page_fragment){
                     item.setChecked(true);
                     replaceFragment(new fragment_notification());
                     replaceAppBarFragment(new fragment_appbar_main_screens());
//                     if (appbar_cv.getVisibility() == View.INVISIBLE){
//                         appbar_cv.setVisibility(View.VISIBLE);
//                     }
                 }
                return false;
            }
        });

    }

    private void replaceAppBarFragment(fragment_appbar_main_screens fragment_appbar_main_screens) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.appbar_cl, fragment_appbar_main_screens);
        fragmentTransaction.commit();
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.main_page_fragment, fragment);
        fragmentTransaction.commit();
    }

}
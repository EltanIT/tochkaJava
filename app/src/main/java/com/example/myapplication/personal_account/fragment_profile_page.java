package com.example.myapplication.personal_account;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.homescreen.fragments.fragment_home;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;


public class fragment_profile_page extends Fragment {


    TextView name_user_tv, surname_user_tv;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    FirebaseUser user;

    private DatabaseReference reference;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_page,container,false);
        initializationElements(view);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("User");

        user = mAuth.getCurrentUser();
        setDateONProfil();



        return view;
    }

    private void initializationElements(View view){
        name_user_tv = view.findViewById(R.id.name_user_tv);
        surname_user_tv = view.findViewById(R.id.surname_user_tv);

    }

    private void setDateONProfil(){
        reference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GenericTypeIndicator<String> genericTypeIndicator = new GenericTypeIndicator<String>(){};
                name_user_tv.setText(snapshot.child("name").getValue(genericTypeIndicator));
                surname_user_tv.setText(snapshot.child("last_name").getValue(genericTypeIndicator));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
   }

}
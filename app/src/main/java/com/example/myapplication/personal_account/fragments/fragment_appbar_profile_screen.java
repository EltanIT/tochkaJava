package com.example.myapplication.personal_account.fragments;

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
import com.example.myapplication.homescreen.fragments.fragment_appbar_main_screens;
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

public class fragment_appbar_profile_screen extends Fragment {

    private ImageButton back_to_MainScrenn_ib;
    private ImageView avatar_image_iv;
    private EditText nickame_user_et;

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    FirebaseUser user;
    private DatabaseReference reference;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference;
    private Bitmap my_image;
    private Uri filePath;
    private final int GALLEY_REQ_CODE = 1000;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appbar_profile_screen, container, false);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("User");
        user = mAuth.getCurrentUser();
        setDateONProfil();

        back_to_MainScrenn_ib = view.findViewById(R.id.back_to_MainScrenn_ib);
        nickame_user_et = view.findViewById(R.id.nickame_user_et);
        avatar_image_iv = view.findViewById(R.id.avatar_image_iv);

        nickame_user_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                mAuth = FirebaseAuth.getInstance();
                db = FirebaseDatabase.getInstance();
                reference = db.getReference("User");

                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                reference.child(firebaseUser.getUid()).child("nickname")
                        .setValue(nickame_user_et.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getContext(),"никнейм успешно изменен", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(),"Что-то пошло не так", Toast.LENGTH_SHORT).show();
                            }
                        });
                return false;
            }
        });

        avatar_image_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLEY_REQ_CODE);
            }
        });

        back_to_MainScrenn_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_home backFragHome= new fragment_home();
                fragment_appbar_main_screens backFragAppbar = new fragment_appbar_main_screens();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_page_fragment, backFragHome, "findThisFragment")
                        .replace(R.id.appbar_cl, backFragAppbar, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode==GALLEY_REQ_CODE){
                avatar_image_iv.setImageURI(data.getData());
                filePath = data.getData();

                storageReference = storage.getReference();
                StorageReference storageReference1 = storageReference.child("images/").child(user.getUid()+".jpg");
                storageReference1.delete();
                uploadImageInFireBase();
            }
        }
    }

    private void setDateONProfil(){
        reference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GenericTypeIndicator<String> genericTypeIndicator = new GenericTypeIndicator<String>(){};
                nickame_user_et.setText(snapshot.child("nickname").getValue(genericTypeIndicator));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        StorageReference ref = storage.getReference().child("images/").child(user.getUid());
        try {
            final File localFile = File.createTempFile("Images", "jpeg");
            ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener< FileDownloadTask.TaskSnapshot >() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    my_image = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    avatar_image_iv.setImageBitmap(my_image);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void uploadImageInFireBase() {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ user.getUid());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

}
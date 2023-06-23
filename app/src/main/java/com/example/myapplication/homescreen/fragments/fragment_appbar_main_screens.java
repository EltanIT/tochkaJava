package com.example.myapplication.homescreen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.personal_account.fragment_profile_page;
import com.example.myapplication.personal_account.fragments.fragment_appbar_profile_screen;

public class fragment_appbar_main_screens extends Fragment {

    ImageButton back_btn;
    ImageView avatar_appbar_iv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appbar_main_screens,container,false);
        back_btn = view.findViewById(R.id.image_button_back);
        avatar_appbar_iv = view.findViewById(R.id.avatar_appbar_iv);

        back_btn.setVisibility(View.INVISIBLE);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back_btn.setVisibility(View.INVISIBLE);
            }
        });
        avatar_appbar_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProfile();
            }
        });
        return view;
    }

    private void goToProfile(){
        fragment_profile_page nextFrag= new fragment_profile_page();
        fragment_appbar_profile_screen nextFragAppBar= new fragment_appbar_profile_screen();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_page_fragment, nextFrag, "findThisFragment")
                .replace(R.id.appbar_cl, nextFragAppBar,"findThisFragment")
                .addToBackStack(null)
                .commit();
    }
}
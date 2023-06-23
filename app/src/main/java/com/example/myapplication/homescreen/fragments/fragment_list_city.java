package com.example.myapplication.homescreen.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class fragment_list_city extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_city_list, container, false);

        ImageButton image_button_back = getActivity().findViewById(R.id.image_button_back);
        image_button_back.setVisibility(View.VISIBLE);
        image_button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_home nextFrag= new fragment_home();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_page_fragment, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
                image_button_back.setVisibility(View.INVISIBLE);
            }
        });


        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = view.findViewById(R.id.list);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
        return view;
    }

}
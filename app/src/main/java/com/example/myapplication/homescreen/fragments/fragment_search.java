package com.example.myapplication.homescreen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.homescreen.home_screen_adapters.NewsBaseAdapter;
import com.example.myapplication.homescreen.home_screen_adapters.RecGroupBaseAdapter;
import com.example.myapplication.models.HomeScreenNews;
import com.example.myapplication.models.HomeScreenRecGroup;

import java.util.ArrayList;


public class fragment_search extends Fragment {

    ArrayList<HomeScreenNews> listNews = new ArrayList<>();
    RecyclerView now_going_rv, now_going_rv2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,null);
        now_going_rv = view.findViewById(R.id.now_going_rv);
        now_going_rv2 = view.findViewById(R.id.now_going_rv2);
        addNews();

        return view;
    }

    private void addNews(){
        NewsBaseAdapter newsBaseAdapter = new NewsBaseAdapter(getContext(),listNews);
        now_going_rv.setAdapter(newsBaseAdapter);
        now_going_rv2.setAdapter(newsBaseAdapter);

        listNews.add(new HomeScreenNews("Футбол", "NOM 01 - NOM 02", 1));
        listNews.add(new HomeScreenNews("Стандоф", "SEN 19 - SEN 18", 1));
        listNews.add(new HomeScreenNews("пир", "NOM 01 - NOM 02", 1));
        listNews.add(new HomeScreenNews("тртртр", "SEN 19 - SEN 18", 1));
        listNews.add(new HomeScreenNews("Тарас", "NOM 01 - NOM 02", 1));

    }


}
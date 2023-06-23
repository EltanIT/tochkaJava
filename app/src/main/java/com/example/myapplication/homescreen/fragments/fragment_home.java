package com.example.myapplication.homescreen.fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.homescreen.home_screen_adapters.NewsBaseAdapter;
import com.example.myapplication.homescreen.home_screen_adapters.RecGroupBaseAdapter;
import com.example.myapplication.homescreen.home_screen_adapters.YourGroupsBaseAdapter;
import com.example.myapplication.models.HomeScreenNews;
import com.example.myapplication.models.HomeScreenRecGroup;
import com.example.myapplication.models.YourGroup;

import java.util.ArrayList;

public class fragment_home extends Fragment {
    ArrayList<HomeScreenNews> listNews = new ArrayList<>();
    ArrayList<HomeScreenRecGroup> listRecGroups = new ArrayList<>();
    ArrayList<YourGroup> listYourGroups = new ArrayList<>();
    RecyclerView news_listview, groups_in_your_city_rv,your_groups_rv;
    LinearLayout your_orientation_linearlayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home2,null);

        news_listview = view.findViewById(R.id.news_listview);
        groups_in_your_city_rv = view.findViewById(R.id.groups_in_your_city_rv);
        your_groups_rv = view.findViewById(R.id.your_groups_rv);
        your_orientation_linearlayout = view.findViewById(R.id.your_orientation_linearlayout);

        addYourGroups();
        addRecGroups();
        addNews();

        your_orientation_linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_list_city nextFrag= new fragment_list_city();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_page_fragment, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    private void addNews(){
        NewsBaseAdapter newsBaseAdapter = new NewsBaseAdapter(getContext(),listNews);
        news_listview.setAdapter(newsBaseAdapter);

        listNews.add(new HomeScreenNews("Футбол", "NOM 01 - NOM 02", 1));
        listNews.add(new HomeScreenNews("Стандоф", "SEN 19 - SEN 18", 1));
        listNews.add(new HomeScreenNews("пир", "NOM 01 - NOM 02", 1));
        listNews.add(new HomeScreenNews("тртртр", "SEN 19 - SEN 18", 1));
        listNews.add(new HomeScreenNews("Тарас", "NOM 01 - NOM 02", 1));

    }
    private void addRecGroups(){
        RecGroupBaseAdapter recGroupBaseAdapter = new RecGroupBaseAdapter(getContext(),listRecGroups);
        groups_in_your_city_rv.setAdapter(recGroupBaseAdapter);

        listRecGroups.add(new HomeScreenRecGroup("Футбол", "13", 1));
        listRecGroups.add(new HomeScreenRecGroup("Стандоф", "142542", 1));
        listRecGroups.add(new HomeScreenRecGroup("пир", "1556", 1));
        listRecGroups.add(new HomeScreenRecGroup("тртртр", "1", 1));
        listRecGroups.add(new HomeScreenRecGroup("Тарас", "1000000", 1));
    }

    private void addYourGroups(){
        YourGroupsBaseAdapter yourGroupsBaseAdapter = new YourGroupsBaseAdapter(getContext(),listYourGroups);
        your_groups_rv.setAdapter(yourGroupsBaseAdapter);

        listYourGroups.add(new YourGroup("Футбол", "13", 1));
        listYourGroups.add(new YourGroup("Стандоф", "142542", 1));
        listYourGroups.add(new YourGroup("пир", "1556", 1));
        listYourGroups.add(new YourGroup("тртртр", "1", 1));
        listYourGroups.add(new YourGroup("Тарас", "1000000", 1));
    }

}
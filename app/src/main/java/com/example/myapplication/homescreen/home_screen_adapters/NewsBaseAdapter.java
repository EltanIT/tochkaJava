package com.example.myapplication.homescreen.home_screen_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.HomeScreenNews;

import java.util.ArrayList;

public class NewsBaseAdapter extends RecyclerView.Adapter<NewsBaseAdapter.ViewHolder>{

    Context context;
   ArrayList<HomeScreenNews> listNews;
    LayoutInflater inflater;

    public NewsBaseAdapter(Context context,  ArrayList<HomeScreenNews> listNews) {
        this.context = context;
        this.listNews = listNews;
        inflater = LayoutInflater.from(context);

//        this.banner_event = banner_event;
    }


    @NonNull
    @Override
    public NewsBaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_screen_news_listview_item_model,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeScreenNews homeScreenNews = listNews.get(position);
        holder.name_event_tv.setText(homeScreenNews.getName());
        holder.date_event_tv.setText(homeScreenNews.getDate());
//        holder.banner_event
    }


    @Override
    public int getItemCount() {
        return listNews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name_event_tv, date_event_tv;
        final ImageView banner_event;
        ViewHolder(View view){
            super(view);
            name_event_tv = view.findViewById(R.id.name_event_tv);
            date_event_tv = view.findViewById(R.id.date_event_tv);
            banner_event = view.findViewById(R.id.banner_event_imageview);
        }
    }
}

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
import com.example.myapplication.models.HomeScreenRecGroup;

import java.util.ArrayList;

public class RecGroupBaseAdapter extends RecyclerView.Adapter<RecGroupBaseAdapter.ViewHolder>{

        Context context;
        ArrayList<HomeScreenRecGroup> listRecGroups;
        LayoutInflater inflater;

        public RecGroupBaseAdapter(Context context,ArrayList<HomeScreenRecGroup> listRecGroups) {
            this.context = context;
            this.listRecGroups = listRecGroups;
            inflater = LayoutInflater.from(context);

//        this.banner_event = banner_event;
        }



        @NonNull
        @Override
        public RecGroupBaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.home_screen_rec_group_lv_item_model,parent,false);
            return new RecGroupBaseAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecGroupBaseAdapter.ViewHolder holder, int position) {
            HomeScreenRecGroup homeScreenRecGroup = listRecGroups.get(position);
            holder.name_group_tv.setText(homeScreenRecGroup.getName());
            holder.count_participant_group_tv.setText(homeScreenRecGroup.getCount_participant());
            holder.paticipant_group_tv.setText(getParticipantAddition(Integer.parseInt(homeScreenRecGroup.getCount_participant())));
//        holder.banner_event
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public int getItemCount() {
            return listRecGroups.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            final TextView name_group_tv, count_participant_group_tv, paticipant_group_tv;
            final ImageView image_group_iv;
            ViewHolder(View view){
                super(view);
                name_group_tv = view.findViewById(R.id.name_group_tv);
                count_participant_group_tv = view.findViewById(R.id.count_participant_group_tv);
                image_group_iv = view.findViewById(R.id.image_group_iv);
                paticipant_group_tv = view.findViewById(R.id.paticipant_group_tv);
            }
        }

    public String getParticipantAddition(int num) {

        int preLastDigit = num % 100 / 10;

        if (preLastDigit == 1) {
            return "участников";
        }

        switch (num % 10) {
            case 1:
                return "участник";
            case 2:
            case 3:
            case 4:
                return "участника";
            default:
                return "участников";
        }

    }

}

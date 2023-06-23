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
import com.example.myapplication.models.YourGroup;

import java.util.ArrayList;

public class YourGroupsBaseAdapter extends RecyclerView.Adapter<YourGroupsBaseAdapter.ViewHolder>{

    Context context;
    ArrayList<YourGroup> listYourGroups;
    LayoutInflater inflater;

    public YourGroupsBaseAdapter(Context context, ArrayList<YourGroup> listYourGroups) {
        this.context = context;
        this.listYourGroups = listYourGroups;
        inflater = LayoutInflater.from(context);

//        this.banner_event = banner_event;
    }

    @NonNull
    @Override
    public YourGroupsBaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_screen_your_groups_lv_item_model,parent,false);
        return new YourGroupsBaseAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YourGroupsBaseAdapter.ViewHolder holder, int position) {
        YourGroup homeScreenYourGroup= listYourGroups.get(position);
        holder.name_your_group_tv.setText(homeScreenYourGroup.getName());
        holder.count_participant_your_group_tv.setText(homeScreenYourGroup.getCount_participant());
        holder.participant_group_tv.setText(getParticipantAddition(Integer.parseInt(homeScreenYourGroup.getCount_participant())));
//      holder.banner_event
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return listYourGroups.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name_your_group_tv, count_participant_your_group_tv, participant_group_tv;
        final ImageView image_your_group_cv;
        ViewHolder(View view){
            super(view);
            name_your_group_tv = view.findViewById(R.id.name_your_group_tv);
            count_participant_your_group_tv = view.findViewById(R.id.count_participant_your_group_tv);
            image_your_group_cv = view.findViewById(R.id.image_your_group_cv);
            participant_group_tv = view.findViewById(R.id.participant_group_tv);
        }
    }


    private String getParticipantAddition(int num) {

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

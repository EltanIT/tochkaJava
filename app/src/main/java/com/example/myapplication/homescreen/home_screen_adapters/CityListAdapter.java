package com.example.myapplication.homescreen.home_screen_adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.homescreen.fragments.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.myapplication.databinding.FragmentListCityBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {

    private final List<PlaceholderItem> mValues;

    public CityListAdapter(List<PlaceholderItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    return new ViewHolder(FragmentListCityBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public PlaceholderItem mItem;

    public ViewHolder(FragmentListCityBinding binding) {
      super(binding.getRoot());
      mIdView = binding.itemNumber;
      mContentView = binding.content;
    }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
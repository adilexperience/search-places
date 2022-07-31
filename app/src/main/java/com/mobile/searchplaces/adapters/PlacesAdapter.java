package com.mobile.searchplaces.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.searchplaces.R;
import com.mobile.searchplaces.models.PlaceModel;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {
    Context mContext;
    ArrayAdapter<PlaceModel> places;

    public PlacesAdapter() {
    }

    public PlacesAdapter(Context mContext, ArrayAdapter<PlaceModel> places) {
        this.mContext = mContext;
        this.places = places;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.place, parent, false);
        return new PlaceViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesAdapter.PlaceViewHolder holder, int position) {
        final PlaceModel placeModel = places.getItem(position);

        holder.mTVName.setText(placeModel.getName());
        holder.mTVDescription.setText(placeModel.getDescription());
        holder.mTVRating.setText("Rating: " + placeModel.getRating());
    }

    @Override
    public int getItemCount() {
        return places.getCount();
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        ImageView mIVPlace;
        TextView mTVName, mTVDescription, mTVRating;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);

            mIVPlace = itemView.findViewById(R.id.iv_place);
            mTVName = itemView.findViewById(R.id.tv_name);
            mTVDescription = itemView.findViewById(R.id.tv_description);
            mTVRating = itemView.findViewById(R.id.tv_rating);
        }

    }

}

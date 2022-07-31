package com.mobile.searchplaces.adapters;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.mobile.searchplaces.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        View listItem = inflater.inflate(R.layout.place, parent, null);
        PlaceViewHolder placeViewHolder = new PlaceViewHolder(listItem);
        return placeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesAdapter.PlaceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

}

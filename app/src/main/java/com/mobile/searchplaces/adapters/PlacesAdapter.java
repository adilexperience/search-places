package com.mobile.searchplaces.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.searchplaces.PlaceDetailActivity;
import com.mobile.searchplaces.R;
import com.mobile.searchplaces.models.PlaceModel;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {
    Context mContext;
    ArrayList<PlaceModel> places;

    public PlacesAdapter() {
    }

    public PlacesAdapter(Context mContext, ArrayList<PlaceModel> places) {
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
        final PlaceModel placeModel = places.get(position);

        holder.mTVName.setText(placeModel.getName());
        holder.mTVDescription.setText(placeModel.getDescription());
        holder.mTVRating.setText("Rating: " + placeModel.getRating());

        holder.mIVPlace.setImageBitmap(getImage(placeModel.getImage()));

        holder.cvPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PlaceDetailActivity.class);
                intent.putExtra("place", placeModel);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        ImageView mIVPlace;
        TextView mTVName, mTVDescription, mTVRating;
        CardView cvPlace;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);

            mIVPlace = itemView.findViewById(R.id.iv_place);
            mTVName = itemView.findViewById(R.id.tv_name);
            mTVDescription = itemView.findViewById(R.id.tv_description);
            mTVRating = itemView.findViewById(R.id.tv_rating);
            cvPlace = itemView.findViewById(R.id.cv_place);
        }

    }

}

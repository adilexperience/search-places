package com.mobile.searchplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.searchplaces.models.PlaceModel;

public class PlaceDetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvName, tvDescription, tvRating;
    PlaceModel place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        place = (PlaceModel) getIntent().getSerializableExtra("place");


        imageView = findViewById(R.id.iv_place);
        tvName = findViewById(R.id.tv_name);
        tvDescription = findViewById(R.id.tv_description);
        tvRating = findViewById(R.id.tv_rating);


        imageView.setImageBitmap(getImage(place.getImage()));
        tvName.setText(place.getName());
        tvDescription.setText(place.getDescription());
        tvRating.setText("Rating: " + place.getRating());
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
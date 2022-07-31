package com.mobile.searchplaces.ui.add_place;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobile.searchplaces.R;
import com.mobile.searchplaces.database.DatabaseHelper;
import com.mobile.searchplaces.models.PlaceModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddPlaceFragment extends Fragment {
    public static final int PICK_IMAGE = 1;

    FloatingActionButton fabSelectImage;
    ImageView ivSelectedImage;
    EditText etName, etDescription, etRating;
    Button btnAddPlace;

    Bitmap bitmap;

    ActivityResultLauncher<Intent> mLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // Do your code from onActivityResult

                    if(result.getData() != null && result.getResultCode() == RESULT_OK) {
                        InputStream inputStream = null;
                        try {
                            inputStream = getContext().getContentResolver().openInputStream(result.getData().getData());
                            bitmap = BitmapFactory.decodeStream(inputStream);
                            ivSelectedImage.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            Log.e("ADD_PLACE", e.toString());
                            e.printStackTrace();
                        }

                    }
                }
            });

    public AddPlaceFragment() {
        // Required empty public constructor
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_place, container, false);

        fabSelectImage = view.findViewById(R.id.fab_select_image);
        ivSelectedImage = view.findViewById(R.id.iv_selected_image);
        etName = view.findViewById(R.id.et_name);
        etDescription = view.findViewById(R.id.et_description);
        etRating = view.findViewById(R.id.et_rating);
        btnAddPlace = view.findViewById(R.id.btn_add_place);

        fabSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                Intent chooserIntent = Intent.createChooser(intent, "Select picture");
                mLauncher.launch(chooserIntent);
            }
        });

        btnAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim(), description = etDescription.getText().toString().trim(), rating = etRating.getText().toString().trim();
                if(name.isEmpty()) {
                    Toast.makeText(getContext(), "Enter place name", Toast.LENGTH_SHORT).show();
                }else if(description.isEmpty()) {
                    Toast.makeText(getContext(), "Enter place description", Toast.LENGTH_SHORT).show();
                }else if(rating.isEmpty()) {
                    Toast.makeText(getContext(), "Enter place rating", Toast.LENGTH_SHORT).show();
                }else {
                    double doubleRating = Double.parseDouble(rating);

                    Log.e("ADD_PLACE", bitmap+ "");
                    if(bitmap == null) {
                        Toast.makeText(getContext(), "Select image", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    PlaceModel place = new PlaceModel(0, name, description, getBytes(bitmap), doubleRating);

                    // add to database now
                    DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                    databaseHelper.addPlace(place);
                    Toast.makeText(getContext(), "Place added successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}
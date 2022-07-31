package com.mobile.searchplaces.ui.add_place;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobile.searchplaces.R;

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

                    if(result.getData() != null) {

                        Uri selectedImage = result.getData().getData();
                        String[] filePathColumn = { MediaStore.Images.Media.DATA };

                        Cursor cursor = getContext().getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);
                        cursor.close();

                        bitmap = BitmapFactory.decodeFile(picturePath);
                        ivSelectedImage.setImageBitmap(bitmap);
                    }
                }
            });

    public AddPlaceFragment() {
        // Required empty public constructor
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
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Intent chooserIntent = Intent.createChooser(intent, "Select picture");
                mLauncher.launch(chooserIntent);
            }
        });

        return view;
    }
}
package com.mobile.searchplaces.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.searchplaces.R;
import com.mobile.searchplaces.adapters.PlacesAdapter;
import com.mobile.searchplaces.database.DatabaseHelper;
import com.mobile.searchplaces.databinding.FragmentHomeBinding;
import com.mobile.searchplaces.models.PlaceModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView rvPlaces;

    private PlacesAdapter placesAdapter;
    private ArrayList<PlaceModel> places = new ArrayList<PlaceModel>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvPlaces = view.findViewById(R.id.rv_popular_places);


        // get places from database and display
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        places =  databaseHelper.getAllPlaces();

        placesAdapter = new PlacesAdapter(getContext(), places);

        rvPlaces.setHasFixedSize(true);
        rvPlaces.setLayoutManager(new LinearLayoutManager(getContext()));

        rvPlaces.setAdapter(placesAdapter);

        return view;
    }
}
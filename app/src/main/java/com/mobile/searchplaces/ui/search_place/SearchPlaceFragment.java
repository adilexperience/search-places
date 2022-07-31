package com.mobile.searchplaces.ui.search_place;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mobile.searchplaces.R;
import com.mobile.searchplaces.adapters.PlacesAdapter;
import com.mobile.searchplaces.database.DatabaseHelper;
import com.mobile.searchplaces.models.PlaceModel;

import java.util.ArrayList;

public class SearchPlaceFragment extends Fragment {
    EditText etSearch;
    ImageView ivSearch;

    RecyclerView rvSearch;
    private PlacesAdapter placesAdapter;
    private ArrayList<PlaceModel> places = new ArrayList<PlaceModel>();
    public SearchPlaceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_place, container, false);

        etSearch = view.findViewById(R.id.et_search);
        ivSearch = view.findViewById(R.id.iv_search);
        rvSearch = view.findViewById(R.id.rv_search_places);

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etSearch.getText().toString().trim();

                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                places = databaseHelper.getSearchedPlace(name);

                placesAdapter = new PlacesAdapter(getContext(), places);

                rvSearch.setHasFixedSize(true);
                rvSearch.setLayoutManager(new LinearLayoutManager(getContext()));

                rvSearch.setAdapter(placesAdapter);
            }
        });

        return view;
    }
}
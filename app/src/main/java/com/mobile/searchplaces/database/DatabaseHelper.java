package com.mobile.searchplaces.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.searchplaces.models.PlaceModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    String placesTable = "Places";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "PlacesDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + placesTable + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DESCRIPTION TEXT, RATING REAL, IMAGE BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + placesTable);
    }

    public void addPlace(PlaceModel place) {
        ContentValues values = new ContentValues();
        values.put("NAME", place.getName());
        values.put("DESCRIPTION", place.getName());
        values.put("RATING", place.getRating());
        values.put("IMAGE", place.getImage());
        this.getWritableDatabase().insert(placesTable, null, values);
    }

    public ArrayList<PlaceModel> getSearchedPlace(String name) {
        ArrayList<PlaceModel> places = new ArrayList<PlaceModel>();
        Cursor cursor = this.getWritableDatabase().rawQuery("SELECT * FROM " + placesTable + " WHERE NAME='" + name + "'", null);

        while(cursor.moveToNext()) {
            PlaceModel place = new PlaceModel();
            place.setId(Integer.parseInt(cursor.getString(0)));
            place.setName(cursor.getString(1));
            place.setDescription(cursor.getString(2));
            place.setRating(Double.parseDouble(cursor.getString(3)));
            place.setImage(cursor.getBlob(4));

            places.add(place);
        }

        return places;
    }

    public ArrayList<PlaceModel> getAllPlaces() {
        ArrayList<PlaceModel> places = new ArrayList<PlaceModel>();
        Cursor cursor = this.getWritableDatabase().rawQuery("SELECT * FROM " + placesTable, null);

        while(cursor.moveToNext()) {
            PlaceModel place = new PlaceModel();
            place.setId(Integer.parseInt(cursor.getString(0)));
            place.setName(cursor.getString(1));
            place.setDescription(cursor.getString(2));
            place.setRating(Double.parseDouble(cursor.getString(3)));
            place.setImage(cursor.getBlob(4));

            places.add(place);
        }

        return places;
    }

}

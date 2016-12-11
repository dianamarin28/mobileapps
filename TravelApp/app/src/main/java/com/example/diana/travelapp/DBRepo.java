package com.example.diana.travelapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Diana on 12/10/2016.
 */

public class DBRepo extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "TravelApp.db";

    private static final String TABLE_PLACES = "Places";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_COUTRY = "country";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_RATING = "rating";

    public DBRepo(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_PLACES + " ( " +
                COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                COLUMN_COUTRY + " TEXT, " +
                COLUMN_CITY + " TEXT, " +
                COLUMN_RATING + " INTEGER" +
                " );";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_PLACES;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public boolean addPlace(Place place) {
        if(place != null){
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, place.getId());
            values.put(COLUMN_COUTRY, place.getCountry());
            values.put(COLUMN_CITY, place.getCity());
            values.put(COLUMN_RATING, place.getRating());
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLE_PLACES + " WHERE " + COLUMN_ID + " ='" + place.getId() + "';");
            db.insert(TABLE_PLACES, null, values);
            return true;
        }
        return false;
    }

    public Place getPlace(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PLACES + " WHERE " + COLUMN_ID + "='" + id + "';", null);
        if(cursor.moveToFirst()){
            try{
                Integer placeID = Integer.parseInt(cursor.getString(0));
                String country = cursor.getString(1);
                String city = cursor.getString(2);
                Integer rating = Integer.parseInt(cursor.getString(3));
                Place place = new Place(placeID, country, city, rating);
                cursor.close();
                return place;
            }
            catch(Exception ex) {
                cursor.close();
                return null;
            }
        }
        cursor.close();
        return null;
    }

    public void deletePlace(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PLACES + " WHERE " + COLUMN_ID + " ='" + id + "';");
    }

    public Place[] getAllPlaces(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PLACES + ";", null);
        int placesCount = this.getPlacesCount();
        Place[] places = new Place[placesCount];
        if(cursor.moveToFirst()){
            for(int i=0; i<placesCount; i++){
                try{
                    Integer placeID = Integer.parseInt(cursor.getString(0));
                    String country = cursor.getString(1);
                    String city = cursor.getString(2);
                    Integer rating = Integer.parseInt(cursor.getString(3));
                    places[i] = new Place(placeID, country, city, rating);
                    cursor.moveToNext();
                }
                catch(Exception ex){
                    System.out.println("________EXCEPTION");
                    cursor.moveToNext();
                }
            }
        }
        cursor.close();
        return places;
    }

    public int getPlacesCount(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PLACES;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getRatingCount(int rating) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PLACES + " WHERE " + COLUMN_RATING + " ='" + rating +"';";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}

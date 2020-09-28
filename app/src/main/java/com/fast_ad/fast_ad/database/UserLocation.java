package com.fast_ad.fast_ad.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class UserLocation  extends SQLiteOpenHelper {

    private static final String TAG = UserDbHelper.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "fastap";

    // Login table name
    private static final String TABLE_LOCATION = "location";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_LONGITUDE= "longitude";
    private static final String KEY_LATITTUDE= "latitude";
    private static final String KEY_CREATED_AT = "created_at";

    public UserLocation(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOCATION_TABLE = "CREATE TABLE " + TABLE_LOCATION + "("
                + KEY_ID + " INTEGER PRIMARY KEY," +  " TEXT,"+ KEY_LONGITUDE + " TEXT,"+ KEY_LATITTUDE+ " TEXT,"
                + KEY_CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_LOCATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public HashMap<String, String> getUserSaveLocation() {
        HashMap<String, String> location = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_LOCATION;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            location.put("id", cursor.getString(1));
            location.put("longitude", cursor.getString(2));
            location.put("latitude", cursor.getString(3));
        }
        cursor.close();
        db.close();

        return location;
    }



    public void addLocation(double longitude , double latitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LATITTUDE, latitude);
        values.put(KEY_LONGITUDE, longitude);

        db.insert(TABLE_LOCATION, null, values);
        db.close();

    }




    public  void deletelocation() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOCATION, null, null);
        db.close();
    }
}

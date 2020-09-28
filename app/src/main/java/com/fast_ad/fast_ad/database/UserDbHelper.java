/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 * */
package com.fast_ad.fast_ad.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class UserDbHelper extends SQLiteOpenHelper {

	private static final String TAG = UserDbHelper.class.getSimpleName();

	// All Static variables
	// Database Version
	public static final int DATABASE_VERSION = 1;

	// Database Name
	public static final String DATABASE_NAME = "fastapuser";

	// Login table name
	private static final String TABLE_USER = "users";

	// Login Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME= "name";
	private static final String KEY_EMAIL= "email";
	private static final String KEY_PHONE= "telephone";
	private static final String KEY_AUTH_KEY = "auth_key";

	public UserDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"+ KEY_EMAIL+ " TEXT,"+ KEY_AUTH_KEY+ " TEXT,"
				+ KEY_PHONE + " TEXT" + ")";
		db.execSQL(CREATE_LOGIN_TABLE);

		Log.d(TAG, "Database tables created");
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	/**
	 * Storing user details in database
	 * */
	public boolean addUser(String name, String email,String telephone,String auth_key) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name);
		values.put(KEY_EMAIL, email);
		values.put(KEY_PHONE, telephone);
		values.put(KEY_AUTH_KEY, auth_key);

		// Inserting Row
		long id = db.insert(TABLE_USER, null, values);
		db.close();
		if (id != 0){
			return true;
		}else{
			return false;
		}


	}


	public boolean updateProfil(String phone, String uid) {
		try{
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(KEY_PHONE, phone);
			db.update(TABLE_USER, values, KEY_PHONE + " = ?",
					new String[]{String.valueOf(uid)});
			return  true;
		}catch (Exception e){
			return false;
		}

	}


	/**
	 * Getting user data from database
	 * */
	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> user = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE_USER;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.put("name", cursor.getString(1));
			user.put("email", cursor.getString(2));
			user.put("auth_key", cursor.getString(3));
			user.put("telephone", cursor.getString(4));

		}
		cursor.close();
		db.close();
		// return user
		Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

		return user;
	}

	/**
	 * Re crate database Delete all tables and create them again
	 * */
	public void deleteUsers() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_USER, null, null);
		db.close();

		Log.d(TAG, "Deleted all user info from sqlite");
	}

}

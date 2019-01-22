package com.application.aayush.geeta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Praveen K S
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userManager";
    private static final String TABLE_USER = "user";
    private static String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MOB_NO = "mobile_number";
    private static final String KEY_EMAIL = "email_id";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_CITY = "city";


    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        //3rd argument to be passed is cursorFactory instance
    }

    // Creating Tables

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " +TABLE_USER +"("
                +KEY_ID+" INTEGER PRIMARY KEY ,"
                +KEY_NAME+" VARCHAR(20),"
                +KEY_MOB_NO+" VARCHAR(20),"
                +KEY_EMAIL+" VARCHAR(64),"
                +KEY_ADDRESS+" VARCHAR(64),"
                +KEY_CITY+" VARCHAR(64)"
                +")";
        db.execSQL(CREATE_USER_TABLE);
    }

    //code to get all contacts in a listview
    public List<UserProfile> getAllUsers(){
        List<UserProfile> profileList = new ArrayList<UserProfile>();
        //select all query

        String selectQuery = "SELECT * FROM " +TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        //Looping trough all rows and adding to list
        if (cursor.moveToFirst()){
            do {
                UserProfile userProfile = new UserProfile();
                userProfile.setUser_id(Integer.parseInt(cursor.getString(0)));
                userProfile.setName(cursor.getString(1));
                userProfile.setMobile_no(cursor.getString(2));
                userProfile.setEmail_id(cursor.getString(3));
                userProfile.setAddress(cursor.getString(4));
                userProfile.setCity(cursor.getString(5));

                //adding user to list
                profileList.add(userProfile);
            }while (cursor.moveToNext());
        }
        cursor.close();
        //return userlist
        return profileList;
    }

    //Upgrading database

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table if exists
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_USER);

        //create tables again
        onCreate(db);
    }

    //code to add the new contact
    void addUser(UserProfile userProfile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,userProfile.getName());
        values.put(KEY_MOB_NO,userProfile.getMobile_no());
        values.put(KEY_EMAIL,userProfile.getEmail_id());
        values.put(KEY_ADDRESS,userProfile.getAddress());
        values.put(KEY_CITY,userProfile.getCity());

        //Inserting Row
        db.insert(TABLE_USER,null,values);
        //2nd argument is String containing nullcolumnHack
        db.close();
    }

    //code to get the single contact
    UserProfile getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_USER,
                new String[]{KEY_ID,KEY_NAME,KEY_MOB_NO,KEY_EMAIL,KEY_ADDRESS,KEY_CITY},
                KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null
        );
          if (cursor != null)
              cursor.moveToFirst();

        UserProfile  userProfile = new UserProfile(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
        cursor.close();
        return userProfile;
    }
    public int updateUser(UserProfile userProfile){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,userProfile.getName());
        values.put(KEY_MOB_NO,userProfile.getMobile_no());
        values.put(KEY_EMAIL,userProfile.getEmail_id());
        values.put(KEY_ADDRESS,userProfile.getAddress());
        values.put(KEY_CITY,userProfile.getCity());
        int update_count = db.update(TABLE_USER,values,KEY_ID+"=?",new String[]{String.valueOf(userProfile.getUser_id())});
        db.close();
        //updating row
        return update_count;

    }
    public void deleteUser(UserProfile userProfile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete(TABLE_USER,KEY_ID+"=?",new String[]{String.valueOf(userProfile.getUser_id())});
        db.close();
    }
    public int userCount(){
        String countQuery = "SELECT * FROM "+TABLE_USER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        if(cursor == null){
            return  0;
        }
        int count = cursor.getCount();
        cursor.close();
        return  count;
    }


}

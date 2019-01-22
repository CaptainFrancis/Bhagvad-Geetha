package com.application.aayush.geeta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aayush on 11/12/2017.
 */

public class DiaryDatabaseHandler extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 1;
    static final String TABLE_DIARY = "diary";
    static final String DATABASE_NAME = "diary_manager";
    static final String KEY_CHAPTER_ID = "chapter";
    static final String KEY_DIARY_DETAIL = "diary";

    public DiaryDatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        //3rd argument to be passed is cursorFactory instance
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DIARY_TABLE = "CREATE TABLE " + TABLE_DIARY +"("
                +KEY_CHAPTER_ID +" INTEGER PRIMARY KEY, "
                + KEY_DIARY_DETAIL + " TEXT "+
                ")";
        db.execSQL(CREATE_DIARY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIARY);

        // Create tables again
        onCreate(db);

    }
    //adding a new content in diary
    void addContent(Diary diary){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CHAPTER_ID,diary.getChapter_no());
        values.put(KEY_DIARY_DETAIL,diary.getDiary_details());
        //inserting record
        db.insert(TABLE_DIARY,null,values);
        db.close();//closing database connection
    }
    boolean deleteContent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        return db.delete(TABLE_DIARY, KEY_CHAPTER_ID + "=" + id, null) > 0;
    }
    //get a single diary content
    Diary getContent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Diary diary;
        Cursor cursor = db.query(
                TABLE_DIARY,
                new String[]{KEY_CHAPTER_ID,KEY_DIARY_DETAIL},
                KEY_CHAPTER_ID +"= ?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor == null) {
            return null;
        }
            cursor.moveToNext();
        diary = new Diary(Integer.parseInt(cursor.getString(0)),cursor.getString(1));
        cursor.close();
        db.close();
        return diary;
    }
    public int UpdateContent(Diary diary){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(KEY_DIARY_DETAIL,diary.getDiary_details());
        //updating row
        return db.update(TABLE_DIARY,values,KEY_CHAPTER_ID +"=?",new String[]{String.valueOf(diary.getChapter_no())});
    }
    public int contentCount(){
        String countQuery = "SELECT * FROM "+TABLE_DIARY;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        if(cursor == null){
            return  0;
        }
        int count = cursor.getCount();
        cursor.close();
        return  count;
    }
    //code to get all contacts in a listview
    public List<Diary> getAllUsers(){
        List<Diary> profileList = new ArrayList<Diary>();
        //select all query

        String selectQuery = "SELECT * FROM " +TABLE_DIARY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        //Looping trough all rows and adding to list
        if (cursor.moveToFirst()){
            do {
                Diary diary = new Diary();
                diary.setChapter_no(Integer.parseInt(cursor.getString(0)));
                diary.setDiary_details(cursor.getString(1));
                //adding user to list
                profileList.add(diary);
            }while (cursor.moveToNext());
        }
        cursor.close();
        //return userlist
        return profileList;
    }
    int getChapterId(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_DIARY,
                new String[]{KEY_CHAPTER_ID,KEY_DIARY_DETAIL},
                KEY_CHAPTER_ID +"= ?",new String[]{String.valueOf(id)},null,null,null
        );
        if (cursor != null)
            cursor.moveToFirst();
        Diary diary = new Diary(Integer.parseInt(cursor.getString(0)),cursor.getString(1));
        cursor.close();
        return diary.getChapter_no();
    }
}


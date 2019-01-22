package com.application.aayush.geeta;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.application.aayush.geeta.DiaryDatabaseHandler.KEY_CHAPTER_ID;
import static com.application.aayush.geeta.DiaryDatabaseHandler.TABLE_DIARY;

/**
 * Created by Praveen K S
 */

public class DataBaseHandlerShloka extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String TABLE_SHLOKA = "shlokas_12";
    private static final String DATABASE_NAME = "shloka_manager11";
    private static final String KEY_SHLOKA_ID = "id";
    private static final String KEY_SHLOKA_DETAIL = "verse";
    private static final String KEY_SHLOKA_TRANSLATION = "translation";
    private static final String KEY_SHLOKA_PURPOSE = "purpose";
    private static final String KEY_CHAPTER_ID = "chapter_id";
    private static final String KEY_SHLOKA_ACCESS_FLAG = "access_flag";
    private static final String KEY_SHLOKA_READ_FLAG = "read_flag";
    public DataBaseHandlerShloka(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        //3rd argument to be passed is cursorFactory instance
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SHLOKA_TABLE = "CREATE TABLE " + TABLE_SHLOKA +"("
                +KEY_SHLOKA_ID +" INTEGER PRIMARY KEY,"
                + KEY_SHLOKA_DETAIL + " TEXT,"
                + KEY_SHLOKA_TRANSLATION + " TEXT,"
                + KEY_SHLOKA_PURPOSE + " TEXT,"
                + KEY_CHAPTER_ID + " INTEGER DEFAULT 0,"
                + KEY_SHLOKA_ACCESS_FLAG + " INTEGER DEFAULT 0,"
                + KEY_SHLOKA_READ_FLAG + " INTEGER DEFAULT 0,"
                + " FOREIGN KEY("+KEY_CHAPTER_ID+") REFERENCES "+TABLE_DIARY+"("+KEY_CHAPTER_ID+"));";
        db.execSQL(CREATE_SHLOKA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHLOKA);

        // Create tables again
        onCreate(db);

    }
    //adding a new shloka in db
    public void addShloka(Shlokas shlokas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SHLOKA_DETAIL,shlokas.getVerse_details());
        values.put(KEY_SHLOKA_TRANSLATION,shlokas.getVerse_translation());
        values.put(KEY_SHLOKA_PURPOSE,shlokas.getVerse_purpose());
        values.put(KEY_SHLOKA_ACCESS_FLAG,shlokas.getAccessFlag());
        values.put(KEY_SHLOKA_READ_FLAG,shlokas.getReadFlag());
        //inserting record
        db.insert(TABLE_SHLOKA,null,values);
        db.close();//closing database connection
    }

    //get a single shloka
    Shlokas getsholka(int id){
        Shlokas shlokas;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_SHLOKA,
                new String[]{KEY_SHLOKA_ID,KEY_SHLOKA_DETAIL,KEY_SHLOKA_TRANSLATION,KEY_SHLOKA_PURPOSE,KEY_CHAPTER_ID,KEY_SHLOKA_ACCESS_FLAG,KEY_SHLOKA_READ_FLAG},
                KEY_SHLOKA_ID +"= ?",new String[]{String.valueOf(id)},null,null,null
        );
        if(cursor == null) {
            return null;
        }
        cursor.moveToNext();
        shlokas = new Shlokas(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5)),Integer.parseInt(cursor.getString(6)));
        cursor.close();
     /*   if (cursor != null && cursor.getCount() > 0) {
           cursor.moveToFirst();
                shlokas = new Shlokas(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),Integer.parseInt(cursor.getString(4)));

        }*/

            db.close();
        return shlokas;
    }
    //get chapter id
    int getChapterId(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_SHLOKA,
                new String[]{KEY_SHLOKA_ID,KEY_SHLOKA_DETAIL,KEY_SHLOKA_TRANSLATION,KEY_SHLOKA_PURPOSE,KEY_CHAPTER_ID},
                KEY_SHLOKA_ID +"= ?",new String[]{String.valueOf(id)},null,null,null
        );
        if (cursor != null)
            cursor.moveToFirst();
        Shlokas shlokas = new Shlokas(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),Integer.parseInt(cursor.getString(4)));
        cursor.close();
        return shlokas.getChapter_id();
    }
    //get Access Flag
    int getaccessFlag(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_SHLOKA,
                new String[]{KEY_SHLOKA_ID,KEY_CHAPTER_ID,KEY_SHLOKA_ACCESS_FLAG},
                KEY_SHLOKA_ID +"=?",new String[]{String.valueOf(id)},null,null,null
                );
        if (cursor != null)
            cursor.moveToFirst();
        //Shlokas shlokas = new Shlokas(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),Integer.parseInt(cursor.getString(5)));
        Shlokas shlokas  = new Shlokas(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))),Integer.parseInt(cursor.getString(cursor.getColumnIndex("chapter_id"))),Integer.parseInt(cursor.getString(cursor.getColumnIndex("access_flag"))));
        cursor.close();
        return shlokas.getAccessFlag();
    }

    //get Read Flag
    int getReadFlag(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_SHLOKA,
                new String[]{KEY_SHLOKA_ID,KEY_CHAPTER_ID,KEY_SHLOKA_ACCESS_FLAG,KEY_SHLOKA_READ_FLAG},
                KEY_SHLOKA_ID +"=?",new String[]{String.valueOf(id)},null,null,null
        );
        if (cursor != null)
            cursor.moveToFirst();
        //Shlokas shlokas = new Shlokas(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),Integer.parseInt(cursor.getString(5)));
        Shlokas shlokas  = new Shlokas(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))),Integer.parseInt(cursor.getString(cursor.getColumnIndex("chapter_id"))),Integer.parseInt(cursor.getString(cursor.getColumnIndex("access_flag"))),Integer.parseInt(cursor.getString(cursor.getColumnIndex("read_flag"))));
        cursor.close();
        return shlokas.getReadFlag();
    }

    //set access flag
    public int updateAccessFlag(Shlokas shlokas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(KEY_SHLOKA_ID,shlokas.getId());
        values.put(KEY_CHAPTER_ID,shlokas.getChapter_id());
        values.put(KEY_SHLOKA_ACCESS_FLAG,shlokas.getAccessFlag());
        //updating row
  //      String mysql = "Update "+TABLE_SHLOKA+" set "+KEY_SHLOKA_ACCESS_FLAG+"='"+shlokas.getAccessFlag()+"'" +" where "+KEY_SHLOKA_ID+" = "+shlokas.getId()+" and "+KEY_CHAPTER_ID+" ="+shlokas.getChapter_id();
//        db.execSQL(mysql);

       //return db.update(TABLE_SHLOKA,values,KEY_SHLOKA_ID +"=?",new String[]{String.valueOf(shlokas.getId())});
        //return db.update(TABLE_SHLOKA,values,KEY_SHLOKA_ID +"=?",new String[{String.valueOf(shlokas.getId()),String.valueOf(shlokas.getChapter_id())}]);
        return db.update(TABLE_SHLOKA,values,"id =? ",new String[]{String.valueOf(shlokas.getId())});
    }
    public  int updateReadFlag(Shlokas shlokas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(KEY_SHLOKA_ID,shlokas.getId());
        values.put(KEY_CHAPTER_ID,shlokas.getChapter_id());
        values.put(KEY_SHLOKA_ACCESS_FLAG,shlokas.getAccessFlag());
        values.put(KEY_SHLOKA_READ_FLAG,shlokas.getReadFlag());
        //updating row
        return db.update(TABLE_SHLOKA,values,KEY_SHLOKA_ID +"=?",new String[]{String.valueOf(shlokas.getId())});
    }


    public int updateSholka(Shlokas shlokas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(KEY_SHLOKA_DETAIL,shlokas.getVerse_details());
        values.put(KEY_SHLOKA_TRANSLATION,shlokas.getVerse_translation());
        values.put(KEY_SHLOKA_PURPOSE,shlokas.getVerse_purpose());
        values.put(KEY_CHAPTER_ID,shlokas.getChapter_id());

        //updating row
        return db.update(TABLE_SHLOKA,values,KEY_SHLOKA_ID +"=?",new String[]{String.valueOf(shlokas.getId())});
    }
    public int addChapter(Shlokas shlokas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(KEY_CHAPTER_ID,shlokas.getChapter_id());
        //updating row
        return db.update(TABLE_SHLOKA,values,KEY_SHLOKA_ID +"=?",new String[]{String.valueOf(shlokas.getId())});
    }
    public int userCount(){
        String countQuery = "SELECT * FROM "+TABLE_SHLOKA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        if(cursor == null){
            return  0;
        }
        int count = cursor.getCount();
        cursor.close();
        return  count;
    }
    //code to get all contacts in a listview
    public List<Shlokas> getAllShlokas(){
        List<Shlokas> shlokasList = new ArrayList<Shlokas>();
        //select all query

        String selectQuery = "SELECT * FROM " +TABLE_SHLOKA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        //Looping trough all rows and adding to list
        if (cursor.moveToFirst()){
            do {
                Shlokas shlokas = new Shlokas();
                shlokas.setId(Integer.parseInt(cursor.getString(0)));
                shlokas.setVerse_details(cursor.getString(1));
                shlokas.setVerse_translation(cursor.getString(2));
                shlokas.setVerse_purpose(cursor.getString(3));
                shlokas.setChapter_id(Integer.parseInt(cursor.getString(4)));
                shlokas.setAccessFlag(Integer.parseInt(cursor.getString(5)));
                shlokas.setReadFlag(Integer.parseInt(cursor.getString(6)));
                //adding shlokas to list
                shlokasList.add(shlokas);
            }while (cursor.moveToNext());
        }
        cursor.close();
        //return userlist
        return shlokasList;
    }
}
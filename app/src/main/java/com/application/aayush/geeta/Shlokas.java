package com.application.aayush.geeta;

/**
 * Created by Aayush on 11/1/2017.
 */

public class Shlokas {
    private int id;
    private String verse_details;
    private String verse_translation;
    private String verse_purpose;
    private int chapter_id;
    private int accessFlag;
    private int readFlag;

    public Shlokas(int id, String verse_details, String verse_translation, String verse_purpose, int chapter_id, int accessFlag, int readFlag) {
        this.id = id;
        this.verse_details = verse_details;
        this.verse_translation = verse_translation;
        this.verse_purpose = verse_purpose;
        this.chapter_id = chapter_id;
        this.accessFlag = accessFlag;
        this.readFlag = readFlag;
    }

    public Shlokas(int id, int chapter_id, int accessFlag, int readFlag) {
        this.id = id;
        this.chapter_id = chapter_id;
        this.accessFlag = accessFlag;
        this.readFlag = readFlag;
    }

    public int getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(int accessFlag) {
        this.accessFlag = accessFlag;
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
    }

    public Shlokas() {
    }

    public Shlokas(int id, String verse_details, String verse_translation, String verse_purpose) {
        this.id = id;
        this.verse_details = verse_details;
        this.verse_translation = verse_translation;
        this.verse_purpose = verse_purpose;
    }

    public Shlokas(int id, String verse_details, String verse_translation, String verse_purpose, int chapter_id, int accessFlag) {
        this.id = id;
        this.verse_details = verse_details;
        this.verse_translation = verse_translation;
        this.verse_purpose = verse_purpose;
        this.chapter_id = chapter_id;
        this.accessFlag = accessFlag;
    }

    public Shlokas(int id, int chapter_id) {
        this.id = id;
        this.chapter_id = chapter_id;
    }
    public Shlokas(int id, String verse_details, String verse_translation, String verse_purpose, int chapter_id) {
        this.id = id;
        this.verse_details = verse_details;
        this.verse_translation = verse_translation;
        this.verse_purpose = verse_purpose;
        this.chapter_id = chapter_id;
    }

    public Shlokas(String verse_details, String verse_translation, String verse_purpose) {
        this.verse_details = verse_details;
        this.verse_translation = verse_translation;
        this.verse_purpose = verse_purpose;
    }

    public Shlokas(String verse_details, String verse_translation, String verse_purpose,int accessFlag) {
        this.verse_details = verse_details;
        this.verse_translation = verse_translation;
        this.verse_purpose = verse_purpose;
        this.accessFlag = accessFlag;
    }
   
    public Shlokas(int id,int chapter_id,int accessFlag) {
        this.id = id;
        this.chapter_id = chapter_id;
        this.accessFlag = accessFlag;
    }

    public int getId() {
        return id;
    }

    public String getVerse_details() {
        return verse_details;
    }

    public String getVerse_translation() {
        return verse_translation;
    }

    public String getVerse_purpose() {
        return verse_purpose;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setVerse_details(String verse_details) {
        this.verse_details = verse_details;
    }


    public void setVerse_translation(String verse_translation) {
        this.verse_translation = verse_translation;
    }

    public void setVerse_purpose(String verse_purpose) {
        this.verse_purpose = verse_purpose;
    }

    public int getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(int readFlag) {
        this.readFlag = readFlag;
    }
}

package com.application.aayush.geeta;

import android.app.Activity;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Aayush on 11/2/2017.
 */

public class ReadJSON extends Activity {
    protected void readData() throws IOException, JSONException {
        InputStream inputStream = getAssets().open("shloka_details.json");
        Scanner sc = new Scanner(inputStream);
        StringBuilder builder = new StringBuilder();
        while(sc.hasNextLine()){
            builder.append(sc.nextLine());
        }
        parseJSON(builder.toString());
    }
    private void parseJSON(String s ) throws JSONException {
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        StringBuilder stringBuilder3 = new StringBuilder();

        JSONObject root = new JSONObject(s);
        JSONObject sholka = root.getJSONObject("shlokas");
        JSONArray items = sholka.getJSONArray("shlokas");
        for(int i = 0;i<items.length();i++){
            JSONObject item = items.getJSONObject(i);
            stringBuilder1.append(item.getString("verse"));
            stringBuilder2.append(item.getString("translation"));
            stringBuilder3.append(item.getString("purpose"));

        }
    }
}

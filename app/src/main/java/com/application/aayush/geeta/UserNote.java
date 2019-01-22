package com.application.aayush.geeta;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static java.lang.System.out;

/**
 * Created by Aayush on 6/12/2018.
 */

public class UserNote extends AppCompatActivity implements View.OnClickListener {
    EditText note;
    Button save;
    ImageButton back;
    Bundle bundle;
    String chapter_no = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        bundle = getIntent().getExtras();
        chapter_no = bundle.getString("chapter_number");
        note = (EditText)findViewById(R.id.editText3);
        save = (Button)findViewById(R.id.button10);
        back = (ImageButton)findViewById(R.id.button41);
        note.setText("What do you learn from today's Chapter...");

        back.setOnClickListener(UserNote.this);
        save.setOnClickListener(UserNote.this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button10:onSave(note,chapter_no);
                break;
            case R.id.button41:
                UserNote.super.onBackPressed();
                break;
            default:
                break;
        }
    }
    private void onSave(final EditText editText,String chapter_no){
        String data_to_be_saved = editText.getText().toString();
        saveData(data_to_be_saved,chapter_no);
        editText.setText(data_to_be_saved);
       // diary_no.setText(chapter.getText().toString());*/
    }
    private void saveData(String data_to_be_saved, String chapter) {
        DiaryDatabaseHandler diaryDatabaseHandler = new DiaryDatabaseHandler(this);
        DataBaseHandlerShloka dataBaseHandlerShloka = new DataBaseHandlerShloka(this);
        int chapter_no = Integer.parseInt(chapter);
        diaryDatabaseHandler.addContent(new Diary(chapter_no,data_to_be_saved));
        int v = dataBaseHandlerShloka.addChapter(new Shlokas(chapter_no,chapter_no));
        List<Diary> profileList = diaryDatabaseHandler.getAllUsers();
        for (Diary up:profileList){
            String log = "ID:"+up.getChapter_no()+",Name:"+up.getDiary_details();
            Log.d("Diary Entry :",log);
        }
        out.print("**************"+v);
        //edit.setVisibility(View.VISIBLE);
        save.setVisibility(View.GONE);

    }
}
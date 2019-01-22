package com.application.aayush.geeta;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Chapter extends Fragment {

    Toolbar toolbar;
    ImageButton back;
    Button save,notification,cancel;
    TextView title,subtitle;
    EditText chapter_content;
    String name,email,address,city,mobile_number,chapter_no,data;
    Bundle bundle;
    public Chapter() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chapter, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        bundle = new Bundle();
        back = (ImageButton) view.findViewById(R.id.button14);
        toolbar = (Toolbar)view.findViewById(R.id.my_toolbar);
        chapter_no = getArguments().getString("chapter_no");
        data = getArguments().getString("diary_data");
        name = getArguments().getString("user_name");
        mobile_number = getArguments().getString("user_mobilenumber");
        email = getArguments().getString("user_email");
        address = getArguments().getString("user_address");
        city = getArguments().getString("user_city");
        title = (TextView)view.findViewById(R.id.toobar_title);
        subtitle = (TextView)view.findViewById(R.id.textView30);
        chapter_content = (EditText) view.findViewById(R.id.edittext39);
        save = (Button) view.findViewById(R.id.button13);
        cancel = (Button) view.findViewById(R.id.button12);
        notification = (Button) view.findViewById(R.id.notification_button1);
        title.setText("Chapter"+" "+chapter_no);
        subtitle.setText("Chapter"+" "+chapter_no);
        chapter_content.setText(data);
        bundle.putString("user_name",name);
        bundle.putString("user_mobilenumber",mobile_number);
        bundle.putString("user_email",email);
        bundle.putString("user_address",address);
        bundle.putString("user_city",city);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),NotificationActivity.class);
                intent.putExtra("user_name",name);
                intent.putExtra("user_mobilenumber",mobile_number);
                intent.putExtra("user_email",email);
                intent.putExtra("user_address",address);
                intent.putExtra("user_city",city);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().show();
                getActivity().onBackPressed();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data_to_be_saved = chapter_content.getText().toString();
                //Toast.makeText(UserMenu.this,"Inside save of if first part of Front button",Toast.LENGTH_SHORT).show();
                saveData(data_to_be_saved,chapter_no);
                getActivity().onBackPressed();


            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }

    private void saveData(String data_to_be_saved, String chapter_no) {
        DiaryDatabaseHandler diaryDatabaseHandler = new DiaryDatabaseHandler(Chapter.this.getContext());
        int chapter = Integer.parseInt(chapter_no);
        //diaryDatabaseHandler.addContent(new Diary(chapter,data_to_be_saved));
        diaryDatabaseHandler.UpdateContent(new Diary(chapter,data_to_be_saved));
        List<Diary> profileList = diaryDatabaseHandler.getAllUsers();
        for (Diary up:profileList){
            String log = "ID:"+up.getChapter_no()+",Name:"+up.getDiary_details();
            //writing users to the list
            Log.d("Diary Entry :",log);
        }
        Toast.makeText(Chapter.this.getContext(),"Updated",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDetach() {
        super.onDetach();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

    }
}

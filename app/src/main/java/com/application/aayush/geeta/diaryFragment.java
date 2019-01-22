package com.application.aayush.geeta;


import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class diaryFragment extends Fragment {

    Toolbar toolbar;
    String name,mobile_number,email,address,city,chapter_no,data;
    Button notify;
    ImageButton back;
    private RecyclerView recyclerView;
    private DiaryAdapter diaryAdapter;
    DiaryDatabaseHandler diaryDatabaseHandler;
    DataBaseHandlerShloka db;
    Bundle bundle;
    public diaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_diary, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view3);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        toolbar = (Toolbar)view.findViewById(R.id.my_toolbar);
        name = getArguments().getString("user_name");
        mobile_number = getArguments().getString("user_mobilenumber");
        email = getArguments().getString("user_email");
        address = getArguments().getString("user_address");
        city = getArguments().getString("user_city");
        chapter_no = getArguments().getString("chapter_no");
        data = getArguments().getString("diary_data");
        back = (ImageButton) view.findViewById(R.id.button14);
        diaryAdapter = new DiaryAdapter(getActivity(),getData());
        recyclerView.setAdapter(diaryAdapter);
        bundle = new Bundle();
        bundle.putString("user_name",name);
        bundle.putString("user_mobilenumber",mobile_number);
        bundle.putString("user_email",email);
        bundle.putString("user_address",address);
        bundle.putString("user_city",city);



        diaryAdapter.setOnItemClickListener(new DiaryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(diaryFragment.this.getContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEditOnClick(int position) {
                View view = recyclerView.getLayoutManager().findViewByPosition(position);
                TextView chapter_no1 = (TextView) view.findViewById(R.id.textView36);
                TextView chapter_content1 = (TextView)view.findViewById(R.id.textView35);
                Chapter chapter1 = new Chapter();
                FragmentManager fragmentManager1 = getChildFragmentManager();
                bundle.putString("chapter_no",chapter_no1.getText().toString());
                bundle.putString("diary_data",chapter_content1.getText().toString());
                chapter1.setArguments(bundle);
               if (fragmentManager1.getBackStackEntryCount() > 0) {
                   fragmentManager1.popBackStack();}

                fragmentManager1
                        .beginTransaction()
                        .addToBackStack(chapter1.getTag())
                        .replace(R.id.diary_layout,chapter1,chapter1.getTag())
                        .commit();

            }

            @Override
            public void onDeleteOnClick(int position,List<DiaryContents> contentsList) {
                View view = recyclerView.getLayoutManager().findViewByPosition(position);
                TextView chapter_no = (TextView) view.findViewById(R.id.textView36);
                TextView chapter_content = (TextView)view.findViewById(R.id.textView35);
                DataBaseHandlerShloka dataBaseHandlerShloka = new DataBaseHandlerShloka(diaryFragment.this.getContext());
                DiaryDatabaseHandler diaryDatabaseHandler = new DiaryDatabaseHandler(diaryFragment.this.getContext());
                int ch = Integer.parseInt(chapter_no.getText().toString());
                dataBaseHandlerShloka.addChapter(new Shlokas(ch,0));
                boolean ret = diaryDatabaseHandler.deleteContent(ch);
                try {
                    contentsList.remove(position);
                    diaryAdapter.notifyItemRemoved(position);
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
//                Toast.makeText(diaryFragment.this.getContext(),chapter_no1.getText().toString()+":"+chapter_content1.getText().toString(),Toast.LENGTH_SHORT).show();
            }

        });
        //Toast.makeText(diaryFragment.this.getContext(),chapter_no+":"+data,Toast.LENGTH_SHORT).show();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().show();

                getActivity().onBackPressed();

            }
        });
        notify = (Button)view.findViewById(R.id.notification_button1);
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        notify.setOnClickListener(new View.OnClickListener() {
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

     return view;
    }

    public List<DiaryContents> getData() {
        List<DiaryContents>  list = new ArrayList<>();

        diaryDatabaseHandler = new DiaryDatabaseHandler(diaryFragment.this.getContext());
        db = new DataBaseHandlerShloka(diaryFragment.this.getContext());
        for(int i = 1,j=1;j<=db.userCount();i++,j++){
            DiaryContents current = new DiaryContents();
            Shlokas shlokas = db.getsholka(i);
            int chapter_id = db.getChapterId(i);
            if (chapter_id == 0) {
                continue;
            }
            Diary diary = diaryDatabaseHandler.getContent(chapter_id);

            current.chapter_no = String.valueOf(diary.getChapter_no());
            current.chapter_content = diary.getDiary_details();
            list.add(current);
        }

      /*  Toast.makeText(diaryFragment.this.getContext(),String.valueOf(diaryDatabaseHandler.contentCount()),Toast.LENGTH_SHORT).show();*/
        return list;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }
}

package com.application.aayush.geeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Aayush on 11/12/2017.
 */

public class DiaryDetails extends Fragment {
    private RecyclerView recyclerView;
    private ViewAdapter adapter;
    Toolbar toolbar;
    ImageButton back;
    Bundle bundle;
    String name,mobile_number,email,address,city;

    public DiaryDetails(){
}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.diary_details_layout, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view1);
        toolbar = (Toolbar)view.findViewById(R.id.my_toolbar);
        bundle = new Bundle();
        toolbar = (Toolbar)view.findViewById(R.id.my_toolbar);
        /*name = getIntent().getStringExtra("user_name");
        mobile_number = getIntent().getStringExtra("user_mobilenumber");
        email = getIntent().getStringExtra("user_email");
        address = getIntent().getStringExtra("user_address");
        city = getIntent().getStringExtra("user_city");
*/
        back = (ImageButton) view.findViewById(R.id.button14);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),UserMenu.class);
  /*              intent.putExtra("user_name",name);
                intent.putExtra("user_mobilenumber",mobile_number);
                intent.putExtra("user_email",email);
                intent.putExtra("user_address",address);
                intent.putExtra("user_city",city);
  */              startActivity(intent);

            }
        });
       // adapter = new ViewAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }
}
package com.application.aayush.geeta;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aayush on 10/30/2017.
 */

public class DynamicallyAddReminder extends Fragment {
    Bundle bundle;
    private RecyclerView recyclerView;
    private ViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.dynamic_layout_reminder, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view1);

        int hour = getArguments().getInt("hour");
        int minute = getArguments().getInt("minute");
        Toast.makeText(DynamicallyAddReminder.this.getContext(),hour+":"+minute, Toast.LENGTH_SHORT).show();

        adapter = new ViewAdapter(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
    public static List<Item> getData(){
        List<Item> data = new ArrayList<>();
        String[] text = {"10:30","9:40","8:50"};
        for(int i=0;i<text.length;i++){
            Item current = new Item();
            current.time = text[i];
            data.add(current);
        }
        return data;
    }
}
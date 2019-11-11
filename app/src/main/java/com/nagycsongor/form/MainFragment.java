package com.nagycsongor.form;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class MainFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FormModel> form;
    public MainFragment() {
        form = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //name
        FormModel t = new TextFormModel();
        form.add(t);
        //location
        FormModel s= new SpinnerFormModel(R.array.Locations,"Location:");
        form.add(s);
        //gender
        FormModel rb = new RadioButtonFormModel("Gender");
        form.add(rb);
        FormModel cb = new CheckBoxFormModel("Hobbies");
        form.add(cb);
        //department
        FormModel s1= new SpinnerFormModel(R.array.Department,"Department:");
        form.add(s1);



        FormModel b = new ButtonFormModel();
        form.add(b);

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new FormAdapter(form, getContext());
        recyclerView.setAdapter(mAdapter);
        return view;
    }

}

package com.shamim.recipeapps;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewImage_food_item extends Fragment implements View.OnClickListener {
    RecyclerView objectRecyclerView;
   private DatabaseHelper_Food_Item objectDatabaseHelper;
    private  RVAdapter_Food_Item objectRvAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_view_image_food_item, container, false);

        objectRecyclerView= view.findViewById(R.id.imageRV);

        objectDatabaseHelper = new DatabaseHelper_Food_Item(getActivity());
        objectRvAdapter = new RVAdapter_Food_Item(getContext(), objectDatabaseHelper.getallImageData());
        objectRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        objectRecyclerView.setAdapter(objectRvAdapter);



    return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

        }
    }
}
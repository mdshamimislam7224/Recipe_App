package com.shamim.recipeapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    FrameLayout itemdetailscontainer;
    private static final String TAG = "Detailactivit";
    ArrayList<Model_Class_Food_Item> foodItems = new ArrayList<>();
    String foodTitle;
    int position = 0;
    Button next, back;
    int id;
    DatabaseHelper_Food_Item databaseHelper_food_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);


        itemdetailscontainer = findViewById(R.id.itemdetailscontainer);

        databaseHelper_food_item = new DatabaseHelper_Food_Item(this);

        next = findViewById(R.id.detailnext);
        back = findViewById(R.id.detailprevious);

        Bundle bundle = getIntent().getExtras();
        foodTitle = bundle.getString("Foodtitle");
        id = bundle.getInt("id");
        position = bundle.getInt("position");
        foodItems = databaseHelper_food_item.getdataidbysubtitle(foodTitle);


        Log.d(TAG, "Foodtitle=" + foodTitle + " Position=" + position + " Size=" + foodItems.size());

        if (savedInstanceState == null) {
            loadFragment(position);
        }

        next.setOnClickListener(this);
        back.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.detailnext:
                if (position < (foodItems.size() - 1)) {
                    position++;
                    loadFragment(position);
                }

                break;

            case R.id.detailprevious:
                if (position > 0) {
                    position--;
                    loadFragment(position);
                }
                break;

        }

    }

    private void loadFragment(int position) {
        Model_Class_Food_Item foodItem = foodItems.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", foodItem.getId());

        ItemDetailsFragment itemDetailsFragment = new ItemDetailsFragment();
        itemDetailsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.itemdetailscontainer, itemDetailsFragment).commit();
    }

}
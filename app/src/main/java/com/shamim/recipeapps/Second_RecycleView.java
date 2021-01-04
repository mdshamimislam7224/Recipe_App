package com.shamim.recipeapps;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Second_RecycleView extends AppCompatActivity {
    private TextView birani_text, text;
    ImageView setimagefromfirstRV;
    private RecyclerView recyclerViewtext;
    DatabaseHelper_Food_Item database_helper;
    private RV_Adapter_2nd objectRvAdapter;
    String item = null;
    Bitmap image;
    int id;

   String Foodtitle;
    ArrayList<Model_Class_Food_Item> food_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second__recycle_view);
        text = findViewById(R.id.text);


        recyclerViewtext = findViewById(R.id.RVsecond);
        setimagefromfirstRV = findViewById(R.id.setimagefromfirstRV);
        database_helper = new DatabaseHelper_Food_Item(this);

        Bundle bundle = getIntent().getExtras();



        if (bundle != null) {
            id = bundle.getInt("id");
            Foodtitle=bundle.getString("Foodtitle");
            image = database_helper.getimage(id);

            item = database_helper.getdatabyid(id);

            food_items = database_helper.getdataidbysubtitle(Foodtitle);


            objectRvAdapter = new RV_Adapter_2nd(this, food_items);
            recyclerViewtext.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewtext.setAdapter(objectRvAdapter);

            if (image != null && item != null) {

                setimagefromfirstRV.setImageBitmap(image);
                text.setText(item);




            }

        else {
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();

            }



        /*objectDatabaseHelper = new DatabaseHelper_Food_Item(this);
        objectRvAdapter = new RVAdapter_Food_Item(this, objectDatabaseHelper.getmatch());
        recyclerViewtext.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewtext.setAdapter(objectRvAdapter);*/

       /* Bundle bundle= getIntent().getExtras();
        if(bundle!=null)
        {
            birani_text.setText(bundle.getString("name"));

        }*/




        /*//recycleView.findViewById(R.id.RecyclerView_beef);
        //recyclerViewbeef =findViewById(R.id.RecyclerView_beef);



            RecyclerView mRecycler = (RecyclerView) findViewById(R.id.RVsecond);

            mRecycler.setLayoutManager(new LinearLayoutManager(this));

            RV_Adapter_2nd adapt = new RV_Adapter_2nd(this,Bus_Name);

            mRecycler.setAdapter(adapt);*/


        }
    }
}
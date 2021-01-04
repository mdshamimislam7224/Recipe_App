package com.shamim.recipeapps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemDetailsFragment extends Fragment implements View.OnClickListener {
    TextView detailtext, detailequipment, detailprocessing;
    ImageView detailimage;
    int id;
    String Equipment, EquipmentID;
    String item;

    ArrayList<Model_Class_Food_Item> changevalue;
    private int ChangeCounter;
    private int changeCountTotal;
    private Model_Class_Food_Item currentchange;

    DatabaseHelper_Food_Item database_helper;
    Bitmap image;
    private static final String TAG = "Detailactivit";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_item_details,container,false);

        detailtext = view.findViewById(R.id.detailtext);
        detailequipment = view.findViewById(R.id.detailequipment);
        detailprocessing = view.findViewById(R.id.detailprocessing);
        detailimage = view.findViewById(R.id.deatilimage);




        database_helper = new DatabaseHelper_Food_Item(getActivity());
        changevalue= new ArrayList<>();
        changevalue=database_helper.getalldata();
        changeCountTotal=changevalue.size();


        Bundle bundle = this.getArguments();


        if (bundle != null) {

            id = bundle.getInt("id");
            Equipment = database_helper.getdatabyidequiment(id);
            EquipmentID = database_helper.getdatabyidprocesing(id);
            image = database_helper.getimage(id);
            item = database_helper.getdatabyid(id);


            if (image != null && item != null && Equipment != null && EquipmentID != null) {
                detailequipment.setText(Equipment);
                detailprocessing.setText(EquipmentID);
                detailtext.setText(item);
                detailimage.setImageBitmap(image);
                Log.d(TAG, "id=" + id);
                Log.d(TAG, "Image=" + image);


            }


        }


        return view;
    }


    @Override
    public void onClick(View v) {





    }
}
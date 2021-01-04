package com.shamim.recipeapps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class Add_Food_Item_Activity extends AppCompatActivity implements View.OnClickListener {
    private Button saveimage,viewimage;
    private EditText imgeDetailsET,foodtitle,equipment,processing,subtitle;
    private ImageView objectimageView;
    private static  final int PICK_IMAGE_Request=99;
    private Uri ImageFilePath;
    private Bitmap ImageStore;
    DatabaseHelper_Food_Item databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__food__item_);


        imgeDetailsET=findViewById(R.id.imagename);
        objectimageView=findViewById(R.id.image);
        saveimage=findViewById(R.id.saveimage);
        viewimage=findViewById(R.id.viewimage);

        foodtitle = findViewById(R.id.foodtitle);
        equipment = findViewById(R.id.equipment);
        processing = findViewById(R.id.processing);
        subtitle= findViewById(R.id.subtitle);

        databaseHelper= new DatabaseHelper_Food_Item(this);

        objectimageView.setOnClickListener(this);
        saveimage.setOnClickListener(this);
        viewimage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.image:
                choose_image();
                break;
            case R.id.saveimage:


                if (!imgeDetailsET.getText().toString().isEmpty() && objectimageView.getDrawable() !=null && ImageStore !=null){

                    databaseHelper.storeImage(imgeDetailsET.getText().toString(),ImageStore,foodtitle.getText().toString()
                    ,equipment.getText().toString(),processing.getText().toString(),subtitle.getText().toString());


                    imgeDetailsET.setText("");
                    foodtitle.setText("");
                    equipment.setText("");
                    processing.setText("");;
                    subtitle.setText("");
                    objectimageView.setImageBitmap(null);
                    //objectimageView.getDrawable().
                    Toast.makeText(this, "Successfully Add Data", Toast.LENGTH_SHORT).show();


                }
                else
                {
                    Toast.makeText(this, "Please Select the Image and Image Name", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.viewimage:
               /* Toast.makeText(this, "View", Toast.LENGTH_SHORT).show();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ViewImage_food_item loginFragment = new ViewImage_food_item();
                transaction.replace(R.id.fragment_container, loginFragment);
                transaction.addToBackStack(null);
                transaction.commit();*/
               Intent intent = new Intent(Add_Food_Item_Activity.this,MainActivity.class);
               startActivity(intent);
                break;







        }
    }



    private void choose_image()
    {
        Intent choose = new Intent();
        choose.setType("image/*");
        choose.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(choose,"Select Image"),PICK_IMAGE_Request);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==PICK_IMAGE_Request  && resultCode == RESULT_OK
                &&  data != null && data.getData() != null )
        {
            Log.d("justimage","image"+requestCode+" "+resultCode);

            ImageFilePath = data.getData();

            try {
                ImageStore = MediaStore.Images.Media.getBitmap(getContentResolver(),ImageFilePath);
                objectimageView.setImageBitmap(ImageStore);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    /*public void ViewImage(View view) {
        Toast.makeText(this, "View", Toast.LENGTH_SHORT).show();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ViewImage_food_item loginFragment = new ViewImage_food_item();
        transaction.replace(R.id.fragment_container, loginFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }*/
}
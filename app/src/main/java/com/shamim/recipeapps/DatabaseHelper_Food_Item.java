package com.shamim.recipeapps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static android.os.Build.ID;

public class DatabaseHelper_Food_Item extends SQLiteOpenHelper {

    //just variable Defined global
    private static final String Database_Name = "Totaldatabase.db";
    private static final String Table_Name = "Recipe_Info";
    private static final String CREATE_tableQuery = "CREATE TABLE Recipe_Info ( id INTEGER PRIMARY KEY AUTOINCREMENT" + ", imageName TEXT" + ",image BLOB, Foodtitle TEXT " + " , SubTitle TEXT " + ", Equipment TEXT" + ",Processing TEXT)";
    private static final String Drop_table = "DROP TABLE IF EXISTS " + Table_Name;
    private static final String SelectAll = "SELECT * FROM " + Table_Name;
    private ByteArrayOutputStream objectOutputStream;
    private byte[] ImageInBytes;
    private static final String TAG = "DatabaseHelper_Food_Ite";
    private static final int Version = 3;

    //Context means from user's Information
    private Context context;

    public DatabaseHelper_Food_Item(@Nullable Context context) {
        super(context, Database_Name, null, Version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_tableQuery);
            Toast.makeText(context, "Table Created Successful", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void storeImage(String imageName, Bitmap bitmap, String Foodtitle, String Equipment, String Processing, String SubTitle) {
        SQLiteDatabase objectsqLiteDatabase = this.getWritableDatabase();
        objectOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, objectOutputStream);
        ImageInBytes = objectOutputStream.toByteArray();
        ContentValues objcetcontentValues = new ContentValues();
        objcetcontentValues.put("imageName", imageName);
        objcetcontentValues.put("image", ImageInBytes);
        objcetcontentValues.put("Foodtitle", Foodtitle);
        objcetcontentValues.put("Equipment", Equipment);
        objcetcontentValues.put("Processing", Processing);
        objcetcontentValues.put("SubTitle", SubTitle);

        long checkquery = objectsqLiteDatabase.insert("Recipe_Info", null, objcetcontentValues);

        if (checkquery != 0) {
            Toast.makeText(context, "Data Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Data is not insert", Toast.LENGTH_SHORT).show();
            // objectsqLiteDatabase.close();
        }

    }

    public ArrayList<Model_Class_Food_Item> getallImageData() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor objectCursor = sqLiteDatabase.rawQuery("select * from Recipe_Info", null);

        return extractData(objectCursor);
    }
    /*public ArrayList<Model_Class_Food_Item> getmatch() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Model_Class_Food_Item> objectModel_classes = new ArrayList<>();
        Cursor objectCursor = sqLiteDatabase.rawQuery("select * from Image_INFO WHERE Recipe_Info LIKE 'bus';", null);


        if (objectCursor.getCount() != -1) {
            while (objectCursor.moveToNext()) {
                byte[] imgebBytes = objectCursor.getBlob(2);
                Bitmap objectBitmap = BitmapFactory.decodeByteArray(imgebBytes, 0, imgebBytes.length);
                objectModel_classes.add(new Model_Class_Food_Item(objectBitmap));


            }
            return objectModel_classes;

        } else {
            Toast.makeText(context, "No Value Exists in Database", Toast.LENGTH_SHORT).show();
        }


        return null;
    }*/


    /*public Integer deletedata(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(Table_Name, "id" + " = ?", new String[]{id});
    }*/


   /* public ArrayList<Model_Class_Food_Item> getmatchsubtitle(String title) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Model_Class_Food_Item> objectModel_classes = new ArrayList<>();
        Cursor objectCursor = sqLiteDatabase.rawQuery("select * from Recipe_Info  ",new String[]{title});


        //Log.d("subtitle","data");

            Log.d("subtitle","data");
            while (objectCursor.moveToNext()) {

                String nameofImage = objectCursor.getString(4);


                objectModel_classes.add(new Model_Class_Food_Item(nameofImage));


            }
            return objectModel_classes;




    }*/

    public String getdatabyid(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor objectCursor = sqLiteDatabase.rawQuery("select * from Recipe_Info WHERE  id=?", new String[]{String.valueOf(id)});

        String model_class_food_item = null;

        if (objectCursor.moveToNext()) {

            model_class_food_item = objectCursor.getString(4);

        }


        return model_class_food_item;
    }


    public Bitmap getimage(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap bt = null;
        Cursor cursor = db.rawQuery("select * from Recipe_Info where id=?", new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            byte[] image = cursor.getBlob(2);
            bt = BitmapFactory.decodeByteArray(image, 0, image.length);
        }
        return bt;
    }


    public ArrayList<Model_Class_Food_Item> getdataidbysubtitle(String Foodtitle) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Recipe_Info Where Foodtitle = '" + Foodtitle + "'", null);


        return extractData(cursor);
    }


    public static ArrayList<Model_Class_Food_Item> extractData(Cursor cursor) {
        ArrayList<Model_Class_Food_Item> objectModel_classes = new ArrayList<>();
        if (cursor.getCount() != -1) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String imagename = cursor.getString(1);
                byte[] image = cursor.getBlob(2);
                Bitmap bt = BitmapFactory.decodeByteArray(image, 0, image.length);
                String foodtitle = cursor.getString(3);
                String subtitle = cursor.getString(4);
                String equipment = cursor.getString(5);
                String processing = cursor.getString(6);


                Model_Class_Food_Item food_item = new Model_Class_Food_Item(id, imagename, bt, foodtitle, subtitle, equipment, processing);

                objectModel_classes.add(food_item);

                Log.d(TAG, "id=" + food_item.getId() + " subtitle=" + food_item.getImagename());


            }
            return objectModel_classes;


        }

        return null;
    }


    public String getdatabyidequiment(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor objectCursor = sqLiteDatabase.rawQuery("select * from Recipe_Info WHERE  id=?", new String[]{String.valueOf(id)});

        String model_class_food_item = null;

        if (objectCursor.moveToNext()) {


            model_class_food_item = objectCursor.getString(5);

        }


        return model_class_food_item;
    }


    public String getdatabyidprocesing(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor objectCursor = sqLiteDatabase.rawQuery("select * from Recipe_Info WHERE  id=?", new String[]{String.valueOf(id)});

        String model_class_food_item = null;

        if (objectCursor.moveToNext()) {


            model_class_food_item = objectCursor.getString(6);

        }


        return model_class_food_item;
    }

    public ArrayList<Model_Class_Food_Item> getalldata() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor objectCursor = sqLiteDatabase.rawQuery("select * from Recipe_Info", null);

        return extractData(objectCursor);
    }
}




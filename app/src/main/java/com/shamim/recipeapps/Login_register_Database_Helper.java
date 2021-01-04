package com.shamim.recipeapps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Login_register_Database_Helper extends SQLiteOpenHelper {

    //just variable Defined global
    private  static  final String Database_Name="sign_in_and_sign_up.db";
    private static  final String Table_Name="Information";
    private static  final String ID="_id";
    private static  final String Username="User_name";
    private static  final String Userpassword = "User_password";
    private static  final String CREATE_table="CREATE TABLE "+Table_Name+" ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Username+" VARCHAR(255),"+Userpassword+" VARCHAR(20));";
    private static final String Drop_table="DROP TABLE IF EXISTS "+Table_Name;
    private  static  final  String SelectAll= "SELECT * FROM "+Table_Name;
    //why we change the version and
    //if we can ot
    private static  final int Version=1;

    //Context means from user's Information
    private Context context;



    public Login_register_Database_Helper(@Nullable Context context) {

        //Question: what is the work of factory? why we give this null

        super(context, Database_Name, null, Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            Toast.makeText(context, "onCreate is created : ", Toast.LENGTH_SHORT).show();
            //just table create quire
            sqLiteDatabase.execSQL(CREATE_table);
        }
        catch (Exception e)
        {
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
       try {
           Toast.makeText(context, "onUpgrade is called", Toast.LENGTH_SHORT).show();
           sqLiteDatabase.execSQL(Drop_table);
           onCreate(sqLiteDatabase);
       }
       catch (Exception e)
       {
           
       }

    }
    public long insertData(String uname,String upass)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Username,uname);
        contentValues.put(Userpassword,upass);

       long rowid= sqLiteDatabase.insert(Table_Name,null,contentValues);
       return rowid;
    }

    /*public Cursor Display()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SelectAll,null);
        return cursor;
    }*/

    /*public  boolean Udatedata(String id,String Foodname,String Food_title, String Food_price)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(Food_Name,Foodname);
        contentValues.put(Food_Title,Food_title);
        contentValues.put(Food_Price,Food_price);
        sqLiteDatabase.update(Table_Name,contentValues,ID+" = ?",new String[]{id});

        return true;
    }
    public Integer deletedata(String id)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       return sqLiteDatabase.delete(Table_Name,ID + " = ?" ,new String[]{id});
    }


    public  Cursor showallData()
    {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM "+Table_Name,null);
        return cursor;
    }*/
  public long   insertdata (Signup_details signup_details)
  {
      SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();
      ContentValues values= new ContentValues();
      values.put(Username,signup_details.getName());
      values.put(Userpassword,signup_details.getPassword());
      long rowid= sqLiteDatabase.insert(Table_Name,null,values);
  return rowid;
  }


  public  Boolean matchdata(String uname,String upass)
  {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    Cursor cursor =sqLiteDatabase.rawQuery("SELECT * FROM "+ Table_Name,null);

    Boolean result= false;
    if (cursor.getCount()==0)
    {
        Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show();
    }
    else {
        while (cursor.moveToNext())
        {
            String username= cursor.getString(1);
            String userpassword=cursor.getString(2);
            if (username.equals(uname) && userpassword.equals(upass))
            {
                result=true;
                break;
            }
        }
    }
    return result;
  }

}

package com.shamim.recipeapps;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class Login_Register extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Login_register_Database_Helper database_helper;
    TextView textView;
    EditText uname, upassword;
    Button signup, login, donothaveaccunt, haveaccunt, cancel;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__register);

        database_helper = new Login_register_Database_Helper(this);
        SQLiteDatabase sqLiteDatabase = database_helper.getWritableDatabase();

        textView = findViewById(R.id.textView);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        donothaveaccunt = findViewById(R.id.donothaveaccunt);
        haveaccunt = findViewById(R.id.havaccount);
        cancel = findViewById(R.id.cancel);
        uname = findViewById(R.id.uname);
        upassword = findViewById(R.id.upassword);

        Logincontain();

        preferences = getSharedPreferences("info", Context.MODE_PRIVATE);
        editor = preferences.edit();

        Boolean isLogged = preferences.getBoolean("isLogged", false);
        if (isLogged) {
            Intent intent = new Intent(Login_Register.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText("Register Page");
        }


        Intent intent = getIntent();
        type = intent.getStringExtra("user");

        /*if (type.equals("Register") )
        {
            textView.setText("Register");
            login.setVisibility(View.GONE);
            signup.setVisibility(View.VISIBLE);

        }

        if (type.equals("Login") )
        {
            textView.setText("Login");
            signup.setVisibility(View.GONE);

            login.setVisibility(View.VISIBLE);
        }*/
        signup.setOnClickListener(this);
        login.setOnClickListener(this);
        cancel.setOnClickListener(this);
        haveaccunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signup.setVisibility(View.GONE);
                haveaccunt.setVisibility(View.GONE);
                donothaveaccunt.setVisibility(View.VISIBLE);
                login.setVisibility(View.VISIBLE);
            }
        });
        donothaveaccunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                textView.setText("Register Page");
                login.setVisibility(View.GONE);
                donothaveaccunt.setVisibility(View.GONE);
                haveaccunt.setVisibility(View.VISIBLE);


                signup.setVisibility(View.VISIBLE);
            }
        });
    }


    private void Logincontain() {
        textView.setText("");
        textView.setText("Login Page");
        signup.setVisibility(View.VISIBLE);
        login.setVisibility(View.GONE);


        donothaveaccunt.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        String username = uname.getText().toString();
        String userpassword = upassword.getText().toString();
        switch (v.getId()) {


            case R.id.cancel:
                Intent cancel = new Intent(Login_Register.this, MainActivity.class);
                startActivity(cancel);
                break;
            case R.id.signup:


                if (username.isEmpty() || userpassword.isEmpty()) {
                    Toast.makeText(this, "Please Enter Your Name and Password", Toast.LENGTH_LONG).show();
                }
                else {

                    Signup_details details = new Signup_details();
                    details.setName(username);
                    details.setPassword(userpassword);

                    long rowid = database_helper.insertdata(details);

                    if (rowid > 0) {

                        Toast.makeText(this, "Sign Up is successful" + "Your ID is:" + rowid, Toast.LENGTH_LONG).show();

                        login.setVisibility(View.VISIBLE);
                        donothaveaccunt.setVisibility(View.VISIBLE);
                        login.setVisibility(View.VISIBLE);
                        signup.setVisibility(View.GONE);
                        uname.setText("");
                        upassword.setText("");
                        haveaccunt.setVisibility(View.GONE);


                    } else {
                        Toast.makeText(this, "Sign Up is not successful", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case R.id.login:
                if (username.isEmpty() || userpassword.isEmpty()) {
                    Toast.makeText(this, "Please Enter Your Name and Password", Toast.LENGTH_LONG).show();
                } else {


                    Boolean result = database_helper.matchdata(username, userpassword);
                    if (result) {

                        preferences = getSharedPreferences("info", Context.MODE_PRIVATE);
                        editor = preferences.edit();
                        editor.putString("namekey", username);
                        editor.putString("password", userpassword);
                        editor.putBoolean("isLogged", true);
                        editor.commit();


                        Intent login = new Intent(Login_Register.this, MainActivity.class);
                        startActivity(login);
                        finish();

                    }
                    else {


                        Toast.makeText(this, "User Name or Password Error", Toast.LENGTH_SHORT).show();
                    }
                    break;

                }

        }

    }
}
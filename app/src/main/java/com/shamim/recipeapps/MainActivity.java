package com.shamim.recipeapps;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        preferences = getSharedPreferences("info", Context. MODE_PRIVATE);
        editor = preferences.edit();
        Log.d(TAG,"Info="+" "+preferences+editor);



        //For Used Drawerlayout (ToolBar)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ViewImage_food_item()).commit();
            navigationView.setCheckedItem(R.id.recipe);
        }

        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addrecipe:
                Intent addrecipe = new Intent(MainActivity.this, Add_Food_Item_Activity.class);
                startActivity(addrecipe);

            case R.id.recipe:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ViewImage_food_item()).commit();
                /*FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Recipe_Recycle loginFragment = new Recipe_Recycle();
                transaction.replace(R.id.fragment_container, loginFragment);
                transaction.addToBackStack(null);
                transaction.commit();*/
                break;
            case R.id.recipe_like:
                Toast.makeText(this, "Recipe", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,Change_Button_navigation.class);
                startActivity(intent);

                break;
            case R.id.recipe_tips:
                gotUrl("https://www.femina.in/bengali/food");
                Toast.makeText(this, "Open browser for github", Toast.LENGTH_SHORT).show();

                break;
            case R.id.recipe_health_tips:
                gotUrl("https://www.femina.in/bengali/health");
                Toast.makeText(this, "Open browser for github", Toast.LENGTH_SHORT).show();
                break;
            case R.id.moreApps:
                gotUrl("https://github.com/mdshamimislam7224?tab=repositories");
                Toast.makeText(this, "Open browser for github", Toast.LENGTH_SHORT).show();
                break;

            case R.id.share:
                Intent sharingInent = new Intent(Intent.ACTION_SEND);
                sharingInent.setType("text/plain");
                String shareBody = "https://github.com/mdshamimislam7224/Recipe_App";
                String shareSubject = "Your Subject here";

                sharingInent.putExtra(Intent.EXTRA_TEXT, shareBody);
                sharingInent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                startActivity(Intent.createChooser(sharingInent, "Share Using"));
                break;
            case R.id.logout:
                SharedPreferences sharedPreferences = getSharedPreferences("info",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLogged",false);
                editor.commit();
                Intent intent1= new Intent(MainActivity.this,Login_Register.class);
                startActivity(intent1);
                Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();
                break;

            case R.id.exit:
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void gotUrl(String s)
    {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
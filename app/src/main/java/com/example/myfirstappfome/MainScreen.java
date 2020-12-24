package com.example.myfirstappfome;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myfirstappfome.Adapters.SaveMovieList;
import com.example.myfirstappfome.Services.MediaService;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import static androidx.navigation.Navigation.findNavController;

/**
 * this class show main screen application with navigation and fragments
 */
public class MainScreen extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private static SaveMovieList saveMovieList ;


    public static void  setAppMovieList(SaveMovieList movieList){
        saveMovieList = movieList;
    }
    public static SaveMovieList getAppMoivieLsist (){
        if(saveMovieList!=null){
            return  saveMovieList;
        }
        else {
            return new SaveMovieList(new ArrayList<>());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_favorite, R.id.nav_random_video ,R.id.aboutAuthor , R.id.Music)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("bundle", saveMovieList);
        MenuItem menuItem = navigationView.getMenu().findItem(R.id.Music); // This is the menu item that contains your switch
        SwitchCompat nawSwitch = menuItem.getActionView().findViewById(R.id.naw_switch);
        Intent i=new Intent(this, MediaService.class);
            nawSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if(isChecked) {
                startService(i);
                }
                else {
                    stopService(i);
                }
            });
    }


//        HomeFragment fragment = new HomeFragment();
//        getRandomVideo getRandomVideo = new getRandomVideo();
//        getRandomVideo.setArguments(bundle);
//        fragment.setArguments(bundle);



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}

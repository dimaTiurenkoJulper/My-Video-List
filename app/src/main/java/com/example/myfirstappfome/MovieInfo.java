package com.example.myfirstappfome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstappfome.DataClasses.MovieFullInfo;
import com.example.myfirstappfome.DataClasses.MyMovie;

import java.util.Objects;

/**
 *this activity show all information about movie object
 */
public class MovieInfo extends AppCompatActivity {
    private static final String MOVIE = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        MyMovie movie = (MyMovie) getIntent().getSerializableExtra(MOVIE);
        TextView textName = (TextView) findViewById(R.id.MovieName);
        TextView textDescription = (TextView) findViewById(R.id.MovieDescription);
        TextView textComment = (TextView) findViewById(R.id.MovieComment);
        ImageView image = (ImageView) findViewById(R.id.MovieImage);
        ImageView favorite = (ImageView) findViewById(R.id.favorite);

        //String name  = getIntent().getStringExtra("name");
        textName.setText(Objects.requireNonNull(movie).getName());
        //String description  = getIntent().getStringExtra("Description");
        textDescription.setText(movie.getDescription());
        //int imag = Objects.requireNonNull(getIntent().getExtras()).getInt("Image");
        //image.setImageBitmap(movie.getImage());
    }

    public void click(View view) {

        Intent intent = new Intent(this, CastsActivity.class);
        intent.putExtra(MOVIE, getIntent().getSerializableExtra(MOVIE));
        startActivity(intent);
    }

}

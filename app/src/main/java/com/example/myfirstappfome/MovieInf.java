package com.example.myfirstappfome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MovieInf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_inf);

        TextView textName = (TextView) findViewById(R.id.MovieName);
        TextView textDescription = (TextView) findViewById(R.id.MovieDescription);
        TextView textComment = (TextView) findViewById(R.id.MovieComment);
        ImageView image = (ImageView) findViewById(R.id.MovieImage);
        ImageView favorite = (ImageView) findViewById(R.id.favorite);

        String name  = getIntent().getStringExtra("name");
        textName.setText(name);
        String description  = getIntent().getStringExtra("Description");
        textDescription.setText(description);
        int imag = Objects.requireNonNull(getIntent().getExtras()).getInt("Image");
        image.setImageResource(imag);
    }

    public void click(View view) {
    }
}

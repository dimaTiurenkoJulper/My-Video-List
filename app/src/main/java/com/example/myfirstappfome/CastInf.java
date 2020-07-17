package com.example.myfirstappfome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstappfome.DataClasses.CastFullInfo;
import com.example.myfirstappfome.DataClasses.MovieFullInfo;

public class CastInf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_inf);
        CastFullInfo cast = (CastFullInfo) getIntent().getSerializableExtra("Cast");
        TextView textName = (TextView) findViewById(R.id.MovieName);
        TextView textDescription = (TextView) findViewById(R.id.MovieDescription);
        TextView textComment = (TextView) findViewById(R.id.MovieComment);
        ImageView image = (ImageView) findViewById(R.id.MovieImage);
        ImageView favorite = (ImageView) findViewById(R.id.favorite);

        //String name  = getIntent().getStringExtra("name");
        assert cast != null;
        textName.setText(cast.getName());
        //String description  = getIntent().getStringExtra("Description");
        textDescription.setText(cast.getDescription());
        //int imag = Objects.requireNonNull(getIntent().getExtras()).getInt("Image");
        image.setImageResource(cast.getImage());
    }
}

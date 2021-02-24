package com.example.myfirstappfome.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myfirstappfome.DataClasses.MovieFullInfo;
import com.example.myfirstappfome.R;
import com.example.myfirstappfome.ui.Casts.CastsActivity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;


/**
 * this activity show all information about movie object
 */
public class MovieInfo extends AppCompatActivity {
    private static final String MOVIE = "movie";
    private Context context = this;
    MovieFullInfo movie;
    TextView textName;
    TextView textDescription;
    TextView notice;
    ImageView image;
    ImageView favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        initUiEl();
        //String name  = getIntent().getStringExtra("name");
        textName.setText(Objects.requireNonNull(movie).getName());
        //String description  = getIntent().getStringExtra("Description");
        textDescription.setText(movie.getDescription());
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        storageRef.child(movie.getImage()).getDownloadUrl().addOnSuccessListener(uri -> Glide.with(context).load(uri).into(image)
        );
    }

    void initUiEl() {
        movie = (MovieFullInfo) getIntent().getSerializableExtra(MOVIE);
        textName = findViewById(R.id.MovieName);
        textDescription = findViewById(R.id.MovieDescription);
        notice = findViewById(R.id.MovieComment);
        image = findViewById(R.id.MovieImg);
        favorite = findViewById(R.id.favorite);
    }

    public void click(View view) {
        Intent intent = new Intent(this, CastsActivity.class);
        intent.putExtra(MOVIE, movie);
        startActivity(intent);
    }

    public void goIntoSite(View view) {
        Toast.makeText(this , "Not yet implement", Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(this, CastsActivity.class);
//        intent.putExtra("Url", getIntent().getSerializableExtra(MOVIE));
//        startActivity(intent);
    }
}

package com.example.myfirstappfome.ui.Casts.addcast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myfirstappfome.DataClasses.MovieFullInfo;
import com.example.myfirstappfome.R;

import androidx.appcompat.app.AppCompatActivity;

public class AddCast extends AppCompatActivity implements View.OnClickListener {
    private MovieFullInfo movie;
    private final static String ADD_NAME = "name";
    private final static String ADD_DESCRIPTION = "description";
    private final static String ADD_COMMENT = "comment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cast);
        movie = (MovieFullInfo) getIntent().getSerializableExtra("movie");

    }


    @Override
    public void onClick(View view) {
        EditText textName = findViewById(R.id.CastName);
        EditText textDescription = findViewById(R.id.CastDescription);
        EditText textComment = findViewById(R.id.CastComment);
        Intent intent = new Intent(this, AddCastImage.class);
        String name = textName.getText().toString();
        intent.putExtra("movie", movie);
        intent.putExtra(ADD_NAME, name);
        intent.putExtra(ADD_DESCRIPTION, textDescription.getText().toString());
        intent.putExtra(ADD_COMMENT, textComment.getText().toString());
        startActivity(intent);
    }
}
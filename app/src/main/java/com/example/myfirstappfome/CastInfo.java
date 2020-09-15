package com.example.myfirstappfome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstappfome.DataClasses.CastFullInfo;
import com.example.myfirstappfome.DataClasses.MovieFullInfo;

import java.util.Objects;

/***
 * Activity , which show information about Cast
 */
public class CastInfo extends AppCompatActivity {
    private static final String CAST = "Cast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_info);
        CastFullInfo cast = (CastFullInfo) getIntent().getSerializableExtra(CAST);
        TextView textName = (TextView) findViewById(R.id.CastName);
        TextView textDescription = (TextView) findViewById(R.id.CastDescription);
        TextView textComment = (TextView) findViewById(R.id.CastComment);
        ImageView image = (ImageView) findViewById(R.id.CastImage);
        ImageView favorite = (ImageView) findViewById(R.id.favorite);

        //String name  = getIntent().getStringExtra("name");
        textName.setText(Objects.requireNonNull(cast).getName());
        //String description  = getIntent().getStringExtra("Description");
        textDescription.setText(cast.getDescription());
        //int imag = Objects.requireNonNull(getIntent().getExtras()).getInt("Image");
        image.setImageBitmap(cast.getImage());
    }
}

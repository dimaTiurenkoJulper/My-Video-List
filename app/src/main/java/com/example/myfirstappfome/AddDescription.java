package com.example.myfirstappfome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddDescription extends AppCompatActivity {

    //private String name = getIntent().getStringExtra("name");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_description);


    }

    public void ConfirmData(View view) {
        EditText description;
        Intent intent = new Intent(this, AddImage.class);
        description = (EditText) view.findViewById(R.id.MovieDescription);
        //Intent Confirmdata = new Intent(this,ConfirmData.class);
       // Confirmdata.putExtra("Description" , description.getText());
        //intent.putExtra("Description", description.getText());
        //intent.putExtra("name", name);
        startActivity(intent);
    }
}

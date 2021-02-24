package com.example.myfirstappfome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/***
 *this class show login screen and check password
 */
public class StartScreen extends AppCompatActivity {
    private final static String PASSWORD = "d";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void LogOnClick(View view) {
        intent = new Intent(this, MainScreen.class);
        EditText editText = findViewById(R.id.MainTextBox);
        if (!PASSWORD.contentEquals(editText.getText())) {
            Toast toast = Toast.makeText(this, R.string.pass, Toast.LENGTH_LONG);
            toast.show();
            super.recreate();
        } else {
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
        }
    }
}

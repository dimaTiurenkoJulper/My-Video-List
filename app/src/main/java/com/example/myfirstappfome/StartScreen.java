package com.example.myfirstappfome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myfirstappfome.DataClasses.MyMovie;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/***
 *this class show login screen and check password
 */
public class StartScreen extends AppCompatActivity {
    private final static String PASSWORD = "d";
    private final static String MOVIE_DESCRIPTION = "d";
    private final static String MOVIE_NAME = "d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient GoogleSingIn = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    }

    public void LogOnClick(View view) {
        EditText editText = (EditText) findViewById(R.id.MainTextBox);
        if (!PASSWORD.contentEquals(editText.getText())) {
            Toast toast = Toast.makeText(this, R.string.pass, Toast.LENGTH_LONG);
            toast.show();
            super.recreate();
        } else {
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference ref = db.getReference("movies"); // Key
            MyMovie movie = new MyMovie(MOVIE_NAME, MOVIE_DESCRIPTION, R.drawable.avatar);
            ref.setValue(movie); // Value
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
        }
    }
}
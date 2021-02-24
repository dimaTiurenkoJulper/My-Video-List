package com.example.myfirstappfome.ui.Casts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myfirstappfome.Adapters.CastsAdapter;
import com.example.myfirstappfome.DataClasses.CastFullInfo;
import com.example.myfirstappfome.DataClasses.MovieFullInfo;
import com.example.myfirstappfome.R;
import com.example.myfirstappfome.ui.Casts.addcast.AddCast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

/***
 * Activity for show all Cast element in RecycleView
 */
public class CastsActivity extends AppCompatActivity {
    private List<CastFullInfo> castsList = new ArrayList<>();
    private CastsAdapter adapter;
    private static final String MOVIE = "movie";
    private static final String CAST = "Cast";
    MovieFullInfo movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casts);
        RecyclerView recyclerView = findViewById(R.id.list);
        movie = (MovieFullInfo) getIntent().getSerializableExtra(MOVIE);
        adapter = new CastsAdapter(this, castsList, (v, myCast) -> {
            Intent intent = new Intent(v.getContext(), CastInfo.class);
            intent.putExtra(CAST, myCast);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
        setInitialData();
    }

    private void setInitialData() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(movie.getName()+ "/casts/");
        ref.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            }
            else {
                for (DataSnapshot postSnapshot : task.getResult().getChildren()) {
                    adapter.addItem(postSnapshot.getValue(CastFullInfo.class));
                }
            }
        });
    }

        public void addCast(View view) {
        Intent intent = new Intent(this, AddCast.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}
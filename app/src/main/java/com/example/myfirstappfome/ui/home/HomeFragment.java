package com.example.myfirstappfome.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfirstappfome.Adapters.MoviesAdapter;
import com.example.myfirstappfome.DataClasses.MovieFullInfo;
import com.example.myfirstappfome.DataClasses.Movies;
import com.example.myfirstappfome.R;
import com.example.myfirstappfome.ui.MovieInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

/**
 * fragment , which show movie list
 */
public class HomeFragment extends Fragment {
    static Movies movies = Movies.getInstance();
    private List<MovieFullInfo> movieList = new ArrayList<>();
    private MoviesAdapter adapter;
    private static final String MOVIE = "movie";
    //private homeModel myHomeModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.list);
        adapter = new MoviesAdapter(getContext(), movieList, /* onClick in adapter*/(v, myMovie) -> {
            Intent intent = new Intent(getContext(), MovieInfo.class);
            intent.putExtra(MOVIE, myMovie);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
        setInitialData();
        return root;
    }

    private void setInitialData() {
        ArrayList<MovieFullInfo> list = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });
        ref.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                for (DataSnapshot postSnapshot : task.getResult().getChildren()) {
                    MovieFullInfo movie = postSnapshot.getValue(MovieFullInfo.class);
                    adapter.addItem(movie);
                    list.add(movie);
                    movies.setList(list);
                }
            }
        });
    }
}
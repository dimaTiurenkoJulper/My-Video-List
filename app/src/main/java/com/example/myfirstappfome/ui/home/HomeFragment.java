package com.example.myfirstappfome.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myfirstappfome.Adapters.MoviesAdapter;
import com.example.myfirstappfome.Adapters.SaveMovieList;
import com.example.myfirstappfome.DataClasses.MovieFullInfo;
import com.example.myfirstappfome.DataClasses.MyMovie;
import com.example.myfirstappfome.MainScreen;
import com.example.myfirstappfome.MovieInfo;
import com.example.myfirstappfome.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * fragment , which show movie list
 */
public class HomeFragment extends Fragment {
    private List<MyMovie> movieList = new ArrayList<>();
    private MoviesAdapter adapter;
    private static final String MOVIE = "movie";
    //private homeModel myHomeModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        setInitialData();

        RecyclerView recyclerView = root.findViewById(R.id.list);
        adapter = new MoviesAdapter(getContext(), movieList, /* onClick in adapter*/(v, myMovie) -> {
            Intent intent = new Intent(getContext(), MovieInfo.class);
            intent.putExtra(MOVIE, myMovie);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
        return root;
    }

    private void setInitialData() {
        ArrayList  <MyMovie> list = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    MovieFullInfo movieInfo = postSnapshot.getValue(MovieFullInfo.class);
                    MyMovie movie = new MyMovie(Objects.requireNonNull(movieInfo).getName(), movieInfo.getDescription(), movieInfo.getImage(),movieInfo.getIsFavorite());
                    adapter.addItem(movie);
                   // Toast toas = Toast.makeText(getContext()," show " + movie.isFavorite(), Toast.LENGTH_LONG);
                    MainScreen.setAppMovieList(new SaveMovieList(list));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error handling
            }
        });
    }
}
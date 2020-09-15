package com.example.myfirstappfome.ui.home;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myfirstappfome.Adapters.MoviesAdapter;
import com.example.myfirstappfome.DataClasses.CastFullInfo;
import com.example.myfirstappfome.DataClasses.MovieFullInfo;
import com.example.myfirstappfome.DataClasses.MyMovie;
import com.example.myfirstappfome.MovieInfo;
import com.example.myfirstappfome.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
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

        // myHomeModel = ViewModelProviders.of(this).get(homeModel.class);
        // myHomeModel.setInitialData();
        setInitialData();
        RecyclerView recyclerView = root.findViewById(R.id.list);
        // create adapter
        adapter = new MoviesAdapter(getContext(), movieList, /* onClick in adapter*/(v, myMovie) -> {
            Log.i(TAG, "Click !" + myMovie.getName());
           // MovieFullInfo movie = new MovieFullInfo(myMovie.getName(), myMovie.getDescription(), myMovie.getImage());
            Intent intent = new Intent(getContext(), MovieInfo.class);
            intent.putExtra(MOVIE, myMovie);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
        return root;
    }

    /*0IKeAqYUdDw7CgwDJz4Hv67mYcrHdKPN4kTXVi6q
    https://myfirstappfome.firebaseio.com/*/
    private void setInitialData() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(); // Key

        // MyMovie secmov = new MyMovie("Avatar", "film about avatar " , R.drawable.avatar);
        // movieList.add(secmov);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve latest value
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    MovieFullInfo movieInfo = postSnapshot.getValue(MovieFullInfo.class);
                    Log.i(TAG, postSnapshot.child("name").getValue(String.class) + postSnapshot.child("description").getValue(String.class)
                          + R.drawable.avatar);
                   MyMovie movie = new MyMovie(movieInfo.getName(),movieInfo.getDescription(), movieInfo.getImage());
                   adapter.addItem(movie);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error handling
            }
        });
    }
}
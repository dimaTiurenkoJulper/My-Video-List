package com.example.myfirstappfome.ui.favorite;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myfirstappfome.Adapters.MoviesAdapter;
import com.example.myfirstappfome.DataClasses.MyMovie;
import com.example.myfirstappfome.MainScreen;
import com.example.myfirstappfome.MovieInfo;
import com.example.myfirstappfome.R;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FavoriteFragment extends Fragment {
    private static final String MOVIE = "movie";

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.favoriteList);
        MoviesAdapter adapter = new MoviesAdapter(getContext(), getFavoriteList(), (v, myMovie) -> {
            Intent intent = new Intent(getContext(), MovieInfo.class);
            intent.putExtra(MOVIE, myMovie);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
        return view;
    }

    private List<MyMovie> getFavoriteList() {
        ArrayList<MyMovie> favoriteList = new ArrayList<>();
        for (MyMovie movie : MainScreen.getAppMoivieLsist().getMovieList()) {
            Toast toas = Toast.makeText(getContext()," show " + movie.getIsFavorite(), Toast.LENGTH_LONG);
            toas.show();
            if (movie.getIsFavorite()) {
                favoriteList.add(movie);
            }
        }
        return favoriteList;
    }
}

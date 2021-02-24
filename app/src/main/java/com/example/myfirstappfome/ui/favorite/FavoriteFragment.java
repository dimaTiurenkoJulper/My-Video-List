package com.example.myfirstappfome.ui.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfirstappfome.Adapters.MoviesAdapter;
import com.example.myfirstappfome.DataClasses.MovieFullInfo;
import com.example.myfirstappfome.DataClasses.Movies;
import com.example.myfirstappfome.R;
import com.example.myfirstappfome.ui.MovieInfo;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

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

    private List<MovieFullInfo> getFavoriteList() {
        ArrayList<MovieFullInfo> favoriteList = new ArrayList<>();
        for (MovieFullInfo movie : Movies.getInstance().getList()) {
            if (movie.getIsFavorite()) {
                favoriteList.add(movie);
            }
        }
        return favoriteList;
    }
}

package com.example.myfirstappfome.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myfirstappfome.DataClasses.MovieFullInfo;
import com.example.myfirstappfome.DataClasses.Movies;
import com.example.myfirstappfome.R;

import java.util.List;
import java.util.Random;

import androidx.fragment.app.Fragment;


public class getRandomVideo extends Fragment {
    Movies movies = Movies.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_random, container, false);
        Button randomButton = view.findViewById(R.id.get_Random);
        randomButton.setOnClickListener(view1 -> {
            List<MovieFullInfo> myList = movies.getList();
            MovieFullInfo randomMovie = myList.get(new Random().nextInt(myList.size()));
            startActivity(new Intent(getContext(), MovieInfo.class).putExtra("movie", randomMovie));
        });
        return view;
    }
}

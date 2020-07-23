package com.example.myfirstappfome;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfirstappfome.Adapters.CastsAdapter;
import com.example.myfirstappfome.DataClasses.CastFullInfo;
import com.example.myfirstappfome.DataClasses.Cast;
import com.example.myfirstappfome.DataClasses.MovieFullInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/***
 * fragment , which show Recycle View for Casts
 */
public class CastFragment extends Fragment {
    private List<Cast> castsList = new ArrayList<>();
    private CastsAdapter adapter;
    private static final String MOVIE = "movie";
    private static final String CAST = "Cast";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cast_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.list);
        MovieFullInfo movie = (MovieFullInfo) getActivity().getIntent().getSerializableExtra(MOVIE);
        setInitialData(Objects.requireNonNull(movie));
        adapter = new CastsAdapter(getContext(), castsList, (v, myCast) -> {
            Intent intent = new Intent(v.getContext(), CastInfo.class);
            intent.putExtra(CAST, myCast);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void setInitialData(MovieFullInfo movie) {
        for (CastFullInfo cast : movie.getCasts()) {
            adapter.addItem(new CastFullInfo(cast.getName(), cast.getDescription(), cast.getImage()));
        }
    }
}
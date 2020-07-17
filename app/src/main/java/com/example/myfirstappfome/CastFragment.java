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
import com.example.myfirstappfome.DataClasses.Casts;
import com.example.myfirstappfome.DataClasses.MovieFullInfo;

import java.util.ArrayList;
import java.util.List;


public class CastFragment extends Fragment {
    private List<Casts> castsList = new ArrayList<>();
    private CastsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cast_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.list);
        MovieFullInfo movie = (MovieFullInfo) getActivity().getIntent().getSerializableExtra("movie");
        adapter = new CastsAdapter(getContext(), castsList, (v, myCast) -> {
            Intent intent = new Intent(v.getContext(), CastInf.class);
            intent.putExtra("Cast", myCast);
            startActivity(intent);
        });
        assert movie != null;
        setInitialData(movie);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void setInitialData(MovieFullInfo movie) {
        assert movie != null;
        for (CastFullInfo cast : movie.getCasts()) {
            adapter.addItems(new CastFullInfo(cast.getName(), cast.getDescription(), cast.getImage()));
        }
    }
}
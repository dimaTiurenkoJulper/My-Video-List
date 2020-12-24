package com.example.myfirstappfome;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfirstappfome.Adapters.CastsAdapter;
import com.example.myfirstappfome.DataClasses.Cast;
import com.example.myfirstappfome.DataClasses.CastFullInfo;
import com.example.myfirstappfome.DataClasses.MyMovie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;

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
        MyMovie movie = (MyMovie) requireActivity().getIntent().getSerializableExtra(MOVIE);
        setInitialData(Objects.requireNonNull(movie));
        adapter = new CastsAdapter(getContext(), castsList, (v, myCast) -> {
            Intent intent = new Intent(v.getContext(), CastInfo.class);
            intent.putExtra(CAST, myCast);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void setInitialData(MyMovie movie) {
        for (CastFullInfo cast : movie.getCasts()) {
            if (cast != null) {
                adapter.addItem(new CastFullInfo(cast.getName(), cast.getDescription(), cast.getImage()));
            } else {
                Log.i(TAG, "this movie don`t have any added cast");
            }
        }
    }
}
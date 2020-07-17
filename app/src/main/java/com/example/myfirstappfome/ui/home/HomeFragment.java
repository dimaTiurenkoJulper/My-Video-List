package com.example.myfirstappfome.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myfirstappfome.Adapters.MoviesAdapter;
import com.example.myfirstappfome.DataClasses.CastFullInfo;
import com.example.myfirstappfome.DataClasses.MovieFullInfo;
import com.example.myfirstappfome.DataClasses.MyMovie;
import com.example.myfirstappfome.MovieInf;
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
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment {
    private List<MyMovie> movieList = new ArrayList<>();
    private MoviesAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        setInitialData();
        RecyclerView recyclerView = root.findViewById(R.id.list);
        // create adapter
        adapter = new MoviesAdapter(getContext(), movieList, /* onClick in adapter*/(v, myMovie) -> {
            MovieFullInfo movie = new MovieFullInfo(myMovie.getName(), myMovie.getDescription(), myMovie.getImage());
            movie.addCast(new CastFullInfo("onidzuka", " Pro" , R.drawable.avatar));
            Intent intent = new Intent(v.getContext(), MovieInf.class);
            intent.putExtra("movie", movie);
            startActivity(intent);
            Toast toast = Toast.makeText(v.getContext(), "Click !" + myMovie.getName(), Toast.LENGTH_LONG);
            toast.show();
        });
        recyclerView.setAdapter(adapter);

        return root;
    }

    //0IKeAqYUdDw7CgwDJz4Hv67mYcrHdKPN4kTXVi6q
    //https://myfirstappfome.firebaseio.com/
    private void setInitialData() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(); // Key

        // MyMovie secmov = new MyMovie("Avatar", "film about avatar " , R.drawable.avatar);
        // movieList.add(secmov);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve latest value
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    MyMovie movie = postSnapshot.getValue(MyMovie.class);
                    Toast toast = Toast.makeText(getContext(), postSnapshot.child("name").getValue(String.class)
                            + postSnapshot.child("description").getValue(String.class)
                            + R.drawable.avatar, Toast.LENGTH_LONG);
                    toast.show();
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

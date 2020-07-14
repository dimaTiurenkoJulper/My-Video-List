package com.example.myfirstappfome.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myfirstappfome.Adapters.MoviesAdapter;
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
    List<MyMovie> movieList = new ArrayList<MyMovie>();
    private MoviesAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        setInitialData();
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.list);
        // create adapter
        adapter = new MoviesAdapter(getContext(), movieList , /* onClick in adapter*/(v, myMovie) -> {

            MovieFullInfo movie = new MovieFullInfo(myMovie.getName(), myMovie.getDescription(),myMovie.getImage());
            Intent intent = new Intent(v.getContext(), MovieInf.class);
            intent.putExtra("Movie",  movie);
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

        MyMovie secmov = new MyMovie("Avatar", "film about avatar " , R.drawable.avatar);
        // Attach listener
        movieList.add(secmov);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve latest value
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    MyMovie myMovie2 = postSnapshot.getValue(MyMovie.class);
                    MyMovie myMovie = new MyMovie(postSnapshot.child("name").getValue(String.class),postSnapshot.child("description").getValue(String.class), R.drawable.avatar);
                    Toast toast = Toast.makeText(getContext(), postSnapshot.child("name").getValue(String.class)
                            + postSnapshot.child("description").getValue(String.class)
                            + R.drawable.avatar , Toast.LENGTH_LONG);
                    toast.show();
                    adapter.addItem(myMovie2);
                    adapter.addItem(myMovie);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Error handling
            }
        });
    }
}

package com.example.myfirstappfome.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstappfome.Adaptor;
import com.example.myfirstappfome.DataClasses.MyMovie;
import com.example.myfirstappfome.MovieInf;
import com.example.myfirstappfome.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment {
    List<MyMovie> movieList = new ArrayList<MyMovie>();
    private Adaptor adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        setInitialData();
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.list);
        // create adapter
        adapter = new Adaptor(getContext(), movieList , /* onClick in adapter*/(v, myMovie) -> {


            Intent intent = new Intent(v.getContext(), MovieInf.class);
            intent.putExtra("name", myMovie.getName());
            intent.putExtra("Image", myMovie.getImage());
            intent.putExtra("Description", myMovie.getDescription());
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

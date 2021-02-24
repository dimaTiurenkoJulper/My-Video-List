package com.example.myfirstappfome.ui.home;

import android.util.Log;

import com.example.myfirstappfome.DataClasses.MovieFullInfo;
import com.example.myfirstappfome.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import static androidx.constraintlayout.widget.Constraints.TAG;

class homeModel extends ViewModel {
     void setInitialData() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(); // Key

        // MovieFullInfo secmov = new MovieFullInfo("Avatar", "film about avatar " , R.drawable.avatar);
        // movieList.add(secmov);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve latest value
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    MovieFullInfo movie = postSnapshot.getValue(MovieFullInfo.class);
                    Log.i(TAG, postSnapshot.child("name").getValue(String.class) + postSnapshot.child("description").getValue(String.class)
                            + R.drawable.avatar);
                    // adapter.addItem(movie);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error handling
            }
        });
    }
}

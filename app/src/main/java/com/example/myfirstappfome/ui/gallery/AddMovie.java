package com.example.myfirstappfome.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myfirstappfome.AddDescription;
import com.example.myfirstappfome.R;

/**
 * in this class u can add new movie into the firebase
 */
public class AddMovie extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    public void ConfirmData(View view) {
        Intent intent = new Intent(getContext(), AddDescription.class);
        //Intent Confirmdata = new Intent(getContext(),ConfirmData.class);
        TextView name = (TextView) view.findViewById(R.id.MovieName);
        // Confirmdata.putExtra("name" , name.getText());
        getContext().startActivity(intent);
    }
}

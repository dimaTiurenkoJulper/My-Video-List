package com.example.myfirstappfome.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myfirstappfome.AddImage;
import com.example.myfirstappfome.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * in this class u can add new movie into the firebase
 */
public class AddMovie extends Fragment {

    final static String ADD_NAME = "name";
    final static String ADD_DESCRIPTION = "description";
    final static String ADD_COMMENT = "comment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View layoutView = inflater.inflate(R.layout.fragment_add_movie, container, false);
        Button pickImage = (Button) layoutView.findViewById(R.id.action);
        //Настраиваем для нее обработчик нажатий OnClickListener:
        pickImage.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             EditText textName = (EditText) layoutView.findViewById(R.id.AddMovieName);
                                             EditText textDescription = (EditText) layoutView.findViewById(R.id.AddMovieDescription);
                                             EditText textComment = (EditText) layoutView.findViewById(R.id.AddMovieComment);
                                             Intent intent = new Intent(getContext(), AddImage.class);
                                             String name = textName.getText().toString();
                                             intent.putExtra(ADD_NAME, name);
                                             intent.putExtra(ADD_DESCRIPTION, textDescription.getText().toString());
                                             intent.putExtra(ADD_COMMENT, textComment.getText().toString());
                                             startActivity(intent);
                                         }
                                     }
        );
        return layoutView;
    }
}

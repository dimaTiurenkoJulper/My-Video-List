package com.example.myfirstappfome.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myfirstappfome.AddImage;
import com.example.myfirstappfome.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * in this class u can add new movie into the firebase
 */
public class AddMovie extends Fragment {

    private final static String ADD_NAME = "name";
    private final static String ADD_DESCRIPTION = "description";
    private final static String ADD_COMMENT = "comment";
    private final static String ADD_FAVORITE = "favorite";
    private boolean isFavorite = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View layoutView = inflater.inflate(R.layout.fragment_add_movie, container, false);
        ImageView image = layoutView.findViewById(R.id.IsFavorite);
        image.setImageResource(R.drawable.ic_empty_star);
        image.setOnClickListener(view -> {
            if (!isFavorite) {
                image.setImageResource(R.drawable.ic_star);
                isFavorite = true;
            } else {
                image.setImageResource(R.drawable.ic_empty_star);
                isFavorite = false;
            }
        });
        Button pickImage = layoutView.findViewById(R.id.action);
        pickImage.setOnClickListener(view -> {
                    EditText textName = layoutView.findViewById(R.id.AddMovieName);
                    EditText textDescription = layoutView.findViewById(R.id.AddMovieDescription);
                    EditText textComment = layoutView.findViewById(R.id.AddMovieComment);
                    Intent intent = new Intent(getContext(), AddImage.class);
                    String name = textName.getText().toString();
                    intent.putExtra(ADD_NAME, name);
                    intent.putExtra(ADD_FAVORITE, isFavorite);
                    intent.putExtra(ADD_DESCRIPTION, textDescription.getText().toString());
                    intent.putExtra(ADD_COMMENT, textComment.getText().toString());
                    startActivity(intent);
                }
        );
        return layoutView;
    }
}

package com.example.myfirstappfome.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myfirstappfome.DataClasses.MovieFullInfo;
import com.example.myfirstappfome.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/***
 * Class for create relations with data class movie and RecycleView and show movie elements
 * {@link com.example.myfirstappfome.DataClasses.MovieFullInfo}
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<MovieFullInfo> movies;
    private ClickProcess<View, MovieFullInfo> clickAction;
    private Context context ;

    public MoviesAdapter(Context context, List<MovieFullInfo> movies, ClickProcess<View, MovieFullInfo> clickAction) {
        this.movies = new ArrayList<>(movies);
        this.inflater = LayoutInflater.from(context);
        this.clickAction = clickAction;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.movie, parent,  false);
        return new ViewHolder(view);
    }

    /***
     * method add movie in RecycleView
     * @param movie item , which will be added in recycle View
     */
    public void addItem(MovieFullInfo movie) {
        movies.add(movie);
        notifyItemInserted(movies.size() - 1);

    }

    @Override
    public void onBindViewHolder(MoviesAdapter.ViewHolder holder, int position) {
        final MovieFullInfo myMovie = movies.get(position);
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();

        storageRef.child(myMovie.getImage()).getDownloadUrl().addOnSuccessListener(uri -> Glide.with(context).load(uri).into(holder.imageView))
                .addOnFailureListener(exception -> Log.i("Error",  "error with download image in movie list"));

        //holder.imageView.setImageResource(myMovie.getImage());
        holder.nameView.setText(myMovie.getName());
        holder.descriptionView.setText(myMovie.getDescription());
        holder.itemView.setOnClickListener(v -> {
            clickAction.get(v, myMovie);
//                Intent intent = new Intent(v.getContext(),MovieInf.class);
//                intent.putExtra("name", myMovie.getName());
//                intent.putExtra("image", myMovie.getImage());
//                intent.putExtra("Description", myMovie.getDescription());
//                startActivity(intent);
//                Toast toast = Toast.makeText(v.getContext(), "Click !" + myMovie.getName(),Toast.LENGTH_LONG);
//                toast.show();
        });
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView, descriptionView;

        ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
            nameView = view.findViewById(R.id.name);
            descriptionView = view.findViewById(R.id.description);
        }
    }
}

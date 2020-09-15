package com.example.myfirstappfome.Adapters;

import android.content.Context;
import android.net.Uri;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myfirstappfome.ClickProcess;
import com.example.myfirstappfome.DataClasses.MyMovie;
import com.example.myfirstappfome.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;

/***
 * Class for create relations with data class movie and RecycleView and show movie elements
 * {@link com.example.myfirstappfome.DataClasses.MovieFullInfo}
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<MyMovie> movies;
    private ClickProcess<View, MyMovie> clickAction;
    private Context context ;

    public MoviesAdapter(Context context, List<MyMovie> movies, ClickProcess<View, MyMovie> clickAction) {
        this.movies = new ArrayList(movies);
        this.inflater = LayoutInflater.from(context);
        this.clickAction = clickAction;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.movie, parent,  false);
        return new ViewHolder(view);
    }

    /***
     * method add movie in RecycleView
     * @param movie item , which will be added in recycle View
     */
    public void addItem(MyMovie movie) {
        movies.add(movie);
        notifyItemInserted(movies.size() - 1);

    }

    @Override
    public void onBindViewHolder(MoviesAdapter.ViewHolder holder, int position) {
        final MyMovie myMovie = movies.get(position);
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();

        storageRef.child(myMovie.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri).into(holder.imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.i("Error",  "error with download image in movie list");
            }
        });

        //holder.imageView.setImageResource(myMovie.getImage());
        holder.nameView.setText(myMovie.getName());
        holder.descriptionView.setText(myMovie.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAction.get(v, myMovie);
//                Intent intent = new Intent(v.getContext(),MovieInf.class);
//                intent.putExtra("name", myMovie.getName());
//                intent.putExtra("image", myMovie.getImage());
//                intent.putExtra("Description", myMovie.getDescription());
//                startActivity(intent);
//                Toast toast = Toast.makeText(v.getContext(), "Click !" + myMovie.getName(),Toast.LENGTH_LONG);
//                toast.show();
            }
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
            imageView = (ImageView) view.findViewById(R.id.image);
            nameView = (TextView) view.findViewById(R.id.name);
            descriptionView = (TextView) view.findViewById(R.id.description);
        }
    }
}

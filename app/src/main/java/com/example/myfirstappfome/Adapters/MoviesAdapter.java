package com.example.myfirstappfome.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstappfome.Custom;
import com.example.myfirstappfome.DataClasses.MyMovie;
import com.example.myfirstappfome.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<MyMovie> movies;
    private Custom<View, MyMovie> clickAction;

    public MoviesAdapter(Context context, List<MyMovie> movies, Custom<View , MyMovie>  clickAction) {
        this.movies = new ArrayList(movies);
        this.inflater = LayoutInflater.from(context);
        this.clickAction = clickAction;
    }
    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.movie, parent, false);
        return new ViewHolder(view);
    }

    public void addItem(MyMovie movie) {
        movies.add(movie);
        notifyItemInserted(movies.size() - 1);
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.ViewHolder holder, int position) {
        final MyMovie myMovie = movies.get(position);
        holder.imageView.setImageResource(myMovie.getImage());
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView, descriptionView;
        ViewHolder(View view){
            super(view);
            imageView = (ImageView)view.findViewById(R.id.image);
            nameView = (TextView) view.findViewById(R.id.name);
            descriptionView = (TextView) view.findViewById(R.id.description);
        }
    }
}

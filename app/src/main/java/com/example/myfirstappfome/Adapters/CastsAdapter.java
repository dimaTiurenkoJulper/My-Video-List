package com.example.myfirstappfome.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstappfome.CastFragment;
import com.example.myfirstappfome.Custom;
import com.example.myfirstappfome.DataClasses.CastFullInfo;
import com.example.myfirstappfome.DataClasses.Casts;
import com.example.myfirstappfome.DataClasses.MyMovie;
import com.example.myfirstappfome.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CastsAdapter extends RecyclerView.Adapter<CastsAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<CastFullInfo> casts ;
    private Custom<View, CastFullInfo> clickAction;

    public CastsAdapter(Context context, List<Casts> casts , Custom<View, CastFullInfo> clickAction) {
        this.casts = new ArrayList(casts);
        this.inflater = LayoutInflater.from(context);
        this.clickAction = clickAction;
    }

    @NonNull
    @Override
    public CastsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.movie, parent, false);
        return new CastsAdapter.ViewHolder(view);
    }

    public void addItems(CastFullInfo cast) {
        casts.add(cast);
        notifyItemInserted(casts.size() - 1);
    }

    @Override
    public void onBindViewHolder(CastsAdapter.ViewHolder holder, int position) {
        final CastFullInfo myCast = casts.get(position);
        holder.imageView.setImageResource(myCast.getImage());
        holder.nameView.setText(myCast.getName());
        holder.descriptionView.setText(myCast.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAction.get(v, myCast);
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
        return casts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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

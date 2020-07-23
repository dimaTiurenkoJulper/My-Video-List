package com.example.myfirstappfome.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstappfome.ClickProcess;
import com.example.myfirstappfome.DataClasses.CastFullInfo;
import com.example.myfirstappfome.DataClasses.Cast;
import com.example.myfirstappfome.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/***
 * Class for create relations with data class Cast and RecycleView and show cast elements
 * {@link Cast}
 */
public class CastsAdapter extends RecyclerView.Adapter<CastsAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<CastFullInfo> casts;
    private ClickProcess<View, CastFullInfo> clickAction;

    /***
     * class constructor with this param:
     * @param context
     * @param casts
     * @param clickAction
     */
    public CastsAdapter(Context context, List<Cast> casts, ClickProcess<View, CastFullInfo> clickAction) {
        this.casts = new ArrayList(casts);
        this.inflater = LayoutInflater.from(context);
        this.clickAction = clickAction;
    }
    @NonNull
    @Override
    public CastsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.movie, parent, false);
        return new CastsAdapter.ViewHolder(view);
    }

    /***
     * method , added Cast object in list
     * @param cast - object , which will be added in list
     */
    public void addItem(CastFullInfo cast) {
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
//                Intent intent = new Intent(v.getContext(),MovieInfo.class);
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

    protected class ViewHolder extends RecyclerView.ViewHolder {
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

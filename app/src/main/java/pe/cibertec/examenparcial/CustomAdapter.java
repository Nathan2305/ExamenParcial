package pe.cibertec.examenparcial;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context context;
    ArrayList<Job> list;

    public CustomAdapter(Context context, ArrayList<Job> list) {
        this.context = context;
        this.list = list;

    }
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.each_job, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.company.setText(list.get(i).getCompany());
        viewHolder.description.setText(list.get(i).getDescription());
        Glide.with(context).load(list.get(i).getCompany_logo()).into(viewHolder.fotoCompany);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, company;
        ImageView fotoCompany;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleJob);
            description = itemView.findViewById(R.id.descJob);
            company = itemView.findViewById(R.id.company);
            fotoCompany = itemView.findViewById(R.id.fotoCompany);
        }
    }
}

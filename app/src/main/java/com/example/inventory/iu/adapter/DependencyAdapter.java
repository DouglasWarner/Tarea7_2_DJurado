package com.example.inventory.iu.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.model.NameSort;
import com.example.inventory.data.model.ShortNameSort;
import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder> {

    public interface OnManagerDependencyListener{
        void onEditDependency(View view);
        void onDeleteDependency(Dependency dependency);
    }

    private List<Dependency> list;
    private Random rndColorLetter;

    public DependencyAdapter(List<Dependency> list) {
        this.list = list;
        rndColorLetter = new Random();
    }

    @NonNull
    @Override
    public DependencyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dependency, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DependencyAdapter.ViewHolder holder, int position) {
        holder.iconLetter.setLetter(list.get(position).getShortName());
        holder.iconLetter.setShapeColor(Color.rgb(rndColorLetter.nextInt(255),rndColorLetter.nextInt(255),rndColorLetter.nextInt(255)));
        holder.tvName.setText(list.get(position).getName());
        holder.tvDescription.setText(list.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * Metodo que actualiza los datos del RecyclerView y se DEBE llamar al metodo
     * notifyDataSetChanged() para que la vista se anule y se vuelva a mostrar
     * @param list
     */
    public void update (List<Dependency> list)
    {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        MaterialLetterIcon iconLetter;
        TextView tvName;
        TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconLetter = itemView.findViewById(R.id.iconLetter);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);

            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((OnManagerDependencyListener)v).onEditDependency(v);
                }
            });
            itemView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ((OnManagerDependencyListener)v).onDeleteDependency(null);
                    return false;
                }
            });
        }
    }


    public Dependency getDependencyItem(int position)
    {
        return list.get(position);
    }

    public void SortByShortName(boolean reverse)
    {
        List<Dependency> orderList = new ArrayList<>();
        orderList.addAll(this.list);

        if(reverse)
            Collections.sort(orderList, new ShortNameSort());
        else
            Collections.reverse(orderList);

        update(orderList);
    }

    public void SortByName(boolean reverse)
    {
        List<Dependency> orderList = new ArrayList<>();
        orderList.addAll(this.list);

        if(reverse)
            Collections.sort(orderList, new NameSort());
        else
            Collections.reverse(orderList);

        update(orderList);
    }
}

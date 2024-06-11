package com.example.recipe.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe.R;
import com.example.recipe.models.Equipment;
import com.example.recipe.models.Ingredient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionsEquipmentsAdapter extends RecyclerView.Adapter<InstructionEquipmentsViewHolder>{

    Context context;
    List<Equipment> list;

    public InstructionsEquipmentsAdapter(Context context, List<Equipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionEquipmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionEquipmentsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions_step_items, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstructionEquipmentsViewHolder holder, int position) {

        holder.textView_instructions_step_item.setText(list.get(position).name);
        holder.textView_instructions_step_item.setSelected(true);
        Picasso.get().load("https://img.spoonacular.com/equipment_100x100/"+list.get(position).image).into(holder.imageView_instructions_step_items);
    }

    @Override
    public int getItemCount() {

        return list.size();
    }
}
class InstructionEquipmentsViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView_instructions_step_items;
    TextView textView_instructions_step_item;

    public InstructionEquipmentsViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_instructions_step_items = itemView.findViewById(R.id.imageView_instructons_step_items);
        textView_instructions_step_item = itemView.findViewById(R.id.textView_instructions_step_item);
    }

}

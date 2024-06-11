package com.example.recipe.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe.R;
import com.example.recipe.models.InstructionResponse;

import java.util.List;

public class InstructionsAdapter  extends RecyclerView.Adapter<InstructionsViewHolder>{

    Context context;
    List<InstructionResponse> list;

    public InstructionsAdapter(Context context, List<InstructionResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsViewHolder holder, int position) {
        holder.textView_instruction_name.setText(list.get(position).name);
        holder.recycler_instruction_steps.setHasFixedSize(true);
        holder.recycler_instruction_steps.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        InstructionsStepAdapter  stepAdapter= new InstructionsStepAdapter (context,list.get(position).steps);
        holder.recycler_instruction_steps.setAdapter(stepAdapter);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }
}
class InstructionsViewHolder extends RecyclerView.ViewHolder{
    TextView textView_instruction_name;
    RecyclerView recycler_instruction_steps;
    public InstructionsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_instruction_name = itemView.findViewById(R.id.textView_instruction_name);
        recycler_instruction_steps = itemView.findViewById(R.id.recycler_instruction_steps);

    }
}

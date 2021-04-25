package com.utopia.vznotes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final ArrayList<State> states;

    Adapter(Context context, ArrayList<State> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        State state = states.get(position);
        holder.category.setText(state.getCategory());
        holder.text.setText(state.getText());
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button change, delete;
        TextView category, text;
        ViewHolder(View view){
            super(view);
            change = view.findViewById(R.id.change_btn);
            delete = view.findViewById(R.id.delete_btn);
            category = view.findViewById(R.id.category);
            text = view.findViewById(R.id.text);
        }
    }
}

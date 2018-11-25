package com.example.auricelia.reshipi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<String> array;

    public CustomAdapter(Context context,ArrayList<String> array) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.array = array;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_buscar_por_ingred, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.serial_number.setText(this.array.get(position));
    }

    @Override
    public int getItemCount() {
        return this.array.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView serial_number;

        public MyViewHolder(View itemView) {
            super(itemView);
            serial_number = (TextView)itemView.findViewById(R.id.textView5);
        }
    }
}
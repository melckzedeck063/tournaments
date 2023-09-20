package com.example.cive_field_cup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FixtureAdapter extends RecyclerView.Adapter<FixtureAdapter.ViewHolder> {

    private LayoutInflater  inflater;
    private ArrayList<FixturesModel> fixturesModelArrayList;
    private Context context;

    public FixtureAdapter(ArrayList<FixturesModel> fixturesModelArrayList, Context context) {
        inflater = LayoutInflater.from(context);
        this.fixturesModelArrayList = fixturesModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FixtureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.fixture_buble,parent,false);
        FixtureAdapter.ViewHolder holder=  new FixtureAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FixtureAdapter.ViewHolder holder, int position) {
        holder.game.setText(fixturesModelArrayList.get(position).getMatch_played());
        holder.date.setText(fixturesModelArrayList.get(position).getDate());
        holder.ground.setText(fixturesModelArrayList.get(position).getGround());
        holder.day.setText(fixturesModelArrayList.get(position).getDay());
        holder.time.setText(fixturesModelArrayList.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return fixturesModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView game,date,day,ground,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            game = itemView.findViewById(R.id.game_day);
            date = itemView.findViewById(R.id.date_text);
            ground = itemView.findViewById(R.id.game_ground);
            day = itemView.findViewById(R.id.day_text);
            time = itemView.findViewById(R.id.time_text);
        }
    }
}

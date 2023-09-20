package com.example.cive_field_cup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<ResulstModel> resulstModelArrayList;
    private Context context;

    public ResultsAdapter(ArrayList<ResulstModel> resulstModelArrayList, Context context) {
        inflater =  LayoutInflater.from(context);

        this.resulstModelArrayList = resulstModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ResultsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.results_buble,parent,false);
        ResultsAdapter.ViewHolder holder =  new ResultsAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsAdapter.ViewHolder holder, int position) {
        holder.game.setText(resulstModelArrayList.get(position).getGame_played());
        holder.results.setText(resulstModelArrayList.get(position).getGameResults());
        holder.motm.setText(resulstModelArrayList.get(position).getMotm());
        holder.yellow_card.setText(resulstModelArrayList.get(position).getYellow());
        holder.red_card.setText(resulstModelArrayList.get(position).getRed());
    }


    @Override
    public int getItemCount() {
        return resulstModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView game,results,yellow_card,red_card,motm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            game = itemView.findViewById(R.id.results_game_day);
            results = itemView.findViewById(R.id.results_text);
            yellow_card = itemView.findViewById(R.id.yellowCard_text);
            red_card = itemView.findViewById(R.id.redCard_text);
            motm = itemView.findViewById(R.id.motm_text);
        }
    }
}

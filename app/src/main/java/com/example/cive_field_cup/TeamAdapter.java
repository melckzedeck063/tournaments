package com.example.cive_field_cup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeamAdapter extends  RecyclerView.Adapter<TeamAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private ArrayList<TeamModel> teamModelArrayList;
    private Context context;

    public TeamAdapter(ArrayList<TeamModel> teamModelArrayList, Context context) {
        inflater = LayoutInflater.from(context);
        this.teamModelArrayList = teamModelArrayList;
        this.context = context;
    }


    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.team_buble,parent,false);
        TeamAdapter.ViewHolder holder  =   new TeamAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.team.setText(teamModelArrayList.get(position).getTeam());
        holder.coach.setText(teamModelArrayList.get(position).getCoach());
        holder.points.setText(String.valueOf(teamModelArrayList.get(position).getPoints()));
        holder.goals.setText(String.valueOf(teamModelArrayList.get(position).getGoals()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"You have clicked " + teamModelArrayList.get(position).getTeam(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView team,coach,points,goals;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            team = itemView.findViewById(R.id.teamName);
            coach = itemView.findViewById(R.id.coachName);
            points = itemView.findViewById(R.id.points_text);
            goals = itemView.findViewById(R.id.goals_text);
        }
    }
}

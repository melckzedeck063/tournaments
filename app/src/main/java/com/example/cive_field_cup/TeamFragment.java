package com.example.cive_field_cup;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class TeamFragment extends Fragment {
   TextInputLayout teamName,coachName,teamPoints,teamPosition,teamGoals,adminID;
   Button submitButton;

   private RequestQueue requestQueue;
   private StringRequest stringRequest;
   private String teamURL = "http://192.168.43.33/field/team.php";


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_team, container, false);

        coachName = view.findViewById(R.id.team_coach_name);
        teamName = view.findViewById(R.id.team_team_name);
        teamPoints = view.findViewById(R.id.team_points);
        teamPosition = view.findViewById(R.id.team_position);
        teamGoals = view.findViewById(R.id.team_goals);
        adminID = view.findViewById(R.id.admin_ID);


        submitButton = view.findViewById(R.id.submitBtn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerTeam();
            }
        });


        return view;
    }

    private  void registerTeam (){
        final String coach = coachName.getEditText().getText().toString();
        final String team = teamName.getEditText().getText().toString();
        final String points = teamPoints.getEditText().getText().toString();
        final String position = teamPosition.getEditText().getText().toString();
        final String goals = teamGoals.getEditText().getText().toString();
        final String admin = adminID.getEditText().getText().toString();

        if(coach.isEmpty()){
            coachName.getEditText().setError("coach name is required");
            coachName.getEditText().requestFocus();
            return;
        }
        if(team.isEmpty()){
            teamName.getEditText().setError("team name is  required");
            teamName.getEditText().requestFocus();
            return;
        }
        if(points.isEmpty()){
            teamPoints.getEditText().setError("points is required");
            teamPoints.getEditText().requestFocus();
            return;
        }
        if(position.isEmpty()){
            teamPosition.getEditText().setError("team position is  required");
            teamPosition.getEditText().requestFocus();
            return;
        }


        stringRequest =  new StringRequest(Request.Method.POST, teamURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String results  = jsonObject.getString("success");

                            if(results.equals("1")){
                                teamName.getEditText().setText("");
                                coachName.getEditText().setText("");

                                Toast.makeText(getContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();

                            }
                            else  {
                                Toast.makeText(getContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                        Toast.makeText(getContext(),error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("team",team);
                params.put("coach", coach);
                params.put("points",points);
                params.put("position",position);
                params.put("goals", goals);
                params.put("adminID",admin);
                return  params;
            }
        };
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}